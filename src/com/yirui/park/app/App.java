package com.yirui.park.app;

import com.base.app.BaseApp;
import com.yirui.park.model.User;

/**
 * 启动类，初始化参数等
 * 
 * @author pythoner
 * 
 */
public class App extends BaseApp {
	
	public static User user=new User();
	
	public final static String EVENT_FINISH="finish";
	public final static String EVENT_UPDATE_MAIN_PAGE="updateMainPage";
	public final static String EVENT_UPDATE_MERCHANT_MANAGEMENT="updateMerchantManagement";
	
	@Override
	public void onCreate() {
		super.onCreate();
		user.setConfig(config);
	}

	public static void release(){
	}
}
