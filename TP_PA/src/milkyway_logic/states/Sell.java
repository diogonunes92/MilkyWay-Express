package milkyway_logic.states;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

public class Sell extends StatesAdapter {

    public Sell(Game game) {
        super(game);
    }
    
    @Override
    public StatesAdapter isFinished() {
        if (getGame().getPlayer().getCoins() <= 0) {
            System.out.println("Acabou :(");
            System.exit(0);
        }
        return this;
    }
    
    
    @Override
    public StatesAdapter sellCargo(String cargo) {
        int PosX = getGame().getPlayer().getSpaceship().getPosX();
        int PosY = getGame().getPlayer().getSpaceship().getPosY();

        if (getGame().getBoard()[PosX][PosY] instanceof Planet) {
            //If spaceship is on planet ortherwise it can't sell

            int cargoPrice;
            HashMap<String, Integer> prices = getGame().getBoard()[PosX][PosY].getPrices();
            List<Cube> cubeListPlanet = getGame().getBoard()[PosX][PosY].getCubeList();
            //^FETCHES CUBE LIST PLANET

            List<Cube> cubesSpaceship = getGame().getPlayer().getSpaceship().getCargo();
            cargoPrice = prices.get(cargo);
            if (getGame().getBoard()[PosX][PosY].isPirate()) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo));

                int pos = getCubePos(cubesSpaceship, cargo);
                if (pos < 99) {
                    cubesSpaceship.remove(pos);
                }
                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);

                return this;
            }

            if (cubeListPlanet.get(0).getColor().compareTo(cargo) == 0 && cubeListPlanet.get(1).getColor().compareTo(cargo) == 0) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo));

                int pos = getCubePos(cubesSpaceship, cargo);
                if (pos < 99) {
                    cubesSpaceship.remove(pos);
                }
                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
            } else if ((!cubeListPlanet.get(0).getColor().equals(cargo) && cubeListPlanet.get(1).getColor().equals(cargo)) || (cubeListPlanet.get(0).getColor().equals(cargo) && !cubeListPlanet.get(1).getColor().equals(cargo))) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo) + 1);

                int pos = getCubePos(cubesSpaceship, cargo);
                if (pos < 99) {
                    cubesSpaceship.remove(pos);
                }

                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
            } else {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo) + 2);
                int pos = getCubePos(cubesSpaceship, cargo);
                if (pos < 99) {
                    cubesSpaceship.remove(pos);
                }
                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
            }

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
    public StatesAdapter nextState() {
        isFinished();
        return new Buy(getGame());
    }

    private int getCubePos(List<Cube> c, String cargo) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getColor().compareTo(cargo) == 0) {
                return i;
            }
        }
        return 99;
    }
}
