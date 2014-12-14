package com.my.weichat.mvc;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.my.weichat.domain.Customer;
import com.my.weichat.repo.CustomerDao;

@Component("userInfoFilter")
public class UserInfoFilter implements Filter{
	@Autowired
	CustomerDao customerDao = null;
	public void setCustomerDao(CustomerDao customerDao){
		this.customerDao = customerDao;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String customerID= (String)request.getSession().getAttribute("customerID");
		System.out.println(req);
		System.out.println("customerID(Session)=="+customerID);
		try {
			if(!StringUtils.isEmpty(customerID)){
				Customer customer = customerDao.findByPrimaryKey(customerID);
				if(customer==null || StringUtils.isEmpty(customer.getCustomerCellPhone())){
					response.sendRedirect("views/BindUserInfo.jsp?"+new Date().getTime());
				}else if(!StringUtils.isEmpty(customer.getCustomerCellPhone())){
					chain.doFilter(request, response);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("views/BindUserInfo.jsp?t="+new Date().getTime());
			return;
		}
		String cellPhone = request.getParameter("cellPhone");
		String wechatID = request.getParameter("wid");
		customerID = request.getParameter("customerID");
		System.out.println("customerID(Request)="+customerID+",cellPhone="+cellPhone+",wechatID="+wechatID);
		
		if(StringUtils.isEmpty(cellPhone) && StringUtils.isEmpty(wechatID)){
			response.sendRedirect("views/BindUserInfo.jsp?t="+new Date().getTime());
			return;
		}else{
			try {
				List<Customer> list= 
				customerDao.queryBySql(
					"select * from t_customer where customer_cellphone=? or customer_wechatid=?",
					new String[]{cellPhone,wechatID});
				System.out.println("Query...... size="+list.size());
				if(list.size()>0){
					Customer c = list.get(0);
					customerID = c.getCustomerID();
					System.out.println("query cellphone="+c.getCustomerCellPhone());
					if(StringUtils.isEmpty(c.getCustomerCellPhone()))
						response.sendRedirect("views/BindUserInfo.jsp?t="+new Date().getTime());
				}else{
					if(StringUtils.isEmpty(customerID))
						customerID = customerDao.newCustomerID();
					System.out.println("customerID====="+customerID);
					Customer cus = new Customer();
					cus.setCustomerID(customerID);
					cus.setCustomerCellPhone(cellPhone);
					cus.setCustomerWechatID(wechatID);
					customerDao.insert(cus);
				}
				request.getSession().setAttribute("customerID", customerID);
				System.out.println("customerID>>>>>>>>>"+customerID);
				response.sendRedirect("beauticianReservation.do");
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("views/BindUserInfo.jsp?"+new Date().getTime());
			}
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
