package milkyway_logic.states;

import milkyway_logic.cards.Planet;
import java.util.List;
import milkyway_logic.cards.Card;
import milkyway_logic.elements.*;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class Replenish extends State {

    public Replenish(Game game) {
        super(game);
    }

    @Override
    public State constructGame() {
        return this;
    }

    @Override
    public State buyCargo(String carga) {
        return this;
    }

    @Override
    public State sellCargo(String carga) {
        return this;
    }

    @Override
    public State isFinished() {
        return this;
    }

    @Override
    public State pirateAtack() {
        int piratePower, numAtacks, doneAtacks;
        numAtacks = 1 + (int) (Math.random() * 3);
        doneAtacks = 0;
        while (doneAtacks < numAtacks) {
            piratePower = 1 + (int) (Math.random() * 6);
            if (getGame().getSpaceship().getPower() < piratePower) {
                getGame().setMyCoins(getGame().getMyCoins() - (piratePower - getGame().getSpaceship().getPower()));
            }
        }

        return new Sell(getGame());
    }

    @Override
    public State turnCards() {

        int sShip_posX = getGame().getSpaceship().getPosX();
        int sShip_posY = getGame().getSpaceship().getPosY();

        if(sShip_posY+1 < Constants.BOARD_LIMIT_SUP_Y){
            if (getGame().getBoard()[sShip_posX][sShip_posY + 1] instanceof Card) {
                getGame().getBoard()[sShip_posX][sShip_posY + 1].setIsTurned(true);
            }
        }
        
        if(sShip_posX+1 < Constants.BOARD_LIMIT_SUP_X && sShip_posY+1 < Constants.BOARD_LIMIT_SUP_Y){
            if (getGame().getBoard()[sShip_posX + 1][sShip_posY + 1] instanceof Card) {
                getGame().getBoard()[sShip_posX + 1][sShip_posY + 1].setIsTurned(true);
            }
        }
        
        if(sShip_posX+1 < Constants.BOARD_LIMIT_SUP_X){
            if (getGame().getBoard()[sShip_posX + 1][sShip_posY] instanceof Card) {
                getGame().getBoard()[sShip_posX + 1][sShip_posY].setIsTurned(true);
            }
        }

        if(sShip_posY-1 > Constants.BOARD_LIMIT_INF_Y){
            if (getGame().getBoard()[sShip_posX][sShip_posY-1] instanceof Card ) {
                getGame().getBoard()[sShip_posX][sShip_posY-1].setIsTurned(true);
            }
        }
        
        if(sShip_posY-1 > Constants.BOARD_LIMIT_INF_Y && sShip_posX-1 > Constants.BOARD_LIMIT_INF_X){
            if (getGame().getBoard()[sShip_posX-1][sShip_posY-1] instanceof Card) {
                getGame().getBoard()[sShip_posX-1][sShip_posY-1].setIsTurned(true);
            }
        }
        
        if(sShip_posY+1 < Constants.BOARD_LIMIT_SUP_Y && sShip_posX-1 > Constants.BOARD_LIMIT_INF_X){
            if (getGame().getBoard()[sShip_posX-1][sShip_posY+1] instanceof Card) {
                getGame().getBoard()[sShip_posX-1][sShip_posY+1].setIsTurned(true);
            }
        }
    
        return this;
    }

    @Override
    public State upgradeWeapon() {
        return this;
    }

    @Override
    public State upgradeCargo() {
        return this;
    }

    @Override
    public State replenishMarkets() {
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
                                    color = "Red";
                                } else if (randomNum == 2) {
                                    color = "Blue";
                                } else if (randomNum == 3) {
                                    color = "Yellow";
                                } else if (randomNum == 4) {
                                    color = "Black";
                                } else {
                                    color = "White";
                                }
                                List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();
                                cubeList.add(new Cube(color));
                                getGame().getBoard()[i][j].setCubeList(cubeList);
                            }
                        }
                    }
                }
            }
        }
        //TODO: Turn cards;
        return new Sell(getGame());
    }

    @Override
    public State move(String move) {
         return this;
    }

    @Override
    public State nextState() {
        return this;
    }
}
