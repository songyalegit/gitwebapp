package com.itheima.store.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.Product;
import com.itheima.store.service.ProductService;
import com.itheima.store.service.serviceimp.ProductServiceImp;

public class IndexServlet extends BaseServlet {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		ProductService productService=new ProductServiceImp();
		List<Product> Newslist;
		List<Product> Hotslist;
		try {
			Newslist = productService.find9ProductNews();
			Hotslist = productService.find9ProductHots();
			System.out.println("最新"+Newslist);
			System.out.println("热卖"+Hotslist);
			request.setAttribute("news", Newslist);
			request.setAttribute("hots", Hotslist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/jsp/index.jsp";
	}

}