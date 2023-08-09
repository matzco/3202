package au.edu.sydney.brawndo.erp.spfea.strategy;

import au.edu.sydney.brawndo.erp.ordering.Order;
import au.edu.sydney.brawndo.erp.ordering.Product;

/**
 * Class implementing the bulk discount methods for DiscountStrategy
 */
public class BulkDiscountStrategy implements DiscountStrategy {

    CompOrder order;

    @Override
    public void setOrder(CompOrder order) {
        this.order = order;
    }

    /**
     * Deep copies the order.
     * @return a duplicate CompOrder
     */
    @Override
    public Order copy() {

        Order copy = new CompOrder(
                this.order.getOrderID(), this.order.getOrderDate(), this.order.getCustomer(),
                this.order.getDiscountRate(), this.order.numberOfShipmentsOrdered(), this.order.getDiscountThreshold(),
                this.order.getDiscountStrategy(), this.order.getCustomerStrategy(), this.order.getDescriptionStrategy());
        for (Product product: this.order.getAllProducts()) {
            copy.setProduct(product, this.order.getProducts().get(product));
        }
        return copy;
    }

    /**
     * Calculates the total cost of the order. If subscription, calculation accounts for the number of deliveries.
     * @return double dollar amount
     */
    @Override
    public double getTotalCost() {
        double cost = 0.0;
        for (Product product: this.order.getProducts().keySet()) {
            int count = this.order.getProducts().get(product);
            if (count >= this.order.getDiscountThreshold()) {
                cost +=  count * product.getCost() * this.order.getDiscountRate();
            } else {
                cost +=  count * product.getCost();
            }
        }

        if (this.order.getDescriptionStrategy().getType().equals("order")) {
            return cost;
        } else {
            return cost * this.order.numberOfShipmentsOrdered();
        }
    }
}
