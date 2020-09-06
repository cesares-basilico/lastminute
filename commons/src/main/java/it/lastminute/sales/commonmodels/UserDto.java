package it.lastminute.sales.commonmodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String id;
    private String name;
    private String authToken;

}
