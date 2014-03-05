package collegetickr.application.Fragments;

import collegetickr.application.ComplimentsConfessionsFragmentBaseClass;
import collegetickr.application.R;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import android.support.v4.view.ViewPager;


public class Compliments extends ComplimentsConfessionsFragmentBaseClass {
	public Compliments(ViewPager mPager) {
		super(mPager);
		// TODO Auto-generated constructor stub
	}

	public Compliments() {
		super();
		// TODO Auto-generated constructor stub
	}

	int rootLayout = R.layout.compliments_layout,
			editText = R.id.editTextCompliments,
			uploadPictureButton = R.id.uploadPictureCompliment,
			submitButton = R.id.submitCompliment, viewContent = R.id.viewCompliments;

	@Override
	protected void initializeValues() {
		super.initializeValues(rootLayout, editText, uploadPictureButton, submitButton, appIdentifier, viewContent);
	}


}