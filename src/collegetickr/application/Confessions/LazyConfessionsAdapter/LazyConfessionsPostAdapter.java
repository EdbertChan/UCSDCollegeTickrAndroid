package collegetickr.application.Confessions.LazyConfessionsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.michenux.drodrolib.db.utils.CursorUtils;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import collegetickr.application.R;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsDatabaseHelper;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsTrackExpandedTable;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.Post.Post;
import collegetickr.application.Post.PostWrapperForExpandableTextView;

import collegetickr.library.ApplicationSettings;
import collegetickr.library.ExpandableTextView;
import collegetickr.library.IdentifiersList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LazyConfessionsPostAdapter extends SimpleCursorAdapter {
	Context context;
	ConfessionsDatabaseHelper databaseHelper;

	public LazyConfessionsPostAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		databaseHelper = new ConfessionsDatabaseHelper(context);

	}

	protected Activity activity;

	private LayoutInflater inflater = null;

	private static String DEBUG_TAG = LazyConfessionsPostAdapter.class
			.getSimpleName();

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		if (ApplicationSettings.DEBUG)
			Log.d(DEBUG_TAG, "getView called");

		View vi = inflater.inflate(R.layout.post_layout_item, parent, false);
		holder = new ViewHolder();
		ExpandableTextView confessionText = (ExpandableTextView) vi
				.findViewById(R.id.postItemText);
		ImageView confessionImage = (ImageView) vi.findViewById(R.id.postItemImage);

		//vi.setTag(holder);
		bindView(vi, context, cursor);
		return vi;
	}

	@Override
	public void bindView(View v, Context context, final Cursor c) {
	
		final ExpandableTextView confessionText = (ExpandableTextView) v.findViewById(R.id.postItemText);
		ImageView confessionImage = (ImageView) v.findViewById(R.id.postItemImage);
		if (!CursorUtils.getString(
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE, c)
				.equals("")
				&& CursorUtils
						.getString(
								ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
								c) != null) {
			// Populating
			Log.v(DEBUG_TAG, "ContentImage detected"); 
			String imageURL = IdentifiersList.collegeTickrBaseURL
					+ CursorUtils
							.getString(
									ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
									c);

			final ImageAware imageAware = new ImageViewAware(confessionImage,
					false);

			MainActivity.imageLoader.displayImage(imageURL, imageAware,
					ApplicationSettings.defaultImageOptions(),
					new AnimateFirstDisplayListener());
			confessionText.setVisibility(View.GONE);
			confessionImage.setVisibility(View.VISIBLE);
		} else {
			Log.v(DEBUG_TAG, "No contentImage detected");
			confessionText.setText(CursorUtils.getString(
					ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT, c));
			confessionImage.setVisibility(View.GONE);
			confessionText.setVisibility(View.VISIBLE);
			int valuetrim = CursorUtils.getInt(
					ConfessionsTrackExpandedTable.COLUMN_TRIMMED, c);
			boolean booltrim = PostWrapperForExpandableTextView
					.convertIntToBoolean(valuetrim);

			confessionText.setTrim(booltrim);

			// This code needs to be fixed. 
			//each time you touch it, it alternates between this and the next view.
			
		
		}
		
	}

	private ViewHolder holder;

	// I honestly have no idea why this is the class we need to have to make
	// this work. Without it,
	// we will have images loading in the wrong positions. This is dependent on
	// the UniversalLoader libarary.

	// this is just a container. it literally just holds a value and
	// remembers all the previous values.
	protected class ViewHolder {
		public ExpandableTextView primaryTextView;
		public ImageView image;

	}

	protected static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			// if this is disabled, then it just rebounces
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;

				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}

	}

}