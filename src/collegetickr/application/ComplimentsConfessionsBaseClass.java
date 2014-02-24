/*Consists of an edit text box, upload image, submit confession, and view others.
 * Does not consist of an adapter becuase of the possibility of creating infinite
 * instances and crashing.
 */
package collegetickr.application;


import collegetickr.library.AsyncTasks.PostDataWebTask;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public abstract class ComplimentsConfessionsBaseClass extends Fragment {
	private static int RESULT_LOAD_IMAGE = 1;
	private String selectedImagePath = "";
	protected int rootLayout, editText, uploadPictureButton, submitButton;
	protected String appIdentifier;

	protected abstract void initializeValues();

	protected void initializeValues(int prootLayout, int peditText,
			int puploadPictureButton, int psubmitButton, String pappIdentifier) {
		rootLayout = prootLayout;
		editText = peditText;
		uploadPictureButton = puploadPictureButton;
		submitButton = psubmitButton;
		appIdentifier = pappIdentifier;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initializeValues();
		
		View rootView = inflater.inflate(rootLayout, container, false);

		final EditText edit_text = (EditText) rootView.findViewById(editText);

		
		Button buttonLoadImage = (Button) rootView
				.findViewById(uploadPictureButton);
		buttonLoadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
		

		return rootView;
	}

	// Gets the string of the path to the picture
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			Uri selectedImageUri = data.getData();
			selectedImagePath = selectedImageUri.getPath();
			// note: might want to change icon to let user know we have
			// selected?
			// set to change image and display small icon underneath?
		}
	}

}
