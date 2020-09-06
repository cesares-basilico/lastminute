package it.lastminute.sales.api;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.lastminute.sales.business.jwt.JwtTokenModel;
import it.lastminute.sales.business.shoppingcart.ShoppingCartBd;
import it.lastminute.sales.commonmodels.ShoppingCartRequest;
import it.lastminute.sales.commonmodels.ShoppingCartResponse;
import it.lastminute.sales.security.authentication.SalesAuthentication;

@RestController
@RequestMapping("/shoppingCart")
public class ShopApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopApiController.class);

    @Autowired
    private ShoppingCartBd shoppingCartBd;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartResponse createCart(@RequestBody ShoppingCartRequest shoppingCartRequest, Principal principal,
            HttpServletRequest httpRequest) {
        final SalesAuthentication salesAuthentication = (SalesAuthentication) principal;
        final String userId = ((JwtTokenModel) salesAuthentication.getPrincipal()).getId();
        LOGGER.info("Creating cart for userId {}", userId);
        return shoppingCartBd.createCart(shoppingCartRequest, userId);
    }

}
