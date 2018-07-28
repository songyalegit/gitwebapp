package com.itheima.store.service;

import java.util.List;

import com.itheima.store.domain.Product;
import com.itheima.store.utils.PageModel;

public interface ProductService {

	List<Product> find9ProductNews() throws Exception;

	List<Product> find9ProductHots() throws Exception;

	PageModel findProductByCidWithNum(String cid, int num)throws Exception;

	Product findProductInfo(String pid)throws Exception;

}
