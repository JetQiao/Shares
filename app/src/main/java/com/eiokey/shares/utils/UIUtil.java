package com.eiokey.shares.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eiokey.shares.views.AlertDialogUI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class UIUtil
{

    /**
     * 设置本地图片
     *
     * @param url
     * @return
     */
    public static void setLoacalBitmap(ImageView view, String url)
    {
        try
        {
            FileInputStream fis = new FileInputStream(url);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            if (bitmap != null)
            {
                view.setImageBitmap(bitmap);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public static boolean isConnect(Context context)
    {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try
        {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null)
            {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected())
                {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            Log.v("error", e.toString());
        }
        return false;
    }

    /**
     * @param mcontext
     * @param v        设定文件
     * @return void 返回类型
     * @throws
     * @Title: hideSystemKeyBoard
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public static void hideSystemKeyBoard(Context mcontext, View v)
    {
        try
        {
            InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e)
        {
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context)
    {
        try
        {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int Dp2Px(Context con, int dp)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, con.getResources().getDisplayMetrics());
    }

    public static int Px2Dp(Context con, int px)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, con.getResources().getDisplayMetrics());
    }

    public static int getPxFromDpRes(Context con, int resid)
    {
        return (int) con.getResources().getDimension(resid);
    }

    /**
     * 二维码的 大小
     *
     * @return void
     */
    public static int getMaxCodeSize(float density)
    {
        int textSize = 0;
        if (density >= 3.0)
        {
            textSize = 220;
        } else if (density > 2.0 && density < 3.0)
        {
            textSize = 180;
        } else if (density >= 1.5 && density < 2.0)
        {
            textSize = 140;
        } else if (density >= 1.0 && density < 1.5)
        {
            textSize = 100;
        } else
        {
            textSize = 100;
        }
        return textSize;
    }

    /**
     * 密度计算 字体大小 文本框
     *
     * @return void
     */
    public static float getTextSize(float density)
    {
        float textSize = 0.0f;
        if (density >= 3.0)
        {
            textSize = 24;
        } else if (density >= 2.0 && density < 3.0)
        {
            textSize = 16;
        } else if (density >= 1.5 && density < 2.0)
        {
            textSize = 14;
        } else if (density >= 1.0 && density < 1.5)
        {
            textSize = 10;
        } else
        {
            textSize = 12;
        }
        return textSize;
    }

    /**
     * 密度计算 发送短信 字体 字体大小
     *
     * @return void
     */
    public static float getSendCodeBtnTextSize(float density)
    {
        float textSize = 0.0f;
        if (density >= 3.0)
        {
            textSize = 15;
        } else if (density >= 2.0 && density < 3.0)
        {
            textSize = 14;
        } else if (density >= 1.5 && density < 2.0)
        {
            textSize = 12;
        } else if (density >= 1.0 && density < 1.5)
        {
            textSize = 10;
        } else
        {
            textSize = 10;
        }
        return textSize;
    }

    public static float getH(float density)
    {
        int hight = 0;
        if (density >= 3.0)
        {
            hight = 800;
        } else if (density >= 2.0 && density < 3.0)
        {
            hight = 600;
        } else if (density >= 1.5 && density < 2.0)
        {
            hight = 480;
        } else if (density >= 1.0 && density < 1.5)
        {
            hight = 480;
        } else
        {
            hight = 240;
        }
        return hight;
    }

    /**
     * 动态计算设置listview高度
     *
     * @param listView
     * @函数名 setListViewHeightBasedOnChildren
     * @功能 TODO
     * @备注 <其它说明>
     */
    public static void changeListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // ((MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        listView.setLayoutParams(params);
    }

    private static Toast toast = null;

    public static void showToastl(Context con, String str)
    {
        if (toast == null)
        {
            toast = Toast.makeText(con, str, Toast.LENGTH_LONG);
        } else
        {
            toast.setText(str);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showToasts(Context con, String str)
    {
        if (toast == null)
        {
            toast = Toast.makeText(con, str, Toast.LENGTH_SHORT);
        } else
        {
            toast.setText(str);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }


    public static Drawable resizeDrawable(Context con, Drawable dr, int w, int h)
    {
        // Read your drawable from somewhere
        // Drawable dr = getResources().getDrawable(R.drawable.somedrawable);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        // Scale it to 50 x 50
        Drawable rdr = new BitmapDrawable(con.getResources(), Bitmap.createScaledBitmap(bitmap, w, h, true));
        // Set your new, scaled drawable "d"
        return rdr;
    }

    public static Drawable Bitmap2Drawable(Context con, Bitmap bm)
    {
        BitmapDrawable bd = new BitmapDrawable(con.getResources(), bm);
        return bd;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap Drawable2Bitmap(Context con, Drawable dr)
    {
        int w = dr.getIntrinsicWidth();
        int h = dr.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = dr.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        dr.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        dr.draw(canvas);
        return bitmap;
    }

    public static Bitmap rotateBitmap(Bitmap b, int degrees)
    {
        if (degrees != 0 && b != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try
            {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2)
                {
                    b.recycle(); // Android开发网再次提示Bitmap操作完应该显示的释放
                    b = b2;
                }
            } catch (OutOfMemoryError ex)
            {
                // Android123建议大家如何出现了内存不足异常，最好return 原始的bitmap对象。.
                // Log.e(TAG, "rotateBmp OutOfMemoryError");
                ex.printStackTrace();
            }
        }
        return b;
    }

    // to do need to improve and do with jpg and quality
    // // image.compressToJpeg(
    // new Rect(0, 0, image.getWidth(), image.getHeight()), 90,
    // filecon);

    @SuppressWarnings("deprecation")
    public static Bitmap loadBitmapFromLocal(String imgPath)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //
        options.inPreferredConfig = Bitmap.Config.RGB_565;// 表示16位位图
        // 565代表对应三原色占的位数
        // options.inInputShareable=true; //??
        options.inPurgeable = true;// 设置图片可以被回收
        options.inTempStorage = new byte[1024 * 1024 * 10];
        //
        options.inSampleSize = 1;
        Bitmap bm = BitmapFactory.decodeFile(imgPath, options);
        return bm;
        // jpgView.setImageBitmap(bm);
    }

    public static Bitmap loadBitmapFromLocalWithSampleSize(String imgPath, int sampleSize)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        Bitmap bm = BitmapFactory.decodeFile(imgPath, options);
        return bm;
        // jpgView.setImageBitmap(bm);
    }

    // todo calc file size with inJustDecodeBounds
    // todo get bitmap with inSampleSize

    public static void saveBitmap2JPG(String path, Bitmap bitmap)
    {
        File f = new File(path);// ("/sdcard/" + bitName + ".png");
        FileOutputStream fOut = null;
        try
        {
            f.createNewFile();
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void saveBitmap2JPG(String dir, String filename, Bitmap bitmap)
    {
        saveBitmap2JPG(dir + File.separator + filename, bitmap);
    }

    public static void saveBitmap2PNG(String path, Bitmap bitmap)
    {
        File f = new File(path);// ("/sdcard/" + bitName + ".png");
        FileOutputStream fOut = null;
        try
        {
            f.createNewFile();
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void saveBitmap2PNG(String dir, String filename, Bitmap bitmap)
    {
        saveBitmap2PNG(dir + File.separator + filename, bitmap);
    }

    /**
     * like ".../data/tmpResImgFile.jpg"
     */
    public static String saveResImg2Path(int resid, Context con, String filePath)
    {

        Drawable drawable = con.getResources().getDrawable(resid);
        Bitmap bmp = Drawable2Bitmap(con, drawable);
        String tmpResImgPath = filePath;
        saveBitmap2JPG(tmpResImgPath, bmp);
        return tmpResImgPath;
    }

    /**
     * 安全获取edit的string
     */
    public static String getEditTextSafe(EditText editText)
    {
        if (editText != null && editText.getText() != null && !(editText.getText().toString().trim().equals("")))
        {
            return editText.getText().toString().trim();
        } else
        {
            return "";
        }
    }

    /**
     * 判断edittext是否null,是的话返回true
     */
    public static Boolean isEditTextEmpty(EditText editText)
    {
        if (editText != null && editText.getText() != null && !(editText.getText().toString().trim().equals("")))
        {
            return false;
        } else
        {
            return true;
        }
    }

    // public static void confirm(Context context, String title, String message,
    // String ok, DialogInterface.OnClickListener onOK) {
    // AlertDialog.Builder builder = new AlertDialog.Builder(context);
    // if (!TextUtils.isEmpty(title)) {
    // builder.setTitle(title);
    // }
    // if (!TextUtils.isEmpty(message)) {
    // builder.setMessage(message);
    // }
    // builder.setCancelable(false);
    // builder.setPositiveButton(ok, onOK);
    // builder.create().show();
    // }

    // public static void confirm(Context context, String title, String message,
    // String ok, String cancel, DialogInterface.OnClickListener onOK) {
    // AlertDialog.Builder builder = new AlertDialog.Builder(context);
    // if (!TextUtils.isEmpty(title)) {
    // builder.setTitle(title);
    // }
    // if (!TextUtils.isEmpty(message)) {
    // builder.setMessage(message);
    // }
    // builder.setCancelable(false);
    // builder.setPositiveButton(ok, onOK);
    // builder.setNegativeButton(cancel, null);
    // builder.create().show();
    // }

    // public static void confirmYesNo(Context context, String title,
    // String message, String yes, String no,
    // DialogInterface.OnClickListener onYes,
    // DialogInterface.OnClickListener onNo) {
    // AlertDialog.Builder builder = new AlertDialog.Builder(context);
    // if (!TextUtils.isEmpty(title)) {
    // builder.setTitle(title);
    // }
    // if (!TextUtils.isEmpty(message)) {
    // builder.setMessage(message);
    // }
    // builder.setCancelable(false);
    // builder.setPositiveButton(yes, onYes);
    // builder.setNegativeButton(no, onNo);
    // builder.create().show();
    // }

    public static void alert(Context context, String message)
    {
        final AlertDialogUI alertDialog = new AlertDialogUI(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("确定", new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });
    }

    public static void alert(Context context, String title, String message)
    {
        final AlertDialogUI alertDialog = new AlertDialogUI(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("确定", new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });
    }

    public static void alert(Context context, String title, String message, String ok, boolean dis)
    {
        final AlertDialogUI alertDialog = new AlertDialogUI(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        if (dis)
        {
            alertDialog.setNegativeButton("", true, new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    // TODO Auto-generated method stub
                    alertDialog.dismiss();
                }
            });
        } else
        {
            alertDialog.setNegativeButton("", false, null);
        }
        alertDialog.setPositiveButton(ok, new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }
        });
    }


    public static void showToastLong(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static ProgressDialog loadingDialog(Context context)
    {

        ProgressDialog dlg = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        dlg.show();

        ProgressBar progress = (ProgressBar) dlg.findViewById(android.R.id.progress);
        LayoutParams progressParams = progress.getLayoutParams();
        progressParams.height = LayoutParams.WRAP_CONTENT;
        progressParams.width = LayoutParams.MATCH_PARENT;// set MATCH_PARENT to
        // center
        progress.setLayoutParams(progressParams);

        LinearLayout linear = (LinearLayout) progress.getParent();
        FrameLayout contentp = (FrameLayout) linear.getParent();
        FrameLayout contentp2 = (FrameLayout) contentp.getParent();
        FrameLayout contentp3 = (FrameLayout) contentp2.getParent();
        contentp3.setBackgroundResource(0);// remove gray background

        return dlg;
    }

    public static ProgressDialog loadingDialog(Context context, String title, String message)
    {
        ProgressDialog dlg = ProgressDialog.show(context, title, message, true, false);
        TextView msg = (TextView) dlg.findViewById(android.R.id.message);
        msg.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        return dlg;
    }

    /**
     * 重新定义图片高度
     *
     * @param bitmapToScale
     * @param newWidth
     * @param newHeight
     * @return
     * @author Davis
     */
    public static Bitmap scaleBitmap(Bitmap bitmapToScale, float newWidth, float newHeight)
    {
        if (bitmapToScale == null)
        {
            return null;
        }

        // get the original width and height
        int width = bitmapToScale.getWidth();
        int height = bitmapToScale.getHeight();

        if (width == newWidth && height == newHeight)
        {
            return bitmapToScale;
        }

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(newWidth / width, newHeight / height);

        // recreate the new Bitmap and set it back
        return Bitmap.createBitmap(bitmapToScale, 0, 0, bitmapToScale.getWidth(), bitmapToScale.getHeight(), matrix, true);
    }

    private static long lastClickTime;

    public static boolean isFastDoubleClick()
    {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000)
        {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    @SuppressWarnings("unused")
    private static boolean isInstallByread(String packageName)
    {
        return new File("/data/data/" + packageName).exists();
    }

    public static String bitmapToString(String filePath)
    {
        int degree = readPictureDegree(filePath);
        Bitmap bm = getSmallBitmap(filePath);
        bm = rotaingImageView(degree, bm);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static Bitmap getSmallBitmap(String filePath)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 768, 1024);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth)
        {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap rotaingImageView(int angle, Bitmap bitmap)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static int readPictureDegree(String path)
    {
        int degree = 0;
        try
        {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation)
            {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * @param mContext
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context mContext)
    {
        try
        {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            return packInfo.versionName;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static Bitmap returnBitMap(String url)
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inSampleSize = computeInitialSampleSize(opts, -1, 320 * 240);
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try
        {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            BitmapFactory.decodeFile(url, opts);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e)
        {

            e.printStackTrace();

        }
        return bitmap;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels)
    {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound)
        {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1))
        {
            return 1;
        } else if (minSideLength == -1)
        {
            return lowerBound;
        } else
        {
            return upperBound;
        }
    }

    @SuppressWarnings("unused")
    private static int color(String colorStr)
    {
        int color = 0;
        String[] colorStrs = colorStr.split(",");
        color = Color.rgb(Integer.parseInt(colorStrs[0].trim()), Integer.parseInt(colorStrs[1].trim()), Integer.parseInt(colorStrs[2].trim()));
        return color;
    }


    /**
     * 获取屏幕尺寸与密度.
     *
     * @param context the context
     * @return mDisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context)
    {
        Resources mResources;
        if (context == null)
        {
            mResources = Resources.getSystem();

        } else
        {
            mResources = context.getResources();
        }
        // DisplayMetrics{density=1.5, width=480, height=854, scaledDensity=1.5,
        // xdpi=160.421, ydpi=159.497}
        // DisplayMetrics{density=2.0, width=720, height=1280,
        // scaledDensity=2.0, xdpi=160.42105, ydpi=160.15764}
        DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();

        return mDisplayMetrics;
    }


    public static boolean hasNetwork(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
        {
            return true;
        }
        return false;
    }


    private static ProgressDialog dialog;

    private UIUtil()
    {
    }

    public static void showToast(Context c, int resId)
    {
        Toast.makeText(c, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context c, String desc)
    {
        Toast.makeText(c, desc, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressDialog(Context c, String msg)
    {
        dialog = ProgressDialog.show(c, "", msg);
    }

    public static void showProgressDialog(Context c, int resId)
    {
        dialog = ProgressDialog.show(c, "", c.getString(resId));
    }

    public static void cancelProgressDialog()
    {
        if (dialog != null)
        {
            dialog.cancel();
            dialog = null;
        }
    }
}
