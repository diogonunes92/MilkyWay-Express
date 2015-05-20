package milkyway_logic.states;

import milkyway_logic.cards.Card;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class Move extends StatesAdapter {

    public Move(Game game) {
        super(game);
    }

    @Override
    public StatesAdapter isFinished() {
        if (getGame().getPlayer().getCoins() == 0) {
            System.exit(0);
        }
        return this;
    }

    @Override
    public StatesAdapter move(String move) {

        // TODO: implement other directions
        
        int posX = getGame().getPlayer().getSpaceship().getPosX();
        int posY = getGame().getPlayer().getSpaceship().getPosY();

        if (move.equalsIgnoreCase("f") && cardVerifier(posX - 1, posY)) {
            getGame().getPlayer().getSpaceship().setPosX(posX - 1);

        } else if (move.equalsIgnoreCase("b") && cardVerifier(posX + 1, posY)) {
            getGame().getPlayer().getSpaceship().setPosX(posX + 1);

        } else if (move.equalsIgnoreCase("l") && cardVerifier(posX, posY - 1)) {
            getGame().getPlayer().getSpaceship().setPosY(posY - 1);

        } else if (move.equalsIgnoreCase("r") && cardVerifier(posX, posY + 1)) {
            getGame().getPlayer().getSpaceship().setPosY(posY + 1);
        }

        getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 1);

        return new Move(getGame());
    }
    
        public boolean cardVerifier(int posX, int posY) {

        if (posX >= Constants.BOARD_LIMIT_INF_X && posX <= Constants.BOARD_LIMIT_SUP_X && posY >= Constants.BOARD_LIMIT_INF_Y && posY <= Constants.BOARD_LIMIT_SUP_Y) {

            if (getGame().getBoard()[posX][posY] instanceof Card) {

                if (getGame().getBoard()[posX][posY].getIsTurned()) {
                    // if everything passed 
                    return true;
                } else {
//                    System.err.println("It's not a turned card!");
                    return false;
                }
            } else {
//                System.err.println("It's not a card");
            }
        } else {
//            System.err.println("out of limits");
        }
        return false;
    }

    @Override
    public StatesAdapter nextState() {
        return new Sell(getGame());
    }
}
