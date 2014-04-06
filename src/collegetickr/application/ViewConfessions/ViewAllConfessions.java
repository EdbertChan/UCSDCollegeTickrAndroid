package collegetickr.application.ViewConfessions;

import java.util.ArrayList;

import org.michenux.drodrolib.db.utils.CursorUtils;



import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import collegetickr.application.R;
import collegetickr.application.Confessions.SubmitConfessions;
import collegetickr.application.Confessions.SyncAdapter.ConfessionsSyncHelper;
import collegetickr.application.Confessions.SyncAdapter.ConfessionsSyncAdapter;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsContentProvider;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsTrackExpandedTable;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.LazyAdapter.LazyConfessionsPostAdapter;
import collegetickr.application.Post.Post;
import collegetickr.application.Post.PostWrapperForExpandableTextView;
import collegetickr.application.SubmitComplimentsConfessions.Compliments;


import collegetickr.library.ExpandableTextView;
import collegetickr.library.IdentifiersList;
import collegetickr.library.JSONHandlerLibrary;
import collegetickr.library.AbstractFragments.NavDrawerContainerSwapFragmentsViaActionBarTemplate;
import collegetickr.library.ListenersAdapters.ViewPagerAdapter;
import collegetickr.library.WebPostGetAsyncTask.AsyncTaskCompleteListener;
import collegetickr.library.WebPostGetAsyncTask.GetDataWebTask;
import android.accounts.Account;
import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;

import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAllConfessions extends Fragment implements OnRefreshListener,
		LoaderManager.LoaderCallbacks<Cursor> {
	int rootLayout = R.layout.view_all_confessions_layout;
	PullToRefreshLayout mPullToRefreshLayout;
	String url = "";
	private static final String DEBUG_TAG = ViewAllConfessions.class
			.getSimpleName();
	ListView list;
	// LazyPostAdapter lazyPostAdapter;
	private boolean mHasSync = false;

	ConfessionsSyncHelper mTutorialSyncHelper;
	
	private BroadcastReceiver onFinishSyncReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.v(DEBUG_TAG, "onReceive");
			updateRefresh(false);
			mHasSync = true;
			// fillData();
		}
	};

	private BroadcastReceiver onStartSyncReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.v(DEBUG_TAG, "onStartSyncReceiver");
			updateRefresh(true);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(DEBUG_TAG, "onCreate called");
		super.onCreate(savedInstanceState);
		//        setRetainInstance(true);
		setHasOptionsMenu(true);
		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		Log.v(DEBUG_TAG, "onViewCreated called");
		super.onViewCreated(view, savedInstanceState);
		
		ActionBarPullToRefresh.from(this.getActivity())
		.allChildrenArePullable().listener(this)
		.setup(mPullToRefreshLayout);

		   if ( ((MainActivity) getActivity()).isPostSyncAdapterRunning()) {
	         //   Log.d(YourApplication.LOG_TAG, "onViewCreated: synchronizing");
	            updateRefresh(true);
	        }
	       
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(DEBUG_TAG, "onCreateView called");
		super.onCreateView(inflater, container, savedInstanceState);
		setHasOptionsMenu(true);
		mTutorialSyncHelper = new ConfessionsSyncHelper(this.getActivity()
				.getBaseContext());
		View rootView = inflater.inflate(rootLayout, container, false);

		// /You will setup the action bar with pull to refresh layout
		mPullToRefreshLayout = (PullToRefreshLayout) rootView
				.findViewById(R.id.ptr_layout);
	
		// begin set upadapter
		list = (ListView) rootView.findViewById(R.id.listView);
		//list.setFastScrollEnabled(true);
		fillData();

		/*
		 * 
		 * if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
		 * Log.d(DEBUG_TAG, "Restoring from saved state available!");
		 * latestFetchArrayList = savedInstanceState
		 * .getParcelableArrayList("arraylist");
		 * resetAdapter(latestFetchArrayList);
		 * 
		 * } else if (latestFetchArrayList != null &&
		 * !latestFetchArrayList.isEmpty()) { Log.d(DEBUG_TAG,
		 * "ArrayList has not changed. Reloading adapter");
		 * 
		 * resetAdapter(latestFetchArrayList); } else { Log.d(DEBUG_TAG,
		 * "Regetting the fragments. No state available!");
		 * 
		 * // getNewFragments(); }
		 */

		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		Log.v(DEBUG_TAG, "onSaveInstanceState");
		
		super.onSaveInstanceState(savedInstanceState);

		/*
		 * savedInstanceState.putParcelableArrayList(
		 * IdentifiersList.LIST_OF_COMPLIMENTS_CONFESSIONS_TAG,
		 * latestFetchArrayList);
		 */

	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.confession_view_action_bar_option, menu);

	}

	@Override
	public void onRefreshStarted(View view) {
		// PostsSyncAdapter p = new PostsSyncAdapter();

		mTutorialSyncHelper.performSync();

		// getNewFragments();
		// ContentResolver.requestSync(mAccount, AUTHORITY, settingsBundle);
		// mTutorialSyncHelper.performSync();
	}

	@Override
	public void onAttach(Activity activity) {
		Log.v(DEBUG_TAG, "onAttach");

		super.onAttach(activity);

		// Create account, if needed
		// PostsSyncHelper.CreateSyncAccount(activity);
	}

	private void updateRefresh(final boolean isSyncing) {
		if (!isSyncing) {
			// Log.d(YourApplication.LOG_TAG, "show as not refreshing");
			mPullToRefreshLayout.setRefreshComplete();
		} else {
			// Log.d(YourApplication.LOG_TAG, "show as refreshing");
			mPullToRefreshLayout.setRefreshing(true);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.v(DEBUG_TAG, "onResume");
		LocalBroadcastManager.getInstance(this.getActivity()).registerReceiver(
				onFinishSyncReceiver,
				new IntentFilter(ConfessionsSyncAdapter.SYNC_FINISHED));
		LocalBroadcastManager.getInstance(this.getActivity()).registerReceiver(
				onStartSyncReceiver,
				new IntentFilter(ConfessionsSyncAdapter.SYNC_STARTED));

		//autosync on bootup
		if (!mHasSync) {
			mTutorialSyncHelper.performSync();
		}else{
			
		 lazyPostAdapterWithExpandablePosts.changeCursor(c);
		}
		//our cursor went null!
		
		
		//this.mAdapter.changeCursor(cursor);
		
		//need to get back our views
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.v(DEBUG_TAG, "onPause");
		LocalBroadcastManager.getInstance(this.getActivity())
				.unregisterReceiver(onFinishSyncReceiver);
		LocalBroadcastManager.getInstance(this.getActivity())
				.unregisterReceiver(onStartSyncReceiver);
		c= lazyPostAdapterWithExpandablePosts.getCursor();
	
		}
	Cursor c;
	LazyConfessionsPostAdapter lazyPostAdapterWithExpandablePosts;
	private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

	private void fillData() {
		getLoaderManager().initLoader(0, null, this);
		String[] mAdapterFromColumns = new String[] {
				ConfessionsTrackExpandedTable.COLUMN_ID,
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT,
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
				ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT,
				ConfessionsTrackExpandedTable.COLUMN_TRIMMED};
		int[] mAdapterToViews = new int[] { R.id.postItemText,
				R.id.postItemImage };

		mCallbacks = this;
	
		this.lazyPostAdapterWithExpandablePosts = new LazyConfessionsPostAdapter(this.getActivity(),
				R.layout.post_layout_item, null, mAdapterFromColumns,
				mAdapterToViews, 0);
	//	lazyPostAdapterWithExpandablePosts.notifyDataSetChanged();

		/*this.lazyPostAdapterWithExpandablePosts.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			public boolean setViewValue(View view, Cursor cursor,
					int columnIndex) {

				if (view.getId() == R.id.postItemText) {

	
					String title = cursor.getString(cursor
							.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT));
					ExpandableTextView textView = (ExpandableTextView) view;
					
		
					int trimmed = cursor.getInt( cursor
							.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_TRIMMED));
					
					textView.setTrim(PostWrapperForExpandableTextView.convertIntToBoolean(trimmed));
					textView.setText(title);

					return true;
				}

				return false;
			}
		});*/

		lazyPostAdapterWithExpandablePosts.notifyDataSetChanged();
		list.setAdapter(this.lazyPostAdapterWithExpandablePosts);

	}
	CursorLoader cursorLoader;
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle bundle) {
		String[] projection = { ConfessionsTrackExpandedTable.COLUMN_ID,
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT,
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
				ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT,
				ConfessionsTrackExpandedTable.COLUMN_TRIMMED};
		///this is the line?
		 cursorLoader = new CursorLoader(this.getActivity(),
				ConfessionsContentProvider.CONTENT_URI, projection, null, null,
				ConfessionsTrackExpandedTable.COLUMN_ID + " DESC");

		return cursorLoader;
	}


	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		Log.v(DEBUG_TAG, "onLoadFinished");
		//cursor.moveToFirst();

		// we return a cursor. We want to manipulate the cursor into being
		// sorted by a particular column

		// for some reason, this is the line that prevents shit from being
		// sorted?

		this.lazyPostAdapterWithExpandablePosts.changeCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursor) {
		Log.v(DEBUG_TAG, "onLoaderReset");
		this.lazyPostAdapterWithExpandablePosts.changeCursor(null);
	}



}