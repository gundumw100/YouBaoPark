package com.yirui.park.model;

import java.util.ArrayList;

import com.base.model.Config;


public class User {

	private int app=3;//应用id，1：普通用户版（默认）；2：商家版；3：停车场版
	private Config config;
	private boolean isLogined=false;
	private String user_session_key;
	private String user_id;
	private String phone;
	private String code;
	private String password;
	private String token;
	private long delta;
	private String nick_name;
	private String type;
	private ArrayList<Card> cards;
	private String gender;
	private Enterprise enterprise;
	private String shop_name;
	private String parking_name;
	private String shop_id;
	private String parking_id;
	
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getParking_name() {
		return parking_name;
	}
	public void setParking_name(String parking_name) {
		this.parking_name = parking_name;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getParking_id() {
		return parking_id;
	}
	public void setParking_id(String parking_id) {
		this.parking_id = parking_id;
	}
	public long getDelta() {
		return delta;
	}
	public void setDelta(long delta) {
		this.delta = delta;
	}
	public String getNick_name() {
		return nick_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isLogined() {
		return isLogined;
	}
	public void setLogined(boolean isLogined) {
		this.isLogined = isLogined;
	}
	public String getUser_session_key() {
		return user_session_key;
	}
	public void setUser_session_key(String user_session_key) {
		this.user_session_key = user_session_key;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
