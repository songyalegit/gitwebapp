package com.itheima.store.dao.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.store.dao.UserDao;
import com.itheima.store.domain.User;
import com.itheima.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	@Override
	public void regist(User user) throws Exception {
		//INSERT INTO `user` VALUES ('373eb242933b4f5ca3bd43503c34668b','ccc','ccc',
		//'aaa','bbb@store.com','15723689921','2015-11-04','男',0,
		//'9782f3e837ff422b9aee8b6381ccf927bdd9d2ced10d48f4ba4b9f187edf7738')
		String sql="INSERT INTO `user` VALUES (?,?,?,?,?,?,?,?,?,?)";
		Object[] parames={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql, parames);
	}

	@Override
	public User active(String code) throws Exception {
		String sql="select * from user where code=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new  BeanHandler<User>(User.class),code);
	}

	@Override
	public User login(User user) throws Exception {
		String sql="select * from user where username=? and password=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

	@Override
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		String sql="update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		Object[] parames={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql, parames);
	}

}
