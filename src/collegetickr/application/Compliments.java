package collegetickr.application;


public class Compliments extends ComplimentsConfessionsBaseClass {
	int rootLayout = R.layout.compliments_layout,
			editText = R.id.editTextCompliments,
			uploadPictureButton = R.id.uploadPictureCompliment,
			submitButton = R.id.submitCompliment;

	@Override
	protected void initializeValues() {
		super.initializeValues(rootLayout, editText, uploadPictureButton, submitButton, appIdentifier);
	}


}