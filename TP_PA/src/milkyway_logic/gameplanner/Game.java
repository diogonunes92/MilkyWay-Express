package milkyway_logic.gameplanner;

import java.util.HashMap;
import java.util.List;
import milkyway_logic.elements.Cube;
import milkyway_logic.elements.Planet;
import milkyway_logic.elements.Player;
import milkyway_logic.elements.Spaceship;
import milkyway_logic.gameboard.BoardConstructor;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.State;

public final class Game {

    private State mState;
    private Player mPlayer;
    private Spaceship mSpaceship;
    private BoardConstructor mBoard;
    private static int totalCoins;
    private int myCoins;

    public Game() {

        mState = new StartGame(this);
        totalCoins = 30;
        myCoins = 10;

    }

    public State getState() {
        return mState;
    }

    public void setState(State mState) {
        this.mState = mState;
    }

    public void newGame() {
        totalCoins = 20;
        myCoins = 10;
    }
    
    public void initialize() {
        mBoard = new BoardConstructor();
    }

    public static int getTotalCoins() {
        return totalCoins;
    }

    public static void setTotalCoins(int totalCoins) {
        Game.totalCoins = totalCoins;
    }

    public int getMyCoins() {
        return myCoins;
    }

    public State getmState() {
        return mState;
    }

    public BoardConstructor getmBoard() {
        return mBoard;
    }

    public Spaceship getmSpaceship() {
        return mSpaceship;
    }

    public void setMyCoins(int myCoins) {
        this.myCoins = myCoins;
    }

    public void constructGame() {
        this.mState = mState.constructGame();
    }
    
    
    public void move(String move){
        this.mState = mState.move(move);
    };
    
    public void upgradeWeapon(){
        this.mState = mState.upgradeWeapon();
    };
    
    public void upgradeCargo(){
        this.mState = mState.upgradeCargo();
    };
    
    public void buyCargo(String carga){
        this.mState = mState.buyCargo(carga);
    };
    
    public void sellCargo(String carga){
        this.mState = mState.sellCargo(carga);
    };
    
    public void isFinished(){
        this.mState = mState.isFinished();
    };
    
    public void pirateAtack(){
        this.mState = mState.pirateAtack();
    };


}
