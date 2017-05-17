package com.elk.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.elk.base.BaseActivity;
import com.elk.base.BaseApplication;
import com.elk.base.CommonConstants;
import com.elk.base.WeiXinLoginBean;
import com.elk.base.utils.SharedPreferencesUtil;
import com.elk.base.utils.UiUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

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

public class GetPhoneNumActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener ,IWXAPIEventHandler {
    private static final int   ERR_OK = 0;//(用户同意)
    private static final int  ERR_AUTH_DENIED = -4;//（用户拒绝授权）
    private static final int  ERR_USER_CANCEL = -2;//（用户取消）

    private static final String TEL_REGEX = "[1]\\d{10}";
    private static final String PAW_REGEX = "^[a-zA-Z0-9]{6,15}$";

    public static final String KEY_ASSCOIATE_STATUS  = "associateStatus";
    public static final String KEY_PASSWORD_STATUS  = "passwdStatus";
    public static final String KEY_WECHAT_OPEN_ID  = "weChatOpenId";
    public static final String KEY_WECHAT_UNION_ID = "weChatUnionId";
    public static final String KEY_WECHAT_CODE  = "weChatCode";
    Toolbar mToolbar;
    AppBarLayout mAppBar;
    EditText mEtPhoneNum;
    EditText mEtVerifyCode;
    TextView mTvGetCode;
    Button mBtNext;
    private RecyclerView.Adapter mAdapter;
    private String mPhoneNum;
    private String mVerifyCode;
    private String mCode;
    private IWXAPI api;
    private String wxOpenId;
    public String wxUnionId;
    private int mAssociateStatus;
    private int mPasswdStatus;
    private String mOpenId;
    private String mUnionId;
    private CountDownTimer CountTimer;
    //    private List<LoginFromWeixinBean.RowsBean> mLoginFromWeixinList = new ArrayList();



    //======================================初始化控件=========================
    @Override
    protected int initLayout() {
        return R.layout.activity_get_phone_num;
    }

    @Override
    protected void initView() {


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar);
        mEtPhoneNum = (EditText) findViewById(R.id.et_phone_num);
        mEtVerifyCode = (EditText) findViewById(R.id.et_verify_code);
        mTvGetCode = (TextView) findViewById(R.id.tv_get_code);
        mBtNext = (Button) findViewById(R.id.bt_next);


        handlerIntent();
        regToWx();
        initToolBar();
        mEtVerifyCode.setFilters(new InputFilter[]{new MaxTextLengthFilter(6)});
    }

    private void handlerIntent() {
        mAssociateStatus = getIntent().getIntExtra(KEY_ASSCOIATE_STATUS,0);
        mPasswdStatus = getIntent().getIntExtra(KEY_PASSWORD_STATUS,0);
        mOpenId = getIntent().getStringExtra(KEY_WECHAT_OPEN_ID);
        mUnionId = getIntent().getStringExtra(KEY_WECHAT_UNION_ID);
    }

    /**
     * 开启微信服务对象
     */
    private void regToWx(){
        api = WXAPIFactory.createWXAPI(this, CommonConstants.APP_ID);
        // 将该app注册到微信
        api.registerApp(CommonConstants.APP_ID);
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
        mEtVerifyCode.setOnClickListener(this);
        mTvGetCode.setOnClickListener(this);
        mBtNext.setOnClickListener(this);
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
        if (i == R.id.tv_get_code) {
            mPhoneNum = mEtPhoneNum.getText().toString();
            if (!verifyPhone()) {
                return;
            } else {
                mPresenter.bindGetOtpCode(mPhoneNum);
            }

        } else if (i == R.id.bt_next) {
            if (checkPhoneNum()) {
                String accessToken = SharedPreferencesUtil.getString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_ACCESS_TOKEN);
                Log.d(TAG, "accessToken: =============================" + accessToken);
                mPresenter.bindPhoneNo_1_0_0(accessToken, mOpenId, mUnionId, mVerifyCode, mPhoneNum);
                //                    if (WeiXinLoginBean.PASSWORD_HAS_SET==mPasswdStatus
                //                            && WeiXinLoginBean.WEIXIN_NOT_ASSCOCIATE== mAssociateStatus){
                //                        //密码关联,微信没关联，跳绑定手机号码界面，不跳输入密码界面
                //                        String WechatCode= SharedPreferencesUtil.getString(UiUtils.getContext(),WeiXinLoginBean.KEY_WEIXIN_CODE);
                //                        mPresenter.bindPhoneNo_1_0_0(WechatCode,mOpenId,mUnionId,mVerifyCode, mPhoneNum);
                //                    }else if (WeiXinLoginBean.PASSWORD_NOT_SET==mPasswdStatus
                //                            && WeiXinLoginBean.WEIXIN_NOT_ASSCOCIATE==mAssociateStatus){
                //                        //密码没关联,微信没关联，走完全部流程
                //                        String WechatCode= SharedPreferencesUtil.getString(UiUtils.getContext(),WeiXinLoginBean.KEY_WEIXIN_CODE);
                //                        mPresenter.bindPhoneNo_1_0_0(WechatCode,mOpenId,mUnionId,mVerifyCode, mPhoneNum);
                //                        Intent intent=new Intent(this,GetPassWordActivity.class);
                ////                        intent.putExtra(GetPassWordActivity.KEY_ASSCOIATE_STATUS,mAssociateStatus);
                ////                        intent.putExtra(GetPassWordActivity.KEY_PASSWORD_STATUS,mPasswdStatus);
                ////                        intent.putExtra(GetPassWordActivity.KEY_WECHAT_OPEN_ID,mOpenId);
                //                        intent.putExtra(GetPassWordActivity.KEY_VERIFY_CODE,mVerifyCode);
                //                        intent.putExtra(GetPassWordActivity.KEY_PHONE_NUM,mPhoneNum);
                //                        startActivity(intent);
                //                        killMyself();
                //                    }else if (new Integer(mPasswdStatus).equals(null)
                //                            && WeiXinLoginBean.WEIXIN_NOT_ASSCOCIATE== mAssociateStatus){
                //                        //密码没关联,微信没关联，走完全部流程
                //
                //                    }
                //                    }

            }


        }
    }

    /**
     *
     */
    private boolean checkPhoneNum() {
        mPhoneNum = mEtPhoneNum.getText().toString();
        mVerifyCode = mEtVerifyCode.getText().toString();
        if (TextUtils.isEmpty(mPhoneNum) || TextUtils.isEmpty(mVerifyCode)) {
            UiUtils.makeText("手机号与验证码不能为空");
            return false;
        } else {
            mBtNext.setEnabled(true);
            if (!mPhoneNum.matches(TEL_REGEX)) {
                UiUtils.makeText("请输入正确的手机号码");
            }  else {
                return true;
            }
        }
        return false;
    }



    private boolean verifyPhone() {
        if (TextUtils.isEmpty(mPhoneNum)) {
            UiUtils.makeText("手机号码不能为空");
            return false;
        } else if (!mPhoneNum.matches(TEL_REGEX)) {
            UiUtils.makeText("手机格式不正确");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void handleVerifyCode(CodeBean mineCodeBean) {
        if (CodeBean.SUCCESS.equals(mineCodeBean.getCode())) {
           CountTimer= new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (mTvGetCode==null){return;}
                    mTvGetCode.setText((millisUntilFinished / 1000) + "秒后重新获取");
                    mTvGetCode.setEnabled(false);
                }

                @Override
                public void onFinish() {
                    if (mTvGetCode==null){return;}
                    mTvGetCode.setEnabled(true);
                    mTvGetCode.setText("获取验证码");
                }
            }.start();
        }
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
                        intent.putExtra(GetPassWordActivity.KEY_ASSCOIATE_STATUS,mAssociateStatus);
                        intent.putExtra(GetPassWordActivity.KEY_PASSWORD_STATUS,mPasswdStatus);
                        intent.putExtra(GetPassWordActivity.KEY_WECHAT_OPEN_ID,mOpenId);
        intent.putExtra(GetPassWordActivity.KEY_VERIFY_CODE,mVerifyCode);
        intent.putExtra(GetPassWordActivity.KEY_PHONE_NUM,mPhoneNum);
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //        倒计时操作会因为界面关闭而停止，如果UI界面关闭，倒计时会发生空指针
        if (CountTimer!=null){
            CountTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**微信请求回调
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**微信响应回调
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode){
            case ERR_OK:    //用户同意
                UiUtils.makeText("用户同意");
                break;
            case ERR_AUTH_DENIED:       // 用户拒绝授权
                UiUtils.makeText("用户拒绝授权");
                break;
            case ERR_USER_CANCEL://用户取消
                UiUtils.makeText("用户取消");
                break;
        }


    }


    //======================================控件操作=========================

}