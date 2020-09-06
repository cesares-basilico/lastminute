package it.lastminute.sales.business.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtTokenModel {

    private String id;
    private Long expiration;
    private boolean isValid;
    private String role;

}
