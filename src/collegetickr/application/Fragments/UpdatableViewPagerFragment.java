package collegetickr.application.Fragments;

/*This class is responsible for housing fragments in a viewpager and updating as needed. 
 * It will house a temlplate for the asynctask. The ontaskcomplete can be overridden at any time*/
//Overall, this is just a class that has an adapter and loads more posts as needed.
//we will abstract this class in the future.
import java.util.List;

import collegetickr.application.FragmentApplicationsForNavDrawer.EmptyFragment;
import collegetickr.application.FragmentApplicationsForNavDrawer.SpinnerFragment;
import collegetickr.application.Post.PostFragment;
import collegetickr.library.AndroidAbstractClasses.OnPageSwipeListener;

import collegetickr.library.AndroidAbstractClasses.ViewPagerAdapter;
import collegetickr.library.WebPostGetAsyncTask.AsyncTaskCompleteListener;
import collegetickr.library.WebPostGetAsyncTask.GetDataWebTask;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public abstract class UpdatableViewPagerFragment extends
		Fragment implements AsyncTaskCompleteListener {

	protected ViewPager mPager;
	protected ViewPagerAdapter mAdapter;

	protected int rootLayout, viewPager;
	protected String url;
	private static final String DEBUG_TAG ="UpdatableViewPagerFragment";
	protected abstract void initializeValues();

	protected void initializeValues(int rootLayout, int viewPager, String url) {
		this.rootLayout = rootLayout;
		this.viewPager = viewPager;
		this.url = url;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeValues();
		View rootView = inflater.inflate(rootLayout, container, false);
		mPager = (ViewPager) rootView.findViewById(viewPager);
		mAdapter = new ViewPagerAdapter(getChildFragmentManager());
		mPager.setAdapter(mAdapter);

		// mPager.setOnTouchListener(new OnSwipeTouchListener(mPager));
		mPager.setOnPageChangeListener(new OnPageSwipeListener(mPager) {

			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				float previousPositionOffset = mLastPositionOffset;
				super.onPageScrolled(position, positionOffset,
						positionOffsetPixels);
				if (positionOffset > previousPositionOffset
						&& positionOffset > 0.1) {
					// if we swipe to the right, this will trigger the check
					checkLoadMore();
				}

			}
		});

		return rootView;
	}
	public void addFragment(Fragment f, int position){
		mAdapter.addFragment(f);
		mAdapter.notifyDataSetChanged();
	}
	public void addFragment(Fragment f){
		mAdapter.addFragment(f);
		mAdapter.notifyDataSetChanged();
	}
	public void replaceFragment(int position, Fragment f){
		mAdapter.replaceFragment(position, f);
		mAdapter.notifyDataSetChanged();
	}
	public void deleteFragment(int position){
		mAdapter.deleteFragment(position);
		mAdapter.notifyDataSetChanged();
	}
	public void checkLoadMore() {
		// loadAllthePosts. We write this into the listener.

		// dummy code to load more w/o making an async call. Testing code
		int numOfFragLeft = mAdapter.getCount() - mPager.getCurrentItem();
		Log.v(DEBUG_TAG, "Current Item: " + String.valueOf(mPager.getCurrentItem()));
		Log.v(DEBUG_TAG, "Number of Elements in Adapter: " + String.valueOf(mAdapter.getCount()));
		Log.v(DEBUG_TAG, "Frag left: " + String.valueOf(numOfFragLeft));
		if (numOfFragLeft <= 1) {
			
			new GetDataWebTask(this).execute("http://www.google.com");
		
		}
		// new GetDataWebTask(this).execute("http://www.google.com");

		// Actual Code but in development: checks if we should load more based
		// on the current position.
		/*
		 * int numOfFragLeft = mAdapter.getCount() - mPager.getCurrentItem();
		 * Log.i("hi",String.valueOf(mPager.getCurrentItem())); if(numOfFragLeft
		 * <= JSONHandlerLibrary.numOfPostFetch){ //get more. new
		 * collegetickr.library
		 * .AsyncTasks.GetDataWebTask(getActivity()).execute(
		 * "http://www.google.com"); }
		 */
	}

	// this only exists to handle the string result after we get it from the
	// AsyncTask

	@Override
	public void onTaskComplete(Object result) {
		// TODO Auto-generated method stub
		addFragment(new PostFragment());
		
	}

	public void onSaveInstanceState(Bundle icicle) {
		super.onSaveInstanceState(icicle);
	
	}

	// since nested fragments cannot recieve the activity result, we just call
	// the parent
	// fragment (this) and then try to find out who sent it. This itterates
	// through all the
	// fragments but ideally, we can reduce this code to something better.
	// we could just have a list of requestCodes and put them. We're basically assuming
	// that all our fragments are unique at the moment but this
	// is probably going to change. We need a better implementation of
	// creating and mapping unique requestCodes
	
	//Google pls fix nested fragments issue. 
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
