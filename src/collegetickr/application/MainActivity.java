package collegetickr.application;


import android.os.Bundle;
import collegetickr.application.DrawerItems.AbstractNavDrawerActivity;
import collegetickr.application.DrawerItems.NavDrawerActivityConfiguration;
import collegetickr.application.DrawerItems.NavDrawerAdapter;
import collegetickr.application.DrawerItems.NavDrawerItem;
import collegetickr.application.DrawerItems.NavMenuItem;
import collegetickr.application.DrawerItems.NavMenuSection;
import collegetickr.library.IdentifiersList;

public class MainActivity extends AbstractNavDrawerActivity {
    
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
        
        NavDrawerItem[] menu = new NavDrawerItem[] {
                NavMenuSection.create( 100, "Account"),
                NavMenuItem.create(IdentifiersList.signupNumericID,IdentifiersList.signupID, "navdrawer_friends", false, this),
                NavMenuItem.create(IdentifiersList.loginNumericID, IdentifiersList.loginID, "navdrawer_airport", true, this), 
                NavMenuSection.create(200, "School News"),
                NavMenuItem.create(IdentifiersList.guardianNumericID, IdentifiersList.guardianID, "navdrawer_rating", false, this),
                NavMenuItem.create(IdentifiersList.TVNumericID, IdentifiersList.TVID, "navdrawer_eula", false, this),
                NavMenuSection.create(300, "Community"),
                NavMenuItem.create(IdentifiersList.complimentsNumericID, IdentifiersList.complimentsID, "navdrawer_quit", false, this),
                NavMenuItem.create(IdentifiersList.confessionsNumericID, IdentifiersList.confessionsID, " ", false, this)};
        
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
    
    @Override
    protected void onNavItemSelected(int id) {
    	//need to work on these. Just match the ID to the activity.
        switch ((int)id) {
        case IdentifiersList.guardianNumericID:
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PostsViewerBaseClass()).commit();
            break;
        case 102:
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Confessions()).commit();
            break;
        }
    }
}