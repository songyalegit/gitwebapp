package com.itheima.store.service.serviceimp;

import com.itheima.store.dao.UserDao;
import com.itheima.store.dao.daoimp.UserDaoImp;
import com.itheima.store.domain.User;
import com.itheima.store.service.UserService;
import com.itheima.store.utils.BeanFactory;
import com.itheima.store.utils.MailUtils;

public class UserServiceImp implements UserService {

//	private UserDao userDao=new UserDaoImp();
	private UserDao userDao=(UserDao) BeanFactory.createBean("userDao");
	@Override
	public void regist(User user)throws Exception {
		userDao.regist(user);
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}
	@Override
	public User active(String code)throws Exception {
		return userDao.active(code);
	}
	@Override
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	@Override
	public void update(User user) throws Exception {
		userDao.update(user);
		
	}

}
