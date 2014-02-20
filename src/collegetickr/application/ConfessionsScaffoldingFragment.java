package collegetickr.application;

import collegetickr.views.PostDataWebTask;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ConfessionsScaffoldingFragment extends Fragment{
	ViewPager mPager;
	ViewPagerAdapter mAdapter; 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
		
		View rootView = inflater.inflate(R.layout.confessions_scafoold_layout, container, false);
		mPager  = (ViewPager) rootView.findViewById(R.id.pager);
		mAdapter = new ViewPagerAdapter(getChildFragmentManager());
		mAdapter.addFragment(new Confessions());
		mAdapter.addFragment(new Compliments());
		mPager.setAdapter(mAdapter);
		 
		return rootView;
	}
      
}