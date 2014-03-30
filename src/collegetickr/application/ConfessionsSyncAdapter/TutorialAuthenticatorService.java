package collegetickr.application.ConfessionsSyncAdapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import collegetickr.library.ApplicationCompileSettings;
import collegetickr.library.IdentifiersList;

public class TutorialAuthenticatorService extends Service {

    // Instance field that stores the authenticator object
    private TutorialAuthenticator mAuthenticator;
    public static final String DEBUG_TAG = TutorialAuthenticatorService.class.getSimpleName();
    @Override
    public void onCreate() {
        // Create a new authenticator object
        if (ApplicationCompileSettings.DEBUG ) {
            Log.v(DEBUG_TAG, "TutorialAuthenticatorService.onCreate()");
        }
        mAuthenticator = new TutorialAuthenticator(this);
    }
    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        if (ApplicationCompileSettings.DEBUG ) {
            Log.v(DEBUG_TAG, "TutorialAuthenticatorService.onBind()");
        }
        return mAuthenticator.getIBinder();
    }
}
