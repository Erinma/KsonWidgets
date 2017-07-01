package com.ksonwidgets.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ksonwidgets.R;
import com.ksonwidgets.widgets.viewpagerindicator.KsonViewpagerIndicator;
import com.ksonwidgets.widgets.viewpagerindicator.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class KsonViewPagerIndicatorActivity extends AppCompatActivity {
    private List<Fragment> mTabContents = new ArrayList<>();
    private KsonViewpagerIndicator ksonViewpagerIndicator;
    private ViewPager viewPager;
    private List<String> titles;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kson_view_pager_indicator);
        setTitle("viewpager指示器");

        ksonViewpagerIndicator = (KsonViewpagerIndicator) findViewById(R.id.viewpager_indicator);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        titles = new ArrayList<>();

        titles.add("推荐");
        titles.add("社会");
        titles.add("生活");
        titles.add("时事");
        titles.add("娱乐");
        titles.add("视频");
        titles.add("直播");
        titles.add("体育");
        titles.add("程序员");
        titles.add("头条");
        titles.add("摄影");

        initDatas();
        ksonViewpagerIndicator.setTableClickListener(new KsonViewpagerIndicator.TableOnclickListener() {
            @Override
            public void onTableOnclicker(int position) {
                Toast.makeText(KsonViewPagerIndicatorActivity.this,"title:"+titles.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        ksonViewpagerIndicator.setTableTitles(titles).bindViewPager(viewPager);
        viewPager.setAdapter(mAdapter);
    }


    private void initDatas()
    {

        for (String data : titles)
        {
            MyFragment fragment = MyFragment.newInstance(data);
            mTabContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position)
            {
                return mTabContents.get(position);
            }
        };
    }
}
