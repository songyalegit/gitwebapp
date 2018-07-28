package com.itheima.store.dao.daoimp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.store.dao.ProductDao;
import com.itheima.store.domain.Product;
import com.itheima.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> find9ProductNews() throws Exception {
		String sql="select * from product where pflag=0 order by pdate desc limit 0,9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> find9ProductHots() throws Exception {
		String sql="select * from product where is_hot=1 and pflag=0 order by pdate desc limit 0,9";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public int findProductByCidWithNum(String cid) throws Exception {
		String sql="SELECT count(*) FROM product WHERE cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long count=(Long) qr.query(sql, new ScalarHandler(),cid);
		return count.intValue();
	}

	@Override
	public List<Product> findProductByCid(String cid, int num) throws Exception {
		String sql="SELECT * FROM product WHERE cid=? LIMIT ?,12";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,(num-1)*12);
	}

	@Override
	public Product findProductInfo(String pid) throws Exception {
		String sql="select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}


}
