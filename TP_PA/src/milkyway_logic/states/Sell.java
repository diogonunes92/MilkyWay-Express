package milkyway_logic.states;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

public class Sell extends State {

    public Sell(Game game) {
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
        if (getGame().getPlayer().getSpaceship().getCargo().isEmpty()) {
            return new Buy(getGame());
        } else if (getGame().getBoard()[getGame().getPlayer().getSpaceship().getPosX()][getGame().getPlayer().getSpaceship().getPosY()] instanceof Planet) {
            //If spaceship is on planet ortherwise it can't sell

            int cargoPrice;
            HashMap<String, Integer> prices = getGame().getBoard()[getGame().getPlayer().getSpaceship().getPosX()][getGame().getPlayer().getSpaceship().getPosY()].getPrices();
            List<Cube> cubeListPlanet = getGame().getBoard()[getGame().getPlayer().getSpaceship().getPosX()][getGame().getPlayer().getSpaceship().getPosY()].getCubeList();

            if (!getGame().getPlayer().getSpaceship().getCargo().isEmpty()) {

                List<Cube> cubesSpaceship = getGame().getPlayer().getSpaceship().getCargo();
                cargoPrice = prices.get(carga);
                if (cubeListPlanet.get(0).getColor().equals(carga) && cubeListPlanet.get(1).getColor().equals(carga)) {
                    getGame().getPlayer().setCoins(+prices.get(carga));
                    cubesSpaceship.remove(new Cube(carga));
                    getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
                } else if ((!cubeListPlanet.get(0).getColor().equals(carga) && cubeListPlanet.get(1).getColor().equals(carga)) || (cubeListPlanet.get(0).getColor().equals(carga) && !cubeListPlanet.get(1).getColor().equals(carga))) {
                    getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(carga) + 1);
                    cubesSpaceship.remove(new Cube(carga));
                    getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
                } else {
                    getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(carga) + 2);
                    cubesSpaceship.remove(new Cube(carga));
                    getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
                }
            }

        }
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
        if (getGame().getPlayer().getCoins() == 0) {
            return new StartGame(getGame());
        }

        if (getGame().getPlayer().getSpaceship().getPower() < 6 && getGame().getPlayer().getCoins() >= 4) {
            getGame().getPlayer().getSpaceship().setPower(+1);
        }

        return this;
    }

    @Override
    public State upgradeCargo() {
        if (getGame().getPlayer().getCoins() == 0) {
            return new StartGame(getGame());
        }

        if (getGame().getPlayer().getSpaceship().getCargo().size() == 2) {
            getGame().getPlayer().getSpaceship().setCapacity(+1);
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
        return new Buy(getGame());
    }
}
