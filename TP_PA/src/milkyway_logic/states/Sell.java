package milkyway_logic.states;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

public class Sell extends State{

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
        if(getGame().getSpaceship().getCargo().isEmpty()){
            return new Buy(getGame());
        }
        else if(getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()] instanceof Planet){
            //If spaceship is on planet ortherwise it can't sell
            
        int cargoPrice;
        HashMap<String,Integer> prices = getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()].getPrices();
        List<Cube> cubeListPlanet = getGame().getBoard()[getGame().getSpaceship().getPosX()][getGame().getSpaceship().getPosY()].getCubeList();

        if(!getGame().getSpaceship().getCargo().isEmpty()){
            
            List<Cube> cubesSpaceship = getGame().getSpaceship().getCargo();
            cargoPrice = prices.get(carga);
            if(cubeListPlanet.get(0).getColor().equals(carga) && cubeListPlanet.get(1).getColor().equals(carga)){
                getGame().setMyCoins(+prices.get(carga));
                cubesSpaceship.remove(new Cube(carga));
                getGame().getSpaceship().setCargo(cubesSpaceship);
            }
            else if((!cubeListPlanet.get(0).getColor().equals(carga) && cubeListPlanet.get(1).getColor().equals(carga)) || (cubeListPlanet.get(0).getColor().equals(carga) && !cubeListPlanet.get(1).getColor().equals(carga))){
                getGame().setMyCoins(getGame().getMyCoins()+prices.get(carga)+1);
                cubesSpaceship.remove(new Cube(carga));
                getGame().getSpaceship().setCargo(cubesSpaceship);
            }
            else{
                getGame().setMyCoins(getGame().getMyCoins()+prices.get(carga)+2);
                cubesSpaceship.remove(new Cube(carga));
                getGame().getSpaceship().setCargo(cubesSpaceship);
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
        if(getGame().getMyCoins()== 0){
            return new StartGame(getGame()); 
        }
        
        if(getGame().getSpaceship().getPower() <6 && getGame().getMyCoins() >=4){
            getGame().getSpaceship().setPower(+1);
        }
        
        return this;
    }

    @Override
    public State upgradeCargo() {
        if(getGame().getMyCoins() == 0){
            return new StartGame(getGame());
        }
        
        if(getGame().getSpaceship().getCargo().size() == 2){
            getGame().getSpaceship().setCapacity(+1);
        }
        
        return this;   
    }

    @Override
    public State replenishMarkets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State move(String move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State nextState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
