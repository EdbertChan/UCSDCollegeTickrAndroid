package collegetickr.application;
/*Ideally, we'd have this be an abstract class for any and all. all that changes is the initializing fragment*/
//Overall, this is just a class that has an adapter and loads more posts as needed.
//we will abstract this class in the future.
import collegetickr.library.JSONHandlerLibrary;

import collegetickr.library.OnPageSwipeListener;
import collegetickr.library.OnSwipeTouchListener;
import collegetickr.library.AsyncTasks.AsyncTaskCompleteListener;
import collegetickr.library.AsyncTasks.GetDataWebTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class ConfessionsApplicationFragment extends Fragment implements AsyncTaskCompleteListener{
//	private GestureDetector gesturedetector = null;
	ViewPager mPager;
	ViewPagerAdapter mAdapter; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       
		
		View rootView = inflater.inflate(R.layout.confessions_scaffold_layout, container, false);
		mPager  = (ViewPager) rootView.findViewById(R.id.pager);
		mAdapter = new ViewPagerAdapter(getChildFragmentManager());
		mPager.setAdapter(mAdapter);
		
		/*mAdapter.addFragment(new Compliments());*/
		mAdapter.addFragment(new Confessions(mPager));
		mAdapter.addFragment(new Compliments(mPager));
		mAdapter.addFragment(new PostFragment());
		mAdapter.addFragment(new PostFragment());
		
	
		//mPager.setOnTouchListener(new OnSwipeTouchListener(mPager));
		mPager.setOnPageChangeListener(new OnPageSwipeListener(mPager));
		checkLoadMore();
		//start the load into mAdapter. Launch an Asynctask. We will
		//change this logic once the API changes but for now we load all
		//the posts
		return rootView;
	}
	private void checkLoadMore(){
		//loadAllthePosts
		new GetDataWebTask(this).execute("http://www.google.com");
		//checks if we should load more based on the current position.
		/*int numOfFragLeft = mAdapter.getCount() - mPager.getCurrentItem();
				Log.i("hi",String.valueOf(mPager.getCurrentItem()));
		if(numOfFragLeft <= JSONHandlerLibrary.numOfPostFetch){
			//get more.
			new collegetickr.library.AsyncTasks.GetDataWebTask(getActivity()).execute("http://www.google.com");
		}*/
	}
	//this only exists to handle the string result after we get it from the AsyncTask

	@Override
	public void onTaskComplete(Object result) {
		// TODO Auto-generated method stub
		Log.i("done", "done");
	}
	public void onSaveInstanceState(Bundle icicle) {
		  super.onSaveInstanceState(icicle);
		  Log.i("saving", "saving");
		}
	
}
