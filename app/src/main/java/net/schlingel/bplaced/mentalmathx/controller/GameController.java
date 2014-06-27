package net.schlingel.bplaced.mentalmathx.controller;

import net.schlingel.bplaced.mentalmathx.math.Term;

/**
 * Created by zombie on 27.06.14.
 *
 * Similar to the android lifecycle the game itself knows different states too.
 *         reset
 *       +------+ +------- initialize/setValues
 *       |      | |
 *       Y      | Y
 *     +-----------+
 *     |  running  |<---+
 *     +-----------+    |
 *           | pause    | resume
 *           Y          |
 *     +-----------+    |
 *     |   paused  |----+
 *     +-----------+
 */
public interface GameController {
    public void onFigureInput(int figure);

    public void onTimeChange(long timeInSec);

    public void reset();

    public void pause();

    public void resume();

    public void setValues(Term currentExercise, int correctGuesses, int wrongGuesses, long timeInSeconds);
}
