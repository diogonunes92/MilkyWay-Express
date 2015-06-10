package milkyway_logic.states;

import java.awt.Color;
import java.io.Serializable;

public interface States extends Serializable {

    public States constructGame();
    
    public States move(int x, int y);
    public States replishMarkets();
    public States explore();
    public States pirateAttack();
    
    public States upgradeWeapon();
    public States upgradeCargo();
    public States buyCargo(String carga);
    public States sellCargo(Color carga);
    public States isFinished();
    public States nextState();
    
    public States newGame();

    public States moveWormhole(int move_x, int move_y);

}
