package collegetickr.application.Confessions.SyncAdapter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.auth.AuthenticationException;
import org.json.JSONException;

import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsContentProvider;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsTrackExpandedTable;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.Post.Post;
import collegetickr.application.Post.PostWrapperForExpandableTextView;

import collegetickr.library.ApplicationSettings;
import collegetickr.library.IdentifiersList;

import android.accounts.Account;
import android.accounts.AccountManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class ConfessionsSyncAdapter extends AbstractThreadedSyncAdapter {
	public static final String SYNC_FINISHED = "sync_finished";
	public static final String SYNC_STARTED = "sync_started";
	private static final String DEBUG_TAG = ConfessionsSyncAdapter.class
			.getSimpleName();

	// Global variables
	// Define a variable to contain a content resolver instance
	public ContentResolver mContentResolver;
	/**
	 * Set up the sync adapter
	 */
	protected long mCurrentSyncTime;

	/**
	 * Flag made 'true' when a request to cancel the synchronization is received
	 */
	protected boolean mCancellation;

	/**
	 * When 'true' the process was requested by the user through the user
	 * interface; when 'false', it was requested automatically by the system
	 */
	protected boolean mIsManualSync;

	/** Counter for failed operations in the synchronization process */
	protected int mFailedResultsCounter;

	protected SyncResult mSyncResult;
	// private final AccountManager mAccountManager;

	private static final String TAG = ConfessionsSyncAdapter.class.getSimpleName();
	protected Date mLastUpdated;

	public ConfessionsSyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		
		mContentResolver = context.getContentResolver();
	}

	/**
	 * Set up the sync adapter. This form of the constructor maintains
	 * compatibility with Android 3.0 and later platform versions
	 */
	public ConfessionsSyncAdapter(Context context, boolean autoInitialize,
			boolean allowParallelSyncs) {
		super(context, autoInitialize, allowParallelSyncs);
		/*
		 * If your app uses a content resolver, get an instance of it from the
		 * incoming Context
		 */
		mContentResolver = context.getContentResolver();
	}

	@Override
	public void onPerformSync(Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) {

		List<Post> remotePosts;
		if(ApplicationSettings.DEBUG)
			Log.v(DEBUG_TAG, "onPerformSync");
		MainActivity.setPostSyncAdapterRunning(true);
		LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(
				new Intent(SYNC_STARTED));
		Cursor localPostsCursor = null;
		try {
			// we get the urls based on the prefernces. We will route this through the
			//network utiliites.
			remotePosts = ConfessionsNetworkUtilities.fetchPostsFromURL("http://collegetickr.com/api/v1/contents/ucsd/confessions");

			// Get shows from local disk. Only used for compairson when making the diff.
			ArrayList<Post> localPosts = new ArrayList<Post>();
			 localPostsCursor = provider.query(
					ConfessionsContentProvider.CONTENT_URI, null, null, null,
					null);
			if (localPostsCursor != null) {
				while (localPostsCursor.moveToNext()) {
					localPosts.add(PostWrapperForExpandableTextView.fromCursor(localPostsCursor));
				}
			}
			localPostsCursor.close();
		
			// See what Remote shows are missing on Local.
			ArrayList<PostWrapperForExpandableTextView> diff = new ArrayList<PostWrapperForExpandableTextView>();
			//contains? is this a reference?
			for (Post remotePost : remotePosts) {
				if (!localPosts.contains(remotePost))
					diff.add(new PostWrapperForExpandableTextView(remotePost));
			}
			
			if (diff.size() == 0) {
				Log.d("udinic", TAG
						+ "> No server changes to update local database");
			} else {
				Log.d("udinic", TAG
						+ "> Updating local database with remote changes");
				
			}//NOTE: THIS SEEMS LIKE BAD MEMORY MANAGEMENT?!
			
			// Updating our content provider
            int i = 0;
            ContentValues showsToLocalValues[] = new ContentValues[diff.size()];
            for (PostWrapperForExpandableTextView localTvShow : diff) {
                showsToLocalValues[i++] = localTvShow.getContentValues();
            }
            provider.bulkInsert(ConfessionsContentProvider.CONTENT_URI, showsToLocalValues);
        

			// want to add all the
			/*
			 * ContentValues postsContentValues[] = new
			 * ContentValues[remotePosts .size()]; for (int i = 0; i <
			 * remotePosts.size(); i++) { postsContentValues[i] =
			 * remotePosts.get(i).getContentValues(); }
			 * 
			 * provider.bulkInsert(ConfessionsContentProvider.CONTENT_URI,
			 * postsContentValues);
			 */
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.apache.http.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Log.v(DEBUG_TAG, "endSync");
			//prevents requery on already open cursor
			if(localPostsCursor != null && !localPostsCursor.isClosed()){
				 localPostsCursor.close();
			    }
			LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(
					new Intent(SYNC_FINISHED));
			MainActivity.setPostSyncAdapterRunning(false);
		}
	}
	
}