package net.schlingel.bplaced.mentalmathx.view;

/**
 * Created by zombie on 27.06.14.
 */
public interface GameView {
    public void updateStats(long runningTimeInSec, int correctCalcs, int wrongGuesses);

    public void updateExercise(String exerciseTerm);

    public void onCorrectGuess();

    public void onWrongGuess();

    public void onGameOver();
}
