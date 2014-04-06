package collegetickr.application.Confessions;

import collegetickr.application.R;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import collegetickr.application.SubmitComplimentsConfessions.ComplimentsConfessionsFragmentBaseClass;
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
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class SubmitConfessions extends ComplimentsConfessionsFragmentBaseClass {
	public SubmitConfessions(ViewPager mPager) {
		super(mPager);
		// TODO Auto-generated constructor stub
	}
	public SubmitConfessions() {
		super();
		// TODO Auto-generated constructor stub
	}

	int rootLayout = R.layout.confessions_compose_layout,
			editText = R.id.editTextConfessions,
			uploadPictureButton = R.id.uploadPictureConfession,
			submitButton = R.id.submitConfession,
			viewContent = R.id.viewConfession,
			uploadPicturePreviewID = R.id.confessionImageUploadPreview,
			cancelUploadButtonID = R.id.confessionCancelImageUpload;

	@Override
	protected void initializeValues() {
		super.initializeValues(rootLayout, editText, uploadPictureButton,
				submitButton, appIdentifier, viewContent,
				uploadPicturePreviewID, cancelUploadButtonID);

	}

	//since we put these in an adapter, we need to refresh the pager every time we
	//upload/change the image
}