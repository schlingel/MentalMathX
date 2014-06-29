package net.schlingel.bplaced.mentalmathx.game;

import net.schlingel.bplaced.mentalmathx.math.Calculations;
import net.schlingel.bplaced.mentalmathx.math.Term;

/**
 * Created by zombie on 29.06.14.
 */
public class MediumExerciseStrategy implements ExerciseStrategy {
    private final Calculations problemGenerator;

    public MediumExerciseStrategy() {
        this.problemGenerator = new Calculations(Difficulty.Medium);
    }

    @Override
    public Term nextProblem(int round) {
        Term problem = problemGenerator.getProblem();
        int hardenSteps = round / 10;

        if(round > 5 && round < 10) {
            problemGenerator.hardenProblem(problem);
        }

        for(int i = 0; i < hardenSteps; i++) {
            problemGenerator.hardenProblem(problem);
        }

        return problem;
    }
}
