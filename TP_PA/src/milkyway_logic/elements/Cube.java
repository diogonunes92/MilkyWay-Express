package milkyway_logic.elements;

import java.awt.Color;
import java.io.Serializable;
import util.Constants;

public class Cube implements Serializable {

    private String color;
    private Color bgColor;

    public Cube(String color) {
        this.color = color;
        this.bgColor = Color.GRAY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Color getColorObject() {

        if (this.color.equals(Constants.BLACK_COLOR)) {
            return bgColor = Color.WHITE;
        } else if (this.color.equals(Constants.BLUE_COLOR)) {
            return bgColor = Color.BLUE;
        } else if (this.color.equals(Constants.RED_COLOR)) {
            return bgColor = Color.RED;
        }
        return bgColor;
    }
}
