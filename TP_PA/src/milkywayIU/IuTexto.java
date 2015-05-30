package milkywayIU;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import milkyway_logic.gameplanner.Game;

import milkyway_logic.states.*;
import util.Constants;

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
                        loadGameUI();
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

        isFinish = false;

        if (this.game.verifyLoser()) {
            System.out.println("");
            System.out.println(" ::::::  GAME OVER  ::::::::::");
            System.out.println(" IT HAS BEEN A PLEASURE BUT");
            System.out.println(" IT APPEARS YOU HAVE RUN OUT");
            System.out.println(" OF COINS! BETTER LUCK NEXT TIME :( ");
            System.exit(0);

        } else if (this.game.verifyFinishedGame()) {

            if (this.game.verifyLoser()) {
                System.out.println("");
                System.out.println(" ::::::  GAME OVER  ::::::::::");
                System.out.println(" IT HAS BEEN A PLEASURE BUT");
                System.out.println(" IT APPEARS YOU HAVE RUN OUT");
                System.out.println(" OF COINS! BETTER LUCK NEXT TIME :( ");

            } else {
                System.out.println("");
                System.out.println(" ::::::  CONGRATULATIONS  ::::::::::");
                System.out.println(" YOU'VE JUST CONQUERED THE ENTIRE   ");
                System.out.println(" UNIVERSE AND HAVE PAYED YOUR DEBT");
                System.out.println(" YOU'RE JUST LIKE THE LANNISTERS :) ");
            }
            System.exit(0);
        }

        while (!isFinish) {

            System.out.println(" ::::::  MILKY WAY EXPRESS :: MOVE  ::::::::::");
            printBoard();
            System.out.println(" 1. Move in Direction");
            System.out.println(" 2. Move to Wormhole");
            System.out.println(" 3. Next Fase");
            System.out.println(" 4. Quit game");

            System.out.print(" Option: ");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();
            boolean isCorrect = true;

            switch (option) {
                case 1:

                    while (isCorrect) {
                        System.out.println("(x (enter) y)");
                        System.out.println("Option: ");
                        try {
                            int move_x = mScanner.nextInt();
                            int move_y = mScanner.nextInt();
                                isCorrect = false;
                                hasMoved = true;
                                this.game.move(move_x, move_y);
                            
                        } catch (InputMismatchException e) {
                            System.err.println("Wrong caracter");
                            iuMove();
                        }
                    }
                    isFinish = true;
                    break;

                case 2:
                    System.out.println("Enter the wormhole coordinates to which you want to move. (X enter Y)");
                    int move_x = mScanner.nextInt();
                    int move_y = mScanner.nextInt();
                    this.game.moveWormhole(move_x, move_y);
                    hasMoved = true;
                    break;

                case 3:
                    if (hasMoved) {
                        this.game.replishMarkets();
                        this.game.explore();
                        this.game.nextState();

                        //System.out.println(this.game.verifyPirateAttack());

                        isFinish = true;
                        hasMoved = false;
                    } else {
                        System.out.println("You have to move at least once to go forward!");
                    }
                    break;
                case 4:

                    iuSaveGame();

                    if (mScanner.nextInt() == 1) {
                        saveGameUI();
                    } else {
                        System.out.println("See you, mate!");
                    }
            }
            game.pirateAtack(2);

        }
    }

    private void iuSell() {

        isFinish = false;

        while (!isFinish) {

            System.out.println(" ::::::  MILKY WAY EXPRESS :: SELL  ::::::::::");
            printBoard();

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
                        System.out.println("Chose which cargo you want to sell:");
                        String cargo = mScanner.next();
                        System.out.println(this.game.sellCargoVerifier(cargo));
                        isFinish = true;
                        break;
                    case 2:
                        System.out.println(this.game.seePricesOnPlanet());
                    case 3:
                        seeCargoOnShip();
                        break;
                    case 4:
                        System.out.println(this.game.upgradeWeaponVerifier());
                        break;
                    case 5:
                        System.out.println(this.game.upgradeCargoVerifier());
                        break;

                    case 6:
                        this.game.nextState();
                        isFinish = true;
                        return;
                    case 7:

                        iuSaveGame();

                        if (mScanner.nextInt() == 1) {
                            saveGameUI();
                        } else {
                            System.out.println("See you, mate!");
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

        while (!isFinish) {

            System.out.println(" ::::::  MILKY WAY EXPRESS :: BUY  ::::::::::");
            printBoard();

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
                        System.out.println("Chose which cargo you want to buy:");
                        String cargo = mScanner.next();
                        System.out.println(cargo);
                        isFinish = true;
                        System.out.println(this.game.buyCargoVerifier(cargo));
                        break;
                    case 2:
                        System.out.println(this.game.seePricesOnPlanet());
                        break;
                    case 3:
                        System.out.println(this.game.seeCargoOnPlanet());
                        break;
                    case 4:
                        System.out.println(this.game.upgradeWeaponVerifier());
                        break;
                    case 5:
                        System.out.println(this.game.upgradeCargoVerifier());
                        break;

                    case 6:
                        this.game.nextState();
                        isFinish = true;
                        break;

                    case 7:

                        iuSaveGame();

                        if (mScanner.nextInt() == 1) {
                            saveGameUI();
                        } else {
                            System.out.println("See you, mate!");
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
            System.out.println("Player name: " + game.getPlayer().getName() + "          " + "Player coins: " + game.getPlayer().getCoins());
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
            System.out.println("Spaceship Power: " + game.getPlayer().getSpaceship().getPower() + "          " + "Spaceship Capacity: " + game.getPlayer().getSpaceship().getCapacity());
            System.out.println("----------------------------------------------");

        } else {
            System.out.println("\033[31m Board doesnt exists \033[0m");
        }
    }

    boolean loadGameUI() {

        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        String opcao, fileName = Constants.FILE_NAME_DEFAULT;

//        try {
//            opcao = bin.readLine();
//
//            if (opcao.length() >= 1) {
//
//                if (!new java.io.File(opcao).exists()) {
//                    System.out.println("File \"" + opcao + "\" doesn't exist!");
//                    return false;
//                }
//
//                fileName = opcao;
//            }
//        } catch (IOException e) {
//            System.out.println("An error ocureed!");
//            System.out.println(e);
//            System.out.println();
//            return false;
//        }
        try {
            game = Game.loadGame(fileName);
            System.out.println("Game loaded");
            System.out.println();

            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error ocureed loading \"" + fileName + "\" file!");
            System.out.println(e);
            System.out.println();
            return false;
        }

    }

    boolean saveGameUI() {

        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        String opcao, fileName = Constants.FILE_NAME_DEFAULT;

        try {
            opcao = bin.readLine();

            if (opcao.length() >= 1) {
                fileName = opcao;
            }

        } catch (IOException e) {
            return false;
        }

        try {
            game.saveGame(fileName);
            System.out.println("Game saved!");
            System.out.println();
            return true;
        } catch (IOException e) {
            System.out.println("An error ocureed saving \"" + fileName + "\" file!");
            System.out.println(e);
            System.out.println();
            return false;
        }
    }
}
