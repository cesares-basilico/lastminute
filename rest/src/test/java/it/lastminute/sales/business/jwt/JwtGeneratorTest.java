package it.lastminute.sales.business.jwt;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.lastminute.sales.dao.entity.User;

@ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = SalesApplication.class)
public class JwtGeneratorTest {

    @TestConfiguration
    static class JwtGeneratorTestContextConfiguration {

        @Bean
        public JwtUtilBd jwtUtilBd() {
            return new JwtUtilBdImpl();
        }
    }

    private static User user;

    @Autowired
    private JwtUtilBd jwtUtilBd;

    @MockBean
    private Environment environment;

    @BeforeAll
    public static void setUp() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setName("user Name");
        user.setPassword("user Password");
        user.setUsername("username");
    }

    @Test
    public void testGenerateJwt() throws Exception {

        Mockito.when(environment.getProperty("jwt.secret")).thenReturn("secret");
        Mockito.when(environment.getProperty("jwt.expiration", Long.class)).thenReturn(1200l);

        final String jwt = jwtUtilBd.generateJwt(user);

        assertNotNull("jwt could not be null", jwt);

        JwtTokenModel jwtTokenModel = jwtUtilBd.verifyJwt(jwt);
        assertEquals(user.getId(), UUID.fromString(jwtTokenModel.getId()), "Wrong user id found after decoding");
    }

}