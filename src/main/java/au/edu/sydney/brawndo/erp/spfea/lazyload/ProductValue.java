package au.edu.sydney.brawndo.erp.spfea.lazyload;

import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class for ValueObject DP that encapsulates calculating/handling the hashcode of Product objects.
 */
public class ProductValue {

    private final int hash;

    /**
     * Constructor that automatically generates a hashcode from the input Product
     * @param product
     */
    public ProductValue(Product product) {
        this.hash = Objects.hash(product.getProductName(), product.getCost(), Arrays.hashCode(product.getManufacturingData()),
                Arrays.hashCode(product.getRecipeData()), Arrays.hashCode(product.getMarketingData()),
                Arrays.hashCode(product.getSafetyData()), Arrays.hashCode(product.getLicensingData()));
    }

    public int getHash(){
        return this.hash;
    }

    /**
     * Equals override.
     * @param o object, expecting a ProductValue
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductValue productValue = (ProductValue) o;
        return this.getHash() == productValue.getHash();
    }

}
