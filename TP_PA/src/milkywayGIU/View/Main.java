package milkywayGIU.View;

import milkywayGIU.Model.Model;
import milkyway_logic.gameplanner.Game;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Model m = new Model(new Game());
        IuGraphic iuGraphic = new IuGraphic(m);
    }
}
