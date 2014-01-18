package collegetickr.views;

import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View.OnClickListener;

public class PostDataWebTask extends AsyncTask<Object, Void, String>{

	Fragment activity;
    @Override
    protected String doInBackground(Object...params) {        
            String serviceUrl = (String) params[0];
            String pathToImg = (String) params[1];
            
            Log.i("arg2", "checking");
            return "doInBG done";
    }
    
   


    @Override
    protected void onPostExecute(String response) {
           Log.d("done","done");
    }

    @Override
    protected void onPreExecute() {
            super.onPreExecute();
    }


	

}
