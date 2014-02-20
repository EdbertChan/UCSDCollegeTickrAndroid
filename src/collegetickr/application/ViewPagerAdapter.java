package collegetickr.application;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{


private final List<Fragment> mFragments = new ArrayList<Fragment>();

public ViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  
    
}
public void addFragment(Fragment fragment){
    mFragments.add(fragment);
    notifyDataSetChanged();
}

@Override
public Fragment getItem(int position) {
	return mFragments.get(position);
}

@Override
public int getCount() {
    return mFragments.size();
}



}