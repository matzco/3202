package au.edu.sydney.brawndo.erp.spfea.strategy;

import au.edu.sydney.brawndo.erp.ordering.Order;
import au.edu.sydney.brawndo.erp.ordering.Product;
import au.edu.sydney.brawndo.erp.spfea.lazyload.ProductValue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that replaced all 8 previous order classes. Uses different strategies to compile Orders with differing behaviours.
 */
public class CompOrder implements Order {

    private Map<Product, Integer> products = new HashMap<>();
    private LocalDateTime date;
    private double discountRate;
    private int discountThreshold;
    private int customerID;
    private int id;
    private int numShipments;
    /**
     * Generic DiscountStrategy, will be defined while the object is generated
     */
    private DiscountStrategy discountStrategy;
    /**
     * Generic CustomerStrategy, will be defined while the object is generated
     */
    private CustomerStrategy customerStrategy;
    /**
     * Generic DescriptionStrategy, will be defined while the object is generated
     */
    private DescriptionStrategy descriptionStrategy;
    private boolean finalised = false;

    /**
     * Constructor.
     * @param id order id
     * @param date LocalDateTime
     * @param customerID customerId
     * @param discountRate discount rate
     * @param numShipments num of shipments
     * @param discountThreshold discount threshold
     * @param discountStrategy discount strategy
     * @param customerStrategy customer strategy
     * @param descriptionStrategy description strategy
     */
    public CompOrder(int id, LocalDateTime date, int customerID, double discountRate, int numShipments,
                     int discountThreshold, DiscountStrategy discountStrategy, CustomerStrategy customerStrategy,
                     DescriptionStrategy descriptionStrategy) {
        this.id = id;
        this.date = date;
        this.customerID = customerID;
        this.discountRate = discountRate;
        this.discountThreshold = discountThreshold;
        this.numShipments = numShipments;
        this.discountStrategy = discountStrategy;
        this.customerStrategy = customerStrategy;
        this.descriptionStrategy = descriptionStrategy;
    }

    @Override
    public int getOrderID() {
        return this.id;
    }

    /**
     * Returns the total cost of the order
     * First populate the ConcreteStrategy attributes with the Order for the sub-conditional checks (order or subscription)
     * @return the total cost of the order
     */
    @Override
    public double getTotalCost() {
        this.descriptionStrategy.setOrder(this);
        this.discountStrategy.setOrder(this);
        return this.discountStrategy.getTotalCost();
    }

    @Override
    public LocalDateTime getOrderDate() {
        return this.date;
    }

    /**
     * Sets a product and quantity in the products Map. Uses ProductValue to streamline comparisons.
     * @param product product
     * @param qty quantity
     */
    @Override
    public void setProduct(Product product, int qty) {
        if (finalised) throw new IllegalStateException("Order was already finalised.");

        for (Product contained : products.keySet()) {
            if (new ProductValue(contained).getHash() == new ProductValue(product).getHash()) {
                product = contained;
                break;
            }
        }

        products.put(product, qty);
    }

    @Override
    public Set<Product> getAllProducts() {
        return products.keySet();
    }

    /**
     * Calculates the quantity of a product. Uses ProductValue to streamline Product comparisons.
     * @param product product
     * @return quantity of product
     */
    @Override
    public int getProductQty(Product product) {

        for (Product contained : products.keySet()) {
            if (new ProductValue(contained).getHash() == new ProductValue(product).getHash()) {
                product = contained;
                break;
            }
        }
        Integer result = products.get(product);
        return null == result ? 0 : result;
    }

    /**
     * Generates an invoice with detailed data from the order, as a string.
     * @return a detailed String invoice
     */
    @Override
    public String generateInvoiceData() {
        this.customerStrategy.setOrder(this);
        return this.customerStrategy.generateInvoiceData();
    }

    @Override
    public int getCustomer() {
        return this.customerID;
    }

    @Override
    public void finalise() {
        this.finalised = true;
    }

    /**
     * Deep copies the order. .
     * @return a deep copy
     */
    @Override
    public Order copy() {
        return this.discountStrategy.copy();
    }

    /**
     * Generate a short string representation of the order. Passes Order so that the method can acccess relevant data
     * @return a short string description
     */
    @Override
    public String shortDesc() {
        this.descriptionStrategy.setOrder(this);
        return this.descriptionStrategy.shortDesc();
    }

    /**
     * Generate a long string representation of the order. Passes Order so that the method can acccess relevant data
     * @return a long string description
     */
    @Override
    public String longDesc() {
        this.descriptionStrategy.setOrder(this);
        return this.descriptionStrategy.longDesc();
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    public double getDiscountRate() {
        return this.discountRate;
    }

    public int numberOfShipmentsOrdered() {
        return this.numShipments;
    }

    public DiscountStrategy getDiscountStrategy() {return this.discountStrategy;}

    /**
     * Gets the recurring cost of the order. only called by subscription types
     * @return the cost
     */
    public double getRecurringCost() {
        this.discountStrategy.setOrder(this);
        return this.discountStrategy.getTotalCost();
    }

    public CustomerStrategy getCustomerStrategy() {
        return this.customerStrategy;
    }

    public int getDiscountThreshold() {
        return this.discountThreshold;
    }

    public boolean isFinalised() {
        return this.finalised;
    }

    public DescriptionStrategy getDescriptionStrategy() {
        return this.descriptionStrategy;
    }
}
