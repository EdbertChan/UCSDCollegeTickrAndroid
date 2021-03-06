package collegetickr.library.AbstractFragments;
/*for now we assume that login is the only thing we need the popupFragment for*/


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import collegetickr.application.R;
import collegetickr.application.Confessions.SubmitConfessions;

import collegetickr.library.ListenersAdapters.ViewPagerAdapter;

/* calling:
 *  FragmentManager fragmentManager = getFragmentManager();
 PopupLoginFragment editNameDialog = new PopupLoginFragment();
 editNameDialog.show(fragmentManager, "fragment edit name");
 */
public abstract class PopupFragmentWithAdapterBaseFragment extends DialogFragment {
	protected static ViewPager mPager;
	protected static ViewPagerAdapter mAdapter;
	static Dialog dialog;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public static Dialog showDialog() {
		return dialog;
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {

		// the content
		final RelativeLayout root = new RelativeLayout(getActivity());
		root.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		// creating the fullscreen dialog
		dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(root);
		
		dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		  dialog.getWindow().getAttributes().windowAnimations = R.style.AnimationPopup;
		return dialog;
	}

	public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);

	public static ViewPager getmPager() {
		return mPager;
	}

	public static void setViewPagerPosition(int i) {
		mPager.setCurrentItem(i);

		mAdapter.notifyDataSetChanged();
	}

}