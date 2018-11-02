package com.eiokey.shares.activity.jiaoyi.agu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eiokey.shares.R;

public class AguBuyFragment extends Fragment
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
//        setTabSelection(0); // 第一次启动时选中第0个tab

        return jiaoyiAguLayout;
    }

    private void initViews()
    {
        ll_jiaoyi_agu_zichan = jiaoyiAguLayout.findViewById(R.id.ll_jiaoyi_agu_zichan);
    }

}
