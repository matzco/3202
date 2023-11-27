package au.edu.sydney.brawndo.erp.spfea.strategy;

/**
 * Interface to define strategy for business or individual customers.
 */
public interface CustomerStrategy {

    /**
     * Generates a invoice String with relevant Order data and formatting.
     * @return an invoice String
     */
    String generateInvoiceData();

    /**
     * Sets the Order related to the strategy
     * @param order
     */
    void setOrder(CompOrder order);
}
