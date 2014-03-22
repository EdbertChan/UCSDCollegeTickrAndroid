package collegetickr.application.profileLogin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment{
	 UserSessionManager session;
	 private static final String DEBUG_TAG = "ProfileFragment";
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
	      session = new UserSessionManager(getActivity().getApplicationContext());
          
		if(session.checkLogin())
            Log.i(DEBUG_TAG, "LoginSuccessful");
		return null;
         
	}
}