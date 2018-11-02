package com.eiokey.shares.activity.jiaoyi;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eiokey.shares.R;
import com.eiokey.shares.activity.home.ShouyeFragment;

import libs.eiokey.utils.LogUtil;

public class AguFragment extends Fragment
{
    private View jiaoyiAguLayout;

    private LinearLayout ll_jiaoyi_agu_zichan;

    /* 用于对Fragment进行管理*/
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        jiaoyiAguLayout = inflater.inflate(R.layout.fragment_jiaoyi_agu, container, false);

        initViews(); // 初始化布局元素
        fragmentManager = getFragmentManager();
        //                setTabSelection(3); // 第一次启动时选中第0个tab

        return jiaoyiAguLayout;
    }

    private void initViews()
    {
        ll_jiaoyi_agu_zichan = jiaoyiAguLayout.findViewById(R.id.ll_jiaoyi_agu_zichan);

        ll_jiaoyi_agu_zichan.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v)
            {


                LogUtil.myLog().d("-----------");
                //                Fragment jiaoyi = new ShouyeFragment();
                //
                //                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                //                transaction.replace(R.layout.fragment_jiaoyi, jiaoyi).commit();
                //                transaction.add(R.id.content_jiaoyi_agu, jiaoyi).commit();


                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_jiaoyi, new ShouyeFragment()).commit();//将当前fragment加入到返回栈中

                //                FragmentManager fm = getChildFragmentManager();
                //
                //                fm.beginTransaction().replace(R.layout.fragment_jiaoyi, new JiaoyiFragment()).commit();//替换为TwoFragment
            }
        });
    }

}
