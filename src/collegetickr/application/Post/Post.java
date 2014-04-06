package collegetickr.application.Post;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsContentProvider;
import collegetickr.application.Confessions.SyncAdapter.Database.ConfessionsTrackExpandedTable;

import collegetickr.library.JSONHandlerLibrary;

public class Post {
	protected String content;
	protected String content_image;
	protected String created_at;
	int id;

	public Post(JSONObject jObject) throws JSONException {
		content = jObject.optString(JSONHandlerLibrary.content);
		created_at = jObject.optString(JSONHandlerLibrary.createdAt);
		content_image = jObject.optString(JSONHandlerLibrary.contentImage);
		id = jObject.optInt(JSONHandlerLibrary.postID);
	}

	public Post(String content, String content_image, String created_at, int id) {
		this.content = content;
		this.content_image = content_image;
		this.created_at = created_at;
		this.id = id;
	}

	public Post() {
		content = content_image = created_at = "";
		id = 0;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_image() {
		return content_image;
	}

	public void setContent_image(String content_image) {
		this.content_image = content_image;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void readFromParcel(Parcel in) {
		content = in.readString();
		content_image = in.readString();
		created_at = in.readString();

	}

	public static Post fromCursor(Cursor curTvShows) {
		String db_content_image = curTvShows.getString(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE));
		String db_created_at = curTvShows.getString(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT));
		String db_content = curTvShows.getString(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT));
		int db_id = curTvShows.getInt(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_ID));

		return new Post(db_content, db_content_image, db_created_at, db_id);
	}

	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(ConfessionsTrackExpandedTable.COLUMN_ID, id);
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE, content_image);
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT, created_at);
		values.put(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT, content);
		return values;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// this just compares it based on ID, not content.
	@Override
	public boolean equals(Object object) {
		boolean sameSame = false;
		Post comparing = (Post) object;
		if (object != null && comparing.getId() == id) {
			sameSame = true;
		}

		return sameSame;
	}
}