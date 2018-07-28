package com.itheima.store.service.serviceimp;

import java.util.List;

import com.itheima.store.dao.CategoryDao;
import com.itheima.store.dao.daoimp.CategoryDaoImp;
import com.itheima.store.domain.Category;
import com.itheima.store.service.CategoryService;
import com.itheima.store.utils.BeanFactory;

public class CategoryServiceImp implements CategoryService {

//	private CategoryDao categoryDao=new CategoryDaoImp();
	private CategoryDao categoryDao=(CategoryDao) BeanFactory.createBean("categoryDao");
	@Override
	public List<Category> findCategory() throws Exception {
		return categoryDao.findCategory();
	}

}
