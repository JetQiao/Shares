package com.eiokey.shares.activity.jiaoyi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eiokey.shares.R;

public class MoniFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View newsLayout = inflater.inflate(R.layout.activity_home, container, false);
        return newsLayout;


    }

}
