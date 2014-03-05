//we assume all comments are uniform.
package collegetickr.application.Comments;

import java.util.ArrayList;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import collegetickr.application.R;
import collegetickr.application.Post.Post;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;

public class CommentsFragment extends DialogFragment {
	Button mButton;
	EditText mEditText;
	onSubmitListener mListener;
	String text = "";
	ListView listView;
	Post postOfComment = null;
	private ProgressBar spinner;
	CommentsAdapter mCommentsAdapter;
	ArrayList<Comment> listOfComments;

	public interface onSubmitListener {
		void setOnSubmitListener(String arg);
	}

	public CommentsFragment(Post postOfComment) {
		listOfComments = postOfComment.getComments();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); // do we
																	// really
																	// want it
																	// to not
																	// say
																	// comments?
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.comments_scaffold_layout);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dialog.show();
		mButton = (Button) dialog.findViewById(R.id.commentSubmit);
		mEditText = (EditText) dialog.findViewById(R.id.commentEditText);
		spinner = (ProgressBar) dialog.findViewById(R.id.commentLoadingSpinner);

		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// mListener.setOnSubmitListener(mEditText.getText().toString());
				//dismiss();
				//doneLoadingDismissSpinner();
			}
		});
		
		ArrayList<Comment> n = new ArrayList<Comment>();
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		n.add(new Comment());
		mCommentsAdapter = new CommentsAdapter(getActivity(),
				R.id.commentContent, n);

		// View view = inflater.inflate(R.layout.aroundme_fragment, container,
		// false);

		listView = (ListView) dialog.findViewById(R.id.aroundme_listview);
		listView.setAdapter(mCommentsAdapter);
		//changeToLoading();
		spinner.setVisibility(View.GONE);
		return dialog;
	}

	public void populateComments() {
		if (postOfComment != null) {
			// do a get request
			// new GetDataWebTask(this).execute("http://www.google.com");
			// Replace google with our URL

			// for now, lets populate with dummy comments

			// populate our scrollview
		}
	}

	public void changeToLoading() {
		spinner.setVisibility(View.VISIBLE);
		listView.setVisibility(View.GONE);
	}

	public void doneLoadingDismissSpinner() {
		spinner.setVisibility(View.GONE);
		listView.setVisibility(View.VISIBLE);
	}
}
