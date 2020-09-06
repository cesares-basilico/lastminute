package it.lastminute.sales.business.product;

import it.lastminute.sales.commonmodels.InsertProductRequest;
import it.lastminute.sales.commonmodels.InsertProductResponse;
import it.lastminute.sales.commonmodels.ProductListResponse;

public interface ProductBd {

    InsertProductResponse insertProduct(final InsertProductRequest request);

    ProductListResponse getProductList();

}
