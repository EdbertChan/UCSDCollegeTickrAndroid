/*Consists of an edit text box, upload image, submit confession, and view others.
 * Does not consist of an adapter becuase of the possibility of creating infinite
 * instances and crashing.
 */
package collegetickr.application;

import collegetickr.library.WebPostGetAsyncTask.PostDataWebTask;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.MediaStore;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public abstract class ComplimentsConfessionsFragmentBaseClass extends Fragment {
	private static int RESULT_LOAD_IMAGE = 1;
	private String selectedImagePath = "";
	protected int rootLayout, editText, uploadPictureButton, submitButton,
			viewContent;
	protected String appIdentifier;
	private ViewPager mPager;

	protected abstract void initializeValues();

	protected void initializeValues(int prootLayout, int peditText,
			int puploadPictureButton, int psubmitButton, String pappIdentifier,
			int pviewContent) {
		rootLayout = prootLayout;
		editText = peditText;
		uploadPictureButton = puploadPictureButton;
		submitButton = psubmitButton;
		appIdentifier = pappIdentifier;
		viewContent = pviewContent;
	}

	public ComplimentsConfessionsFragmentBaseClass() {

		this.mPager = null;

	}

	public ComplimentsConfessionsFragmentBaseClass(ViewPager mPager) {

		this.mPager = mPager;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeValues();

		View rootView = inflater.inflate(rootLayout, container, false);

		final EditText edit_text = (EditText) rootView.findViewById(editText);

		/*
		 * InputMethodManager inputMethodManager = (InputMethodManager)
		 * ((Activity) context).getSystemService(Activity.INPUT_METHOD_SERVICE);
		 * inputMethodManager.hideSoftInputFromWindow( ((Activity)
		 * context).getCurrentFocus().getWindowToken(), 0);
		 */

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
		// this is how we change on "view compliments/view confessions". We just
		// go
		// to the next item. We assume the posts immediately follow the
		// submit confessions/compliments. This is something that we should
		// probably change in the future (eg scan until we find an instance of a
		// post fragment but we will discuss this).
		if (mPager != null) {
			Button buttonViewContent = (Button) rootView
					.findViewById(viewContent);
			buttonViewContent.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {

					mPager.setCurrentItem(mPager.getCurrentItem() + 1);

				}
			});
		}

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

	@Override
	public void onStop() {
		super.onStop();
		Log.i("called onStop", "colled onStop");
	}
}
