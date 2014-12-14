package com.my.weichat.domain;

import java.util.Date;

public class Comment {
	private int commentID;
	private int beauticianID;
	private String orderCode;
	private Date commentTime;
	private String commentDetail;
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getBeauticianID() {
		return beauticianID;
	}
	public void setBeauticianID(int beauticianID) {
		this.beauticianID = beauticianID;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getCommentDetail() {
		return commentDetail;
	}
	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}
	
}
