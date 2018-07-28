package com.itheima.store.service;

import java.util.List;

import com.itheima.store.domain.Order;
import com.itheima.store.domain.OrderItem;
import com.itheima.store.domain.User;
import com.itheima.store.utils.PageModel;

public interface OrderService {

	void saveSubmit(Order order)throws Exception;

	PageModel findOrderByIdWithPage(User user, int num)throws Exception;

	Order findOrderByOidInfo(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

}
