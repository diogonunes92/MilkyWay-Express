package milkyway_logic.states;

import java.awt.Color;
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
        
        if(getGame().getPlayer().getCoins() <= 0) {
            getGame().getPlayer().setIsWinner(false);
            getGame().getPlayer().setIsFinished(true);
        }
        if(getGame().getPlayer().getCoins() > 0 && getGame().getPlayer().isIsFinished()){
            getGame().getPlayer().setIsWinner(true);
        }
        return this;
    }

    @Override
    public StatesAdapter sellCargo(Color cargo) {
        String cargo_color = null;
        
        switch (cargo.toString()) {
            case "java.awt.Color[r=255,g=0,b=0]":
                cargo_color = "red";
                break;
            case "java.awt.Color[r=255,g=255,b=255]":
                cargo_color = "white";
                break;
            case "java.awt.Color[r=0,g=0,b=0]":
                cargo_color = "black";
                break;
            case "java.awt.Color[r=255,g=255,b=0]":
                cargo_color = "yellow";
                break;
            case "java.awt.Color[r=0,g=0,b=25]":
                cargo_color = "blue";
                break;
        }
        
        int PosX = getGame().getPlayer().getSpaceship().getPosX();
        int PosY = getGame().getPlayer().getSpaceship().getPosY();

        if (getGame().getBoard()[PosX][PosY] instanceof Planet) {
            //If spaceship is on planet ortherwise it can't sell
            System.out.println("Estou no planeta");
            int cargoPrice;
            HashMap<String, Integer> prices = getGame().getBoard()[PosX][PosY].getPrices();
            List<Cube> cubeListPlanet = getGame().getBoard()[PosX][PosY].getCubeList();
            //^FETCHES CUBE LIST PLANET

            List<Cube> cubesSpaceship = getGame().getPlayer().getSpaceship().getCargo();
            cargoPrice = prices.get(cargo_color);
            if (getGame().getBoard()[PosX][PosY].isPirate()) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo_color));
            //If spaceship is on planet ortherwise it can't sell
            System.out.println("É pirata!");
                int pos = getCubePos(cubesSpaceship, cargo_color);
                if (pos < 99) {
            System.out.println("Remove!");
                    cubesSpaceship.remove(pos);
                }
                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);

                return this;
            }

            if (cubeListPlanet.get(0).getColor().compareTo(cargo_color) == 0 && cubeListPlanet.get(1).getColor().compareTo(cargo_color) == 0) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo_color));
            System.out.println("Entrei no 1º if");

                int pos = getCubePos(cubesSpaceship, cargo_color);
                if (pos < 99) {
                    cubesSpaceship.remove(pos);
                                System.out.println("Vendi");

                }
                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
            } else if ((!cubeListPlanet.get(0).getColor().equals(cargo_color) && cubeListPlanet.get(1).getColor().equals(cargo_color)) || (cubeListPlanet.get(0).getColor().equals(cargo_color) && !cubeListPlanet.get(1).getColor().equals(cargo_color))) {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo_color) + 1);
            System.out.println("Entrei 2º If");
                int pos = getCubePos(cubesSpaceship, cargo_color);
                if (pos < 99) {
            System.out.println("Vendi");
                    cubesSpaceship.remove(pos);
                }

                getGame().getPlayer().getSpaceship().setCargo(cubesSpaceship);
            } else {
                getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() + prices.get(cargo_color) + 2);
                int pos = getCubePos(cubesSpaceship, cargo_color);
                            System.out.println("Entrei Else");

                if (pos < 99) {
                    cubesSpaceship.remove(pos);
                                System.out.println("Vendi");

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
        if (getGame().getPlayer().getSpaceship().getCargo().size() == 2) {
            getGame().getPlayer().getSpaceship().setCapacity(getGame().getPlayer().getSpaceship().getCapacity() + 1);
            getGame().getPlayer().setCoins(getGame().getPlayer().getCoins() - 4);
            getGame().getPlayer().getSpaceship().setIsCargoUpdated(true);
        }
        return this;
    }
    @Override

    public StatesAdapter nextState() {
        boolean areAllTurned = true;
        for(int i = 0; i< 7; i++){
            for(int j = 0; j < 9; j++){
                if(getGame().getBoard()[i][j] != null)
                if(!getGame().getBoard()[i][j].getIsTurned())
                    areAllTurned = false;
            }
        }
        
        if(areAllTurned){
            getGame().getPlayer().setIsFinished(true);
        }
        
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
