package it.lastminute.sales.business.login;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.lastminute.sales.business.jwt.JwtUtilBd;
import it.lastminute.sales.commonmodels.LoginRequest;
import it.lastminute.sales.commonmodels.UserDto;
import it.lastminute.sales.dao.UserRepository;
import it.lastminute.sales.dao.entity.User;

@ExtendWith(SpringExtension.class)
public class LoginBdTest {

    @TestConfiguration
    static class LoginBdTestTestContextConfiguration {

        @Bean
        public LoginBd loginBd() {
            return new LoginBdImpl();
        }
    }

    @Autowired
    private LoginBd loginBd;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtUtilBd jwtUtilBd;

    private static User user;
    private static User userDisabled;

    @BeforeAll
    public static void setUp() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setActive(true);
        userDisabled = new User();
        userDisabled.setId(UUID.randomUUID());
        userDisabled.setActive(false);
    }

    @Test
    public void testUserActive() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("username");
        request.setPassword("password");
        Mockito.when(userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()))
                .thenReturn(Optional.of(user));
        UserDto user = loginBd.login(request);

        assertNotNull(user);

    }

    @Test
    public void testUserDisabled() throws Exception {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            LoginRequest request = new LoginRequest();
            request.setUsername("username");
            request.setPassword("password");
            Mockito.when(userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()))
                    .thenReturn(Optional.of(userDisabled));
            UserDto user = loginBd.login(request);
        });

    }

    @Test
    public void testUserMissing() throws Exception {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            LoginRequest request = new LoginRequest();
            request.setUsername("username");
            request.setPassword("password");
            Mockito.when(userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()))
                    .thenReturn(Optional.empty());
            UserDto user = loginBd.login(request);

            assertNotNull(user);
        });
    }
}