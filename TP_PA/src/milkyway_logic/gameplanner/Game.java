package milkyway_logic.gameplanner;

import java.io.IOException;
import java.io.PrintWriter;
import milkyway_logic.cards.Card;
import milkyway_logic.elements.Player;
import milkyway_logic.elements.Spaceship;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.State;

public final class Game {

    private State state;
    private Player player;

    private Spaceship spaceship;
    private static int bankCoins;

    private Card[][] board;
    private int myCoins;

    public Game() {
        state = new StartGame(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
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

    public int getMyCoins() {
        return myCoins;
    }

    public void setMyCoins(int myCoins) {
        this.myCoins = myCoins;
    }

    public void constructGame() {
        this.state = state.constructGame();
    }

    public void move(String move) {
        this.state = state.move(move);
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
    
    public void sellCargo(String carga) {
        this.state = state.sellCargo(carga);
    }
    
    public void isFinished() {
        this.state = state.isFinished();
    }
    
    public void pirateAtack() {
        this.state = state.pirateAtack();
    }

    public void nextState() {
        this.state = state.nextState();
    }

    public void saveGame(){

        try {
            PrintWriter writer = new PrintWriter("MilkyWayExpress_savefile.txt", "UTF-8");
            writer.println(myCoins);
            writer.println(bankCoins);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error ocurred when it was writing the file.");
        }
    }
}
