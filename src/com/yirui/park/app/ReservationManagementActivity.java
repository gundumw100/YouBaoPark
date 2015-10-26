package com.yirui.park.app;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;

import com.base.model.KeyValue;
import com.widget.view.SimpleViewPagerIndicator;
import com.widget.view.TabsLayout;
import com.yirui.park.fragment.ReservationManagementFragment;

/**
 * 预定管理
 * @author Administrator
 *
 */
public class ReservationManagementActivity extends BaseActivity{

	private Context context;
	
	private String[] titles = new String[] { /*"待付费",*/ "已付费", "退单", "已入场" };
//	private SimpleViewPagerIndicator indicator;
//	private TabsLayout tabsLayout;
	private ViewPager viewPager;
	private FragmentPagerAdapter pagerAdapter;
	private Fragment[] fragments = new Fragment[titles.length];
	private int curPosition=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_management);
		context=this;
		initActionBar("预定管理", null, null);
		initViews();
	}

	
	private void initSimpleViewPagerIndicator(SimpleViewPagerIndicator indicator){
		indicator.setTitles(titles);
		indicator.setViewPager(viewPager);
		indicator.setCurrentItem(curPosition);
		indicator.setOnPageChangeListener(new SimpleViewPagerIndicator.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				curPosition=position;
			}
		});
		
		indicator.setOnItemClickListener(new SimpleViewPagerIndicator.OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private void initTabsLayout(TabsLayout tabsLayout){
		tabsLayout.setViewPager(viewPager);
		tabsLayout.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		// 底部游标颜色
		tabsLayout.setIndicatorColor(App.res.getColor(R.color.secondary));
		// tab的分割线颜色
		tabsLayout.setDividerColor(Color.TRANSPARENT);
		// tab背景
		tabsLayout.setBackgroundColor(App.res.getColor(R.color.primary));
		// tab底线高度
		tabsLayout.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				1, getResources().getDisplayMetrics()));
		// 游标高度
		tabsLayout.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				5, getResources().getDisplayMetrics()));
		// 选中的文字颜色
		tabsLayout.setSelectedTextColor(Color.WHITE);
		// 正常文字颜色
		tabsLayout.setTextColor(Color.BLACK);
//		tabsLayout.setTabPaddingLeftRight(128);
	}
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		//预订状态；1：未确认；2：待支付；3：已支付；4：已使用；5；用户退订；6：停车场退订；7：退款中；8；已退款
//		fragments[0]=ReservationManagementFragment.newInstance(new KeyValue("2","待支付"));
		fragments[0]=ReservationManagementFragment.newInstance(new KeyValue("3","已支付"));
		fragments[1]=ReservationManagementFragment.newInstance(new KeyValue("6","退单"));
		fragments[2]=ReservationManagementFragment.newInstance(new KeyValue("4","已入场"));
		
		pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public CharSequence getPageTitle(int position) {
				return titles[position];
			}
			
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
		
		
		View indicator = findViewById(R.id.indicator);
		if(indicator instanceof SimpleViewPagerIndicator){
			initSimpleViewPagerIndicator((SimpleViewPagerIndicator)indicator);
		}else if(indicator instanceof TabsLayout){
			initTabsLayout((TabsLayout)indicator);
		}
		
	}

	@Override
	public void updateViews(Object obj) {
		// TODO Auto-generated method stub
		
	}

}
