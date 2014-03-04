package collegetickr.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ViewFlipper;


public class Confessions extends ComplimentsConfessionsBaseClass {
	public Confessions(ViewPager mPager) {
		super(mPager);
		// TODO Auto-generated constructor stub
	}

	int rootLayout = R.layout.confessions_layout,
			editText = R.id.editTextConfessions,
			uploadPictureButton = R.id.uploadPictureConfession,
			submitButton = R.id.submitConfession, viewContent = R.id.viewConfession;

	@Override
	protected void initializeValues() {
		super.initializeValues(rootLayout, editText, uploadPictureButton, submitButton, appIdentifier, viewContent);
		
	}
	

}