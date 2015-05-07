package milkywayIU;

import java.util.Scanner;
import milkyway_logic.gameplanner.Game;

import milkyway_logic.states.*;

public class IuTexto {

    private Game game;
    private boolean isExit;

    public IuTexto() {
        this.isExit = false;

    }

    public void start() {

        printMenu();
        
            System.out.println(" Option:");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    this.game = new Game();
                    run();
                    break;
                case 2: //vercenas

                case 3: //consultar

                case 4:
                    System.out.println(" Thank's for being with us, mate!");
                    isExit = true;
                    return;
            }
        
    }

    void run() {
        while (!isExit) {
            State mState = game.getState();
            if (mState instanceof Buy) {
                iuStart();
            } else if (mState instanceof Buy) {
                iuBuy();
            } else if (mState instanceof Sell) {
                iuSell();
            } else if (mState instanceof Move) {
                iuMove();
            }
        }
    }

    private void printMenu() {

        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" 1. Iniciar Jogo");
        System.out.println(" 2. Ver cenas");
        System.out.println(" 3. Consultar");
        System.out.println(" 4. Sair");
    }

    private void iuMove() {

        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" 1. Mover");
        System.out.println(" 2. Next Fase");
        
         while (!isExit) {

            System.out.println(" Option:");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(" Option: (Front - F | Back - B | Left - L | Right - R)");
                    String move = mScanner.next();
                    this.game.move(move);
                    break;
                case 2: 
                    this.game.setState(new Replenish(this.game));
                    break;
            }
        }
    }

    private void iuSell() {

        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" 1. Buy Cargo");
        System.out.println(" 2. See Prices on Planet");
        System.out.println(" 3. See Cargo on Ship");
        System.out.println(" 4. Update Weapon");
        System.out.println(" 5. Update Cargo");
        System.out.println(" 6. Next Fase");
        
               while (true) {

            System.out.println(" Option:");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Chose which cargo you want to sell:");
                    String cargo = mScanner.next();
                    this.game.buyCargo(cargo);
                    break;
                case 2: 
                    seePricesOnPlanet();
                    break;
                case 3: 
                    seeCargoOnShip();
                    break;
                    
                case 6:
                    this.game.setState(new Move(this.game));
                    return;
                    
            }
        }  
    
    }

    private void iuBuy() {
        
        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" 1. Buy Cargo");
        System.out.println(" 2. See Prices on Planet");
        System.out.println(" 3. See Cargo for Sale");
        System.out.println(" 4. Update Weapon");
        System.out.println(" 5. Update Cargo");
        System.out.println(" 6. Next Fase");
        
         while (true) {

            System.out.println(" Option:");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Chose which cargo you want to buy:");
                    String cargo = mScanner.next();
                    this.game.buyCargo(cargo);
                    break;
                case 2: 
                    seePricesOnPlanet();
                    break;
                case 3: 
                    seeCargoForSale();
                    break;
                    
                case 6:
                    this.game.setState(new Sell(this.game));
                    return;
                    
            }
        }
        
    }

    public void seeCargoForSale() {
        int i=0;
        System.out.println("Planet: " + this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getPlanetName());
        
        while(i < this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getCubeList().size()){
            System.out.println(this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getCubeList().get(i).getColor());
        }
    }
    
    public void seePricesOnPlanet() {
        System.out.println("Planet: " + this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getPlanetName());
        
        System.out.println("Blue"+" : "+this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getPrices().get("Blue"));
        System.out.println("Red"+" : "+this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getPrices().get("Red"));
        System.out.println("Yellow"+" : "+this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getPrices().get("Yellow"));
        System.out.println("Black"+" : "+this.game.getmBoard().getGameBoard()[this.game.getmSpaceship().getPosX()][this.game.getmSpaceship().getPosY()].getPrices().get("Black"));
    }

    private void seeCargoOnShip() {
        int i = 0;
        
        System.out.println("Cargo On Ship:");
        while(i<this.game.getmSpaceship().getCargo().size()){
            System.out.println(this.game.getmSpaceship().getCargo().get(i).getColor());
        }
    }

    private void iuStart() {
        //this.game.initialize();
        
        
    }
}
