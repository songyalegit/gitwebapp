package com.itheima.store.domain;

public class CartItem {
	
	private Product product;//商品信息
	private int num;//购买数量
	private double subtotal;//小计/金额
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubtotal() {
		return product.getShop_price()*num;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", num=" + num + ", subtotal=" + subtotal + "]";
	}
	
	
}
