package com.kevinquan.accounts_mock.tasks;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.kevinquan.accounts_mock.model.CreateNewAccountListItem;
import com.kevinquan.accounts_mock.model.ExistingAccountListItem;
import com.kevinquan.accounts_mock.model.IAccountListItem;
import com.kevinquan.accounts_mock.ui.AccountsAdapter;

public class PopulateAccountListTask extends GetMockAccountsTask {

    private static final String TAG = PopulateAccountListTask.class.getSimpleName();
    
    protected WeakReference<ListView> mListToPopulateReference;

    public PopulateAccountListTask(Context context, ListView list) {
        super(context);
        mListToPopulateReference = new WeakReference<ListView>(list);
    }

    @Override
    protected void onPostExecute(List<Account> result) {
        super.onPostExecute(result);
        Context context = mContextReference.get();
        ListView listView = mListToPopulateReference.get();
        if (context == null || listView == null) {
            Log.w(TAG, "Not updating list as context or list is no longer referenced.");
            return;
        }
        List<IAccountListItem> items = new ArrayList<IAccountListItem>();
        if (result != null) {
            // Sort alphabetically
            Collections.sort(result, new Comparator<Account>() {
                @Override
                public int compare(Account lhs, Account rhs) {
                    return lhs.name.compareTo(rhs.name);
                }
            });
            for (Account account : result) {
                items.add(new ExistingAccountListItem(context, account));
            }
        }
        // Always show "Create a new account" as the last item
        items.add(new CreateNewAccountListItem(context));
        
        listView.setAdapter(new AccountsAdapter(context, items));
    }

}
