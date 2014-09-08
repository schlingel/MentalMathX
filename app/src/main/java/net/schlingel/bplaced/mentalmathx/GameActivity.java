package net.schlingel.bplaced.mentalmathx;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.schlingel.bplaced.mentalmathx.controller.GameController;
import net.schlingel.bplaced.mentalmathx.controller.impl.GameControllerImpl;
import net.schlingel.bplaced.mentalmathx.game.Difficulty;
import net.schlingel.bplaced.mentalmathx.game.Mode;
import net.schlingel.bplaced.mentalmathx.model.Result;
import net.schlingel.bplaced.mentalmathx.model.Score;
import net.schlingel.bplaced.mentalmathx.utils.DatabaseHelper;
import net.schlingel.bplaced.mentalmathx.utils.DelayedTask;
import net.schlingel.bplaced.mentalmathx.utils.LabelHelper;
import net.schlingel.bplaced.mentalmathx.view.DisplayMode;
import net.schlingel.bplaced.mentalmathx.view.GameView;
import net.schlingel.bplaced.mentalmathx.view.ResultsView;
import net.schlingel.bplaced.mentalmathx.view.impl.DialogResultsView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

/**
 * Created by zombie on 27.06.14.
 */
@EActivity(R.layout.game)
public class GameActivity extends FragmentActivity implements GameView, View.OnClickListener, DialogResultsView.OKListener, DelayedTask.OnContinueCallback {
    private static final long DISPLAY_DELAY_IN_MILLIS = 75L;

    private static class InsertScoreTasks extends AsyncTask<Score, Void, Void> {
        private Context context;

        public InsertScoreTasks(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Score... scores) {
            Score scoreToInsert = scores[0];

            try {
                DatabaseHelper helper = new DatabaseHelper(context);
                helper.persist(scoreToInsert);
                Log.i(GameActivity.class.getSimpleName(), "Inserted new score");
            } catch (SQLException e) {
                Log.e(GameActivity.class.getSimpleName(), "SQL Exception occurred! Couldn't persist score!");
            }

            return null;
        }

        public static InsertScoreTasks from(Context context) {
            return new InsertScoreTasks(context);
        }
    }

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

    @ViewById(R.id.txtVwInput)
    TextView txtInput;

    private ResultsView resultsView;

    private Result lastResult;

    private GameController controller;

    private Mode currentMode;

    @AfterViews
    public void init() {
        currentMode = (Mode)getIntent().getExtras().getSerializable(Mode.NAME);
        Difficulty d = (Difficulty)getIntent().getExtras().getSerializable(Difficulty.NAME);

        for(int btnID : BUTTON_IDS) {
            findViewById(btnID).setOnClickListener(this);
        }

        controller = new GameControllerImpl(d, currentMode, this);
        resultsView = DialogResultsView.from(this);
    }

    public static Intent asIntent(Context sender, Difficulty difficulty, Mode mode) {
        Intent i = new Intent(sender, GameActivity_.class);
        i.putExtra(Difficulty.NAME, difficulty);
        i.putExtra(Mode.NAME, mode);

        return i;
    }

    @Override
    public void updateDisplayMode(DisplayMode mode) {
        switch (mode) {
            case Error:
                txtInput.setBackgroundColor(getResources().getColor(R.color.ErrorBg));
                txtInput.setTextColor(getResources().getColor(R.color.ErrorFront));
                break;
            case Correct:
                txtInput.setBackgroundColor(getResources().getColor(R.color.CorrectBg));
                txtInput.setTextColor(getResources().getColor(R.color.CorrectFront));
                break;
            case Default:
                txtInput.setBackgroundColor(getResources().getColor(R.color.VeryLight));
                txtInput.setTextColor(getResources().getColor(R.color.Dark));
                break;
            default:
                throw new IllegalArgumentException("Displaymode must not be null!");
        }
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
        updateDisplayMode(DisplayMode.Correct);
        resetDisplayMode(DISPLAY_DELAY_IN_MILLIS);
    }

    @Override
    public void onWrongGuess() {
        updateDisplayMode(DisplayMode.Error);
        resetDisplayMode(DISPLAY_DELAY_IN_MILLIS);
    }

    private void resetDisplayMode(long waitPeriod) {
        DelayedTask task = new DelayedTask(waitPeriod, this);
        task.execute();
    }

    @Override
    public void doAction() {
        txtInput.post(new Runnable() {
            @Override
            public void run() {
                updateDisplayMode(DisplayMode.Default);
            }
        });
    }

    @Override
    public void onGameOver() {
        InsertScoreTasks.from(this).execute(Score.from(lastResult, currentMode));
        resultsView.show(lastResult);
    }

    @Override
    public void updateInput(final String inputString) {
        txtInput.post(new Runnable() {
            @Override
            public void run() {
                txtInput.setText(inputString);
            }
        });
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
    protected void onResume() {
        super.onResume();
        controller.resume();
    }

    @Override
    public void onOK(DialogFragment fragment) {
        startActivity(NewGameActivity.asIntent(this));
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(NewGameActivity.asIntent(this));
    }
}
