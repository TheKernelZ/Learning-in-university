package com.thekernel.entity;

public class Goods {

	private int goodid;
	private String goodname;
	private int goodprice;
	private int amount;
	
	public Goods() {
		super();
	}
	
	public Goods(int goodid, String goodname, int goodprice, int amount) {
		super();
		this.goodid = goodid;
		this.goodname = goodname;
		this.goodprice = goodprice;
		this.amount = amount;
	}
	
	public Goods(String goodname, int goodprice, int amount) {
		super();
		this.goodname = goodname;
		this.goodprice = goodprice;
		this.amount = amount;
	}
	
	public int getGoodid() {
		return goodid;
	}
	public void setGoodid(int goodid) {
		this.goodid = goodid;
	}
	public String getGoodname() {
		return goodname;
	}
	public void setGoodname(String goodname) {
		this.goodname = goodname;
	}
	public int getGoodprice() {
		return goodprice;
	}
	public void setGoodprice(int goodprice) {
		this.goodprice = goodprice;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Goods [goodid=" + goodid + ", goodname=" + goodname
				+ ", goodprice=" + goodprice + ", amount=" + amount + "]";
	}
	
}
