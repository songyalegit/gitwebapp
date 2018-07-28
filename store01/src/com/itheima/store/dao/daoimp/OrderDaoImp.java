package com.itheima.store.dao.daoimp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.store.dao.OrderDao;
import com.itheima.store.domain.Order;
import com.itheima.store.domain.OrderItem;
import com.itheima.store.domain.Product;
import com.itheima.store.domain.User;
import com.itheima.store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public void saveSubmit(Order order) throws Exception {
		String sql="INSERT INTO orders VALUES(?,?,?,?,null,null,null,?)";
		Object[] parames={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getUser().getUid()};
		QueryRunner qr=new QueryRunner();
		qr.update(JDBCUtils.getConnection(), sql,parames);
	}

	@Override
	public void saveSubmitItem(OrderItem item) throws Exception {
		String sql="INSERT INTO orderitem VALUES(?,?,?,?,?)";
		Object[] parames={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		QueryRunner qr=new QueryRunner();
		qr.update(JDBCUtils.getConnection(), sql,parames);
	}

	@Override
	public int findOrderItempage(User user) throws Exception {
		String sql="select count(*) from orders o where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long count=(Long) qr.query(sql, new ScalarHandler(),user.getUid());
		return count.intValue();
	}

	@Override
	public List<Order> findOrderByIdWithPage(User user, int startindex) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM orders WHERE uid=? limit ?,3";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startindex);
		for (Order order : list) {
			order.setList(findOrderByOid(order.getOid()));
		}
		System.out.println(list);
		return list;
	}

	@Override
	public List<OrderItem> findOrderByOid(String oid) throws Exception {
		// 查询订单项
		String sql="SELECT * FROM orderitem item,product p WHERE p.pid=item.pid AND item.oid= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<OrderItem> listitem=new ArrayList<>();
		List<Map<String, Object>> listmap=qr.query(sql, new MapListHandler(),oid);
		for (Map<String, Object> map : listmap) {
			OrderItem orderItem=new OrderItem();
			Product product=new Product();
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			orderItem.setProduct(product);
			listitem.add(orderItem);
		}
		return listitem;
	}

	@Override
	public Order findOrderByOidInfo(String oid) throws Exception {
		String sql="select * from orders where oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		order.setList(findOrderByOid(oid));
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		String sql="UPDATE orders SET address=?,name=?,telephone=?,total=?,state=? WHERE oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] parames={order.getAddress(),order.getName(),order.getTelephone(),order.getTotal(),order.getState(),order.getOid()};
		qr.update(sql,parames);
		
	}

	

}
