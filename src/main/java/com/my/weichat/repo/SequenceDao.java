package com.my.weichat.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.my.weichat.domain.Sequence;

@Component("sequenceDao")
public class SequenceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public void init(Sequence sequence){
		System.out.println(sequence);
		this.jdbcTemplate.update(
				"insert into sequence(name,current_value,increment)" +
				"values (?,?,?) ",
				new Object[]{sequence.toString(),0,1});
	}
	public int curVal(Sequence sequence){
		Integer val = this.jdbcTemplate.query(
				"select * from sequence where name=?", 
				new Object[]{sequence.toString()},
				new ResultSetExtractor<Integer>(){
				public Integer extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					Integer val = null;
					while(rs.next())
						val = rs.getInt("current_value");
					return val;
				}
		});
		if(val==null){
			this.init(sequence);
			val = this.curVal(sequence);
		}
		return val;
	}
	public int nextVal(Sequence sequence){
		int updateRows = this.jdbcTemplate.update(
				"update sequence set current_value = current_value + increment" +
				" where name = ? " ,
				new Object[]{sequence.toString()});
		if(updateRows==0) this.init(sequence);
		return this.curVal(sequence);
	}
}
