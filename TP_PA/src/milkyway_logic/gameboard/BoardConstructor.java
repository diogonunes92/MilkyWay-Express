package milkyway_logic.gameboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import milkyway_logic.elements.Card;
import milkyway_logic.elements.Planet;
import milkyway_logic.elements.Wormhole;
import util.Constants;

public class BoardConstructor {

    private Card[][] gameBoard;

    private ArrayList<Card> cardShuffle = new ArrayList<Card>();

    private final Planet planetGether;
    private final Planet planetKiber;
    private final Planet planetReverie;
    private final Planet planetTiamat;
    private final Planet planetLamarckia;
    private final Planet planetArrakis;

    private final Planet planetWhirl;
    private final Planet planetStriterax;
    private final Planet planetAsperta;

    private final Wormhole mWormhole1;
    private final Wormhole mWormhole2;
    private final Wormhole mWormhole3;
    private final Wormhole mWormhole4;

    public BoardConstructor() {

        this.gameBoard = new Card[7][9];
        this.planetGether = new Planet("Gether");
        this.planetKiber = new Planet("Kiber");
        this.planetReverie = new Planet("Reverie");
        this.planetTiamat = new Planet("Tiamat");
        this.planetLamarckia = new Planet("Lamarckia");
        this.planetArrakis = new Planet("Arrakis");
        this.planetWhirl = new Planet("Whirl");
        this.planetStriterax = new Planet("Striterax");
        this.planetAsperta = new Planet("Asperta");
        this.mWormhole1 = new Wormhole();
        this.mWormhole2 = new Wormhole();
        this.mWormhole3 = new Wormhole();
        this.mWormhole4 = new Wormhole();
        this.gameBoard[7][0] = mWormhole1;
        this.gameBoard[0][9] = mWormhole2;
        
        // TODO: Create free places. i forgot, kurwa 

        setCardShuffleArray();

        Collections.shuffle(cardShuffle);

        fillGameBoard();
    }

    private void setCardShuffleArray() {
        cardShuffle.add(planetGether);
        cardShuffle.add(planetKiber);
        cardShuffle.add(planetReverie);
        cardShuffle.add(planetTiamat);
        cardShuffle.add(planetLamarckia);
        cardShuffle.add(planetArrakis);
        cardShuffle.add(planetWhirl);
        cardShuffle.add(planetStriterax);
        cardShuffle.add(planetAsperta);
        cardShuffle.add(mWormhole4);
        cardShuffle.add(mWormhole4);
        
        
    }

    private void fillGameBoard() {

        for (int i = 0; i < cardShuffle.size(); i++) {

            gameBoard[Constants.BOARD_POSITION_X[i]][Constants.BOARD_POSITION_X[i]] = cardShuffle.get(i);
        }
    }

    public Card[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Card[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

}
