package collegetickr.application;

import info.androidhive.tabsswipe.R;



public class Confessions extends ApplicationBaseClass {
	int rootLayout = R.layout.confessions_layout,
			editText = R.id.editTextConfessions,
			uploadPictureButton = R.id.uploadPictureConfession,
			submitButton = R.id.submitConfession;

	@Override
	protected void initializeValues() {
		super.initializeValues(rootLayout, editText, uploadPictureButton, submitButton, appIdentifier);
	}


}