package com.my.weichat.domain;

import java.util.Date;

public class Order {
	private int orderID;
	private String orderCode;
	private Date orderDate;
	private String orderTime;
	private Date bakOrderDate;
	private String bakOrderTime;
	private double orderPrice;
	private Date orderDealTime;
	private String orderStatus;
	private String orderRemark;
	private String customerID;
	private int beauticianID;
	private String beauticianName;
	private String pet;
	private String petType;
	private String cellPhone;
	private String addr;
	private String door;
	private String remark;
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Date getBakOrderDate() {
		return bakOrderDate;
	}
	public void setBakOrderDate(Date bakOrderDate) {
		this.bakOrderDate = bakOrderDate;
	}
	public String getBakOrderTime() {
		return bakOrderTime;
	}
	public void setBakOrderTime(String bakOrderTime) {
		this.bakOrderTime = bakOrderTime;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Date getOrderDealTime() {
		return orderDealTime;
	}
	public void setOrderDealTime(Date orderDealTime) {
		this.orderDealTime = orderDealTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public int getBeauticianID() {
		return beauticianID;
	}
	public void setBeauticianID(int beauticianID) {
		this.beauticianID = beauticianID;
	}
	public String getBeauticianName() {
		return beauticianName;
	}
	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}
	public String getPet() {
		return pet;
	}
	public void setPet(String pet) {
		this.pet = pet;
	}
	public String getPetType() {
		return petType;
	}
	public void setPetType(String petType) {
		this.petType = petType;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDoor() {
		return door;
	}
	public void setDoor(String door) {
		this.door = door;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
