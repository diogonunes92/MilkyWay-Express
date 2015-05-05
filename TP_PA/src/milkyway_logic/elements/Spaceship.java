package milkyway_logic.elements;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {
    
    private static int id = 0;
    private int power;
    private int posY;
    private int posX;
    private List<String> cargo;

    public Spaceship() {
        Spaceship.id++;
        this.power = 3;
        this.cargo = new ArrayList<>();
        this.posX = 0;
        this.posY = 0;
    }

    public int getId() {
        return id;
    }
    
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public List<String> getCargo() {
        return cargo;
    }

    public void setCargo(List<String> cargo) {
        this.cargo = cargo;
    }
    
    
}
