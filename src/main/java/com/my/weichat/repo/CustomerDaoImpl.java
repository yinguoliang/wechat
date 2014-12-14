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

import com.my.weichat.domain.Customer;
import com.my.weichat.domain.Sequence;
@Component("customerDao")
public class CustomerDaoImpl implements CustomerDao{
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
	public String newCustomerID() throws Exception{
//		String customerID = "0000000000"+sequenceDao.nextVal(Sequence.CustomerID);
		return sequenceDao.nextVal(Sequence.CustomerID)+"";
	}
	@Override
	public void insert(Customer customer) throws Exception {
		if(customer==null) return ;
		if(StringUtils.isEmpty(customer.getCustomerID())){
			String customerID = this.newCustomerID();
			customer.setCustomerID(customerID);
		}
		this.jdbcTemplate.update(
			"insert into t_customer(customer_id,customer_wechatid,customer_name,customer_cellphone,customer_email," +
			"		customer_addr,customer_door,customer_remark) " +
			"values(?,?,?,?,?,?,?,?)",
			new Object[]{customer.getCustomerID(),customer.getCustomerWechatID(),customer.getCustomerName(),customer.getCustomerCellPhone(),customer.getCustomerEmail(),
					customer.getCustomerAddr(),customer.getCustomerDoor(),customer.getCustomerRemark()}
			);
	}

	@Override
	public Customer delete(String customerID) throws Exception {
		Customer customer = this.findByPrimaryKey(customerID);
		if(customer==null) return null;
		this.jdbcTemplate.update("delete t_customer where customer_id=?",new Object[]{customerID});
		return customer;
	}

	@Override
	public void update(Customer customer) throws Exception {
		this.jdbcTemplate.update(
			"update t_customer set customer_wechatid=?,customer_name=?,customer_cellphone=?,customer_email=?, " +
			"customer_addr=?,customer_door=?,customer_remark=? " +
			" where customer_id=? ",new Object[]{customer.getCustomerWechatID(),
			customer.getCustomerName(),customer.getCustomerCellPhone(),customer.getCustomerEmail(),
			customer.getCustomerAddr(),customer.getCustomerDoor(),customer.getCustomerRemark(),
			customer.getCustomerID()
			});
		
	}

	@Override
	public List<Customer> queryAll() throws Exception {
		return this.queryBySql("select * from t_customer", null);
	}

	@Override
	public Customer findByPrimaryKey(String customerID) throws Exception {
		List<Customer> list = 
				this.queryBySql("select * from t_customer where customer_id=?", 
						new Object[]{customerID});
		if(list==null || list.size()==0) return null;
		return list.get(0);
	}

	@Override
	public List<Customer> queryBySql(String sql, Object[] param)
			throws Exception {
		List<Customer> list = 
				this.jdbcTemplate.query(
					sql, 
					param, 
					new ResultSetExtractor<List<Customer>>(){
						public List<Customer> extractData(ResultSet rs) throws SQLException,
								DataAccessException {
							List<Customer> list = new ArrayList<Customer>();
							while(rs.next()){
								Customer customer = new Customer();
								customer.setCustomerID(rs.getString("customer_id"));
								customer.setCustomerName(rs.getString("customer_name"));
								customer.setCustomerCellPhone(rs.getString("customer_cellphone"));
								customer.setCustomerAddr(rs.getString("customer_addr"));
								customer.setCustomerEmail(rs.getString("customer_email"));
								customer.setCustomerRemark(rs.getString("customer_remark"));
								customer.setCustomerDoor(rs.getString("customer_door"));
								customer.setCustomerWechatID(rs.getString("customer_wechatid"));
								list.add(customer);
							}
							return list;
						}
				});
		return list;
	}

}
