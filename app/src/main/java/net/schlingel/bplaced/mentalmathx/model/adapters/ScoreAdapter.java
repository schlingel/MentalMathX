package net.schlingel.bplaced.mentalmathx.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.schlingel.bplaced.mentalmathx.R;
import net.schlingel.bplaced.mentalmathx.model.Score;
import net.schlingel.bplaced.mentalmathx.utils.LabelHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zombie on 22.07.14.
 */
public class ScoreAdapter extends BaseAdapter {
    private static class ScoreViewHolder {
        TextView txtVwCorrect;
        TextView txtVwErrors;
        TextView txtVwTime;
    }

    private List<Score> scores;
    private LayoutInflater inflater;
    private Context context;

    public ScoreAdapter(Collection<Score> scores, Context context) {
        this.scores = new ArrayList<Score>();
        this.inflater = LayoutInflater.from(context);
        this.context = context;

        setItems(scores.toArray(new Score[0]));
    }

    public ScoreAdapter(Score[] scores, Context context) {
        this(Arrays.asList(scores), context);
    }

    public ScoreAdapter() {
        throw new IllegalStateException("Don't use this constructor. Context is needed!");
    }

    public void setItems(Score[] scores) {
        this.scores.clear();
        this.scores.add(Score.HEADER_ELEMENT);
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
        if(view == null) {
            view = inflater.inflate(R.layout.lv_item_score, null);
        }

        if(view.getTag() == null) {
            TextView txtVwCorrect = (TextView) view.findViewById(R.id.txtVwCorrect);
            TextView txtVwErrors = (TextView) view.findViewById(R.id.txtVwErrors);
            TextView txtVwTime = (TextView)view.findViewById(R.id.txtVwTime);

            ScoreViewHolder tag = new ScoreViewHolder();
            tag.txtVwCorrect = txtVwCorrect;
            tag.txtVwErrors = txtVwErrors;
            tag.txtVwTime = txtVwTime;

            view.setTag(tag);
        }

        Score score = (Score)getItem(i);
        ScoreViewHolder holder = (ScoreViewHolder)view.getTag();

        String sCorrect = null;
        String sWrong = null;
        String sTimeLabel = null;

        int styleId = (i == 0) ? R.style.BoldText : R.style.NormalText;

        if(i == 0) {
            sCorrect = context.getString(R.string.txtVwCorrectHeader);
            sWrong = context.getString(R.string.txtVwWrongHeader);
            sTimeLabel = context.getString(R.string.txtVwTimeHeader);
        } else {
            sCorrect = score.getCorrectGuesses().toString();
            sWrong = score.getWrongGuesses().toString();
            sTimeLabel = LabelHelper.timeLabelFrom(score.getTimeInSec());
        }

        holder.txtVwCorrect.setTextAppearance(context, styleId);
        holder.txtVwTime.setTextAppearance(context, styleId);
        holder.txtVwErrors.setTextAppearance(context, styleId);

        holder.txtVwCorrect.setText(sCorrect);
        holder.txtVwErrors.setText(sWrong);
        holder.txtVwTime.setText(sTimeLabel);

        return view;
    }
}
