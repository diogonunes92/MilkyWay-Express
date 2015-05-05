package milkyway_logic.gameplanner;

import milkyway_logic.elements.Player;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.State;

public final class Game {

    private State mState;
    private Player mPlayer;
    
    private static int totalCoins;
    private static int myCoins;

    public Game() {

        mState = new StartGame(this);
        totalCoins = 30;
        myCoins = 0;
    }

    public State getState() {
        return mState;
    }

    public void setState(State mState) {
        this.mState = mState;
    }

    public static void newGame() {
        totalCoins = 20;
        myCoins = 10;
    }

    public static int getTotalCoins() {
        return totalCoins;
    }

    public static void setTotalCoins(int totalCoins) {
        Game.totalCoins = totalCoins;
    }

    public static int getMyCoins() {
        return myCoins;
    }

    public static void setMyCoins(int myCoins) {
        Game.myCoins = myCoins;
    }

    public void constructGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
