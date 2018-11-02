package com.eiokey.shares.activity.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eiokey.shares.R;

public class ZixuanFragment extends Fragment
{
private View zixuanLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        zixuanLayout = inflater.inflate(R.layout.fragment_shouye, container, false);
        return zixuanLayout;
    }




}
