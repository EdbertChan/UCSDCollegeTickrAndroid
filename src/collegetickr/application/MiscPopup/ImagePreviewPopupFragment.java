/*we can use this to preview multiple images*/
package collegetickr.application.MiscPopup;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import collegetickr.application.R;

import collegetickr.library.HUD;
import collegetickr.library.ListenersAdapters.ViewPagerAdapter;

/* calling:
 *  FragmentManager fragmentManager = getFragmentManager();
 PopupLoginFragment editNameDialog = new PopupLoginFragment();
 editNameDialog.show(fragmentManager, "fragment edit name");
 */
public class ImagePreviewPopupFragment extends DialogFragment {
	public static final String DEBUG_TAG = "ImagePreviewPopupFragment";
	static Dialog dialog;
	Bitmap bitmapImage;
	PhotoViewAttacher mAttacher;
	ImageView img;
	Button exitImagePreview;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public ImagePreviewPopupFragment(Bitmap bitmapImage) {
		this.bitmapImage = bitmapImage;
	}

	public static Dialog showDialog() {
		return dialog;
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {

		// the content
		final RelativeLayout root = new RelativeLayout(getActivity());
		root.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		// creating the fullscreen dialog
		dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(root);
		dialog.getWindow()
				.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
		dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		dialog.getWindow().getAttributes().windowAnimations = R.style.AnimationPopup;
		return dialog;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 Log.v(DEBUG_TAG, "onCreateView");
		// super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.image_preview_popup_layout,
				container, false);
		img = (ImageView) rootView.findViewById(R.id.imagePreview);
		img.setImageBitmap(bitmapImage);

		// Attach a PhotoViewAttacher, which takes care of all of the zooming
		// functionality.
		mAttacher = new PhotoViewAttacher(img);

		// set it false for now until we can fix the button issue (button does
		// not dissapear on zoom in)
		// we also need to figure out how to double tap to zoom out

		mAttacher.setZoomable(true);
		
		
		//getActivity().startService(new Intent(getActivity(),HUD.class));
		//stopService(new Intent(this, BackgroundMusicService.class));
		/*mAttacher.setOnViewTapListener(new OnViewTapListener() {

			@Override
			public void onViewTap(View view, float x, float y) {
				// TODO Auto-generated method stub
				// mAttacher.
				if (exitImagePreview.getVisibility() == View.GONE) {
					exitImagePreview.setVisibility(View.VISIBLE);
				} else if (exitImagePreview.getVisibility() == View.VISIBLE) {

					if (x >= (exitImagePreview.getX()
							- (exitImagePreview.getWidth() / 2) - 25)
							&& x <= (exitImagePreview.getX()
									+ (exitImagePreview.getWidth() / 2) + 25)) {
						if (y >= (exitImagePreview.getY()
								- (exitImagePreview.getHeight() / 2) - 25)
								&& y <= (exitImagePreview.getY()
										+ (exitImagePreview.getHeight() / 2) + 25)) {
							// Log.i("touch a button", "touched");
							dialog.dismiss();
						}
					}
					exitImagePreview.setVisibility(View.GONE);
				}
			}

		});*/

	/*exitImagePreview = (Button) rootView
			.findViewById(R.id.exitImagePreview);

		exitImagePreview.setVisibility(View.GONE);*/
		return rootView;
	}

	private class PhotoTapListener implements OnPhotoTapListener {

		@Override
		public void onPhotoTap(View view, float x, float y) {
			if (exitImagePreview.getVisibility() == View.GONE) {
				exitImagePreview.setVisibility(View.VISIBLE);
			} else if (exitImagePreview.getVisibility() == View.VISIBLE) {
				exitImagePreview.setVisibility(View.GONE);
			}
		}
	}
}