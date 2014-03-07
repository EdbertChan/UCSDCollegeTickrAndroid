package collegetickr.application;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import collegetickr.application.DrawerItems.AbstractNavDrawerActivity;
import collegetickr.application.DrawerItems.NavDrawerActivityConfiguration;
import collegetickr.application.DrawerItems.NavDrawerAdapter;
import collegetickr.application.DrawerItems.NavDrawerItem;
import collegetickr.application.DrawerItems.NavMenuItem;
import collegetickr.application.DrawerItems.NavMenuSection;
import collegetickr.application.Fragments.Compliments;
import collegetickr.application.NavDrawerFragments.ConfessionNavDrawer;
import collegetickr.application.login.LoginFragment;
import collegetickr.application.login.RegisterFragment;

import collegetickr.library.IdentifiersList;

public class MainActivity extends AbstractNavDrawerActivity{
	static NavDrawerItem[] menu;
	static FragmentManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( savedInstanceState == null ) {
        	//just make a the main Fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Compliments()).commit();
        }
    }

    @Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
        
    	menu = new NavDrawerItem[] {
                NavMenuSection.create(100, "School News"),
                NavMenuItem.create(IdentifiersList.guardianNumericID, IdentifiersList.guardianID, "navdrawer_rating", false, this),
                NavMenuItem.create(IdentifiersList.TVNumericID, IdentifiersList.TVID, "navdrawer_eula", false, this),
                NavMenuSection.create(200, "Community"),
                NavMenuItem.create(IdentifiersList.complimentsNumericID, IdentifiersList.complimentsID, "navdrawer_quit", false, this),
                NavMenuItem.create(IdentifiersList.confessionsNumericID, IdentifiersList.confessionsID, " ", false, this),
                NavMenuSection.create( 300, "Account"),
                NavMenuItem.create(IdentifiersList.loginNumericID, IdentifiersList.loginID, "navdrawer_airport", true, this),};
        
        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);       
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(
            new NavDrawerAdapter(this, R.layout.navdrawer_item, menu ));
        return navDrawerActivityConfiguration;
    }
    
    //add a function that will allow us to add in a navdrawer item to logout and change account settings?
    //or we can add 3 nav drawers under the account settings
    @Override
  public void onNavItemSelected(int id) {
  
    	//need to work on these. Just match the ID to the activity we want.

        switch ((int)id) {
        case IdentifiersList.loginNumericID:
        	replaceFragment(new LoginFragment());
        	break;

        case IdentifiersList.complimentsNumericID:
            //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Compliments()).commit();
        	//fragment = new Compliments();
        	replaceFragment( new Compliments());
        	break;
        case IdentifiersList.confessionsNumericID:
            //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new ConfessionsApplicationFragment()).commit();
        	// replaceFragment(new ConfessionsApplicationFragment());
        	replaceFragment(new ConfessionNavDrawer());
        	
        	break;
        }
    	 
    }
    //pulls fragment from backstack if it already exists. This allows us to save 
    //user's data every time we go to the fragment. 
    //however, we may want to figure out how to automatically go to the front without
    //always scrolling throug hall the stuff.
    public void replaceFragment (Fragment fragment){
    	  String backStateName =  fragment.getClass().getName();
    	  //String fragmentTag = backStateName;

    	  FragmentManager manager = getSupportFragmentManager();
    	  boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
    	  //fragment not in back stack, create it.
    	  if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null){ 

    	    FragmentTransaction ft = manager.beginTransaction();
    	    //ft.replace(R.id.content_frame, fragment, fragmentTag);
    	    ft.replace(R.id.content_frame, fragment, backStateName);
    	    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    	    //ft.addToBackStack(backStateName);
    	    ft.addToBackStack(backStateName);
    	    ft.commit();
    	    
    	  } 
    	//  need to update title: setTitle(fragment.get);
    	  
    	}
    
}