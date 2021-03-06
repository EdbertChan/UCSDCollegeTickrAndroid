package collegetickr.application.Confessions.SyncAdapter.Database;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class ConfessionsContentProvider extends ContentProvider {

	// database
	private ConfessionsDatabaseHelper database;

	// used for the UriMacher
	private static final int TODOS = 10;
	private static final int TODO_ID = 20;

	public static final String AUTHORITY = "collegetickr.application.PostsSyncAdapter.MyTodoContentProvider";

	private static final String BASE_PATH = "collegetickrConfessions";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
	}

	@Override
	public boolean onCreate() {
		database = new ConfessionsDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Uisng SQLiteQueryBuilder instead of query() method
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// check if the caller has requested a column which does not exists
		checkColumns(projection);

		// Set the table
		queryBuilder.setTables(ConfessionsTrackExpandedTable.TABLE_CONFESSIONS);

		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case TODOS:
			break;
		case TODO_ID:
			// adding the ID to the original query
			queryBuilder.appendWhere(ConfessionsTrackExpandedTable.COLUMN_ID
					+ "=" + uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		SQLiteDatabase db = database.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		// make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
		long id = 0;
		switch (uriType) {
		case TODOS:
			id = sqlDB.insert(ConfessionsTrackExpandedTable.TABLE_CONFESSIONS,
					null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
		switch (uriType) {
		case TODOS:
			rowsDeleted = sqlDB.delete(
					ConfessionsTrackExpandedTable.TABLE_CONFESSIONS, selection,
					selectionArgs);
			break;
		case TODO_ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = sqlDB.delete(
						ConfessionsTrackExpandedTable.TABLE_CONFESSIONS,
						ConfessionsTrackExpandedTable.COLUMN_ID + "=" + id,
						null);
			} else {
				rowsDeleted = sqlDB.delete(
						ConfessionsTrackExpandedTable.TABLE_CONFESSIONS,
						ConfessionsTrackExpandedTable.COLUMN_ID + "=" + id
								+ " and " + selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsUpdated = 0;
		switch (uriType) {
		case TODOS:
			rowsUpdated = sqlDB.update(
					ConfessionsTrackExpandedTable.TABLE_CONFESSIONS, values,
					selection, selectionArgs);
			break;
		case TODO_ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = sqlDB.update(
						ConfessionsTrackExpandedTable.TABLE_CONFESSIONS,
						values, ConfessionsTrackExpandedTable.COLUMN_ID + "="
								+ id, null);
			} else {
				rowsUpdated = sqlDB.update(
						ConfessionsTrackExpandedTable.TABLE_CONFESSIONS,
						values, ConfessionsTrackExpandedTable.COLUMN_ID + "="
								+ id + " and " + selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

	private void checkColumns(String[] projection) {
		String[] available = { ConfessionsTrackExpandedTable.COLUMN_ID,
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT,
				ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE,
				ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT,
				ConfessionsTrackExpandedTable.COLUMN_TRIMMED };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(
					Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(
					Arrays.asList(available));
			// check if all columns which are requested are available
			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException(
						"Unknown columns in projection");
			}
		}
	}

}