package collegetickr.application.profileLogin;


import collegetickr.application.R;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.Login.LoginFragment;
import collegetickr.application.Login.RegisterFragment;
import collegetickr.application.MiscPopup.PopupLoginFragment;
import collegetickr.library.AndroidAbstractClasses.ViewPagerAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	TextView linkToRegister, email, loginLink, loginLaterLink, emailLabel, confirmPasswordLabel;
	

	EditText password, confirmPassword;
	Button btnLogin, btnRegister;

	// User Session Manager Class
	UserSessionManager session;
	EditText fullName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity_layout);

		// User Session Manager
		session = new UserSessionManager(getApplicationContext());

		loginLink = (TextView) findViewById(R.id.loginLink);
		confirmPasswordLabel = (TextView) findViewById(R.id.confirmPasswordLabel);
		
		linkToRegister = (TextView) findViewById(R.id.linkToRegister);
		fullName = (EditText) findViewById(R.id.fullName);
		email = (EditText) findViewById(R.id.email);

		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirmPassword);
		btnLogin = (Button) findViewById(R.id.login);
		btnRegister = (Button) findViewById(R.id.register);
		loginLaterLink = (TextView) findViewById(R.id.loginRegisterLater);
		emailLabel =  (TextView) findViewById(R.id.emailLabel);
		
		setUpLoginForm();
		loginLink.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setUpLoginForm();
			}
		});
		linkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setUpRegisterForm();	
				
			}
		});

		
		loginLaterLink.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				returnToMainActivity();	
			}
		});

		// User Login button

		// Login button click event
		/*btnLoginRegisterLater.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
			
			}
		});*/
	}
	private void returnToMainActivity(){
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
         
        finish();
	}

	private void setUpRegisterForm() {
		linkToRegister.setVisibility(View.GONE);
		loginLink.setVisibility(View.VISIBLE);
		email.setVisibility(View.VISIBLE);
		btnLogin.setVisibility(View.GONE);
		btnRegister.setVisibility(View.VISIBLE);
		emailLabel.setVisibility(View.VISIBLE);
		confirmPasswordLabel.setVisibility(View.VISIBLE);
		confirmPassword.setVisibility(View.VISIBLE);
		}
	private void setUpLoginForm() {
		linkToRegister.setVisibility(View.VISIBLE);
		loginLink.setVisibility(View.GONE);
		email.setVisibility(View.GONE);
		btnLogin.setVisibility(View.VISIBLE);
		btnRegister.setVisibility(View.GONE);
		emailLabel.setVisibility(View.GONE);
		confirmPasswordLabel.setVisibility(View.GONE);
		confirmPassword.setVisibility(View.GONE);
	}
/*
	private void beginLogin() {

		// Get username, password from EditText
		String username = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();

		// Validate if username, password is filled
		if (username.trim().length() > 0 && password.trim().length() > 0) {

			// For testing puspose username, password is checked with static
			// data
			// username = admin
			// password = admin

			if (username.equals("admin") && password.equals("admin")) {

				// Creating user login session
				// Statically storing name="Android Example"
				// and email="androidexample84@gmail.com"
				session.createUserLoginSession("Android Example",
						"androidexample84@gmail.com");

				// Starting MainActivity
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				// Add new Flag to start new Activity
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);

				finish();

			} else {

				// username / password doesn't match&
				Toast.makeText(getApplicationContext(),
						"Username/Password is incorrect", Toast.LENGTH_LONG)
						.show();

			}
		} else {

			// user didn't entered username or password
			Toast.makeText(getApplicationContext(),
					"Please enter username and password", Toast.LENGTH_LONG)
					.show();

		}
	}*/
}