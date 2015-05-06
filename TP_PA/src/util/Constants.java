package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marcobarbosa
 */
public class Constants {

    //Game initiation
    public static final int INITIAL_BANK_COINS = 30;
    public static final int INITIAL_PLAYER_COINS = 10;

    // Board
    public static final int BOARD_TOTAL_CARDS = 25;
    public static final int BLACK_HOLES_TOTAL_CARDS = 4;

    public static final int TOTAL_BOARD_SPACES = 63;
    public static final int TOTAL_CARD_SPACES = 23;

    public static final String[] PLANET_NAMES = {"Gether", "Kiber", "Reverie", "Tiamat", "Lamarckia", "Arrakis", "Whirl", "Striterax", "Asperta"};//You Can Add More Names
    public static final boolean[] isPIRATE = {false, false, false, false, false, false, true, true, true};//You Can Add More Amount

    // TODO Decrement on the number. marco knows why, ask him
    public static final int[] BOARD_POSITION_X = {4, 9, 3, 3, 4, 5, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 5, 6, 7, 1, 6};
    public static final int[] BOARD_POSITION_Y = {2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6};

    public static final String[] NON_PIRATE_CUBE_COLOR = {"blue", "yellow", "red", "black"};
    public static final int[][] NON_PIRATE_CUBE_PRICE = {{1, 3, 2, 3},
                                                        {3, 1, 2, 3},
                                                        {1, 2, 3, 3},
                                                        {3, 2, 1, 3},
                                                        {2, 3, 1, 3},
                                                        {2, 1, 3, 3}};
}
