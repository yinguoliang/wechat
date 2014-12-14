package com.my.weichat.repo;

import java.util.List;

public interface BaseDao {
	public<T> List<T> find();
	public void init();
}
