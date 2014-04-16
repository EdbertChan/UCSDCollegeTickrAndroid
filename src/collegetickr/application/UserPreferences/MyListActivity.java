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


import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import collegetickr.application.R;
import collegetickr.library.University;

import com.nhaarman.listviewanimations.ArrayAdapter;

public class MyListActivity extends Activity {

	private ListView mListView;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_college_layout);
		mListView = (ListView) findViewById(android.R.id.list);
	
		mListView.setDivider(null);
				  getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public ListView getListView() {
		return mListView;
	}

	protected ArrayAdapter<University> createListAdapter() {
		return new MyListAdapter(this, getItems());
	}

	private ArrayList<University> getItems() {
		//pull from shared preferences
		ArrayList<University> items = new ArrayList<University>();
		items.add(new University());
		/*for (int i = 0; i < 1000; i++) {
			items.add(i);
		}*/
		return items;
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private static class MyListAdapter extends ArrayAdapter<University> {

		private final Context mContext;

		public MyListAdapter(final Context context, final ArrayList<University> items) {
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
		public View getView(final int position, final View convertView, final ViewGroup parent) {
			TextView tv = (TextView) convertView;
			if (tv == null) {
				tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.list_row, parent, false);
			}
			tv.setText(getItem(position).getUniversityID());
			return tv;
		}
	}
}