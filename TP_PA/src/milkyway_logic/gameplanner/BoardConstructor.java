package milkyway_logic.gameplanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import milkyway_logic.cards.EmptySpace;
import milkyway_logic.cards.Planet;
import milkyway_logic.cards.Wormhole;
import milkyway_logic.cards.Card;
import util.Constants;

public class BoardConstructor {

    private final Card[][] gameBoard;
    private ArrayList<Card> cardShuffle = new ArrayList<>();
    private HashMap<String, Integer> prices;

    public BoardConstructor() {

        this.gameBoard = new Card[7][9];

        setCardShuffleArray();
        fillGameBoard();
    }

    public Card[][] getGameBoard() {
        return gameBoard;
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
            cardShuffle.add(new Wormhole(false));
        }

        for (int k = 0; k < 12; k++) {
            cardShuffle.add(new EmptySpace());
        }

        Collections.shuffle(cardShuffle);
    }

    private void fillGameBoard() {

        for (int row = 0; row < cardShuffle.size(); row++) {

            gameBoard[Constants.BOARD_POSITIONS[row][0]][Constants.BOARD_POSITIONS[row][1]] = cardShuffle.get(row);
        }

        this.gameBoard[6][0] = new Wormhole(true);
        this.gameBoard[0][8] = new Wormhole(true);
//        First card after the first
        this.gameBoard[5][0].setIsTurned(true);

    }
}
