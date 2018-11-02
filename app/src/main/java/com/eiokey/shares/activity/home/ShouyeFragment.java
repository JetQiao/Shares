package com.eiokey.shares.activity.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eiokey.shares.R;

/**
 * 首页
 */
public class ShouyeFragment extends Fragment
{
    private View shouyeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        shouyeLayout = inflater.inflate(R.layout.fragment_shouye, container, false);
        return shouyeLayout;


    }

}
