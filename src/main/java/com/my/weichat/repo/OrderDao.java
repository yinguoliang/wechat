package com.my.weichat.repo;

import java.util.List;

import com.my.weichat.domain.Order;

public interface OrderDao {
	public void insert(Order order) throws Exception;
	public Order delete(int orderID) throws Exception;
	public void update(Order order) throws Exception;
	public List<Order> queryAll() throws Exception;
	public Order findByPrimaryKey(int orderID) throws Exception;
	public List<Order> queryByCustomer(String customerID)throws Exception;
	public List<Order> queryByOrder(Order order)throws Exception;
	public List<Order> queryBySql(String sql,Object[] param)throws Exception;
}
