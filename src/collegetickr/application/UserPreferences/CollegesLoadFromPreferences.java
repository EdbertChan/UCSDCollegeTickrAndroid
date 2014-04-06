package collegetickr.application.UserPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.h6ah4i.android.compat.preference.MultiSelectListPreferenceCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import collegetickr.library.University;
import android.app.AlertDialog.Builder;
public class CollegesLoadFromPreferences extends MultiSelectListPreferenceCompat implements Preference.OnPreferenceChangeListener {
	protected Context context;
    public CollegesLoadFromPreferences(final Context context) {
        this(context, null);
        
    }

   public CollegesLoadFromPreferences(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//this is a reference to the actual loadFrom.
       initializeValues();

    }
 private void initializeValues(){
	 
	  ArrayList<University> listLoadFrom = new ArrayList(UserPreferenceClass.getLoadFrom());
      
      String[] setLabels = new String[listLoadFrom.size()];
      Set<String> defaultValues = new HashSet<String>();

      Collections.sort(listLoadFrom, new University.UniversityComparator());

      for (int i = 0; i < listLoadFrom.size(); i++) {
          String preferenceLabel = listLoadFrom.get(i).getUniversityID();
          setLabels[i] = preferenceLabel;
          defaultValues.add(preferenceLabel); // each set enabled by default
      }
      setEntries(setLabels);
      setEntryValues(setLabels);
      setDefaultValue(defaultValues);

      setOnPreferenceChangeListener(this);
     
     /*   CharSequence[] entries = { "English", "French" };
          CharSequence[] entryValues = {"1" , "2"};
          setEntries(entries);
     //  setDefaultValue("1");
       
          setEntryValues(entryValues);*/
 }
    @Override
    protected void showDialog(Bundle state) {
    	initializeValues();
        
       // setEntries(entries());
      //  setEntryValues(entryValues());
        //setValueIndex(initializeIndex());
        
    	super.showDialog(state);
    }
    @Override
    protected void onPrepareDialogBuilder(Builder builder) {
    	final Button addCollegeButton = new Button(context);
    	addCollegeButton.setText("Add a College");
    	builder.setView(addCollegeButton);
    	/*final EditText input = new EditText(context);
    	builder.setView(input);*/
    	
   
    	super.onPrepareDialogBuilder(builder);
    	
    	 final boolean[] checkedItems = getSelectedItems(mNewValues);
    	   
    builder.setMultiChoiceItems(getEntries(), checkedItems,
                 new DialogInterface.OnMultiChoiceClickListener() {
                     public void onClick(DialogInterface dialog, int which,
                             boolean isChecked) {
                         if (isChecked) {
                             mPreferenceChanged |= mNewValues
                                     .add(mEntryValues[which].toString());
                         } else {
                             mPreferenceChanged |= mNewValues
                                     .remove(mEntryValues[which].toString());
                         }
                     }
                 });
    }
    
    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult && mPreferenceChanged) {
            final Set<String> values = mNewValues;
            if (callChangeListener(values)) {
                setValues(values);
            }
        }
        mNewValues = null;
        mPreferenceChanged = false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Set<University> newSets = (Set<University>) newValue;
        if (newSets == null || newSets.isEmpty()) {
            return false; // disallow update
        }
        
        UserPreferenceClass.mergeSetLoadFrom(newSets);
        return true; // approve update, and allow it to be persisted
    }

    @Override
    protected View onCreateDialogView() {
        ListView view = new ListView(getContext());
        view.setAdapter(adapter());
        
        return view;
    }
    private ListAdapter adapter() {
        return new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_multichoice);
    }
    
  /*  @Override
    public CharSequence getSummary() {
        final CharSequence entry = getEntry();
        final CharSequence summary = super.getSummary();
        if (summary == null || entry == null) {
             return null;
        } else {
            return String.format(summary.toString(), entry);
        }
    }
    @Override
    public void setValue(final String value) {
        super.setValue(value);
        
        notifyChanged();
    }*/

}