package com.itheima.store.dao;

import java.util.List;

import com.itheima.store.domain.Product;

public interface ProductDao {

	List<Product> find9ProductNews()throws Exception;

	List<Product> find9ProductHots()throws Exception;

	int findProductByCidWithNum(String cid)throws Exception;

	List<Product> findProductByCid(String cid, int num)throws Exception;

	Product findProductInfo(String pid)throws Exception;

}
