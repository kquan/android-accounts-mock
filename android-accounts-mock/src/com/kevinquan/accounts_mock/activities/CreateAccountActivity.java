package com.kevinquan.accounts_mock.activities;

import java.util.HashSet;
import java.util.List;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kevinquan.accounts_mock.tasks.CreateAccountTask;
import com.kevinquan.accounts_mock.tasks.CreateAccountTask.AccountInfo;
import com.kevinquan.accounts_mock.tasks.GetMockAccountsTask;
import com.kevinquan.android_accounts_mock.R;

public class CreateAccountActivity extends Activity {

    @SuppressWarnings("unused")
    private static final String TAG = CreateAccountActivity.class.getSimpleName();
    
    public static final int RESULT_CODE_CREATED_ACCOUNT = 100;
    
    protected static final String BUNDLE_NAME = "BundleName";
    
    protected HashSet<String> mExistingAccounts;
    protected EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mExistingAccounts = new HashSet<String>();
        new GetMockAccountsTask(this) {
            @Override
            protected void onPostExecute(List<Account> result) {
                super.onPostExecute(result);
                if (result != null) {
                    for (Account aResult : result) {
                        if (!TextUtils.isEmpty(aResult.name))
                        mExistingAccounts.add(aResult.name.trim());
                    }
                }
            }
            
        }.execute();
        
        mName = (EditText)findViewById(R.id.account_name);
        
        if (savedInstanceState != null) {
            mName.setText(savedInstanceState.getString(BUNDLE_NAME));
        }
    }
    
    public void onCreateAccount(View view) {
        mName.setError(null);
        String name = mName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mName.setError(getString(R.string.create_error_no_name));
            mName.requestFocus();
            return;
        }
        name = name.trim();
        if (mExistingAccounts.contains(name)) {
            mName.setError(getString(R.string.create_error_duplicate_name));
            mName.requestFocus();
            return;            
        }
        new CreateAccountTask(this) {

            @Override
            protected void onPostExecute(Boolean[] result) {
                super.onPostExecute(result);
                if (result != null && result.length == 1 && result[0]) {
                    setResult(RESULT_CODE_CREATED_ACCOUNT);
                    finish();
                } else {
                    Toast.makeText(CreateAccountActivity.this, getString(R.string.create_error_general), Toast.LENGTH_SHORT).show();
                }
            }
            
        }.execute(new AccountInfo(name));
    }
    
    public void onCancel(View view) {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_NAME, mName.getText().toString());
    }

}
