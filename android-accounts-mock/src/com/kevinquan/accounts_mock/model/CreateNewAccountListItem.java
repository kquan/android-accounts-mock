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
