package collegetickr.library;

import collegetickr.application.R;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
public class HUD extends Service {
    HUDView mView;
	public static final String DEBUG_TAG = "HUD";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
    	 super.onCreate();
    	 Log.v(DEBUG_TAG, "Creating HUD");
         //Toast.makeText(getBaseContext(),"onCreate", Toast.LENGTH_LONG).show();
         mView = new HUDView(this);
         WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                 WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                 WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                 WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                  PixelFormat.TRANSLUCENT);
     WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
     LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
     View myView = inflater.inflate(R.layout.exit_image_preview_overlay, null);
     myView.setOnTouchListener(new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
        	Log.v(DEBUG_TAG, "Touching View");
           // Log.d(TAG, "touch me");
            return false;
        }
      });

     // Add layout to window manager
     wm.addView(myView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(getBaseContext(),"onDestroy", Toast.LENGTH_LONG).show();
        Log.v(DEBUG_TAG, "onDestroy");
        if(mView != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mView);
            mView = null;
        }
    }
}

class HUDView extends ViewGroup {
    private Paint mLoadPaint;
    public static final String DEBUG_TAG = "HUDView";
  
    public HUDView(Context context) {
        super(context);
    //    Toast.makeText(getContext(),"HUDView", Toast.LENGTH_LONG).show();
    	Log.v(DEBUG_TAG, "HUDView Constructor");
        mLoadPaint = new Paint();
        mLoadPaint.setAntiAlias(true);
        mLoadPaint.setTextSize(10);
        mLoadPaint.setARGB(255, 255, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Hello World", 5, 15, mLoadPaint);
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	super.onTouchEvent(event);
        // ATTENTION: GET THE X,Y OF EVENT FROM THE PARAMETER
        // THEN CHECK IF THAT IS INSIDE YOUR DESIRED AREA
    	Log.v(DEBUG_TAG, "HUDView OnTouchEvent");
        

        //Toast.makeText(getContext(),"onTouchEvent", Toast.LENGTH_LONG).show();
        return true;
    }
}