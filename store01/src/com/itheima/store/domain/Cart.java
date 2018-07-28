package com.itheima.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<String, CartItem> map=new HashMap<>();
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//添加购物项
	public void addCartItem(CartItem item){
		if(map.containsKey(item.getProduct().getPid())){
			int oldNum=map.get(item.getProduct().getPid()).getNum();
			map.get(item.getProduct().getPid()).setNum(oldNum+item.getNum());
		}else{
			map.put(item.getProduct().getPid(), item);
		}
	}
	//移除购物项
	public void removeCartItem(String pid){
		map.remove(pid);
	}
	//清空购物项
	public void clearCartItem(){
		map.clear();
	}
	private double total;
	public double getTotal() {
		total=0;
		for (CartItem item : map.values()) {
			total+=item.getSubtotal();
		}
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
