package collegetickr.application.Confessions.SyncAdapter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseIntArray;

import org.michenux.drodrolib.info.AppUsageUtils;
import org.michenux.drodrolib.network.sync.AbstractSyncHelper;

import collegetickr.application.R;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsContentProvider;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsTrackExpandedTable;
import collegetickr.library.ApplicationSettings;

public class ConfessionsSyncHelper {

    public static final String ACCOUNT = "postConfessionsAccount" ;
    private static final String DEBUG_TAG = ConfessionsSyncHelper.class.getSimpleName();
    public ConfessionsSyncHelper(Context c) {
    	//createAccount();
    	createAccount("temp", ACCOUNT, ConfessionsContentProvider.AUTHORITY , true, c);
    }

    private Account mAccount ;

    private String mAuthority;

    private int mDefaultIntervalIndex;

    private SparseIntArray mIntervals ;
   /* public void createTutorialAccount( Context context ) {
        super.createAccount(ACCOUNT, context.getString(R.string.posts_confessions_accountType), MyTodoContentProvider.AUTHORITY,
                this.isNotificationEnabled(context), context);
    }*/


    public boolean createAccount( String accountName, String accountType, String authority, boolean enableSync, Context context ) {

        this.mAccount = new Account(accountName, accountType);
        this.mAuthority = authority ;
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        boolean result = accountManager.addAccountExplicitly(mAccount, null, null);
   /*   if ( enableSync ) {
            addPeriodicSync(getSyncIntervalInMinute(context));
        }*/

        AppUsageUtils.updateLastSync(context);

        return result;
    }
   
 /*   protected boolean addPeriodicSync(int intervalInMinute) {
        if ( ApplicationCompileSettings.DEBUG ) {
            Log.d(DEBUG_TAG, "addPeriodicSync in minute: " + intervalInMinute);
        }
        boolean added = super.addPeriodicSync(intervalInMinute);
        if (ApplicationCompileSettings.DEBUG) {
            if ( added ) {
                Log.d(DEBUG_TAG, "periodic sync does not exist");
                Log.d(DEBUG_TAG, "add periodic sync with interval in minute : " + intervalInMinute );
            }
            else {
                Log.d(DEBUG_TAG, "periodic sync already exists");
            }
        }
        return added;
    } */
    public void performSync() {
        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        Log.v(DEBUG_TAG, "Perform Sync");
        ContentResolver.requestSync(mAccount, mAuthority, settingsBundle);
    }

    public boolean isNotificationEnabled( Context context ) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("notificationPref", true);
    }
}
