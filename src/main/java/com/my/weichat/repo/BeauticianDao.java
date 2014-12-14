package com.my.weichat.repo;

import java.util.List;

import com.my.weichat.domain.Beautician;

public interface BeauticianDao {
	public int newBeauticianID() throws Exception;
	public void insert(Beautician beautician) throws Exception;
	public Beautician delete(int beauticianID) throws Exception;
	public void update(Beautician beautician) throws Exception;
	public List<Beautician> queryAll() throws Exception;
	public Beautician findByPrimaryKey(int beauticianID) throws Exception;
	public List<Beautician> queryBySql(String sql,Object[] param)throws Exception;

}
