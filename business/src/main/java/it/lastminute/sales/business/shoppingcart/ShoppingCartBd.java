package it.lastminute.sales.business.shoppingcart;

import it.lastminute.sales.commonmodels.ShoppingCartRequest;
import it.lastminute.sales.commonmodels.ShoppingCartResponse;

public interface ShoppingCartBd {

    ShoppingCartResponse createCart(ShoppingCartRequest shoppingCartRequest, String userId);
}
