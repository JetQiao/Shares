package com.eiokey.shares.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.eiokey.shares.R;
import com.eiokey.shares.utils.UIUtil;
import com.eiokey.shares.views.ListViewForScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eiokey.shares.R.id.tv_lv_loss;
import static com.eiokey.shares.R.id.tv_lv_money_all;

public class MainActivity extends Activity
{

    ListViewForScrollView lv_content;
    SimpleAdapter adapter;
    ScrollView scrol_main;
    private LinearLayout ll_main_money, ll_main_order;
    private AlertDialog alertDialog;
    private Context mContext;
    private TextView tv_main_total, tv_main_lost, tv_main_money_total, tv_main_use, tv_main_have;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        getViews();
        initViews();//初始化

        adapter = new SimpleAdapter(this, getDataList(), R.layout.item_content_list, new String[]{"tv_lv_name", "tv_lv_money_all", "tv_lv_loss", "tv_lv_loss_proint", "tv_lv_have", "tv_lv_use", "tv_lv_cost", "tv_lv_now"}, new int[]{R.id.tv_lv_name, tv_lv_money_all, tv_lv_loss, R.id.tv_lv_loss_proint, R.id.tv_lv_have, R.id.tv_lv_use, R.id.tv_lv_cost, R.id.tv_lv_now})
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView tv_lv_name = (TextView) view.findViewById(R.id.tv_lv_name);
                TextView tv_lv_money_all = (TextView) view.findViewById(R.id.tv_lv_money_all);
                TextView tv_lv_loss = (TextView) view.findViewById(R.id.tv_lv_loss);
                TextView tv_lv_loss_proint = (TextView) view.findViewById(R.id.tv_lv_loss_proint);
                TextView tv_lv_have = (TextView) view.findViewById(R.id.tv_lv_have);
                TextView tv_lv_use = (TextView) view.findViewById(R.id.tv_lv_use);
                TextView tv_lv_cost = (TextView) view.findViewById(R.id.tv_lv_cost);
                TextView tv_lv_now = (TextView) view.findViewById(R.id.tv_lv_now);
                Boolean isFocus = (Boolean) getDataList().get(position).get("isfocus");
                if (isFocus)
                {
                    tv_lv_name.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_money_all.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_loss.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_loss_proint.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_have.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_use.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_cost.setTextColor(getResources().getColor(R.color.red));
                    tv_lv_now.setTextColor(getResources().getColor(R.color.red));
                } else
                {
                    tv_lv_name.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_money_all.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_loss.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_loss_proint.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_have.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_use.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_cost.setTextColor(getResources().getColor(R.color.text_weather_trend));
                    tv_lv_now.setTextColor(getResources().getColor(R.color.text_weather_trend));
                }
                return view;

            }
        };
        lv_content.setAdapter(adapter);
        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                //                HashMap<String, String> map = (HashMap<String, String>) lv_content.getItemAtPosition(position);
                //                String title = map.get("tv_lv_name");
                //                String content = map.get("tv_lv_money_all");
                //                Toast.makeText(getApplicationContext(), "你选择了第" + position + "个Item，itemTitle的值是：" + title + "itemContent的值是:" + content, Toast.LENGTH_SHORT).show();
                UpdateList(position);
                return false;

            }
        });
    }


    public void UpdateList(int selectedItem)
    {
        //        ListAdapter la = lv_content.getAdapter();
        int itemNum = lv_content.getCount();
        //        for(int i=0; i<itemNum; i++)
        //        {
        HashMap<String, Object> map = (HashMap<String, Object>) adapter.getItem(selectedItem);
        //            if ( i == selectedItem)
        map.put("tv_lv_name", "测试");
        //            else
        //                map.put("ItemIcon", R.drawable.image2);
        //        }

        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化
     */
    private void initViews()
    {
        //变更资产的值
        ll_main_money.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDialogEdt();
            }
        });
    }

    private void getViews()
    {
        lv_content = (ListViewForScrollView) findViewById(R.id.lv_content);
        scrol_main = (ScrollView) findViewById(R.id.scrol_main);//Scrollview
        ll_main_money = (LinearLayout) findViewById(R.id.ll_main_money);//变更资产值
        tv_main_total = (TextView) findViewById(R.id.tv_main_total);//总资产
        tv_main_lost = (TextView) findViewById(R.id.tv_main_lost);//总盈亏
        tv_main_money_total = (TextView) findViewById(R.id.tv_main_money_total);//总市值
        tv_main_use = (TextView) findViewById(R.id.tv_main_use);//可取
        tv_main_have = (TextView) findViewById(R.id.tv_main_have);//可用
        ll_main_order = (LinearLayout) findViewById(R.id.ll_main_order);//交易
    }


    private List<? extends Map<String, ?>> getDataList()
    {
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map;

        map = new HashMap<>();
        map.put("tv_lv_name", "易众联");
        map.put("tv_lv_money_all", "97790.000");
        map.put("tv_lv_loss", "-72590.000");
        map.put("tv_lv_loss_proint", "-42.600%");
        map.put("tv_lv_have", "7000");
        map.put("tv_lv_use", "7000");
        map.put("tv_lv_cost", "24.340");
        map.put("tv_lv_now", "13.970");
        map.put("isfocus", false);
        list.add(map);

        map = new HashMap<>();
        map.put("tv_lv_name", "华东重机");
        map.put("tv_lv_money_all", "99900.000");
        map.put("tv_lv_loss", "-98000.000");
        map.put("tv_lv_loss_proint", "-50.480%");
        map.put("tv_lv_have", "10000");
        map.put("tv_lv_use", "10000");
        map.put("tv_lv_cost", "19.790");
        map.put("tv_lv_now", "9.990");
        map.put("isfocus", false);
        list.add(map);

        map = new HashMap<>();
        map.put("tv_lv_name", "中国电建");
        map.put("tv_lv_money_all", "26070.000");
        map.put("tv_lv_loss", "3810.000");
        map.put("tv_lv_loss_proint", "16.860%");
        map.put("tv_lv_have", "3000");
        map.put("tv_lv_use", "3000");
        map.put("tv_lv_cost", "7.420");
        map.put("tv_lv_now", "8.690");
        map.put("isfocus", true);
        list.add(map);


        map = new HashMap<>();
        map.put("tv_lv_name", "沪电股份");
        map.put("tv_lv_money_all", "13980.000");
        map.put("tv_lv_loss", "-4830.000");
        map.put("tv_lv_loss_proint", "-25.680%");
        map.put("tv_lv_have", "3000");
        map.put("tv_lv_use", "3000");
        map.put("tv_lv_cost", "6.270");
        map.put("tv_lv_now", "4.660");
        map.put("isfocus", false);
        list.add(map);


        map = new HashMap<>();
        map.put("tv_lv_name", "升华拜克");
        map.put("tv_lv_money_all", "139860.000");
        map.put("tv_lv_loss", "76720.000");
        map.put("tv_lv_loss_proint", "121.500%");
        map.put("tv_lv_have", "14000");
        map.put("tv_lv_use", "14000");
        map.put("tv_lv_cost", "4.510");
        map.put("tv_lv_now", "9.990");
        map.put("isfocus", true);
        list.add(map);

        map = new HashMap<>();
        map.put("tv_lv_name", "乐视网");
        map.put("tv_lv_money_all", "15040.000");
        map.put("tv_lv_loss", "-33745.000");
        map.put("tv_lv_loss_proint", "-69.170%");
        map.put("tv_lv_have", "500");
        map.put("tv_lv_use", "500");
        map.put("tv_lv_cost", "97.570");
        map.put("tv_lv_now", "30.080");
        map.put("isfocus", false);
        list.add(map);

        map = new HashMap<>();
        map.put("tv_lv_name", "亚夏股份");
        map.put("tv_lv_money_all", "29730.000");
        map.put("tv_lv_loss", "-70200.000");
        map.put("tv_lv_loss_proint", "-70.240%");
        map.put("tv_lv_have", "3000");
        map.put("tv_lv_use", "3000");
        map.put("tv_lv_cost", "33.310");
        map.put("tv_lv_now", "9.910");
        map.put("isfocus", false);
        list.add(map);

        map = new HashMap<>();
        map.put("tv_lv_name", "葛洲坝");
        map.put("tv_lv_money_all", "59950.000");
        map.put("tv_lv_loss", "11750.000");
        map.put("tv_lv_loss_proint", "24.370%");
        map.put("tv_lv_have", "5000");
        map.put("tv_lv_use", "5000");
        map.put("tv_lv_cost", "9.640");
        map.put("tv_lv_now", "11.990");
        map.put("isfocus", true);
        list.add(map);

        map = new HashMap<>();
        map.put("tv_lv_name", "申能股份");
        map.put("tv_lv_money_all", "24200.000");
        map.put("tv_lv_loss", "-16120.000");
        map.put("tv_lv_loss_proint", "-39.980%");
        map.put("tv_lv_have", "4000");
        map.put("tv_lv_use", "4000");
        map.put("tv_lv_cost", "10.080");
        map.put("tv_lv_now", "6.050");
        map.put("isfocus", false);
        list.add(map);
        return list;
    }

    @Override
    protected void onResume()
    {
        scrol_main.smoothScrollTo(0, 0);
        super.onResume();
    }


    public void showDialogEdt()
    {
        if ((alertDialog == null) || !(alertDialog.isShowing()))
        {
            alertDialog = new AlertDialog.Builder(mContext).create();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            alertDialog.setView(inflater.inflate(R.layout.ui_alertdialog_edt, null));
            alertDialog.show();
            Window window = alertDialog.getWindow();
            //            WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
            //            layoutParams.width = width;
            //            window.setAttributes(layoutParams);
            window.setContentView(R.layout.ui_alertdialog_edt);
            alertDialog.setCanceledOnTouchOutside(false);
            Button btn_dialog_edt_submit = (Button) window.findViewById(R.id.btn_dialog_edt_submit);

            final EditText edt_money_total = (EditText) window.findViewById(R.id.edt_money_total);//输入总资产
            final EditText edt_money_lost = (EditText) window.findViewById(R.id.edt_money_lost);//输入总盈亏
            final EditText edt_money_total_money = (EditText) window.findViewById(R.id.edt_money_total_money);//输入总市值
            final EditText edt_money_use = (EditText) window.findViewById(R.id.edt_money_use);//可取
            final EditText edt_money_have = (EditText) window.findViewById(R.id.edt_money_total);//可用

            //确认按钮
            btn_dialog_edt_submit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (TextUtils.isEmpty(edt_money_total.getText().toString()))
                    {
                        UIUtil.alert(mContext, "提示", "请先输入总资产", "确定", false);
                    } else if (TextUtils.isEmpty(edt_money_lost.getText().toString()))
                    {
                        UIUtil.alert(mContext, "提示", "请先输入浮动盈亏", "确定", false);
                    } else if (TextUtils.isEmpty(edt_money_total_money.getText().toString()))
                    {
                        UIUtil.alert(mContext, "提示", "请先输入总市值", "确定", false);
                    } else if (TextUtils.isEmpty(edt_money_use.getText().toString()))
                    {
                        UIUtil.alert(mContext, "提示", "请先输入可取金额", "确定", false);
                    } else if (TextUtils.isEmpty(edt_money_have.getText().toString()))
                    {
                        UIUtil.alert(mContext, "提示", "请先输入可用金额", "确定", false);
                    } else
                    {
                        alertDialog.dismiss();
                        tv_main_total.setText(edt_money_total.getText().toString());
                        if (Double.valueOf(edt_money_lost.getText().toString()) < 0)
                        {
                            tv_main_lost.setTextColor(getResources().getColor(R.color.text_blue));
                        } else
                        {
                            tv_main_lost.setTextColor(getResources().getColor(R.color.red));
                        }
                        tv_main_lost.setText(edt_money_lost.getText().toString());
                        tv_main_money_total.setText(edt_money_total_money.getText().toString());
                        tv_main_use.setText(edt_money_use.getText().toString());
                        tv_main_have.setText(edt_money_have.getText().toString());
                    }
                }
            });
        }
    }
}
