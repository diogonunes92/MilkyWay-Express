package milkyway_logic.elements;

import java.awt.Color;
import java.io.Serializable;
import util.Constants;

public class Cube implements Serializable {

    private String color;
    private Color bgColor;

    public Cube(String color) {

        if (null != color) {
            
            this.color = color;

            switch (color) {
                case "red":
                    bgColor = Color.red;
                    break;
                case "blue":
                    bgColor = Color.blue;
                    break;
                case "yellow":
                    bgColor = Color.yellow;
                    break;
                case "white":
                    bgColor = Color.white;
                    break;
                case "black":
                    bgColor = Color.black;
                    break;
            }
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;

        if (null != color) {
            switch (color) {
                case "red":
                    bgColor = Color.red;
                    break;
                case "blue":
                    bgColor = Color.blue;
                    break;
                case "yellow":
                    bgColor = Color.yellow;
                    break;
                case "white":
                    bgColor = Color.white;
                    break;
                case "black":
                    bgColor = Color.black;
                    break;
            }
        }
    }

    public Color getColorObject() {
        return bgColor;
    }
}
