package collegetickr.application.FragmentApplicationsForNavDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import collegetickr.application.R;
import collegetickr.application.ComplimentsConfessions.Compliments;
import collegetickr.application.ComplimentsConfessions.Confessions;
import collegetickr.application.Post.Post;

import collegetickr.library.IdentifiersList;
import collegetickr.library.JSONHandlerLibrary;
import collegetickr.library.AndroidAbstractClasses.ViewPagerAdapter;
import collegetickr.library.LazyPostAdapter.LazyPostAdapter;
import collegetickr.library.WebPostGetAsyncTask.AsyncTaskCompleteListener;
import collegetickr.library.WebPostGetAsyncTask.GetDataWebTask;
import android.content.Intent;
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

public abstract class NavDrawerContainerSwapFragmentsViaActionBarTemplate extends
		Fragment {
	int rootLayout, containerLayoutId;// = R.layout.compliments_confessions_container_layout;
	PullToRefreshLayout mPullToRefreshLayout;
	String url = "";
	private static final String DEBUG_TAG = "NavDrawerSwapFragmentsViaActionBarTemplate";
	protected Map<Integer, Fragment> map;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	
	}

	protected abstract void initializeValues();

	protected void initializeValues(int rootLayout, int containerLayoutId) {
		this.rootLayout = rootLayout;
		this.containerLayoutId = containerLayoutId;
	}

	public void replaceFragment(Fragment fragment) {

		Log.v(DEBUG_TAG, "replaceFragment called");
		String backStateName = fragment.getClass().getName();

		FragmentManager manager = this.getChildFragmentManager();
		boolean fragmentPopped = manager
				.popBackStackImmediate(backStateName, 0);

		// fragment not in back stack, create it.
		if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
			Log.d(DEBUG_TAG, "creating new Fragment. Wasnt in the stack");
			FragmentTransaction ft = manager.beginTransaction();
			ft.replace(containerLayoutId, fragment, backStateName);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(backStateName);
			ft.commit();

		} else {
			Log.d(DEBUG_TAG, "Already exists. Calling it from the stack.");
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v(DEBUG_TAG, "onOptionsItemSelected");
		Log.v(DEBUG_TAG, item.toString());

		for (int i = 0; i < map.size(); i++) {
			if (map.containsKey(Integer.valueOf(item.getItemId()))) {
				replaceFragment(map.get(item.getItemId()));
				return true;
			}
		}

		return super.onOptionsItemSelected(item);/*
												 * switch (item.getItemId()) {
												 * case R.id.action_compose:
												 * Log.v(DEBUG_TAG,
												 * "action compose was called");
												 * replaceFragment(new
												 * Confessions()); return true;
												 * case
												 * R.id.action_quit_compose:
												 * Log.v(DEBUG_TAG,
												 * "action compose was called");
												 * replaceFragment(new
												 * ViewAllConfessions()); return
												 * true; default: return
												 * super.onOptionsItemSelected
												 * (item); }
												 */
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		// View rootView = super.onCreateView(inflater,
		// container,savedInstanceState);
		initializeValues();
		View rootView = inflater.inflate(rootLayout, container, false);

		
		return rootView;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 super.onActivityResult(requestCode, resultCode, data);
		 
		    // notifying nested fragments (support library bug fix)
		    final FragmentManager childFragmentManager = getChildFragmentManager();
		 
		    if (childFragmentManager != null) {
		        final List<Fragment> nestedFragments = childFragmentManager.getFragments();
		 
		        if (nestedFragments == null || nestedFragments.size() == 0) return;
		 
		        for (Fragment childFragment : nestedFragments) {
		            if (childFragment != null && !childFragment.isDetached() && !childFragment.isRemoving()) {
		            	//need to generate a unique requestCode for each instance of the class. This seems really
		            	//inefficient.
		                childFragment.onActivityResult(requestCode, resultCode, data);
		            }
		        }
		    }
	}
}