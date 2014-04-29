package com.nxn.textscrollertable;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;
/**
 * wrap scoller
 * @author nixiaoning@163.com
 *
 */
public class ScrollLinerLayout extends LinearLayout {
	private static final String TAG="ScrollLinerLayout";
	private Scroller scrollerview;
	private float mLastionMotionX = 0 ;
	private float mLastionMotionY = 0 ;
	private scrollerLisner lisner;
	private int statues =0;
	private static int isXScroll =1;
	private static int isIgnore =0;
	 private GestureDetector mGestureDetector;
	 private boolean ingnore = false;
	public scrollerLisner getLisner() {
		return lisner;
	}

	public void setLisner(scrollerLisner lisner) {
		this.lisner = lisner;
	}

	public ScrollLinerLayout(Context context) {
		super(context);
		init();
	}

	public ScrollLinerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mGestureDetector = new GestureDetector(new Shoushi());
		scrollerview = new Scroller(getContext());
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						Log.d(TAG, "onTouchEvent ingnore=true");
						ingnore  = true;
						break;
					}
		          return mGestureDetector.onTouchEvent(event);
	}
	@Override
	public void computeScroll() {
		if(scrollerview!=null&&scrollerview.computeScrollOffset()&&lisner!=null){
			Log.d(TAG, "computeScroll "+scrollerview.getFinalX());
			lisner.fling(scrollerview.getFinalX());
			postInvalidate();
		}
		super.computeScroll();
	}
public interface scrollerLisner{
	       public void  scrollertox(int x,boolean isFlying);
 	       public void  fling(int dir);
}
@Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
	int action =ev.getAction();
	float x = ev.getX();
	float y = ev.getY();
    switch (action) {
	case MotionEvent.ACTION_DOWN:
		if(scrollerview!=null){
			scrollerview.abortAnimation();
		}
		//Logger.d(TAG, "onInterceptTouchEvent ingnore=true");
		statues =isIgnore; 
		ingnore  = true;
		mLastionMotionX = x ;
		mLastionMotionY=y;
		break;
	case MotionEvent.ACTION_MOVE:
		int detaX = (int)(mLastionMotionX - x );
		int detaY = (int)(mLastionMotionY - y );	
		if(Math.abs(detaX)>Math.abs(2*detaY)){
			statues =isXScroll;
			//Logger.d(TAG, "onInterceptTouchEvent ACTION_MOVE=isXScroll");
		}else{
			statues =isIgnore;
			//Logger.d(TAG, "onInterceptTouchEvent ACTION_MOVE=isIgnore");
		}
		break;
	case MotionEvent.ACTION_UP:
			statues =isIgnore;
		break;
	case MotionEvent.ACTION_CANCEL:
		statues =isIgnore;
		break;
	default:
		break;
	}
	  return statues==isXScroll?true:false;
}
 
private class Shoushi  extends  SimpleOnGestureListener{
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return super.onSingleTapUp(e);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2,
			float distanceX, float distanceY) {
        if(ingnore){
        	ingnore  = false;//first error
        	//Logger.d(TAG, "onScroll  not"+distanceX);
        }else{
        	//Logger.d(TAG, "onScroll  "+distanceX);
        	if(lisner!=null)
    		lisner.scrollertox((int)(distanceX),false);
        }
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
//		lisner.fling(e1, e2, velocityX, velocityY);
		if(scrollerview!=null&&Math.abs(velocityX)>=2000){
			//Logger.d(TAG, "onFling "+velocityX);
			scrollerview.abortAnimation();
			if(velocityX<0){//l
				scrollerview.startScroll(0, 0, 20, 0,500);
			}else{
			    scrollerview.startScroll(0, 0, -20, 0,500);
			}
			invalidate();
		}
		return super.onFling(e1, e2, velocityX, velocityY);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		//Logger.d(TAG, "onDown ");
		return super.onDown(e);
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		//Logger.d(TAG, "onSingleTapConfirmed ");
		return super.onSingleTapConfirmed(e);
	}
	
}

}
