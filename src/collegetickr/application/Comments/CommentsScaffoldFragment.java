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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import collegetickr.application.R;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import collegetickr.library.Post;
import collegetickr.library.Comment;

public class CommentsScaffoldFragment extends DialogFragment {
	Button mButton;
	EditText mEditText;
	onSubmitListener mListener;
	String text = "";
	Post postOfComment = null;
	CommentsAdapter mCommentsAdapter;
	public interface onSubmitListener {
		void setOnSubmitListener(String arg);
	}

	public CommentsScaffoldFragment(Post postOfComment){
		this.postOfComment = postOfComment;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE); //do we really want it to not say comments?
		dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.setContentView(R.layout.comments_scaffold_layout);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dialog.show();
		mButton = (Button) dialog.findViewById(R.id.commentSubmit);
		mEditText = (EditText) dialog.findViewById(R.id.commentEditText);
		//mEditText.setText(text);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//mListener.setOnSubmitListener(mEditText.getText().toString());
				dismiss();
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
		  mCommentsAdapter = new CommentsAdapter(getActivity(), R.id.commentContent, n);

	//	View view = inflater.inflate(R.layout.aroundme_fragment, container, false);

		     ListView listView = (ListView) dialog.findViewById(R.id.aroundme_listview);
		       listView.setAdapter(mCommentsAdapter);
		        
		   //    int width = this.getActivity().getResources().getDisplayMetrics().widthPixels;
		     //   int height = this.getActivity().getResources().getDisplayMetrics().heightPixels;
		      // dialog.getWindow().setLayout(width, height);
		        
		return dialog;
	}
	public void populateComments(){
		if(postOfComment != null){
			//do a get request 
			//new GetDataWebTask(this).execute("http://www.google.com"); Replace google with our URL
			
			//for now, lets populate with dummy comments
			 
			//populate our scrollview
		}
	}
}
