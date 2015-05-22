package milkyway_logic.states;

import milkyway_logic.elements.Player;
import milkyway_logic.elements.Spaceship;
import milkyway_logic.gameplanner.BoardConstructor;
import milkyway_logic.gameplanner.Game;
import util.Constants;

public class StartGame extends StatesAdapter {

    public StartGame(Game game) {
        super(game);
    }

    @Override
    public StatesAdapter constructGame() {
        System.out.println("Estou no constructgame");
        BoardConstructor mBoardConstructor = new BoardConstructor();
        getGame().setBoard(mBoardConstructor.getGameBoard());
        getGame().setBankCoins(Constants.INITIAL_PLAYER_COINS - Constants.INITIAL_PLAYER_COINS);

        getGame().setPlayer(new Player(1, "player_1", Constants.INITIAL_PLAYER_COINS, new Spaceship(Constants.INITIAL_SPACESHIP_POSITION_X, Constants.INITIAL_SPACESHIP_POSITION_Y)));

        return new Move(getGame());
    }
    
    @Override
    public StatesAdapter isFinished() {
        if (getGame().getPlayer().getCoins() == 0) {
            System.exit(0);
        }
        return this;
    }

    @Override
    public StatesAdapter nextState() {
        return new Move(getGame());
    }
}
