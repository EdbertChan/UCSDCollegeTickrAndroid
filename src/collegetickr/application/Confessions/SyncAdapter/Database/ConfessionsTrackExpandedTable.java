package collegetickr.application.Confessions.SyncAdapter.Database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ConfessionsTrackExpandedTable {

	// Database table
	public static final String TABLE_CONFESSIONS = "table_confessions";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_POST_CONTENT = "post_content";
	public static final String COLUMN_POST_CONTENT_IMAGE = "post_content_image";
	public static final String COLUMN_POST_CREATED_AT = "post_created_at";
	public static final String COLUMN_TRIMMED = "post_trimmed_view";
	// public static final String COLUMN_WRAPPER_TRIMMED = "trimmed";

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_CONFESSIONS + "(" + COLUMN_ID + " integer primary key, "
			+ COLUMN_POST_CONTENT + "  text not null, "
			+ COLUMN_POST_CONTENT_IMAGE + " text not null, "
			+ COLUMN_POST_CREATED_AT + " text not null, " 
			+ COLUMN_TRIMMED + " integer not null" +
			");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(ConfessionsTrackExpandedTable.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_CONFESSIONS);
		onCreate(database);
	}

	public static void onReset(SQLiteDatabase database) {
		database.execSQL("delete from " + DATABASE_CREATE);
		database.execSQL(DATABASE_CREATE);
	}
}