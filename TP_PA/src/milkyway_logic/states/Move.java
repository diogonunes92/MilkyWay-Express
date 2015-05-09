package milkyway_logic.states;

import milkyway_logic.cards.Card;
import milkyway_logic.gameplanner.Game;

public class Move extends State {

    public Move(Game game) {
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
        getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 1);

        return new Move(getGame());
    }

    @Override
    public State nextState() {
        return new Sell(getGame());
    }
}
