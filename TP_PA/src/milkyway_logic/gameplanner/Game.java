package milkyway_logic.gameplanner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import milkyway_logic.cards.Card;
import milkyway_logic.cards.Planet;
import milkyway_logic.elements.Cube;
import milkyway_logic.elements.Player;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.States;
import util.Constants;

public final class Game implements Serializable{

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
        System.out.println("Cheguei ao game bro!");
        this.state = state.buyCargo(carga);
    }

    public void sellCargo(String carga) {
        this.state = state.sellCargo(carga);
    }

    public void isFinished() {
        this.state = state.isFinished();
    }

    public void pirateAtack() {

        int piratePower, numAtacks, doneAtacks, powerDiference;
        numAtacks = 1 + (int) (Math.random() * 3);
        int playerCoins = this.getPlayer().getCoins();
        doneAtacks = 0;
        while (doneAtacks < numAtacks) {
            piratePower = 1 + (int) (Math.random() * 6);
            if (this.getPlayer().getSpaceship().getPower() < piratePower) {
                powerDiference = piratePower - this.getPlayer().getSpaceship().getPower();
                this.getPlayer().setCoins(playerCoins - powerDiference);
            }
            doneAtacks++;
        }

    }

    public void nextState() {
        this.state = state.nextState();
    }

    public void replenishMarkets() {
        String color;
        int randomNum;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {

                if (this.getBoard()[i][j] instanceof Planet) {

                    if (this.getBoard()[i][j].getIsTurned() && !this.getBoard()[i][j].isPirate()) {

                        if (this.getBoard()[i][j].getCubeList().size() < 2) {

                            while (this.getBoard()[i][j].getCubeList().size() < 2) {
                                randomNum = 1 + (int) (Math.random() * 6);

                                if (randomNum == 1) {
                                    color = "red";
                                } else if (randomNum == 2) {
                                    color = "blue";
                                } else if (randomNum == 3) {
                                    color = "yellow";
                                } else if (randomNum == 4) {
                                    color = "black";
                                } else {
                                    color = "white";
                                }
                                List<Cube> cubeList = this.getBoard()[i][j].getCubeList();
                                cubeList.add(new Cube(color));
                                this.getBoard()[i][j].setCubeList(cubeList);
                            }
                        }
                    } else if (this.getBoard()[i][j].getIsTurned() && this.getBoard()[i][j].isPirate()) {
                        List<Cube> cubeList = this.getBoard()[i][j].getCubeList();
                        cubeList.add(new Cube("black"));
                        this.getBoard()[i][j].setCubeList(cubeList);
                    }
                }
            }
        }
    }

    public void turnCards() {
        int posX = this.getPlayer().getSpaceship().getPosX();
        int posY = this.getPlayer().getSpaceship().getPosY();

        //RIGHT 
        if (cardVerifierTurn(posX + 1, posY)) {
            this.getBoard()[posX + 1][posY].setIsTurned(true);
        }
        //DOWN
        if (cardVerifierTurn(posX, posY - 1)) {
            this.getBoard()[posX][posY - 1].setIsTurned(true);
        }

        //LEFT 
        if (cardVerifierTurn(posX - 1, posY)) {
            this.getBoard()[posX - 1][posY].setIsTurned(true);
        }

        //UP
        if (cardVerifierTurn(posX, posY + 1)) {
            this.getBoard()[posX][posY + 1].setIsTurned(true);
        }

        //        VERIFY WHY IS NOT OPENING THE ADJACENT PLACES
//        RIGHT DOWN 
        if (cardVerifierTurn(posX + 1, posY - 1)) {
            this.getBoard()[posX + 1][posY - 1].setIsTurned(true);
        }

//        LEFT DOWN
        if (cardVerifierTurn(posX - 1, posY - 1)) {
            this.getBoard()[posX - 1][posY - 1].setIsTurned(true);
        }

        // LEFT UP
        if (cardVerifierTurn(posX - 1, posY + 1)) {
            this.getBoard()[posX - 1][posY + 1].setIsTurned(true);
        }

        //RIGHT UP
        if (cardVerifierTurn(posX + 1, posY + 1)) {
            this.getBoard()[posX + 1][posY + 1].setIsTurned(true);
        }
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

    public boolean sellCargoVerifier() {
        if (this.player.getSpaceship().getCargo().isEmpty()) {
            return false;
        }

        if (board[player.getSpaceship().getPosX()][player.getSpaceship().getPosY()] instanceof Planet) {
            return true;
        }

        return false;
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

    public boolean verifyPirateAttack() {
        int isPirateAttack = 1 + (int) (Math.random() * 2);
        if (isPirateAttack == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cardVerifierTurn(int posX, int posY) {

        if (posX >= Constants.BOARD_LIMIT_INF_X && posX <= Constants.BOARD_LIMIT_SUP_X && posY >= Constants.BOARD_LIMIT_INF_Y && posY <= Constants.BOARD_LIMIT_SUP_Y) {

            if (this.getBoard()[posX][posY] instanceof Card) {

                if (!this.getBoard()[posX][posY].getIsTurned()) {
                    // if everything passed 
                    return true;
                } else {
//                    System.err.println("The card is already turned!");
                }
            } else {
//                System.err.println("It's not a card");
            }
        } else {
//            System.err.println("out of limits");
        }
        return false;
    }

        public void saveGame(String nomeFicheiro) throws IOException
    {
        ObjectOutputStream oout = null;
        
        try{
            oout = new ObjectOutputStream(new FileOutputStream(nomeFicheiro));        
            oout.writeObject(this);
        }finally{
            if(oout != null){
                oout.close();
            }
        }
        
    }
    
    public static Game loadGame(String nomeFicheiro) throws IOException, ClassNotFoundException 
    {
        ObjectInputStream oin = null;
        Game j;
        
        try{
            oin = new ObjectInputStream(new FileInputStream(nomeFicheiro));        
            j = (Game)oin.readObject();
            return j;
        }finally{
            if(oin != null){
                oin.close();
            }
        }        
    }
}
