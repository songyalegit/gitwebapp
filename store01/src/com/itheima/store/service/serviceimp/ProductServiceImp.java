package com.itheima.store.service.serviceimp;

import java.util.List;

import com.itheima.store.dao.ProductDao;
import com.itheima.store.dao.daoimp.ProductDaoImp;
import com.itheima.store.domain.Product;
import com.itheima.store.service.ProductService;
import com.itheima.store.utils.BeanFactory;
import com.itheima.store.utils.PageModel;

public class ProductServiceImp implements ProductService {

//	private ProductDao productDao=new ProductDaoImp();
	private ProductDao productDao=(ProductDao) BeanFactory.createBean("productDao");
	@Override
	public List<Product> find9ProductNews() throws Exception {
		return productDao.find9ProductNews();
	}
	@Override
	public List<Product> find9ProductHots() throws Exception {
		return productDao.find9ProductHots();
	}
	@Override
	public PageModel findProductByCidWithNum(String cid, int num) throws Exception {
		int totalRecords=productDao.findProductByCidWithNum(cid);
		List<Product> list=productDao.findProductByCid(cid, num);
		PageModel pm=new PageModel(num, totalRecords,12 );
		pm.setList(list);
		return pm;
	}
	@Override
	public Product findProductInfo(String pid) throws Exception {
		return productDao.findProductInfo(pid);
	}

}
