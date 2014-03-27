package collegetickr.application.ViewConfessions;

import java.util.ArrayList;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import collegetickr.application.R;
import collegetickr.application.ComplimentsConfessions.Compliments;
import collegetickr.application.ComplimentsConfessions.Confessions;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.LazyAdapter.LazyPostAdapter;
import collegetickr.application.Post.Post;

import collegetickr.library.IdentifiersList;
import collegetickr.library.JSONHandlerLibrary;
import collegetickr.library.ListenersAdapters.ViewPagerAdapter;
import collegetickr.library.WebPostGetAsyncTask.AsyncTaskCompleteListener;
import collegetickr.library.WebPostGetAsyncTask.GetDataWebTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

public class ViewAllConfessions extends Fragment implements OnRefreshListener,
		AsyncTaskCompleteListener {
	int rootLayout = R.layout.view_all_confessions_layout;
	PullToRefreshLayout mPullToRefreshLayout;
	String url = "";
	private static final String DEBUG_TAG = "ConfessionNavDrawer";
	ListView list;
	LazyPostAdapter lazyPostAdapter;

	ArrayList<Post> latestFetchArrayList = new ArrayList<Post>();


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(rootLayout, container, false);

		// /You will setup the action bar with pull to refresh layout
		mPullToRefreshLayout = (PullToRefreshLayout) rootView
				.findViewById(R.id.ptr_layout);
		ActionBarPullToRefresh.from(this.getActivity())
				.allChildrenArePullable().listener(this)
				.setup(mPullToRefreshLayout);

		// begin set upadapter
				list = (ListView) rootView.findViewById(R.id.listView);

		if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
			Log.d(DEBUG_TAG, "Restoring from saved state available!");
			latestFetchArrayList = savedInstanceState
					.getParcelableArrayList("arraylist");
			resetAdapter(latestFetchArrayList);

		} else if (latestFetchArrayList != null
				&& !latestFetchArrayList.isEmpty()) {
			Log.d(DEBUG_TAG, "ArrayList has not changed. Reloading adapter");

			resetAdapter(latestFetchArrayList);
		} else {
			Log.d(DEBUG_TAG, "Regetting the fragments. No state available!");

			// getNewFragments();
		}

		return rootView;
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		Log.v(DEBUG_TAG, "onSaveInstanceState");
		super.onSaveInstanceState(savedInstanceState);

		savedInstanceState.putParcelableArrayList(
				IdentifiersList.LIST_OF_COMPLIMENTS_CONFESSIONS_TAG,
				latestFetchArrayList);

	}

	private void getNewFragments() {

		new GetDataWebTask(this)
				.execute("http://collegetickr.com/api/v1/contents/ucsd/confessions");
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.confession_view_action_bar_option, menu);

	}

	@Override
	public void onRefreshStarted(View view) {
		getNewFragments();

	}

	private void resetAdapter(ArrayList<Post> arrayListPosts) {
		lazyPostAdapter = new LazyPostAdapter(getActivity(),
				arrayListPosts);

		list.setAdapter(lazyPostAdapter);
		lazyPostAdapter.notifyDataSetChanged();
	}

	@Override
	public void onTaskComplete(Object result) {
		Log.v(DEBUG_TAG, "OnTaskComplete");

		latestFetchArrayList = JSONHandlerLibrary
				.convertStringtoArrayListPosts((String) result);
		Log.i(DEBUG_TAG, latestFetchArrayList.get(0).getContent_image());

		resetAdapter(this.latestFetchArrayList);
		mPullToRefreshLayout.setRefreshComplete();
	}


}