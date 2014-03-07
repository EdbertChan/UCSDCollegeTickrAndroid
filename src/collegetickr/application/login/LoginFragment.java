package collegetickr.application.login;


import collegetickr.application.MainActivity;
import collegetickr.application.R;
import collegetickr.library.IdentifiersList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginFragment extends Fragment {
	View loginRootView;
	Button registerButton, loginButton;
	EditText name;
	 TextView nameLabel, loginScreen, registerScreen;
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 loginRootView = inflater.inflate(R.layout.login, container, false);
		  
	       
	         registerScreen = (TextView) loginRootView.findViewById(R.id.link_to_register);
	         registerButton = (Button) loginRootView.findViewById(R.id.btnRegister);
	         loginButton = (Button) loginRootView.findViewById(R.id.btnLogin);
	         name = (EditText) loginRootView.findViewById(R.id.reg_fullname);
	        // nameLabel = (TextView)rootView.findViewById(R.id.reg_fullname_label);
	       
	        		 loginScreen = (TextView)loginRootView.findViewById(R.id.link_to_login);
	        // Listening to register new account link
	        registerScreen.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
				//seems to create instance every time. This is technically a memory leak.
					
					((MainActivity) getActivity()).replaceFragment(new RegisterFragment());
					
						}
			});
	        return loginRootView;
	}
	@Override
	public void onStop() {
		super.onStop();
		
	}
}