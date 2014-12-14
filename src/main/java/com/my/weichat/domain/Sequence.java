package com.my.weichat.domain;

public enum Sequence {
	OrderID("Seq_Order_ID"),
	CustomerID("Seq_Customer_ID"),
	BeauticianID("Seq_Beautician_ID");
	
	private String sequenceName;
	private Sequence(String sequenceName){
		this.sequenceName = sequenceName;
	}
	public String toString(){
		return sequenceName;
	}
}
