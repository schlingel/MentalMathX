package net.schlingel.bplaced.mentalmathx.utils;

import android.content.Context;

import net.schlingel.bplaced.mentalmathx.R;
import net.schlingel.bplaced.mentalmathx.game.Mode;

/**
 * Created by zombie on 06.07.14.
 */
public class LabelHelper {
    public static String timeLabelFrom(long timeInSec) {
        long seconds = timeInSec % 60;
        long minutes = (timeInSec / 60) % 60;
        long hours = timeInSec / 3600;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String summaryLabelFor(Mode mode, Context context) {
        String format = context.getString(R.string.txtVwSummary);

        switch (mode) {
            case HoundredRounds:
                return String.format(format, context.getString(R.string.btnHoundredRoundes));
            case TenRounds:
                return String.format(format, context.getString(R.string.btnTenRoundes));
            case Marathon:
                return String.format(format, context.getString(R.string.btnMarathon));
            default:
                throw new IllegalArgumentException("mode must not be null!");
        }
    }
}
