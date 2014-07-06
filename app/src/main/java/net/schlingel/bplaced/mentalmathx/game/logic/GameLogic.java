package net.schlingel.bplaced.mentalmathx.game.logic;

import net.schlingel.bplaced.mentalmathx.game.strategy.ExerciseStrategy;

/**
 * Created by zombie on 29.06.14.
 */
public interface GameLogic {
    public void endRound();

    public ExerciseStrategy exerciseFactory();

    public void onWrongGuess();

    public boolean isGameOver();
}
