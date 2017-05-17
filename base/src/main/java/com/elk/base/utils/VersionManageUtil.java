package com.elk.base.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import java.io.File;

/**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2016/10/16 12:30
 * author caojiaxu
 */
public class VersionManageUtil {

    private static final String TAG = "VersionManageUtil";

    public static final String DEFAULT_PATH = "/sdcard/tourist/";//默认目录

    public static final String DOWNLOAD_APK = DEFAULT_PATH+"download_apk"; //更新apk目录

    public static final String SPLASH = DEFAULT_PATH+"spflash"; //启动页

    public static final String SPLSH_IMGAGE = "/sdcard/Android/data/com.elk.tourist/files/spflash.png";//启动页

    private Activity mActivity;
    private Dialog mDialog;
    private View mBtnDialogColse;
    private View mBtnUpdate;
    private ProgressDialog mProgressDialog;

    public VersionManageUtil(Activity context) {
        this.mActivity = context;
    }

    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "com.elk.tourist", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return verCode;
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.elk.tourist", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return verName;
    }



    public void updateVersion(String mIntroduction,String newVersion,int isWay) {

//        LayoutInflater inflaterDl = LayoutInflater.from(UiUtils.getContext());
//        LinearLayout layout = (LinearLayout) inflaterDl.inflate(R.layout.dialog_aboutus_version, null);
//        //去掉白色背景要用Dialog不能用AlertDialog
//        mDialog = new Dialog(mActivity);
//        //对话框
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        mDialog.show();
//
//        // 设置宽度为屏幕的宽度
//        WindowManager windowManager = mActivity.getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        Window dialogWindow = mDialog.getWindow();
//        dialogWindow.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (display.getWidth()); // 设置宽度
//        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        mDialog.getWindow().setAttributes(lp);
//        mDialog.getWindow().setContentView(layout);
//
//        TextView tvVersionCode = (TextView) layout.findViewById(R.id.dialog_version_tv_code);
//        TextView tvIntroduce = (TextView) layout.findViewById(R.id.dialog_version_tv_msg);
//        if (mIntroduction != null)
//            RichText.fromHtml(mIntroduction).into(tvIntroduce);
//
//        if (newVersion != null)
//            tvVersionCode.setText("新版本 " + newVersion);
//        /*-------------關閉dialog-------------*/
//        mBtnDialogColse = layout.findViewById(R.id.version_btn_dialog_close);
//
//        View line = layout.findViewById(R.id.version_line);
//        if (isWay == 1) {
//            mBtnDialogColse.setVisibility(View.GONE);
//            line.setVisibility(View.GONE);
//        } else {
//            mBtnDialogColse.setVisibility(View.VISIBLE);
//            line.setVisibility(View.VISIBLE);
//
////            mBtnDialogColse.setOnClickListener(this);
//        }
//        /*-------------升級版本-------------*/
//        mBtnUpdate = layout.findViewById(R.id.version_btn_update);
////        mBtnUpdate.setOnClickListener(this);
//        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    return true;
//                }
//                return false;
//            }
//        });

    }


    public void downFile(String url,String savePath,String saveName){
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMax(100);
        mProgressDialog.setTitle("正在下载");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCanceledOnTouchOutside(false);
        dismiss();

        toDownLoad(url,savePath,saveName);
    }


    public void toDownLoad(final String appPath,String savePath,String saveName) {

        mProgressDialog.show();

//        DownloadUtil.get().download(appPath, savePath, saveName,new DownloadUtil.OnDownloadListener() {
//            @Override
//            public void onDownloadSuccess(File file) {
//
//                if(file != null){
//                    mProgressDialog.dismiss();
//                    installApk(file);
//                }
//
//            }
//
//            @Override
//            public void onDownloading(int progress) {
//                mProgressDialog.setProgress(progress);
//
//            }
//
//            @Override
//            public void onDownloadFailed() {
//                mProgressDialog.dismiss();
//
//            }
//        });

    }

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        mActivity.startActivity(intent);
    }

    public View getBtnDialogColse(){
        return mBtnDialogColse;
    }

    public View getBtnUpdate(){
        return mBtnUpdate;
    }



    public void dismiss(){
        if(mDialog != null){
            mDialog.dismiss();
        }
    }
}
