/*This class only exists to do queue the json activity on swipe to the right. Currently it has no use other than
 * to server as a fancier ontouch listener but customization is available should the need arise.*/
package collegetickr.library;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class OnSwipeTouchListener implements OnTouchListener {

    static final String logTag = "OnSwipeTouchListener";
  
    static final int MIN_DISTANCE = 1;// TODO change this runtime based on screen resolution. for 1920x1080 is to small the 100 distance
    private float downX, downY, upX, upY;
    private int  lastDownPointerId;
    private ViewPager vp;
    public OnSwipeTouchListener(ViewPager vp){
    	this.vp = vp;
    }
    public OnSwipeTouchListener() {
      
    }

    public void onRightToLeftSwipe() {
        Log.i(logTag, "RightToLeftSwipe!");
       
    }

    public void onLeftToRightSwipe() {
        Log.i(logTag, "LeftToRightSwipe!");
           }

    public void onTopToBottomSwipe() {
        Log.i(logTag, "onTopToBottomSwipe!");
      
    }

    public void onBottomToTopSwipe() {
        Log.i(logTag, "onBottomToTopSwipe!");
       
    }
 
   
    public boolean onMultiTouchEvent(View v, MotionEvent event) {
        return true;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
    	
        switch(event.getAction()){
        case MotionEvent.ACTION_MOVE:{
        	 vp.requestDisallowInterceptTouchEvent(true);
            return false;
        }
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
               // lastDownPointerId = event.getPointerId(0);
                
             break;
            }
       
            case MotionEvent.ACTION_UP: {
            
                upX = event.getX();
                upY = event.getY();
                
               
                float deltaX = downX - upX;
            

                // horizontal
          
                    if(deltaX < 0) { 
                        onLeftToRightSwipe();
                      vp.setCurrentItem(vp.getCurrentItem()-1);
                     // vp.setOnTouchListener(new OnSwipeTouchListener());
                      //vp.requestDisallowInterceptTouchEvent(true);
                       return true; 
                    }
                    
                    else if(deltaX > 0) { 
                        onRightToLeftSwipe(); 
                        vp.setCurrentItem(vp.getCurrentItem()+1);
                       // vp.requestDisallowInterceptTouchEvent(true);
                        return true; 
                    }
                

                return true;
            }
            case MotionEvent.ACTION_CANCEL:{
                vp.requestDisallowInterceptTouchEvent(false);
                break;}
        }
        return false;
    }
}