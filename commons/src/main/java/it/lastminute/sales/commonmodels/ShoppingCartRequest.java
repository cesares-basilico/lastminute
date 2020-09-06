package it.lastminute.sales.commonmodels;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShoppingCartRequest {

    @NotEmpty(message = "lines is mandatory and must not be empty")
    private List<LineDto> lines;

}
