/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.Model;

import java.util.Observable;
import milkywayGIU.View.IuGraphic;
import milkyway_logic.cards.Card;
import milkyway_logic.elements.Player;
import milkyway_logic.gameplanner.Game;
import milkyway_logic.states.States;

/**
 *
 * @author Diogo
 */
public class Model extends Observable {

    Game game;
    private boolean modified = false;

    public Model(Game g) {
        this.game = g;
    }

    public void constructGame() {
        this.game.constructGame();
    }

    public void move(int x, int y) {
        this.game.move(x, y);
        setChanged();
        notifyObservers();
    }

    public void moveWormhole(int move_x, int move_y) {
        this.game.moveWormhole(move_x, move_y);
        setChanged();
        notifyObservers();
    }

    public void replishMarkets() {
        this.game.replishMarkets();
        setChanged();
        notifyObservers();
    }

    public void upgradeWeapon() {
        this.game.upgradeWeapon();
        setChanged();
        notifyObservers();
    }

    public void upgradeCargo() {
        this.game.upgradeCargo();
        setChanged();
        notifyObservers();
    }

    public void buyCargo(String carga) {
        this.game.buyCargo(carga);
        setChanged();
        notifyObservers();
    }

    public void sellCargo(String carga) {
        this.game.sellCargo(carga);
        setChanged();
        notifyObservers();
    }

    public void isFinished() {
        this.game.isFinished();
        setChanged();
        notifyObservers();
    }

    public void nextState() {
        this.game.nextState();
        setChanged();
        notifyObservers();

    }

    public void explore() {
        this.game.explore();
        setChanged();
        notifyObservers();

    }

    public States getState() {
        return this.game.getState();
    }
    
    public Player getPlayer(){
        return this.game.getPlayer();
    }

    public Card[][] getBoard(){
        return this.game.getBoard();
    }
}
