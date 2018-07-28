package com.itheima.store.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.User;
import com.itheima.store.service.UserService;
import com.itheima.store.service.serviceimp.UserServiceImp;
import com.itheima.store.utils.MyBeanUtils;
import com.itheima.store.utils.UUIDUtils;

public class UserServlet extends BaseServlet {

	//registUI用户注册
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/jsp/register.jsp";
	}
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "/jsp/login.jsp";
	}
	public String register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=MyBeanUtils.populate(User.class, request.getParameterMap());
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getUUID64());
		System.out.println(user);
		UserService userService=new UserServiceImp();
		userService.regist(user);
		request.setAttribute("msg", "用户注册成功，请跳到邮箱激活");
		return "/jsp/info.jsp";	
	}
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String code=request.getParameter("code");
		UserService userService=new UserServiceImp();
		User user=userService.active(code);
		if(user!=null){
			user.setState(1);
			user.setCode("");
			userService.update(user);
			request.setAttribute("msg", "用户激活成功，请登录");
			return "/jsp/login.jsp";
		}else{
			request.setAttribute("msg", "用户激活失败，请重新激活");
			return "/jsp/info.jsp";
		}
	}
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user=MyBeanUtils.populate(User.class, request.getParameterMap());
		UserService userService=new UserServiceImp();
		User user1=userService.login(user);
		System.out.println(user1);
		if(user1!=null){
			request.getSession().setAttribute("user", user1);
			return "/jsp/product_list.jsp";
		}else{
			request.setAttribute("msg", "登录失败，请重新登录");
			return "/jsp/login.jsp";
		}
		
	}
	public String loginout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println(request.getSession());
		request.getSession().invalidate();
		response.sendRedirect("/store01/");
		return null;
	}
}