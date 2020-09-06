package it.lastminute.sales.business.taxes;

import it.lastminute.sales.dao.entity.Product;

/**
 * @author Cesare
 *
 */
public interface ITaxesEvaluator {

    default Long evaluateTaxes(Product product) {
        return roundTaxes(importTaxes(product) + salesTaxes(product));
    }

    default Long importTaxes(Product product) {
        if (product.getImported()) {
            return Math.round(product.getPrice() * 0.05);
        } else {
            return 0l;
        }
    }

    default Long salesTaxes(Product product) {
        return Math.round(product.getPrice() * 0.1);
    }

    default Long roundTaxes(Long taxes) {
        long remainder = taxes % 5;
        if (remainder > 2) {
            return taxes + 5 - remainder;
        } else {
            return taxes - remainder;
        }
    }
}
