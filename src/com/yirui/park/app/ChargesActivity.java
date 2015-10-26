package com.yirui.park.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response.Listener;
import com.yirui.park.net.Commands;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 收费情况
 * @author Administrator
 *
 */
public class ChargesActivity extends BaseActivity implements View.OnClickListener{

	private Context context;
	private EditText et_freeTime,et_total,et_weekPrice;
	private LinearLayout container_time,container_day;
	private Button btn_add_day;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charges);
		context=this;
		initActionBar("收费情况", "保存", null);
		initViews();
	}

	@Override
	public void initViews() {
		et_freeTime=(EditText)findViewById(R.id.et_freeTime);
		et_total=(EditText)findViewById(R.id.et_total);
		et_weekPrice=(EditText)findViewById(R.id.et_weekPrice);
		container_time=(LinearLayout)findViewById(R.id.container_time);
		Button btn_add_time=(Button)findViewById(R.id.btn_add_time);
		btn_add_time.setOnClickListener(this);
		
		addTime();
		
		container_day=(LinearLayout)findViewById(R.id.container_day);
		btn_add_day=(Button)findViewById(R.id.btn_add_day);
		btn_add_day.setOnClickListener(this);
		
//		addDay();
	}

	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		
	}
	
	private int toInt(String text){
		if(isEmpty(text)){
			return 0;
		}
		return Integer.parseInt(text);
	}
	
	@Override	
	public void doRightButtonClick(View v) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			if(isEmpty(et_freeTime.getText().toString())){
        		showToast(R.string.toast_data_uncompleted);
        		doAnimation(context, et_freeTime, R.anim.shake);
        		return;
        	}
			if(isEmpty(et_total.getText().toString())){
				showToast(R.string.toast_data_uncompleted);
        		doAnimation(context, et_total, R.anim.shake);
        		return;
			}
			if(isEmpty(et_weekPrice.getText().toString())){
				showToast(R.string.toast_data_uncompleted);
				doAnimation(context, et_weekPrice, R.anim.shake);
				return;
			}
			json.put("free_time", toInt(et_freeTime.getText().toString()));
			json.put("day_limit", toInt(et_total.getText().toString()));
			json.put("week_price", toInt(et_weekPrice.getText().toString()));
			
			int childCount=container_time.getChildCount();
			JSONArray hours = new JSONArray();
	        for (int i = 0; i < childCount; i++){
	        	View child=container_time.getChildAt(i);
	        	EditText item_0=(EditText)child.findViewById(R.id.item_0);
				EditText item_1=(EditText)child.findViewById(R.id.item_1);
				EditText item_2=(EditText)child.findViewById(R.id.item_2);
				EditText item_3=(EditText)child.findViewById(R.id.item_3);
	        	if(isEmpty(item_0.getText().toString())){
	        		showToast(R.string.toast_data_uncompleted);
	        		doAnimation(context, item_0, R.anim.shake);
	        		return;
	        	}
	        	if(isEmpty(item_1.getText().toString())){
	        		showToast(R.string.toast_data_uncompleted);
	        		doAnimation(context, item_1, R.anim.shake);
	        		return;
	        	}
	        	if(isEmpty(item_2.getText().toString())){
	        		showToast(R.string.toast_data_uncompleted);
	        		doAnimation(context, item_2, R.anim.shake);
	        		return;
	        	}
	        	if(isEmpty(item_3.getText().toString())){
	        		showToast(R.string.toast_data_uncompleted);
	        		doAnimation(context, item_3, R.anim.shake);
	        		return;
	        	}
	        	if(i==childCount-1){
	        		if(!et_total.getText().toString().equals(item_1.getText().toString())){
	        			showToast("时间必须一致");
	        			doAnimation(context, et_total, R.anim.shake);
	        			doAnimation(context, item_1, R.anim.shake);
	        			return;
	        		}
	        	}
	        	
	        	if(toInt(item_1.getText().toString())<=toInt(item_0.getText().toString())){
	        		showToast("结束时间必须大于开始时间");
        			doAnimation(context, item_0, R.anim.shake);
        			doAnimation(context, item_1, R.anim.shake);
        			return;
	        	}
	        	
	            JSONObject item = new JSONObject();
	            item.put("start", toInt(item_0.getText().toString()));
	            item.put("end", toInt(item_1.getText().toString()));
	            item.put("price", toInt(item_2.getText().toString()));
	            item.put("unit", toInt(item_3.getText().toString()));
	            hours.put(item);
	        }
	        json.put("hours", hours);
			
	        childCount=container_day.getChildCount();
	        JSONArray days = new JSONArray();
	        for (int i = 0; i < childCount; i++){
	        	View child=container_day.getChildAt(i);
	        	EditText item_0=(EditText)child.findViewById(R.id.item_0);
				EditText item_1=(EditText)child.findViewById(R.id.item_1);
				if(isEmpty(item_0.getText().toString())){
	        		showToast(R.string.toast_data_uncompleted);
	        		doAnimation(context, item_0, R.anim.shake);
	        		return;
	        	}
				if(isEmpty(item_1.getText().toString())){
					showToast(R.string.toast_data_uncompleted);
					doAnimation(context, item_1, R.anim.shake);
					return;
				}
				if(i>0){
					View lastChild=container_day.getChildAt(i-1);
					EditText last_item_0=(EditText)lastChild.findViewById(R.id.item_0);
					if(toInt(item_0.getText().toString())<toInt(last_item_0.getText().toString())){
		        		showToast("上限不能小于上一条记录");
	        			doAnimation(context, item_0, R.anim.shake);
	        			doAnimation(context, last_item_0, R.anim.shake);
	        			return;
		        	}
				}
	            JSONObject item = new JSONObject();
	            item.put("index", i+1);
	            item.put("day_limit", toInt(item_0.getText().toString()));
	            item.put("day_price", toInt(item_1.getText().toString()));
	            days.put(item);
	        }
        
			json.put("days", days);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.i("tag", json.toString());
        if(isEmpty(json.toString())){
    		showToast(R.string.toast_data_uncompleted);
    		return;
    	}
		Commands.doCommandSetPolicy(context, App.user, json.toString(), new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				Log.i("tag", response.toString());
				if(isSuccess(response)){
					showToast(R.string.operate_success);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add_time:
			addTime();
			break;
		case R.id.btn_add_day:
			addDay();
			break;

		default:
			break;
		}
	}
	
	private int timeCount=0;
	private void addTime(){
		int count=5;
		if(timeCount<count){
			View child=LayoutInflater.from(context).inflate(R.layout.item_add_time, null);
			child.setTag("time_"+timeCount);
			EditText item_0=(EditText)child.findViewById(R.id.item_0);
			EditText item_1=(EditText)child.findViewById(R.id.item_1);
//			EditText item_2=(EditText)child.findViewById(R.id.item_2);
//			EditText item_3=(EditText)child.findViewById(R.id.item_3);
			int total=Integer.parseInt(et_total.getText().toString());
			int childCount=container_time.getChildCount();
			if(childCount>0){
				EditText lastItem=(EditText)container_time.getChildAt(childCount-1).findViewById(R.id.item_1);
				int lastItemV=-1;
				if(lastItem!=null&&lastItem.getText().toString().length()>0){
					lastItemV=Integer.parseInt(lastItem.getText().toString());
				}
				
				if(lastItemV>=total){
					showToast("不能新增时间段了，已经达到"+total+"小时的上限时间了");
					return;
				}
			}
			if(timeCount==0){
				item_0.setEnabled(false);
				item_0.setText("0");
			}else{
				View v=container_time.getChildAt(timeCount-1);//上一个
				if(v!=null){
					if(hasEmptyTime(v)){
						showToast("请完善上一条记录");
						doAnimation(context, v, R.anim.shake);
						return;
					}
					
					if(timeCount>=2){
						final EditText last_item_2=(EditText)container_time.getChildAt(timeCount-2).findViewById(R.id.item_1);
						final EditText last_item_1=(EditText)container_time.getChildAt(timeCount-1).findViewById(R.id.item_1);
						if(Integer.parseInt(last_item_1.getText().toString())<=Integer.parseInt(last_item_2.getText().toString())){
							showToast("上限必须大于上一条记录");
							doAnimation(context, last_item_1, R.anim.shake);
							doAnimation(context, last_item_2, R.anim.shake);
							return;
						}
					}
					
					EditText last_item_1=(EditText)v.findViewById(R.id.item_1);
					item_0.setEnabled(false);
					item_0.setText(last_item_1.getText());
				}
				item_1.setText(et_total.getText());
			}
			
			container_time.addView(child);
			item_1.requestFocus();//焦点默认在单价上
			timeCount++;
		}else{
			showToast("最多可以加"+count+"个时间段");
		}
	}
	
	private boolean hasEmptyTime(View v){
		EditText last_item_0=(EditText)v.findViewById(R.id.item_0);
		EditText last_item_1=(EditText)v.findViewById(R.id.item_1);
		EditText last_item_2=(EditText)v.findViewById(R.id.item_2);
		EditText last_item_3=(EditText)v.findViewById(R.id.item_3);
		if (last_item_0.getText().toString().length() == 0 
				|| last_item_1.getText().toString().length() == 0
				|| last_item_2.getText().toString().length() == 0
				|| last_item_3.getText().toString().length() == 0) {
			return true;
		}
		return false;
	}
	
	private boolean hasEmptyDay(View v){
		EditText last_item_0=(EditText)v.findViewById(R.id.item_0);
		if (last_item_0.getText().toString().length() == 0 ){
			return true;
		}
		return false;
	}
	
	private int dayCount=0;
	private void addDay(){
		int count=5;
		if(dayCount<count){
			int size=container_time.getChildCount();
			for(int i=0;i<size;i++){
				if(hasEmptyTime(container_time.getChildAt(i))){
					showToast("请完善时间段数据");
					doAnimation(context, container_time.getChildAt(i), R.anim.shake);
					return;
				}
			}
			View child=LayoutInflater.from(context).inflate(R.layout.item_add_day, null);
			child.setTag("day_"+dayCount);
			TextView tv_day=(TextView)child.findViewById(R.id.tv_day);
			final EditText item_0=(EditText)child.findViewById(R.id.item_0);
			final EditText item_1=(EditText)child.findViewById(R.id.item_1);
			if(dayCount==0){
				item_0.setEnabled(false);
				item_0.setText(et_total.getText());
				int day_money=calDayMoney(Integer.parseInt(et_total.getText().toString()));
				item_1.setEnabled(false);
				item_1.setText(String.valueOf(day_money));
			}else{
				View v=container_day.getChildAt(dayCount-1);//上一个
				if(hasEmptyDay(v)){
					showToast("请完善上一条记录");
					doAnimation(context, v, R.anim.shake);
					return;
				}
				
				if(dayCount>=2){
					final EditText last_item_2=(EditText)container_day.getChildAt(dayCount-2).findViewById(R.id.item_0);
					final EditText last_item_1=(EditText)container_day.getChildAt(dayCount-1).findViewById(R.id.item_0);
					if(Integer.parseInt(last_item_1.getText().toString())<Integer.parseInt(last_item_2.getText().toString())){
						showToast("上限不能小于上一条记录");
						doAnimation(context, last_item_1, R.anim.shake);
						doAnimation(context, last_item_2, R.anim.shake);
						return;
					}
				}
				
				item_1.setEnabled(false);
				item_0.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						if(s.length()>0){
							int day_money=calDayMoney(Integer.parseInt(s.toString()));
							item_1.setText(day_money+"");
						}else{
							item_1.setText("");
						}
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
			
			container_day.addView(child);
			item_0.requestFocus();//焦点默认在小时上
			tv_day.setText("满"+(dayCount+1)+"天:上限");
			dayCount++;
			btn_add_day.setText("满"+(dayCount+1)+"天");
		}else{
			showToast("最多可以加"+count+"个日期");
		}
	}
	
	/**
	 * 
	 * @param lastTime 最后一个上限时间
	 * @return
	 */
	private int calDayMoney(int lastTime){
		int day_money=0;
		int size=container_time.getChildCount();//timeCount
		for(int i=0;i<size;i++){
			View view=container_time.getChildAt(i);
			EditText et_0=(EditText)view.findViewById(R.id.item_0);
			EditText et_1=(EditText)view.findViewById(R.id.item_1);
			EditText et_2=(EditText)view.findViewById(R.id.item_2);
			EditText et_3=(EditText)view.findViewById(R.id.item_3);
			int unit=Integer.parseInt(et_3.getText().toString().length()==0?"0":et_3.getText().toString());
			String text_0=et_0.getText().toString().length()==0?"0":et_0.getText().toString();
			String text_1=et_1.getText().toString().length()==0?"0":et_1.getText().toString();
			String text_2=et_2.getText().toString().length()==0?"0":et_2.getText().toString();
			if(i==size-1){
				day_money+=(lastTime-Integer.parseInt(text_0))*Integer.parseInt(text_2)*60/unit;
			}else{
				day_money+=(Integer.parseInt(text_1)-Integer.parseInt(text_0))*Integer.parseInt(text_2)*60/unit;
			}
		}
		return day_money;
	}
	
}
