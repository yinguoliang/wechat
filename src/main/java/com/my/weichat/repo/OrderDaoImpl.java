package com.my.weichat.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.my.weichat.domain.Order;
import com.my.weichat.domain.Sequence;

@Component("orderDao")
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	private SequenceDao sequenceDao;
	public void setSequence(SequenceDao sequenceDao){
		this.sequenceDao = sequenceDao;
	}
	@Override
	public void insert(Order order) throws Exception {
		if(order==null) return;
		if(order.getOrderID()==0) order.setOrderID(sequenceDao.nextVal(Sequence.OrderID));
		this.jdbcTemplate.update(
				"insert into t_order(order_id,order_code,order_date,order_time," +
				"bak_orderdate,bak_ordertime,order_price,order_dealtime,order_status," +
				"order_remark,customer_id,beautician_id,beautician_name,pet,pettype," +
				"cellphone,addr,door,remark)" +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new Object[]{order.getOrderID(),order.getOrderCode(),order.getOrderDate(),order.getOrderTime(),
						order.getBakOrderDate(),order.getBakOrderTime(),order.getOrderPrice(),order.getOrderDealTime(),order.getOrderStatus(),
						order.getOrderRemark(),order.getCustomerID(),order.getBeauticianID(),order.getBeauticianName(),order.getPet(),order.getPetType(),
						order.getCellPhone(),order.getAddr(),order.getDoor(),order.getRemark()});
	}

	@Override
	public Order delete(int orderID) throws Exception {
		Order order = this.findByPrimaryKey(orderID);
		if(order==null) return null;
		this.jdbcTemplate.update(
			"delete t_order where order_id = ? ",
			new Object[]{orderID}
		);
		return order;
	}

	@Override
	public void update(Order order) throws Exception {
		this.jdbcTemplate.update(
			"update t_order set order_code=?,order_date=?,order_time=?," +
				"bak_orderdate=?,bak_ordertime=?,order_price=?,order_dealtime=?,order_status=?," +
				"order_remark=?,customer_id=?,beautician_id=?,beautician_name=?,pet=?,pettype=?," +
				"cellphone=?,addr=?,door=?,remark=? " +
				"where order_id = ? ",
			new Object[]{order.getOrderCode(),order.getOrderDate(),order.getOrderTime(),
					order.getBakOrderDate(),order.getBakOrderTime(),order.getOrderPrice(),order.getOrderDealTime(),order.getOrderStatus(),
					order.getOrderRemark(),order.getCustomerID(),order.getBeauticianID(),order.getBeauticianName(),order.getPet(),order.getPetType(),
					order.getCellPhone(),order.getAddr(),order.getDoor(),order.getRemark(),order.getOrderID()}
			);
	}

	@Override
	public List<Order> queryByCustomer(String customerID) throws Exception {
		return this.queryBySql("select * from t_order where customer_id=?", new Object[]{customerID});
	}

	@Override
	public List<Order> queryByOrder(Order order) throws Exception {
		StringBuffer sql = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		if(!StringUtils.isEmpty(order.getOrderCode())){
			sql.append(" and order_code=? ");
			param.add(order.getOrderCode());
		}
		if(!StringUtils.isEmpty(order.getCustomerID())){
			sql.append(" and customer_id = ? ");
			param.add(order.getCustomerID());
		}
		if(!StringUtils.isEmpty(order.getBeauticianID())){
			sql.append( " and beautician_id = ? ");
			param.add(order.getBeauticianID());
		}
		if(!StringUtils.isEmpty(order.getOrderID())){
			sql.append(" and order_id = ? ");
			param.add(order.getOrderID());
		}
		if(!StringUtils.isEmpty(order.getOrderStatus())){
			sql.append(" and order_status = ? ");
			param.add(order.getOrderStatus());
		}
		if(sql.length()==0) return null;
		sql.insert(0, "select * from t_order where 1=1 ");
		return this.queryBySql(sql.toString(), param.toArray());
	}

	@Override
	public List<Order> queryBySql(String sql, Object[] param) throws Exception {
		List<Order> orderList = 
				this.jdbcTemplate.query(
					sql, 
					param, 
					new ResultSetExtractor<List<Order>>(){
						public List<Order> extractData(ResultSet rs) throws SQLException,
								DataAccessException {
							List<Order> list = new ArrayList<Order>();
							/*
							order_id,order_code,order_date,order_time," +
							"bak_orderdate,bak_ordertime,order_price,order_dealtime,order_status," +
							"order_remark,customer_id,beautician_id,beautician_name,pet,pettype," +
							"cellphone,addr,door,remark
							*/
							while(rs.next()){
								Order order = new Order();
								order.setOrderID(rs.getInt("order_id"));
								order.setOrderCode(rs.getString("order_code"));
								order.setOrderDate(rs.getDate("order_date"));
								order.setOrderTime(rs.getString("order_time"));
								order.setBakOrderDate(rs.getDate("order_date"));
								order.setBakOrderTime(rs.getString("order_time"));
								order.setOrderPrice(rs.getDouble("order_price"));
								order.setOrderDealTime(rs.getDate("order_dealtime"));
								order.setOrderStatus(rs.getString("order_status"));
								order.setOrderRemark(rs.getString("order_remark"));
								order.setCustomerID(rs.getString("customer_id"));
								order.setBeauticianID(rs.getInt("beautician_id"));
								order.setBeauticianName(rs.getString("beautician_name"));
								order.setPet(rs.getString("pet"));
								order.setPetType(rs.getString("pettype"));
								order.setCellPhone(rs.getString("cellphone"));
								order.setAddr(rs.getString("addr"));
								order.setDoor(rs.getString("door"));
								order.setRemark(rs.getString("remark"));
								list.add(order);
							}
							return list;
						}
				});
		return orderList;
	}
	@Override
	public Order findByPrimaryKey(int orderID) throws Exception {
		List<Order> list = 
				this.queryBySql("select * from t_order where order_id = ?", new Object[]{orderID});
		if(list.size()==0) return null;
		else return list.get(0);
	}
	@Override
	public List<Order> queryAll() throws Exception {
		return this.queryBySql("select * from t_order ", null);
	}

}
