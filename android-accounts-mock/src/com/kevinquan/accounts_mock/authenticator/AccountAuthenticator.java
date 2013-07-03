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

package com.kevinquan.accounts_mock.authenticator;

import java.lang.ref.WeakReference;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kevinquan.accounts_mock.activities.CreateAccountActivity;

public class AccountAuthenticator extends AbstractAccountAuthenticator {

    private static final String TAG = AccountAuthenticator.class.getSimpleName();
    
    protected WeakReference<Context> mContextReference;

    public AccountAuthenticator(Context context) {
        super(context);
        mContextReference = new WeakReference<Context>(context);
    }
    
    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
            String authTokenType, String[] requiredFeatures, Bundle options)
            throws NetworkErrorException {
        Bundle result = new Bundle();
        Context context = mContextReference.get();
        if (context == null) {
            Log.w(TAG, "Could not return login screen information as context no longer exists.");
            return result;
        }

        Intent createIntent = new Intent(context, CreateAccountActivity.class);
        createIntent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        result.putParcelable(AccountManager.KEY_INTENT, createIntent);
        return result;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

}
