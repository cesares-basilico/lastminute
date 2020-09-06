package it.lastminute.sales.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import it.lastminute.sales.business.jwt.JwtTokenModel;
import it.lastminute.sales.business.jwt.JwtUtilBd;
import it.lastminute.sales.dao.UserRepository;
import it.lastminute.sales.dao.entity.User;
import it.lastminute.sales.security.authentication.SalesAuthentication;
import it.lastminute.sales.security.authority.SalesGrantedAuthority;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtilBd jwtUtilBd;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final SalesAuthentication originalSalesAuthentication = (SalesAuthentication) authentication;

        final JwtTokenModel jwtTokenModel = jwtUtilBd.verifyJwt(originalSalesAuthentication.getJwtToken());
        if (!jwtTokenModel.isValid()) {
            throw new AccessDeniedException("User not authorized");
        }

        Optional<User> user = userRepository.findById(UUID.fromString(jwtTokenModel.getId()));

        if (user.isPresent() && user.get().getActive()) {
            final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            final SalesGrantedAuthority salesGrantedAuthority = new SalesGrantedAuthority();
            salesGrantedAuthority.setRole(jwtTokenModel.getRole());
            grantedAuthorities.add(salesGrantedAuthority);
            return new SalesAuthentication(jwtTokenModel, grantedAuthorities);
        } else {
            throw new AccessDeniedException("User not authorized");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(SalesAuthentication.class);
    }
}