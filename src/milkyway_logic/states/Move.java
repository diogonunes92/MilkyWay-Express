package milkyway_logic.states;

import java.util.Iterator;
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
    public StatesAdapter moveWormhole(int x, int y) {

        if (wormholeVerifier(x, y)) {
            getGame().getPlayer().getSpaceship().setPosX(x);
            getGame().getPlayer().getSpaceship().setPosY(y);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 1);
        }

        return this;
    }

    @Override
    public StatesAdapter move(int x, int y) {

        // TODO: implement other directions
        int posX = getGame().getPlayer().getSpaceship().getPosX();
        int posY = getGame().getPlayer().getSpaceship().getPosY();

        if (cardVerifier(x, y) && adjacentCardVerifier(x, y)) {
            getGame().getPlayer().getSpaceship().setPosX(x);
            getGame().getPlayer().getSpaceship().setPosY(y);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 1);
            getGame().getPlayer().getSpaceship().setHasMoved(true);

        }

        return new Move(getGame());
    }

    public boolean cardVerifier(int posX, int posY) {

        if (posX >= Constants.BOARD_LIMIT_INF_X && posX <= Constants.BOARD_LIMIT_SUP_X && posY >= Constants.BOARD_LIMIT_INF_Y && posY <= Constants.BOARD_LIMIT_SUP_Y) {

            if (getGame().getBoard()[posX][posY] instanceof Card) {

                return getGame().getBoard()[posX][posY].getIsTurned(); // if everything passed
//                    System.err.println("It's not a turned card!");
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

        String color = "white";
        int randomNum;
        int removed = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {

                if (getGame().getBoard()[i][j] instanceof Planet) {

                    if (getGame().getBoard()[i][j].getIsTurned() && !getGame().getBoard()[i][j].isPirate()) {

                        if (getGame().getBoard()[i][j].getCubeList().size() < 2) {
                            List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();

                            while (getGame().getBoard()[i][j].getCubeList().size() < 2) {
                                randomNum = 1 + (int) (Math.random() * 5);

                                if (randomNum == 1) {
                                    color = "red";
                                } else if (randomNum == 2) {
                                    color = "blue";
                                } else if (randomNum == 3) {
                                    color = "yellow";
                                } else if (randomNum == 4) {
                                    verifyBlackCargo();
                                    System.out.println("OLHA A ANFÂNDEGA!!!!");
                                    color = "white";
                                } else {
                                    color = "white";
                                }

                                cubeList.add(new Cube(color));
                                getGame().getBoard()[i][j].setCubeList(cubeList);
                            }
                        } else {
                            List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();
                            Iterator<Cube> itCube = cubeList.iterator();
                            System.out.println("vou remover o white");
                            while (itCube.hasNext()) {
                                if (itCube.next().getColor().equals("white")) {
                                    System.out.println("Removi o white");
                                    itCube.remove();
                                    removed++;
                                }
                            }
                            for (int r = 0; r < removed; r++) {
                                randomNum = 1 + (int) (Math.random() * 3);

                                if (randomNum == 1) {
                                    color = "red";
                                } else if (randomNum == 2) {
                                    color = "blue";
                                } else if (randomNum == 3) {
                                    color = "yellow";
                                }
                                cubeList.add(new Cube(color));
                            }
                            getGame().getBoard()[i][j].setCubeList(cubeList);
                        }
                    } else if (getGame().getBoard()[i][j].getIsTurned() && getGame().getBoard()[i][j].isPirate() && getGame().getBoard()[i][j].getCubeList().size() < 1) {
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
        System.out.println("Fui chamado (Explore no Move)");
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
            System.out.println("Fiz isto");
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

    private boolean adjacentCardVerifier(int x, int y) {
        int posX = getGame().getPlayer().getSpaceship().getPosX();
        int posY = getGame().getPlayer().getSpaceship().getPosY();

        if (getGame().getBoard()[x][y] instanceof Wormhole && getGame().getBoard()[posX][posY] instanceof Wormhole) {
            return true;
        }

        //RIGHT 
        if (x == posX + 1 && posY == y) {
            return true;
        }
        //DOWN
        if (x == posX && posY + 1 == y) {
            return true;
        }

        //LEFT 
        if (x == posX - 1 && posY == y) {
            return true;
        }

        //UP
        if (x == posX && posY - 1 == y) {
            return true;
        }

        //        VERIFY WHY IS NOT OPENING THE ADJACENT PLACES
//        RIGHT DOWN 
        if (x == posX + 1 && posY - 1 == y) {
            return true;
        }

//        LEFT DOWN
        if (x == posX - 1 && posY - 1 == y) {
            return true;
        }

        // LEFT UP
        if (x == posX - 1 && posY + 1 == y) {
            return true;
        }

        //RIGHT UP
        if (x == posX + 1 && posY + 1 == y) {
            return true;
        }
        return false;
    }

    @Override
    public States pirateAttack() {
        return super.pirateAttack(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StatesAdapter upgradeCargo() {
        getGame().getPlayer().getSpaceship().setCapacity(getGame().getPlayer().getSpaceship().getCapacity() + 1);
        getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);
        getGame().getPlayer().getSpaceship().setIsCargoUpdated(true);
        return this;
    }

    private void verifyBlackCargo() {
        List<Cube> listCubes = getGame().getPlayer().getSpaceship().getCargo();
        Iterator<Cube> it = listCubes.iterator();
        while (it.hasNext()) {
            if (it.next().getColor().equals("black")) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);
                it.remove();
                getGame().getPlayer().getSpaceship().setCargo(listCubes);
                getGame().getPlayer().setCargoSeized(true);
            }
        }

    }
}
