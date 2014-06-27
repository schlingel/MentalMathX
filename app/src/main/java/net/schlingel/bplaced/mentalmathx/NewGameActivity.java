package net.schlingel.bplaced.mentalmathx;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;


@Fullscreen
@EActivity(R.layout.activity_new_game)
public class NewGameActivity extends ActionBarActivity {
    @Click(R.id.btnNewGame)
    public void startNewGame() {
        startActivity(SelectDifficultyActivity.asIntent(this));
    }

    @Click(R.id.btnHighscore)
    public void showHighscores() {
        Toast.makeText(this, "To implement", Toast.LENGTH_SHORT).show();
    }
}
