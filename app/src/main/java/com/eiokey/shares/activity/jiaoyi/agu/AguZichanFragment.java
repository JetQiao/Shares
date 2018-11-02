package com.eiokey.shares.activity.jiaoyi.agu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eiokey.shares.R;
import com.eiokey.shares.activity.jiaoyi.AguFragment;
import com.eiokey.shares.activity.jiaoyi.GangmeiguFragment;
import com.eiokey.shares.activity.jiaoyi.MoniFragment;

public class AguZichanFragment extends Fragment implements View.OnClickListener
{
    private View jiaoyiLayout;

    /* 用于展示三个页面的Fragment*/
    private AguFragment aguFragment;//A股
    private GangmeiguFragment gangmeiguFragment;//港美股
    private MoniFragment moniFragment; //模拟

    /* 在Tab布局上显示文字的控件*/
    private TextView tv_jiaoyi_agu, tv_jiaoyi_gangmeigu, tv_jiaoyi_moni;

    /* 用于对Fragment进行管理*/
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        jiaoyiLayout = inflater.inflate(R.layout.fragment_jiaoyi, container, false);
        initViews(); // 初始化布局元素
        fragmentManager = getFragmentManager();
        setTabSelection(0); // 第一次启动时选中第0个tab
        return jiaoyiLayout;
    }

    private void initViews()
    {
        /*三个界面布局*/
        View rl_jiaoyi_agu = jiaoyiLayout.findViewById(R.id.rl_jiaoyi_agu);
        View rl_jiaoyi_gangmeigu = jiaoyiLayout.findViewById(R.id.rl_jiaoyi_gangmeigu);
        View rl_jiaoyi_moni = jiaoyiLayout.findViewById(R.id.rl_jiaoyi_moni);

        tv_jiaoyi_agu = jiaoyiLayout.findViewById(R.id.tv_jiaoyi_agu);
        tv_jiaoyi_gangmeigu = jiaoyiLayout.findViewById(R.id.tv_jiaoyi_gangmeigu);
        tv_jiaoyi_moni = jiaoyiLayout.findViewById(R.id.tv_jiaoyi_moni);

        rl_jiaoyi_agu.setOnClickListener(this);
        rl_jiaoyi_gangmeigu.setOnClickListener(this);
        rl_jiaoyi_moni.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rl_jiaoyi_agu: //点击A股
                setTabSelection(0);
                break;
            case R.id.rl_jiaoyi_gangmeigu://点击港美股
                setTabSelection(1);
                break;
            case R.id.rl_jiaoyi_moni://点击模拟
                setTabSelection(2);
                break;

            default:
                break;
        }
    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0A股，1港美股，2模拟
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
                tv_jiaoyi_agu.setTextColor(Color.WHITE);
                if (aguFragment == null)
                {
                    aguFragment = new AguFragment(); // 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content_jiaoyi, aguFragment);
                } else
                {
                    transaction.show(aguFragment); // 如果Fragment不为空，则直接将它显示出来
                }
                break;
            case 1://点击行情tab,改变图标文字颜色
                tv_jiaoyi_gangmeigu.setTextColor(Color.WHITE);
                if (gangmeiguFragment == null)
                {
                    gangmeiguFragment = new GangmeiguFragment();// 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content_jiaoyi, gangmeiguFragment);
                } else
                {
                    transaction.show(gangmeiguFragment);// 如果Fragment不为空，则直接将它显示出来
                }
                break;
            case 2://点击自选tab,改变图标文字颜色
                tv_jiaoyi_moni.setTextColor(Color.WHITE);
                if (moniFragment == null)
                {
                    moniFragment = new MoniFragment();// 如果Fragment为空，则创建一个并添加到界面上
                    transaction.add(R.id.content_jiaoyi, moniFragment);
                } else
                {
                    transaction.show(moniFragment);// 如果Fragment不为空，则直接将它显示出来
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
        tv_jiaoyi_agu.setTextColor(Color.parseColor("#A8A9AD"));

        tv_jiaoyi_gangmeigu.setTextColor(Color.parseColor("#A8A9AD"));

        tv_jiaoyi_moni.setTextColor(Color.parseColor("#A8A9AD"));

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction)
    {
        if (aguFragment != null)
        {
            transaction.hide(aguFragment);
        }
        if (gangmeiguFragment != null)
        {
            transaction.hide(gangmeiguFragment);
        }
        if (moniFragment != null)
        {
            transaction.hide(moniFragment);
        }
    }
}
