package collegetickr.application.Post;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import collegetickr.library.JSONHandlerLibrary;

public class Post implements Parcelable{
	private String content, content_image, created_at;
	public Post(JSONObject jObject) throws JSONException {
		content = jObject.optString (JSONHandlerLibrary.content);
		created_at = jObject.optString (JSONHandlerLibrary.createdAt);
		content_image =  jObject.optString (JSONHandlerLibrary.contentImage);
	}
	public Post() {
		content = content_image = created_at = "";
		// TODO Auto-generated constructor stub
	}
	 public Post(Parcel in) {
      
         readFromParcel(in);
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
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	 public void readFromParcel(Parcel in) {
		 content = in.readString();
		 content_image =  in.readString();
		 created_at = in.readString();

       }
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
	
        arg0.writeString(content);
        arg0.writeString(content_image);
        arg0.writeString(created_at);
	}
	
}