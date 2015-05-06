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
    private static int myCoins;

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

    public State getmState() {
        return mState;
    }

    public BoardConstructor getmBoard() {
        return mBoard;
    }

    public Spaceship getmSpaceship() {
        return mSpaceship;
    }

    public static void setMyCoins(int myCoins) {
        Game.myCoins = myCoins;
    }

    public void constructGame() {

    }
    
    
    public void move(){
        
    };
    
    public void upgrade(){
        
    };
    
    public void buyCargo(String carga){
        boolean existsCargo = false;
        int cargoPrice;
        HashMap<String,Integer> prices;
        List<Cube> cubeList = this.mBoard.getGameBoard()[this.mSpaceship.getPosX()][this.mSpaceship.getPosX()].getCubeList();
         
            prices = this.mBoard.getGameBoard()[this.mSpaceship.getPosX()][this.mSpaceship.getPosX()].getPrices();
            
            List<Cube> cubesSpaceship = mSpaceship.getCargo();
            cargoPrice = prices.get(carga);
            if(getMyCoins() >= cargoPrice){
                setMyCoins(-cargoPrice);
                cubesSpaceship.add(new Cube(carga));
                mSpaceship.setCargo(cubesSpaceship);
                cubeList.remove(new Cube(carga));
                this.mBoard.getGameBoard()[this.mSpaceship.getPosX()][this.mSpaceship.getPosX()].setCubeList(cubeList);
            }
    };
    
    public void sellCargo(String carga){
        boolean existsCargo = false;
        int cargoPrice;
        HashMap<String,Integer> prices = this.mBoard.getGameBoard()[this.mSpaceship.getPosX()][this.mSpaceship.getPosX()].getPrices();
        List<Cube> cubeListPlanet = this.mBoard.getGameBoard()[this.mSpaceship.getPosX()][this.mSpaceship.getPosX()].getCubeList();

         
        if(!mSpaceship.getCargo().isEmpty()){
            
            List<Cube> cubesSpaceship = mSpaceship.getCargo();
            cargoPrice = prices.get(carga);
            if(cubeListPlanet.get(0).getColor() == carga && cubeListPlanet.get(1).getColor() == carga){
                setMyCoins(+prices.get(carga));
                cubesSpaceship.remove(new Cube(carga));
            }
            else if((cubeListPlanet.get(0).getColor() != carga && cubeListPlanet.get(1).getColor() == carga) || (cubeListPlanet.get(0).getColor() == carga && cubeListPlanet.get(1).getColor() != carga)){
                setMyCoins(getMyCoins()+prices.get(carga)+1);
                cubesSpaceship.remove(new Cube(carga));
            }
            else{
                setMyCoins(getMyCoins()+prices.get(carga)+2);
                cubesSpaceship.remove(new Cube(carga));
            }
         }
        
    };
    
    public void isFinished(){
        
    };
    
    public void pirateAtack(){
        
    };
}
