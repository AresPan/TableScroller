package com.nxn.textscrollertable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
/**
 * 
 * @author nixiaoning@163.com
 *
 */
public abstract class BaseTable extends FrameLayout implements View.OnClickListener, ScrollLinerLayout.scrollerLisner,
AbsListView.OnScrollListener{
	public BaseTable(Context context) {
		super(context);
	}
	public BaseTable(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public BaseTable(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	ScrollLinerLayout  getScrollLinerLayout(Context mContext,ScrollLinerLayout.scrollerLisner lisnter){
		ScrollLinerLayout layout = new ScrollLinerLayout(mContext);
		layout.setLisner(lisnter);
		layout.setLayoutParams(ViewLayoutParamsFactory.getMMLinerP());
		return layout;
	}
	LinearLayout  getContentLayout(Context mContext){
		LinearLayout content = new LinearLayout(
				mContext);
		content.setGravity(Gravity.CLIP_HORIZONTAL);
		content.setLayoutParams(ViewLayoutParamsFactory.getMMLinerP());
		content.setOrientation(LinearLayout.VERTICAL);
		return content;
	}
	View getLine(Context mContext,int color){
		View top = new View(mContext);
		top.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, 1));
		top.setBackgroundColor(color);
		return top;
	}
	 View getLineRoate(Context mContext,int color){
		View top = new View(mContext);
		top.setLayoutParams(new LayoutParams(
				1,LayoutParams.FILL_PARENT));
		top.setBackgroundColor(color);
		return top;
	}
	 LinearLayout getHeaderLayout(Context mContext,int color){
		LinearLayout header = new LinearLayout(
				mContext);
		header.setLayoutParams(ViewLayoutParamsFactory.getMWLinerP());
		header.setOrientation(LinearLayout.HORIZONTAL);
		header.setBackgroundColor(color);
		return header;
	}
	
	   LinearLayout  getHeaderScorllPartLayout(Context mContext,int color){
		LinearLayout headScroller = new LinearLayout(mContext);
		headScroller
				.setLayoutParams(ViewLayoutParamsFactory.getWWLinerP());
		headScroller
				.setOrientation(LinearLayout.HORIZONTAL);
		return headScroller;
	}

}
