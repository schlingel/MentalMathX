package net.schlingel.bplaced.mentalmathx.game.logic;

import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.strategy.ExerciseStrategy;
import net.schlingel.bplaced.mentalmathx.game.strategy.ExerciseStrategyFactory;

/**
 * Created by zombie on 29.06.14.
 */
public abstract class FiniteGameLogic implements GameLogic {
    private ExerciseStrategy strategy;
    private boolean isGameOver;
    private int playedRounds;

    public FiniteGameLogic(Difficulty difficulty) {
        this.strategy = ExerciseStrategyFactory.getInstance(difficulty);
        this.isGameOver = false;
        this.playedRounds = 0;
    }

    @Override
    public void endRound() {
        playedRounds++;

        if(playedRounds >= getMaxRound()) {
            isGameOver = true;
        }
    }

    @Override
    public ExerciseStrategy exerciseFactory() {
        return strategy;
    }

    @Override
    public void onWrongGuess() {
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    protected abstract int getMaxRound();
}
