package collegetickr.application.FragmentApplicationsForNavDrawer;

import collegetickr.application.R;
import collegetickr.application.Fragments.Compliments;
import collegetickr.application.Fragments.Confessions;
import collegetickr.application.Fragments.UpdatableViewPagerFragment;
import collegetickr.application.Post.PostFragment;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import collegetickr.library.AndroidAbstractClasses.ViewPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ConfessionNavDrawer extends
		UpdatableViewPagerFragment {
	int rootLayout = R.layout.confessions_scaffold_layout;
	int viewPager = R.id.pager;
	String url = "";
	SpinnerFragment sf= new SpinnerFragment();

	@Override
	protected void initializeValues() {
		// TODO Auto-generated method stub
		super.initializeValues(rootLayout, viewPager, url);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = super.onCreateView(inflater, container,
				savedInstanceState);

		// View rootView = inflater.inflate(rootLayout, container, false);
		mPager = (ViewPager) rootView.findViewById(viewPager);

		// initialize the adapter for whatever testing purposes. This code gets
		// taken out later.
		addFragment(new Confessions(mPager));
		//addPostFragment( ViewPagerAdapter.getMfragments().size(),new PostFragment());
		addFragment(new PostFragment());
		addFragment(sf);
		return rootView;
	}
	@Override
	public void onTaskComplete(Object result) {
		// TODO Auto-generated method stub
		//we assume this to be at the end
		int i = mPager.getAdapter().getItemPosition(sf);
		replaceFragment(i, new PostFragment());
		addFragment(sf);
		
	}
}