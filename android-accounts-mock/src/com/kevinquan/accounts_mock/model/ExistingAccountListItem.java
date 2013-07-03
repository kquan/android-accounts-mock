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
