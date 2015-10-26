package com.yirui.park.app;

import org.json.JSONObject;
import org.simple.eventbus.Subcriber;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Response.Listener;
import com.yirui.park.net.Commands;

/**
 * 
 * @author pythoner
 *	@see http://blog.csdn.net/yayun0516/article/details/47699943
 */
public class UserVerificationCodeActivity extends BaseActivity implements
		OnClickListener {

	private EditText et_phone, et_code;
	private CheckBox check;
	private Button btn_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_verification_code);
		initActionBar("", App.res.getDrawable(R.drawable.back), "注册", "下一步", null);
		initViews();

	}

	@Override
	public void initViews() {
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_code = (EditText) findViewById(R.id.et_code);
		btn_code = (Button) findViewById(R.id.btn_code);
		btn_code.setEnabled(true);
		btn_code.setOnClickListener(this);
		check = (CheckBox) findViewById(R.id.check);
	}

	public void doRightButtonClick(View v) {
		if (check.isChecked()) {
			String phone = et_phone.getText().toString().trim();
			String code = et_code.getText().toString().trim();
			if (TextUtils.isEmpty(phone) || phone.length() < 11) {
				showToast("手机号不正确");
				return;
			}
			if (TextUtils.isEmpty(code) || code.length() != 4) {
				showToast("验证码不正确");
				return;
			}
			Intent intent = new Intent(context, UserPasswordActivity.class);
			intent.putExtra("Phone", phone);
			intent.putExtra("Code", code);
			startActivity(intent);
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
			doCommandVerificationCode(phone, "1");
			break;

		default:
			break;
		}
	}

	private void doCommandVerificationCode(String phone, String type) {
		Commands.doCommandVerificationCode(context, phone, type,
				new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						// Log.i("tag", response.toString());
						if (isSuccess(response)) {
							showToast("验证码已发送，请注意查收");
							// et_phone.setEnabled(false);

							int timer = 60 * 1000;

							new CountDownTimer(timer, 1000) {
								public void onTick(long millisUntilFinished) {
									btn_code.setEnabled(false);
									btn_code.setText("获取验证码("
											+ (millisUntilFinished / 1000)
											+ ")");
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
