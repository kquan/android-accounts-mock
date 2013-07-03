
package com.kevinquan.accounts_mock.tasks;

import java.lang.ref.WeakReference;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public abstract class AbstractAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    @SuppressWarnings("unused")
    private static final String TAG = AbstractAsyncTask.class.getSimpleName();

    protected WeakReference<Context> mContextReference;

    public AbstractAsyncTask(Context context) {
        mContextReference = new WeakReference<Context>(context);
    }
    
    protected ProgressDialog showIndeterminableProgressDialog(String title, String message) {
        Context context = mContextReference.get();
        if (context == null) {
            return null;
        }
        ProgressDialog dialog = ProgressDialog.show(context, title, message);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }

}
