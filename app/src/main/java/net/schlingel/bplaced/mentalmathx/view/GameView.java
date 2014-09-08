package net.schlingel.bplaced.mentalmathx.view;

import net.schlingel.bplaced.mentalmathx.model.Result;

/**
 * Created by zombie on 27.06.14.
 */
public interface GameView {
    public void updateStats(Result status);

    public void updateExercise(String exerciseTerm);

    public void updateDisplayMode(DisplayMode mode);

    public void onCorrectGuess();

    public void onWrongGuess();

    public void onGameOver();

    public void updateInput(String inputString);
}
