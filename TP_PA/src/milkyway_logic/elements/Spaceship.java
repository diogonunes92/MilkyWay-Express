package milkyway_logic.elements;

import java.util.List;

/**
 *
 * @author marcobarbosa
 */
public class Spaceship {
    
    private final int id;
    private int power;
    private List<String> cargo;

    public Spaceship(int id, int power, List<String> cargo) {
        this.id = id;
        this.power = power;
        this.cargo = cargo;
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
