package collegetickr.library;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable{
	//might want to make createdDate and updated Date 
	String iconURL, content, createdDate, updatedDate, author, id;
	int upvote, downvote, totalScore;
	ArrayList<Comment> 	comments = new ArrayList<Comment>();
	boolean allowMsg;
	//constructor input JSON string,
	public Post(){
		//this is just default. we should never call this in practice
		iconURL = content = createdDate = updatedDate = author = id = "";
		upvote = downvote = totalScore = 0;
	
		
		allowMsg = false;
	}
	public Post(JSONObject jObject){
		
		
		try {
			
			id = jObject.getString(JSONHandlerLibrary.postID);
			author = jObject.getString(JSONHandlerLibrary.userID);
			iconURL = jObject.getString(JSONHandlerLibrary.imageFileName);
			content = jObject.getString(JSONHandlerLibrary.content);
			createdDate = jObject.getString(JSONHandlerLibrary.createdAt);
			updatedDate = jObject.getString(JSONHandlerLibrary.updatedAt);
			
			upvote = jObject.getInt(JSONHandlerLibrary.upVotes);
			downvote = jObject.getInt(JSONHandlerLibrary.downVotes);
			totalScore = jObject.getInt(JSONHandlerLibrary.weightedScore);
			
			allowMsg = jObject.getBoolean(JSONHandlerLibrary.allowMsg);	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		 dest.writeStringArray(new String[] {
				 iconURL,
	                content,
	                createdDate,
	                updatedDate, author, id
	            });
dest.writeIntArray(new int[]{totalScore,upvote,downvote});
dest.writeBooleanArray(new boolean[]{allowMsg
		
});
	dest.writeList(comments);
	}

}