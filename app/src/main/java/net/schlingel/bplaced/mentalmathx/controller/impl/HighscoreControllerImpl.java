package net.schlingel.bplaced.mentalmathx.controller.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import net.schlingel.bplaced.mentalmathx.controller.HighscoreController;
import net.schlingel.bplaced.mentalmathx.model.Score;
import net.schlingel.bplaced.mentalmathx.utils.DatabaseHelper;
import net.schlingel.bplaced.mentalmathx.view.HighscoresView;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zombie on 01.08.14.
 */
public class HighscoreControllerImpl implements HighscoreController {
    public static class QueryTask extends AsyncTask<Void, Void, List<Score>> {
        private Context context;
        private HighscoresView highscoresView;

        public QueryTask(Context context, HighscoresView highscoresView) {
            this.context = context;
            this.highscoresView = highscoresView;
        }

        @Override
        protected List<Score> doInBackground(Void... voids) {
            Log.i(HighscoreControllerImpl.TAG, "Starting DB fetch");

            try {
                DatabaseHelper helper = new DatabaseHelper(context);
                List<Score> scores = helper.fetchAll();

                return scores;
            } catch(SQLException e) {
                Log.e(HighscoreControllerImpl.TAG, "SQL exception occurred! Fallback to error view");
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Score> scores) {
            Log.i(HighscoreControllerImpl.TAG, String.format("Got %d entries in scores database", scores.size()));

            highscoresView.show(scores.toArray(new Score[0]));
        }

        public static QueryTask from(Context context, HighscoresView view) {
            return new QueryTask(context, view);
        }
    }

    private static final String TAG = HighscoreControllerImpl.class.getSimpleName();
    private final HighscoresView view;
    private final Context context;

    public HighscoreControllerImpl(Context context, HighscoresView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void fetchScores() {
        QueryTask.from(context, view).execute();
    }
}
