package com.itheima.store.dao;

import java.util.List;

import com.itheima.store.domain.Category;

public interface CategoryDao {

	List<Category> findCategory()throws Exception;

}
