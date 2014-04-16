package collegetickr.application.UserPreferences;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import collegetickr.application.R;
import collegetickr.library.University;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter.CountDownFormatter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter.DeleteItemCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipetoDeleteListFragment extends Fragment implements
		OnDismissCallback, DeleteItemCallback {
	public static final String SHARED_PREFS_NAME = "college_settings";
	private ArrayAdapter<University> adapterOfUniversities;
	
	private static final String DEBUG_TAG = SwipetoDeleteListFragment.class
			.getSimpleName();

	protected ArrayAdapter<University> createListAdapter() {
		// replace by getting from shared preference
		return new MyListAdapter(this.getActivity(), getItems());
	}

	public static ArrayList<University> getItems() {
		ArrayList<University> items = new ArrayList<University>();
		items.add(new University());
		items.add(new University());
		/*
		 * for (int i = 0; i < 1000; i++) { items.add(i); }
		 */
		return items;
	}

	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.choose_college_layout, container,
				false);
		listView = (ListView) v.findViewById(android.R.id.list);
	//	listView.setDivider(null);
		adapterOfUniversities = createListAdapter();
		setSwipeDismissAdapter();
		
		//read from prefernce
		// readAllUniversitiesFromSharedPreferences();
		return v;
	}

	public void readAllUniversitiesFromSharedPreferences() {
		SharedPreferences prefs = this.getActivity().getApplicationContext()
				.getSharedPreferences("settings", Context.MODE_PRIVATE);
		String value = prefs.getString("list", null);

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		// first run it is null
	//	if (adapterOfUniversities != null) {
		
			adapterOfUniversities.clear();
			if(value != null)
			adapterOfUniversities.addAll(Arrays.asList(gson.fromJson(value,
					University[].class)));
		//}

	}

	public void writeAllUniversitiesToSharedPreferences() {
		// SharedPreferences.Editor editor =
		// getPreferences(MODE_PRIVATE).edit();

		Gson gson = new Gson();
		String value = gson.toJson(adapterOfUniversities);
		SharedPreferences prefs = this.getActivity().getApplicationContext()
				.getSharedPreferences("settings", Context.MODE_PRIVATE);
		Editor e = prefs.edit();
		e.putString("list", value);
		e.commit();
	}

	@Override
	public void onPause() {
		super.onPause();
		writeAllUniversitiesToSharedPreferences();
		Log.v(DEBUG_TAG, "onPause called");
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	private void setSwipeDismissAdapter() {
		SwipeDismissAdapter adapter = new SwipeDismissAdapter(
				adapterOfUniversities, this);
		adapter.setAbsListView(listView);
		listView.setAdapter(adapter);
	}

	@Override
	public void onDismiss(final AbsListView listView,
			final int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			adapterOfUniversities.remove(position);
		}
		// Toast.makeText(this, "Removed positions: " +
		// Arrays.toString(reverseSortedPositions), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void deleteItem(final int position) {
		adapterOfUniversities.remove(position);
		adapterOfUniversities.notifyDataSetChanged();
	}

	/* Non-ListViewAnimations related stuff below */

	private static class MyListAdapter extends ArrayAdapter<University> {

		private final Context mContext;

		public MyListAdapter(final Context context, ArrayList<University> items) {
			super(items);
			mContext = context;
		}

		@Override
		public long getItemId(final int position) {
			return getItem(position).hashCode();
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getView(final int position, final View convertView,
				final ViewGroup parent) {
			TextView tv = (TextView) convertView;
			if (tv == null) {
				tv = (TextView) LayoutInflater.from(mContext).inflate(
						R.layout.list_row, parent, false);
			}
			tv.setText("This is row number " + getItem(position).getUniversityID());
			return tv;
		}
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "SwipetoDeleteList [listView="
				+ listView
				+ ", mAdapter="
				+ (adapterOfUniversities != null ? adapterOfUniversities
						.subList(0,
								Math.min(adapterOfUniversities.size(), maxLen))
						: null) + "]";
	}
}