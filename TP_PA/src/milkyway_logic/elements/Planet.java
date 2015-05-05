package milkyway_logic.elements;

import java.util.HashMap;

public class Planet extends Card{
    
    private Cube[] cubeList;
    private HashMap<String, Integer> prices;
    private final boolean isPirate;
    

    public Planet(HashMap<String, Integer> prices, boolean isTurned, boolean isPirate) {
        super(isTurned);
        this.cubeList = new Cube[2];
        this.prices = prices;
        this.isPirate = isPirate;
    }

    public Cube[] getCubeList() {
        return cubeList;
    }

    public void setCubeList(Cube[] cubeList) {
        this.cubeList = cubeList;
    }

    public HashMap<String, Integer> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, Integer> prices) {
        this.prices = prices;
    }
}
