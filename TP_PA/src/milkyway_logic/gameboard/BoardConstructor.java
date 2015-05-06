package milkyway_logic.gameboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import milkyway_logic.elements.Card;
import milkyway_logic.elements.EmptySpace;
import milkyway_logic.elements.Planet;
import milkyway_logic.elements.Wormhole;
import util.Constants;

public class BoardConstructor {

    private Card[][] gameBoard;
    private ArrayList<Card> cardShuffle = new ArrayList<Card>();
    private HashMap<String, Integer> prices;

    public BoardConstructor() {

        this.gameBoard = new Card[7][9];

        setCardShuffleArray();
        Collections.shuffle(cardShuffle);

        fillGameBoard();
    }

    private void setCardShuffleArray() {

        for (int i = 0; i < Constants.PLANET_NAMES.length; i++) {
            for (int k = 0; k < 4; k++) {
                prices = new HashMap<String, Integer>();
                prices.put(Constants.NON_PIRATE_CUBE_COLOR[k], Constants.NON_PIRATE_CUBE_PRICE[i][k]);
            }
            cardShuffle.add(new Planet(Constants.PLANET_NAMES[i], prices, Constants.isPIRATE[i]));
        }

        // Because two of them are going to be declares and added later
        for (int j = 0; j < 2; j++) {
            cardShuffle.add(new Wormhole());
        }

        for (int k = 0; k < 12; k++) {
            cardShuffle.add(new EmptySpace());
        }
    }

    private void fillGameBoard() {

        this.gameBoard[7][0] = new Wormhole();
        this.gameBoard[0][9] = new Wormhole();

        for (int i = 0; i < cardShuffle.size(); i++) {

            gameBoard[Constants.BOARD_POSITION_X[i]][Constants.BOARD_POSITION_Y[i]] = cardShuffle.get(i);
        }
    }

    public String[][] getUIGameBoard() {

        String[][] UIGameBoard = new String[7][9];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                if (gameBoard[i][j] == null) {
                            
                }
            }
        }

        return UIGameBoard;
    }

    public Card[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Card[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

}
