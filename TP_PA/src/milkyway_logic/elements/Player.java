package milkyway_logic.elements;

public class Player {
    
    private final int id;
    private int coins;
    private String name;

    public Player(int id, int coins, String name) {
        this.id = id;
        this.coins = coins;
        this.name = name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
}
