package milkyway_logic.elements;

import java.util.HashMap;
import java.util.List;

public class Planet extends Card {

    private String planetName;
    private List<Cube> cubeList;
    private final boolean isPirate;
    private HashMap<String, Integer> prices;

    public Planet(String planetName, HashMap<String, Integer> prices, boolean isPirate) {
        super();
        this.planetName = planetName;
        this.prices = prices;
        this.isPirate = isPirate;
    }

    @Override
    public List<Cube> getCubeList() {
        return cubeList;
    }

    @Override
    public void setCubeList(List<Cube> cubeList) {
        this.cubeList = cubeList;
    }

    @Override
    public HashMap<String, Integer> getPrices() {
        return prices;
    }

    @Override
    public String getPlanetName() {
        return planetName;
    }

    @Override
    public boolean isPirate() {
        return isPirate;
    }
}
