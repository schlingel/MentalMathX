package net.schlingel.bplaced.mentalmathx.controller.impl;

import net.schlingel.bplaced.mentalmathx.controller.GameController;
import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.ExerciseStrategy;
import net.schlingel.bplaced.mentalmathx.game.ExerciseStrategyFactory;
import net.schlingel.bplaced.mentalmathx.math.Calculations;
import net.schlingel.bplaced.mentalmathx.math.Term;
import net.schlingel.bplaced.mentalmathx.view.GameView;

/**
 * Created by zombie on 27.06.14.
 */
public class GameControllerImpl implements GameController {
    private static class Ticker implements Runnable {
        private GameController ctrl;
        private long seconds;
        private transient boolean isAlive;

        public Ticker(GameController ctrl) {
            this.ctrl = ctrl;
            this.seconds = 0;
            this.isAlive = true;
        }

        public void shutdown() {
            isAlive = false;
        }

        public void setSeconds(long seconds)  {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            while (isAlive) {
                try {
                    Thread thread = Thread.currentThread();

                    synchronized (thread) {
                        Thread.currentThread().wait(1000L);
                        seconds++;

                        ctrl.onTimeChange(seconds);
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private int correctGuesses;
    private int wrongGuesses;
    private ExerciseStrategy strategy;
    private Term currentExercise;
    private String currentInput;
    private GameView view;
    private Ticker ticker;
    private long timeInSeconds;

    public GameControllerImpl(Difficulty difficulty, GameView view) {
        this.strategy = ExerciseStrategyFactory.getInstance(difficulty);
        this.view = view;

        reset();
        updateView();
    }

    @Override
    public void onFigureInput(int figure) {
        String result = Integer.toString(currentExercise.value());
        currentInput = currentInput + Integer.toString(figure);

        if(result.startsWith(currentInput)) {
            if(result.length() == currentInput.length()) {
                correctGuesses++;
                currentInput = "";
                currentExercise = strategy.nextProblem(correctGuesses);
                updateView();
            }
        } else {
            wrongGuesses++;
            currentInput = "";
            updateView();
        }
    }

    @Override
    public void onTimeChange(long timeInSec) {
        this.timeInSeconds = timeInSec;
        updateView();
    }

    private void updateView() {
        view.updateExercise(currentExercise.toString());
        view.updateStats(timeInSeconds, correctGuesses, wrongGuesses);
    }

    @Override
    public void reset() {
        correctGuesses = 0;
        wrongGuesses = 0;
        currentExercise = strategy.nextProblem(correctGuesses);
        currentInput = "";
        timeInSeconds = 0;

        if(ticker != null) {
            ticker.shutdown();
        }

        ticker = new Ticker(this);
        new Thread(ticker).start();
    }

    @Override
    public void pause() {
        ticker.shutdown();
    }

    @Override
    public void resume() {

    }

    @Override
    public void setValues(Term currentExercise, int correctGuesses, int wrongGuesses, long timeInSeconds) {
        this.currentExercise = currentExercise;
        this.correctGuesses = correctGuesses;
        this.wrongGuesses = wrongGuesses;

        this.currentInput = "";

        // TODO: add time change
    }
}
