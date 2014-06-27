package net.schlingel.bplaced.mentalmathx.controller.impl;

import net.schlingel.bplaced.mentalmathx.controller.GameController;
import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.net.schlingel.bplaced.mentalmathx.math.Calculations;
import net.schlingel.bplaced.mentalmathx.net.schlingel.bplaced.mentalmathx.math.Term;
import net.schlingel.bplaced.mentalmathx.view.GameView;

/**
 * Created by zombie on 27.06.14.
 */
public class GameControllerImpl implements GameController {
    private int correctGuesses;
    private int wrongGuesses;
    private Calculations exerciseGenerator;
    private Term currentExercise;
    private String currentInput;
    private GameView view;

    public GameControllerImpl(Difficulty difficulty, GameView view) {
        this.exerciseGenerator = new Calculations(difficulty);
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
                currentExercise = exerciseGenerator.getProblem();
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
    }

    private void updateView() {
        view.updateExercise(currentExercise.toString());
        view.updateStats(0, correctGuesses, wrongGuesses);
    }

    @Override
    public void reset() {
        correctGuesses = 0;
        wrongGuesses = 0;
        currentExercise = exerciseGenerator.getProblem();
        currentInput = "";
    }

    @Override
    public void pause() {
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
