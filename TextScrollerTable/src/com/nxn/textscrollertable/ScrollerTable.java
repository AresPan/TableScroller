package com.nxn.textscrollertable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * @author nixiaoning@163.com
 *
 */
public class ScrollerTable extends BaseTable {
	private static final String TAG="ScrollerTable";
	private LinearLayout headScroller;
	public static final LayoutParams FULL = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	public static final int TEXTSIZE = 20;
	private ScrollLinerLayout rightHsv;
	private ListView rightLst;
	TableRowAdapter tablerowadapter;
	private Context mContext;
	protected ScrollerTable(Context context) {
		super(context);
		mContext= context;
	}
	protected ScrollerTable(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	protected ScrollerTable(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	protected boolean isEditAgain = false;
	public class TableRowAdapter extends BaseAdapter {
		@Override
		public boolean isEnabled(int position) {
			if (position > Tablecells.size() - 1) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public int getCount() {
			if (Tablecells != null && Tablecells.size() > 0) {
				return Tablecells.size() + 1;
			} else {
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = new LinearLayout(mContext);
				holder.listitemScroll = new LinearLayout(mContext);// ------------scroll
				int collength = Tablecells.get(0).length;
				for (int j = 0; j < collength;) {
					TextView tv = new TextView(mContext);
					tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
							cof.tableTextSize);
					tv.setTextColor(getResources().getColor(R.color.black));
					// tv.setSingleLine();
					tv.setEllipsize(TruncateAt.END);
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							(int)cellwidths[j], LinearLayout.LayoutParams.WRAP_CONTENT);
					lp.gravity = Gravity.CENTER;
					tv.setMinimumHeight(cof.tableItemHeight);
					tv.setGravity(Gravity.CENTER);
					// ------------scrolll
					if (CKADD(j)) {// 
						((LinearLayout) convertView).addView(tv, lp);
					} else {
						holder.listitemScroll.addView(tv, lp);
					}
					holder.listItemValues.add(tv);
					j++;
				}
				LinearLayout.LayoutParams lp2 = ViewLayoutParamsFactory.getWWLinerP(Gravity.CENTER);
				convertView.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.hotidealistview_item_bg));
				((LinearLayout) convertView)
						.addView(holder.listitemScroll, lp2);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.listitemScroll.scrollTo(headScroller.getScrollX(), 0);
			if (position + 1 <= Tablecells.size()) {
				for (int i = 0, m = 0; m < Tablecells.get(0).length
						&& i < holder.listItemValues.size();) {
						String TablecellpmStr = Tablecells.get(position)[m];
						holder.listItemValues.get(i).setText(TablecellpmStr);
						i++;
					   m++;
				}
			} else {
				// Generally does not occur
				for (int i = 0; i < holder.listItemValues.size(); i++) {
					holder.listItemValues.get(i).setText("");
				}
			}
			return convertView;
		}
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		defalutx = 0;
		scrollsetdefalut(0);
	}

	public void scrollsetdefalut(int dex) {
		if (headScroller != null && rightLst != null) {
			headScroller.scrollTo(0, 0);
			tableScroller(0, true);
		}
	}

	@Override
	public void fling(int dir) {
		if (dir > 0) {
			scrollertox(cof.scrollerDistance, true);
		} else {
			scrollertox(-cof.scrollerDistance, true);
		}
	}
	protected boolean CKADD(int j){
		if(j<=cof.startScollerColIndex){
			return true;
		}
		return false;
	}
	static class ViewHolder {
		ArrayList<TextView> listItemValues = new ArrayList<TextView>();
		LinearLayout listitemScroll;// ------------scroll
	}
	 protected List<String[]> Tablecells;
	 protected HeaderBean[] Tablehead;
	 float[] cellwidths;
	public void show() {
						this.cellwidths =cof.cellwidths;
						this.Tablehead =cof.Tablehead;
						this.Tablecells =cof.Tablecells;
					if (Tablecells != null && cellwidths != null
							&& Tablehead != null && Tablecells.size() > 0
							&& Tablehead.length > 0) {
						try {
							rightHsv = getScrollLinerLayout(ScrollerTable.this.getContext(),ScrollerTable.this);
							LinearLayout contentOut =getContentLayout(ScrollerTable.this.getContext());
							View top =getLine(cof.headerDiverColor);
							contentOut.addView(top);
							LinearLayout header =  getHeaderLayout(ScrollerTable.this.getContext(),0);
							headScroller = getHeaderScorllPartLayout(ScrollerTable.this.getContext(),0);
							for (int i = 0; i <Tablehead.length; i++) {
								 HeaderBean headbean = Tablehead[i];
								 i = createHeader(header,headScroller,headbean,i,true);
							}
							header.addView(headScroller);// ---scroll
							contentOut.addView(header);// header
							View fenggeheadend =getLine(cof.headerDiverColor);
							contentOut.addView(fenggeheadend);
							rightLst = getlistView();
							tablerowadapter = new TableRowAdapter();
							rightLst.setAdapter(tablerowadapter);
							contentOut.addView(rightLst);
							rightHsv.addView(contentOut);
							addView(rightHsv);
							return;
						} catch (Exception e) {
						}
					}
				}
	@Override
	public void onClick(View v) {
		
	}
	protected float defalutx = 0;
	protected static final long OFFSETCHANGETABTIME =2000;
	@Override
	public void scrollertox(int delx, boolean isFlying) {
		if (headScroller != null && rightLst != null) {
			if (defalutx == 0 && cellwidths != null && cellwidths.length > 0) {
				int size = cellwidths.length;
				for (int i = 0; i < size; i++) {
					if (CKADD(i)) {
					} else {
						defalutx = defalutx + cellwidths[i];
					}
				}
				defalutx = defalutx - headScroller.getWidth();
			}else{
			}
			//
			int inval = headScroller.getScrollX() + delx;
			if (headScroller != null && rightLst != null && inval >= -1
					&& inval <= defalutx) {
				headScroller.scrollBy(delx, 0);
				tableScroller(delx, false);
			} else {
			}
		}
	}
	private void tableScroller(int delx, boolean isreset) {
		Log.d(TAG, "tableScroller");
		int chilredcount =rightLst.getCount();
		for (int j = 0; j < chilredcount; j++) {
			View childrentemp = rightLst.getChildAt(j);
			if (childrentemp instanceof LinearLayout) {
				LinearLayout children = (LinearLayout) childrentemp;
				View itemtemp = null;
				 itemtemp = children.getChildAt(cof.startScollerColIndex+1);
//				if(children.getChildCount()==1){
//				 itemtemp = children.getChildAt(0);
//				}else{
//				 itemtemp = children.getChildAt(1);
//				}
				if (itemtemp instanceof LinearLayout) {
					LinearLayout item = (LinearLayout) itemtemp;
					if (isreset) {
						item.scrollTo(0, 0);
					} else {
						item.scrollBy(delx, 0);
					}
				}
			}
		}
	}
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	   protected int createHeader(LinearLayout headerout,LinearLayout headScroller,HeaderBean headbean,int i,boolean forstocklist) throws Exception{
			 View headerlocal=null;
			 int currentindex=headbean.index;
		      if(headbean.superHeader!=null){
		    	  List<HeaderBean> headbeans = new ArrayList<HeaderBean>();
		    	  for(int j=headbean.index;j<Tablehead.length;j++){
		    		  if(headbean.superHeader.equals(Tablehead[j].superHeader)){
		    			  headbeans.add(Tablehead[j]);
	     		        }else{
	     		        	break;
	     		        }
		    	  }
		    	  if(headbeans.size()==0){
		    		  throw new Exception("createHeader headbean size = 0 error");
		    	  }
		    	  headerlocal =  getSortStatusTextViewD(headbeans);
		    	  currentindex = headbeans.size()>0?headbeans.get(headbeans.size()-1).index:headbean.index;
		      }else{
		    	  headerlocal= getSortStatusTextView(HeaderBean.isDoubleHeader,headbean);
		    	  currentindex =headbean.index;
		      }
		      if (CKADD(i)) {
		    	    headerout.addView(headerlocal);
		    	    headerout.addView(getLineRoate(cof.headerDiverColor));
				} else {
					headScroller.addView(headerlocal);
					headScroller.addView(getLineRoate(cof.headerDiverColor));
				}
			  return currentindex;
		}
		protected View getSortStatusTextViewD(List<HeaderBean> headbeans) {
			LinearLayout doublehead =getDoubleHeadLayout();
			View head =   getHeadViewForSuper(headbeans.get(0).superHeader);
			doublehead.addView(head);
			doublehead.addView(getLine(cof.headerDiverColor));
			LinearLayout headsub = new LinearLayout(
					ScrollerTable.this.getContext());
			headsub.setOrientation(LinearLayout.HORIZONTAL);
			int headsubwidth = 0;
			for(HeaderBean hb :headbeans){
				 View headersubitem = getSortStatusTextView(false, hb);
				 headsub.addView(headersubitem);
				 headsub.addView(getLineRoate(cof.headerDiverColor));
				 headsubwidth = headsubwidth+	(cellwidths.length>hb.index?(int)cellwidths[hb.index]:0);
			}
			doublehead
			.setLayoutParams(new LinearLayout.LayoutParams(
					headsubwidth,
					LayoutParams.WRAP_CONTENT));
	        doublehead.addView(headsub);
//	        doublehead.addView(getLineRoate());
			return doublehead;
		}
		
		/**
		 *  
		 * @param isdoubleHeader
		 * @param headbean
		 * @param forstocklist 
		 * @return
		 */
	    protected TextView getSortStatusTextView(boolean isdoubleHeader,HeaderBean headbean){
	    	TextView head = new TextView(
	    			mContext);
	    	head.setGravity(Gravity.CENTER);
	    	head.setTextSize(
					TypedValue.COMPLEX_UNIT_PX,
					cof.headerTextSize);
			head.setTextColor(cof.hederTextColor);
	    	if(!isdoubleHeader){
				head.setLayoutParams(new LayoutParams(
						(int)cellwidths[headbean.index],
						cof.headerItemHeight));
				head.setText(headbean.header);
				// one  one
			}else{
				head.setLayoutParams(new LayoutParams(
						(int)cellwidths[headbean.index],
						cof.headerItemHeight * 2));
				//  one  twos
					head.setText(headbean.header);
			}
	    			return head;
	    }
		View getLine(int color){
			View top = new View(ScrollerTable.this.getContext());
			top.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, 1));
			top.setBackgroundColor(color);
			return top;
		}
		 View getLineRoate(int color){
			View top = new View(ScrollerTable.this.getContext());
			top.setLayoutParams(new LayoutParams(
					1,LayoutParams.FILL_PARENT));
			top.setBackgroundColor( 
					color);
			return top;
		}
		 LinearLayout getHeaderLayout(){
			LinearLayout header = new LinearLayout(
					ScrollerTable.this.getContext());
			header.setLayoutParams(ViewLayoutParamsFactory.getMWLinerP());
			header.setOrientation(LinearLayout.HORIZONTAL);
			header.setBackgroundColor(getResources()
					.getColor(
							R.color.new_table_header_bg));
			return header;
		}
		 protected LinearLayout getDoubleHeadLayout(){
				LinearLayout doublehead 	= new LinearLayout(
						ScrollerTable.this.getContext());
				doublehead
						.setLayoutParams(new LinearLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
				doublehead
						.setOrientation(LinearLayout.VERTICAL);
				return doublehead;
			}
		 protected View getHeadViewForSuper(String superhead){
				TextView head = new TextView(ScrollerTable.this.getContext());
				head.setGravity(Gravity.CENTER);
				head.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT,
						cof.headerItemHeight));
				head.setGravity(Gravity.CENTER);
				head.setText(superhead);
				head.setTextSize(
						TypedValue.COMPLEX_UNIT_PX,
						cof.headerTextSize);
				head.setTextColor(cof.hederTextColor);
				return head;
			}
			protected ListView getlistView() {
				// rightLst.
				ListView rightLst = new ListView(ScrollerTable.this.getContext());
				rightLst.setVerticalScrollBarEnabled(false);
				rightLst.setCacheColorHint(Color.TRANSPARENT);
				rightLst.setDivider(new ColorDrawable(cof.tableDiverColor));
				rightLst.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				rightLst.setHeaderDividersEnabled(false);
				rightLst.setFooterDividersEnabled(false);
				rightLst.setDividerHeight(getResources()
						.getDimensionPixelSize(
								R.dimen.search_list_item_dividerheight));
				rightLst.setOnScrollListener(ScrollerTable.this);
				return rightLst;
			}
			//#####################3
	public static class Builder{
				private List<String[]> Tablecells;
				private HeaderBean[] Tablehead;
				 private	 float[] cellwidths;
		        private Context mContext;
				private int hederTextColor=-1;
		        private int headerTextSize=-1;
		        private int headerDiverColor=-1;
		        private int headerDiverSize=-1;
		        private int tableTextColor=-1;
		        private int tableTextSize=-1;
		        private int tableDiverColor=-1;
		        private int tableDiverSize=-1;
		        private int startScollerColIndex=-1;// if is 0 col 0 will not scoller  if is 1  col 0 and 1 will not scroller
		        public Builder setHederTextColor(int hederTextColor) {
					this.hederTextColor = hederTextColor;
					return this;
				}
				public Builder setHeaderTextSize(int headerTextSize) {
					this.headerTextSize = headerTextSize;
					return this;
				}

				public Builder setHeaderDiverColor(int headerDiverColor) {
					this.headerDiverColor = headerDiverColor;
					return this;
				}

				public Builder setHeaderDiverSize(int headerDiverSize) {
					this.headerDiverSize = headerDiverSize;return this;
				}

				public Builder setTableTextColor(int tableTextColor) {
					this.tableTextColor = tableTextColor;return this;
				}

				public Builder setTableTextSize(int tableTextSize) {
					this.tableTextSize = tableTextSize;return this;
				}

				public Builder setTableDiverColor(int tableDiverColor) {
					this.tableDiverColor = tableDiverColor;return this;
				}

				public Builder setTableDiverSize(int tableDiverSize) {
					this.tableDiverSize = tableDiverSize;return this;
				}

				public Builder setStartScollerColIndex(int startScollerColIndex) {
					this.startScollerColIndex = startScollerColIndex;return this;
				}
				public  Builder(Context mContext){
					this.mContext = mContext;
				}
				public  ScrollerTable create() throws Exception{
					ScrollerTable table = new ScrollerTable(mContext);
					table.setConfig(new TableConfig(this,mContext));
					return table;
				}
				 public List<String[]> getTablecells() {
						return Tablecells;
					}
					public Builder setTDdata(List<String[]> tablecells) {
						Tablecells = tablecells;return this;
					}
					public HeaderBean[] getTablehead() {
						return Tablehead;
					}
					public Builder setTHdata(HeaderBean[] tablehead) {
						Tablehead = tablehead;return this;
					}
					public Builder setCellwidths(float[] cellwidths) {
						this.cellwidths = cellwidths;return this;
					}
				//_________________________________
				
				 public Context getmContext() {
						return mContext;
					}
					public int getHederTextColor() {
						return hederTextColor;
					}
					public int getHeaderTextSize() {
						return headerTextSize;
					}
					public int getHeaderDiverColor() {
						return headerDiverColor;
					}
					public int getHeaderDiverSize() {
						return headerDiverSize;
					}
					public int getTableTextColor() {
						return tableTextColor;
					}
					public int getTableTextSize() {
						return tableTextSize;
					}
					public int getTableDiverColor() {
						return tableDiverColor;
					}
					public int getTableDiverSize() {
						return tableDiverSize;
					}
					public int getStartScollerColIndex() {
						return startScollerColIndex;
					}
					public float[] getColWidth() {
						return cellwidths;
					}
			}
	 TableConfig cof;
	public void setConfig(TableConfig config) {
		cof = config;
	}
}
