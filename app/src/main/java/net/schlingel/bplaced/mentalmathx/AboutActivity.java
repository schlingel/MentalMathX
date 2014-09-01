package net.schlingel.bplaced.mentalmathx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import org.androidannotations.annotations.EActivity;

/**
 * Created by zombie on 01.09.14.
 */
@EActivity(R.layout.activity_about)
public class AboutActivity extends ActionBarActivity {
    public static Intent asIntent(Context sender) {
        Intent i = new Intent(sender, AboutActivity_.class);
        return i;

    }
}
