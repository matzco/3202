package au.edu.sydney.brawndo.erp.spfea.flyweight;

import java.util.HashMap;

/**
 * The ConcreteFlyweight class of the Flyweight DP. Stores product attributes in a Map
 */
public class ConcreteData {

    private HashMap<Integer, double[]> data = new HashMap<>();

    /**
     * Checks for the specific hashcode in the repository
     * @param hash code of a double[]
     * @return true or false
     */
    public boolean contains(Integer hash) {
        return data.containsKey(hash);
    }

    /**
     * Retrieves the data from the Map
     * @param hash code of a double[]
     * @return a double[]
     */
    public double[] getData(Integer hash) {
        return data.get(hash);
    }

    /**
     * Adds a hashcode Integer and double[] to the Map
     * @param code of the double[]
     * @param array data
     */
    public void addData(Integer code, double[] array) {
        data.put(code, array);
    }

}
