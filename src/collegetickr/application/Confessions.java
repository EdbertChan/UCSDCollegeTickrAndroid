package collegetickr.application;

import com.example.android.navigationdrawerexample.R;

public class Confessions extends ComplimentsConfessionsBaseClass {
	int rootLayout = R.layout.confessions_layout,
			editText = R.id.editTextConfessions,
			uploadPictureButton = R.id.uploadPictureConfession,
			submitButton = R.id.submitConfession;

	@Override
	protected void initializeValues() {
		super.initializeValues(rootLayout, editText, uploadPictureButton, submitButton, appIdentifier);
	}


}