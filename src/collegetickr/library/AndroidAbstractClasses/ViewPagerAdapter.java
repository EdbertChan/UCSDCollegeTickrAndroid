package collegetickr.library.AndroidAbstractClasses;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

//Holds a bunch of fragments. Primarily used for swipe to change views. We do not put the
//api/json logic in here because that is considered one way to adding a fragment.
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private int mIndex;

	public ViewPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);

	}

	public void addFragment(Fragment fragment) {

		mFragments.add(fragment);
		notifyDataSetChanged();
	}

	public void addListOfFragments(ArrayList<Fragment> aList) {
		mFragments.addAll(aList);
		notifyDataSetChanged();
	}

	public void setIndex(int index) {
		mIndex = index;
		notifyDataSetChanged();
	}

	public int getCurrentScrollIndex() {
		return mIndex;
	}
	public void deleteFragment(int position){
		mFragments.remove(position);
		notifyDataSetChanged();
	}
	public void addFragment( int position, Fragment f){
		mFragments.add(position, f);
		notifyDataSetChanged();
	}
	@Override
	public Fragment getItem(int position) {
		// need to impliment copy constructors?
		

		return mFragments.get(position);

	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	public void replaceFragment(int position, Fragment f) {
		mFragments.set(position, f);
		notifyDataSetChanged();
	}

	public void addFragmentKeepSpinnerAtEnd(Fragment f) {
		mFragments.add(mFragments.size() - 1, f);
		notifyDataSetChanged();
	}

	public int getmIndex() {
		return mIndex;
	}

	public void setmIndex(int mIndex) {
		this.mIndex = mIndex;
	}

	public List<Fragment> getMfragments() {
		return mFragments;
	}

	/*
	 * public int getItemPosition(Object item) { Fragment fragment =
	 * (Fragment)item;
	 * 
	 * //String title = fragment.getTitle(); int position = -1; for(int i = 0; i
	 * < mFragments.size(); i++){ if(mFragments.get(i).getId() ==
	 * fragment.getId()){ position = i; break; } }
	 * 
	 * if (position >= 0) { return position; } else { return POSITION_NONE; } }
	 */

}