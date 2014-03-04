package collegetickr.application;

import android.support.v4.view.ViewPager;


public class Compliments extends ComplimentsConfessionsBaseClass {
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