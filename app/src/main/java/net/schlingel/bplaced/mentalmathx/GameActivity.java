package net.schlingel.bplaced.mentalmathx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import net.schlingel.bplaced.metalmathx.game.Difficulty;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * Created by zombie on 27.06.14.
 */
@Fullscreen
@EActivity(R.layout.game)
public class GameActivity extends Activity {
    public static Intent asIntent(Context sender, Difficulty difficulty) {
        Intent i = new Intent(sender, GameActivity_.class);
        i.putExtra(Difficulty.NAME, difficulty);

        return i;
    }
}
