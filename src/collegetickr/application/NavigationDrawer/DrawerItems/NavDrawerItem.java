package collegetickr.application.NavigationDrawer.DrawerItems;

public interface NavDrawerItem {
    public int getId();
    public String getLabel();
    public int getType();
    public boolean isEnabled();
    public boolean updateActionBarTitle();
}