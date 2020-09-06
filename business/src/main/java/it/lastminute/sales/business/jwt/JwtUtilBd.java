package it.lastminute.sales.business.jwt;

import it.lastminute.sales.dao.entity.User;

public interface JwtUtilBd {

    String generateJwt(final User user);

    JwtTokenModel verifyJwt(String jwtToken);
}
