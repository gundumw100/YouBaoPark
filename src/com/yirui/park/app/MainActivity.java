package com.yirui.park.app;

import com.slidingmenu.lib.SlidingMenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements OnClickListener{

	private Context context;
	private SlidingMenu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=this;
		initActionBar(null, App.res.getDrawable(R.drawable.ic_menu), "优宝停车场", null, null);
		initSlidingMenu(false);
		initViews();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		findViewById(R.id.btn_0).setOnClickListener(this);
		findViewById(R.id.btn_1).setOnClickListener(this);
		findViewById(R.id.btn_2).setOnClickListener(this);
		findViewById(R.id.btn_3).setOnClickListener(this);
		findViewById(R.id.btn_4).setOnClickListener(this);
		findViewById(R.id.btn_5).setOnClickListener(this);
		findViewById(R.id.btn_6).setOnClickListener(this);
		findViewById(R.id.btn_7).setOnClickListener(this);
	}

	private void initSlidingMenu(boolean isBoth) {
		menu = new SlidingMenu(this);
		menu.setMode(isBoth ? SlidingMenu.LEFT_RIGHT : SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
//		menu.setBehindOffsetRes(R.dimen.slidingmenu_behind_offset);
//		menu.setBehindOffset(100);
		menu.setBehindWidth((int) (App.user.getConfig().getScreenWidth() * 0.8));
		menu.setBehindScrollScale(0.5f);//设置滑动时拖拽效果
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.sliding_menu_left);
//		if (isBoth) {
//			menu.setSecondaryMenu(R.layout.sliding_menu_right);
//			menu.setSecondaryShadowDrawable(R.drawable.shadow_right);
//			View view=menu.getSecondaryMenu();
//		}
//		View view=menu.getMenu();
		
		
	}
	
	@Override
	public void doLeftButtonClick(View v) {
		menu.toggle();
	}
	
	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_0:
			if(App.user.isLogined()){
				Intent intent=new Intent(context,ReservationManagementActivity.class);
				startActivity(intent);
			}else{
				changeToLoginActivity(v,false);
			}
			break;
		case R.id.btn_1:
			if(App.user.isLogined()){
				Intent intent=new Intent(context,MerchantManagementActivity.class);
				startActivity(intent);
			}else{
				changeToLoginActivity(v,false);
			}
			break;
		case R.id.btn_7://收费情况
			if(App.user.isLogined()){
				Intent intent=new Intent(context,ChargesActivity.class);
				startActivity(intent);
			}else{
				changeToLoginActivity(v,false);
			}
			break;

		default:
			break;
		}
	}
	
	private long exitTime = 0;
	private void exitApp() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}

	}

	@Override
	public void onBackPressed() {
		exitApp();
	}
	
	
}
