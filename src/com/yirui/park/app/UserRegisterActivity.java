package com.yirui.park.app;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Response.Listener;
import com.yirui.park.net.Commands;

/**
 * 忘记密码
 * @author Administrator
 *
 */
public class UserRegisterActivity extends BaseActivity implements
		OnClickListener {

	private EditText et_phone, et_code, et_password;
	private CheckBox check;
	private Button btn_code;
//	private String registerOrForgotPassword="1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register);

//		registerOrForgotPassword=getIntent().getStringExtra("RegisterOrForgotPassword");
//		if(registerOrForgotPassword.equalsIgnoreCase("1")){
//			initActionBar("", App.res.getDrawable(R.drawable.back), "注册", "完成", null);
//		}else if(registerOrForgotPassword.equalsIgnoreCase("2")){
			initActionBar("", App.res.getDrawable(R.drawable.back), "忘记密码", "完成", null);
//		}

		initViews();

	}
	
	@Override
	public void initViews() {
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_code = (EditText) findViewById(R.id.et_code);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_code=(Button)findViewById(R.id.btn_code);
		btn_code.setEnabled(true);
		btn_code.setOnClickListener(this);
		check = (CheckBox) findViewById(R.id.check);

	}

	public void doRightButtonClick(View v) {
		if (check.isChecked()) {
			String phone = et_phone.getText().toString().trim();
			String code = et_code.getText().toString().trim();
			String password = et_password.getText().toString().trim();
			if(TextUtils.isEmpty(phone)||phone.length()<11){
				showToast("手机号不正确");
				return;
			}
			if(TextUtils.isEmpty(code)||code.length()!=4){
				showToast("验证码不正确");
				return;
			}
			if(TextUtils.isEmpty(password)||password.length()<6){
				showToast("密码至少六位");
				return;
			}
//			if(registerOrForgotPassword.equalsIgnoreCase("1")){
//				doCommandCheckRegister(phone,code);
//			}else if(registerOrForgotPassword.equalsIgnoreCase("2")){
				doCommandForgotPassword(phone,code,password);
//			}
		} else {
			showToast("请先同意优宝停车协议");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_code:
			String phone = et_phone.getText().toString().trim();
			if (TextUtils.isEmpty(phone) || phone.length() < 11) {
				showToast("手机号不正确");
				// doAnimation(context, et_phone, R.anim.shake);
				return;
			}
			doCommandVerificationCode(phone,"2");
			break;

		default:
			break;
		}
	}

	private void doCommandVerificationCode(String phone,String type){
		Commands.doCommandVerificationCode(context,phone,type,new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				// Log.i("tag", response.toString());
				if (isSuccess(response)) {
					showToast("验证码已发送，请注意查收");
					et_phone.setEnabled(false);
					
					int timer=60*1000;
					
					new CountDownTimer(timer, 1000) {  
					    public void onTick(long millisUntilFinished) {
					    	btn_code.setEnabled(false);
					    	btn_code.setText("获取验证码("+(millisUntilFinished / 1000)+")");  
					    }  
					    public void onFinish() {
					    	btn_code.setEnabled(true);
					    	btn_code.setText("获取验证码");
					    }  
					 }.start();  
					 
					
				}
			}

		});
	}
//	private void doCommandCheckRegister(String phone,String code){
//		App.user.setPhone(phone);
//		App.user.setCode(code);
//		Commands.doCommandCheckRegister(context,App.user, new Listener<JSONObject>() {
//			@Override
//			public void onResponse(JSONObject response) {
//				// TODO Auto-generated method stub
//				// Log.i("tag", response.toString());
//				if (isSuccess(response)) {
//					JSONObject data = response.optJSONObject("data");
//					if (data != null) {
//						App.user.setUser_session_key(data.optString("user_session_key"));
//						App.user.setUser_id(data.optString("user_id"));
//						
//						String password = et_password.getText().toString().trim();
//						doCommandRegister("1",password);
//					}
//					
//					Log.i("tag", App.user.getUser_id()+";;;"+App.user.getUser_session_key());
//				}
//			}
//			
//		});
//	}
	
//	private void doCommandRegister(String gender,String password){
//		App.user.setPassword(password);
//		App.user.setGender(gender);
//		App.user.setPlatform(App.config.getPlatform());
//		Commands.doCommandRegister(context,App.user, new Listener<JSONObject>() {
//			@Override
//			public void onResponse(JSONObject response) {
//				// TODO Auto-generated method stub
//				// Log.i("tag", response.toString());
//				if (isSuccess(response)) {
//					JSONObject data = response.optJSONObject("data");
//					if (data != null) {
//						App.user.setUser_session_key(data.optString("user_session_key"));
//						App.user.setUser_id(data.optString("user_id"));
//					}
//					
//					Log.i("tag", App.user.getUser_id()+";;;"+App.user.getUser_session_key());
//				}
//			}
//			
//		});
//	}

	private void doCommandForgotPassword(String phone,String code,String newPassword){
		App.user.setPhone(phone);
		App.user.setCode(code);
		Commands.doCommandForgotPassword(context,App.user,newPassword, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				 Log.i("tag", response.toString());
				if (isSuccess(response)) {
					showToast("重设成功");
					finish();
//					JSONObject data = response.optJSONObject("data");
//					if (data != null) {
//						App.user.setUser_session_key(data.optString("user_session_key"));
//						App.user.setUser_id(data.optString("user_id"));
//						
//						String password = et_password.getText().toString().trim();
//						doCommandRegister("1",password);
//					}
//					
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
