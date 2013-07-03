package com.kevinquan.accounts_mock.model;

import java.lang.ref.WeakReference;

import android.content.Context;

public abstract class AbstractAccountListItem implements IAccountListItem {

    @SuppressWarnings("unused")
    private static final String TAG = AbstractAccountListItem.class.getSimpleName();
    
    protected WeakReference<Context> mContextReference;

    public AbstractAccountListItem(Context context) {
        mContextReference = new WeakReference<Context>(context);
    }
    
    public int getIcon() {
        return NO_ICON;
    }

}
