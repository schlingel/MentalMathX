package net.schlingel.bplaced.mentalmathx.view.impl;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.TextView;

import net.schlingel.bplaced.mentalmathx.R;
import net.schlingel.bplaced.mentalmathx.game.Mode;
import net.schlingel.bplaced.mentalmathx.model.Score;
import net.schlingel.bplaced.mentalmathx.model.adapters.ScoreAdapter;
import net.schlingel.bplaced.mentalmathx.view.HighscoresSubView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by zombie on 22.07.14.
 */
@EFragment(R.layout.fragment_highscores)
public class FragmentHighscoresSubView extends Fragment implements HighscoresSubView {
    private Mode mode;

    @ViewById(R.id.txtVwTitle)
    TextView txtVwTitle;

    @ViewById(R.id.lvScores)
    ListView lvScores;

    private ScoreAdapter scoreAdapter;
    private Score[] scores;

    @AfterViews
    public void init() {
        scoreAdapter = new ScoreAdapter(scores);
        lvScores.setAdapter(scoreAdapter);
        scoreAdapter.notifyDataSetChanged();
    }

    @Override
    public void show(Score[] scores) {
        if(scoreAdapter != null) {
            scoreAdapter.setItems(scores);
            scoreAdapter.notifyDataSetChanged();
        }

        this.scores = scores;
    }


    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
