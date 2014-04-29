package com.nxn.textscrollertable.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;

import com.nxn.textscrollertable.HeaderBean;
import com.nxn.textscrollertable.R;
import com.nxn.textscrollertable.ScrollerTable;

public class TextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HeaderBean a = new HeaderBean("code", null, 0);
		HeaderBean a2 = new HeaderBean("name", null, 1);
		HeaderBean a3 = new HeaderBean("UpOrDown(%)", null, 2);
		HeaderBean a4 = new HeaderBean("The latest price($)", null, 3);
		HeaderBean a5 = new HeaderBean("2014.04.23", "The relative market interval price(%)", 4);
		HeaderBean a6 = new HeaderBean("2014.04.24", "The relative market interval price(%)", 5);
		HeaderBean a7 = new HeaderBean("2014.04.25", "The relative market interval price(%)", 6);
		HeaderBean a8 = new HeaderBean("2014.04.28", "The relative market interval price(%)", 7);
		HeaderBean a9 = new HeaderBean("2014.04.29", "The relative market interval price(%)", 8);
		HeaderBean[] headers = { a, a2, a3, a4, a5, a6, a7, a8, a9 };
		// "600234",
		// "山水文化",
		// "--",
		// "--",
		// 10.22,
		// 0.5,
		// 1,
		// 1.62,
		// 0.06
		List<String[]> list = new ArrayList<String[]>(40);
		for (int i = 0; i < 40; i++) {
			String data[] = new String[9];
			data[0] = "60000"+(int)(Math.random()*10);
			data[1] = "demo"+(int)(Math.random()*10);
			data[2] = "--";
			data[3] = "--";
			data[4] = "10.2"+(int)(Math.random()*10);
			data[5] = "0.5"+(int)(Math.random()*10);
			data[6] = "1"+(int)(Math.random()*10);
			data[7] = "1.62"+(int)(Math.random()*10);
			data[8] = "0.66"+(int)(Math.random()*10);
			list.add(data);
		}
		//
		Paint paint = new Paint();
		paint.setTextSize(getResources().getDimensionPixelSize(
				R.dimen.mtablerowtextsize) + 5);
		float[] cellwidth = { paint.measureText("code00"),
				paint.measureText("name00"), paint.measureText("UpOrDown(%)"),
				paint.measureText("The latest price($)"), paint.measureText("2014.04.23"),
				paint.measureText("2014.04.24"),
				paint.measureText("2014.04.25"),
				paint.measureText("2014.04.28"),
				paint.measureText("2014.04.29") };
		ScrollerTable.Builder builder = new ScrollerTable.Builder(this);
		builder.
		setCellwidths(cellwidth).
		setTDdata(list).setTHdata(headers).
		setStartScollerColIndex(0).
		setHeaderDiverColor(getResources().
				getColor(R.color.headerDiverColor))
				.setTableDiverColor(getResources().
				getColor(R.color.tableDiverColor))
				.setTableTextColor(getResources().getColor(R.color.tabletextColor)).
				setHederTextColor(getResources().getColor(R.color.headertextColor));
		ScrollerTable table;
		try {
			table = builder.create();
			table.setBackgroundColor(getResources().getColor(android.R.color.white));
			setContentView(table);
			table.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
