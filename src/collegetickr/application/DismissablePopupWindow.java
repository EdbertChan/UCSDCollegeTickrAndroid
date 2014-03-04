//most likely, we're going to use the same commenting layout for all comment applications. Therefore, we have set this to be the default values
package collegetickr.application;

import java.util.ArrayList;

import collegetickr.application.Comments.CommentsAdapter;
import collegetickr.library.Comment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class DismissablePopupWindow extends android.widget.PopupWindow {
private final int commentingLayout = R.layout.comments_scaffold_layout;

//this is just the linear layout inside the comments layout
private final int commentingLayoutContainer = R.id.commentContainer;
	public DismissablePopupWindow(ViewGroup container, Context context) {
		setUpPopupWindow(container, context, new ArrayList<Comment>(), commentingLayout,commentingLayoutContainer);
	}
	public DismissablePopupWindow(ViewGroup container, Context context, ArrayList<Comment> arrayListComments) {
		setUpPopupWindow(container, context, arrayListComments, commentingLayout,commentingLayoutContainer);
	}

	private void setUpPopupWindow(ViewGroup container, Context context,
			ArrayList<Comment> arrayList, int commentsScaffoldLayout,
			int commentcontainer) {
		// TODO Auto-generated method stub
		try {
			// We need to get the instance of the LayoutInflater

			View layout = LayoutInflater.from(context).inflate(
					commentsScaffoldLayout,
					(ViewGroup) container.findViewById(commentcontainer));
			int width = context.getResources().getDisplayMetrics().widthPixels;
			int height = context.getResources().getDisplayMetrics().heightPixels;
			// dialog.getWindow().setLayout(width, height);

			// KEEP THE POPUPWINDOW THIS IN THIS ORDER!
			// super(layout, width-200,height-200, true);
			setWidth(width);
			setHeight(height - 200);
			setContentView(layout);
			setOutsideTouchable(true);
			setTouchable(true);
			setFocusable(true);
			setBackgroundDrawable(new BitmapDrawable());
			setTouchInterceptor(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
						
						dismiss();
						
						return true;
					}
					return false;
				}
			});
			update();
			showAtLocation(layout, Gravity.CENTER, 0, 0);
			
			// setAnimationStyle(R.style.Animations_popup);
			/*
			 * ArrayList<PostComment> n = new ArrayList<PostComment>();
			 * n.add(new PostComment()); n.add(new PostComment()); n.add(new
			 * PostComment()); n.add(new PostComment()); n.add(new
			 * PostComment()); n.add(new PostComment()); n.add(new
			 * PostComment()); CommentsAdapter mCommentsAdapter = new
			 * CommentsAdapter( getActivity(), R.id.commentContent, n);
			 * 
			 * // View view = inflater.inflate(R.layout.aroundme_fragment,
			 * container, false);
			 */
			CommentsAdapter mCommentsAdapter = new CommentsAdapter( context.getApplicationContext(), R.id.commentContent, arrayList);
			  ListView listView = (ListView) layout.findViewById(R.id.aroundme_listview);
			 listView.setAdapter(mCommentsAdapter);
			 

			Button btnClosePopup = (Button) layout
					.findViewById(R.id.commentSubmit);
			btnClosePopup.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DismissablePopupWindow(Context context) {
		super(context);

	}

}