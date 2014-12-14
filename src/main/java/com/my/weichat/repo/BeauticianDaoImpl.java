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

import com.my.weichat.domain.Beautician;
import com.my.weichat.domain.Sequence;

@Component("beauticianDao")
public class BeauticianDaoImpl implements BeauticianDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	private SequenceDao sequenceDao;
	public void setSequenceDao(SequenceDao sequenceDao){
		this.sequenceDao = sequenceDao;
	}
	@Override
	public int newBeauticianID() throws Exception {
		return sequenceDao.nextVal(Sequence.BeauticianID);
	}

	@Override
	public void insert(Beautician beautician) throws Exception {
		
	}

	@Override
	public Beautician delete(int beauticianID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Beautician beautician) throws Exception {
		
	}

	@Override
	public List<Beautician> queryAll() throws Exception {
		return this.queryBySql("select * from t_beautician", null);
	}

	@Override
	public Beautician findByPrimaryKey(int beautician) throws Exception {
		List<Beautician> list = this.queryBySql(
				"select * from t_beautician where beautician_id=?",
				new Object[]{beautician});
		if(list==null || list.size()==0) return null;
		return list.get(0);
	}

	@Override
	public List<Beautician> queryBySql(String sql, Object[] param)
			throws Exception {
		List<Beautician> beauticianList = 
				this.jdbcTemplate.query(
					sql,param,
					new ResultSetExtractor<List<Beautician>>(){
							public List<Beautician> extractData(ResultSet rs) throws SQLException,
									DataAccessException {
								List<Beautician> list = new ArrayList<Beautician>();
								while(rs.next()){
									Beautician b = new Beautician();
									b.setBeauticianName(rs.getString("Beautician_Name"));
									b.setBeauticianID(rs.getInt("Beautician_ID"));
									b.setBeauticianImg(rs.getString("Beautician_img"));
									b.setBeauticianCellPhone(rs.getString("Beautician_CellPhone"));
									b.setBeauticianFeature(rs.getString("Beautician_feature"));
									b.setBeauticianArea(rs.getString("Beautician_Area"));
									b.setBeauticianRemark(rs.getString("Beautician_Remark"));
									list.add(b);
								}
								return list;
							}
						});
		return beauticianList;
	}

}
