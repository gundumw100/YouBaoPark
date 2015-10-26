package com.yirui.park.app;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Response.Listener;
import com.base.util.comm.SPUtils;
import com.yirui.park.model.User;
import com.yirui.park.net.Commands;
import com.yirui.park.net.Json;

public class UserLoginActivity extends BaseActivity implements OnClickListener{

	private EditText et_phone,et_password;
	private CheckBox check_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initViews();
    }

    @Override
    public void initViews(){
    	et_phone=(EditText)findViewById(R.id.et_phone);
    	et_password=(EditText)findViewById(R.id.et_password);
    	check_password=(CheckBox)findViewById(R.id.check_password);
    	
    	et_phone.setText((String)SPUtils.get(context, "Phone", ""));
    	et_password.setText((String)SPUtils.get(context, "Password", ""));
    	check_password.setChecked((Boolean)SPUtils.get(context, "Checked", true));
    	
    	findViewById(R.id.btn_ok).setOnClickListener(this);
    	findViewById(R.id.tv_forgot).setOnClickListener(this);
    	findViewById(R.id.tv_register).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=null;
		switch (v.getId()) {
		case R.id.btn_ok://登录
			doCommandServerInfo();
			break;
		case R.id.tv_forgot://忘记密码
			intent=new Intent(context,UserRegisterActivity.class);
//			intent.putExtra("RegisterOrForgotPassword", "2");
			startActivity(intent);
			break;
		case R.id.tv_register://还未注册
			intent=new Intent(context,UserVerificationCodeActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	
	private void doCommandServerInfo(){
		Commands.doCommandServerInfo(context,App.user, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
//				 Log.i("tag", response.toString());
				if (isSuccess(response)) {
					JSONObject data = response.optJSONObject("data");
					if (data != null) {
						App.user.setToken(data.optString("server_time"));
						long delta=Long.parseLong(App.user.getToken())-System.currentTimeMillis();
						Log.i("tag", "delta==="+delta);
						App.user.setDelta(delta);
//						App.user.setIP(data.optString("server_ip"));
						doCommandLogin();
					}else{
						showToast("Token is null");
					}
					
				}
			}

		});
	}
	
	private void doCommandLogin(){
		App.user.setPhone(et_phone.getText().toString().trim());
		//密码，二次md5后和token合并再一次md5.md5(md5(md5(password))+token)
		String password=et_password.getText().toString().trim();
		App.user.setPassword(password);
		App.user.setLogined(false);
		Commands.doCommandLogin(context,App.user, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
//				 Log.i("tag", response.toString());
				if (isSuccess(response)) {
					if(Json.parseLogin(response)){
						savePhoneAndPasswordOrNot(App.user);
						doCommandGetUserInfo();
					}
				}
			}

		});
	}

	private void doCommandGetUserInfo(){
		Commands.doCommandGetUserInfo(context,App.user,new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
//				Log.i("tag", response.toString());
				if (isSuccess(response)) {
					if(Json.parseGetUserInfo(response)){
						EventBus.getDefault().post(App.EVENT_UPDATE_MAIN_PAGE);
						finish();
					}
				}
			}
		});
	}
	
	private void savePhoneAndPasswordOrNot(User user){
		if(check_password.isChecked()){
			SPUtils.put(context, "Phone", user.getPhone());
			SPUtils.put(context, "Password", user.getPassword());
			SPUtils.put(context, "Checked", check_password.isChecked());
		}else{
			if(SPUtils.contains(context, "Phone")){
				SPUtils.remove(context, "Phone");
			}
			if(SPUtils.contains(context, "Password")){
				SPUtils.remove(context, "Password");
			}
			if(SPUtils.contains(context, "Checked")){
				SPUtils.remove(context, "Checked");
			}
		}
	}
	
	@Subcriber
	void updateByEventBus(String event) {
		if (event.equals(App.EVENT_FINISH)) {
			finish();
		}
	}

	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
}
