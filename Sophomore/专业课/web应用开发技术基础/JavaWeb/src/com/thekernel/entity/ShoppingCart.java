package com.thekernel.entity;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	/** 商品的id和商品数量 */
	private Map<Integer, Integer> goodMap = new HashMap<Integer, Integer>();
	private int goodCnt = 0;
	private int totalMoney;

	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(Map<Integer, Integer> goodMap, int goodCnt, int totalMoney) {
		super();
		this.goodMap = goodMap;
		this.goodCnt = goodCnt;
		this.totalMoney = totalMoney;
	}

	public Map<Integer, Integer> getGoodMap() {
		return goodMap;
	}

	public void setGoodMap(Map<Integer, Integer> goodMap) {
		this.goodMap = goodMap;
	}

	public int getGoodCnt() {
		return goodCnt;
	}

	public void setGoodCnt(int goodCnt) {
		this.goodCnt = goodCnt;
	}

	public void setGoodIncrement() {
		this.goodCnt++;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Override
	public String toString() {
		return "ShoppingCart [goodMap=" + goodMap + ", goodCnt=" + goodCnt + ", totalMoney=" + totalMoney + "]";
	}
	
}
