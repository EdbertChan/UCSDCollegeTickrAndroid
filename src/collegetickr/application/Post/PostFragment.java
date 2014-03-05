//we assume all post fragments are the same. Same Layout.
package collegetickr.application.Post;

import java.util.ArrayList;

import collegetickr.application.CustomScrollView;
import collegetickr.application.R;
import collegetickr.application.Comments.Comment;
import collegetickr.application.Comments.CommentsFragment.onSubmitListener;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import collegetickr.library.AndroidAbstractClasses.DismissablePopupWindow;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;

/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
 DemoFragment fragmentDemo = DemoFragment.newInstance(5, "my title");
 ft.replace(R.id.your_placeholder, fragmentDemo);
 ft.commit();*/

public class PostFragment extends Fragment {
	Post postClass = null;
	private ProgressBar spinner;
	CustomScrollView scrollViewLayout;
	ImageView upVote, downVote, icon;
	ArrayList<Comment> listOfComments = new ArrayList<Comment>();
	TextView content, author, postScore;
	View rootView = null;
	private Animation animShow, animHide;
	private DismissablePopupWindow dpwindo = null;

	public PostFragment() {
		postClass = new Post();
	}

	public PostFragment(Post postClass) {

		this.postClass = postClass;

	}

	private void initializeValues(LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.post_layout, container, false);
		scrollViewLayout = (CustomScrollView) rootView
				.findViewById(R.id.postScrollView);
		spinner = (ProgressBar) rootView.findViewById(R.id.postLoadingSpinner);
		upVote = (ImageView) rootView.findViewById(R.id.postUpVote);
		downVote = (ImageView) rootView.findViewById(R.id.postDownVote);
		postScore = (TextView) rootView.findViewById(R.id.postScore);
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

		initializeValues(inflater, container, savedInstanceState);
		upVote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* add what we do on the upvote */

			}
		});

		downVote.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* add what we do on the downvote */
			}
		});

		postScore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				showComments(container);
				// currently code in progress for touching outside edit text
				// field to dismiss

				/*
				 * View layout =
				 * LayoutInflater.from(container.getContext()).inflate(
				 * R.layout.comments_scaffold_layout, (ViewGroup)
				 * container.findViewById(R.id.commentContainer)); final
				 * EditText commentEditText = (EditText)
				 * layout.findViewById(R.id.commentEditText);
				 * commentEditText.set
				 * commentEditText.setOnFocusChangeListener(new
				 * OnFocusChangeListener(){
				 * 
				 * @Override public void onFocusChange(View v, boolean hasFocus)
				 * { // TODO Auto-generated method stub
				 * 
				 * Log.i("hi","hi"); }
				 * 
				 * }); dpwindo.setTouchIntercepterForEditText(commentEditText);
				 */
			}
		});
		listOfComments.add(new Comment());
		listOfComments.add(new Comment());
		listOfComments.add(new Comment());
		listOfComments.add(new Comment());
		listOfComments.add(new Comment());
		listOfComments.add(new Comment());
		listOfComments.add(new Comment());
		return rootView;
	}

	public void changeToLoading() {
		spinner.setVisibility(View.VISIBLE);
		scrollViewLayout.setVisibility(View.GONE);
	}

	public void doneLoadingDismissSpinner() {
		spinner.setVisibility(View.GONE);
		scrollViewLayout.setVisibility(View.VISIBLE);
	}

	private void showComments(ViewGroup container) {
		// CommentsScaffoldFragment f1 = new
		// CommentsScaffoldFragment(postClass);
		// f1.show(getFragmentManager(), "");
		// initiatePopupWindow(container);

		dpwindo = new DismissablePopupWindow(container, getActivity()
				.getBaseContext(), listOfComments);
		// animShow = AnimationUtils.loadAnimation(
		// this.getActivity().getApplicationContext(), R.anim.popup_enter);

	}

}