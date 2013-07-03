
package com.kevinquan.accounts_mock.tasks;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.kevinquan.accounts_mock.AndroidAccountsMock;
import com.kevinquan.accounts_mock.tasks.CreateAccountTask.AccountInfo;
import com.kevinquan.android_accounts_mock.R;

public class CreateAccountTask extends AbstractAsyncTask<AccountInfo, String, Boolean[]> {

    private static final String TAG = CreateAccountTask.class.getSimpleName();
    
    public static class AccountInfo {
        
        protected String mName;
        protected String mPassword = AndroidAccountsMock.DEFAULT_PASSWORD;
        protected Bundle mBundle;
        
        public AccountInfo(String name) {
            mName = name;
        }

        public String getName() {
            return mName;
        }

        public String getPassword() {
            return mPassword;
        }

        public Bundle getBundle() {
            return mBundle;
        }
    }
    
    protected ProgressDialog mDialog;

    public CreateAccountTask(Context context) {
        super(context);
    }

    @Override
    protected Boolean[] doInBackground(AccountInfo... params) {
        if (params == null || params.length == 0) {
            Log.d(TAG, "No account information provided, therefore no accounts can be created.");
            return null;
        }
        Boolean[] results = new Boolean[params.length];
        Context context = mContextReference.get();
        if (context == null) {
            Log.w(TAG, "Could not add account information as context no longer exists.");
            return results;
        }
        int i = 0;
        for (AccountInfo accountInfo : params) {
            publishProgress(new String[] { accountInfo.getName() });
            Account newAccount = new Account(accountInfo.getName(), AndroidAccountsMock.ACCOUNT_TYPE);
            AccountManager.get(context).addAccountExplicitly(newAccount, accountInfo.getPassword(), accountInfo.getBundle());
            results[i++] = true;
        }
        return results;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog = showIndeterminableProgressDialog(null,null);
    }

    @Override
    protected void onPostExecute(Boolean[] result) {
        super.onPostExecute(result);
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Context context = mContextReference.get();
        if (context == null) {
            return;
        }
        if (values != null && values.length == 1 && mDialog != null && mDialog.isShowing()) {
            mDialog.setMessage(context.getString(R.string.create_status, values[0]));
        }
    }
}
