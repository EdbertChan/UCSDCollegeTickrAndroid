package collegetickr.library;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;

import collegetickr.application.R;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class ApplicationSettings {

	public static final boolean DEBUG = true;

	// ExpandableTextView options
	public static final int DEFAULT_LENGTH = 250;
	public static boolean LOAD_ALL_AT_ONCE = true;
	public static final int previewUploadThumbnailSize = 100;
	// default size for viewallconfessions

	
	public static DisplayImageOptions defaultImageOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(false)
				// default
				.considerExifParams(false)
				// default
			//.showImageOnLoading(R.drawable.loading)
				
				.cacheInMemory(true).cacheOnDisc(true).resetViewBeforeLoading()
				.considerExifParams(true).build();

		return options;
	}

	public static ImageLoaderConfiguration defaultImageLoaderConfiguration(
			Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).tasksProcessingOrder(QueueProcessingType.LIFO)
				.defaultDisplayImageOptions(defaultImageOptions())
				.discCacheExtraOptions(480, 480, CompressFormat.PNG, 75, null)
				.memoryCacheSizePercentage(25) // default
				.writeDebugLogs().build();
		return config;
	}
}