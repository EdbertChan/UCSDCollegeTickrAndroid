package collegetickr.application;

import java.util.ArrayList;
import java.util.List;

import collegetickr.library.Post;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

//Holds a bunch of fragments. Primarily used for swipe to change views. We do not put the
//api/json logic in here because that is considered one way to adding a fragment.
public class ViewPagerAdapter extends FragmentPagerAdapter {


private final List<Fragment> mFragments = new ArrayList<Fragment>();

public ViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  
    
}
public void addFragment(Fragment fragment){
    mFragments.add(fragment);
    notifyDataSetChanged();
}

public void addListOfFragments(ArrayList<Fragment> aList){
	mFragments.addAll(aList);
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