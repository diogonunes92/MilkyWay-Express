package milkyway_logic.states;

import java.util.List;
import milkyway_logic.cards.Card;
import milkyway_logic.cards.Planet;
import milkyway_logic.cards.Wormhole;
import milkyway_logic.elements.Cube;
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
    public StatesAdapter moveWormhole(int x, int y) {

        if (wormholeVerifier(x, y)) {
            getGame().getPlayer().getSpaceship().setPosX(x);
            getGame().getPlayer().getSpaceship().setPosY(y);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins()-1);
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
    public States replishMarkets() {

        String color;
        int randomNum;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {

                if (getGame().getBoard()[i][j] instanceof Planet) {

                    if (getGame().getBoard()[i][j].getIsTurned() && !getGame().getBoard()[i][j].isPirate()) {

                        if (getGame().getBoard()[i][j].getCubeList().size() < 2) {

                            while (getGame().getBoard()[i][j].getCubeList().size() < 2) {
                                randomNum = 1 + (int) (Math.random() * 6);

                                if (randomNum == 1) {
                                    color = "red";
                                } else if (randomNum == 2) {
                                    color = "blue";
                                } else if (randomNum == 3) {
                                    color = "yellow";
                                } else if (randomNum == 4) {
                                    color = "black";
                                } else {
                                    color = "white";
                                }
                                List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();
                                cubeList.add(new Cube(color));
                                getGame().getBoard()[i][j].setCubeList(cubeList);
                            }
                        }
                    } else if (getGame().getBoard()[i][j].getIsTurned() && getGame().getBoard()[i][j].isPirate()) {
                        List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();
                        cubeList.add(new Cube("black"));
                        getGame().getBoard()[i][j].setCubeList(cubeList);
                    }
                }
            }
        }

        return this;
    }

    @Override
    public States explore() {

        int posX = getGame().getPlayer().getSpaceship().getPosX();
        int posY = getGame().getPlayer().getSpaceship().getPosY();

        //RIGHT 
        if (cardVerifierTurn(posX + 1, posY)) {
            getGame().getBoard()[posX + 1][posY].setIsTurned(true);
        }
        //DOWN
        if (cardVerifierTurn(posX, posY - 1)) {
            getGame().getBoard()[posX][posY - 1].setIsTurned(true);
        }

        //LEFT 
        if (cardVerifierTurn(posX - 1, posY)) {
            getGame().getBoard()[posX - 1][posY].setIsTurned(true);
        }

        //UP
        if (cardVerifierTurn(posX, posY + 1)) {
            getGame().getBoard()[posX][posY + 1].setIsTurned(true);
        }

        //        VERIFY WHY IS NOT OPENING THE ADJACENT PLACES
//        RIGHT DOWN 
        if (cardVerifierTurn(posX + 1, posY - 1)) {
            getGame().getBoard()[posX + 1][posY - 1].setIsTurned(true);
        }

//        LEFT DOWN
        if (cardVerifierTurn(posX - 1, posY - 1)) {
            getGame().getBoard()[posX - 1][posY - 1].setIsTurned(true);
        }

        // LEFT UP
        if (cardVerifierTurn(posX - 1, posY + 1)) {
            getGame().getBoard()[posX - 1][posY + 1].setIsTurned(true);
        }

        //RIGHT UP
        if (cardVerifierTurn(posX + 1, posY + 1)) {
            getGame().getBoard()[posX + 1][posY + 1].setIsTurned(true);
        }

        return this;
    }

    private boolean cardVerifierTurn(int posX, int posY) {

        if (posX >= Constants.BOARD_LIMIT_INF_X && posX <= Constants.BOARD_LIMIT_SUP_X && posY >= Constants.BOARD_LIMIT_INF_Y && posY <= Constants.BOARD_LIMIT_SUP_Y) {

            if (getGame().getBoard()[posX][posY] instanceof Card) {

                if (!getGame().getBoard()[posX][posY].getIsTurned()) {
                    // if everything passed 
                    return true;
                } else {
//                    System.err.println("The card is already turned!");
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

    private boolean wormholeVerifier(int posX, int posY) {

        if (posX >= Constants.BOARD_LIMIT_INF_X && posX <= Constants.BOARD_LIMIT_SUP_X && posY >= Constants.BOARD_LIMIT_INF_Y && posY <= Constants.BOARD_LIMIT_SUP_Y) {
            if (getGame().getBoard()[posX][posY] instanceof Wormhole) {
                if (getGame().getBoard()[posX][posY].getIsTurned()) {
                    return true;
                }
            }
        }
        return false;
    }
}
