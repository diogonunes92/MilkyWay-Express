package milkyway_logic.elements;

import java.io.Serializable;

public class Player implements Serializable{

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
        this.coins = coins;
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
