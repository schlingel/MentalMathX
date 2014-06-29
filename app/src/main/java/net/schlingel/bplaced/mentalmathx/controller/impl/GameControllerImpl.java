package net.schlingel.bplaced.mentalmathx.controller.impl;

import net.schlingel.bplaced.mentalmathx.controller.GameController;
import net.schlingel.bplaced.mentalmathx.game.GameLogic;
import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.ExerciseStrategy;
import net.schlingel.bplaced.mentalmathx.game.HoundredRoundsGameLogic;
import net.schlingel.bplaced.mentalmathx.game.InfiniteGameLogic;
import net.schlingel.bplaced.mentalmathx.game.Mode;
import net.schlingel.bplaced.mentalmathx.game.TenExercisesGameLogic;
import net.schlingel.bplaced.mentalmathx.game.TenRoundsGameLogic;
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
    private ExerciseStrategy exerciseFactory;
    private Term currentExercise;
    private String currentInput;
    private GameView view;
    private Ticker ticker;
    private long timeInSeconds;
    private GameLogic gameLogic;

    public GameControllerImpl(Difficulty difficulty, Mode mode, GameView view) {
        this.gameLogic = createGameLogic(difficulty, mode);
        this.exerciseFactory = gameLogic.exerciseFactory();
        this.view = view;

        reset();
        updateView();
    }

    private GameLogic createGameLogic(Difficulty difficulty, Mode mode) {
        switch (mode) {
            case HoundredRounds:
                return new HoundredRoundsGameLogic(difficulty);
            case TenRounds:
                return new TenRoundsGameLogic(difficulty);
            case Marathon:
                return new InfiniteGameLogic(difficulty);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void onFigureInput(int figure) {
        String result = Integer.toString(currentExercise.value());
        currentInput = currentInput + Integer.toString(figure);

        if(result.startsWith(currentInput)) {
            if(result.length() == currentInput.length()) {
                correctGuesses++;
                currentInput = "";

                gameLogic.endRound();
                currentExercise = exerciseFactory.nextProblem(correctGuesses);
            }
        } else {
            wrongGuesses++;
            gameLogic.onWrongGuess();
            currentInput = "";
            updateView();
        }

        if(gameLogic.isGameOver()) {
            ticker.shutdown();
            view.onGameOver();
        } else {
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
        currentExercise = exerciseFactory.nextProblem(correctGuesses);
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
    }
}
