package collegetickr.application.UserPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v4.preference.PreferenceFragment;
import collegetickr.application.R;

public class UserPreferencesFragment extends PreferenceFragment implements
		SharedPreferences.OnSharedPreferenceChangeListener {


	@Override
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		PreferenceManager preferenceManager = getPreferenceManager();
		preferenceManager.setSharedPreferencesName(UserPreferenceDefaultSettingsClass.SHARED_PREFS_NAME);
		addPreferencesFromResource(R.xml.settings);

		preferenceManager.getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);

	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
	}

}