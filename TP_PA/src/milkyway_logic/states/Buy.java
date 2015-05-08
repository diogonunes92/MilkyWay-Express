package milkyway_logic.states;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

public class Buy extends State {

    public Buy(Game game) {
        super(game);
    }

    @Override
    public State constructGame() {
        return this;
    }

    @Override
    public State buyCargo(String carga) {
        if (getGame().getBankCoins() == 0) {
            return new StartGame(getGame());
        } else if ((getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()] instanceof Planet)
                && (getGame().getSpaceship().getCargo().size() < 2 || getGame().getSpaceship().getCapacity() == 3 && getGame().getSpaceship().getCargo().size() < 3)) {

            int cargoPrice;
            HashMap<String, Integer> prices;
            List<Cube> cubeList = getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()].getCubeList();

            prices = getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()].getPrices();

            List<Cube> cubesSpaceship = getGame().getSpaceship().getCargo();
            cargoPrice = prices.get(carga);
            
            if (getGame().getMyCoins() >= cargoPrice) {
                getGame().setMyCoins(-cargoPrice);
                cubesSpaceship.add(new Cube(carga));
                getGame().getSpaceship().setCargo(cubesSpaceship);
                cubeList.remove(new Cube(carga));
                getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()].setCubeList(cubeList);
            }
            return this;
        }
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
        return this;
    }

    @Override
    public State turnCards() {
        return this;
    }

    @Override
    public State upgradeWeapon() {
        if (getGame().getMyCoins() == 0) {
            return new StartGame(getGame());
        }

        if (getGame().getSpaceship().getPower() < 6 && getGame().getMyCoins() >= 4) {
            getGame().getSpaceship().setPower(+1);
        }
        return this;
    }

    @Override
    public State upgradeCargo() {
        if (getGame().getMyCoins() == 0) {
            return new StartGame(getGame());
        }

        if (getGame().getSpaceship().getCargo().size() == 2) {
            getGame().getSpaceship().setCapacity(+1);
        }
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
