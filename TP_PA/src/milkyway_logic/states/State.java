package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

public abstract class State {

    private Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    abstract public State constructGame();
    abstract public State move(String move);
    abstract public State upgradeWeapon();
    abstract public State upgradeCargo();
    abstract public State buyCargo(String carga);
    abstract public State sellCargo(String carga);
    abstract public State isFinished();
    abstract public State pirateAtack();
    abstract public State turnCards();
    abstract public State replenishMarkets();
    abstract public State nextState();

}
