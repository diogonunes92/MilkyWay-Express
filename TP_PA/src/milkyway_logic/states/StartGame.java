package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

public class StartGame extends State {

    public StartGame(Game game) {
        super(game);
    }

    @Override
    public State constructGame() {

        if (getGame().initialize()) {
            return new Move(getGame());
        }
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
        if (getGame().getPlayer().getCoins() == 0) {
            System.exit(0);
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
    public State move() {
        return this;
    }

    @Override
    public State nextState() {
        return new Move(getGame());
    }
}
