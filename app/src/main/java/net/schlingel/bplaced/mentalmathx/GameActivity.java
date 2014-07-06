package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.schlingel.bplaced.mentalmathx.controller.GameController;
import net.schlingel.bplaced.mentalmathx.controller.impl.GameControllerImpl;
import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.Mode;
import net.schlingel.bplaced.mentalmathx.model.Result;
import net.schlingel.bplaced.mentalmathx.utils.LabelHelper;
import net.schlingel.bplaced.mentalmathx.view.GameView;
import net.schlingel.bplaced.mentalmathx.view.ResultsView;
import net.schlingel.bplaced.mentalmathx.view.impl.DialogResultsView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

/**
 * Created by zombie on 27.06.14.
 */
@Fullscreen
@EActivity(R.layout.game)
public class GameActivity extends FragmentActivity implements GameView, View.OnClickListener, DialogResultsView.OKListener {
    private static final int[] BUTTON_IDS = new int[] {
        R.id.btnOne,
        R.id.btnTwo,
        R.id.btnThree,
        R.id.btnFour,
        R.id.btnFive,
        R.id.btnSix,
        R.id.btnSeven,
        R.id.btnEight,
        R.id.btnNine,
        R.id.btnZero
    };

    @ViewById(R.id.txtVwExercise)
    TextView txtVwExercise;

    @ViewById(R.id.txtVwCorrect)
    TextView txtVwCorrectGuesses;

    @ViewById(R.id.txtVwWrongGuesses)
    TextView txtVwWrongGuesses;

    @ViewById(R.id.txtVwTime)
    TextView txtTime;

    private ResultsView resultsView;

    private Result lastResult;

    private GameController controller;

    @AfterViews
    public void init() {
        Mode m = (Mode)getIntent().getExtras().getSerializable(Mode.NAME);
        Difficulty d = (Difficulty)getIntent().getExtras().getSerializable(Difficulty.NAME);

        for(int btnID : BUTTON_IDS) {
            findViewById(btnID).setOnClickListener(this);
        }

        controller = new GameControllerImpl(d, m, this);
        resultsView = DialogResultsView.from(this);
    }

    public static Intent asIntent(Context sender, Difficulty difficulty, Mode mode) {
        Intent i = new Intent(sender, GameActivity_.class);
        i.putExtra(Difficulty.NAME, difficulty);
        i.putExtra(Mode.NAME, mode);

        return i;
    }

    @Override
    public void updateStats(Result status) {
        this.lastResult = status;

        final String correct = String.format("✓ × %d", status.getCorrectGuesses());
        final String wrong = String.format("✗ × %d", status.getWrongGuesses());
        final String timeLabel = LabelHelper.timeLabelFrom(status.getTime());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtVwCorrectGuesses.setText(correct);
                txtVwWrongGuesses.setText(wrong);
                txtTime.setText(timeLabel);
            }
        });
    }

    @Override
    public void updateExercise(String exerciseTerm) {
        final String termLabel = exerciseTerm;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtVwExercise.setText(termLabel);
            }
        });
    }

    @Override
    public void onCorrectGuess() {
        // TODO: add sound
    }

    @Override
    public void onWrongGuess() {
        // TODO: add sound
    }

    @Override
    public void onGameOver() {
        resultsView.show(lastResult);
    }

    @Override
    public void onClick(View view) {
        String sFigure = ((Button)view).getText().toString();
        int figure = Integer.parseInt(sFigure);

        controller.onFigureInput(figure);
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.pause();
    }

    @Override
    public void onOK(DialogFragment fragment) {
        startActivity(NewGameActivity.asIntent(this));
        finish();
    }
}
