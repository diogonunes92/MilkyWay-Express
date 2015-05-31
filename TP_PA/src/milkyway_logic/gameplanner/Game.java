package milkyway_logic.gameplanner;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import milkyway_logic.cards.Card;
import milkyway_logic.cards.Planet;
import milkyway_logic.elements.Player;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.States;
import util.Constants;

public final class Game extends Observable implements Serializable {

    private States state;
    private Player player;
    private Card[][] board;
    private static int bankCoins;

    private static int roundsPlayed = 0;

    public Game() {
        state = new StartGame(this);
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card[][] getBoard() {
        return board;
    }

    public void setBoard(Card[][] board) {
        this.board = board;
    }

    public static int getBankCoins() {
        return bankCoins;
    }

    public static void setBankCoins(int bankCoins) {
        Game.bankCoins = bankCoins;
    }

    public void constructGame() {
        this.state = state.constructGame();
    }

    public void move(int x, int y) {
        this.state = state.move(x, y);
    }

    public void moveWormhole(int move_x, int move_y) {
        this.state = state.moveWormhole(move_x, move_y);
    }

    public void replishMarkets() {
        this.state = state.replishMarkets();
    }

    public void upgradeWeapon() {
        this.state = state.upgradeWeapon();
    }

    public void upgradeCargo() {
        this.state = state.upgradeCargo();
    }

    public void buyCargo(String carga) {
        this.state = state.buyCargo(carga);
    }

    public void sellCargo(Color carga) {
        this.state = state.sellCargo(carga);
    }

    public void isFinished() {
        this.state = state.isFinished();
    }

    public String verifyPirateAttack() {
        int isPirateAttack = 1 + (int) (Math.random() * 6);
        if (isPirateAttack == 1) {
            pirateAtack(1);
            return "You're being attacked by Pirates";
        } else {
            return "";
        }
    }

    public void pirateAtack(int inteiro) {

        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet
                && board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()].isPirate() || inteiro == 1) {
            int piratePower, numAtacks, doneAtacks, powerDiference;
            numAtacks = 1 + (int) (Math.random() * 3);
            doneAtacks = 0;

            while (doneAtacks < numAtacks) {
                piratePower = 1 + (int) (Math.random() * 6);
                int playerCoins = this.getPlayer().getCoins();

                if (this.getPlayer().getSpaceship().getPower() < piratePower) {
                    powerDiference = piratePower - this.getPlayer().getSpaceship().getPower();
                    this.getPlayer().setPirateDamage(this.getPlayer().getPirateDamage() + powerDiference);
                    System.out.println("Player Coins -> " + this.getPlayer().getCoins());
                    this.getPlayer().setCoins(playerCoins - powerDiference);
                    System.out.println("Player Coins -> " + this.getPlayer().getCoins());
                    this.getPlayer().setIsAttacked(true);
                }
                doneAtacks++;
            }
        }
    }

    public void nextState() {
        this.state = state.nextState();
    }

    public void explore() {
        System.out.println("Fui chamado (Explore)");
        this.state = state.explore();
    }

    public static int getRoundsPlayed() {
        return roundsPlayed;
    }

    public static void setRoundsPlayed() {
        Game.roundsPlayed++;
    }

    public boolean onPlanetVerifier() {
        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet) {
            return true;
        }
        return false;
    }

    public String sellCargoVerifier(String cargo) {
        if (this.player.getSpaceship().getCargo().isEmpty()) {
            return "Your cargo is empty";
        }

        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet) {
            //sellCargo(cargo);
        }

        return "You're not on a planet";
    }

    public String seePricesOnPlanet() {

        if ((board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet)) {
            return (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()].toStringPrices());
        } else {
            return "You're not on a planet!";
        }

    }

    public String seeCargoOnPlanet() {

        if ((board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet)) {
            return (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()].toStringPrices());
        } else {
            return "You're not on a planet!";
        }

    }

    public boolean verifyLoser() {
        return player.getCoins() <= 0;
    }

    public boolean verifyFinishedGame() {
        for (int x = 0; x < Constants.BOARD_LIMIT_SUP_X; x++) {
            for (int y = 0; y < Constants.BOARD_LIMIT_SUP_Y; y++) {
                if (board[x][y] instanceof Card) {
                    if (!board[x][y].getIsTurned()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void saveGame(String fileName) throws IOException {
        ObjectOutputStream oout = null;

        try {
            oout = new ObjectOutputStream(new FileOutputStream(fileName));
            oout.writeObject(this);
        } finally {
            if (oout != null) {
                oout.close();
            }
        }

    }

    public static Game loadGame(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream oin = null;
        Game j;

        try {
            oin = new ObjectInputStream(new FileInputStream(fileName));
            j = (Game) oin.readObject();
            return j;
        } finally {
            if (oin != null) {
                oin.close();
            }
        }
    }

    public String upgradeWeaponVerifier() {
        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet) {
            //upgradeWeapon();
            return "The Power was upgraded!";
        }
        return "You're not on a planet!";

    }

    public String upgradeCargoVerifier() {
        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet) {
            upgradeCargo();
            return "The Cargo was upgraded!";
        }
        return "You're not on a planet!";

    }

    public String buyCargoVerifier(String cargo) {

        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet) {
            buyCargo(cargo);
        }
        return "You're not on a planet";
    }
}
