package it.lastminute.sales.business.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.lastminute.sales.business.exception.ValidationException;
import it.lastminute.sales.business.utility.Constraint;
import it.lastminute.sales.commonmodels.InsertUserRequest;
import it.lastminute.sales.commonmodels.InsertUserResponse;
import it.lastminute.sales.dao.UserRepository;
import it.lastminute.sales.dao.entity.User;

@Component
public class UserBdImpl implements UserBd {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBdImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public InsertUserResponse insertUser(InsertUserRequest request) {
        LOGGER.info("Inserting a new user");

        validate(request);

        User user = new User();
        user.setActive(request.isActive());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user = userRepository.save(user);

        final InsertUserResponse response = new InsertUserResponse();
        response.setId(user.getId().toString());
        return response;
    }

    private void validate(InsertUserRequest request) {

        Constraint.isNotNullOrEmpty(request.getName(), "Name could not be null", LOGGER,
                new ValidationException("Name could not be null"));
        Constraint.isNotNullOrEmpty(request.getPassword(), "Password could not be null", LOGGER,
                new ValidationException("Password could not be null"));
        Constraint.isNotNullOrEmpty(request.getUsername(), "Username could not be null", LOGGER,
                new ValidationException("Username could not be null"));
    }

}
