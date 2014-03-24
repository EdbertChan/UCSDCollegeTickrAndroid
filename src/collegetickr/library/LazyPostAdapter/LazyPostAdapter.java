package collegetickr.library.LazyPostAdapter;

import java.util.ArrayList;

import collegetickr.application.R;
import collegetickr.application.Post.Post;
import collegetickr.library.IdentifiersList;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LazyPostAdapter extends BaseAdapter {
    
    private Activity activity;
    //private String[] data;
    private ArrayList<Post> listOfPosts;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    private static String DEBUG_TAG = "LazyPostAdapter";
    public LazyPostAdapter(Activity a, ArrayList<Post> listOfPosts) {
        activity = a;
        this.listOfPosts = listOfPosts;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return listOfPosts.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    //what about updating?
    public  View getView(int position, View convertView, ViewGroup parent){
    	Log.v(DEBUG_TAG, "getView called");
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.post_layout_item, null);

        TextView text=(TextView)vi.findViewById(R.id.postItemText);
        ImageView image=(ImageView)vi.findViewById(R.id.postItemImage);
        ProgressBar loadingWheel=(ProgressBar)vi.findViewById(R.id.postLoadingWheel);
        Log.v(DEBUG_TAG, String.valueOf(listOfPosts.get(position).getContent_image()));
        //if the url is null, then we display text. 
        if(listOfPosts.get(position).getContent_image() != null &&  listOfPosts.get(position).getContent_image() !=  ""){
        	//Populating
        	//Log.v(DEBUG_TAG, String.valueOf(listOfPosts.get(position).getContent_image()));
        	String imageURL = IdentifiersList.collegeTickrURL +listOfPosts.get(position).getContent_image();
        	imageLoader.DisplayImage(imageURL, image);	
        	text.setVisibility(View.GONE);
        	image.setVisibility(View.VISIBLE);
        } else{
        	Log.v(DEBUG_TAG, "No contentImage detected");
        	text.setText(listOfPosts.get(position).getContent());
        	image.setVisibility(View.GONE);
        	text.setVisibility(View.VISIBLE);
        }
        loadingWheel.setVisibility(View.GONE);
        //if the url isnt null, we display image
        
        
        return vi;
    }
}