package milkyway_logic.gameboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import milkyway_logic.cards.EmptySpace;
import milkyway_logic.cards.Planet;
import milkyway_logic.cards.Wormhole;
import milkyway_logic.cards.Card;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class BoardConstructor {

    private final Card[][] gameBoard;
    private ArrayList<Card> cardShuffle = new ArrayList<>();
    private HashMap<String, Integer> prices;
    private Game game;

    public BoardConstructor(Game game) {

        this.gameBoard = new Card[7][9];
        this.game = game;

        setCardShuffleArray();
        fillGameBoard();
        getUIGameBoard();
    }

    private void setCardShuffleArray() {

        for (int i = 0; i < Constants.PLANET_NAMES.length; i++) {
            prices = new HashMap<>();

            for (int k = 0; k < 4; k++) {
                prices.put(Constants.PLANET_CUBE_COLOR_NAME[k], Constants.PLANET_CUBE_COLOR_PRICES[i][k]);
            }
            cardShuffle.add(new Planet(Constants.PLANET_NAMES[i], prices, Constants.isPIRATE[i]));
        }

        for (int j = 0; j < 2; j++) {
            cardShuffle.add(new Wormhole());
        }

        for (int k = 0; k < 12; k++) {
            cardShuffle.add(new EmptySpace());
        }

        Collections.shuffle(cardShuffle);
    }

    private void fillGameBoard() {

        for (int row = 0; row < cardShuffle.size(); row++) {

            // TODO: Fix this problem. This is returning the position number, and not the content value
            gameBoard[Constants.BOARD_POSITIONS[row][0]][Constants.BOARD_POSITIONS[row][1]] = cardShuffle.get(row);
        }

        this.gameBoard[6][0] = new Wormhole();
        this.gameBoard[0][8] = new Wormhole();
    }

    public String[][] getUIGameBoard() {

        String[][] UIGameBoard = new String[7][9];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {

//                if(this.game.getmSpaceship().getPosX() == i && this.game.getmSpaceship().getPosY() == j){
//                    UIGameBoard[i][j] = "[ S ]";
//                }    
                if (gameBoard[i][j] == null) {
                    UIGameBoard[i][j] = "  *  ";
                } else if (gameBoard[i][j] instanceof Planet) {
                    UIGameBoard[i][j] = "[ P ]";
                } else if (gameBoard[i][j] instanceof Wormhole) {
                    UIGameBoard[i][j] = "[ W ]";
                } else if (gameBoard[i][j] instanceof EmptySpace) {
                    UIGameBoard[i][j] = "[ E ]";
                }

                System.out.print(UIGameBoard[i][j]);
            }
            System.out.println("");
        }

        System.out.print("\033[31m OLA MUNDO  \033[0m");
        return UIGameBoard;
    }

    public Card[][] getGameBoard() {
        return gameBoard;
    }
}
