package net.schlingel.bplaced.mentalmathx;


import android.app.Activity;
import android.support.v4.app.DialogFragment;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by zombie on 29.06.14.
 */
@EFragment(R.layout.summary_dialog)
public class SummaryFragmentDialog extends DialogFragment {
    public interface OKListener {
        public void onOK(DialogFragment fragment);
    }

    private OKListener listener;

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
