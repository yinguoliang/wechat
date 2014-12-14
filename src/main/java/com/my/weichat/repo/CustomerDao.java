package com.my.weichat.repo;

import java.util.List;

import com.my.weichat.domain.Customer;


public interface CustomerDao {
	public String newCustomerID() throws Exception;
	public void insert(Customer customer) throws Exception;
	public Customer delete(String customerID) throws Exception;
	public void update(Customer customer) throws Exception;
	public List<Customer> queryAll() throws Exception;
	public Customer findByPrimaryKey(String customerID) throws Exception;
	public List<Customer> queryBySql(String sql,Object[] param)throws Exception;
}
