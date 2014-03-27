package collegetickr.application.LazyAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import collegetickr.application.R;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.Post.Post;
import collegetickr.library.ApplicationCompileSettings;
import collegetickr.library.ExpandableTextView;
import collegetickr.library.IdentifiersList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.support.v4.view.ViewPager.LayoutParams;
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

public class LazyPostAdapter extends BaseAdapter {

	protected Activity activity;
	// private String[] data;
	private ArrayList<Post> listOfPosts;
	private static LayoutInflater inflater = null;

	private static String DEBUG_TAG = LazyPostAdapter.class.getSimpleName();

	public LazyPostAdapter(Activity a, ArrayList<Post> listOfPosts) {
		activity = a;
		this.listOfPosts = listOfPosts;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// imageLoader = new ImageLoader(a);

	}

	public int getCount() {
		return listOfPosts.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		if(ApplicationCompileSettings.DEBUG)
			Log.d(DEBUG_TAG, "getView called");

		View vi = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.post_layout_item, parent, false);
			holder = new ViewHolder();
			holder.primaryTextView = (ExpandableTextView) vi
					.findViewById(R.id.postItemText);
			holder.image = (ImageView) vi.findViewById(R.id.postItemImage);

			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

	
		Log.v(DEBUG_TAG,
				String.valueOf(listOfPosts.get(position).getContent_image()));
		// if the url is null, then we display text.
		// vi.setLayoutParams(new LayoutParams(width, height));
	
		if (listOfPosts.get(position).getContent_image() != null
				&& listOfPosts.get(position).getContent_image() != "") {
			// Populating
			Log.v(DEBUG_TAG, "No contentImage detected");
			String imageURL = IdentifiersList.collegeTickrBaseURL
					+ listOfPosts.get(position).getContent_image();

			ImageAware imageAware = new ImageViewAware(holder.image, false);

			MainActivity.imageLoader.displayImage(imageURL, imageAware,
					ApplicationCompileSettings.defaultImageOptions(),
					new AnimateFirstDisplayListener());
			holder.primaryTextView.setVisibility(View.GONE);
			holder.image.setVisibility(View.VISIBLE);

		} else {
			Log.v(DEBUG_TAG, "No contentImage detected");
			holder.primaryTextView.setText(listOfPosts.get(position)
					.getContent());
			
			holder.image.setVisibility(View.GONE);
			holder.primaryTextView.setVisibility(View.VISIBLE);

			holder.primaryTextView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					holder.primaryTextView.setTrim(!holder.primaryTextView
							.getTrim());
				//	vi.invalidate();
					notifyDataSetChanged();
					
				}
			});
		}
		notifyDataSetChanged();
		return vi;
	}

	// I honestly have no idea why this is the class we need to have to make
	// this work. Without it,
	// we will have images loading in the wrong positions. This is dependent on
	// the UniversalLoader libarary.
	protected static class ViewHolder {
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