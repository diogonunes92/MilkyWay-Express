package milkyway_logic.states;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

public class Buy extends State{

    public Buy(Game game) {
        super(game);
    }

    @Override
    public State constructGame() {
        return this;
    }


    @Override
    public State buyCargo(String carga) {
        if(getGame().getTotalCoins() == 0){
            return new StartGame(getGame());
        }
        
        else if((getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()] instanceof Planet) 
                && (getGame().getmSpaceship().getCargo().size() < 2 || getGame().getmSpaceship().getCapacity() == 3 && getGame().getmSpaceship().getCargo().size() < 3)){
            
                int cargoPrice;
                HashMap<String,Integer> prices;
                List<Cube> cubeList = getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()].getCubeList();

                    prices = getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()].getPrices();

                    List<Cube> cubesSpaceship = getGame().getmSpaceship().getCargo();
                    cargoPrice = prices.get(carga);
                    if(getGame().getMyCoins() >= cargoPrice){
                        getGame().setMyCoins(-cargoPrice);
                        cubesSpaceship.add(new Cube(carga));
                        getGame().getmSpaceship().setCargo(cubesSpaceship);
                        cubeList.remove(new Cube(carga));
                        getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()].setCubeList(cubeList);
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
        if(getGame().getMyCoins()== 0){
            return new StartGame(getGame()); 
        }
        
        if(getGame().getmSpaceship().getPower() <6 && getGame().getMyCoins() >=4){
            getGame().getmSpaceship().setPower(+1);
        }
        
        return this;
    }

    @Override
    public State upgradeCargo() {
        if(getGame().getMyCoins() == 0){
            return new StartGame(getGame());
        }
        
        if(getGame().getmSpaceship().getCargo().size() == 2){
            getGame().getmSpaceship().setCapacity(+1);
        }
        
        return this; 
    }

    @Override
    public State replenishMarkets() {
        return this;
    }

    @Override
    public State move(String move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State nextState() {
        return new Replenish(getGame());
    }

    
}
