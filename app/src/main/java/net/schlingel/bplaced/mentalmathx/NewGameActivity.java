package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import net.schlingel.bplaced.mentalmathx.game.Mode;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;


@Fullscreen
@EActivity(R.layout.activity_new_game)
public class NewGameActivity extends ActionBarActivity {
    @Click(R.id.btnTenRoundes)
    public void startTenRoundesGame() {
        startActivity(SelectDifficultyActivity.asIntent(this, Mode.TenRounds));
    }

    @Click(R.id.btnHoundredRoundes)
    public void startHoundredRoundesGame() {
        startActivity(SelectDifficultyActivity.asIntent(this, Mode.HoundredRounds));
    }

    @Click(R.id.btnMarathon)
    public void startMarathonGame() {
        startActivity(SelectDifficultyActivity.asIntent(this, Mode.Marathon));
    }

    @Click(R.id.btnHighscore)
    public void showHighscores() {
        startActivity(HighscoresActivity.asIntent(this));
    }

    public static Intent asIntent(Context sender) {
        Intent i = new Intent(sender, NewGameActivity_.class);
        return i;
    }
}
