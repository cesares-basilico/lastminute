package it.lastminute.sales.api;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.lastminute.sales.business.login.LoginBd;
import it.lastminute.sales.commonmodels.LoginRequest;
import it.lastminute.sales.commonmodels.UserDto;

@RestController
@RequestMapping("/login")
public class LoginApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    private LoginBd loginBd;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto login(@RequestBody LoginRequest loginRequest, Principal principal, HttpServletRequest httpRequest) {
        LOGGER.info("Logging in...");
        return loginBd.login(loginRequest);
    }

}
