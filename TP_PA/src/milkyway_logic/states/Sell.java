package milkyway_logic.states;

import milkyway_logic.gameplanner.Game;

public class Sell extends State{

    public Sell(Game game) {
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
    public State buyCargo(String carga) {
        return this;
    }

    @Override
    public State sellCargo(String carga) {
        if(getGame().getmSpaceship().getCargo().isEmpty()){
            return new Buy(getGame());
        }
        else{
            return this;
        }
    }

    @Override
    public State upgrade() {
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
