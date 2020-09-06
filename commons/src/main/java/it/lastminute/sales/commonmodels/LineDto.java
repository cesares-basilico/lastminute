package it.lastminute.sales.commonmodels;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LineDto {

    @NotEmpty(message = "productId is mandatory")
    private String productId;
    @NotNull(message = "quantity is mandatory")
    private Integer quantity;

}
