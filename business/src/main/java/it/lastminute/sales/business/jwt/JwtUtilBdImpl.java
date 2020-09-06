package it.lastminute.sales.business.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.lastminute.sales.dao.entity.User;

@Component
public class JwtUtilBdImpl implements JwtUtilBd {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtilBdImpl.class);

    @Autowired
    private Environment environment;

    @Override
    public String generateJwt(User user) {

        LOGGER.info("Generating JWT for user: " + user.getUsername());

        final String secret = environment.getProperty("jwt.secret");
        final Long expiration = environment.getProperty("jwt.expiration", Long.class);

        final Map<String, Object> claims = new HashMap<>();
        claims.put("Name", user.getName());
        claims.put("id", user.getId());
        claims.put("role", user.getRole());

        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate(expiration))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    @Override
    public JwtTokenModel verifyJwt(String jwtToken) {
        final JwtTokenModel jwtTokenModel = new JwtTokenModel();

        try {
            final String secret = environment.getProperty("jwt.secret");
            final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
            jwtTokenModel.setExpiration(((Integer) claims.get("exp")).longValue());
            jwtTokenModel.setId((String) claims.get("id"));
            jwtTokenModel.setRole((String) claims.get("role"));
            jwtTokenModel.setValid(claims.getExpiration().after(new Date()));
        } catch (Exception e) {
            jwtTokenModel.setValid(false);
        }
        return jwtTokenModel;

    }

    private Date generateExpirationDate(Long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
