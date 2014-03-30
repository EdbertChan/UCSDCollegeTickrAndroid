package collegetickr.application.ConfessionsSyncAdapter;

import android.app.Service;

import android.content.Intent;
import android.os.IBinder;
//the only thing we need to modify in a syncservice is the associated adapter. 

//It makes no sense to abstract?

public class PostSyncService extends Service {
	 // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();
    private static PostSyncAdapter sSyncAdapter = null;

   
  
   
    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null)
                sSyncAdapter = new PostSyncAdapter(getApplicationContext(), true);
        }
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}