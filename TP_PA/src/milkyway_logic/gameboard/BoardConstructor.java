package milkyway_logic.gameboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import milkyway_logic.cards.EmptySpace;
import milkyway_logic.cards.Planet;
import milkyway_logic.cards.Wormhole;
import milkyway_logic.elements.Card;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class BoardConstructor {

    private Card[][] gameBoard;
    private ArrayList<Card> cardShuffle = new ArrayList<Card>();
    private HashMap<String, Integer> prices;
    Game game;

    public BoardConstructor(Game g) {

        this.gameBoard = new Card[7][9];
        this.game = g;
        setCardShuffleArray();

        fillGameBoard();
        getUIGameBoard();
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
                
                if(this.game.getmSpaceship().getPosX() == i && this.game.getmSpaceship().getPosY() == j){
                    UIGameBoard[i][j] = "[ S ]";
                }    
                else if (gameBoard[i][j] == null) {
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

        return UIGameBoard;
    }

    public Card[][] getGameBoard() {
        return gameBoard;
    }
}
