package au.edu.sydney.brawndo.erp.spfea.flyweight;

import java.util.Arrays;

/**
 * Factory of the FLyweight DP. Acts as interface for the Client and ConcreteData.
 */
public class FlyweightFactory {

    ConcreteData repo;

    /**
     * Constructor
     */
    public FlyweightFactory() {
        this.repo = new ConcreteData();
    }

    /**
     * Adds data to the ConcreteData repo.
     * @param array of double[] from a Product
     */
    public void addData(double[] array) {
        repo.addData(Arrays.hashCode(array), array);
    }

    /**
     * Retrieves product data from the repository. If the data is not already present, it is added to the Map too
     * @param data double[]
     * @return the double[] from the Map
     */
    public double[] get(double[] data) {
        Integer hash = Arrays.hashCode(data);
        if (repo.contains(hash)) {
            return repo.getData(hash);
        } else {
            repo.addData(hash, data);
            return data;
        }
    }

    /**
     * Checks if the double[] is in the repository
     * @param array
     * @return true or false
     */
    public boolean contains(double[] array) {
        return repo.contains(Arrays.hashCode(array));
    }

}
