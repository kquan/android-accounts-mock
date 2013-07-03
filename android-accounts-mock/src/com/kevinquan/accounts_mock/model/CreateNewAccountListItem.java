package com.kevinquan.accounts_mock.model;

import android.content.Context;

import com.kevinquan.android_accounts_mock.R;

public class CreateNewAccountListItem extends AbstractAccountListItem implements IAccountListItem {

    @SuppressWarnings("unused")
    private static final String TAG = CreateNewAccountListItem.class.getSimpleName();
    
    public CreateNewAccountListItem(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        Context context = mContextReference.get();
        if (context != null) {
            return context.getString(R.string.accounts_create);
        }
        // Default string in case context is missing.
        return "Create a new account";
    }
    
    @Override
    public int getIcon() {
        return android.R.drawable.ic_menu_add;
    }
}
