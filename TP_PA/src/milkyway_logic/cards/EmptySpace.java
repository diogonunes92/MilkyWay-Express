package milkyway_logic.cards;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Card;
import milkyway_logic.elements.Cube;

public class EmptySpace extends Card {

    private boolean isTurned;

    public EmptySpace() {
        super();

        this.isTurned = false;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPirate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
