package milkyway_logic.states;

import java.util.List;
import milkyway_logic.cards.Planet;
import milkyway_logic.elements.Cube;
import milkyway_logic.elements.Player;
import milkyway_logic.elements.Spaceship;
import milkyway_logic.gameplanner.BoardConstructor;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class StartGame extends StatesAdapter {

    public StartGame(Game game) {
        super(game);
    }

    @Override
    public StatesAdapter constructGame() {
        BoardConstructor mBoardConstructor = new BoardConstructor();
        getGame().setBoard(mBoardConstructor.getGameBoard());
        getGame().setBankCoins(Constants.INITIAL_PLAYER_COINS - Constants.INITIAL_PLAYER_COINS);

        getGame().setPlayer(new Player(1, "player_1", Constants.INITIAL_PLAYER_COINS, new Spaceship(Constants.INITIAL_SPACESHIP_POSITION_X, Constants.INITIAL_SPACESHIP_POSITION_Y)));
        getGame().replishMarkets();

        return new Move(getGame());
    }
    
    @Override
    public States replishMarkets() {

        String color = "white";
        int randomNum;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {

                if (getGame().getBoard()[i][j] instanceof Planet) {

                    if (getGame().getBoard()[i][j].getIsTurned() && !getGame().getBoard()[i][j].isPirate()) {

                        if (getGame().getBoard()[i][j].getCubeList().size() < 2) {
                            List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();

                            while (getGame().getBoard()[i][j].getCubeList().size() < 2) {
                                randomNum = 1 + (int) (Math.random() * 4);

                                if (randomNum == 1) {
                                    color = "red";
                                } else if (randomNum == 2) {
                                    color = "blue";
                                } else if (randomNum == 3) {
                                    color = "yellow";
                                } else {
                                    color = "white";
                                }

                                cubeList.add(new Cube(color));
                                getGame().getBoard()[i][j].setCubeList(cubeList);
                            }
                        } else {
                            List<Cube> cubeList = getGame().getBoard()[i][j].getCubeList();

                            System.out.println("vou remover o white");
                            if (cubeList.get(0).getColor().equals("white")) {
                                System.out.println("Removi o white");

                                cubeList.remove(new Cube("white"));
                            }
                            if (cubeList.get(1).getColor().equals("white")) {
                                System.out.println("Removi o white");

                                cubeList.remove(new Cube("white"));
                            }

                            randomNum = 1 + (int) (Math.random() * 3);

                            if (randomNum == 1) {
                                color = "red";
                            } else if (randomNum == 2) {
                                color = "blue";
                            } else if (randomNum == 3) {
                                color = "yellow";
                            }

                            cubeList.add(new Cube(color));
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
    public StatesAdapter isFinished() {
        if (getGame().getPlayer().getCoins() == 0) {
            System.exit(0);
        }
        return this;
    }

    @Override
    public StatesAdapter nextState() {
        return new Move(getGame());
    }
}
