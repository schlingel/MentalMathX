package net.schlingel.bplaced.mentalmathx.game;

/**
 * Created by zombie on 29.06.14.
 */
public class HoundredRoundsGameLogic extends FiniteGameLogic {
    public HoundredRoundsGameLogic(Difficulty difficulty) {
        super(difficulty);
    }

    @Override
    protected int getMaxRound() {
        return 100;
    }
}
