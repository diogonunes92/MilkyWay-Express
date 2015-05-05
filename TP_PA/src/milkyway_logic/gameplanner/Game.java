package milkyway_logic.gameplanner;

import milkyway_logic.elements.Spaceship;
import milkyway_logic.gameboard.BoardConstructor;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.State;

public final class Game {

    private State mState;
    private BoardConstructor mBoard;
    private Spaceship mSpaceship;
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
        this.mSpaceship = new Spaceship();
        this.mBoard = new BoardConstructor();
    }
    
    
    public void move(){
        
    };
    
    public void upgrade(){
        
    };
    
    public void buyCargo(String carga){
         boolean existsCargo = false;
         
         if(mSpaceship.getCargo().size() > 2 && !mSpaceship.isIsCargoUpdated()){
             
         }
    };
    
    public void sellCargo(String carga){
        
    };
    
    public void isFinished(){
        
    };
    
    public void pirateAtack(){
        
    };
}
