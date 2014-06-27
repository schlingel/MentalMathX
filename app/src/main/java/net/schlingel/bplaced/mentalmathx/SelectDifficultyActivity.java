package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import net.schlingel.bplaced.metalmathx.game.Difficulty;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * Created by zombie on 27.06.14.
 */
@Fullscreen
@EActivity(R.layout.select_difficulty)
public class SelectDifficultyActivity extends ActionBarActivity {
    @Click(R.id.btnHard)
    public void startHardGame() {
        startGame(Difficulty.Medium);
    }

    @Click(R.id.btnMedium)
    public void startMediumGame() {
        startGame(Difficulty.Medium);
    }

    @Click(R.id.btnEasy)
    public void startEasyGame() {
        startGame(Difficulty.Easy);
    }

    private void startGame(Difficulty diff) {
        startActivity(GameActivity.asIntent(this, diff));
    }

    public static Intent asIntent(Context sender) {
        Intent i = new Intent(sender, SelectDifficultyActivity_.class);
        return i;
    }
}
