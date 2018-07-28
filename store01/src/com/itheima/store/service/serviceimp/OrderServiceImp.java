package com.itheima.store.service.serviceimp;

import java.util.List;

import com.itheima.store.dao.OrderDao;
import com.itheima.store.dao.daoimp.OrderDaoImp;
import com.itheima.store.domain.Order;
import com.itheima.store.domain.OrderItem;
import com.itheima.store.domain.User;
import com.itheima.store.service.OrderService;
import com.itheima.store.utils.JDBCUtils;
import com.itheima.store.utils.PageModel;

public class OrderServiceImp implements OrderService {

	private OrderDao orderDao=new OrderDaoImp();
	@Override
	public void saveSubmit(Order order) throws Exception {
		try {
			JDBCUtils.startTransaction();
			orderDao.saveSubmit(order);
			for (OrderItem item : order.getList()) {
				orderDao.saveSubmitItem(item);
			}
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();
			e.printStackTrace();
		}
		
	}
	@Override
	public PageModel findOrderByIdWithPage(User user,int num) throws Exception {
		int totalpage=orderDao.findOrderItempage(user);
		PageModel pm=new PageModel(num, totalpage, 3);
		int startindex=pm.getStartIndex();
		List<Order> list=orderDao.findOrderByIdWithPage(user,startindex);
		pm.setList(list);
		pm.setUrl("OrderServlet?method=findOrderByIdWithPage");
		return pm;
	}
	@Override
	public Order findOrderByOidInfo(String oid) throws Exception {
		
		return orderDao.findOrderByOidInfo(oid);
	}
	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);
		
	}
	
	

}
