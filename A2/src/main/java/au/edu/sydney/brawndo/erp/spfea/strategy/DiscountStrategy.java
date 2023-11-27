package au.edu.sydney.brawndo.erp.spfea.strategy;

import au.edu.sydney.brawndo.erp.ordering.Order;

/**
 * Interface for the different concrete discount strategies
 */
public interface DiscountStrategy {

    /**
     * Method to deep copy the order
     * @return a deep copy of the order
     */
    Order copy();

    /**
     * Calculates the total cost of the order
     * @return total cost of the order
     */
    double getTotalCost();

    /**
     * Sets the Order related to the Strategy
     */
    void setOrder(CompOrder compOrder);
}
