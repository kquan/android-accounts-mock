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

package com.kevinquan.accounts_mock.tasks;

import java.util.ArrayList;
import java.util.List;

import com.kevinquan.accounts_mock.AndroidAccountsMock;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;

public class GetMockAccountsTask extends AbstractAsyncTask<Void, Void, List<Account>> {

    private static final String TAG = GetMockAccountsTask.class.getSimpleName();

    public GetMockAccountsTask(Context context) {
        super(context);
    }
    
    @Override
    protected List<Account> doInBackground(Void... params) {
        Context context = mContextReference.get();
        if (context == null) {
            Log.w(TAG, "Could not retrieve accounts as context no longer exists.");
            return null;
        }
        List<Account> result = new ArrayList<Account>();
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (AndroidAccountsMock.ACCOUNT_TYPE.equals(account.type)) {
                result.add(account);
            }
        }
        return result;
    }
}
