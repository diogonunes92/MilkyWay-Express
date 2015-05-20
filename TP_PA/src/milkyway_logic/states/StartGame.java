package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

public class StartGame extends StatesAdapter {

    public StartGame(Game game) {
        super(game);
    }

    @Override
    public StatesAdapter constructGame() {

        if (getGame().initialize()) {
            return new Move(getGame());
        }
        return this;
    }

    @Override
    public StatesAdapter buyCargo(String carga) {
        return this;
    }

    @Override
    public StatesAdapter sellCargo(String carga) {
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
    public StatesAdapter upgradeWeapon() {
        return this;
    }

    @Override
    public StatesAdapter upgradeCargo() {
        return this;
    }

    @Override
    public StatesAdapter move() {
        return this;
    }

    @Override
    public StatesAdapter nextState() {
        return new Move(getGame());
    }
}
