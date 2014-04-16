package collegetickr.application.FragmentApplicationsForNavDrawer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import collegetickr.application.R;
import collegetickr.application.Confessions.SubmitConfessions;
import collegetickr.application.Confessions.ViewAllConfessions;
import collegetickr.application.Confessions.LazyConfessionsAdapter.LazyConfessionsPostAdapter;
import collegetickr.application.Post.Post;
import collegetickr.application.SubmitComplimentsConfessions.Compliments;

import collegetickr.library.IdentifiersList;
import collegetickr.library.JSONHandlerLibrary;
import collegetickr.library.AbstractFragments.NavDrawerContainerSwapFragmentsViaActionBarTemplate;
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

public class ConfessionNavDrawer extends NavDrawerContainerSwapFragmentsViaActionBarTemplate{

	public static final String MAP_ID = "MAP_ID";
	
	public static final String DEBUG_TAG = "ConfessionNavDrawer";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = super.onCreateView(inflater, container, savedInstanceState);


		replaceFragment(map.get(new Integer(R.id.action_quit_compose)));
		return rootView;
	}
	@Override
	protected void setMap() {
		// TODO Auto-generated method stub
		super.initializeValues(R.layout.compliments_confessions_container_layout, R.id.compliments_confessions_container_layout);
		this.map = new HashMap<Integer, Fragment>();
		map.put(new Integer(R.id.action_compose), new SubmitConfessions());
		map.put(new Integer(R.id.action_quit_compose), new ViewAllConfessions());
		
		Log.v(DEBUG_TAG, "initalizeValues");
	}

	
}