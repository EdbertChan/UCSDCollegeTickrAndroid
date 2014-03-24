package collegetickr.application.FragmentApplicationsForNavDrawer;

import collegetickr.application.R;
import collegetickr.application.ComplimentsConfessions.Confessions;
import collegetickr.library.AndroidAbstractClasses.ViewPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PopupConfessionFragment extends PopupFragmentWithAdapterBaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		{
			View rootView = inflater.inflate(R.layout.popup_fragment_with_pager_layout,
					container, false);

			mPager = (ViewPager) rootView.findViewById(R.id.popup_pager);
			mAdapter = new ViewPagerAdapter(getChildFragmentManager());

			mAdapter.addFragment(new Confessions());

			mPager.setAdapter(mAdapter);
			return rootView;
		}
	}
	
}