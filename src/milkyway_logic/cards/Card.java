package milkyway_logic.cards;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;

public abstract class Card implements Serializable{
    private boolean isTurned;

    public Card() {
        this.isTurned = false;
    }
    
    public boolean getIsTurned() {
        return isTurned;
    }

    public void setIsTurned(boolean isTurned) {
        this.isTurned = isTurned;
    }
    
    public abstract String getCardString();
    public abstract List<Cube> getCubeList();
    public abstract void setCubeList(List<Cube> cubeList);
    public abstract HashMap<String, Integer> getPrices();
    public abstract void setPrices(HashMap<String, Integer> prices);
    public abstract String getPlanetName();
    public abstract boolean isPirate();
    public abstract void setPlanetName(String planetName);
    public abstract String toStringPrices();
    public abstract String toStringCargo();
}
