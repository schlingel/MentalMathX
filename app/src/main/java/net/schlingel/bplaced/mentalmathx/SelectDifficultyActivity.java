package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.Mode;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * Created by zombie on 27.06.14.
 */
@EActivity(R.layout.select_difficulty)
public class SelectDifficultyActivity extends ActionBarActivity {
    private Mode mode;

    @AfterViews
    public void init() {
        Object o = getIntent().getExtras().getSerializable(Mode.NAME);

        if(o == null) {
            throw new IllegalStateException("Should have mode argument!");
        }

        this.mode = (Mode)o;
    }

    @Click(R.id.btnVeryEasy)
    public void startVeryEasyGame() {
        startGame(Difficulty.VeryEasy);
    }

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
        startActivity(GameActivity.asIntent(this, diff, mode));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(NewGameActivity.asIntent(this));
    }

    public static Intent asIntent(Context sender, Mode mode) {
        Intent i = new Intent(sender, SelectDifficultyActivity_.class);
        i.putExtra(Mode.NAME, mode);
        return i;
    }
}
