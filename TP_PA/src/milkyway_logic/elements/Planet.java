package milkyway_logic.elements;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author marcobarbosa
 */
public class Planet{
    
    private List<Cube> cubeList;
    private HashMap<String, Integer> prices;
    private boolean isTurned;

    public Planet(List<Cube> cubeList, HashMap<String, Integer> prices, boolean isTurned) {
        this.cubeList = cubeList;
        this.prices = prices;
        this.isTurned = isTurned;
        
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

    public boolean getIsTurned() {
        return isTurned;
    }

    public void setIsTurned(boolean isTurned) {
        this.isTurned = isTurned;
    }         
}
