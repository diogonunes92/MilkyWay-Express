package milkyway_logic.gameboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import milkyway_logic.cards.EmptySpace;
import milkyway_logic.cards.Planet;
import milkyway_logic.cards.Wormhole;
import milkyway_logic.elements.Card;
import util.Constants;

public class BoardConstructor {

    private Card[][] gameBoard;
    private ArrayList<Card> cardShuffle = new ArrayList<Card>();
    private HashMap<String, Integer> prices;

    public BoardConstructor() {

        this.gameBoard = new Card[7][9];

        setCardShuffleArray();

        fillGameBoard();

    }

    private void setCardShuffleArray() {

        for (int i = 0; i < Constants.PLANET_NAMES.length; i++) {
//            for (int k = 0; k < 4; k++) {
//                prices = new HashMap<String, Integer>();
//                prices.put(Constants.NON_PIRATE_CUBE_COLOR[k], Constants.NON_PIRATE_CUBE_PRICE[i][k]);
//            }
            cardShuffle.add(new Planet(Constants.PLANET_NAMES[i], null, Constants.isPIRATE[i]));
        }
        // Because two of them are going to be declares and added later
        for (int j = 0; j < 2; j++) {
            cardShuffle.add(new Wormhole());
        }

        for (int k = 0; k < 12; k++) {
            cardShuffle.add(new EmptySpace());
        }

        Collections.shuffle(cardShuffle);
    }

    private void fillGameBoard() {

        this.gameBoard[6][0] = new Wormhole();
        this.gameBoard[0][8] = new Wormhole();

        for (int row = 0; row < cardShuffle.size(); row++) {
            for (int col = 0; col < 2; col++) {

                // TODO: Fix this problem. This is returning the position number, and not the content value
                gameBoard[Constants.BOARD_POSITIONS[row][col]][Constants.BOARD_POSITIONS[row][col]] = cardShuffle.get(row);

            }
        }
    }

    public String[][] getUIGameBoard() {

        String[][] UIGameBoard = new String[7][9];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j] == null) {
                    UIGameBoard[i][j] = " ";
                } else if (gameBoard[i][j] instanceof Planet) {
                    UIGameBoard[i][j] = "[ P ]";
                } else if (gameBoard[i][j] instanceof Wormhole) {
                    UIGameBoard[i][j] = "[ W ]";
                } else if (gameBoard[i][j] instanceof EmptySpace) {
                    UIGameBoard[i][j] = "[ E ]";
                }
            }
        }

        return UIGameBoard;
    }

    public Card[][] getGameBoard() {
        return gameBoard;
    }
}
