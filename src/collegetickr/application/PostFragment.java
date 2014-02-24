package collegetickr.application;

import collegetickr.library.Post;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
DemoFragment fragmentDemo = DemoFragment.newInstance(5, "my title");
ft.replace(R.id.your_placeholder, fragmentDemo);
ft.commit();*/
public class PostFragment extends Fragment{
	/*public static PostFragment newInstance(Post newPost)
	{
		PostFragment fragmentDemo = new PostFragment();
		   Bundle args = new Bundle();
		   args.putParcelable("newPost", newPost);
		   fragmentDemo.setArguments(args);
	        return fragmentDemo;
	}*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.post_layout, container, false);
		CustomScrollView customScrollView = (CustomScrollView) rootView
				.findViewById(R.id.postScrollView);
		
	
		ImageView upVote = (ImageView) rootView
				.findViewById(R.id.postUpVote);
		upVote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/*add what we do on the upvote*/
			}
		});
		ImageView downVote = (ImageView) rootView
				.findViewById(R.id.postDownVote);
		downVote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/*add what we do on the downvote*/
			}
		});
		
		return rootView;
	}
}