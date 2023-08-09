package au.edu.sydney.brawndo.erp.spfea.strategy;


import au.edu.sydney.brawndo.erp.ordering.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Class implementing the individual methods for customerstrategy
 */
public class IndividualStrategy implements CustomerStrategy {

    public CompOrder order;

    /**
     * Generates an invoice String. Specifics are conditional on whether the type is order or subscription
     * @return invoice String
     */
    @Override
    public String generateInvoiceData() {
        if (this.order.getDescriptionStrategy().getType().equals("order")) {
            StringBuilder sb = new StringBuilder();

            sb.append("Thank you for your Brawndo© order!\n");
            sb.append("Your order comes to: $");
            sb.append(String.format("%,.2f", this.order.getTotalCost()));
            sb.append("\nPlease see below for details:\n");
            List<Product> keyList = new ArrayList<>(this.order.getProducts().keySet());
            keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

            for (Product product : keyList) {
                sb.append("\tProduct name: ");
                sb.append(product.getProductName());
                sb.append("\tQty: ");
                sb.append(this.order.getProducts().get(product));
                sb.append("\tCost per unit: ");
                sb.append(String.format("$%,.2f", product.getCost()));
                sb.append("\tSubtotal: ");
                sb.append(String.format("$%,.2f\n", product.getCost() * this.order.getProducts().get(product)));
            }

            return sb.toString();
        } else {
            Map<Product, Integer> products = this.order.getProducts();

            StringBuilder sb = new StringBuilder();

            sb.append("Thank you for your Brawndo© order!\n");
            sb.append("Your order comes to: $");
            sb.append(String.format("%,.2f", this.order.getRecurringCost()/this.order.numberOfShipmentsOrdered()));
            sb.append(" each week, with a total overall cost of: $");
            sb.append(String.format("%,.2f", this.order.getTotalCost()));
            sb.append("\nPlease see below for details:\n");
            List<Product> keyList = new ArrayList<>(products.keySet());
            keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

            for (Product product: keyList) {
                sb.append("\tProduct name: ");
                sb.append(product.getProductName());
                sb.append("\tQty: ");
                sb.append(products.get(product));
                sb.append("\tCost per unit: ");
                sb.append(String.format("$%,.2f", product.getCost()));
                sb.append("\tSubtotal: ");
                sb.append(String.format("$%,.2f\n", product.getCost() * products.get(product)));
            }

            return sb.toString();
        }
    }

    @Override
    public void setOrder(CompOrder order) {
        this.order = order;
    }
}
