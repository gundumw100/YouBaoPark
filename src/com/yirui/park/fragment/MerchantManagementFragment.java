package com.yirui.park.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import org.simple.eventbus.Subcriber;

import com.android.volley.Response.Listener;
import com.base.app.ViewHolder;
import com.base.fragment.BaseRefreshListFragment;
import com.base.model.KeyValue;
import com.base.util.MD5Utils;
import com.yirui.park.app.App;
import com.yirui.park.app.MerchantDetailActivity;
import com.yirui.park.app.R;
import com.yirui.park.model.Shop;
import com.yirui.park.net.Commands;
import com.yirui.park.net.Json;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 商户管理
 * 
 * @author Administrator
 *
 */
public class MerchantManagementFragment extends BaseRefreshListFragment<Shop> {

	private KeyValue curKeyValue;

	public static MerchantManagementFragment newInstance(KeyValue kv) {
		MerchantManagementFragment fragment = new MerchantManagementFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable("KeyValue", kv);
		fragment.setArguments(bundle);
		return fragment;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			curKeyValue = args.getParcelable("KeyValue");
		}
		initArgs();
	}

	// 初始化参数,需要根据实际情况赋值
	private void initArgs() {
		String token = String.valueOf(System.currentTimeMillis() + App.user.getDelta());
		String pwd = MD5Utils.Md5(MD5Utils.Md5(MD5Utils.Md5(App.user.getPassword())) + token);
		url = Commands.URL_SHOP_LIST;
		map = new HashMap<String, String>();
		map.put("phone", App.user.getPhone());
		map.put("password", pwd);
		map.put("token", token);
		map.put("parking_id", App.user.getParking_id());
		layoutId = R.layout.item_for_merchant;
		listViewType = EXPANDABLE;

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_list_comm, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initListView(view);
		getListView().setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,MerchantDetailActivity.class);
				intent.putExtra("ID", getItems().get(position).getId());
				startActivity(intent);
			}
		});
	}

	@Override
	public ArrayList<Shop> parseToList(JSONObject response) {
		// TODO Auto-generated method stub
		return Json.parseShopList(response);
	}

	@Override
	public void createItem(ViewHolder helper, final Shop item, final int position) {
		// TODO Auto-generated method stub
		helper.setText(R.id.item_0, "Name:" + item.getName());
		helper.setText(R.id.item_1, "Contact:" + item.getContact());
		helper.setText(R.id.item_2, "Mobile_phone:" + item.getMobile_phone());
		View btn_0 = helper.getView(R.id.btn_0);
		View btn_1 = helper.getView(R.id.btn_1);
		View btn_2 = helper.getView(R.id.btn_2);
		btn_0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doCommandCreateAuthorizationCode(item);
			}
		});
		btn_1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.doDial(item.getMobile_phone());
			}
		});
		btn_2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.showToast("锁定");
			}
		});
	}

	@Subcriber
	void updateByEventBus(String event) {
		if (event.equals(App.EVENT_UPDATE_MERCHANT_MANAGEMENT)) {
			initArgs();
			reloadItems();
		}
	}
	
	private void doCommandCreateAuthorizationCode(Shop shop){
		Commands.doCommandCreateAuthorizationCode(context, App.user, shop.getId(), new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.i("tag", response.toString());
				if(isSuccess(response)){
					context.showToast("邀请码发送成功");
				}
			}
		});
	}
	
}
