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
public abstract class State {
    
    private Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    
        abstract public State move();
        abstract public State buyCargo(String carga);
        abstract public State sellCargo(String carga);

}
