package net.schlingel.bplaced.mentalmathx.game.strategy;

import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.math.Calculations;
import net.schlingel.bplaced.mentalmathx.math.Term;

/**
 * Created by zombie on 29.06.14.
 */
public class EasyExerciseStrategy implements ExerciseStrategy {
    private final Calculations problemGenerator;

    public EasyExerciseStrategy() {
        problemGenerator = new Calculations(Difficulty.Easy);
    }

    @Override
    public Term nextProblem(int round) {
        Term problem = problemGenerator.getProblem();
        int hardenSteps = round / 10;

        for(int i = 0; i < hardenSteps; i++) {
            problemGenerator.hardenProblem(problem);
        }

        return problem;
    }
}
