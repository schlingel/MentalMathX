package net.schlingel.bplaced.mentalmathx.game;

import net.schlingel.bplaced.mentalmathx.game.ExerciseStrategy;

/**
 * Created by zombie on 29.06.14.
 */
public interface GameLogic {
    public void endRound();

    public ExerciseStrategy exerciseFactory();

    public void onWrongGuess();

    public boolean isGameOver();
}
