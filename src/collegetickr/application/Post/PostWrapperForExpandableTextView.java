package collegetickr.application.Post;

import collegetickr.application.ConfessionsSyncAdapter.Database.ConfessionsContentProvider;
import collegetickr.application.ConfessionsSyncAdapter.Database.ConfessionsTrackExpandedTable;

import android.content.ContentValues;
import android.database.Cursor;

public class PostWrapperForExpandableTextView extends Post{
	protected boolean trimmed = true;
	public void setTrimmed(int value){
		trimmed = convertIntToBoolean(value);
	}
	public static boolean convertIntToBoolean(int value){
		if(value == 1){
			return true;
		} 
		return false;
	}
	public static int convertBooleanToInt(boolean value){
	     int intValue = (value) ? 1 : 0;
	     return intValue;
	}
	public PostWrapperForExpandableTextView(Post p){
		this.id = p.id;
	
		this.content = p.content;
		this.content_image = p.content_image;
		this.created_at = p.created_at;
		trimmed = true;
	}
	
	public PostWrapperForExpandableTextView(){
		super();
		trimmed = true;
	}
	
	public PostWrapperForExpandableTextView(String content, String content_image, String created_at, int id, int trimmed) {
		this.content = content;
		this.content_image = content_image;
		this.created_at = created_at;
		this.id = id;
		this.trimmed = convertIntToBoolean(trimmed);
	}

	public void setTrimmed(boolean value){
		trimmed = value;
	}
	public boolean getTrimmed(){
		return trimmed;
	}
	
	public ContentValues getContentValues() {
        ContentValues values = super.getContentValues();

       values.put( ConfessionsTrackExpandedTable.COLUMN_TRIMMED, convertBooleanToInt(trimmed));
        return values;
    }
	public static PostWrapperForExpandableTextView fromCursor(Cursor curTvShows) {
		String db_content_image = curTvShows.getString(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT_IMAGE));
		String db_created_at = curTvShows.getString(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_POST_CREATED_AT));
		String db_content = curTvShows.getString(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_POST_CONTENT));
		int db_id = curTvShows.getInt(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_ID));

		int db_trimmed = curTvShows.getInt(curTvShows
				.getColumnIndex(ConfessionsTrackExpandedTable.COLUMN_TRIMMED));
		
		return new PostWrapperForExpandableTextView(db_content, db_content_image, db_created_at, db_id, db_trimmed);
	}

	
}