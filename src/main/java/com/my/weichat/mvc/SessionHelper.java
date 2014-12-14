package com.my.weichat.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionHelper {
	public HttpServletRequest getRequest(){
		HttpServletRequest request =  
				((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	public String getCustomerID(){
		return (String) this.getRequest().getSession().getAttribute("customerID");
	}
	public void putCustomerID(String customerID){
		this.getRequest().getSession().setAttribute("customerID", customerID);
	}
}
