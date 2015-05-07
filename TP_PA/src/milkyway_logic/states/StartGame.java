/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

/**
 *
 * @author marcobarbosa
 */
public class StartGame extends State{

    public StartGame(Game game) {
        super(game);
    }


    @Override
    public State constructGame() {
            getGame().initialize();
            return new Move(getGame());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
