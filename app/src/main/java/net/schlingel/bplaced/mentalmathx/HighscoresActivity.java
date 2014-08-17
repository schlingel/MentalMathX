package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;

import net.schlingel.bplaced.mentalmathx.controller.HighscoreController;
import net.schlingel.bplaced.mentalmathx.controller.impl.HighscoreControllerImpl;
import net.schlingel.bplaced.mentalmathx.game.Mode;
import net.schlingel.bplaced.mentalmathx.model.Score;
import net.schlingel.bplaced.mentalmathx.view.HighscoresView;
import net.schlingel.bplaced.mentalmathx.view.impl.FragmentHighscoresSubView_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_highscores)
public class HighscoresActivity extends ActionBarActivity implements HighscoresView {
    private static final String TAG = HighscoresActivity.class.getSimpleName();
    private static final Mode[] VISIBLE_SCORES = new Mode[] { Mode.TenRounds, Mode.HoundredRounds, Mode.Marathon };
    private HighscoreController controller;

    private static class ScorePagesAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
        private FragmentHighscoresSubView_[] highscoresSubViewse;
        private Score[] scores;

        public ScorePagesAdapter(FragmentManager fm, Score[] scores) {
            super(fm);

            if(scores == null) {
                throw new IllegalArgumentException("Scores mut not be null!");
            }

            this.scores = scores;
            this.highscoresSubViewse = new FragmentHighscoresSubView_[VISIBLE_SCORES.length];
        }

        @Override
        public Fragment getItem(int position) {
            if(highscoresSubViewse[position] == null) {
                highscoresSubViewse[position] = new FragmentHighscoresSubView_();
                highscoresSubViewse[position].setMode(VISIBLE_SCORES[position]);
                highscoresSubViewse[position].show(filterScoresForMode(scores, VISIBLE_SCORES[position]));
            }

            return highscoresSubViewse[position];
        }

        @Override
        public int getCount() {
            return VISIBLE_SCORES.length;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    @ViewById(R.id.vwPgrHighscores)
    ViewPager vwPgrHighscres;

    @ViewById(R.id.rlWaiting)
    RelativeLayout rlWaiting;

    @AfterViews
    public void init() {
        controller = new HighscoreControllerImpl(this, this);
        controller.fetchScores();
    }

    @Override
    public void show(Score[] scores) {
        if(scores == null) {
            return;
        }

        rlWaiting.setVisibility(View.GONE);
        vwPgrHighscres.setVisibility(View.VISIBLE);

        ScorePagesAdapter adapter = new ScorePagesAdapter(getSupportFragmentManager(), scores);
        vwPgrHighscres.setAdapter(adapter);
        vwPgrHighscres.setCurrentItem(0);

        adapter.notifyDataSetChanged();
    }

    private static Score[] filterScoresForMode(Score[] allScores, Mode mode) {
        ArrayList<Score> scores = new ArrayList<Score>();

        for(Score s : allScores) {
            if(s.getGameType() == mode) {
                scores.add(s);
            }
        }

        return scores.toArray(new Score[0]);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public static Intent asIntent(Context sender) {
        Intent i = new Intent(sender, HighscoresActivity_.class);
        return i;
    }
}
