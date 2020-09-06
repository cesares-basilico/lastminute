package it.lastminute.sales.commonmodels;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsertProductRequest {

    @NotEmpty(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Price is mandatory")
    private Long price;
    @NotNull(message = "ProductTypeId is mandatory")
    private Integer productTypeId;
    @NotNull(message = "Imported is mandatory")
    private Boolean imported;

}
