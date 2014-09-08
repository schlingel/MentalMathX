package net.schlingel.bplaced.mentalmathx.utils;

import net.schlingel.bplaced.mentalmathx.model.Score;

import java.util.Comparator;

/**
 * Created by zombie on 08.09.14.
 */
public class MarathonScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score score, Score score2) {
        if(score.getCorrectGuesses() > score2.getCorrectGuesses()) {
            return -1;
        } else if(score.getCorrectGuesses() < score2.getCorrectGuesses()) {
            return 1;
        }

        return 0;
    }
}
