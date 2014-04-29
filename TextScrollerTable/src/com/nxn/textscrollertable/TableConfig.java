package com.nxn.textscrollertable;

import java.util.List;

import android.content.Context;
/**
 * 
 * @author nixiaoning@163.com
 *
 */
public class TableConfig {
	  protected int hederTextColor=-1;
	  protected int tableTextColor=-1;
	  protected int tableDiverColor=-1;
	  protected int headerDiverColor=-1;
	  protected int headerTextSize=-1;
	  protected int tableTextSize=-1;
	  protected int headerDiverSize=-1;
	  protected int tableDiverSize=-1;
	  protected int startScollerColIndex=-1;// if is 0 col 0 will not scoller  if is 1  col 0 and 1 will not scroller
	  protected int scrollerDistance=-1;
	  protected int tableItemHeight=-1;
	  protected int headerItemHeight=-1;
	  //
	  protected List<String[]> Tablecells;
	  protected HeaderBean[] Tablehead;
	  protected	 float[] cellwidths;
	  //
    public TableConfig(ScrollerTable.Builder builder,Context mContext) throws Exception{
    	
    	hederTextColor=builder.getHederTextColor()!=-1?builder.getHederTextColor():mContext.getResources().getColor(android.R.color.black);
    	headerDiverColor=builder.getHeaderDiverColor()!=-1?builder.getHeaderDiverColor():mContext.getResources().getColor(android.R.color.black);
    	tableDiverColor=builder.getTableDiverColor()!=-1?builder.getTableDiverColor():mContext.getResources().getColor(android.R.color.black);
    	tableTextColor=builder.getTableTextColor()!=-1?builder.getTableTextColor():mContext.getResources().getColor(android.R.color.black);
    	//
    	headerTextSize=builder.getHeaderTextSize()!=-1?builder.getHeaderTextSize():36;
    	tableTextSize=builder.getTableTextSize()!=-1?builder.getTableTextSize():36;
    	tableDiverSize=builder.getTableDiverSize()!=-1?builder.getTableDiverSize():1;
    	headerDiverSize=builder.getHeaderDiverSize()!=-1?builder.getHeaderDiverSize():1;
    	//
    	startScollerColIndex=builder.getStartScollerColIndex()>=-1?builder.getStartScollerColIndex():-1;
    	scrollerDistance = (int) (mContext.getResources().getDisplayMetrics().density*25);
    	tableItemHeight =  (int) (mContext.getResources().getDisplayMetrics().density*50);
    	headerItemHeight= tableItemHeight;
    	//
    	Tablecells = builder.getTablecells();
    	if(Tablecells==null){
    		throw new Exception("table data can not be null");
    	}
    	Tablehead= builder.getTablehead();
    	if(Tablecells==null){
    		throw new Exception("head data can not be null");
    	}
    	cellwidths = builder.getColWidth();
    	if(Tablecells==null){
    		throw new Exception("colwidth  can not be null");
    	}
    }
}
