
package com.kevinquan.accounts_mock.authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AccountAuthenticatorService extends Service {

    @SuppressWarnings("unused")
    private static final String TAG = AccountAuthenticatorService.class.getSimpleName();

    private static AccountAuthenticator sAccountAuthenticator = null;

    private AccountAuthenticator getAuthenticator() {
        if (sAccountAuthenticator == null)
            sAccountAuthenticator = new AccountAuthenticator(this);
        return sAccountAuthenticator;
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder ret = null;
        if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT))
            ret = getAuthenticator().getIBinder();
        return ret;

    }

}
