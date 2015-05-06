package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

public class Buy extends State{

    public Buy(Game game) {
        super(game);
    }

    @Override
    public State constructGame() {
        return this;
    }

    @Override
    public State move() {
        return this;
    }

    @Override
    public State upgrade() {
        return this;
    }

    @Override
    public State buyCargo(String carga) {
        if(getGame().getTotalCoins() == 0)
            return new StartGame(getGame());
        else
            return this;
    }

    @Override
    public State sellCargo(String carga) {
        return this;
    }

    @Override
    public State isFinished() {
        return this;
    }

    @Override
    public State pirateAtack() {
        return this;
    }

    @Override
    public State turnCards() {
        return this;
    }
    
}
