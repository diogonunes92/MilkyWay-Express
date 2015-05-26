package milkywayGIU.View;

import milkywayGIU.Model.Model;
import milkyway_logic.gameplanner.Game;

public class Main {

    public static void main(String[] args) {

        Model model = new Model(new Game());
        IuGraphic iuGraphic = new IuGraphic(model);
    }
}
