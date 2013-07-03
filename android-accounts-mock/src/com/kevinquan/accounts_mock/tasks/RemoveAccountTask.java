package com.kevinquan.accounts_mock.tasks;

import java.lang.ref.WeakReference;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.kevinquan.android_accounts_mock.R;

public class RemoveAccountTask extends AbstractAsyncTask<Account, String, Boolean> {

    private static final String TAG = RemoveAccountTask.class.getSimpleName();

    protected WeakReference<ListView> mListToPopulateReference;
    protected ProgressDialog mDialog;
    
    public RemoveAccountTask(Context context, ListView list) {
        super(context);
        mListToPopulateReference = new WeakReference<ListView>(list);
    }


    @Override
    protected Boolean doInBackground(Account... params) {
        Context context = mContextReference.get();
        if (context == null) {
            Log.w(TAG, "Could not remove account as context no longer exists.");
            return null;
        }
        if (params != null && params.length == 1 && params[0] != null) {
            AccountManager.get(context).removeAccount(params[0], null, null);
            return true;
        }
        return false;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        Context context = mContextReference.get();
        ListView listView = mListToPopulateReference.get();
        if (result && context != null && listView != null) {
            new PopulateAccountListTask(context, listView).execute();
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = showIndeterminableProgressDialog(null,null);
    }
    
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Context context = mContextReference.get();
        if (context == null) {
            return;
        }
        if (values != null && values.length == 1 && mDialog != null && mDialog.isShowing()) {
            mDialog.setMessage(context.getString(R.string.accounts_remove_status, values[0]));
        }
    }
    
}
