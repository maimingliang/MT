package com.elk.setting;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.elk.base.BaseActivity;
import com.elk.base.BaseApplication;
import com.elk.base.HttpResult;
import com.elk.base.UrlFileInfo;
import com.elk.base.utils.DataCleanManager;
import com.elk.base.utils.DownloadUtil;
import com.elk.base.utils.UiUtils;
import com.elk.base.utils.UserUtil;
import com.elk.base.utils.VersionManageUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zzhoujay.richtext.RichText;

import org.simple.eventbus.Subscriber;

import java.io.File;

import static com.elk.base.rxError.utils.Preconditions.checkNotNull;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * /**
 * 类       名: 设置页面
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2017
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/3/9
 * author   maimingliang
 */

@Route(path = "/user/SetUpActivity")
public class SetUpActivity extends BaseActivity<SetUpPresenter> implements SetUpContract.View, View.OnClickListener {


     ImageView mImgTitleBack;
     RelativeLayout mMineSettingLlModifyPaw;
     TextView mMineSettingTvCacheSize;
     RelativeLayout mMineSettingLlClearCache;
     RelativeLayout mRlMineAboutUs;
     RelativeLayout mRlMineUserAgreement;
     RelativeLayout mRlMineNewVersion;
     Button mMineSettingBtnLogout;
     RelativeLayout rlFeedBack;
     RelativeLayout mRlHelpCenter;
     ImageView mIvHasUpdate;

    private View mBtnDialogColse;
    private View mBtnUpdate;
    private Dialog mDialog;
    private String mVersionName;
    private String mVersionNumber;
    private String mAppPath;
    private ProgressDialog mProgressDialog;
    private String mIntroduction;
    private Integer mWay;

    public static final int UPDATE_APP_TYPE = 0;
     private RxPermissions mRxPermissions;
      private String saveName = "milulx.apk";
    private String savePath = VersionManageUtil.DOWNLOAD_APK;

    private boolean isKeyBack = false;

    @Subscriber(tag = "pwd_finish")
    public void onPwdEvent(boolean event){
        finish();
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initView() {
        mImgTitleBack = (ImageView) findViewById(R.id.img_title_back);
        mMineSettingLlModifyPaw = (RelativeLayout) findViewById(R.id.mine_setting_ll_modify_paw);
        mMineSettingTvCacheSize = (TextView) findViewById(R.id.mine_setting_tv_cache_size);
        mMineSettingLlClearCache = (RelativeLayout) findViewById(R.id.mine_setting_ll_clear_cache);
        mRlMineAboutUs = (RelativeLayout) findViewById(R.id.rl_mine_about_us);
        mRlMineUserAgreement = (RelativeLayout) findViewById(R.id.rl_mine_user_agreement);
        mRlMineNewVersion = (RelativeLayout) findViewById(R.id.rl_mine_new_version);
        mMineSettingBtnLogout = (Button) findViewById(R.id.mine_setting_btn_logout);
        rlFeedBack = (RelativeLayout) findViewById(R.id.rl_feed_back);
        mRlHelpCenter = (RelativeLayout) findViewById(R.id.rl_help_center);
        mIvHasUpdate = (ImageView) findViewById(R.id.mine_aboutus_iv_has_update);
    }

    @Override
    protected void initData() {
        mVersionName = VersionManageUtil.getVerName(UiUtils.getContext());
        mPresenter.check();
        mMineSettingTvCacheSize.setText(getCacheSize());


        mRxPermissions = new RxPermissions(this);

    }

    @Override
    protected void initListener() {
        mRlMineAboutUs.setOnClickListener(this);
        mRlMineUserAgreement.setOnClickListener(this);
        mMineSettingLlClearCache.setOnClickListener(this);
        mImgTitleBack.setOnClickListener(this);
        mMineSettingLlModifyPaw.setOnClickListener(this);
        rlFeedBack.setOnClickListener(this);
        mRlHelpCenter.setOnClickListener(this);
        mRlMineNewVersion.setOnClickListener(this);
        mMineSettingBtnLogout.setOnClickListener(this);

    }

    @Override
    protected void inject() {
        mPresenter = new SetUpPresenter(new SetUpModel(null,null),this, BaseApplication.getRxErrorHandler());
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.rl_mine_about_us) {
//            startActivity(new Intent(this, AboutUsActivity.class));

        } else if (i == R.id.rl_mine_user_agreement) {
//            startActivity(new Intent(this, AboutUsProtocolActivity.class));//用户协议

        } else if (i == R.id.mine_setting_ll_clear_cache) {
            clearCache();

        } else if (i == R.id.img_title_back) {
            killMyself();

        } else if (i == R.id.mine_setting_ll_modify_paw) {
//            startActivity(new Intent(this, ForgetPasswordActivity.class));

        } else if (i == R.id.rl_feed_back) {
//            startActivity(new Intent(this, UserTicklingActivity.class));

        } else if (i == R.id.rl_help_center) {
//            startActivity(new Intent(this, HelpCenterActivity.class));

        } else if (i == R.id.rl_mine_new_version) {
            if (mVersionNumber == null) {
                UiUtils.makeText("暂无更新");
                return;
            }
            if (mVersionName.equals(mVersionNumber)) {
                UiUtils.makeText("目前app已是最新版本");
            } else {

                isKeyBack = true;
                updateVersion();
            }

        } else if (i == R.id.version_btn_dialog_close) {
            mDialog.dismiss();

        } else if (i == R.id.version_btn_update) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMax(100);
            mProgressDialog.setTitle("正在下载");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mDialog.dismiss();
            if (mAppPath != null) {

                downFile(mAppPath);
            }

        } else if (i == R.id.mine_setting_btn_logout) {
            logout();

        }
    }

    private void logout() {
        mPresenter.logout();
    }

    private void downFile(final String appPath) {
        mProgressDialog.show();
        DownloadUtil.get().download(appPath, savePath, saveName,new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {

                if(file != null){
                    mProgressDialog.dismiss();
                    installApk(file);
                }

            }

            @Override
            public void onDownloading(int progress) {
                mProgressDialog.setProgress(progress);

            }

            @Override
            public void onDownloadFailed() {
                mProgressDialog.dismiss();

            }
        });

    }


    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
    private void updateVersion() {

        LayoutInflater inflaterDl = LayoutInflater.from(UiUtils.getContext());
        LinearLayout layout = (LinearLayout) inflaterDl.inflate(R.layout.dialog_aboutus_version, null);
        //去掉白色背景要用Dialog不能用AlertDialog
        mDialog = new Dialog(this);
        //对话框
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        mDialog.show();

        // 设置宽度为屏幕的宽度
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes(lp);
        mDialog.getWindow().setContentView(layout);

        TextView tvVersionCode = (TextView) layout.findViewById(R.id.dialog_version_tv_code);
        TextView tvIntroduce = (TextView) layout.findViewById(R.id.dialog_version_tv_msg);
        if (mIntroduction != null)
            RichText.fromHtml(mIntroduction).into(tvIntroduce);

        if (mVersionNumber != null)
            tvVersionCode.setText("新版本 " + mVersionNumber);
        /*-------------關閉dialog-------------*/
        mBtnDialogColse = layout.findViewById(R.id.version_btn_dialog_close);

        View line = layout.findViewById(R.id.version_line);
        if (mWay != null && mWay == 1) {
            mBtnDialogColse.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        } else {
            mBtnDialogColse.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);

            mBtnDialogColse.setOnClickListener(this);
        }
        /*-------------升級版本-------------*/
        mBtnUpdate = layout.findViewById(R.id.version_btn_update);
        mBtnUpdate.setOnClickListener(this);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });


    }

    /**
     * @desc 清理缓存
     */
    private void clearCache() {
        DataCleanManager.cleanApplicationData(UiUtils.getContext(),
                UiUtils.getContext().getCacheDir().getAbsolutePath(), UiUtils.getContext().getFilesDir().getAbsolutePath());
        mMineSettingTvCacheSize.setText(getCacheSize());
    }

    private String getCacheSize() {
        long fileSize = 0;
        String cacheSize = "0KB";
        File filesDir = UiUtils.getContext().getFilesDir();// /data/data/package_name/files
        File cacheDir = UiUtils.getContext().getCacheDir();// /data/data/package_name/cache

        fileSize += DataCleanManager.getDirSize(filesDir);
        fileSize += DataCleanManager.getDirSize(cacheDir);
        cacheSize = DataCleanManager.getFormatSize(fileSize);
        return cacheSize;
    }

    @Override
    public String getVersionNum() {
        return VersionManageUtil.getVerName(UiUtils.getContext());
    }

    @Override
    public int getAppType() {
        return UPDATE_APP_TYPE;
    }

    @Override
    public void returnVersionCheck(AppUpdateBeen appUpdateBeen) {
        if (appUpdateBeen == null) {
            return;
        }
        AppUpdateBeen.Data data = appUpdateBeen.getData();
        if (data == null) {
            return;
        }

        AppUpdateBeen.Data.FileInfoExt fileInfoExt = data.getFileInfoExt();
        if (fileInfoExt != null && fileInfoExt.getPath() != null) {
            mAppPath = UrlFileInfo.PREVIEW + fileInfoExt.getPath();
        }
        if (data.getIntroduction() != null) {
            mIntroduction = data.getIntroduction();
        }
        if (data.getVersionNumber() != null) {
            mVersionNumber = data.getVersionNumber();
        }
        if (data.getUpdateFlag() != null && data.getUpdateFlag() == 1) {
            mWay = data.getWay();
            mIvHasUpdate.setVisibility(View.VISIBLE);
        }else {

            mIvHasUpdate.setVisibility(View.GONE);
        }
        if (!mVersionName.equals(data.getVersionNumber())) {
            mVersionNumber = data.getVersionNumber();
            if (data.getFileInfoExt() != null && data.getFileInfoExt().getPath() != null)
                mAppPath = UrlFileInfo.PREVIEW + data.getFileInfoExt().getPath();

        } else {
            mIvHasUpdate.setVisibility(View.GONE);
        }
    }

    @Override
    public void returnLogout(HttpResult<String> data) {
        if(data == null){
            return;
        }
        ARouter.getInstance().build("/touristUser/login").navigation();
        UserUtil.clear();
        killMyself();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
     }


}

