package com.elk.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elk.base.BaseActivity;
import com.elk.base.BaseApplication;
import com.elk.base.WeiXinLoginBean;
import com.elk.base.utils.UiUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;

import butterknife.ButterKnife;

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
 * 类 名: LoginFromWeixin
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/3/17
 * author lixuan
 */

public class GetPassWordActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    private static final int ERR_OK = 0;//(用户同意)
    private static final int ERR_AUTH_DENIED = -4;//（用户拒绝授权）
    private static final int ERR_USER_CANCEL = -2;//（用户取消）

    private static final String TEL_REGEX = "[1]\\d{10}";
    private static final String PAW_REGEX = "^[a-zA-Z0-9]{6,15}$";

    public static final String KEY_ASSCOIATE_STATUS = "associateStatus";
    public static final String KEY_PASSWORD_STATUS = "passwdStatus";
    public static final String KEY_WECHAT_OPEN_ID = "weChatOpenId";
    public static final String KEY_WECHAT_UNION_ID = "weChatUnionId";
    public static final String KEY_VERIFY_CODE = "verifyCode";
    public static final String KEY_PHONE_NUM = "phoneNum";

     Toolbar mToolbar;
     AppBarLayout mAppBar;
     EditText mEtPasswordNum;
     Button mBtOk;

    private RecyclerView.Adapter mAdapter;
    private String mPhoneNum;
    private String mVerifyCode;
    private IWXAPI api;

    private int mAssociateStatus;
    private int mPasswdStatus;
    private String mOpenId;
    private String mUnionId;
    private String verifyCode;
    //    private List<LoginFromWeixinBean.RowsBean> mLoginFromWeixinList = new ArrayList();




    //======================================初始化控件=========================
    @Override
    protected int initLayout() {
        return R.layout.activity_get_password;
    }

    @Override
    protected void initView() {


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mEtPasswordNum = (EditText) findViewById(R.id.et_password_num);
        mBtOk = (Button) findViewById(R.id.bt_ok);



        handlerIntent();
        regToWx();
        initToolBar();
    }

    private void handlerIntent() {
        mAssociateStatus = getIntent().getIntExtra(KEY_ASSCOIATE_STATUS, 0);
        Log.d(TAG, "handlerIntent: associtate :"+mAssociateStatus);
        mPasswdStatus = getIntent().getIntExtra(KEY_PASSWORD_STATUS, 0);
        Log.d(TAG, "handlerIntent: mPasswdStatus :" + mPasswdStatus );
        mOpenId = getIntent().getStringExtra(KEY_WECHAT_OPEN_ID);
        Log.d(TAG, "handlerIntent: mOpenId :" + mOpenId);
        mUnionId = getIntent().getStringExtra(KEY_WECHAT_UNION_ID);
        Log.d(TAG, "handlerIntent:  mUnionId :"+ mUnionId);
        mVerifyCode = getIntent().getStringExtra(KEY_WECHAT_UNION_ID);
        Log.d(TAG, "handlerIntent:  mVerifyCode :"+ mVerifyCode);
        mPhoneNum = getIntent().getStringExtra(KEY_PHONE_NUM);
        Log.d(TAG, "handlerIntent: mPhoneNum :" + mPhoneNum);
    }

    /**
     * 开启微信服务对象
     */
    private void regToWx() {
//        api = WXAPIFactory.createWXAPI(this, WeChatConstants.APP_ID);
//        // 将该app注册到微信
//        api.registerApp(WeChatConstants.APP_ID);
    }

    /**
     * 设置标题栏
     */
    private void initToolBar() {
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            }
        });
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.return_a);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //======================================初始化控件=========================
    //======================================初始化数据=========================
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBtOk.setOnClickListener(this);
    }

    @Override
    protected void inject() {
        mPresenter = new LoginPresenter(new LoginModel(null,null),this, BaseApplication.getRxErrorHandler());
    }


    //======================================初始化数据=========================
    //======================================控件操作=========================
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_ok) {
            if (mEtPasswordNum.length() < 6 || mEtPasswordNum.length() > 15) {
                UiUtils.makeText("请输入6~15位密码");
                return;
            } else {
                mPresenter.setPassword_1_0_0(mPhoneNum, mEtPasswordNum.getText().toString());
            }

        }
    }


    @Override
    public void handleVerifyCode(CodeBean mineCodeBean) {

    }

    @Override
    public String getWeChatOpenId() {
        return null;
    }

    @Override
    public String getWeChatUnionId() {
        return null;
    }

    @Override
    public void returnBindwx(WeiXinLoginBean data) {

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
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}


//======================================控件操作=========================

