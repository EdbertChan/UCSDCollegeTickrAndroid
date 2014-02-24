package collegetickr.application;
/*Ideally, we'd have this be an abstract class for any and all. all that changes is the initializing fragment*/

import collegetickr.library.JSONHandlerLibrary;

import collegetickr.library.OnPageSwipeListener;
import collegetickr.library.OnSwipeTouchListener;

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

public class ConfessionsScaffoldingFragment extends Fragment{
//	private GestureDetector gesturedetector = null;
	ViewPager mPager;
	ViewPagerAdapter mAdapter; 
	

	    
	    //View.OnTouchListener gestureListener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       
		View rootView = inflater.inflate(R.layout.confessions_scafoold_layout, container, false);
		mPager  = (ViewPager) rootView.findViewById(R.id.pager);
		mAdapter = new ViewPagerAdapter(getChildFragmentManager());
		//this Confessions() is primarily what will change between classes
		mAdapter.addFragment(new Confessions());
		/*mAdapter.addFragment(new Compliments());*/
		mAdapter.addFragment(new Confessions());
		mAdapter.addFragment(new Compliments());
		mAdapter.addFragment(new PostFragment());
		mAdapter.addFragment(new PostFragment());
		mPager.setAdapter(mAdapter);
		
	
		mPager.setOnTouchListener(new OnSwipeTouchListener(mPager));
		  
		return rootView;
	}
	private void checkLoadMore(){
		//checks if we should load more based on the current position.
		int numOfFragLeft = mAdapter.getCount() - mPager.getCurrentItem();
				Log.i("hi",String.valueOf(mPager.getCurrentItem()));
		if(numOfFragLeft <= JSONHandlerLibrary.numOfPostFetch){
			//get more.
			new collegetickr.library.AsyncTasks.GetDataWebTask(getActivity()).execute("http://www.google.com");
		}
	}
	//this only exists to handle the string result after we get it from the AsyncTask
	public class GetDataTaskCompleteListener implements collegetickr.library.AsyncTasks.AsyncTaskCompleteListener<String>
    {
 
        @Override
        public void onTaskComplete(String result)
        {
        	//take an arraylist of Posts, convert into an arraylist of Fragments, then merge.
        	//convertStringtoArrayListPosts(result);
        	Log.i("end","end");
        }
    }

}
