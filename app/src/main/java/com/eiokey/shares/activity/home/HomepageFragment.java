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
public class HomepageFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View contactsLayout = inflater.inflate(R.layout.contacts_layout, container, false);
        return contactsLayout;
    }

}
