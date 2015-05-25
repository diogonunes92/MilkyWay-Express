package milkyway_logic.elements;

import java.io.Serializable;

public class Cube implements Serializable{

    private String color;

    public Cube(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
