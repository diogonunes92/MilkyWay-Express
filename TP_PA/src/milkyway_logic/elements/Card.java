package milkyway_logic.elements;

/**
 *
 * @author marcobarbosa
 */
public abstract class Card{
    private boolean isTurned;

    public Card(boolean isTurned) {
        this.isTurned = isTurned;
    }
    
    public boolean isIsTurned() {
        return isTurned;
    }

    public void setIsTurned(boolean isTurned) {
        this.isTurned = isTurned;
    }
    

}
