package milkyway_logic.elements;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {
    
    private int power;
    private int posY;
    private int posX;
    private List<Cube> cargo;
    private boolean isCargoUpdated;

    public Spaceship() {
        this.power = 3;
        this.cargo = new ArrayList<>();
        this.posX = 0;
        this.posY = 0;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public boolean isIsCargoUpdated() {
        return isCargoUpdated;
    }

    public void setIsCargoUpdated(boolean isCargoUpdated) {
        this.isCargoUpdated = isCargoUpdated;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<Cube> getCargo() {
        return cargo;
    }

    public void setCargo(List<Cube> cargo) {
        this.cargo = cargo;
    }
    
    
}
