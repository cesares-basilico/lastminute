package it.lastminute.sales.business.taxes;

import it.lastminute.sales.dao.entity.Product;

/**
 * @author Cesare
 *
 */
public class ExemptTaxesEvaluator implements ITaxesEvaluator {

    @Override
    public Long salesTaxes(Product product) {
        return 0L;
    }
}
