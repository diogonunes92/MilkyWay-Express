package milkyway_logic.gameplanner;

/**
 *
 * @author marcobarbosa
 */
public final class Game {
    
    private static int totalCoins;
    private static int myCoins;

    public Game() {
        
        totalCoins = 30;
        myCoins = 0;
    }
    
    public static void newGame(){
        totalCoins = 20;
        myCoins = 10;
    }

    public static int getTotalCoins() {
        return totalCoins;
    }

    public static void setTotalCoins(int totalCoins) {
        Game.totalCoins = totalCoins;
    }

    public static int getMyCoins() {
        return myCoins;
    }

    public static void setMyCoins(int myCoins) {
        Game.myCoins = myCoins;
    }
}
