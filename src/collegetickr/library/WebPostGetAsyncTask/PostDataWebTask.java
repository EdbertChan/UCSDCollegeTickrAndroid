package collegetickr.library.WebPostGetAsyncTask;

import java.io.File;

import collegetickr.application.SubmitComplimentsConfessions.ComplimentsConfessionsFragmentBaseClass;
import collegetickr.library.IdentifiersList;
import android.app.Fragment;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View.OnClickListener;

public class PostDataWebTask extends AsyncTask<Object, Void, String>{
	private String comment = "", pathToImg = "", fromWhatActivity = "", msgToUser = "";
	boolean flag = false;
	
	public PostDataWebTask(String comment,String pathToImg,String fromWhatActivity){
		this.comment = comment;
		this.pathToImg = pathToImg;
		this.fromWhatActivity = fromWhatActivity;
	}
    @Override
    protected String doInBackground(Object...params) {        
            if(flag == false){
            	return IdentifiersList.notExecutingiD;
            }
       
            //get response
            return "doInBG done";
    }

   


    @Override
    protected void onPostExecute(String response) {
           Log.d("done","done");
    }

    @Override
    protected void onPreExecute() {
            super.onPreExecute();
            
            //check to see if values are valid

            if(comment.equals("") || comment == null){
            	flag = false;
            	msgToUser = IdentifiersList.misingComment;
            	return;
            }
            if(!pathToImg.equals("") && !(new File(pathToImg).exists())){
            	flag = false;
            	msgToUser = IdentifiersList.invalidPicture;
            	return;
            }
            flag = true;
            
    }


	

}
