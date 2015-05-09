package milkyway_logic.states;

import milkyway_logic.elements.Player;
import milkyway_logic.elements.Spaceship;
import milkyway_logic.gameboard.BoardConstructor;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class StartGame extends State {

    public StartGame(Game game) {
        super(game);
    }

    @Override
    public State constructGame() {

        BoardConstructor mBoardConstructor = new BoardConstructor();
        getGame().setBoard(mBoardConstructor.getGameBoard());
        getGame().setBankCoins(Constants.INITIAL_PLAYER_COINS - Constants.INITIAL_PLAYER_COINS);
        
        getGame().setPlayer(new Player(1, "player_1", Constants.INITIAL_PLAYER_COINS, new Spaceship(Constants.INITIAL_SPACESHIP_POSITION_X, Constants.INITIAL_SPACESHIP_POSITION_Y)));
  
        return new Move(getGame());
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
        if (getGame().getPlayer().getCoins() == 0) {
            System.exit(0);
        }
        return this;
    }

    @Override
    public State pirateAtack() {
        return this;
    }

    @Override
    public State turnCards() {
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
        return this;
    }

    @Override
    public State move(String move) {
        return this;
    }

    @Override
    public State nextState() {
        return new Move(getGame());
    }
}
