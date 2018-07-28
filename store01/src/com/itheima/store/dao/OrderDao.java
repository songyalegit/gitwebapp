package com.itheima.store.dao;

import java.util.List;

import com.itheima.store.domain.Order;
import com.itheima.store.domain.OrderItem;
import com.itheima.store.domain.User;

public interface OrderDao {

	void saveSubmit(Order order)throws Exception;

	void saveSubmitItem(OrderItem item)throws Exception;

	int findOrderItempage(User user)throws Exception;

	List<Order> findOrderByIdWithPage(User user, int startindex)throws Exception;

	List<OrderItem> findOrderByOid(String oid)throws Exception;

	Order findOrderByOidInfo(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;


}
