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

import it.lastminute.sales.business.user.UserBd;
import it.lastminute.sales.commonmodels.InsertUserRequest;
import it.lastminute.sales.commonmodels.InsertUserResponse;

@RestController
@RequestMapping("/user")
public class UserApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    @Autowired
    private UserBd userBd;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public InsertUserResponse login(@RequestBody InsertUserRequest request, Principal principal,
            HttpServletRequest httpRequest) {
        LOGGER.info("Creating user...");
        return userBd.insertUser(request);
    }

}
