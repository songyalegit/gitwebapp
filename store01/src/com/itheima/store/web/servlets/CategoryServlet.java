package com.itheima.store.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.Category;
import com.itheima.store.service.CategoryService;
import com.itheima.store.service.serviceimp.CategoryServiceImp;
import com.itheima.store.utils.JedisUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {

	public String findCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=utf-8");
		String jsonstr=null;
		Jedis jedis=JedisUtils.getJedis();
		jsonstr=jedis.get("allCats");
		if(jsonstr==null){
			System.out.println("redis中没有数据");
			CategoryService categoryService=new CategoryServiceImp();
			List<Category> list=categoryService.findCategory();
			System.out.println(list);
			jsonstr=JSONArray.fromObject(list).toString();
			jedis.set("allCats", jsonstr);
		}else{
			System.out.println("redis中有数据");
		}
		response.getWriter().print(jsonstr);
		JedisUtils.closeJedis(jedis);
		return null;
	}
}