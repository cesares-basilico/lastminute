package it.lastminute.sales.business.taxes;

import it.lastminute.sales.enums.ProductType;

/**
 * @author Cesare
 *
 */
public class TaxesEvaluatorFactory {

    public static ITaxesEvaluator getTaxesEvaluator(ProductType productType) {

        switch (productType) {
            case BOOKS:
            case FOOD:
            case MEDICAL:
                return new ExemptTaxesEvaluator();
            default:
                return new BasicTaxesEvaluator();
        }

    }

}
