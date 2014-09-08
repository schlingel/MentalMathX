package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import net.schlingel.bplaced.mentalmathx.game.Mode;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_new_game)
public class NewGameActivity extends ActionBarActivity {
    @Click(R.id.btnTenRoundes)
    public void startTenRoundesGame() {
        startGameActivity(SelectDifficultyActivity.asIntent(this, Mode.TenRounds));
    }

    @Click(R.id.btnHoundredRoundes)
    public void startHoundredRoundesGame() {
        startGameActivity(SelectDifficultyActivity.asIntent(this, Mode.HoundredRounds));
    }

    @Click(R.id.btnMarathon)
    public void startMarathonGame() {
        startGameActivity(SelectDifficultyActivity.asIntent(this, Mode.Marathon));
    }

    @Click(R.id.btnHighscore)
    public void showHighscores() {
        startActivity(HighscoresActivity.asIntent(this));
    }

    @Click(R.id.btnAbout)
    public void showAbout() {
        startActivity(AboutActivity.asIntent(this));
    }

    private void startGameActivity(Intent i) {
        startActivity(i);
        finish();
    }

    public static Intent asIntent(Context sender) {
        Intent i = new Intent(sender, NewGameActivity_.class);
        return i;
    }
}
