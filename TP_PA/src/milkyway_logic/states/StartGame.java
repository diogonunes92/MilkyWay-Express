package milkyway_logic.states;

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
        // TODO: Faz sentido ter isto no interface? 

        BoardConstructor mBoardConstructor = new BoardConstructor();
        getGame().setBoard(mBoardConstructor.getGameBoard());
        getGame().setMyCoins(Constants.INITIAL_PLAYER_COINS);
        getGame().setBankCoins(Constants.INITIAL_PLAYER_COINS - Constants.INITIAL_PLAYER_COINS);
        
        Spaceship mSpaceship = new Spaceship();
        mSpaceship.setPosX(Constants.INITIAL_SPACESHIP_POSITION_X);
        mSpaceship.setPosY(Constants.INITIAL_SPACESHIP_POSITION_Y);
        getGame().setSpaceship(mSpaceship);
        
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
        if (getGame().getMyCoins() == 0) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State nextState() {
        return new Move(getGame());
    }
}
