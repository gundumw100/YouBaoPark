package com.yirui.park.net;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yirui.park.app.App;
import com.yirui.park.model.Card;
import com.yirui.park.model.Data;
import com.yirui.park.model.Enterprise;
import com.yirui.park.model.Order;
import com.yirui.park.model.Park;
import com.yirui.park.model.Parking;
import com.yirui.park.model.Shop;
import com.yirui.park.model.Stat;

public class Json {

	public static boolean parseGetUserInfo(JSONObject response) {
		try {
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				App.user.setShop_name(data.optString("shop_name"));
				App.user.setParking_name(data.optString("parking_name"));
				App.user.setShop_id(data.optString("shop_id"));
				App.user.setParking_id(data.optString("parking_id"));
				
				JSONObject enterprise = data.optJSONObject("enterprise");
				if(enterprise!=null){
					Enterprise b=new Enterprise();
					b.setId(enterprise.optString("id"));
					b.setName(enterprise.optString("name"));
					App.user.setEnterprise(b);
				}
				
				JSONArray array = data.optJSONArray("my_cards");
				ArrayList<Card> cards = new ArrayList<Card>();
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					Card instance = new Card();
					instance.setCode(obj.optString("code"));
					instance.setCode(obj.optString("category"));
					instance.setCode(obj.optString("category_cn"));
					cards.add(instance);
				}
				App.user.setCards(cards);
				
				JSONObject user = data.optJSONObject("user");
				if (user != null) {
					App.user.setPhone(user.optString("phone"));
					App.user.setNick_name(user.optString("nick_name"));
					App.user.setType(user.optString("type"));
				}
				return true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public static boolean parseLogin(JSONObject response) {
		JSONObject data = response.optJSONObject("data");
		if (data != null) {
			App.user.setUser_session_key(data.optString("user_session_key"));
			App.user.setUser_id(data.optString("user_id"));
			App.user.setLogined(true);
			return true;
		}
		return false;
	}

	public static ArrayList<Parking> parseParkings(JSONObject response) {
		try {
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				JSONArray array = data.optJSONArray("parkings");
				if (array != null) {
					ArrayList<Parking> list = new ArrayList<Parking>();
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Parking instance = new Parking();
						instance.setId(obj.optString("id"));
						instance.setAddress(obj.optString("address"));
						instance.setBookable(obj.optString("bookable"));
						instance.setDescription(obj.optString("description"));
						instance.setFree(obj.optString("free"));
						instance.setHas_discount(obj.optString("has_discount"));
						instance.setImage(obj.optString("image"));
						instance.setLatitude(obj.optString("latitude"));
						instance.setLongitude(obj.optString("longitude"));
						instance.setName(obj.optString("name"));
						instance.setPhone(obj.optString("phone"));
						instance.setPrice(obj.optString("price"));
						instance.setTotal(obj.optString("total"));
						instance.setDistance(obj.optString("distance"));
						instance.setOpen(false);
						list.add(instance);
					}
					return list;
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static ArrayList<Park> parseParkList(JSONObject response) {
		try {
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				JSONArray array = data.optJSONArray("park_list");
				if (array != null) {
					ArrayList<Park> list = new ArrayList<Park>();
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Park instance = new Park();
						instance.setIn_gate_name(obj.optString("in_gate_name"));
						instance.setIn_time(obj.optString("in_time"));
						instance.setStatus(obj.optString("status"));
						instance.setShould_leave_time(obj.optString("should_leave_time"));
						instance.setPark_no(obj.optString("park_no"));
						instance.setPark_id(obj.optString("park_id"));
						instance.setParking_id(obj.optString("parking_id"));

						JSONObject parking = obj.optJSONObject("parking");
						if (parking != null) {
							Parking bean = new Parking();
							bean.setId(parking.optString("id"));
							bean.setName(parking.optString("name"));
							bean.setAddress(parking.optString("address"));
							bean.setLongitude(parking.optString("longitude"));
							bean.setLatitude(parking.optString("latitude"));
							bean.setFree(parking.optString("free"));
							bean.setTotal(parking.optString("total"));
							bean.setPrice(parking.optString("price"));
							bean.setDescription(parking.optString("description"));
							bean.setImage(parking.optString("image"));
							bean.setPhone(parking.optString("phone"));
							bean.setBookable(parking.optString("bookable"));

							instance.setParking(bean);
						}

						list.add(instance);
					}
					return list;
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static Data parseShopDetail(JSONObject response) {
		try {
			Data datas = null;
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				datas = new Data();
				JSONObject obj = data.optJSONObject("shop");
				if (obj != null) {
					Shop instance = new Shop();
					instance.setId(obj.optString("id"));
					instance.setBalance(obj.optString("balance"));
					instance.setMobile_phone(obj.optString("mobile_phone"));
					instance.setQuota(obj.optString("quota"));
					instance.setParking_name(obj.optString("parking_name"));
					instance.setAddress(obj.optString("address"));
					instance.setParking_id(obj.optString("parking_id"));
					instance.setEnd_time(obj.optString("end_time"));
					instance.setCreate_time(obj.optString("create_time"));
					instance.setStart_time(obj.optString("start_time"));
					instance.setName(obj.optString("name"));
					instance.setCode(obj.optString("code"));
					instance.setPhone(obj.optString("phone"));
					instance.setContact(obj.optString("contact"));
					instance.setGrant_quota(obj.optString("grant_quota"));
					instance.setBuy_quota(obj.optString("buy_quota"));
					datas.setShop(instance);
				}

				datas.setBuy_balance(data.optString("buy_balance"));
				datas.setGrant_balance(data.optString("grant_balance"));
				datas.setCurrent_month(data.optString("current_month"));
				datas.setPrice(data.optString("price"));

				JSONObject stats = data.optJSONObject("stats");
				if (stats != null) {
					JSONArray array = stats.optJSONArray("list");
					if (array != null) {
						ArrayList<Stat> list = new ArrayList<Stat>();
						for (int i = 0; i < array.length(); i++) {
							JSONObject o = array.getJSONObject(i);
							Stat instance = new Stat();
							instance.setShop_id(o.optString("shop_id"));
							instance.setYear(o.optString("year"));
							instance.setMonth(o.optString("month"));
							instance.setGrant_size(o.optString("grant_size"));
							instance.setBuy_size(o.optString("buy_size"));
							instance.setTotal(o.optString("total"));
							list.add(instance);
						}

						datas.setStats(list);
					}
				}
			}
			return datas;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Shop> parseShopList(JSONObject response) {
		try {
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				JSONArray array = data.optJSONArray("shops");
				if (array != null) {
					ArrayList<Shop> list = new ArrayList<Shop>();
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Shop instance = new Shop();
						instance.setId(obj.optString("id"));
						instance.setBalance(obj.optString("balance"));
						instance.setMobile_phone(obj.optString("mobile_phone"));
						instance.setQuota(obj.optString("quota"));
						instance.setParking_name(obj.optString("parking_name"));
						instance.setAddress(obj.optString("address"));
						instance.setParking_id(obj.optString("parking_id"));
						instance.setEnd_time(obj.optString("end_time"));
						instance.setCreate_time(obj.optString("create_time"));
						instance.setStart_time(obj.optString("start_time"));
						instance.setName(obj.optString("name"));
						instance.setCode(obj.optString("code"));
						instance.setPhone(obj.optString("phone"));
						instance.setContact(obj.optString("contact"));
						list.add(instance);
					}
					return list;
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static ArrayList<Order> parseOrderList(JSONObject response) {
		try {
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				JSONArray array = data.optJSONArray("orders");
				if (array != null) {
					ArrayList<Order> list = new ArrayList<Order>();
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Order instance = new Order();
						instance.setPrice(obj.optString("price"));
						instance.setUpdate_time(obj.optString("update_time"));
						instance.setEnd_time(obj.optString("end_time"));
						instance.setStatus_value(obj.optString("status_value"));
						instance.setStatus(obj.optString("status"));
						instance.setCreate_time(obj.optString("create_time"));
						instance.setStart_time(obj.optString("start_time"));
						instance.setPay_status_value(obj.optString("pay_status_value"));
						instance.setOrder_no(obj.optString("order_no"));
						instance.setPay_status(obj.optString("pay_status"));

						JSONObject parking = obj.optJSONObject("parking");
						if (parking != null) {
							Parking bean = new Parking();
							bean.setId(parking.optString("id"));
							bean.setName(parking.optString("name"));
							bean.setAddress(parking.optString("address"));
							bean.setLongitude(parking.optString("longitude"));
							bean.setLatitude(parking.optString("latitude"));
							bean.setFree(parking.optString("free"));
							bean.setTotal(parking.optString("total"));
							bean.setPrice(parking.optString("price"));
							bean.setDescription(parking.optString("description"));
							bean.setImage(parking.optString("image"));
							bean.setPhone(parking.optString("phone"));
							bean.setBookable(parking.optString("bookable"));
							instance.setParking(bean);
						}

						list.add(instance);
					}
					return list;
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// 根据停车编码获取停车记录
	public static ArrayList<Park> parseParkRecordList(JSONObject response) {
		try {
			JSONObject data = response.optJSONObject("data");
			if (data != null) {
				JSONArray array = data.optJSONArray("parks");
				if (array != null) {
					ArrayList<Park> list = new ArrayList<Park>();
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Park instance = new Park();
						instance.setPark_no(obj.optString("park_no"));
						instance.setIn_gate_name(obj.optString("in_gate_name"));
						instance.setIn_time(obj.optString("in_time"));
						instance.setStatus(obj.optString("status"));
						instance.setShould_leave_time(obj.optString("should_leave_time"));
						// instance.setPark_id(obj.optString("park_id"));
						// instance.setParking_id(obj.optString("parking_id"));
						instance.setParking_name(obj.optString("parking_name"));
						instance.setPayed_price(obj.optString("payed_price"));

						list.add(instance);
					}
					return list;
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
