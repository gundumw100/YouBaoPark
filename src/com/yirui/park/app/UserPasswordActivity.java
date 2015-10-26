package com.yirui.park.app;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.android.volley.Response.Listener;
import com.yirui.park.net.Commands;

public class UserPasswordActivity extends BaseActivity implements
		OnClickListener {

	private EditText et_password;
	private String phone,code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_password);
		initActionBar("", App.res.getDrawable(R.drawable.back), "注册", "确定", null);
		phone=getIntent().getStringExtra("Phone");
		code=getIntent().getStringExtra("Code");
		initViews();
	}
	@Override
	public void initViews() {
		et_password = (EditText) findViewById(R.id.et_password);
	}

	public void doRightButtonClick(View v) {
		String password = et_password.getText().toString().trim();
		if(TextUtils.isEmpty(password)||password.length()<6){
			showToast("密码至少六位");
			return;
		}
		doCommandCheckRegister(phone,code);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	private void doCommandCheckRegister(final String phone,final String code){
		Commands.doCommandCheckRegister(context,phone,code, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
//				 Log.i("tag", response.toString());
				if (isSuccess(response)) {
					App.user.setPhone(phone);
					App.user.setCode(code);
					
					String password = et_password.getText().toString().trim();
					doCommandRegister(password,"1");
				}
			}
			
		});
	}
	
	private void doCommandRegister(final String password,final String gender){
		Commands.doCommandRegister(context,App.user,password,gender, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
//				 Log.i("tag", response.toString());
				if (isSuccess(response)) {
					JSONObject data = response.optJSONObject("data");
					if (data != null) {
						App.user.setLogined(true);
						App.user.setPassword(password);
						App.user.setGender(gender);
						App.user.setUser_session_key(data.optString("user_session_key"));
						App.user.setUser_id(data.optString("user_id"));
					}
					EventBus.getDefault().post(App.EVENT_FINISH);
					finish();
//					Log.i("tag", App.user.getUser_id()+";;;"+App.user.getUser_session_key());
					
				}
			}
			
		});
	}
	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		
	}

}
