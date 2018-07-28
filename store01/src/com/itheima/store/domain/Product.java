package com.itheima.store.domain;

import java.util.Date;

/*
 * `pid` varchar(32) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,		#商品名称
  `market_price` double DEFAULT NULL,	#市场价
  `shop_price` double DEFAULT NULL,		#商城价
  `pimage` varchar(200) DEFAULT NULL,	#商品图片路径
  `pdate` date DEFAULT NULL,			#上架时间
  `is_hot` int(11) DEFAULT NULL,		#是否热门：0=不热门,1=热门
  `pdesc` varchar(255) DEFAULT NULL,	#商品描述
  `pflag` int(11) DEFAULT 0,			#商品标记：0=未下架(默认值),1=已经下架
  `cid` varchar(32) DEFAULT NULL,		#分类id*/
public class Product {
	private String pid;//商品id
	private String pname;//商品名称
	private double market_price;//市场价
	private double shop_price;//商城价
	private String pimage;//商品图片路径
	private Date pdate;//上架时间
	private int is_hot;//是否热门：0=不热门,1=热门
	private String pdesc;//商品描述
	private int pflag;//商品标记：0=未下架(默认值),1=已经下架
	private String cid;	//分类id
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public int getPflag() {
		return pflag;
	}
	public void setPflag(int pflag) {
		this.pflag = pflag;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc
				+ ", pflag=" + pflag + ", cid=" + cid + "]";
	}
	
}
