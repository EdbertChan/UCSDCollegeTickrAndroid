/* performs a get request and returns the string representation of the data. It does not appear that we need anything
 * more for an api call
 */
package collegetickr.library.WebPostGetAsyncTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import collegetickr.application.Fragments.Confessions;
import collegetickr.library.JSONHandlerLibrary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
//How to use: new MyTask(this).execute("http://samir-mangroliya.blogspot.in");
public class GetDataWebTask extends AsyncTask<String, String, String>{
	// private Activity activity;
	 
	 private AsyncTaskCompleteListener callback;

	 
	 public GetDataWebTask(Fragment act) {
		// this.activity = act;
		  this.callback = (AsyncTaskCompleteListener)act;
		 }

    @Override
    protected String doInBackground(String... uri) {
    	 HttpURLConnection urlConnection = null;
          //  response = httpclient.execute(new HttpGet(uri[0]));
            URL url;
            String response = "";
			try {
				url = new URL(uri[0]);
			
				urlConnection = (HttpURLConnection) url.openConnection();
           
              InputStream in = new BufferedInputStream(urlConnection.getInputStream());
              response = readStream(in);
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//may have to say whether or not connection passed or failed
			} 
             finally {
              urlConnection.disconnect();
            }
			return response;
          
    }
    private String readStream(InputStream is) {
        try {
          ByteArrayOutputStream bo = new ByteArrayOutputStream();
          int i = is.read();
          while(i != -1) {
            bo.write(i);
            i = is.read();
          }
          return bo.toString();
        } catch (IOException e) {
          return "";
        }
    }
    @Override
    protected void onPostExecute(String result) {
    	//once we get the stuff, we should go ahead and process all of them. we will process all of the
    	//new entries and add them to the adapater. Maybe this will be inheritable b/c of confessions and 
    	//compliments
    	
    	
        super.onPostExecute(result);
        callback.onTaskComplete(result);
       
    }
}