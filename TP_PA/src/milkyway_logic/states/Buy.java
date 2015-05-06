package milkyway_logic.states;

import milkyway_logic.elements.Planet;
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
    public State move() {
        return this;
    }

    @Override
    public State buyCargo(String carga) {
        if(getGame().getTotalCoins() == 0)
            return new StartGame(getGame());
       
        else if((getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()] instanceof Planet) 
                && (getGame().getmSpaceship().getCargo().size() < 2 || !getGame().getmSpaceship().isIsCargoUpdated() && getGame().getmSpaceship().getCargo().size() < 3)){
            
            getGame().buyCargo(carga);
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
        if(getGame().getTotalCoins() == 0){
            return new StartGame(getGame()); 
        }
        return this;
    }

    @Override
    public State upgradeCargo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State replenishMarkets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
