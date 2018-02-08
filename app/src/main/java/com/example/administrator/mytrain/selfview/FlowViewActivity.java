package com.example.administrator.mytrain.selfview;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.administrator.mytrain.BaseActivity;
import com.example.administrator.mytrain.R;
import com.example.administrator.mytrain.fragment.BaseFragment;
import com.example.administrator.mytrain.fragment.FlowViewFragment;
import com.example.administrator.mytrain.uitls.FragmentUtil;
import com.example.administrator.mytrain.uitls.ToastUtil;
import com.example.administrator.mytrain.view.ProcessImgView;

import java.util.ArrayList;
import java.util.List;

public class FlowViewActivity extends BaseActivity {
    private ProcessImgView processImg1,processImg2,processImg3;
    private ViewPager viewPager;
    List<BaseFragment> fragments,listFragments;
    private FragmentManager fragmentManager;
    FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_flow_view);
        String title=getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title)){
            setTitle(title);
        }
        fragments=new ArrayList<>();
        listFragments=new ArrayList<>();
        initViews();
        setProcessImg1();
        setProcessImg2();
//        setProcessImg3();
        addViewPagerFragment();
        addRadioButtonFragment();
    }

    private void addRadioButtonFragment() {
        fragmentManager = getSupportFragmentManager();
        final FlowViewFragment flowViewFragment1=new FlowViewFragment();
        FlowViewFragment flowViewFragment2=new FlowViewFragment();
        FlowViewFragment flowViewFragment3=new FlowViewFragment();
        flowViewFragment1.setNextClick("1",new Runnable() {
            @Override
            public void run() {
                processImg1.setProcess(3,2);
                FragmentUtil.showFragment(fragmentManager, listFragments, listFragments.get(1), R.id.containerId);
            }
        });

        flowViewFragment2.setNextClick("2",new Runnable() {
            @Override
            public void run() {
                processImg1.setProcess(3,3);
                FragmentUtil.showFragment(fragmentManager, listFragments, listFragments.get(2), R.id.containerId);
            }
        });
        flowViewFragment3.setNextClick("3",new Runnable() {
            @Override
            public void run() {
                ToastUtil.show("完成");
            }
        });
        listFragments.add(flowViewFragment1);
        listFragments.add(flowViewFragment2);
        listFragments.add(flowViewFragment3);
        FragmentUtil.showFragment(fragmentManager, listFragments, flowViewFragment1, R.id.containerId);
    }

    private void addViewPagerFragment() {
        for (int i=0;i<5;i++){
            FlowViewFragment flowViewFragment=new FlowViewFragment();
            flowViewFragment.num=String.valueOf(i+1);
            flowViewFragment.setNextClick(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(processImg2.getCurrentProcess());
                }
            });
            fragments.add(flowViewFragment);
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                processImg2.setProcess(5,position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews(){
        processImg1 = (ProcessImgView) findViewById(R.id.p1);
        processImg2 = (ProcessImgView) findViewById(R.id.p2);
        processImg3 = (ProcessImgView) findViewById(R.id.p3);
        viewPager = ((ViewPager) findViewById(R.id.viewpager));
        framelayout = ((FrameLayout) findViewById(R.id.containerId));
    }
    private void setProcessImg1(){
        processImg1.setColor(Color.parseColor("#FFFF8C56"));
        processImg1.setProcess(3,1);
        processImg1.setTitle(1,"title1");
        processImg1.setTitle(2,"title2");
        processImg1.setTitle(3,"title3");
        processImg1.setClick(1, new ProcessImgView.Click() {
            @Override
            public void click() {
                Toast.makeText(mContext, "点击第1项", Toast.LENGTH_SHORT).show();
            }
        });
        processImg1.setClick(2, new ProcessImgView.Click() {
            @Override
            public void click() {
                Toast.makeText(mContext, "点击第2项", Toast.LENGTH_SHORT).show();
            }
        });
        processImg1.setClick(3, new ProcessImgView.Click() {
            @Override
            public void click() {
                Toast.makeText(mContext, "点击第3项", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProcessImg2(){
        processImg2.setColor(Color.parseColor("#FFFF8C56"));
        processImg2.setProcess(5,1);
        processImg2.setTitle(1,"title1");
        processImg2.setTitle(2,"title2");
        processImg2.setTitle(3,"title3");
        processImg2.setTitle(4,"title4");
        processImg2.setTitle(5,"title5");
        processImg2.setTitle(6,"title6");
    }

    private void setProcessImg3(){
        processImg3.setColor(Color.parseColor("#809bff"));
        processImg3.setProcess(4,2);
        processImg3.setTitle(1,"标题1");
        processImg3.setTitle(2,"标题2");
        processImg3.setTitle(3,"标题3");
        processImg3.setTitle(4,"标题4");

    }
}
