package collegetickr.application.Login;

import collegetickr.application.R;
import collegetickr.application.MiscPopup.PopupLoginFragment;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

public class LoginFragment extends Fragment {
	View loginRootView;
	Button loginButton;
	EditText nameField;
	Fragment callingFragment;
	TextView nameLabel, linkToRegister, loginLaterLink;

	@Override
	public View onCreateView(final LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loginRootView = inflater.inflate(R.layout.login, container, false);

		linkToRegister = (TextView) loginRootView
				.findViewById(R.id.link_to_register);
		loginLaterLink = (TextView) loginRootView
				.findViewById(R.id.login_later_link);
		loginButton = (Button) loginRootView.findViewById(R.id.btnLogin);
		nameField = (EditText) loginRootView.findViewById(R.id.reg_fullname);

		// Listening to register new account link

		loginButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				PopupLoginFragment.showDialog().dismiss();

			}
		});

		linkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// by default, we set it to change a fragment. We fully expect
				// this to be an abstract and to be change
				// at some point
				// furthermore, this has a memory leak because it keeps trying
				// to create a new fragment

				PopupLoginFragment.setViewPagerPosition(PopupLoginFragment
						.getmPager().getCurrentItem() + 1);
			}
		});
		loginLaterLink.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
		
				PopupLoginFragment.showDialog().dismiss();

			}
		});


		return loginRootView;
	}

	@Override
	public void onStop() {
		super.onStop();

	}
}