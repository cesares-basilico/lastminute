package it.lastminute.sales.business.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.lastminute.sales.business.exception.ValidationException;
import it.lastminute.sales.commonmodels.InsertProductRequest;
import it.lastminute.sales.commonmodels.InsertProductResponse;
import it.lastminute.sales.commonmodels.ProductDto;
import it.lastminute.sales.commonmodels.ProductListResponse;
import it.lastminute.sales.dao.ProductRepository;
import it.lastminute.sales.dao.ProductTypeRepository;
import it.lastminute.sales.dao.entity.Product;
import it.lastminute.sales.dao.entity.ProductTypeLkp;

@Component
public class ProductBdImpl implements ProductBd {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBdImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private Validator validator;

    @Override
    public InsertProductResponse insertProduct(InsertProductRequest request) {

        Set<ConstraintViolation<InsertProductRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ValidationException(constraintViolations);
        }

        Product product = new Product();
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        final Optional<ProductTypeLkp> productTypeLkp = productTypeRepository.findById(request.getProductTypeId());
        if (!productTypeLkp.isPresent()) {
            throw new ValidationException("Invalid productTypeId");
        }
        product.setProductTypeLkp(productTypeLkp.get());
        product.setImported(request.getImported());
        product = productRepository.save(product);

        return new InsertProductResponse(product.getId().toString());
    }

    @Override
    public ProductListResponse getProductList() {
        final Iterable<Product> products = productRepository.findAll();
        final List<ProductDto> results = new ArrayList<>();
        products.forEach(p -> {
            final ProductDto product = new ProductDto();
            product.setDescription(p.getDescription());
            product.setId(p.getId().toString());
            product.setImported(p.getImported());
            product.setPrice(Double.valueOf(p.getPrice()) / 100);
            product.setProductType(p.getProductTypeLkp().getDescription());
            results.add(product);
        });
        final ProductListResponse response = new ProductListResponse();
        response.setProducts(results);
        return response;
    }

}
