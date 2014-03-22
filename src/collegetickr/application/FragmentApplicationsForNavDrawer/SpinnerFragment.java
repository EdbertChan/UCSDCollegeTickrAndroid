package collegetickr.application.FragmentApplicationsForNavDrawer;

import collegetickr.application.R;
import collegetickr.application.profileLogin.LoginActivity;
import collegetickr.library.IdentifiersList;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class SpinnerFragment extends Fragment{
//	LoginPopupWindow popUp;
    LinearLayout ll;
    private PopupWindow mpopup;
    View layout;
    View popUpView;
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
	
		final View rootView = inflater.inflate(R.layout.spinner_layout, container, false);
		
      
	return rootView;
	}
}