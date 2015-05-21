package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

public class StatesAdapter implements States {

    private Game game;

    public StatesAdapter(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    @Override
    public States constructGame() {
        return this;
    }

    @Override
    public States move(String move) {
        return this;
    }

    @Override
    public States upgradeWeapon() {
        return this;
    }

    @Override
    public States upgradeCargo() {
        return this;
    }

    @Override
    public States buyCargo(String carga) {
        return this;
    }

    @Override
    public States sellCargo(String carga) {
        return this;
    }

    @Override
    public States isFinished() {
        return this;
    }

    @Override
    public States nextState() {
        return this;
    }

    @Override
    public States replishMarkets() {
        return this;
    }

    @Override
    public States explore() {
        return this;
    }

    @Override
    public States pirateAttack() {
        return this;
    } 
}
