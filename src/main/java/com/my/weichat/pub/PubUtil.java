package com.my.weichat.pub;

public class PubUtil {
	public static String mosaicString(String str,int length){
		if(str==null || "".equals(str.trim())) return null;
		if(str.length() < length){
			int idx = str.length()/2;
			str = str.substring(0,idx)+"****"+str.substring(idx);
		}else{
			int idx = length/2;
			str = str.substring(0,idx)+"****"+str.substring(str.length()-idx);
		}
		return str;
	}
	public static void main(String args[]){
		System.out.println(mosaicString("fdswfg",6));
	}

}
