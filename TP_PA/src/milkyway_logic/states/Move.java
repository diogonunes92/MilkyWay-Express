package milkyway_logic.states;

import milkyway_logic.cards.Card;
import milkyway_logic.gameplanner.Game;

public class Move extends StatesAdapter {

    public Move(Game game) {
        super(game);
    }

    @Override
    public StatesAdapter constructGame() {
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
        getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 1);

        return new Move(getGame());
    }

    @Override
    public StatesAdapter nextState() {
        return new Sell(getGame());
    }
}
