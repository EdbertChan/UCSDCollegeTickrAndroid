package collegetickr.application.FragmentApplicationsForNavDrawer;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import collegetickr.application.R;
import collegetickr.application.Confessions.ViewAllConfessions;
import collegetickr.application.NavigationDrawer.DrawerItems.AbstractNavDrawerActivity;
import collegetickr.application.NavigationDrawer.DrawerItems.NavDrawerActivityConfiguration;
import collegetickr.application.NavigationDrawer.DrawerItems.NavDrawerAdapter;
import collegetickr.application.NavigationDrawer.DrawerItems.NavDrawerItem;
import collegetickr.application.NavigationDrawer.DrawerItems.NavMenuItem;
import collegetickr.application.NavigationDrawer.DrawerItems.NavMenuSection;
import collegetickr.application.SubmitComplimentsConfessions.Compliments;
import collegetickr.application.UserPreferences.PreferenceListFragment.OnPreferenceAttachedListener;
import collegetickr.application.UserPreferences.ListOfCollegesToPullFromActivity;
import collegetickr.application.UserPreferences.SwipetoDeleteListFragment;
import collegetickr.application.UserPreferences.UserPreferencesFragment;

import collegetickr.library.ApplicationSettings;
import collegetickr.library.IdentifiersList;
import collegetickr.library.TestingFragments.EmptyFragment;

public class MainActivity extends AbstractNavDrawerActivity implements
		OnPreferenceAttachedListener {
	static NavDrawerItem[] menu;
	static FragmentManager manager;
	public static final String DEBUG_TAG = "MainActivity";
	public static ImageLoader imageLoader;
	private static boolean postSyncAdapterRunning = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			// just make a the main Fragment
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new ConfessionNavDrawer())
					.commit();
		}
		imageLoader = ImageLoader.getInstance();
		imageLoader.getInstance().init(
				ApplicationSettings
						.defaultImageLoaderConfiguration(this));
		lockNavigationDrawer();
		setupActionBar();
		// acount code
		// mAccount = CreateSyncAccount(this);
	}

	private void lockNavigationDrawer() {
		// For now, we lock the drawer
		DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		getActionBar().setHomeButtonEnabled(false);
	}

	private void setupActionBar() {
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setDisplayShowCustomEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		// ab.setIcon(R.drawable.your_left_action_icon);
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar_for_scrolling, null);

		TextView titleTV = (TextView) v.findViewById(R.id.title);
		// Typeface font =
		// Typeface.createFromAsset(getAssets(),"fonts/your_custom_font.ttf");
		// titleTV.setTypeface(font);

		ab.setCustomView(v);

		ab.setHomeButtonEnabled(true);
	}

	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
		// setupActionBar();
		menu = new NavDrawerItem[] {
				NavMenuItem.create(IdentifiersList.confessionsNumericID,
						IdentifiersList.confessionsID, " ", true, this),
				NavMenuSection.create(300, "Settings"),
				NavMenuItem.create(IdentifiersList.preferenceNumericID,
						IdentifiersList.preferenceID,
						"preference icon string goes here", true, this), };
		/*
		 * menu = new NavDrawerItem[] { NavMenuSection.create(100,
		 * "School News"), NavMenuItem.create(IdentifiersList.guardianNumericID,
		 * IdentifiersList.guardianID, "icon string", true, this),
		 * NavMenuItem.create(IdentifiersList.TVNumericID, IdentifiersList.TVID,
		 * "icon string", true, this), NavMenuSection.create(200, "Community"),
		 * NavMenuItem.create(IdentifiersList.complimentsNumericID,
		 * IdentifiersList.complimentsID, "icon string", true, this),
		 * NavMenuItem.create(IdentifiersList.confessionsNumericID,
		 * IdentifiersList.confessionsID, " ", true, this),
		 * NavMenuSection.create(300, "Account"),
		 * NavMenuItem.create(IdentifiersList.loginNumericID,
		 * IdentifiersList.loginID, "login icon string goes here", true, this),
		 * };
		 */

		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
		navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
		navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		navDrawerActivityConfiguration.setNavItems(menu);
		navDrawerActivityConfiguration
				.setDrawerShadow(R.drawable.drawer_shadow);
		navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
		navDrawerActivityConfiguration
				.setDrawerCloseDesc(R.string.drawer_close);
		navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
				this, R.layout.navdrawer_item, menu));

		return navDrawerActivityConfiguration;

	}

	// add a function that will allow us to add in a navdrawer item to logout
	// and change account settings?
	// or we can add 3 nav drawers under the account settings
	@Override
	public void onNavItemSelected(int id) {

		// need to work on these. Just match the ID to the activity we want.

		switch ((int) id) {
		case IdentifiersList.guardianNumericID:
			replaceFragment(new EmptyFragment());
			break;

		case IdentifiersList.complimentsNumericID:
			replaceFragment(new Compliments());
			break;
		case IdentifiersList.confessionsNumericID:
			 replaceFragment(new ConfessionNavDrawer());
			//LocationFound();
			break;
		case IdentifiersList.preferenceNumericID:
			replaceFragment(new UserPreferencesFragment());
			break;
		}

	}

	// pulls fragment from backstack if it already exists. This allows us to
	// save
	// user's data every time we go to the fragment. This also assumes that we
	// have
	// only one of each fragment but we can change it by changing the
	// backStateName to
	// something else (that's the identifier for each instance of the class in
	// the stack).
	// however, we may want to figure out how to automatically go to the front
	// without
	// always scrolling throug hall the stuff.
	public void replaceFragment(Fragment fragment) {

		Log.v(DEBUG_TAG, "replaceFragment called");
		String backStateName = fragment.getClass().getName();

		FragmentManager manager = getSupportFragmentManager();
		boolean fragmentPopped = manager
				.popBackStackImmediate(backStateName, 0);

		// fragment not in back stack, create it.
		if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
			Log.d(DEBUG_TAG, "creating new Fragment. Wasnt in the stack");
			FragmentTransaction ft = manager.beginTransaction();
			ft.replace(R.id.content_frame, fragment, backStateName);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(backStateName);
			ft.commit();

		} else {
			Log.d(DEBUG_TAG, "Already exists. Calling it from the stack.");
		}

	}

	public boolean isPostSyncAdapterRunning() {
		return postSyncAdapterRunning;
	}

	public static void setPostSyncAdapterRunning(boolean syncAdapterRunning) {
		postSyncAdapterRunning = syncAdapterRunning;
	}

	@Override
	public void onPreferenceAttached(PreferenceScreen root, int xmlId) {
		// TODO Auto-generated method stub

	}

}