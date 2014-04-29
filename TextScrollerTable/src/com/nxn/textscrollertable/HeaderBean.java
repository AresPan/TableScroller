package com.nxn.textscrollertable;
/**
 * 
 * @author nixiaoning@163.com
 *
 */    
public class HeaderBean{
	private static final String TAG="Header";
	public  static boolean isDoubleHeader=false;
	String header;
	String superHeader;
	int index;
	public HeaderBean(String header, String superHeader,int index) {
		super();
		this.header = header;
		this.index = index;
		this.superHeader = superHeader;
		if(superHeader!=null&&superHeader.contains("null")){
			this.superHeader = null;
		}
		if(this.superHeader!=null)isDoubleHeader=true;
	}
	@Override
	public String toString() {
		return "Header [header=" + header + ", superHeader=" + superHeader
				+ ", index=" + index + "]";
	}

}
