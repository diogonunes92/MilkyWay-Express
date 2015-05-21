package milkyway_logic.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;

public class Planet extends Card {

    private String planetName;
    private List<Cube> cubeList;
    private boolean isPirate;
    private HashMap<String, Integer> prices;

    public Planet(String planetName, HashMap<String, Integer> prices, boolean isPirate) {
        super();
        this.cubeList = new ArrayList<>();
        this.planetName = planetName;
        this.prices = prices;
        this.isPirate = isPirate;
    }

    public String toStringPrices() {
        return "Planet{" + "prices=" + prices + '}';
    }


    public String toStringCargo(){
        String cargo ="Planet Cargo { ";
        
        for(int i=0;i<cubeList.size();i++){
            cargo = " "+cubeList.get(i);
        }
        return cargo;
    }
        
    @Override
    public List<Cube> getCubeList() {
        return cubeList;
    }

    public boolean getIsPirate() {
        return isPirate;
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
    public void setPrices(HashMap<String, Integer> prices) {
        this.prices = prices;
    }

    @Override
    public String getPlanetName() {
        return planetName;
    }

    @Override
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    @Override
    public boolean isPirate() {
        return this.isPirate;
    }

    @Override
    public String getCardString() {
        return "[ P ]";
    }
}
