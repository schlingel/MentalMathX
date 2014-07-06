package net.schlingel.bplaced.mentalmathx.game.logic;

import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.strategy.ExerciseStrategy;
import net.schlingel.bplaced.mentalmathx.game.strategy.ExerciseStrategyFactory;

/**
 * Created by zombie on 29.06.14.
 */
public class InfiniteGameLogic implements GameLogic {
    private ExerciseStrategy strategy;
    private boolean isGameOver;

    public InfiniteGameLogic(Difficulty difficulty) {
        this.strategy = ExerciseStrategyFactory.getInstance(difficulty);
        this.isGameOver = false;
    }

    @Override
    public void endRound() {
    }

    @Override
    public ExerciseStrategy exerciseFactory() {
        return strategy;
    }

    @Override
    public void onWrongGuess() {
        this.isGameOver = true;
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }
}
