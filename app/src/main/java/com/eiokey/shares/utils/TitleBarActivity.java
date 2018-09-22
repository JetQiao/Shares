package com.eiokey.shares.utils;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eiokey.shares.R;

import libs.eiokey.base.BaseActivity;

/**
 * 公共标题类
 */
public class TitleBarActivity extends BaseActivity
{
    private RelativeLayout rl_title_bar; // 标题栏
    private ImageView img_title_back;// 返回图标
    private TextView tv_title_left;// 标题栏左侧文字（隐藏返回图标情况下）

    private TextView tv_title_name, tv_title_menu_text;// 标题栏文字,右侧文字
    private ImageView img_title_menu; // 设置或编辑图片

    private RelativeLayout rl_title_back; // 返回
    private RelativeLayout rl_title_menu, rl_title_menu_text;// 设置或编辑

    /**
     * 标题栏是否可见
     */
    protected void setTitleBarShow(boolean show_boolean, int color)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return;
        }
        if (show_boolean)
        {
            rl_title_bar.setVisibility(View.VISIBLE);
            rl_title_bar.setBackgroundResource(color);
        } else
        {
            rl_title_bar.setVisibility(View.GONE);
        }
    }

    /***
     * 设置页面可返回 text_left,text_leftcolor 在show_left为true时使用
     *
     * @return
     */
    protected RelativeLayout setTitleBarBack(boolean show_back, boolean show_left, String text_left, int text_leftcolor)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        img_title_back = (ImageView) findViewById(R.id.img_title_back);
        tv_title_left = (TextView) findViewById(R.id.tv_title_left);
        if (rl_title_bar == null)
        {
            return null;
        }
        rl_title_back = getReturnRel();
        if (show_back)
        {
            rl_title_back.setVisibility(View.VISIBLE);
            rl_title_back.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    closeActivity();
                    hideSystemKeyBoard(mContext, v);
                }
            });

            if (show_left)
            {
                tv_title_left.setVisibility(View.VISIBLE);
                tv_title_left.setText(text_left);
                tv_title_left.setTextColor(getResources().getColor(text_leftcolor));
            }
        } else
        {
            img_title_back.setVisibility(View.GONE);
            if (show_left)
            {
                tv_title_left.setVisibility(View.VISIBLE);
                tv_title_left.setText(text_left);
                tv_title_left.setTextColor(getResources().getColor(text_leftcolor));
            }
        }
        return rl_title_back;
    }

    /**
     * 隐藏系统键盘
     *
     * @param mcontext
     * @param v
     */
    public static void hideSystemKeyBoard(Context mcontext, View v)
    {
        try
        {
            InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e)
        {
            // TODO: handle exception
        }
    }

    /***
     * 获取返回rel
     *
     * @return
     */
    protected RelativeLayout getReturnRel()
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return null;
        }
        return (RelativeLayout) findViewById(R.id.rl_title_back);
    }

    /**
     * 标题文字
     */
    protected void setTitleBarText(String title)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return;
        }
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        tv_title_name.setVisibility(View.VISIBLE);
        tv_title_name.setText(title);
    }

    /**
     * 标题文字带图标
     */
    protected void setTitleBarText(String title, int resId, String direction)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return;
        }
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        tv_title_name.setVisibility(View.VISIBLE);
        tv_title_name.setText(title);
        if (direction.equals("left"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
        }
        if (direction.equals("right"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
        }
        if (direction.equals("top"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
        }
        if (direction.equals("bottom"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resId);
        }
    }

    /**
     * 设置右侧图标
     */
    protected void setTitleBarImg(int res_id, boolean b, OnClickListener ls)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return;
        }
        if (b)
        {
            img_title_menu = (ImageView) findViewById(R.id.img_title_menu);
            rl_title_menu = (RelativeLayout) findViewById(R.id.rl_title_menu);
            img_title_menu.setVisibility(View.VISIBLE);
            img_title_menu.setImageResource(res_id);
            img_title_menu.setOnClickListener(ls);
        } else
        {
            rl_title_menu = (RelativeLayout) findViewById(R.id.rl_title_menu);
            rl_title_menu.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右侧图标文字
     */
    protected void setTitleBarTextRight(String text, boolean b, OnClickListener ls)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return;
        }
        if (b)
        {
            tv_title_menu_text = (TextView) findViewById(R.id.tv_title_menu_text);
            tv_title_menu_text.setVisibility(View.VISIBLE);
            tv_title_menu_text.setText(text);
            tv_title_menu_text.setOnClickListener(ls);
        } else
        {
            rl_title_menu_text = (RelativeLayout) findViewById(R.id.rl_title_menu_text);
            rl_title_menu_text.setVisibility(View.GONE);
        }
    }

    /**
     * 标题文字带图标，文字大小，是否粗体
     */
    protected void setTitleBarText(String title, int resId, String direction, float textSize, boolean style)
    {
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null)
        {
            return;
        }
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        tv_title_name.setVisibility(View.VISIBLE);
        tv_title_name.setText(title);
        tv_title_name.setTextSize(textSize);
        TextPaint tp = tv_title_name.getPaint();
        if (style)
        {
            tp.setFakeBoldText(true);
        } else
        {
            tp.setFakeBoldText(false);
        }
        if (direction.equals("left"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
        }
        if (direction.equals("right"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
        }
        if (direction.equals("top"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
        }
        if (direction.equals("bottom"))
        {
            tv_title_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, resId);
        }
    }

}
