package net.schlingel.bplaced.mentalmathx.utils;

import net.schlingel.bplaced.mentalmathx.model.Score;

import java.util.Comparator;

/**
 * Created by zombie on 08.09.14.
 */
public class RegularScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score score, Score score2) {
        if(score.getTimeInSec() < score2.getTimeInSec()) {
            return -1;
        } else if(score.getTimeInSec() > score2.getTimeInSec()) {
            return 1;
        } else if(score.getWrongGuesses() < score2.getWrongGuesses()) {
            return -1;
        } else if(score.getWrongGuesses() > score2.getWrongGuesses()) {
            return 1;
        }

        return 0;
    }
}
