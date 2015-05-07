package milkyway_logic.cards;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Card;
import milkyway_logic.elements.Cube;

public class Planet extends Card {

    private String planetName;
    private List<Cube> cubeList;
    private boolean isPirate;
    private HashMap<String, Integer> prices;

    public Planet(String planetName, HashMap<String, Integer> prices, boolean isPirate) {
        super();
        this.planetName = planetName;
        this.prices = prices;
        this.isPirate = isPirate;
    }

    public List<Cube> getCubeList() {
        return cubeList;
    }

    public boolean getIsPirate() {
        return isPirate;
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

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}