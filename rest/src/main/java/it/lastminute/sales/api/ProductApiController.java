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
import it.lastminute.sales.business.product.ProductBd;
import it.lastminute.sales.commonmodels.InsertProductRequest;
import it.lastminute.sales.commonmodels.InsertProductResponse;
import it.lastminute.sales.commonmodels.ProductListResponse;
import it.lastminute.sales.security.authentication.SalesAuthentication;

@RestController
@RequestMapping("/products")
public class ProductApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);

    @Autowired
    private ProductBd productBd;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public InsertProductResponse insertProduct(@RequestBody InsertProductRequest insertProductRequest,
            Principal principal, HttpServletRequest httpRequest) {
        SalesAuthentication salesAuthentication = (SalesAuthentication) principal;
        LOGGER.info("userId {} is adding a new product", ((JwtTokenModel) salesAuthentication.getPrincipal()).getId());
        return productBd.insertProduct(insertProductRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductListResponse getProductList(Principal principal, HttpServletRequest httpRequest) {
        SalesAuthentication salesAuthentication = (SalesAuthentication) principal;
        LOGGER.info("userId {} is listing products", ((JwtTokenModel) salesAuthentication.getPrincipal()).getId());
        return productBd.getProductList();
    }

}
