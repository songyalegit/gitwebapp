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
import com.itheima.store.utils.PageModel;


public class ProductServlet extends BaseServlet {


	public String findProductByCidWithNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("cid");
		int  num=Integer.parseInt(request.getParameter("num"));
		ProductService productService=new ProductServiceImp();
		PageModel pm=productService.findProductByCidWithNum(cid,num);
		System.out.println(pm);
		pm.setUrl("ProductServlet?method=findProductByCidWithNum&cid="+cid);
		request.setAttribute("page", pm);
		return "/jsp/product_list.jsp";
	}
	public String findProductInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		ProductService productService=new ProductServiceImp();
		Product product=productService.findProductInfo(pid);
		request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}

}