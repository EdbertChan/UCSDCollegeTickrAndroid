package collegetickr.application.Confessions.SyncAdapter;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;

import collegetickr.application.Post.Post;
import collegetickr.library.JSONHandlerLibrary;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ConfessionsNetworkUtilities {
	/*public static List<Post> fetchNewPostsFromPreference(){
		
	}*/
	public static List<Post> fetchPostsFromURL(String urlGet)
			throws JSONException, ParseException, IOException,
			AuthenticationException {

		List<Post> newPosts = new ArrayList<Post>();
		HttpURLConnection urlConnection = null;
		HttpEntity entity = null;
		URL url;
		String response = "";
	
		url = new URL(urlGet);

		urlConnection = (HttpURLConnection) url.openConnection();

		InputStream in = new BufferedInputStream(urlConnection.getInputStream());
		response = readStream(in);
		urlConnection.disconnect();

		newPosts = JSONHandlerLibrary.convertStringtoArrayListPosts(response);

		return newPosts;
	}

	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while (i != -1) {
				bo.write(i);
				i = is.read();
			}
			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}
}