package com.eiokey.shares.activity.home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eiokey.shares.R;

public class HangqingFragment extends Fragment
{
    private View hangqingLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        hangqingLayout = inflater.inflate(R.layout.fragment_hangqing, container, false);
        return hangqingLayout;


    }

}
