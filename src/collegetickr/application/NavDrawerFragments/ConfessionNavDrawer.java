package collegetickr.application.NavDrawerFragments;

import collegetickr.application.PostAndCommentingWithAdapterFragmentBaseClass;
import collegetickr.application.R;
import collegetickr.application.Fragments.Compliments;
import collegetickr.application.Fragments.Confessions;
import collegetickr.application.Post.PostFragment;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConfessionNavDrawer extends PostAndCommentingWithAdapterFragmentBaseClass{
int rootLayout = R.layout.confessions_scaffold_layout;
int viewPager = R.id.pager;
String url = "";
	@Override
	protected void initializeValues() {
		// TODO Auto-generated method stub
		super.initializeValues(rootLayout, viewPager, url);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = super.onCreateView( inflater,  container, savedInstanceState);

		
	//	View rootView = inflater.inflate(rootLayout, container, false);
		mPager  = (ViewPager) rootView.findViewById(viewPager);
		
		
		//initialize the adapter for whatever testing purposes. This code gets taken out later.
		addFragmentToAdapter(new Confessions(mPager));
		addFragmentToAdapter(new Compliments(mPager));
			addFragmentToAdapter(new PostFragment());
			addFragmentToAdapter(new PostFragment());
		return rootView;
	}
}