package milkywayIU;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import milkyway_logic.cards.Planet;
import milkyway_logic.gameplanner.Game;

import milkyway_logic.states.*;

public class IuTexto {

    private Game game;
    private boolean isExit, isFinish, hasMoved;

    public IuTexto() {
        this.isExit = false;
        this.game = new Game();
        this.game.setState(new StartGame(game));
    }

    void run() {
        while (!isExit) {
            States mState = game.getState();
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

        while (!isFinish) {

            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Start game");
            System.out.println(" 2. Load previous game");
            System.out.println(" 3. Credits");
            System.out.println(" 4. Quit");

            System.out.print(" Option: ");

            int option;

            try {
                Scanner mScanner = new Scanner(System.in);
                option = mScanner.nextInt();

                switch (option) {
                    case 1:
                        game.constructGame();
                        isFinish = true;
                        break;
                    case 2:
                        System.out.println("Not implemented yet");
                        break;
                    case 3:
                        System.out.println("");
                        System.out.println("Course: Programação Avançada");
                        System.out.println("v.1.0");
                        System.out.println("");
                        System.out.println("Programmers:");
                        System.out.println("Marco Barbosa, n21200304");
                        System.out.println("Diogo Nunes, n21200809");
                        System.out.println("");
                        break;
                    case 4:
                        isFinish = true;
                        isExit = true;
                        System.out.println("See you next time, mate!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wrong caracter");
                iuStartGame();
            }
        }
    }

    private void iuMove() {

        printBoard();
        isFinish = false;

        if (this.game.verifyLoser()) {
            System.out.println("");
            System.out.println(" ::::::  GAME OVER  ::::::::::");
            System.out.println(" IT HAS BEEN A PLEASURE BUT");
            System.out.println(" IT APPEARS YOU HAVE RUN OUT");
            System.out.println(" OF COINS! BETTER LUCK NEXT TIME :( ");
            System.exit(0);
        }

        if (this.game.verifyFinishedGame()) {
            if (this.game.verifyLoser()) {
                System.out.println("");
                System.out.println(" ::::::  GAME OVER  ::::::::::");
                System.out.println(" IT HAS BEEN A PLEASURE BUT");
                System.out.println(" IT APPEARS YOU HAVE RUN OUT");
                System.out.println(" OF COINS! BETTER LUCK NEXT TIME :( ");
                System.exit(0);
            } else {
                System.out.println("");
                System.out.println(" ::::::  CONGRATULATIONS  ::::::::::");
                System.out.println(" YOU'VE JUST CONQUERED THE ENTIRE   ");
                System.out.println(" UNIVERSE AND HAVE PAYED YOUR DEBT");
                System.out.println(" YOU'RE JUST LIKE THE LANNISTERS :) ");
                System.exit(0);
            }
        }

        while (!isFinish) {

            System.out.println("");
            System.out.println("Player name: " + game.getPlayer().getName());
            System.out.println("Player coins: " + game.getPlayer().getCoins());
            System.out.println("Spaceship Power: " + game.getPlayer().getSpaceship().getPower());
            System.out.println("Spaceship Capacity: " + game.getPlayer().getSpaceship().getCapacity());
            System.out.println("");
            System.out.println(" ::::::  MILKY WAY EXPRESS :: MOVE  ::::::::::");
            System.out.println(" 1. Move");
            System.out.println(" 2. Next Fase");
            System.out.println(" 3. Quit game");

            System.out.print(" Option: ");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();
            boolean isCorrect = true;

            switch (option) {
                case 1:

                    while (isCorrect) {
                        System.out.println("(Front - F | Back - B | Left - L | Right - R)");
                        System.out.println("Option: ");
                        try {
                            String move = mScanner.next();

                            if (move.equalsIgnoreCase("f") || move.equalsIgnoreCase("b") || move.equalsIgnoreCase("l") || move.equalsIgnoreCase("r")) {
                                isCorrect = false;

                                if (this.game.moveSpaceship(move)) {
                                    System.out.println("Correct move");
                                    hasMoved = true;
                                    this.game.move();
                                } else {
                                    System.out.println(" Wrong move");
                                }

                            } else {
                                System.out.println(" Incorrect choice");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Wrong caracter");
                            iuMove();
                        }
                    }
                    isFinish = true;
                    break;

                case 2:
                    if (hasMoved) {
                        this.game.nextState();
                        this.game.replenishMarkets();
                        if (this.game.verifyPirateAttack()) {
                            System.out.println("You are under a pirate atack!");
                            this.game.pirateAtack();
                        }
                        this.game.turnCards();
                        isFinish = true;
                        hasMoved = false;
                    } else {
                        System.out.println("You have to move at least once to go forward!");
                    }
                    break;
                case 3:
                    iuSaveGame();

                    if (mScanner.nextInt() == 1) {
                        try {
                            game.saveGame("ola");

                        } catch (IOException ex) {
                            Logger.getLogger(IuTexto.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {

                    }
            }

            if (game.getBoard()[game.getPlayer().getSpaceship().getPosX()][game.getPlayer().getSpaceship().getPosY()] instanceof Planet
                    && game.getBoard()[game.getPlayer().getSpaceship().getPosX()][game.getPlayer().getSpaceship().getPosY()].isPirate()) {
                game.pirateAtack();
            }

        }
    }

    private void iuSell() {

        printBoard();
        isFinish = false;

        while (!isFinish) {

            System.out.println("");
            System.out.println("Player name: " + game.getPlayer().getName());
            System.out.println("Player coins: " + game.getPlayer().getCoins());
            System.out.println("Spaceship Power: " + game.getPlayer().getSpaceship().getPower());
            System.out.println("Spaceship Capacity: " + game.getPlayer().getSpaceship().getCapacity());
            System.out.println("");

            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Sell Cargo");
            System.out.println(" 2. See Prices on Planet");
            System.out.println(" 3. See Cargo on Ship");
            System.out.println(" 4. Update Weapon");
            System.out.println(" 5. Update Cargo");
            System.out.println(" 6. Next Fase");
            System.out.println(" 7. Quit game");

            try {
                System.out.print(" Option: ");

                Scanner mScanner = new Scanner(System.in);
                int option = mScanner.nextInt();

                switch (option) {
                    case 1:
                        if (this.game.sellCargoVerifier()) {
                            System.out.println("Chose which cargo you want to sell:");
                            String cargo = mScanner.next();
                            this.game.sellCargo(cargo);
                        } else {
                            System.out.println("You're not on a planet or don't have cargo to sell!");
                        }

                        isFinish = true;
                        break;
                    case 2:
                        if (this.game.onPlanetVerifier()) {
                            seePricesOnPlanet();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;
                    case 3:
                        seeCargoOnShip();
                        break;
                    case 4:
                        if (this.game.onPlanetVerifier()) {
                            this.game.upgradeWeapon();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;
                    case 5:
                        if (this.game.onPlanetVerifier()) {
                            this.game.upgradeCargo();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;

                    case 6:
                        this.game.nextState();
                        isFinish = true;
                        return;
                    case 7:
                        iuSaveGame();

                        if (mScanner.nextInt() == 1) {
                            try {
                                game.saveGame("ola");

                            } catch (IOException ex) {
                                Logger.getLogger(IuTexto.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {

                        }
                }
            } catch (InputMismatchException e) {
                System.err.println("Wrong caracter");
                iuMove();
            }
        }
    }

    private void iuBuy() {
        isFinish = false;
        printBoard();

        while (!isFinish) {

            System.out.println("");
            System.out.println("----------------------------------------------");
            System.out.println("Player name: " + game.getPlayer().getName());
            System.out.println("Player coins: " + game.getPlayer().getCoins());
            System.out.println("Spaceship Power: " + game.getPlayer().getSpaceship().getPower());
            System.out.println("Spaceship Capacity: " + game.getPlayer().getSpaceship().getCapacity());
            System.out.println("");
            System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
            System.out.println(" 1. Buy Cargo");
            System.out.println(" 2. See Prices on Planet");
            System.out.println(" 3. See Cargo for Sale");
            System.out.println(" 4. Update Weapon");
            System.out.println(" 5. Update Cargo");
            System.out.println(" 6. Next Fase");
            System.out.println(" 7. Quit game");

            try {
                System.out.print(" Option: ");
                Scanner mScanner = new Scanner(System.in);
                int option = mScanner.nextInt();

                switch (option) {
                    case 1:
                        if (this.game.onPlanetVerifier()) {
                            System.out.println("Chose which cargo you want to buy:");
                            String cargo = mScanner.next();
                            System.out.println(cargo);
                            this.game.buyCargo(cargo);
                            isFinish = true;
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;
                    case 2:
                        if (this.game.onPlanetVerifier()) {
                            seePricesOnPlanet();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;
                    case 3:
                        if (this.game.onPlanetVerifier()) {
                            seeCargoForSale();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;

                    case 4:
                        if (this.game.onPlanetVerifier()) {
                            this.game.upgradeWeapon();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;
                    case 5:
                        if (this.game.onPlanetVerifier()) {
                            this.game.upgradeCargo();
                        } else {
                            System.out.println("You're not on a planet!");
                        }
                        break;
                    case 6:
                        this.game.nextState();
                        isFinish = true;
                        break;

                    case 7:
                        iuSaveGame();

                        if (mScanner.nextInt() == 1) {
                            try {
                                game.saveGame("ola");

                            } catch (IOException ex) {
                                Logger.getLogger(IuTexto.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {

                        }
                }

            } catch (InputMismatchException e) {
                System.err.println("Wrong caracter");
                iuMove();
            }
        }
    }

    public void iuSaveGame() {
        System.out.println(" Do you wanna save the game?");
        System.out.println(" 1. Yes");
        System.out.println(" 2. No");

    }

    public void seeCargoForSale() {
        int i = 0;
        int posX = this.game.getPlayer().getSpaceship().getPosX();
        int posY = this.game.getPlayer().getSpaceship().getPosY();
        System.out.println("Planet: " + this.game.getBoard()[posX][posY].getPlanetName());

        while (i < this.game.getBoard()[posX][posY].getCubeList().size()) {
            System.out.println(this.game.getBoard()[posX][posY].getCubeList().get(i).getColor());
            i++;
        }
    }

    public void seePricesOnPlanet() {

        int posX = this.game.getPlayer().getSpaceship().getPosX();
        int posY = this.game.getPlayer().getSpaceship().getPosY();

        System.out.println("Planet: " + this.game.getBoard()[posX][this.game.getPlayer().getSpaceship().getPosY()].getPlanetName());

        System.out.println("Blue" + " : " + this.game.getBoard()[posX][posY].getPrices().get("blue"));
        System.out.println("Red" + " : " + this.game.getBoard()[posX][posY].getPrices().get("red"));
        System.out.println("Yellow" + " : " + this.game.getBoard()[posX][posY].getPrices().get("yellow"));
        System.out.println("Black" + " : " + this.game.getBoard()[posX][posY].getPrices().get("black"));
    }

    private void seeCargoOnShip() {
        int i = 0;

        System.out.println("Cargo On Ship:");
        while (i < this.game.getPlayer().getSpaceship().getCargo().size()) {
            System.out.println(this.game.getPlayer().getSpaceship().getCargo().get(i).getColor());
            i++;
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
                            UIGameBoard[i][j] = game.getBoard()[i][j].getCardString();
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
            System.out.println("State: " + game.getState().toString());
            System.out.println("");
            System.out.println("----------------------------------------------");

        } else {
            System.out.println("\033[31m Board doesnt exists \033[0m");
        }
    }

}
