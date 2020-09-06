package it.lastminute.sales.business.shoppingcart;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.lastminute.sales.business.exception.ValidationException;
import it.lastminute.sales.business.taxes.TaxesEvaluatorFactory;
import it.lastminute.sales.commonmodels.ProductDto;
import it.lastminute.sales.commonmodels.ShoppingCartRequest;
import it.lastminute.sales.commonmodels.ShoppingCartResponse;
import it.lastminute.sales.dao.ProductRepository;
import it.lastminute.sales.dao.ShoppingCartRepository;
import it.lastminute.sales.dao.UserRepository;
import it.lastminute.sales.dao.entity.Product;
import it.lastminute.sales.dao.entity.ShoppingCart;
import it.lastminute.sales.dao.entity.ShoppingCartLine;
import it.lastminute.sales.dao.entity.User;
import it.lastminute.sales.enums.ProductType;

@Component
public class ShoppingCartBdImpl implements ShoppingCartBd {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartBdImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Override
    public ShoppingCartResponse createCart(ShoppingCartRequest request, String userId) {
        Set<ConstraintViolation<ShoppingCartRequest>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ValidationException(constraintViolations);
        }

        final ShoppingCart shoppingCart = generateShoppingCart(request, userId);

        shoppingCartRepository.save(shoppingCart);

        final ShoppingCartResponse response = new ShoppingCartResponse();

        response.setProducts(new ArrayList<>());

        shoppingCart.getShoppingCartList().forEach(l -> {
            response.getProducts().add(generateProductDto(l.getProduct()));
        });

        response.setTaxes(Double.valueOf(shoppingCart.getTaxes()) / 100);
        response.setTotal(Double.valueOf(shoppingCart.getTotal()) / 100);

        return response;
    }

    /**
     * @param product
     * @return
     */
    private ProductDto generateProductDto(Product product) {
        final ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setId(product.getId().toString());
        productDto.setImported(product.getImported());
        productDto.setPrice(Double.valueOf(product.getPriceTaxed()) / 100);
        productDto.setProductType(product.getProductTypeLkp().getDescription());
        return productDto;
    }

    /**
     * @param request
     * @return
     */
    private ShoppingCart generateShoppingCart(ShoppingCartRequest request, String userId) {
        final ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTotal(0L);
        shoppingCart.setTaxes(0L);

        Optional<User> userOpt = userRepository.findById(UUID.fromString(userId));
        shoppingCart.setUser(userOpt.get());

        request.getLines().forEach(p -> {
            final Optional<Product> productOpt = productRepository.findById(UUID.fromString(p.getProductId()));
            if (!productOpt.isPresent()) {
                throw new ValidationException("Invalid product");
            }
            final ShoppingCartLine shoppingCartLine = new ShoppingCartLine();
            final Product product = productOpt.get();
            shoppingCartLine.setProduct(product);
            shoppingCartLine.setShoppingCart(shoppingCart);
            shoppingCartLine.setQuantity(p.getQuantity());
            shoppingCart.getShoppingCartList().add(shoppingCartLine);
            final Long productTaxes = TaxesEvaluatorFactory
                    .getTaxesEvaluator(ProductType.getByKey(product.getProductTypeLkp().getKey()))
                    .evaluateTaxes(product);
            shoppingCart.increaseTotal(p.getQuantity() * product.getPrice() + productTaxes);
            shoppingCart.increaseTaxes(productTaxes);
            product.setPriceTaxed(productTaxes + product.getPrice());
        });
        return shoppingCart;
    }

}
