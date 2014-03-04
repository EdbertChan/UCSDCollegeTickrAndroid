package collegetickr.application;

import java.util.ArrayList;

import collegetickr.application.Comments.CommentsScaffoldFragment.onSubmitListener;
import collegetickr.library.Post;
import collegetickr.library.Comment;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
 DemoFragment fragmentDemo = DemoFragment.newInstance(5, "my title");
 ft.replace(R.id.your_placeholder, fragmentDemo);
 ft.commit();*/

public class PostFragment extends Fragment {
	Post postClass = null;

	static PostFragment newInstance(int num) {
		PostFragment f = new PostFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}

	public PostFragment() {
		postClass = new Post();
	}

	/*
	 * public static PostFragment newInstance(Post newPost) { PostFragment
	 * fragmentDemo = new PostFragment(); Bundle args = new Bundle();
	 * args.putParcelable("newPost", newPost); fragmentDemo.setArguments(args);
	 * return fragmentDemo; }
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater
				.inflate(R.layout.post_layout, container, false);

		ImageView upVote = (ImageView) rootView.findViewById(R.id.postUpVote);
		upVote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* add what we do on the upvote */
			}
		});
		ImageView downVote = (ImageView) rootView
				.findViewById(R.id.postDownVote);
		downVote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* add what we do on the downvote */
			}
		});

		TextView comment = (TextView) rootView.findViewById(R.id.postScore);
		comment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				showComments(container);
				/*View layout = LayoutInflater.from(container.getContext()).inflate(
						R.layout.comments_scaffold_layout,
						(ViewGroup) container.findViewById(R.id.commentContainer));
				final EditText commentEditText = (EditText) layout.findViewById(R.id.commentEditText);
				commentEditText.set
				commentEditText.setOnFocusChangeListener(new OnFocusChangeListener(){

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
					
						Log.i("hi","hi");
					}
					
				});
				dpwindo.setTouchIntercepterForEditText(commentEditText);*/
			}
		});
		return rootView;
	}

	private DismissablePopupWindow dpwindo = null;

	private void showComments(ViewGroup container) {
		// CommentsScaffoldFragment f1 = new
		// CommentsScaffoldFragment(postClass);
		// f1.show(getFragmentManager(), "");
		// initiatePopupWindow(container);
		ArrayList<Comment> n = new ArrayList<Comment>();
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		dpwindo = new DismissablePopupWindow(container, getActivity()
				.getBaseContext(), n);
		
		
		
	}


}