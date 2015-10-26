package com.yirui.park.net;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Response.Listener;
import com.base.net.VolleyHelper;
import com.base.util.MD5Utils;
import com.yirui.park.app.App;
import com.yirui.park.app.R;
import com.yirui.park.model.Shop;
import com.yirui.park.model.User;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

public class Commands {

	// BASE URL
  public static final String BASE_URL = "http://api.prod.yiruiy.cn/parking/api/v1/";//正式环境
//  public static final String BASE_URL = "http://192.168.1.114:8080/parking/api/v1/";
  
  public static final String ROLE="admins/";//角色
  
  public static final String URL_SERVER_INFO = BASE_URL + "systems/server_info";
  public static final String URL_VERIFICATION = BASE_URL+"systems/send_verification_code";
  public static final String URL_CHECK_REGISTER = BASE_URL+ROLE+"check_register";
  public static final String URL_REGISTER = BASE_URL+ROLE+"register";
  public static final String URL_LOGIN = BASE_URL+ROLE+"login";
  
  public static final String URL_MODIFY_PASSWORD = BASE_URL +ROLE+ "modify_password";
  public static final String URL_LOGOUT = BASE_URL +ROLE+ "logout";
  public static final String URL_IS_REGISTERED = BASE_URL +ROLE+ "is_registered";
  public static final String URL_FORGOT_PASSWORD = BASE_URL +ROLE+ "forget_password";
  public static final String URL_GET_USER_INFO = BASE_URL +"users/get_user_infor";
  
  public static final String URL_PARKING_ORDERS = BASE_URL + "admins/parking_orders";//停车场员工的预订列表
  public static final String URL_SHOP_LIST = BASE_URL + "admins/shop_list";//停车场店铺列表
  public static final String URL_SHOP_DETAIL = BASE_URL + "admins/shop_detail";//停车场查看店铺详情
  public static final String URL_SET_SHOP = BASE_URL + "admins/set_shop";//创建和修改店铺
  public static final String URL_CREATE_AUTHORIZATION_CODE = BASE_URL + "admins/create_authorizationCode";//停车场公司管理员创建店铺授权码
  public static final String URL_SET_POLICY = BASE_URL + "admins/set_policy";//设置停车场费率策略
  
//  public static final String URL_PARKING_QUERY = BASE_URL + "parking/query/99";
//  public static final String URL_PARKING_SURROUND = BASE_URL + "parking/surround/99";
//  public static final String URL_PARKING_DETAIL = BASE_URL + "parking/detail/";
//  public static final String URL_PARKING_ENTER_LIST = BASE_URL + "parking/enter_list/";//用户的进出停车场记录
//  
//  public static final String URL_ORDERS_USER_ORDERS = BASE_URL + "orders/user_orders";//用户预订列表
//  public static final String URL_ORDERS_CREATE = BASE_URL + "orders/create";//预订停车位
//  public static final String URL_ORDERS_CANCEL = BASE_URL + "orders/cancel";//用户订单退订
//  public static final String URL_ORDERS_PAY = BASE_URL + "orders/pay";//用户支付订单
//  
//  public static final String URL_PARK_LIST = BASE_URL + "park/list";//根据停车编码获取停车记录
  
  /**
   * 设置停车场费率策略
   * @param context
   * @param user
   * @param onSuccessListener
   */
  public static void doCommandSetPolicy(Context context,User user,String policy,Listener<JSONObject> onSuccessListener){
	  String[] session = getSession();
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("phone", user.getPhone());// 商铺管理员手机号
	  map.put("password", session[0]);
	  map.put("token", session[1]);
	  map.put("parking_type", "2");//停车类型，1：固定车位；2：临时车位
	  map.put("leave_time", "15");//最晚离场时间，分钟（整型），支付后离场时间
	  map.put("price_per_day", "0");//每天的计费价格（整型）
	  map.put("sale_unit_price", "0");//优惠券购买单价，支持小数
	  map.put("grant_discount_type", "1");//发放优惠券配额类型1：清空型；2：可累计型
	  map.put("parking_id", user.getParking_id());//
	  map.put("content", policy);//计费策略，json格式字符串
	  map.put("platform", user.getConfig().getPlatform());//平台，1：iOS，2：Android
	  VolleyHelper.execPostRequest(context,URL_SET_POLICY, map, onSuccessListener);
  }
  /**
   * 停车场公司管理员创建店铺授权码
   * @param context
   * @param user
   * @param shop_id
   * @param onSuccessListener
   */
  public static void doCommandCreateAuthorizationCode(Context context,User user,String shop_id,Listener<JSONObject> onSuccessListener){
	  String[] session = getSession();
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("phone", user.getPhone());// 商铺管理员手机号
	  map.put("password", session[0]);
	  map.put("token", session[1]);
	  map.put("shop_id", shop_id);//
	  map.put("platform", user.getConfig().getPlatform());//平台，1：iOS，2：Android
	  VolleyHelper.execPostRequest(context,URL_CREATE_AUTHORIZATION_CODE, map, onSuccessListener);
  }
  /**
   * 创建和修改店铺
   * @param context
   * @param user
   * @param shop
   * @param onSuccessListener
   */
  public static void doCommandSetShop(Context context,User user,Shop shop,Listener<JSONObject> onSuccessListener){
	  String[] session = getSession();
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("phone", user.getPhone());// 商铺管理员手机号
	  map.put("password", session[0]);
	  map.put("token", session[1]);
	  if(!TextUtils.isEmpty(shop.getId())){
		  map.put("id", shop.getId());//修改店铺公司id
	  }else{
		  map.put("parking_id", shop.getParking_id());//
	  }
//	  map.put("enterprise_id", shop.getEnterprise_id());//创建店铺所属公司id
	  map.put("name", shop.getName());
	  map.put("address", shop.getAddress());
//	  map.put("code", shop.getCode());
//	  map.put("shop_phone", shop.getShop_phone());
//	  map.put("email", shop.getEmail());
	  map.put("mobile_phone", shop.getMobile_phone());
	  map.put("contact", shop.getContact());
	  map.put("grant_quota", shop.getGrant_quota());//优惠额度
	  map.put("buy_quota", shop.getBuy_quota());//可购买的额度限额
	  
	  map.put("platform", user.getConfig().getPlatform());//平台，1：iOS，2：Android
	  VolleyHelper.execPostRequest(context,URL_SET_SHOP, map, onSuccessListener);
  }
  /**
   * 停车场查看店铺详情
   * @param context
   * @param user
   * @param shop_id
   * @param onSuccessListener
   */
  public static void doCommandShopDetail(Context context,User user,String shop_id,Listener<JSONObject> onSuccessListener){
	  String[] session = getSession();
	  Map<String, String> map = new HashMap<String, String>();
	  map.put("phone", user.getPhone());// 商铺管理员手机号
	  map.put("password", session[0]);
	  map.put("token", session[1]);
	  map.put("shop_id", shop_id);//
	  map.put("platform", user.getConfig().getPlatform());//平台，1：iOS，2：Android
	  VolleyHelper.execPostRequest(context,URL_SHOP_DETAIL, map, onSuccessListener);
  }
 
	// 发送验证码
	public static void doCommandVerificationCode(Context context, String phone, String type,
			Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("type", type);
		VolleyHelper.execPostRequest(context, URL_VERIFICATION, map, onSuccessListener);
	}

	// 注册前验证
	public static void doCommandCheckRegister(Context context, String phone, String code,
			Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("code", code);
		VolleyHelper.execPostRequest(context, URL_CHECK_REGISTER, map, onSuccessListener);
	}

	// 注册
	public static void doCommandRegister(Context context, User user, String password, String gender,
			Listener<JSONObject> onSuccessListener) {
		// String pwd = MD5Utils.Md5(MD5Utils.Md5(MD5Utils.Md5(password)) +
		// String.valueOf(System.currentTimeMillis()+App.user.getDelta()));
		String pwdMd5 = MD5Utils.Md5(password);
		String pwd = MD5Utils.Md5(pwdMd5);

		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", user.getPhone());
		map.put("code", user.getCode());
		map.put("password", pwd);
		map.put("gender", gender);
		map.put("platform", user.getConfig().getPlatform());
		VolleyHelper.execPostRequest(context, URL_REGISTER, map, onSuccessListener);
	}

	public static void doCommandServerInfo(Context context, User user, Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("platform", user.getConfig().getPlatform());
		VolleyHelper.execPostRequest(context, URL_SERVER_INFO, map, onSuccessListener);
	}

	// 登录
	public static void doCommandLogin(Context context, User user, Listener<JSONObject> onSuccessListener) {
		String token = String.valueOf(System.currentTimeMillis() + App.user.getDelta());
		String pwd = MD5Utils.Md5(MD5Utils.Md5(MD5Utils.Md5(user.getPassword())) + token);
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", user.getPhone());
		map.put("password", pwd);// 密码，二次md5后和token合并再一次md5.md5(md5(md5(password))+token)
		map.put("token", token);// 服务器时间戳
		map.put("platform", user.getConfig().getPlatform());
		VolleyHelper.execPostRequest(context, URL_LOGIN, map, onSuccessListener);
	}

	// 修改密码
	public static void doCommandModifyPassword(Context context, User user, String oldPassword, String newPassword,
			Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_session_key", user.getUser_session_key());
		map.put("old_password", MD5Utils.Md5(MD5Utils.Md5(oldPassword)));
		map.put("new_password", MD5Utils.Md5(MD5Utils.Md5(newPassword)));
		VolleyHelper.execPostRequest(context, URL_MODIFY_PASSWORD, map, onSuccessListener);
	}

	// 退出
	public static void doCommandLogout(Context context, User user, String oldPassword, String newPassword,
			Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_session_key", user.getUser_session_key());
		VolleyHelper.execPostRequest(context, URL_LOGOUT, map, onSuccessListener);
	}

	// 判断用户是否注册
	public static void doCommandIsRegistered(Context context, String phone, String oldPassword, String newPassword,
			Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		VolleyHelper.execPostRequest(context, URL_IS_REGISTERED, map, onSuccessListener);
	}

	// 忘记密码
	public static void doCommandForgotPassword(Context context, User user, String newPassword,
			Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phone", user.getPhone());
		map.put("code", user.getCode());
		map.put("new_password", MD5Utils.Md5(MD5Utils.Md5(newPassword)));
		VolleyHelper.execPostRequest(context, URL_FORGOT_PASSWORD, map, onSuccessListener);
	}

	// 用户信息
	public static void doCommandGetUserInfo(Context context, User user, Listener<JSONObject> onSuccessListener) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_session_key", user.getUser_session_key());
		map.put("app", String.valueOf(user.getApp()));//
		VolleyHelper.execPostRequest(context, URL_GET_USER_INFO, map, onSuccessListener);
	}

	/**
	 * 加载图片
	 * 
	 * @param url
	 * @param iv
	 */
	public static void loadImage(String url, ImageView iv) {
		VolleyHelper.loadImageByVolley(url, iv, R.drawable.default_img);
	}

	/**
	 * 0:pwd 1:token
	 * 
	 * @return
	 */
	private static String[] getSession() {
		String[] s = new String[2];
		String token = String.valueOf(System.currentTimeMillis() + App.user.getDelta());
		String pwd = MD5Utils.Md5(MD5Utils.Md5(MD5Utils.Md5(App.user.getPassword())) + token);
		s[0] = pwd;
		s[1] = token;
		return s;
	}
	
}
