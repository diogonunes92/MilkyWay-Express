package milkywayIU;

import java.util.Scanner;
import milkyway_logic.cards.EmptySpace;
import milkyway_logic.cards.Planet;
import milkyway_logic.cards.Wormhole;
import milkyway_logic.gameplanner.Game;

import milkyway_logic.states.*;

public class IuTexto {

    private Game game;
    private boolean isExit, isFinish;

    public IuTexto() {
        this.isExit = false;
        this.game = new Game();
        this.game.setState(new StartGame(game));
    }

    void run() {
        while (!isExit) {
            State mState = game.getState();
            if (mState instanceof StartGame) {
                iuStartGame();
            } else if (mState instanceof Move) {
                iuMove();
            } else if (mState instanceof Buy) {
                iuBuy();
            } else if (mState instanceof Sell) {
                iuSell();
            }
        }
    }

    private void iuStartGame() {

        clearConsole();

        while (!isFinish) {
            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Start game");
            System.out.println(" 2. Load previous game");
            System.out.println(" 3. Credits");
            System.out.println(" 4. Sair");

            System.out.print(" Option: ");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    game.constructGame();
                    isFinish = true;
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    isFinish = true;
                    isExit = true;
                    System.out.println("See you next time, mate!");
                    break;
            }
        }
    }

    private void iuMove() {

        clearConsole();
        printBoard();
        isFinish = false;

        while (!isFinish) {
            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Mover");
            System.out.println(" 2. Next Fase");

            System.out.print(" Option: ");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    boolean isCorrect = true;
                    
                    while(isCorrect){
                        System.out.println(" Option: (Front - F | Back - B | Left - L | Right - R)");
                        String move = mScanner.next();
                        
                        if(move.equalsIgnoreCase("f") ||move.equalsIgnoreCase("b")|| move.equalsIgnoreCase("l") || move.equalsIgnoreCase("r")){
                            isCorrect = false;
//                            game.isInsideLimits(option, option);
                            this.game.move(move);
                        } else{
                            System.out.println(" Incorrect choice");
                        }
                    }
                    
                    isFinish = true;
                    break;
                case 2:
                    this.game.nextState();
                    this.game.turnCards();
                    this.game.replenishMarkets();
                    isFinish = true;
                    break;
            }
        }
    }

    private void iuSell() {

        clearConsole();
        printBoard();
        isFinish = false;

        while (!isFinish) {
            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Sell Cargo");
            System.out.println(" 2. See Prices on Planet");
            System.out.println(" 3. See Cargo on Ship");
            System.out.println(" 4. Update Weapon");
            System.out.println(" 5. Update Cargo");
            System.out.println(" 6. Next Fase");

            System.out.print(" Option: ");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Chose which cargo you want to sell:");
                    String cargo = mScanner.next();
                    this.game.sellCargo(cargo);
                    isFinish = true;
                    break;
                case 2:
                    seePricesOnPlanet();
                    break;
                case 3:
                    seeCargoOnShip();
                    break;

                case 6:
                    this.game.nextState();
                    isFinish = true;
                    return;

            }
        }
    }

    private void iuBuy() {
        isFinish = false;
        printBoard();

        while (!isFinish) {
            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Buy Cargo");
            System.out.println(" 2. See Prices on Planet");
            System.out.println(" 3. See Cargo for Sale");
            System.out.println(" 4. Update Weapon");
            System.out.println(" 5. Update Cargo");
            System.out.println(" 6. Next Fase");

            System.out.print(" Option: ");
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
                    this.game.nextState();
                    isFinish = true;
                    break;

            }
        }

    }

    public void iuSaveGame() {

        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" Do you wanna save the game?");
        System.out.println(" 1. Yes");
        System.out.println(" 2. No");

    }

    public void seeCargoForSale() {
        int i = 0;
        System.out.println("Planet: " + this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getPlanetName());

        while (i < this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getCubeList().size()) {
            System.out.println(this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getCubeList().get(i).getColor());
        }
    }

    public void seePricesOnPlanet() {

        System.out.println("Planet: " + this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getPlanetName());

        System.out.println("Blue" + " : " + this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getPrices().get("Blue"));
        System.out.println("Red" + " : " + this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getPrices().get("Red"));
        System.out.println("Yellow" + " : " + this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getPrices().get("Yellow"));
        System.out.println("Black" + " : " + this.game.getBoard()[this.game.getPlayer().getSpaceship().getPosX()][this.game.getPlayer().getSpaceship().getPosY()].getPrices().get("Black"));
    }

    private void seeCargoOnShip() {
        int i = 0;

        System.out.println("Cargo On Ship:");
        while (i < this.game.getPlayer().getSpaceship().getCargo().size()) {
            System.out.println(this.game.getPlayer().getSpaceship().getCargo().get(i).getColor());
        }
    }

    public void printBoard() {

        String[][] UIGameBoard = new String[7][9];

        if (game.getBoard() != null) {
            
            System.out.println("----------------------------------------------");
            System.out.println("Player name: " + game.getPlayer().getName());
            System.out.println("Player coins: " + game.getPlayer().getCoins());
            System.out.println("");
            
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 9; j++) {

                    if (game.getBoard()[i][j] == null) {
                        UIGameBoard[i][j] = "  *  ";
                    } else {

                        if (game.getBoard()[i][j].getIsTurned()) {
                            if (game.getBoard()[i][j] instanceof Planet) {
                                UIGameBoard[i][j] = "[ P ]";
                            } else if (game.getBoard()[i][j] instanceof Wormhole) {
                                UIGameBoard[i][j] = "[ W ]";
                            } else if (game.getBoard()[i][j] instanceof EmptySpace) {
                                UIGameBoard[i][j] = "[ E ]";
                            }
                        } else {
                            UIGameBoard[i][j] = "[   ]";
                        }
                    }
                    if (this.game.getPlayer().getSpaceship().getPosX() == i && this.game.getPlayer().getSpaceship().getPosY() == j) {
                        System.out.print("\033[31m" + UIGameBoard[i][j] + "\033[0m");
                    } else {
                        System.out.print(UIGameBoard[i][j]);
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("Rounds played: " + game.getRoundsPlayed());
            System.out.println("");
            System.out.println("----------------------------------------------");
            
        } else {
            System.out.println("\033[31m Board doesnt exists \033[0m");
        }
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }
}
