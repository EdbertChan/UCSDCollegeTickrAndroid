package collegetickr.application.Confessions.SyncAdapter;

import android.app.Service;

import android.content.Intent;
import android.os.IBinder;
//the only thing we need to modify in a syncservice is the associated adapter. 

//It makes no sense to abstract. We could but it seems redundant.

public class ConfessionsSyncService extends Service {
	 // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();
    private static ConfessionsSyncAdapter sSyncAdapter = null;

   
  
   
    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null)
                sSyncAdapter = new ConfessionsSyncAdapter(getApplicationContext(), true);
        }
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}