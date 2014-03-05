/*This class only exists to allow the snap swipe modification to a viewPager. We may add more features later. */

package collegetickr.library.AndroidAbstractClasses;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;

	public class OnPageSwipeListener implements OnPageChangeListener {
	    protected float mLastPositionOffset = 0f;
	    ViewPager mPager;
	    
	    public OnPageSwipeListener(ViewPager mPager){
	    	this.mPager = mPager;
	    
	    }
	  
	    @Override
	    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	        if(positionOffset < mLastPositionOffset && positionOffset < 0.9) {
	        	mPager.setCurrentItem(position);
	        } else if(positionOffset > mLastPositionOffset && positionOffset > 0.1) {
	        	mPager.setCurrentItem(position+1);
	        }
	        mLastPositionOffset = positionOffset;
	    }
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
		}
	}