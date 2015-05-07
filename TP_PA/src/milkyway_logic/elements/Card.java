package milkyway_logic.elements;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author marcobarbosa
 */
public abstract class Card{
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
    
    public abstract List<Cube> getCubeList();
    public abstract void setCubeList(List<Cube> cubeList);
    public abstract HashMap<String, Integer> getPrices();
    public abstract void setPrices(HashMap<String, Integer> prices);
    public abstract String getPlanetName();
    public abstract boolean isPirate();
    public abstract void setPlanetName(String planetName);
}
