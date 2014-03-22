package collegetickr.application.FragmentApplicationsForNavDrawer;

import collegetickr.application.R;
import collegetickr.application.MiscPopup.PopupLoginFragment;
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

public class EmptyFragment extends Fragment{
//	LoginPopupWindow popUp;
    LinearLayout ll;
    private PopupWindow mpopup;
    View layout;
    View popUpView;
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
	
		final View rootView = inflater.inflate(R.layout.empty_button_layout, container, false);
		  popUpView = inflater.inflate(R.layout.login_register_layout, null); // inflating popup layout
      //   mpopup = new LoginPopupWindow(popUpView, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true); //Creation of popup
 
  // Displaying popup
         
         Button btnOk = (Button)rootView.findViewById(R.id.empty_button);
         btnOk.setOnClickListener(new OnClickListener() 
         {                    
             @Override
             public void onClick(View v) 
             {
                /* mpopup.setAnimationStyle(android.R.style.Animation_Dialog);   
                 mpopup.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0);*/
            	 
                 /*FragmentManager fragmentManager = getFragmentManager();
                 PopupLoginFragment editNameDialog = new PopupLoginFragment();
                 editNameDialog.show(fragmentManager, IdentifiersList.POPUP_LOGIN_FRAGMENT_TAG);*/
            	// After logout redirect user to Login Activity
               
             }
         });
	return rootView;
	}
}