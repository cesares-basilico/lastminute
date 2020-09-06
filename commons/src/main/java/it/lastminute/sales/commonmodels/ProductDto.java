package it.lastminute.sales.commonmodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {

    private String id;
    private String description;
    private Double price;
    private Boolean imported;
    private String productType;

}
