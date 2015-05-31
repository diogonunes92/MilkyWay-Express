package milkyway_logic.states;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

public class Buy extends StatesAdapter {
    
    public Buy(Game game) {
        super(game);
    }
    
    @Override
    public StatesAdapter buyCargo(String cargo) {
        
        int PosX = getGame().getPlayer().getSpaceship().getPosX();
        int PosY = getGame().getPlayer().getSpaceship().getPosY();
        
        if (getGame().getBoard()[PosX][PosY] instanceof Planet) {
            if (getGame().getPlayer().getSpaceship().getCargo().size() < 2) {
                int cargoPrice;
                HashMap<String, Integer> prices = getGame().getBoard()[PosX][PosY].getPrices();
                List<Cube> cubeList = getGame().getBoard()[PosX][PosY].getCubeList();
                System.out.println("ESTOU A COMPRAR");
                //^THIS FETCHS PLANET'S PRICES AND CUBES FOR SALE 
                List<Cube> cubesSpaceship = getGame().getPlayer().getSpaceship().getCargo();
                cargoPrice = prices.get(cargo.toLowerCase());
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
                for (int i = 0; i < getGame().getPlayer().getSpaceship().getCargo().size(); i++) {
                    System.out.println("the cargo on board -> " + getGame().getPlayer().getSpaceship().getCargo().get(i).getColor());
                }
                
                for (int i = 0; i < getGame().getBoard()[PosX][PosY].getCubeList().size(); i++) {
                    System.out.println("the cargo on planet -> " + getGame().getBoard()[PosX][PosY].getCubeList().get(i).getColor());
                }
                
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
                
                return this;
            }
        }
        
        return this;
    }
    
    
    @Override
    public StatesAdapter upgradeWeapon() {
        
        if (!getGame().getPlayer().getSpaceship().isFirstWeaponUpdated()) {
            if (getGame().getPlayer().getCoins() >= 4) {
                getGame().getPlayer().getSpaceship().setPower(5);
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);
                getGame().getPlayer().getSpaceship().setIsFirstWeaponUpdated(true);
            }
        } else {
            if (getGame().getPlayer().getCoins() >= 5) {
                getGame().getPlayer().getSpaceship().setPower(6);
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 5);
                getGame().getPlayer().getSpaceship().setIsSecondWeaponUpdated(true);
            }
        }
        return this;
    }
    
    @Override
    public StatesAdapter upgradeCargo() {
        if (getGame().getPlayer().getSpaceship().getCargo().size() == 2) {
            getGame().getPlayer().getSpaceship().setCapacity(getGame().getPlayer().getSpaceship().getCapacity() + 1);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);
            getGame().getPlayer().getSpaceship().setIsCargoUpdated(true);
        }
        return this;
    }
    
    @Override
    public StatesAdapter nextState() {
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
