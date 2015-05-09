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
    public State buyCargo(String cargo) {

        int PosX = getGame().getPlayer().getSpaceship().getPosX();
        int PosY = getGame().getPlayer().getSpaceship().getPosY();
        if (getGame().getBankCoins() == 0) {
            return new Move(getGame());
        }
        if (getGame().getBoard()[PosX][PosY] instanceof Planet) {
            if (getGame().getPlayer().getSpaceship().getCargo().size() < 2) {
                int cargoPrice;
                HashMap<String, Integer> prices = getGame().getBoard()[PosX][PosY].getPrices();
                List<Cube> cubeList = getGame().getBoard()[PosX][PosY].getCubeList();
                System.out.println("CUBE_LIST [0] - > " + cubeList.get(0));

                //^THIS FETCHS PLANET'S PRICES AND CUBES FOR SALE 
                System.out.println("CARGO - > " + cargo);
                List<Cube> cubesSpaceship = getGame().getPlayer().getSpaceship().getCargo();
                cargoPrice = prices.get(cargo);
                System.out.println("CARGO PRICE -> " + cargoPrice);
                //^THIS FETCHS THE CUBES FROM THE SPACESHIP AND THE PRICES FROM CARGOS ON PLANET
                if (getGame().getPlayer().getCoins() >= cargoPrice) {
                    getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - cargoPrice);
                    cubesSpaceship.add(new Cube(cargo));
                    getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
                    int pos = getCubePos(cubeList, cargo);
                    if (pos < 99) {
                        cubeList.remove(pos);
                    }
                    getGame().getBoard()[PosX][PosY].setCubeList(cubeList);
                }

                getGame().setRoundsPlayed();

                return this;

            } else if (getGame().getPlayer().getSpaceship().isCargoUpdated() && getGame().getPlayer().getSpaceship().getCargo().size() < 3) {
                int cargoPrice;
                HashMap<String, Integer> prices;
                List<Cube> cubeList = getGame().getBoard()[PosX][PosY].getCubeList();

                prices = getGame().getBoard()[PosX][PosY].getPrices();

                List<Cube> cubesSpaceship = getGame().getPlayer().getSpaceship().getCargo();
                cargoPrice = prices.get(cargo);

                if (getGame().getPlayer().getCoins() >= cargoPrice) {
                    getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - cargoPrice);
                    cubesSpaceship.add(new Cube(cargo));
                    getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
                    int pos = getCubePos(cubeList, cargo);
                    if (pos < 99) {
                        cubeList.remove(pos);
                    }
                    getGame().getBoard()[PosX][PosY].setCubeList(cubeList);
                }

                getGame().setRoundsPlayed();
       System.out.println("RETURN THIS 2" );

                return this;
            }
        }
       System.out.println("RETURN THIS " );

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
    public State upgradeWeapon() {

        if (getGame().getPlayer().getSpaceship().getPower() < 6 && getGame().getPlayer().getCoins() >= 4) {
            getGame().getPlayer().getSpaceship().setPower(getGame().getPlayer().getSpaceship().getPower() + 1);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);

        }
        return this;
    }

    @Override
    public State upgradeCargo() {
        if (getGame().getPlayer().getSpaceship().getCargo().size() == 2) {
            getGame().getPlayer().getSpaceship().setCapacity(getGame().getPlayer().getSpaceship().getCapacity() + 1);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);
            getGame().getPlayer().getSpaceship().setIsCargoUpdated(true);
        }
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

    private int getCubePos(List<Cube> c, String cargo) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getColor().compareTo(cargo) == 0) {
                return i;
            }
        }
        return 99;
    }
}
