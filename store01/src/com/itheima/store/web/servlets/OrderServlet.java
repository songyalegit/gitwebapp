package com.itheima.store.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.store.domain.Cart;
import com.itheima.store.domain.CartItem;
import com.itheima.store.domain.Order;
import com.itheima.store.domain.OrderItem;
import com.itheima.store.domain.User;
import com.itheima.store.service.OrderService;
import com.itheima.store.service.serviceimp.OrderServiceImp;
import com.itheima.store.utils.MyBeanUtils;
import com.itheima.store.utils.PageModel;
import com.itheima.store.utils.PaymentUtil;
import com.itheima.store.utils.UUIDUtils;

public class OrderServlet extends BaseServlet {

	public String saveSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart=(Cart) request.getSession().getAttribute("cart");//获取购物车数据
		User user=(User) request.getSession().getAttribute("user");//获取用户信息
		Order order=new Order();//向订单表添加数据
		order.setOid(UUIDUtils.getId());
		order.setState(1);
		order.setUser(user);
		order.setTotal(cart.getTotal());
		order.setOrdertime(new Date());
		OrderService orderService=new OrderServiceImp();
		List<OrderItem> list=new ArrayList<>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem item=new OrderItem();
			item.setItemid(UUIDUtils.getId());
			item.setQuantity(cartItem.getNum());
			item.setTotal(cartItem.getSubtotal());
			item.setOrder(order);
			item.setProduct(cartItem.getProduct());
			list.add(item);
		}
		order.setList(list);
		orderService.saveSubmit(order);
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}
	//findOrderByIdWithPage
	public String findOrderByIdWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User) request.getSession().getAttribute("user");
		int num=Integer.parseInt(request.getParameter("num"));
		OrderService orderService=new OrderServiceImp();
		PageModel pageModel=orderService.findOrderByIdWithPage(user,num);
		System.out.println("订单项"+pageModel);
		request.setAttribute("page", pageModel);
		return "/jsp/order_list.jsp";
	}
	//findOrderByOid
	public String findOrderByOidInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String oid=request.getParameter("oid");
		OrderService orderService=new OrderServiceImp();
		Order order=orderService.findOrderByOidInfo(oid);
		System.out.println("详情向"+order);
		request.setAttribute("order", order);
		return "/jsp/order_info.jsp";
		
	}
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		String hmac = request.getParameter("hmac");
		// 利用本地密钥和加密算法 加密数据
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 有效
			if (r9_BType.equals("1")) {
				
				if(r1_Code.equals("1")){
					// 浏览器重定向
					//response.setContentType("text/html;charset=utf-8");
					//response.getWriter().println(	"支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
					
					//支付成功之后,更新订单状态  r6_Order
					OrderService orderService=new OrderServiceImp();
					Order order=orderService.findOrderByOidInfo(r6_Order);
					order.setState(2);
					orderService.updateOrder(order);
					//向request放入提示消息:
					request.setAttribute("msg", "支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
					//转发到"/jsp/info.jsp"
					return "/jsp/info.jsp";						
				}
			} 

		} else {
			throw new RuntimeException("数据被篡改！");
		}
		return null;
	}
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Order order=MyBeanUtils.populate(Order.class, request.getParameterMap());
		String pd_FrpId=request.getParameter("pd_FrpId");
		//通过订单oid去仓库获取订单数据
		OrderService orderService=new OrderServiceImp();
		Order o=orderService.findOrderByOidInfo(order.getOid());
		//更新订单上收货人姓名,地址,电话
		o.setAddress(order.getAddress());
		o.setName(order.getName());
		o.setTelephone(order.getTelephone());
		orderService.updateOrder(o);
		//拼凑字符串向易宝支付发送请求
		// 把付款所需要的参数准备好:
		String p0_Cmd = "Buy";
		//商户编号
		String p1_MerId = "10001126856";
		//订单编号
		String p2_Order = order.getOid();
		//金额
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		//接受响应参数的Servlet
		String p8_Url = "http://localhost:8080/store01/OrderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//公司的秘钥
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
			
		//调用易宝的加密算法,对所有数据进行加密,返回电子签名
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		System.out.println(sb.toString());
		// 使用重定向：
		response.sendRedirect(sb.toString());
		return null;
		
	}
}