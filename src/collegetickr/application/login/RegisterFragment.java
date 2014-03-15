package collegetickr.application.Login;


import collegetickr.application.R;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.MiscPopup.PopupLoginFragment;
import collegetickr.library.IdentifiersList;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegisterFragment extends Fragment {
	View rootView ;
	TextView loginLink, loginLaterLink;
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 rootView = inflater.inflate(R.layout.register, container, false);
		
	        
	         loginLink = (TextView) rootView.findViewById(R.id.link_to_login);
	    	loginLaterLink = (TextView) rootView
					.findViewById(R.id.register_later_link);
	        loginLink.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// by default, we set it to change a fragment. We fully expect
					// this to be an abstract and to be change
					// at some point
					// furthermore, this has a memory leak because it keeps trying
					// to create a new fragment
					
					PopupLoginFragment.setViewPagerPosition(PopupLoginFragment.getmPager().getCurrentItem()-1);
				}
			});
	        loginLaterLink.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
		
					PopupLoginFragment.showDialog().dismiss();
				}
			});
	        return rootView;
	}
   
}