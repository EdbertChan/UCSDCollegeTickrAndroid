/* This class is responsible for holding how we process the JSONS. It does not actually hold the code for network
 * calls. It is solely responsible for parsing.*/
package collegetickr.library;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import collegetickr.application.Post.Post;

import android.net.ParseException;

public class JSONHandlerLibrary {
	public static final String postID = "id";
	public static final String content = "content";
	public static final String contentSourceID = "content_source_id";
	public static final String createdAt = "created_at";
	public static final String updatedAt = "updated_at";
	public static final String category = "category";
	public static final String imageFileName = "image_file_name";
	public static final String imageContentType = "image_content_type";
	public static final String imageFileSize = "image_file_size";
	public static final String imageUpdatedAt = "image_updated_at";
	public static final String ip = "ip_address";
	public static final String allowMsg = "allow_msg";
	public static final String userID = "user_id";
	public static final String votesTotal = "cached_votes_total";
	public static final String votesScore = "cached_votes_score";
	public static final String upVotes = "cached_votes_up";
	public static final String downVotes = "cached_votes_down";
	public static final String weightedScore = "cached_weighted_score";
	public static final String contentImage ="content_image";
	
	public static final int numOfPostFetch = 5;
	public static final String APIURL = "http://www.google.com";

	public static String getURLFromPosition(int pos) {
		// tells how many # of positions we are at in order to get the next
		// batch of apis. Most likely
		// this is just # of posts we have so far
		return "";
	}

	public static JSONArray convertStringtoJSONArray(String s) {
		try {
			return new JSONArray(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONArray();
	}

	public static ArrayList<Post> convertStringtoArrayListPosts(String s) {
		
		ArrayList<Post> listOfPostsFromJSON = new ArrayList<Post>();
		try {
			JSONArray jsonPosts = new JSONArray(s);
			for (int i = 0; i < 10; i++) {
				listOfPostsFromJSON.add(new Post(jsonPosts.getJSONObject(i)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfPostsFromJSON;
	}
	public static Date JSONtoDate(String dateString) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date  = formatter.parse(dateString);
		return date;
	}
}