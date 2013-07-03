package com.kevinquan.accounts_mock.model;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;

public class ExistingAccountListItem extends AbstractAccountListItem implements IAccountListItem {

    private static final String TAG = ExistingAccountListItem.class.getSimpleName();

    protected Account mAccount;
    protected String mPassword;
    
    public ExistingAccountListItem(Context context, Account account) {
        super(context);
        mAccount = account;
    }
    
    @Override
    public String getName() {
        if (mAccount == null) {
            Log.w(TAG, "No account was provided for this item.");
            return new String();
        }
        return mAccount.name;
    }

    public Account getAccount() {
        return mAccount;
    }

}
