package collegetickr.application.UserPreferences;

import java.util.ArrayList;
import java.util.Set;

import collegetickr.library.University;

public class UserPreferenceDefaultSettingsClass {
	private static Set<University> loadFrom = University.defaultSet();
	private static boolean enableNotifications = false;
	public static final String SHARED_PREFS_NAME = "user_settings";

	public static Set<University> getLoadFrom() {
		return loadFrom;
	}

	public static void mergeSetLoadFrom(Set<University> mergeLoadFrom) {
		loadFrom.addAll(mergeLoadFrom);
	}

	public static void setLoadFrom(Set<University> loadFrom) {
		UserPreferenceDefaultSettingsClass.loadFrom = loadFrom;
	}

	public static boolean isEnableNotifications() {
		return enableNotifications;
	}

	public static void setEnableNotifications(boolean enableNotifications) {
		UserPreferenceDefaultSettingsClass.enableNotifications = enableNotifications;
	}

	public static void updateSetPreferences(Set<University> setSelection) {
		loadFrom.addAll(setSelection);
	}
}