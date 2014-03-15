/*Consists of an edit text box, upload image, submit confession, and view others.
 * Does not consist of an adapter becuase of the possibility of creating infinite
 * instances and crashing.
 */
package collegetickr.application.Fragments;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import collegetickr.application.R;

import collegetickr.application.MiscPopup.ImagePreviewPopupFragment;
import collegetickr.application.MiscPopup.PopupLoginFragment;
import collegetickr.library.IdentifiersList;
import collegetickr.library.WebPostGetAsyncTask.PostDataWebTask;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public abstract class ComplimentsConfessionsFragmentBaseClass extends Fragment {
	private static int RESULT_LOAD_IMAGE = 1;
	private String selectedImagePath = "";
	protected int rootLayout, editText, uploadPictureButton, submitButtonID,
			viewContent, layoutOfFragment, uploadPicturePreviewID, cancelUploadButtonID;
	protected String appIdentifier;
	private ViewPager mPager;
	//Bitmap bitmapOfUploadImage;
	String uriOfPicture;
	// FrameLayout layout_MainMenu;
	// this is a flag that we will use to allow users to cancel uploading. We
	// will give them a popup window
	// teling them that they can either go ahead and upload or not.
	boolean uploadedImage = false;
	View rootView;
	Button buttonLoadImage, buttonCancelUploadImage;
	ImageView uploadImagePreview;
	Bitmap imageUploadBitmap;
	protected abstract void initializeValues();

	protected void initializeValues(int prootLayout, int peditText,
			int puploadPictureButton, int psubmitButton, String pappIdentifier,
			int pviewContent, int uploadPicturePreviewID, int cancelUploadButtonID) {
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
		rootView = inflater.inflate(rootLayout, container, false);

		buttonCancelUploadImage = (Button) rootView.findViewById(cancelUploadButtonID);
		
		buttonCancelUploadImage.setVisibility(View.INVISIBLE);
		uploadImagePreview = (ImageView) rootView.findViewById(uploadPicturePreviewID);
		uploadImagePreview.setVisibility(View.INVISIBLE);
		
		buttonCancelUploadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				uploadedImage = false;
				buttonCancelUploadImage.setVisibility(View.INVISIBLE);
				uploadImagePreview.setVisibility(View.INVISIBLE);
				buttonLoadImage.setText(R.string.uploadImageText);
			}
		});
		uploadImagePreview.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();
				
			ImagePreviewPopupFragment editNameDialog = new ImagePreviewPopupFragment(imageUploadBitmap);
			 editNameDialog.show(fragmentManager, "fragment edit name");
				
			}
		});
		
		
		buttonLoadImage = (Button) rootView.findViewById(uploadPictureButton);

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
					// note: we will also need to call getting the confessions
					// list. This code is incomplete

					mPager.setCurrentItem(mPager.getCurrentItem() + 1);

				}
			});
		}

		return rootView;
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	//	super.onActivityResult(requestCode, resultCode, data);
		/* if (resultCode == Activity.RESULT_OK && requestCode == 1 && null != data) {
		        decodeUri(data.getData());
		      
		    }*/
		 if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data){

			 Uri selectedImageURI = data.getData();
				//File imageFile = new File(getRealPathFromURI(selectedImageURI));
				
				try {
					InputStream is = getActivity().getContentResolver().openInputStream(selectedImageURI);
					 imageUploadBitmap = BitmapFactory.decodeStream(is);
					is.close();
					   uploadImagePreview.setImageBitmap(imageUploadBitmap);
				        uploadImagePreview.setVisibility(View.VISIBLE);
						buttonCancelUploadImage.setVisibility(View.VISIBLE);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		

	}
	 
	public void setThumbnailPreview(Bitmap image){
		uploadImagePreview.setImageBitmap(image);
	}
	@Override
	public void onStop() {
		super.onStop();
		// Log.i("called onStop", "colled onStop");
	}
	public ComplimentsConfessionsFragmentBaseClass() {

		this.mPager = null;

	}

	public ComplimentsConfessionsFragmentBaseClass(ViewPager mPager) {

		this.mPager = mPager;
	}
	// Gets the string of the path to the picture
		public void decodeUri(Uri uri) {
		    ParcelFileDescriptor parcelFD = null;
		    try {
		        parcelFD = getActivity().getContentResolver().openFileDescriptor(uri, "r");
		        FileDescriptor imageSource = parcelFD.getFileDescriptor();

		        // Decode image size
		        BitmapFactory.Options o = new BitmapFactory.Options();
		        o.inJustDecodeBounds = true;
		        BitmapFactory.decodeFileDescriptor(imageSource, null, o);

		        // the new size we want to scale to
		        final int REQUIRED_SIZE = 1024;

		        // Find the correct scale value. It should be the power of 2.
		        int width_tmp = o.outWidth, height_tmp = o.outHeight;
		        int scale = 1;
		        while (true) {
		            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
		                break;
		            }
		            width_tmp /= 2;
		            height_tmp /= 2;
		            scale *= 2;
		        }

		        // decode with inSampleSize
		        BitmapFactory.Options o2 = new BitmapFactory.Options();
		        o2.inSampleSize = scale;
		        Bitmap thumbnail = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

		        uploadImagePreview.setImageBitmap(thumbnail);
		        uploadImagePreview.setVisibility(View.VISIBLE);
				buttonCancelUploadImage.setVisibility(View.VISIBLE);
		    } catch (FileNotFoundException e) {
		        // handle errors
		    }}
}
