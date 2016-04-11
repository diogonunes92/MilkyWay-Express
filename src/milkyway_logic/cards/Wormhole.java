package milkyway_logic.cards;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;

public class Wormhole  extends Card{

    public Wormhole() {
        super();
    }
    
    public Wormhole(boolean isTurned) {
        super();
        super.setIsTurned(isTurned);
    }
    
    @Override
    public List<Cube> getCubeList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCubeList(List<Cube> cubeList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> getPrices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPlanetName() {
        return "Wormhole";
    }

    @Override
    public void setPrices(HashMap<String, Integer> prices) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPlanetName(String planetName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPirate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCardString() {
        return "[ W ]";
    }

    @Override
    public String toStringPrices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toStringCargo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
