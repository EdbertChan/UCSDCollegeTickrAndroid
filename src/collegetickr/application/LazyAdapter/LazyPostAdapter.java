package collegetickr.application.LazyAdapter;

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
import collegetickr.application.ConfessionsSyncAdapter.Database.ConfessionsDatabaseHelper;
import collegetickr.application.ConfessionsSyncAdapter.Database.ConfessionsTrackExpandedTable;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.Post.Post;
import collegetickr.application.Post.PostWrapperForExpandableTextView;

import collegetickr.library.ApplicationCompileSettings;
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
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LazyPostAdapter extends SimpleCursorAdapter {
	Context context;
	ConfessionsDatabaseHelper databaseHelper;

	public LazyPostAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		databaseHelper = new ConfessionsDatabaseHelper(context);

	}

	protected Activity activity;
	// private String[] data;
	// private ArrayList<PostWrapperForExpandableTextView> listOfPosts;
	private LayoutInflater inflater = null;

	private static String DEBUG_TAG = LazyPostAdapter.class.getSimpleName();

	/*
	 * public LazyPostAdapter(Activity a, ArrayList<Post> posts) { activity = a;
	 * listOfPosts = new ArrayList<PostWrapperForExpandableTextView>();
	 * //this.listOfPosts = listOfPosts; addToPostWrapper(posts); inflater =
	 * (LayoutInflater) activity
	 * .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 * 
	 * // imageLoader = new ImageLoader(a);
	 * 
	 * }
	 */

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		if (ApplicationCompileSettings.DEBUG)
			Log.d(DEBUG_TAG, "getView called");

		View vi = inflater.inflate(R.layout.post_layout_item, parent, false);
		holder = new ViewHolder();
		holder.primaryTextView = (ExpandableTextView) vi
				.findViewById(R.id.postItemText);
		holder.image = (ImageView) vi.findViewById(R.id.postItemImage);

		// holder.primaryTextView.setTrim(true);
		vi.setTag(holder);
		bindView(vi, context, cursor);
		return vi;
	}

	@Override
	public void bindView(View v, Context context, final Cursor c) {
		holder = (ViewHolder) v.getTag();
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

			ImageAware imageAware = new ImageViewAware(holder.image, false);

			MainActivity.imageLoader.displayImage(imageURL, imageAware,
					ApplicationCompileSettings.defaultImageOptions(),
					new AnimateFirstDisplayListener());
			holder.primaryTextView.setVisibility(View.GONE);
			holder.image.setVisibility(View.VISIBLE);
		}

		else {
			Log.v(DEBUG_TAG, "No contentImage detected");
			holder.primaryTextView.setText(CursorUtils.getString(
					ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT, c));
			holder.image.setVisibility(View.GONE);
			holder.primaryTextView.setVisibility(View.VISIBLE);

			// code may need to be put into a library (conversion from int to
			// bool and vice versa)
			// int booleanRep =
			// CursorUtils.getInt(TodoTable.COLUMN_WRAPPER_TRIMMED, c);
			int valuetrim = CursorUtils.getInt(
					ConfessionsTrackExpandedTable.COLUMN_TRIMMED, c);
			boolean booltrim = PostWrapperForExpandableTextView
					.convertIntToBoolean(valuetrim);

			holder.primaryTextView.setTrim(booltrim);

			// This code needs to be fixed. It doesnt update the text for some
			// reason?
			/*
			 * holder.primaryTextView.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View v) {
			 * 
			 * 
			 * 
			 * 
			 * //
			 * holder.primaryTextView.setTrim(!listOfPosts.get(position).getTrimmed
			 * ()); //
			 * listOfPosts.get(position).setTrimmed(!listOfPosts.get(position
			 * ).getTrimmed());
			 * 
			 * // update the column w/ expanded
			 * 
			 * // Log.v(DEBUG_TAG, "onClick!"); // vi.invalidate(); int
			 * idOfProduct = CursorUtils.getInt(
			 * ConfessionsTrackExpandedTable.COLUMN_ID, c); int trimmedBool =
			 * CursorUtils.getInt( ConfessionsTrackExpandedTable.COLUMN_TRIMMED,
			 * c); boolean trim = PostWrapperForExpandableTextView
			 * .convertIntToBoolean(trimmedBool);
			 * 
			 * 
			 * //this actually isnt working for some reason?
			 * 
			 * holder.primaryTextView.setTrim(!trim);
			 * 
			 * 
			 * // update table with our value
			 * 
			 * PostWrapperForExpandableTextView temp =
			 * databaseHelper.findProduct(idOfProduct); temp.setTrimmed(!trim);
			 * databaseHelper.updatePost(temp); //vi.invalidate();
			 * holder.primaryTextView.setText();
			 * holder.primaryTextView.requestFocusFromTouch();
			 * notifyDataSetChanged();
			 * 
			 * } });
			 */
		}

		notifyDataSetChanged();

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
		public boolean expanded;

	}

	protected static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
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