package milkyway_logic.elements;

import java.util.HashMap;
import java.util.List;

public class Planet extends Card {

    private String planetName;
    private List<Cube> cubeList;
    private HashMap<String, Integer> prices;

    public Planet(String planetName) {
        super();
        this.planetName = planetName;
        this.cubeList = cubeList;
        this.prices = prices;

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

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}
