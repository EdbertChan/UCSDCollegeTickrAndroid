package collegetickr.application.UserPreferences;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import collegetickr.application.R;
import collegetickr.library.University;

public class SearchCollegeDialog extends Dialog{

	private ListView list;
	private static final String DEBUG_TAG = SearchCollegeDialog.class.getSimpleName();
	private EditText filterText = null;
	ArrayAdapter<String> adapter = null;
	private static final String TAG = "CityList";
	//where will we pull the list of oclleges from?
	private static final String[] collegeList = { "UCSD", "UCSB", "UCLA" };

	public SearchCollegeDialog(Context context) {
		super(context);

		/** Design the dialog in main.xml file */
		setContentView(R.layout.add_college_dialog_layout);
		this.setTitle("Add New College");
		filterText = (EditText) findViewById(R.id.EditBox);
		filterText.addTextChangedListener(filterTextWatcher);
		list = (ListView) findViewById(R.id.List);
		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, collegeList);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OKListener());
		
	}

	OnMyDialogResult mDialogResult; // the callback

	private class OKListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if (mDialogResult != null) {
			//	mDialogResult.finish(String.valueOf(etName.getText()));
				//Log.i(DEBUG_TAG, collegeList[arg2]);
				 mDialogResult.finish( collegeList[arg2]);
				
			}
			SearchCollegeDialog.this.dismiss();
		}
	}

	public void setDialogResult(OnMyDialogResult dialogResult) {
		mDialogResult = dialogResult;
	}

	public interface OnMyDialogResult {
		void finish(String result);
	}

	private TextWatcher filterTextWatcher = new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			adapter.getFilter().filter(s);
		}
	};

	@Override
	public void onStop() {
		filterText.removeTextChangedListener(filterTextWatcher);
	}
}