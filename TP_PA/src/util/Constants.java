package util;

import java.awt.Font;

public class Constants {

    public static final String FILE_NAME_DEFAULT = "saveFileMilkyway.bin";

    //Game initiation
    public static final int INITIAL_BANK_COINS = 30;
    public static final int INITIAL_PLAYER_COINS = 10;
    public static final int INITIAL_SPACESHIP_POSITION_X = 6;
    public static final int INITIAL_SPACESHIP_POSITION_Y = 0;
    public static final int BOARD_LIMIT_SUP_X = 6;
    public static final int BOARD_LIMIT_INF_X = 0;
    public static final int BOARD_LIMIT_SUP_Y = 8;
    public static final int BOARD_LIMIT_INF_Y = 0;

    // Game initiation - Board
    public static final int BOARD_TOTAL_CARDS = 25;
    public static final int BLACK_HOLES_TOTAL_CARDS = 4;
    public static final int TOTAL_BOARD_SPACES = 63;
    public static final int TOTAL_CARD_SPACES = 23;
    public static final String[] PLANET_NAMES = {"Gether", "Kiber", "Reverie", "Tiamat", "Lamarckia", "Arrakis", "Whirl", "Striterax", "Asperta"};
    public static final boolean[] isPIRATE = {false, false, false, false, false, false, true, true, true};
    public static final int[][] BOARD_POSITIONS = {
        {1, 3}, {1, 8}, {2, 2}, {2, 3}, {2, 4}, {2, 6},
        {2, 7}, {2, 8}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5},
        {3, 6}, {3, 7}, {4, 0}, {4, 1}, {4, 2}, {4, 4}, {4, 5},
        {4, 6}, {5, 0}, {5, 5}};

    public static final String[] PLANET_CUBE_COLOR_NAME = {"blue", "yellow", "red", "black"};
    public static final int[][] PLANET_CUBE_COLOR_PRICES
            = {{1, 3, 2, 3},
            {3, 1, 2, 3},
            {1, 2, 3, 3},
            {3, 2, 1, 3},
            {2, 3, 1, 3},
            {2, 1, 3, 3},
            {0, 3, 0, 0},
            {0, 0, 3, 0},
            {1, 0, 0, 0}};

    // Graphical variables
    public static int WINDOW_WIDTH = 1280;
    public static int HEIGHT = 800;
    public static int CARD_WIDTH = 80;
    public static int CARD_HEIGHT = 80;
    public static int NEXT_PHASE_BLOCK_WIDTH = 40;
    public static int NEXT_PHASE_BLOCK_HEIGHT = 40;

    //Fonts
    public static final Font FONT_12 = new Font("Verdana", Font.PLAIN, 12);
    public static final Font FONT_15 = new Font("Verdana", Font.BOLD, 15);
    public static final Font FONT_25 = new Font("Verdana", Font.BOLD, 25);

}
