package com.itheima.store.web.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.Cart;
import com.itheima.store.domain.CartItem;
import com.itheima.store.domain.Product;
import com.itheima.store.domain.User;
import com.itheima.store.service.ProductService;
import com.itheima.store.service.serviceimp.ProductServiceImp;

public class CartIServlet extends BaseServlet {

	public String addCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("msg", "你还没登录，请先登录");
			return "/jsp/info.jsp";
		}
		String pid=request.getParameter("pid");
		int num=Integer.parseInt(request.getParameter("num"));
		ProductService productService=new ProductServiceImp();
		Product product=productService.findProductInfo(pid);
		CartItem item=new CartItem();
		item.setProduct(product);
		item.setNum(num);
		item.setSubtotal(product.getShop_price());
		Cart cart =(Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart =new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addCartItem(item);
		System.out.println(cart);
		response.sendRedirect("/store01/jsp/cart.jsp");
		return null;
	}
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pid=request.getParameter("pid");
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		response.sendRedirect("/store01/jsp/cart.jsp");
		return null;
		
	}
	public String clearCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clearCartItem();
		response.sendRedirect("/store01/jsp/cart.jsp");
		return null;
		
	}
}