package milkyway_logic.elements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spaceship implements Serializable {

    private int power;
    private boolean isFirstWeaponUpdated;
    private boolean isSecondWeaponUpdated;
    private int capacity;
    private int posY;
    private int posX;
    private List<Cube> cargo;
    private boolean isCargoUpdated;
    private boolean hasMoved;

    public Spaceship(int posX, int posY) {
        this.power = 3;
        this.cargo = new ArrayList<>();
        this.cargo.add(new Cube("azul"));
        this.posX = posX;
        this.posY = posY;
        this.capacity = 2;
        this.isCargoUpdated = false;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public boolean isCargoUpdated() {
        return isCargoUpdated;
    }

    public void setIsCargoUpdated(boolean isCargoUpdated) {
        this.isCargoUpdated = isCargoUpdated;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public boolean isFirstWeaponUpdated() {
        return isFirstWeaponUpdated;
    }

    public void setIsFirstWeaponUpdated(boolean isFirstWeaponUpdated) {
        this.isFirstWeaponUpdated = isFirstWeaponUpdated;
    }

    public boolean isSecondWeaponUpdated() {
        return isSecondWeaponUpdated;
    }

    public void setIsSecondWeaponUpdated(boolean isSecondWeaponUpdated) {
        this.isSecondWeaponUpdated = isSecondWeaponUpdated;
    }
    
    
}
