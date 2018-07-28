package com.itheima.store.dao;

import com.itheima.store.domain.User;

public interface UserDao {

	void regist(User user)throws Exception;

	User active(String code)throws Exception;

	User login(User user)throws Exception;

	void update(User user)throws Exception;

}
