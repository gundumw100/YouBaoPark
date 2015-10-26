package com.yirui.park.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.base.app.ViewHolder;
import com.base.fragment.BaseRefreshListFragment;
import com.base.model.KeyValue;
import com.base.util.MD5Utils;
import com.yirui.park.app.App;
import com.yirui.park.app.R;
import com.yirui.park.model.Order;
import com.yirui.park.net.Commands;
import com.yirui.park.net.Json;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * 预定管理
 * 
 * @author Administrator
 *
 */
public class ReservationManagementFragment extends BaseRefreshListFragment<Order> {

	private KeyValue curKeyValue;

	public static ReservationManagementFragment newInstance(KeyValue kv) {
		ReservationManagementFragment fragment = new ReservationManagementFragment();
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
		url = Commands.URL_PARKING_ORDERS;
		map = new HashMap<String, String>();
		map.put("phone", App.user.getPhone());
		map.put("password", pwd);
		map.put("token", token);
		map.put("parking_id", App.user.getParking_id());
		// 预订状态；1：未确认；2：待支付；3：已支付；4：已使用；5；用户退订；6：停车场退订；7：退款中；8；已退款
		map.put("status", curKeyValue.getKey());
		layoutId = R.layout.item_for_order;
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
				context.showToast("position=" + position);
			}
		});
	}

	@Override
	public ArrayList<Order> parseToList(JSONObject response) {
		// TODO Auto-generated method stub
		return Json.parseOrderList(response);
	}

	@Override
	public void createItem(ViewHolder helper, Order item, final int position) {
		// TODO Auto-generated method stub
		helper.setText(R.id.item_0, "订单编号:" + item.getOrder_no());
		helper.setText(R.id.item_1, "进场时间:" + item.getStart_time());
		helper.setText(R.id.item_2, "停车场名称:" + item.getParking().getName());
		View btn_0 = helper.getView(R.id.btn_0);
		View btn_1 = helper.getView(R.id.btn_1);
		View btn_2 = helper.getView(R.id.btn_2);
		View btn_3 = helper.getView(R.id.btn_3);
		int key = Integer.parseInt(curKeyValue.getKey());
		switch (key) {
		case 1:// 待确认
		case 2:// 待支付
		case 3:// 已支付
			btn_0.setVisibility(View.VISIBLE);
			btn_1.setVisibility(View.VISIBLE);
			btn_2.setVisibility(View.GONE);
			btn_3.setVisibility(View.GONE);
			btn_0.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Commands.doCommandOrderCancel(context, App.user, item,
					// new Listener<JSONObject>() {
					// @Override
					// public void onResponse(JSONObject response) {
					// // TODO Auto-generated method stub
					// if (isSuccess(response)) {
					// context.showToast("取消成功");
					// getBeans().remove(item);
					// notifyDataSetChanged();
					// }
					// }
					//
					// });

				}
			});
			break;
		case 4:// 已入场
			break;
		case 5:// 用户退订
			btn_0.setVisibility(View.GONE);
			btn_1.setVisibility(View.VISIBLE);
			btn_2.setVisibility(View.GONE);
			btn_3.setVisibility(View.GONE);
			break;
		case 6:// 退单
			btn_0.setVisibility(View.GONE);
			btn_1.setVisibility(View.VISIBLE);
			btn_2.setVisibility(View.GONE);
			btn_3.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

}
