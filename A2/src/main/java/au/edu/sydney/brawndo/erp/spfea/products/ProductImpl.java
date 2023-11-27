package au.edu.sydney.brawndo.erp.spfea.products;

import au.edu.sydney.brawndo.erp.ordering.Product;
import au.edu.sydney.brawndo.erp.spfea.flyweight.FlyweightFactory;

/**
 * Product implementation of product interface. Has Flyweight applied to reduce memory costs.
 */
public class ProductImpl implements Product {

    private final String name;
    private final double[] manufacturingData;
    private final double cost;
    private double[] recipeData;
    private double[] marketingData;
    private double[] safetyData;
    private double[] licensingData;

    /**
     * FlyweightFactory attribute for connection to flyweight data
     */
    private FlyweightFactory flyweightFactory = new FlyweightFactory();

    /**
     * Constructor. Each param is first checked via the FlyweightFactory to find existing data and thereby reduce the
     * memory consumption.
     * @param name name
     * @param cost cost
     * @param manufacturingData manufacturing data array
     * @param recipeData recipe data array
     * @param marketingData marketing data array
     * @param safetyData safety data array
     * @param licensingData licensing data array
     */
    public ProductImpl(String name,
                       double cost,
                       double[] manufacturingData,
                       double[] recipeData,
                       double[] marketingData,
                       double[] safetyData,
                       double[] licensingData) {

        this.name = name;
        this.cost = cost;

        this.manufacturingData = flyweightFactory.get(manufacturingData);
        this.recipeData = flyweightFactory.get(recipeData);
        this.marketingData = flyweightFactory.get(marketingData);
        this.safetyData = flyweightFactory.get(safetyData);
        this.licensingData = flyweightFactory.get(licensingData);
    }

    @Override
    public String getProductName() {
        return name;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public double[] getManufacturingData() {
        return manufacturingData;
    }

    @Override
    public double[] getRecipeData() {
        return recipeData;
    }

    @Override
    public double[] getMarketingData() {
        return marketingData;
    }

    @Override
    public double[] getSafetyData() {
        return safetyData;
    }

    @Override
    public double[] getLicensingData() {
        return licensingData;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
