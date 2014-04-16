package collegetickr.application.UserPreferences;

/*
 * Copyright 2013 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import collegetickr.application.R;
import collegetickr.application.FragmentApplicationsForNavDrawer.MainActivity;
import collegetickr.application.UserPreferences.SearchCollegeDialog.OnMyDialogResult;
import collegetickr.library.University;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter.CountDownFormatter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.contextualundo.ContextualUndoAdapter.DeleteItemCallback;

import java.util.Arrays;

public class ListOfCollegesToPullFromActivity extends MyListActivity implements
		OnDismissCallback, DeleteItemCallback {
	private static final String collegesToFetchFromSharedPreference = "Colleges_To_Pull_From";
	private static final String collegesToFetchFromListID = "Colleges_To_Pull_From_ID";
	private ArrayAdapter<University> adapterOfUniversities;
	private static final String DEBUG_TAG = ListOfCollegesToPullFromActivity.class
			.getSimpleName();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapterOfUniversities = createListAdapter();

		setSwipeDismissAdapter();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.v(DEBUG_TAG, "onPause called");
		readAllUniversitiesFromSharedPreferences();
	}

	@Override
	public void onResume() {
		super.onPause();
		Log.v(DEBUG_TAG, "onPause called");
		writeAllUniversitiesToSharedPreferences();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.view_all_colleges_action_bar_option, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_add_college:
			launchAddCollegeDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	View search_add_college_layout;

	private void launchAddCollegeDialog() {
		SearchCollegeDialog d = new SearchCollegeDialog(this);
		d.setDialogResult(new OnMyDialogResult() {
			public void finish(String result) {
				// now you can use the 'result' on your activity
				// Log.v(DEBUG_TAG, result);
				// just get the university from the result.
				adapterOfUniversities.add(new University("NYU", "nyu"));
			}
		});
		d.show();
	}

	private void setSwipeDismissAdapter() {
		SwipeDismissAdapter adapter = new SwipeDismissAdapter(
				adapterOfUniversities, this);
		adapter.setAbsListView(getListView());
		getListView().setAdapter(adapter);
	}

	@Override
	public void onDismiss(final AbsListView listView,
			final int[] reverseSortedPositions) {
		for (int position : reverseSortedPositions) {
			adapterOfUniversities.remove(position);
		}

	}

	@Override
	public void deleteItem(final int position) {
		adapterOfUniversities.remove(position);
		adapterOfUniversities.notifyDataSetChanged();
	}

	/* Non-ListViewAnimations related stuff below */

	public void readAllUniversitiesFromSharedPreferences() {
		SharedPreferences prefs = getApplicationContext().getSharedPreferences(
				collegesToFetchFromSharedPreference, Context.MODE_PRIVATE);
		String value = prefs.getString(collegesToFetchFromListID, null);

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		// first run it is null
		// if (adapterOfUniversities != null) {

		adapterOfUniversities.clear();
		if (value != null)
			adapterOfUniversities.addAll(Arrays.asList(gson.fromJson(value,
					University[].class)));
		// }

	}

	public void writeAllUniversitiesToSharedPreferences() {
		// SharedPreferences.Editor editor =
		// getPreferences(MODE_PRIVATE).edit();

		Gson gson = new Gson();
		String value = gson.toJson(adapterOfUniversities);
		SharedPreferences prefs = getApplicationContext().getSharedPreferences(
				collegesToFetchFromSharedPreference, Context.MODE_PRIVATE);
		Editor e = prefs.edit();
		e.putString(collegesToFetchFromListID, value);
		e.commit();
	}

}