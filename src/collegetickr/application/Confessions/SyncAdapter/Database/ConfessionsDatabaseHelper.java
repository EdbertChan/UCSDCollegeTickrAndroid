package collegetickr.application.Confessions.SyncAdapter.Database;

import collegetickr.application.Post.Post;
import collegetickr.application.Post.PostWrapperForExpandableTextView;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ConfessionsDatabaseHelper extends SQLiteOpenHelper {
	private ContentResolver myCR;
	private static final String TABLE_NAME = ConfessionsTrackExpandedTable.TABLE_CONFESSIONS;
	private static final Uri CONTENT_URI = ConfessionsContentProvider.CONTENT_URI;

	private static final String DATABASE_NAME = TABLE_NAME + ".db";
	private static final int DATABASE_VERSION = 1;

	public static final String[] defaultProjection = {
			ConfessionsTrackExpandedTable.COLUMN_ID,
			ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT,
			ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
			ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT,
			ConfessionsTrackExpandedTable.COLUMN_TRIMMED };

	public ConfessionsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		myCR = context.getContentResolver();
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		ConfessionsTrackExpandedTable.onCreate(database);
	}

	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		ConfessionsTrackExpandedTable.onUpgrade(database, oldVersion,
				newVersion);
	}

	public void addPost(PostWrapperForExpandableTextView product) {

		ContentValues values = new ContentValues();

		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT,
				product.getContent());
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
				product.getContent_image());
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT,
				product.getCreated_at());
		values.put(ConfessionsTrackExpandedTable.COLUMN_ID, product.getId());

		values.put(ConfessionsTrackExpandedTable.COLUMN_TRIMMED,
				product.getTrimmed());

		myCR.insert(ConfessionsContentProvider.CONTENT_URI, values);
	}

	public PostWrapperForExpandableTextView findProduct(int id) {

		String selection = ConfessionsTrackExpandedTable.COLUMN_ID + " = \""
				+ String.valueOf(id) + "\"";

		Cursor cursor = myCR.query(CONTENT_URI, defaultProjection, selection,
				null, null);

		PostWrapperForExpandableTextView post = new PostWrapperForExpandableTextView();

		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			// 0 is reserved for id

			post.setId(cursor.getInt(cursor
					.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_ID)));
			post.setContent(cursor.getString(cursor
					.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT)));
			post.setContent_image(cursor.getString(cursor
					.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE)));
			post.setCreated_at(cursor.getString(cursor
					.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT)));

			post.setTrimmed(cursor.getInt(cursor
					.getColumnIndexOrThrow(ConfessionsTrackExpandedTable.COLUMN_TRIMMED)));
			cursor.close();
		} else {
			post = null;
		}
		return post;
	}

	public boolean deletePost(String id) {

		boolean result = false;

		// String selection = "productname = \"" + productname + "\"";
		String selection = ConfessionsTrackExpandedTable.COLUMN_ID + " = \""
				+ id + "\"";

		int rowsDeleted = myCR.delete(CONTENT_URI, selection, null);

		if (rowsDeleted > 0)
			result = true;

		return result;
	}

	public boolean updatePost(PostWrapperForExpandableTextView product) {

		boolean result = false;

		// String selection = "productname = \"" + productname + "\"";
		String selection = ConfessionsTrackExpandedTable.COLUMN_ID + " = \""
				+ product.getId() + "\"";

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();

		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT,
				product.getContent());
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
				product.getContent_image());
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT,
				product.getCreated_at());
		values.put(ConfessionsTrackExpandedTable.COLUMN_ID, product.getId());

		values.put(ConfessionsTrackExpandedTable.COLUMN_TRIMMED,
				product.getTrimmed());

		// 3. updating row
		myCR.update(CONTENT_URI, values, selection, null);

		return result;
	}
}
