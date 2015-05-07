package milkyway_logic.states;

import milkyway_logic.cards.Card;
import milkyway_logic.gameplanner.Game;

public class Move extends State{

    public Move(Game game) {
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
        return this;
    }


    @Override
    public State isFinished() {
        if(getGame().getMyCoins() == 0){
            System.exit(0);
        }
        
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
        return this;
    }

    @Override
    public State upgradeCargo() {
        return this;
    }

    @Override
    public State replenishMarkets() {
        return this;
    }

    @Override
    public State move(String move) {
        
        if(move.compareTo("F") == 0 && getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()+1] instanceof Card){
            getGame().getmSpaceship().setPosY(+1);
        }
        
        else if(move.compareTo("B") == 0 && getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()][getGame().getmSpaceship().getPosY()-1] instanceof Card){
            getGame().getmSpaceship().setPosY(-1);
        }
        
        else if(move.compareTo("L") == 0 && getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()-1][getGame().getmSpaceship().getPosY()] instanceof Card){
            getGame().getmSpaceship().setPosX(-1);
        }
        
        else if(move.compareTo("R") == 0 && getGame().getmBoard().getGameBoard()[getGame().getmSpaceship().getPosX()+1][getGame().getmSpaceship().getPosY()] instanceof Card){
            getGame().getmSpaceship().setPosX(+1);
        }
        
        return new Replenish(getGame());
    }

    @Override
    public State nextState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
