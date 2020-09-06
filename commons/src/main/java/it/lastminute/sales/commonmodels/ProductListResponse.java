package it.lastminute.sales.commonmodels;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductListResponse {

    private List<ProductDto> products;

}
