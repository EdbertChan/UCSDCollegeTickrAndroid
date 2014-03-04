package collegetickr.application.Comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import collegetickr.application.R;
import collegetickr.application.R.id;
import collegetickr.application.R.layout;
import collegetickr.library.Comment;

public class CommentsAdapter extends ArrayAdapter<Comment> {

    private List<Comment> mPlaces;

    public CommentsAdapter(Context context, int textViewResourceId, List<Comment> objects) {
        super(context, textViewResourceId, objects);
        mPlaces = objects;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {

        View updateView;
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            updateView = inflater.inflate(R.layout.comment_list_item, null);

            viewHolder = new ViewHolder();

            //content
            viewHolder.commentContent = (TextView) updateView
                    .findViewById(R.id.commentContent);
            //icon
            viewHolder.commentPosterIcon = (ImageView) updateView
                    .findViewById(R.id.commentPosterIcon);

            //PosterName
            viewHolder.commentPosterName = (TextView) updateView
                    .findViewById(R.id.commentPosterName);

            

            updateView.setTag(viewHolder);
        } else {
            updateView = view;
            viewHolder = (ViewHolder) updateView.getTag();
        }

        Comment currentComment = getItem(position);
        viewHolder.commentContent.setText(currentComment.getContent());
        //viewHolder.commentPosterIcon.setText(currentComment.getIconURL());
        viewHolder.commentPosterName.setText(currentComment.getUserName());
        //ImageLoader.getInstance().displayImage(place.getImage(), viewHolder.placeImageView);

        return updateView;
    }

    private static class ViewHolder {
        public TextView commentContent;
        public ImageView commentPosterIcon;
        public TextView commentPosterName;
        
    }
}
