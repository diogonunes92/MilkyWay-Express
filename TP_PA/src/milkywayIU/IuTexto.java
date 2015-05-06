package milkywayIU;

import java.util.Scanner;
import milkyway_logic.gameplanner.Game;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.Sell;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.State;

public class IuTexto {

    private Game game;
    private boolean isExit;

    public IuTexto() {
        this.isExit = false;

    }

    public void start() {

        printMenu();
        while (!isExit) {

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
    }

    void run() {

        while (!isExit) {
            State mState = new StartGame(this.game);

            if (mState instanceof StartGame) {

            } else if (mState instanceof Buy) {

            } else if (mState instanceof Sell) {

            } else if (mState instanceof Move) {

            }
        }
    }

    private void printMenu() {

        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" 1. Iniciar Jogo");
        System.out.println(" 2. ver cenas");
        System.out.println(" 3. Consultar");
        System.out.println(" 4. Sair");
    }
}
