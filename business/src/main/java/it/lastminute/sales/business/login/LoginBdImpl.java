package it.lastminute.sales.business.login;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.lastminute.sales.business.exception.ValidationException;
import it.lastminute.sales.business.jwt.JwtUtilBd;
import it.lastminute.sales.business.utility.Constraint;
import it.lastminute.sales.commonmodels.LoginRequest;
import it.lastminute.sales.commonmodels.UserDto;
import it.lastminute.sales.dao.UserRepository;
import it.lastminute.sales.dao.entity.User;

@Component
public class LoginBdImpl implements LoginBd {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginBdImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtilBd jwtUtilBd;

    @Override
    public UserDto login(final LoginRequest request) {

        validate(request);

        final Optional<User> userOpt = userRepository.findByUsernameAndPassword(request.getUsername(),
                request.getPassword());

        if (userOpt.isPresent() && userOpt.get().getActive()) {
            final UserDto userDto = new UserDto();
            final User user = userOpt.get();
            userDto.setId(user.getId().toString());
            userDto.setName(user.getName());
            userDto.setAuthToken(jwtUtilBd.generateJwt(user));
            return userDto;
        } else {
            throw new EntityNotFoundException("No user found");
        }

    }

    private void validate(LoginRequest request) {

        Constraint.isNotNullOrEmpty(request.getPassword(), "Password could not be null", LOGGER,
                new ValidationException("Password could not be null"));
        Constraint.isNotNullOrEmpty(request.getUsername(), "Username could not be null", LOGGER,
                new ValidationException("Username could not be null"));
    }

}
