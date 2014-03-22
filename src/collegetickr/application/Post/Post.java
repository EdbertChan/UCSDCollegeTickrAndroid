package collegetickr.application.Post;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import collegetickr.application.Post.Comments.Comment;
import collegetickr.library.JSONHandlerLibrary;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
	// might want to make createdDate and updated Date
	String iconURL, content, createdDate, updatedDate, author, id;
	int upvote, downvote, totalScore;
	ArrayList<Comment> comments = new ArrayList<Comment>();
	boolean allowMsg;
	public Object clone() throws CloneNotSupportedException {
		Post cloned = (Post)super.clone();
		cloned.setIconURL(this.getIconURL());
		cloned.setContent(this.getContent());
		cloned.setCreatedDate(this.getCreatedDate());
		cloned.setUpdatedDate(this.getUpdatedDate());
		cloned.setAuthor(this.getAuthor());
		cloned.setId(this.getId());
		cloned.setUpvote(this.getUpvote());
		cloned.setDownvote(this.getDownvote());
		cloned.setTotalScore(this.getTotalScore());
		cloned.setAllowMsg(this.isAllowMsg());
		ArrayList<Comment> clonedList = new ArrayList<Comment>(comments.size());
	    for(Comment item: comments) clonedList.add((Comment) item.clone());
	    cloned.setComments(clonedList);
	    return cloned;
	}
	public Post(Parcel parcel) {

		iconURL = parcel.readString();
		content= parcel.readString();
		createdDate= parcel.readString();
		updatedDate= parcel.readString();
		author= parcel.readString();
		id= parcel.readString();
		
		upvote = parcel.readInt();
		downvote = parcel.readInt();
		totalScore  = parcel.readInt();
		
		parcel.readList(comments, Comment.class.getClassLoader());
		
		allowMsg = parcel.readByte() != 0;    
		
    }
	// constructor input JSON string,
	public Post() {
		// this is just default. we should never call this in practice
		iconURL = content = createdDate = updatedDate = author = id = "";
		upvote = downvote = totalScore = 0;

		allowMsg = false;
	}
	public ArrayList<Comment> getComments(){
		return comments;
	}

	public Post(JSONObject jObject) {

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

		public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUpvote() {
		return upvote;
	}
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	public int getDownvote() {
		return downvote;
	}
	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public boolean isAllowMsg() {
		return allowMsg;
	}
	public void setAllowMsg(boolean allowMsg) {
		this.allowMsg = allowMsg;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	dest.writeString(iconURL);
	dest.writeString(content);
	dest.writeString(createdDate);
	dest.writeString(updatedDate);
	dest.writeString(author);
	dest.writeString(id);
	
	dest.writeInt(upvote);
	dest.writeInt(downvote);
	dest.writeInt(totalScore);
	
	dest.writeList(comments);

	dest.writeByte((byte) (allowMsg ? 1 : 0));  
	
	}

}