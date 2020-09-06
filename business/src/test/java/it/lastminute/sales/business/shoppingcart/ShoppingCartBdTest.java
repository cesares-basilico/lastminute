package it.lastminute.sales.business.shoppingcart;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.lastminute.sales.commonmodels.LineDto;
import it.lastminute.sales.commonmodels.ShoppingCartRequest;
import it.lastminute.sales.commonmodels.ShoppingCartResponse;
import it.lastminute.sales.dao.ProductRepository;
import it.lastminute.sales.dao.ShoppingCartRepository;
import it.lastminute.sales.dao.UserRepository;
import it.lastminute.sales.dao.entity.Product;
import it.lastminute.sales.dao.entity.ProductTypeLkp;
import it.lastminute.sales.dao.entity.User;

@ExtendWith(SpringExtension.class)
public class ShoppingCartBdTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartBdTest.class);

    @TestConfiguration
    static class ShoppingCartBdTestContextConfiguration {

        @Bean
        public ShoppingCartBd shoppingCartBd() {
            return new ShoppingCartBdImpl();
        }
    }

    @Autowired
    private ShoppingCartBd shoppingCartBd;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Validator validator;

    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    @MockBean
    private ProductRepository productRepository;

    private static User user;
    private static Product product1;
    private static Product product2;
    private static Product product3;
    private static Product product4;
    private static Product product5;
    private static Product product6;
    private static Product product7;
    private static Product product8;
    private static Product product9;

    @BeforeAll
    public static void setUp() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setActive(true);

        ProductTypeLkp prodBook = new ProductTypeLkp();
        prodBook.setKey("BOOK");

        ProductTypeLkp prodFood = new ProductTypeLkp();
        prodFood.setKey("FOOD");

        ProductTypeLkp prodMedical = new ProductTypeLkp();
        prodMedical.setKey("MEDICAL");

        ProductTypeLkp prodOther = new ProductTypeLkp();
        prodOther.setKey("");

        product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setImported(false);
        product1.setPrice(1249l);
        product1.setProductTypeLkp(prodBook);

        product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setImported(false);
        product2.setPrice(1499l);
        product2.setProductTypeLkp(prodOther);

        product3 = new Product();
        product3.setId(UUID.randomUUID());
        product3.setImported(false);
        product3.setPrice(85l);
        product3.setProductTypeLkp(prodFood);

        product4 = new Product();
        product4.setId(UUID.randomUUID());
        product4.setImported(true);
        product4.setPrice(1000l);
        product4.setProductTypeLkp(prodFood);

        product5 = new Product();
        product5.setId(UUID.randomUUID());
        product5.setImported(true);
        product5.setPrice(4750l);
        product5.setProductTypeLkp(prodOther);

        product6 = new Product();
        product6.setId(UUID.randomUUID());
        product6.setImported(true);
        product6.setPrice(2799l);
        product6.setProductTypeLkp(prodOther);

        product7 = new Product();
        product7.setId(UUID.randomUUID());
        product7.setImported(false);
        product7.setPrice(1899l);
        product7.setProductTypeLkp(prodOther);

        product8 = new Product();
        product8.setId(UUID.randomUUID());
        product8.setImported(false);
        product8.setPrice(975l);
        product8.setProductTypeLkp(prodMedical);

        product9 = new Product();
        product9.setId(UUID.randomUUID());
        product9.setImported(true);
        product9.setPrice(1125l);
        product9.setProductTypeLkp(prodFood);

    }

    @Test
    public void testCart1() throws Exception {
        final ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest();
        shoppingCartRequest.setLines(new ArrayList<>());
        LineDto lineDto1 = new LineDto();
        lineDto1.setProductId(product1.getId().toString());
        lineDto1.setQuantity(1);
        LineDto lineDto2 = new LineDto();
        lineDto2.setProductId(product2.getId().toString());
        lineDto2.setQuantity(1);
        LineDto lineDto3 = new LineDto();
        lineDto3.setProductId(product3.getId().toString());
        lineDto3.setQuantity(1);
        shoppingCartRequest.getLines().add(lineDto1);
        shoppingCartRequest.getLines().add(lineDto2);
        shoppingCartRequest.getLines().add(lineDto3);

        Mockito.when(validator.validate(anything())).thenReturn(new HashSet<>());
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));
        Mockito.when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));
        Mockito.when(productRepository.findById(product3.getId())).thenReturn(Optional.of(product3));

        ShoppingCartResponse shoppingCartResponse = shoppingCartBd.createCart(shoppingCartRequest,
                user.getId().toString());

        shoppingCartResponse.getProducts().forEach(p -> LOGGER.info(p.toString()));

        assertEquals(29.83, shoppingCartResponse.getTotal(), "Wrong total");
        assertEquals(1.50, shoppingCartResponse.getTaxes(), "Wrong taxes");

    }

    @Test
    public void testCart2() throws Exception {
        final ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest();
        shoppingCartRequest.setLines(new ArrayList<>());
        LineDto lineDto1 = new LineDto();
        lineDto1.setProductId(product4.getId().toString());
        lineDto1.setQuantity(1);
        LineDto lineDto2 = new LineDto();
        lineDto2.setProductId(product5.getId().toString());
        lineDto2.setQuantity(1);
        shoppingCartRequest.getLines().add(lineDto1);
        shoppingCartRequest.getLines().add(lineDto2);

        Mockito.when(validator.validate(anything())).thenReturn(new HashSet<>());
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(productRepository.findById(product4.getId())).thenReturn(Optional.of(product4));
        Mockito.when(productRepository.findById(product5.getId())).thenReturn(Optional.of(product5));

        ShoppingCartResponse shoppingCartResponse = shoppingCartBd.createCart(shoppingCartRequest,
                user.getId().toString());

        shoppingCartResponse.getProducts().forEach(p -> LOGGER.info(p.toString()));

        assertEquals(65.15, shoppingCartResponse.getTotal(), "Wrong total");
        assertEquals(7.65, shoppingCartResponse.getTaxes(), "Wrong taxes");

    }

    @Test
    public void testCart3() throws Exception {
        final ShoppingCartRequest shoppingCartRequest = new ShoppingCartRequest();
        shoppingCartRequest.setLines(new ArrayList<>());
        LineDto lineDto1 = new LineDto();
        lineDto1.setProductId(product6.getId().toString());
        lineDto1.setQuantity(1);
        LineDto lineDto2 = new LineDto();
        lineDto2.setProductId(product7.getId().toString());
        lineDto2.setQuantity(1);
        LineDto lineDto3 = new LineDto();
        lineDto3.setProductId(product8.getId().toString());
        lineDto3.setQuantity(1);
        LineDto lineDto4 = new LineDto();
        lineDto4.setProductId(product9.getId().toString());
        lineDto4.setQuantity(1);
        shoppingCartRequest.getLines().add(lineDto1);
        shoppingCartRequest.getLines().add(lineDto2);
        shoppingCartRequest.getLines().add(lineDto3);
        shoppingCartRequest.getLines().add(lineDto4);

        Mockito.when(validator.validate(anything())).thenReturn(new HashSet<>());
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(productRepository.findById(product6.getId())).thenReturn(Optional.of(product6));
        Mockito.when(productRepository.findById(product7.getId())).thenReturn(Optional.of(product7));
        Mockito.when(productRepository.findById(product8.getId())).thenReturn(Optional.of(product8));
        Mockito.when(productRepository.findById(product9.getId())).thenReturn(Optional.of(product9));

        ShoppingCartResponse shoppingCartResponse = shoppingCartBd.createCart(shoppingCartRequest,
                user.getId().toString());

        shoppingCartResponse.getProducts().forEach(p -> LOGGER.info(p.toString()));

        assertEquals(74.63, shoppingCartResponse.getTotal(), "Wrong total");
        assertEquals(6.65, shoppingCartResponse.getTaxes(), "Wrong taxes");

    }

}