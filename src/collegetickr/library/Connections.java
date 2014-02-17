package collegetickr.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import collegetickr.library.*;
public class Connections {

	public boolean postConfession(String comment, String picture, String activityiD) {
		String url = "";
		if(activityiD.equals(IdentifiersList.complimentsID)){
			url = IdentifiersList.COMPLIMENTSURL;
		} else if(activityiD.equals(IdentifiersList.confessionsID)){
			url = IdentifiersList.CONFESSIONSURL;
		} else{
			return false; //we had an error somewhere
		}
		return doPost(url, IdentifiersList.commentPostHTTP, comment,
				IdentifiersList.picturePostHTTP, picture);
	}

	private boolean doPost(String url, String commentiD, String comment,
			String pictureiD, String picture) {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
			nameValuePairs.add(new BasicNameValuePair(commentiD, comment));
			// nameValuePairs.add(new BasicNameValuePair(pictureiD, picture));
			// note: octet-stream. may need further modification
			// authenticity_token also may be neeed?
			//this is a basic name value for submit
			nameValuePairs.add(new BasicNameValuePair("commit",
					"Submit Content"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// ADD PICTURE INFO

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				return true;
			}
			return false;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}
}