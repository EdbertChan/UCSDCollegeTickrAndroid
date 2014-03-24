/*Consists of an edit text box, upload image, submit confession, and view others.
 * Does not consist of an adapter becuase of the possibility of creating infinite
 * instances and crashing.
 */
package collegetickr.application.ComplimentsConfessions;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import collegetickr.application.R;

import collegetickr.application.MiscPopup.ImagePreviewPopupFragment;
import collegetickr.application.MiscPopup.PopupLoginFragment;

import collegetickr.library.IdentifiersList;
import collegetickr.library.WebPostGetAsyncTask.AsyncTaskCompleteListener;
import collegetickr.library.WebPostGetAsyncTask.GetDataWebTask;
import collegetickr.library.WebPostGetAsyncTask.PostDataWebTask;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

public abstract class ComplimentsConfessionsFragmentBaseClass extends Fragment {
	public static final String DEBUG_TAG = "ComplimentsConfessionsFragmentBaseClass";

	protected int rootLayout, editText, uploadPictureButton, submitButtonID,
			viewContent, layoutOfFragment, uploadPicturePreviewID,
			cancelUploadButtonID;
	// this identifier seems obsolete
	protected String appIdentifier;

	protected ViewPager mPager;
	protected String uriOfPicture;

	protected EditText confessionComplimentStringContent;
	protected View rootView;
	protected Button buttonLoadImage, buttonCancelUploadImage;
	protected ImageView uploadImagePreview;
	protected Bitmap imageUploadBitmap;

	protected abstract void initializeValues();

	protected void initializeValues(int prootLayout, int peditText,
			int puploadPictureButton, int psubmitButton, String pappIdentifier,
			int pviewContent, int uploadPicturePreviewID,
			int cancelUploadButtonID) {
		rootLayout = prootLayout;
		editText = peditText;
		uploadPictureButton = puploadPictureButton;
		submitButtonID = psubmitButton;
		appIdentifier = pappIdentifier;
		viewContent = pviewContent;
		this.uploadPicturePreviewID = uploadPicturePreviewID;
		this.cancelUploadButtonID = cancelUploadButtonID;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeValues();

		// Creating all the views
		rootView = inflater.inflate(rootLayout, container, false);
		buttonCancelUploadImage = (Button) rootView
				.findViewById(cancelUploadButtonID);
		uploadImagePreview = (ImageView) rootView
				.findViewById(uploadPicturePreviewID);
		buttonLoadImage = (Button) rootView.findViewById(uploadPictureButton);
		confessionComplimentStringContent = (EditText) rootView
				.findViewById(editText);

		// default visibility and config adjust visibility of the image Preview.

		makeImagePreviewInvisible();

		// make the listeners
		buttonCancelUploadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.v(DEBUG_TAG, "buttonCancelUploadImage clicked");
				imageUploadBitmap = null;

				makeImagePreviewInvisible();
				buttonLoadImage.setText(R.string.uploadImageText);
				if (mPager != null) {
					mPager.getAdapter().notifyDataSetChanged();
				}
			}
		});
		uploadImagePreview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.v(DEBUG_TAG, "uploadImagePreview clicked");
				FragmentManager fragmentManager = getFragmentManager();
				ImagePreviewPopupFragment imagePreviewPopup = new ImagePreviewPopupFragment(
						imageUploadBitmap);
				imagePreviewPopup.show(fragmentManager, "");
				if (mPager != null) {
					mPager.getAdapter().notifyDataSetChanged();
				}
			}
		});

		buttonLoadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.v(DEBUG_TAG, "buttonLoadImage clicked");
				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				// we have to send this to the parent fragment or else
				// onActivityResult
				// will never get called in a nested arrangement
				if (getParentFragment() != null) {
					getParentFragment().startActivityForResult(i,
							IdentifiersList.RESULT_LOAD_IMAGE);
				} else {
					startActivityForResult(i, IdentifiersList.RESULT_LOAD_IMAGE);
				}

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
					// this button has no use. currently it is depreciated and
					// made invisible.
				}
			});
		}

		return rootView;
	}

	public void makeImagePreviewVisible() {
		uploadImagePreview.setVisibility(View.VISIBLE);
		buttonCancelUploadImage.setVisibility(View.VISIBLE);
	}

	public void makeImagePreviewInvisible() {
		uploadImagePreview.setVisibility(View.INVISIBLE);
		buttonCancelUploadImage.setVisibility(View.INVISIBLE);
	}

	public String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = this.getActivity().managedQuery(contentUri, proj, null,
				null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		Log.i(DEBUG_TAG, "getRealPath: " + cursor.getString(column_index));
		return cursor.getString(column_index);
	}

	public void changeImagePreview(Uri selectedImageURI) {
		Log.v(DEBUG_TAG, "changeImagePreview called");

		// test to see if the user has deleted the file in between?

		try {
			Log.v(DEBUG_TAG, "File found. Now changing image.");
			InputStream is = getActivity().getContentResolver()
					.openInputStream(selectedImageURI);
			imageUploadBitmap = BitmapFactory.decodeStream(is);
			is.close();
			uploadImagePreview.setImageBitmap(imageUploadBitmap);
			makeImagePreviewVisible();
			buttonLoadImage.setText(R.string.changeUploadImageText);
			if (mPager != null) {

				mPager.getAdapter().notifyDataSetChanged();
			} else {
				Log.i(DEBUG_TAG, "mPager is null");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.confession_compose_action_bar_option, menu);

	}

	// in a nested fragment arrangement like what we have, this would never get
	// called
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == IdentifiersList.RESULT_LOAD_IMAGE
				&& resultCode == Activity.RESULT_OK && null != data) {
			Uri selectedImageURI = data.getData();
			uriOfPicture = selectedImageURI.toString();
			changeImagePreview(selectedImageURI);
		}

	}

	@Override
	public void onStop() {
		super.onStop();
		// Log.i("called onStop", "colled onStop");
		Log.v(DEBUG_TAG, "onStop()  called");
	}

	public ComplimentsConfessionsFragmentBaseClass() {

		this.mPager = null;
		Log.v(DEBUG_TAG, "ComplimentsConfessionsFragmentBaseClass()  called");
	}

	public ComplimentsConfessionsFragmentBaseClass(ViewPager mPager) {

		this.mPager = mPager;
		Log.v(DEBUG_TAG,
				"ComplimentsConfessionsFragmentBaseClass(ViewPager mPager)  called");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.v(DEBUG_TAG, "onPause called");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// we want to save our edit text stuff, visibility of the previews, and
		// image
		outState.putString(IdentifiersList.EDIT_TEXT_BUNDLE_TAG,
				confessionComplimentStringContent.getText().toString());
		outState.putString(IdentifiersList.IMAGE_PREVIEW_BUNDLE_TAG,
				uriOfPicture);
		// we basically assume that the cancelUploadImage and the preview are
		// the same but to be safe, we will pass both
		/*
		 * outState.putInt(
		 * IdentifiersList.EXIT_IMAGE_PREVIEW_BUTTON_VISIBILITY_BUNDLE_TAG,
		 * buttonCancelUploadImage.getVisibility()); outState.putInt(
		 * IdentifiersList.IMAGE_PREVIEW_BUTTON_VISIBILITY_BUNDLE_TAG,
		 * uploadImagePreview.getVisibility());
		 */
		Log.v(DEBUG_TAG, "onSaveInstanceState called");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Log.v(DEBUG_TAG, "onActivityCreated called");
		// we restore things here. This is called after onCreateView()
		if (savedInstanceState != null) {
			Log.i(DEBUG_TAG, "restoring from previous instance");

			String currentProgress = savedInstanceState
					.getString(IdentifiersList.EDIT_TEXT_BUNDLE_TAG);
			confessionComplimentStringContent.setText(currentProgress);

			uriOfPicture = savedInstanceState
					.getString(IdentifiersList.IMAGE_PREVIEW_BUNDLE_TAG);
			// try to upload the image to imagePreview now
			if (uriOfPicture != null)
				changeImagePreview(Uri.parse(uriOfPicture));

		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

}
