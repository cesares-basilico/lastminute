package it.lastminute.sales.enums;

/**
 * @author Cesare
 *
 */
public enum ProductType {

    BOOKS("BOOK"), FOOD("FOOD"), MEDICAL("MEDICAL"), OTHER("");

    private String key;

    private ProductType(String key) {
        this.key = key;
    }

    public static ProductType getByKey(String key) {
        for (ProductType productType : ProductType.values()) {
            if (productType.key.equals(key)) {
                return productType;
            }
        }
        return OTHER;
    }

}
