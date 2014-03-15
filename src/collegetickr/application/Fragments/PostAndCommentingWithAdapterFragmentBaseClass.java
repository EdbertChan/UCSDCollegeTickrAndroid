package collegetickr.application.Fragments;
/*Ideally, we'd have this be an abstract class for any and all. all that changes is the initializing fragment*/
//Overall, this is just a class that has an adapter and loads more posts as needed.
//we will abstract this class in the future.
import collegetickr.application.Post.PostFragment;
import collegetickr.library.AndroidAbstractClasses.OnPageSwipeListener;

import collegetickr.library.AndroidAbstractClasses.ViewPagerAdapter;
import collegetickr.library.WebPostGetAsyncTask.AsyncTaskCompleteListener;
import collegetickr.library.WebPostGetAsyncTask.GetDataWebTask;

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

public abstract class PostAndCommentingWithAdapterFragmentBaseClass extends Fragment implements AsyncTaskCompleteListener{

	protected ViewPager mPager;
	ViewPagerAdapter mAdapter; 

	protected int rootLayout, viewPager;
	protected String url;
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
		mPager  = (ViewPager) rootView.findViewById(viewPager);
		mAdapter = new ViewPagerAdapter(getChildFragmentManager());
		mPager.setAdapter(mAdapter);
		
		//mPager.setOnTouchListener(new OnSwipeTouchListener(mPager));
		mPager.setOnPageChangeListener(new OnPageSwipeListener(mPager){

			  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				  float previousPositionOffset = mLastPositionOffset;
				  super.onPageScrolled(position, positionOffset, positionOffsetPixels);  
				  if(positionOffset > previousPositionOffset && positionOffset > 0.1) {
			        	//if we swipe to the right, this will trigger the check
					  checkLoadMore();
			        }
			     
			    }
		});

		return rootView;
	}
	public void addFragmentToAdapter(Fragment fragment){
	//this appears to work. 
		mAdapter.addFragment(fragment);
		mAdapter.notifyDataSetChanged();
		//this will actually set the adapter and bring it back to the front
	//mPager.setAdapter(mAdapter);
	
	}
	private void checkLoadMore(){
		//loadAllthePosts. We write this into the listener.
		
		
		//dummy code to load more w/o making an async call. Testing code
		int numOfFragLeft = mAdapter.getCount() - mPager.getCurrentItem();
		if(numOfFragLeft <= 2){
			addFragmentToAdapter(new PostFragment());
		}
		//new GetDataWebTask(this).execute("http://www.google.com");
		
		
		//Actual Code but in development: checks if we should load more based on the current position.
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
