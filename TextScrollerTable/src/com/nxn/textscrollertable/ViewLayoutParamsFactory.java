package com.nxn.textscrollertable;

import android.widget.LinearLayout;
/**
 * 
 * @author nixiaoning@163.com
 *
 */
public class ViewLayoutParamsFactory {
	public static LinearLayout.LayoutParams getMWLinerP() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		return lp;
	}

	public static LinearLayout.LayoutParams getMWLinerP(int gravity) {
		LinearLayout.LayoutParams lp = getMWLinerP();
		return lp;
	}
	
	public static LinearLayout.LayoutParams getMWLinerP(int gravity, int marge) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = marge;
		return lp;
	}

	public static LinearLayout.LayoutParams getMWLinerP(int gravity,
			int margetop, int left, int right, int bottom) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = margetop;
		lp.rightMargin = left;
		lp.rightMargin = right;
		lp.bottomMargin = bottom;
		return lp;

		
	}
	// ------------------------------------------------------------------------
	public static LinearLayout.LayoutParams getWMLinerP() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		return lp;
	}
	public static LinearLayout.LayoutParams getWMLinerPWight(int weight) {
		LinearLayout.LayoutParams lp = getWMLinerP();
		lp.weight=weight;
		return lp;
	}
	public static LinearLayout.LayoutParams getWMLinerP(int gravity) {
		LinearLayout.LayoutParams lp = getMWLinerP();
		return lp;
	}
	
	public static LinearLayout.LayoutParams getWMLinerP(int gravity, int marge) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = marge;
		return lp;
	}

	public static LinearLayout.LayoutParams getWMLinerP(int gravity,
			int margetop, int left, int right, int bottom) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = margetop;
		lp.rightMargin = left;
		lp.rightMargin = right;
		lp.bottomMargin = bottom;
		return lp;

		
	}
	// ------------------------------------------------------------------------
	public static LinearLayout.LayoutParams getMMLinerP() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		return lp;
	}

	public static LinearLayout.LayoutParams getMMLinerP(int gravity) {
		LinearLayout.LayoutParams lp = getMWLinerP();
		return lp;
	}
	
	public static LinearLayout.LayoutParams getMMLinerP(int gravity, int marge) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = marge;
		return lp;
	}

	public static LinearLayout.LayoutParams getMMLinerP(int gravity,
			int margetop, int left, int right, int bottom) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = margetop;
		lp.rightMargin = left;
		lp.rightMargin = right;
		lp.bottomMargin = bottom;
		return lp;

		
	}
	// ------------------------------------------------------------------------
	public static LinearLayout.LayoutParams getWWLinerP() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		return lp;
	}

	public static LinearLayout.LayoutParams getWWLinerP(int gravity) {
		LinearLayout.LayoutParams lp = getMWLinerP();
		return lp;
	}
	
	public static LinearLayout.LayoutParams getWWLinerP(int gravity, int marge) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = marge;
		return lp;
	}

	public static LinearLayout.LayoutParams getWWLinerP(int gravity,
			int margetop, int left, int right, int bottom) {
		LinearLayout.LayoutParams lp = getMWLinerP(gravity);
		lp.topMargin = margetop;
		lp.rightMargin = left;
		lp.rightMargin = right;
		lp.bottomMargin = bottom;
		return lp;

		
	}
	// ------------------------------------------------------------------------
}
