package net.schlingel.bplaced.mentalmathx.model;

import net.schlingel.bplaced.mentalmathx.game.Mode;

import java.io.Serializable;

/**
 * Created by zombie on 06.07.14.
 */
public class Result implements Serializable {
    private int rounds;
    private long time;
    private Mode mode;
    private int wrongGuesses;
    private int correctGuesses;

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }
}
