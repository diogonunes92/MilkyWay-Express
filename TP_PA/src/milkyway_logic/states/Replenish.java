/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkyway_logic.states;

import java.util.List;
import milkyway_logic.elements.*;
import milkyway_logic.gameplanner.Game;

/**
 *
 * @author Diogo
 */
public class Replenish extends State{

    public Replenish(Game game) {
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
        return this;
    }

    @Override
    public State pirateAtack() {
        int piratePower, numAtacks, doneAtacks; 
        numAtacks = 1 + (int)(Math.random()*3); 
        doneAtacks=0;
        while(doneAtacks < numAtacks){
           piratePower = 1 + (int)(Math.random()*6); 
           if(getGame().getmSpaceship().getPower() < piratePower){
               getGame().setMyCoins(getGame().getMyCoins() - (piratePower-getGame().getmSpaceship().getPower()));
           }
        }
        
        return new Sell(getGame());
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
        String color;
        int randomNum; 
         for(int i=0;i<7;i++){
             for(int j=0;j<9;j++){
                if(getGame().getmBoard().getGameBoard()[i][j] instanceof Planet){
                    if(getGame().getmBoard().getGameBoard()[i][j].getIsTurned() && !getGame().getmBoard().getGameBoard()[i][j].getIsPirate()){
                        if(getGame().getmBoard().getGameBoard()[i][j].getCubeList().size() < 2)
                            while(getGame().getmBoard().getGameBoard()[i][j].getCubeList().size() <2){
                                randomNum = 1 + (int)(Math.random()*6); 
                                if(randomNum == 1){
                                    color = "Red";
                                }else if(randomNum == 2){
                                    color = "Blue";
                                }else if(randomNum == 3){
                                    color = "Yellow";
                                }else if(randomNum == 4){
                                    color = "Black";
                                }else{
                                    color = "White";
                                }
                                List<Cube> cubeList = getGame().getmBoard().getGameBoard()[i][j].getCubeList();
                                cubeList.add(new Cube(color));
                                getGame().getmBoard().getGameBoard()[i][j].setCubeList(cubeList);
                            }
                    }
                }
                 
            }
        }
         //TODO: Turn cards;
        return new Sell(getGame());
    }

    @Override
    public State move(String move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
