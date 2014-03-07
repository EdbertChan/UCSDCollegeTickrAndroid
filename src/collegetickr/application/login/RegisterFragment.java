package collegetickr.application.login;


import collegetickr.application.MainActivity;
import collegetickr.application.R;
import collegetickr.library.IdentifiersList;
import android.app.Activity;
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
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 rootView = inflater.inflate(R.layout.register, container, false);
		
	        
	        TextView loginScreen = (TextView) rootView.findViewById(R.id.link_to_login);
	        
	        // Listening to Login Screen link
	        loginScreen.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View arg0) {
					// Switching to Login Screen/closing register screen
					//finish();
					//((MainActivity) getActivity()).onNavItemSelected(IdentifiersList.loginNumericID);
					((MainActivity) getActivity()).replaceFragment(new LoginFragment());
					//RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.fullRegisterForm);
					 //rl.removeAllViews();
					
					 //  rl.addView(View.inflate(container.getContext(), R.layout.login, null));
					//((MainActivity) getActivity()).selectItem(2);
				}
			});
	        return rootView;
	}
   
}