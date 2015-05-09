package milkyway_logic.elements;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {

    private int power;
    private int capacity;
    private int posY;
    private int posX;
    private List<Cube> cargo;

    public Spaceship(int posX, int posY) {
        this.power = 3;
        this.cargo = new ArrayList<>();
        this.posX = posX;
        this.posY = posY;
        this.capacity = 2;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = this.posY + posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = this.posX + posX;
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

}
