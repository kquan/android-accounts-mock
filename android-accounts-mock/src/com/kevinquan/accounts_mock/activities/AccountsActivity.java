/*
 * Copyright 2013 Kevin Quan
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.kevinquan.accounts_mock.activities;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.kevinquan.accounts_mock.model.CreateNewAccountListItem;
import com.kevinquan.accounts_mock.model.ExistingAccountListItem;
import com.kevinquan.accounts_mock.model.IAccountListItem;
import com.kevinquan.accounts_mock.tasks.PopulateAccountListTask;
import com.kevinquan.accounts_mock.tasks.RemoveAccountTask;
import com.kevinquan.android_accounts_mock.R;

public class AccountsActivity extends ListActivity {

    @SuppressWarnings("unused")
    private static final String TAG = AccountsActivity.class.getSimpleName();

    protected List<IAccountListItem> mItems;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        new PopulateAccountListTask(this, getListView()).execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Can't use getListAdapter() because we didn't set the adapter directly on the activity.
        final IAccountListItem item = (IAccountListItem)getListView().getAdapter().getItem(position);
        if (item instanceof CreateNewAccountListItem) {
            startActivityForResult(new Intent(this, CreateAccountActivity.class), 0);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                            .setTitle(R.string.accounts_remove_title)
                                            .setMessage(getString(R.string.accounts_remove_desc, item.getName()))
                                            .setNegativeButton(android.R.string.cancel, null)
                                            .setPositiveButton(android.R.string.ok, new OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (!(item instanceof ExistingAccountListItem)) {
                                                        return;
                                                    }
                                                    new RemoveAccountTask(AccountsActivity.this, getListView()).execute(((ExistingAccountListItem)item).getAccount());
                                                }
                                            });
            builder.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == CreateAccountActivity.RESULT_CODE_CREATED_ACCOUNT) {
            new PopulateAccountListTask(this, getListView()).execute();            
        }
    }

}
