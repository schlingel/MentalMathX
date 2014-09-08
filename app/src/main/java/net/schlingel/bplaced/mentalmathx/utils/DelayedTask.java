package net.schlingel.bplaced.mentalmathx.utils;

import android.os.AsyncTask;

/**
 * Created by zombie on 08.09.14.
 */
public class DelayedTask extends AsyncTask<Void, Void, Void> {
    public static interface OnContinueCallback {
        public void doAction();
    }

    private final long timeoutInMillis;
    private final OnContinueCallback onContinue;

    public DelayedTask(long timeout, OnContinueCallback onContinue) {
        this.timeoutInMillis = timeout;
        this.onContinue = onContinue;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        sleep();
        return null;
    }

    private void sleep() {
        try {
            Thread.currentThread().sleep(timeoutInMillis);
        } catch (InterruptedException e) {
            // Ignore. we don't care
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        onContinue.doAction();
    }
}
