package com.eiokey.shares.activity.home;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.eiokey.shares.R;
import com.eiokey.shares.utils.TitleBarActivity;


/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 */
public class HomeActivity extends TitleBarActivity implements View.OnClickListener
{
    /* 用于展示五个页面的Fragment*/
    private ShouyeFragment shouyeFragment;//首页
    private HangqingFragment hangqingFragment;//行情
    private ZixuanFragment zixuanFragment; //自选
    private JiaoyiFragment jiaoyiFragment; //交易
    private ZixunFragment zixunFragment;//资讯

    /* 在Tab布局上显示图标的控件*/
    private ImageView img_home_shouye, img_home_hangqing, img_home_zixuan, img_home_jiaoyi, img_home_zixun;

    /* 在Tab布局上显示文字的控件*/
    private TextView tv_home_shouye, tv_home_hangqing, tv_home_zixuan, tv_home_jiaoyi, tv_home_zixun;

    /* 用于对Fragment进行管理*/
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        initViews(); // 初始化布局元素
        fragmentManager = getFragmentManager();
        setTabSelection(0); // 第一次启动时选中第0个tab
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews()
    {
        /*五个界面布局*/
        View rl_home_shouye = findViewById(R.id.rl_home_shouye);
        View rl_home_hangqing = findViewById(R.id.rl_home_hangqing);
        View rl_home_zixuan = findViewById(R.id.rl_home_zixuan);
        View rl_home_jiaoyi = findViewById(R.id.rl_home_jiaoyi);
        View rl_home_zixun = findViewById(R.id.rl_home_zixun);

        img_home_shouye = findViewById(R.id.img_home_shouye);
        img_home_hangqing = findViewById(R.id.img_home_hangqing);
        img_home_zixuan = findViewById(R.id.img_home_zixuan);
        img_home_jiaoyi = findViewById(R.id.img_home_jiaoyi);
        img_home_zixun = findViewById(R.id.img_home_zixun);


        tv_home_shouye = findViewById(R.id.tv_home_shouye);
        tv_home_hangqing = findViewById(R.id.tv_home_hangqing);
        tv_home_zixuan = findViewById(R.id.tv_home_zixuan);
        tv_home_jiaoyi = findViewById(R.id.tv_home_jiaoyi);
        tv_home_zixun = findViewById(R.id.tv_home_zixun);

        rl_home_shouye.setOnClickListener(this);
        rl_home_hangqing.setOnClickListener(this);
        rl_home_zixuan.setOnClickListener(this);
        rl_home_jiaoyi.setOnClickListener(this);
        rl_home_zixun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rl_home_shouye: //点击首页
                setTabSelection(0);
                break;
            case R.id.rl_home_hangqing://点击行情
                setTabSelection(1);
                break;
            case R.id.rl_home_zixuan://点击自选
                setTabSelection(2);
                break;
            case R.id.rl_home_jiaoyi://点击交易
                setTabSelection(3);
                break;
            case R.id.rl_home_zixun://点击资讯
                setTabSelection(4);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0首页，1行情，2自选，3交易，4资讯
     */
    private void setTabSelection(int index)
    {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();  // 开启一个Fragment事务
        hideFragments(transaction);      // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        switch (index)
        {
            case 0://点击首页tab，改变图标文字颜色
                img_home_shouye.setImageResource(R.drawable.search_select_img);
                tv_home_shouye.setTextColor(Color.RED);
                if (shouyeFragment == null)
                {
                    shouyeFragment = new ShouyeFragment(); // 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content, shouyeFragment);
                } else
                {
                    transaction.show(shouyeFragment); // 如果Fragment不为空，则直接将它显示出来
                }
                break;
            case 1://点击行情tab,改变图标文字颜色
                img_home_hangqing.setImageResource(R.drawable.hangqing_select_img);
                tv_home_hangqing.setTextColor(Color.RED);
                if (hangqingFragment == null)
                {
                    hangqingFragment = new HangqingFragment();// 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content, hangqingFragment);
                } else
                {
                    transaction.show(hangqingFragment);// 如果Fragment不为空，则直接将它显示出来
                }
                break;
            case 2://点击自选tab,改变图标文字颜色
                img_home_zixuan.setImageResource(R.drawable.zixuan_select_img);
                tv_home_zixuan.setTextColor(Color.RED);
                if (zixuanFragment == null)
                {
                    zixuanFragment = new ZixuanFragment();// 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content, zixuanFragment);
                } else
                {
                    transaction.show(zixuanFragment);// 如果Fragment不为空，则直接将它显示出来
                }
                break;
            case 3://点击交易tab,改变图标文字颜色
                img_home_jiaoyi.setImageResource(R.drawable.trade_select_img);
                tv_home_jiaoyi.setTextColor(Color.RED);
                if (jiaoyiFragment == null)
                {
                    jiaoyiFragment = new JiaoyiFragment();// 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content, jiaoyiFragment);
                } else
                {
                    transaction.show(jiaoyiFragment);// 如果Fragment不为空，则直接将它显示出来
                }
                break;
            case 4://点击资讯tab,改变图标文字颜色
                img_home_zixun.setImageResource(R.drawable.news_select_img);
                tv_home_zixun.setTextColor(Color.RED);
                if (zixunFragment == null)
                {
                    zixunFragment = new ZixunFragment();// 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content, zixunFragment);
                } else
                {
                    transaction.show(zixunFragment);// 如果Fragment不为空，则直接将它显示出来
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection()
    {
        img_home_shouye.setImageResource(R.drawable.search_normal_img);
        tv_home_shouye.setTextColor(Color.parseColor("#FFFFFF"));

        img_home_hangqing.setImageResource(R.drawable.hangqing_normal_img);
        tv_home_hangqing.setTextColor(Color.parseColor("#FFFFFF"));

        img_home_zixuan.setImageResource(R.drawable.zixuan_normal_img);
        tv_home_zixuan.setTextColor(Color.parseColor("#FFFFFF"));

        img_home_jiaoyi.setImageResource(R.drawable.trade_normal_img);
        tv_home_jiaoyi.setTextColor(Color.parseColor("#FFFFFF"));

        img_home_zixun.setImageResource(R.drawable.news_normal_img);
        tv_home_zixun.setTextColor(Color.parseColor("#FFFFFF"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction)
    {
        if (shouyeFragment != null)
        {
            transaction.hide(shouyeFragment);
        }
        if (hangqingFragment != null)
        {
            transaction.hide(hangqingFragment);
        }
        if (zixuanFragment != null)
        {
            transaction.hide(zixuanFragment);
        }
        if (jiaoyiFragment != null)
        {
            transaction.hide(jiaoyiFragment);
        }
        if (zixunFragment != null)
        {
            transaction.hide(zixunFragment);
        }
    }
}
