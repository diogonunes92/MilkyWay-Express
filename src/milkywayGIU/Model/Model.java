package milkywayGIU.Model;

import java.awt.Color;
import java.io.IOException;
import java.util.Observable;
import milkyway_logic.cards.Card;
import milkyway_logic.elements.Player;
import milkyway_logic.gameplanner.Game;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.States;
import util.Constants;

public class Model extends Observable {

    Game game;
    private boolean modified = false;

    public Model(Game g) {
        this.game = g;
    }

    public void newGame(){
        this.game.getState().newGame();
    }
    public void constructGame() {
        this.game.constructGame();
        setChanged();
        notifyObservers();
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
        if (this.game.getPlayer().getSpaceship().isCargoUpdated()) {
            setChanged();
            notifyObservers();
        }
    }

    public void buyCargo(String carga) {
        this.game.buyCargo(carga);
        setChanged();
        notifyObservers();
    }

    public void sellCargo(Color carga) {
        this.game.sellCargo(carga);
        setChanged();
        notifyObservers();
    }

    public void isFinished() {

        if (this.game.getState() instanceof Buy) {
            this.game.isFinished();
            setChanged();
            notifyObservers();
        }
    }

    public void nextState() {

        if (this.game.getState() instanceof Move) {
            this.game.getPlayer().roundsPlayed();
        }
        this.game.isFinished();
        this.game.replishMarkets();
        this.game.nextState();
        setChanged();
        notifyObservers();
    }

    public void explore() {
        if (this.game.getState() instanceof Move) {
            this.game.explore();
            this.game.verifyPirateAttack();
            setChanged();
            notifyObservers();
        }
    }

    public States getState() {
        return this.game.getState();
    }

    public Player getPlayer() {
        return this.game.getPlayer();
    }

    public Card[][] getBoard() {
        return this.game.getBoard();
    }

    public void pirateAttack() {
        this.game.pirateAtack(2);
        setChanged();
        notifyObservers();
    }

    public Game loadGame() throws IOException, ClassNotFoundException{
        return Game.loadGame(Constants.FILE_NAME_DEFAULT);
    }

    public void saveGame() throws IOException {
        this.game.saveGame(Constants.FILE_NAME_DEFAULT);
    }
}
