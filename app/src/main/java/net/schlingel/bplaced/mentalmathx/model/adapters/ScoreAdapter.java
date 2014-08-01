package net.schlingel.bplaced.mentalmathx.model.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.schlingel.bplaced.mentalmathx.model.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zombie on 22.07.14.
 */
public class ScoreAdapter extends BaseAdapter {
    private final List<Score> scores;

    public ScoreAdapter(Collection<Score> scores) {
        this.scores = new ArrayList<Score>();
        this.scores.addAll(scores);
    }

    public ScoreAdapter(Score[] scores) {
        this(Arrays.asList(scores));
    }

    public ScoreAdapter() {
        this(new Score[0]);
    }

    public void setItems(Score[] scores) {
        this.scores.clear();
        this.scores.addAll(Arrays.asList(scores));
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public Object getItem(int i) {
        return scores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        return null;
    }
}
