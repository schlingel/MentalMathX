package net.schlingel.bplaced.mentalmathx.view.impl;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
public class DialogResultsView extends DialogFragment implements ResultsView, View.OnClickListener {
    private FragmentActivity host;

    TextView txtVwTime;

    TextView txtVwCorrectGuesses;

    TextView txtVwWrongGuesses;

    TextView txtVwSummary;

    Button btnOK;

    private Result result;

    public static DialogResultsView from(FragmentActivity host) {
        DialogResultsView dlg = new DialogResultsView();
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

    @Override
    public void onClick(View view) {
        listener.onOK(this);
    }

    public interface OKListener {
        public void onOK(DialogFragment fragment);
    }

    private OKListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_results_view, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        txtVwSummary = (TextView)view.findViewById(R.id.txtVwSummary);
        txtVwTime = (TextView)view.findViewById(R.id.txtVwTime);
        txtVwCorrectGuesses = (TextView)view.findViewById(R.id.txtVwCorrectGuesses);
        txtVwWrongGuesses = (TextView)view.findViewById(R.id.txtVwWrongGuesses);
        btnOK = (Button)view.findViewById(R.id.btnOK);

        txtVwCorrectGuesses.setText(Integer.toString(result.getCorrectGuesses()));
        txtVwWrongGuesses.setText(Integer.toString(result.getWrongGuesses()));
        txtVwTime.setText(LabelHelper.timeLabelFrom(result.getTime()));
        txtVwSummary.setText(LabelHelper.summaryLabelFor(result.getMode(), host));
        btnOK.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
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
}
