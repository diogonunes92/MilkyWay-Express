package milkyway_logic.states;

import java.io.Serializable;

public interface States extends Serializable {

    public States constructGame();

    public States move(String move);

    public States upgradeWeapon();

    public States upgradeCargo();

    public States buyCargo(String carga);

    public States sellCargo(String carga);

    public States isFinished();

    public States nextState();

}
