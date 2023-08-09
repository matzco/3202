package au.edu.sydney.brawndo.erp.spfea.strategy;

import au.edu.sydney.brawndo.erp.ordering.Product;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class implementing the order methods for DescriptionStrategy
 */
public class OrderStrategy implements DescriptionStrategy {

    public CompOrder order;

    public final String type = "order";

    public void setOrder(CompOrder order) {
        this.order = order;
    }

    /**
     * Generates a detailed description of the order.
     * @return detailed String representation of order
     */
    @Override
    public String longDesc() {
        double fullCost = 0.0;
        double discountedCost = this.order.getTotalCost();
        StringBuilder productSB = new StringBuilder();

        List<Product> keyList = new ArrayList<>(this.order.getProducts().keySet());
        keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

        for (Product product: keyList) {
            double subtotal = product.getCost() * this.order.getProducts().get(product);
            fullCost += subtotal;

            productSB.append(String.format("\tProduct name: %s\tQty: %d\tUnit cost: $%,.2f\tSubtotal: $%,.2f\n",
                    product.getProductName(),
                    this.order.getProducts().get(product),
                    product.getCost(),
                    subtotal));
        }

        return String.format(this.order.isFinalised() ? "" : "*NOT FINALISED*\n" +
                        "Order details (id #%d)\n" +
                        "Date: %s\n" +
                        "Products:\n" +
                        "%s" +
                        "\tDiscount: -$%,.2f\n" +
                        "Total cost: $%,.2f\n",
                this.order.getOrderID(),
                this.order.getOrderDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                productSB.toString(),
                fullCost - discountedCost,
                discountedCost
        );
    }

    /**
     * Generates a short description of the order.
     * @return brief String representation of order
     */
    @Override
    public String shortDesc() {
        return String.format("ID:%s $%,.2f", this.order.getOrderID(), this.order.getTotalCost());
    }

    public String getType() {return this.type;}
}
