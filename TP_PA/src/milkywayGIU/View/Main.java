package milkywayGIU.View;

import milkywayGIU.Model.Model;
import milkyway_logic.gameplanner.Game;

public class Main {

    public static void main(String[] args) {
        IuGraphic iuGraphic = new IuGraphic(new Model(new Game()));
    }
}
