package com.yirui.park.model;

import java.util.ArrayList;

public class Data {

	private String buy_balance;
	private String grant_balance;
	private String current_month;
	private String price;
	private ArrayList<Stat> stats;
	private Shop shop;

	public ArrayList<Stat> getStats() {
		return stats;
	}

	public void setStats(ArrayList<Stat> stats) {
		this.stats = stats;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getBuy_balance() {
		return buy_balance;
	}

	public void setBuy_balance(String buy_balance) {
		this.buy_balance = buy_balance;
	}

	public String getGrant_balance() {
		return grant_balance;
	}

	public void setGrant_balance(String grant_balance) {
		this.grant_balance = grant_balance;
	}

	public String getCurrent_month() {
		return current_month;
	}

	public void setCurrent_month(String current_month) {
		this.current_month = current_month;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
