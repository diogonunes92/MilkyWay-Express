package milkyway_logic.elements;

import java.io.Serializable;

public class Player implements Serializable{

    private final int id;
    private int coins;
    private String name;
    private Spaceship spaceship;
    private boolean isAttacked;
    private int pirateDamage;
    private boolean isWinner;
    private boolean isLooser;
    private boolean cargoSeized;

    public Player(int id, String name, int coins, Spaceship spaceShip) {
        this.id = id;
        this.name = name;
        this.coins = 100; 
        this.spaceship = spaceShip;
        this.pirateDamage = 0;
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

    public boolean isIsAttacked() {
        return isAttacked;
    }

    public void setIsAttacked(boolean isAttacked) {
        this.isAttacked = isAttacked;
    }

    public int getPirateDamage() {
        return pirateDamage;
    }

    public void setPirateDamage(int pirateDamage) {
        this.pirateDamage = pirateDamage;
    }

    public boolean isIsWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public boolean isIsLooser() {
        return isLooser;
    }

    public void setIsLooser(boolean isLooser) {
        this.isLooser = isLooser;
    }

    public boolean isCargoSeized() {
        return cargoSeized;
    }

    public void setCargoSeized(boolean cargoSeized) {
        this.cargoSeized = cargoSeized;
    }
    
    
}
