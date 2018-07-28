package com.itheima.store.service;

import com.itheima.store.domain.User;

public interface UserService {

	void regist(User user)throws Exception;

	User active(String code)throws Exception;

	User login(User user)throws Exception;

	void update(User user)throws Exception;

}
