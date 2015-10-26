package com.yirui.park.app;

import com.base.model.KeyValue;
import com.yirui.park.fragment.MerchantManagementFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 商户管理
 * @author Administrator
 *
 */
public class MerchantManagementActivity extends BaseActivity{

	private Context context;
	
	private ViewPager viewPager;
	private FragmentPagerAdapter pagerAdapter;
	private Fragment[] fragments = new Fragment[1];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_merchant_management);
		context=this;
		initActionBar("商户管理", "添加", null);
		initViews();
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		fragments[0]=MerchantManagementFragment.newInstance(new KeyValue("3","已支付"));
		
		pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return fragments.length;
			}

			@Override
			public Fragment getItem(int position) {
				return fragments[position];
			}

		};

		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);
		
	}

	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		
	}
	@Override	
	public void doRightButtonClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(context,MerchantDetailActivity.class);
		startActivity(intent);
	}
}
