package com.yirui.park.app;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.base.app.BaseAppActivity;

public abstract class BaseActivity extends BaseAppActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	public void initActionBar(String title, String rightBtn, Drawable drawableForRightBtn){
		initActionBar(null, App.res.getDrawable(R.drawable.back), title, rightBtn, drawableForRightBtn);
	}
	
	
	/**
	 * 初始化view组件，通常写在实现类的onCreate()中
	 */
	public abstract void initViews();
	/**
	 * 初始化view中内容，通常写在实现类的onResume()中
	 */
//	public abstract void initValues();
	/**
	 * 刷新view中内容，通常写在网络请求成功后的回调函数中
	 */
	public abstract void updateViews(Object obj);
	
	
	@SuppressLint("NewApi")
	public void changeToLoginActivity(View view,boolean ani){
		Intent intent = new Intent(context, UserLoginActivity.class);
		if(android.os.Build.VERSION.SDK_INT>=16&&ani){
			ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
					0, view.getWidth(), view.getHeight());
			startActivity(intent, options.toBundle());
		}else{
			startActivity(intent);
		}
	}
	
}
