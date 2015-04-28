package elements;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author marcobarbosa
 */
public class Planet {
    
    private List<Cube> cubeList;
    private HashMap<String, Integer> prices;
    private boolean isVisited;

    public Planet(List<Cube> cubeList, HashMap<String, Integer> prices, boolean isVisited) {
        this.cubeList = cubeList;
        this.prices = prices;
        this.isVisited = isVisited;
    }

    public List<Cube> getCubeList() {
        return cubeList;
    }

    public void setCubeList(List<Cube> cubeList) {
        this.cubeList = cubeList;
    }

    public HashMap<String, Integer> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, Integer> prices) {
        this.prices = prices;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }         
}
