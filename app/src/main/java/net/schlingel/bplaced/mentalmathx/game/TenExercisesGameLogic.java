package net.schlingel.bplaced.mentalmathx.game;

/**
 * Created by zombie on 29.06.14.
 */
public class TenExercisesGameLogic extends FiniteGameLogic {
    public TenExercisesGameLogic(Difficulty difficulty) {
        super(difficulty);
    }

    @Override
    protected int getMaxRound() {
        return 10;
    }
}
