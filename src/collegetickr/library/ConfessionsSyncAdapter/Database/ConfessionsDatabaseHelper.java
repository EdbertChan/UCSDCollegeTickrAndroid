package collegetickr.library.ConfessionsSyncAdapter.Database;

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
	private static final String TABLE_NAME = ConfessionsTable.TABLE_CONFESSIONS;
	private static final Uri CONTENT_URI = ConfessionsContentProvider.CONTENT_URI;
	
	
	
	private static final String DATABASE_NAME = TABLE_NAME
			+ ".db";
	private static final int DATABASE_VERSION = 1;
	
	
	public static final String[] defaultProjection = {
			ConfessionsTable.COLUMN_ID, ConfessionsTable.COLUMN_POST_CONTENT,
			ConfessionsTable.COLUMN_POST_CONTENT_IMAGE,
			ConfessionsTable.COLUMN_POST_CREATED_AT };

	public ConfessionsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		myCR = context.getContentResolver();
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		ConfessionsTable.onCreate(database);
	}

	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		ConfessionsTable.onUpgrade(database, oldVersion, newVersion);
	}

	public void addPostExpandable(PostWrapperForExpandableTextView product) {

		ContentValues values = new ContentValues();
		
		values.put(ConfessionsTable.COLUMN_POST_CONTENT, product.getContent());
		values.put(ConfessionsTable.COLUMN_POST_CONTENT_IMAGE,
				product.getContent_image());
		values.put(ConfessionsTable.COLUMN_POST_CREATED_AT,
				product.getCreated_at());
		values.put(ConfessionsTable.COLUMN_ID, product.getId());

		//values.put(ConfessionsTable, product.getId());

		myCR.insert(ConfessionsContentProvider.CONTENT_URI, values);
	}

	public Post findPostExpandable(String productname) {

		String selection = "productname = \"" + productname + "\"";

		Cursor cursor = myCR.query(CONTENT_URI,
				defaultProjection, selection, null, null);

		Post post = new Post();

		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			// 0 is reserved for id

			post.setId(cursor.getInt(cursor
					.getColumnIndexOrThrow(ConfessionsTable.COLUMN_ID)));
			post.setContent(cursor.getString(cursor
					.getColumnIndexOrThrow(ConfessionsTable.COLUMN_POST_CONTENT)));
			post.setContent_image(cursor.getString(cursor
					.getColumnIndexOrThrow(ConfessionsTable.COLUMN_POST_CONTENT_IMAGE)));
			post.setCreated_at(cursor.getString(cursor
					.getColumnIndexOrThrow(ConfessionsTable.COLUMN_POST_CREATED_AT)));
			cursor.close();
		} else {
			post = null;
		}
		return post;
	}

	public boolean deletePost(String productname) {

		boolean result = false;

		String selection = "productname = \"" + productname + "\"";

		int rowsDeleted = myCR.delete(CONTENT_URI,
				selection, null);

		if (rowsDeleted > 0)
			result = true;

		return result;
	}
}
