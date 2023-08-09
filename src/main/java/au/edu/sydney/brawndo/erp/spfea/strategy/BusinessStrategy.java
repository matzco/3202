package au.edu.sydney.brawndo.erp.spfea.strategy;

/**
 * Class implementing the business methods for customerstrategy
 */
public class BusinessStrategy implements CustomerStrategy {

    CompOrder order;

    public void setOrder(CompOrder order) {
        this.order = order;
    }

    /**
     * Generates an invoice String. Specifics are conditional on whether the type is order or subscription
     * @return invoice String
     */
    @Override
    public String generateInvoiceData() {
        if (this.order.getDescriptionStrategy().getType().equals("order")) {
            return String.format("Your business account has been charged: $%,.2f" +
                    "\nPlease see your Brawndo© merchandising representative for itemised details.", this.order.getTotalCost());
        } else if (this.order.getDescriptionStrategy().getType().equals("subscription")){
            return String.format("Your business account will be charged: $%,.2f each week, with a total overall cost of: $%,.2f" +
                    "\nPlease see your Brawndo© merchandising representative for itemised details.",
                    this.order.getRecurringCost()/this.order.numberOfShipmentsOrdered(), this.order.getTotalCost());
        } else {
            return null;
        }
    }

}
