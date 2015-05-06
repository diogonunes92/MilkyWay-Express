package milkyway_logic.elements;

import java.util.HashMap;
import java.util.List;

public class Planet extends Card{
    
    private List<Cube> cubeList;
    private HashMap<String, Integer> prices;
    private boolean isTurned;
    

    public Planet(String nomedoplaneta) {
        super(isTurned);
        this.cubeList = cubeList;
        this.prices = prices;
        this.isTurned = isTurned;
        
        planetConstructor(nomedoplaneta);
        
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

    private void planetConstructor(String nomedoplaneta) {
        
    
        
    }
}
