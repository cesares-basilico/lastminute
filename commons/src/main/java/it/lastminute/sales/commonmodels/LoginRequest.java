package it.lastminute.sales.commonmodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequest {

    private String username;
    private String password;

}
