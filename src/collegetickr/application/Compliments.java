package collegetickr.application;

import info.androidhive.tabsswipe.R;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Compliments extends Fragment {
	private static int RESULT_LOAD_IMAGE = 1;
	 private String selectedImagePath = "";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.compliments_layout, container, false);
		
		Button buttonLoadImage = (Button) rootView.findViewById(R.id.uploadPictureCompliment);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
                 
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        

      
		return rootView;
	}
	//Gets the string of the path to the picture
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode == Activity.RESULT_OK) {
    		Uri selectedImageUri = data.getData();
    		selectedImagePath = selectedImageUri.getPath();
            //note: might want to change icon to let user know we have selected?

        }
    }
}
