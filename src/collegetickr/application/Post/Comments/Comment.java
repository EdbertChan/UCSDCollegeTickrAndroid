//class to represent comment
package collegetickr.application.Post.Comments;

import java.util.Date;

public class Comment implements Cloneable{
	String timePosted, content, userName, iconURL;
	int upvote, downvote;
	public Comment() {
		timePosted = "timePosted";
		content = "content";
		userName = "userName";
		iconURL = "http://i981.photobucket.com/albums/ae295/wyfrost/Emoticon/Yuuko-Normal.png";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Comment cloned = (Comment)super.clone();
		cloned.setTimePosted(this.timePosted);
		cloned.setContent(this.content);
		cloned.setUserName(this.userName);
		cloned.setIconURL(this.iconURL);
		cloned.setUpvote(this.upvote);
		cloned.setDownvote(this.downvote);
		return cloned;
	}
	public Comment(String timePosted, String content, String userName,
			String iconURL, int upvote, int downvote) {

		this.timePosted = timePosted;
		this.content = content;
		this.userName = userName;
		this.iconURL = iconURL;
		this.upvote = upvote;
		this.downvote = downvote;
	}

	public String getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
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

	
}