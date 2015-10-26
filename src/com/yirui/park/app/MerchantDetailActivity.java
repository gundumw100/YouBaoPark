package com.yirui.park.app;

import java.util.ArrayList;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

import com.android.volley.Response.Listener;
import com.base.app.CommonAdapter;
import com.base.app.ViewHolder;
import com.yirui.park.model.Data;
import com.yirui.park.model.Shop;
import com.yirui.park.model.Stat;
import com.yirui.park.net.Commands;
import com.yirui.park.net.Json;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MerchantDetailActivity extends BaseActivity implements OnClickListener {

	private EditText et_name, et_address, et_grant_quota;
	private TextView tv_current_month, tv_buy_balance, tv_grant_balance, et_contact, et_mobile_phone, et_buy_quota;
	private String curId;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_merchant_detail);
		initActionBar("商户信息", "保存", null);
		initViews();
		curId = getIntent().getStringExtra("ID");
		if (!isEmpty(curId)) {
			doCommandShopDetail();
		}
	}

	private void doCommandShopDetail() {
		Commands.doCommandShopDetail(context, App.user, curId, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				// Log.i("tag", response.toString());
				if (isSuccess(response)) {
					updateViews(Json.parseShopDetail(response));
				}
			}
		});
	}

	@Override
	public void initViews() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_address = (EditText) findViewById(R.id.et_address);
		et_grant_quota = (EditText) findViewById(R.id.et_grant_quota);
		et_contact = (EditText) findViewById(R.id.et_contact);
		et_mobile_phone = (EditText) findViewById(R.id.et_mobile_phone);
		et_buy_quota = (EditText) findViewById(R.id.et_buy_quota);
		tv_current_month = (TextView) findViewById(R.id.tv_current_month);
		tv_buy_balance = (TextView) findViewById(R.id.tv_buy_balance);
		tv_grant_balance = (TextView) findViewById(R.id.tv_grant_balance);
		listView = (ListView) findViewById(R.id.listView);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	@Subcriber
	void updateByEventBus(String event) {
	}

	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		Data bean = (Data) obj;
		et_name.setText(bean.getShop().getName());
		et_address.setText(bean.getShop().getAddress());
		et_grant_quota.setText(bean.getShop().getGrant_quota());
		et_contact.setText(bean.getShop().getContact());
		et_mobile_phone.setText(bean.getShop().getMobile_phone());
		et_buy_quota.setText(bean.getShop().getBuy_quota());

		tv_current_month.setText("本月优惠发放总量:" + bean.getCurrent_month());
		tv_buy_balance.setText("超额购买余量:" + bean.getBuy_balance());
		tv_grant_balance.setText("本月额度余量:" + bean.getGrant_balance());

		notifyDataSetChanged(bean.getStats());
	}

	@Override
	public void doRightButtonClick(View v) {
		// TODO Auto-generated method stub
		Shop shop=new Shop();
		shop.setId(curId);
		shop.setName(et_name.getText().toString());
		shop.setAddress(et_address.getText().toString());
		shop.setGrant_quota(et_grant_quota.getText().toString());
		shop.setContact(et_contact.getText().toString());
		shop.setMobile_phone(et_mobile_phone.getText().toString());
		shop.setBuy_quota(et_buy_quota.getText().toString());
		shop.setParking_id("1");
		
		Commands.doCommandSetShop(context, App.user, shop, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				// Log.i("tag", response.toString());
				if (isSuccess(response)) {
					showToast(R.string.operate_success);
					EventBus.getDefault().post(App.EVENT_UPDATE_MERCHANT_MANAGEMENT);
					finish();
				}
			}
		});
		
	}

	private CommonAdapter<Stat> adapter;

	protected void notifyDataSetChanged(ArrayList<Stat> stats) {
		if (adapter == null) {
			adapter = new CommonAdapter<Stat>(context, stats, R.layout.item_for_stat) {
				@Override
				public void setValues(ViewHolder helper, Stat item, int position) {
					helper.setText(R.id.item_0, item.getYear() +"-"+ item.getMonth());
					helper.setText(R.id.item_1, item.getGrant_size());
					helper.setText(R.id.item_2, item.getBuy_size());
					helper.setText(R.id.item_3, item.getTotal());
				}
			};
			listView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

}
