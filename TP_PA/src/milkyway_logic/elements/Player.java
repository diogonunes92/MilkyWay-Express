package milkyway_logic.elements;

public class Player {

    private final int id;
    private int coins;
    private String name;
    private Spaceship spaceship;

    public Player(int id, String name, int coins, Spaceship spaceShip) {
        this.id = id;
        this.name = name;
        this.coins = coins;
        this.spaceship = spaceShip;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = this.coins + coins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

}
