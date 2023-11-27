package au.edu.sydney.brawndo.erp.spfea.strategy;

/**
 * Interface for the type of Order, order or subscription
 */
public interface DescriptionStrategy {

    /**
     * Generates a invoice String with relevant Order data and formatting.
     * @return an invoice String
     *
     *String generateInvoiceData();/


    /**
     * Creates a string that is a detailed outline of the Order current state.
     * @return a detailed formatted String
     */
    String longDesc();

    /**
     * Generates a brief outline of the Order current state
     * @return a short string outlining the Order
     */
    String shortDesc();

    /**
     * Sets the Order locally related to the strategy
     * @param compOrder
     */
    void setOrder(CompOrder compOrder);

    /**
     * Returns the String name of the concrete type
     * @return order or subscription
     */
    String getType();
}
