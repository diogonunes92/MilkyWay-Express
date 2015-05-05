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
            getGame().constructGame();
            return this;
    }

    @Override
    public State move() {
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

    
    
}
