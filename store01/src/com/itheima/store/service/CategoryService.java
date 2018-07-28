package com.itheima.store.service;

import java.util.List;

import com.itheima.store.domain.Category;

public interface CategoryService {

	List<Category> findCategory()throws Exception;

}
