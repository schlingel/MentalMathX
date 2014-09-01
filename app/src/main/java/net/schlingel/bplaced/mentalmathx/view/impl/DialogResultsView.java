package net.schlingel.bplaced.mentalmathx.view.impl;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import net.schlingel.bplaced.mentalmathx.R;
import net.schlingel.bplaced.mentalmathx.model.Result;
import net.schlingel.bplaced.mentalmathx.utils.LabelHelper;
import net.schlingel.bplaced.mentalmathx.view.ResultsView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

/**
 * Created by zombie on 29.06.14.
 */
@EFragment(R.layout.dialog_results_view)
public class DialogResultsView extends DialogFragment implements ResultsView {
    private FragmentActivity host;

    TextView txtVwTime;

    TextView txtVwCorrectGuesses;

    TextView txtVwWrongGuesses;

    TextView txtVwSummary;

    private Result result;

    public static DialogResultsView_ from(FragmentActivity host) {
        DialogResultsView_ dlg = new DialogResultsView_();
        dlg.setHost(host);

        return dlg;
    }

    @Override
    public void show(Result results) {
        result = results;
        show(host.getSupportFragmentManager(), this.getClass().getSimpleName());
    }

    public void setHost(FragmentActivity host) {
        this.host = host;
    }

    public interface OKListener {
        public void onOK(DialogFragment fragment);
    }

    private OKListener listener;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtVwSummary = (TextView)view.findViewById(R.id.txtVwSummary);
        txtVwTime = (TextView)view.findViewById(R.id.txtVwTime);
        txtVwCorrectGuesses = (TextView)view.findViewById(R.id.txtVwCorrectGuesses);
        txtVwWrongGuesses = (TextView)view.findViewById(R.id.txtVwWrongGuesses);
        txtVwCorrectGuesses.setText(Integer.toString(result.getCorrectGuesses()));
        txtVwWrongGuesses.setText(Integer.toString(result.getWrongGuesses()));
        txtVwTime.setText(LabelHelper.timeLabelFrom(result.getTime()));
        txtVwSummary.setText(LabelHelper.summaryLabelFor(result.getMode(), host));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OKListener) {
            this.listener = (OKListener)activity;
        } else {
            throw new IllegalArgumentException("Activity must be an OKListener instance!");
        }
    }

    @Click(R.id.btnOK)
    public void onOKClick() {
        listener.onOK(this);
    }
}
