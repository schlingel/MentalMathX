package net.schlingel.bplaced.mentalmathx.game;

/**
 * Created by zombie on 29.06.14.
 */
public class ExerciseStrategyFactory {
    public static ExerciseStrategy getInstance(Difficulty difficulty) {
        if(difficulty == null) {
            throw new IllegalArgumentException("Difficulty must not be null!");
        }

        ExerciseStrategy strategy = null;

        switch (difficulty) {
            case Easy:
                strategy = new EasyExerciseStrategy();
                break;
            case Medium:
                strategy = new MediumExerciseStrategy();
                break;
            case Hard:
                strategy = new HardExerciseStrategy();
                break;
            default:
                throw new IllegalStateException("Got difficulty instace which was neither Easy, Medium nor Hard!");
        }

        return strategy;
    }
}
