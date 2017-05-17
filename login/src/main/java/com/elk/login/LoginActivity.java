package com.elk.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.elk.base.BaseActivity;
import com.elk.base.BaseApplication;
import com.elk.base.CommonConstants;
import com.elk.base.LoginBeen;
import com.elk.base.WeiXinLoginBean;
import com.elk.base.utils.SharedPreferencesUtil;
import com.elk.base.utils.UiUtils;
import com.elk.base.utils.UserUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import timber.log.Timber;

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
 * 类 名:
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/2/6
 * author lixuan
 */

@Route(path = "/touristUser/login")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    public static final String EVENT_KEY_ISLOGIN = "evebt_key_islogin";
    public static final String EVENT_KEY_ISREGIST = "evebt_key_isregist";
    ImageButton mIvGoBack;
    ImageView mLoginIvPhone;
    EditText mLoginEtUserid;
    ImageView mLoginIvClearid;
    ImageView mLoginIvLock;
    EditText mLoginEtPaw;
    ImageView mLoginIvClearpaw;
    LinearLayout mLoginEditLayout;
    Button mLoginBtnLogin;
    TextView mTvLoginFromThird;
    RelativeLayout mRlLoginFromThird;
    RelativeLayout mLoginFromWexin;
    TextView mTvRegister;
    TextView mLoginTvForgetpaw;
    RelativeLayout mRlActivityContainer;
    private String mUserId;
    private String mPassword;
    private String mWxOpenId;
    private String mWxUnionId;
    final Calendar mCalendar = Calendar.getInstance();
    private int lastGetOpenIdTime;
    private IWXAPI api;


    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        api = WXAPIFactory.createWXAPI(UiUtils.getContext(), CommonConstants.APP_ID);
        // 将该app注册到微信
        api.registerApp(CommonConstants.APP_ID);

        mIvGoBack = (ImageButton) findViewById(R.id.iv_go_back);
        mLoginIvPhone = (ImageView) findViewById(R.id.login_iv_phone);
        mLoginEtUserid = (EditText) findViewById(R.id.login_et_userid);
        mLoginIvClearid = (ImageView) findViewById(R.id.login_iv_clearid);
        mLoginIvLock = (ImageView) findViewById(R.id.login_iv_lock);
        mLoginEtPaw = (EditText) findViewById(R.id.login_et_paw);
        mLoginIvClearpaw = (ImageView) findViewById(R.id.login_iv_clearpaw);
        mLoginEditLayout = (LinearLayout) findViewById(R.id.login_edit_layout);
        mLoginBtnLogin = (Button) findViewById(R.id.login_btn_login);
        mTvLoginFromThird = (TextView) findViewById(R.id.tv_login_from_third);
        mRlLoginFromThird = (RelativeLayout) findViewById(R.id.rl_login_from_third);
        mLoginFromWexin = (RelativeLayout) findViewById(R.id.login_from_wexin);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mLoginTvForgetpaw = (TextView) findViewById(R.id.login_tv_forgetpaw);
        mRlActivityContainer = (RelativeLayout) findViewById(R.id.rl_activity_container);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserUtil.isStored()) {
                killMyself();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnClickEvnet();
        mLoginIvClearid.setOnClickListener(this);
        mLoginIvClearpaw.setOnClickListener(this);
        mIvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void inject() {
        mPresenter = new LoginPresenter(new LoginModel(null,null),this, BaseApplication.getRxErrorHandler());
    }

    private void btnClickEvnet() {
//        登录
        RxView.clicks(mLoginBtnLogin)
                .throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                doLogin();
            }
        });
//注册
        RxView.clicks(mTvRegister)
                .throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
//                startActivity(new Intent(UiUtils.getContext(), RegisterActivity.class));
            }
        });
//微信登录
        RxView.clicks(mLoginFromWexin)
                .throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                                startActivity(new Intent(UiUtils.getContext(), GetPhoneNumActivity.class));
                bindWx();
            }
        });
//忘记密码
        RxView.clicks(mLoginTvForgetpaw)
                .throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
//                startActivity(new Intent(UiUtils.getContext(), ForgetPasswordActivity.class));
            }
        });
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
        UiUtils.makeText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }


    @Subscriber(tag = EVENT_KEY_ISREGIST)
    public void onModifyUserEvent(boolean event) {
        if (!event) {
            return;
        }
        killMyself();
    }


    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.login_iv_clearid) {
            mLoginEtUserid.setText("");

        } else if (i == R.id.login_iv_clearpaw) {
            mLoginEtPaw.setText("");

        }
    }

    private void doLogin() {
         mUserId = mLoginEtUserid.getText().toString();
        mPassword = mLoginEtPaw.getText().toString();
        if (TextUtils.isEmpty(mUserId) || "".equals(mUserId)) {
            UiUtils.makeText("账号不能为空");
            return;
        } else if (mUserId.length() != 11) {
            UiUtils.makeText("请输入11位手机号码");
            return;
        } else if (TextUtils.isEmpty(mPassword) || "".equals(mUserId)) {
            UiUtils.makeText("密码不能为空");
            return;
        } else if (mPassword.length() < 6 || mPassword.length() > 15) {
            UiUtils.makeText("请输入6~15位密码");
            return;
        } else {
             mPresenter.login(mUserId, mPassword);
        }
    }

    @Override
    public void handleVerifyCode(CodeBean mineCodeBean) {

    }

    @Subscriber(tag = LoginActivity.EVENT_KEY_ISLOGIN)
    public void onEventHasLogin(boolean event) {
        if(event){
            UiUtils.makeText("账号已经登录");
            killMyself();
        }
    }


    /**
     * 获取Openid和UnionId
     */
    private void bindWx() {
//        if (mWxOpenId!=null&&mWxUnionId!=null){
//            Log.d(TAG, "bindWx: "+"======================已有的授权证书");
//                UiUtils.makeText("使用已有的授权证书发起注册");
//                Log.d(TAG, "bindWx: "+"======================使用已有的授权证书发起注册");
//                mPresenter.bindWeChatLogin_1_0_0(mWxOpenId,mWxUnionId);
//                return;
//        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test" + new Random().nextInt(10000);
        api.sendReq(req);
        Timber.tag(TAG).d("------> bindWx ========================== ");
        UiUtils.makeText("发起微信授权。。。");

//        ShareSDK.initSDK(this);
//        Platform platform = ShareSDK.getPlatform(this, Wechat.NAME);
//        platform.setPlatformActionListener(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//                if (hashMap == null) {
//                    return;
//                }
////                map:value
////                {unionid=o8c2oxFolBrTAldkk0WlPOTkg_xg,
////                        country=CN,
////                        nickname=lx,
////                        city=Shenzhen,
////                        privilege=[],
////                    province=Guangdong,
////                            language=zh_CN,
////                            headimgurl=http://wx.qlogo.cn/mmopen/4ZtlQk4Pd4M9RpFuXQua4giayTRInY6miaUJ25x55K4UY7EB4YibnEkpSGEZ2MHavU8YBkXTibPoL2TuWF7Jr9SH71ttxAOpgXJy/0,
////                    sex=1,
////                            openid=opBDavpy-78WfzAc9oGbVjVWArbA}
//                System.out.println("HashMap-------------->"+hashMap);
//                Timber.tag(TAG).d("------> opid = " + hashMap.get("openid"));
//                Timber.tag(TAG).d("------> unionid = " + hashMap.get("unionid"));
//                mWxOpenId = (String) hashMap.get("openid");
//                mWxUnionId = (String) hashMap.get("unionid");
//                mPresenter.bindWeChatLogin_1_0_0(mWxOpenId,mWxUnionId);
//            }
//            @Override
//            public void onError(Platform platform, int i, Throwable throwable) {
//                Timber.tag(TAG).d("------> error = " + throwable.getMessage());
//                UiUtils.makeText("授权操作遇到错误");
//            }
//            @Override
//            public void onCancel(Platform platform, int i) {
//                UiUtils.makeText("授权操作取消");
//            }
//        });
//        platform.SSOSetting(true);
//        platform.showUser(null);
//        platform.authorize();
    }

    /**类WXEntryActivity获得Code后回调此方法
     * @param event
     */
    @Subscriber(tag = WeiXinLoginBean.KEY_WEIXIN_CODE)
    public void doGetWXAccessCode(boolean event) {
        if (!event) {
            return;
        }
        UiUtils.makeText("微信授权码请求中. . . ");
        String code = SharedPreferencesUtil.getString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_CODE);
        mPresenter.getAccessCode(code);
        Log.d(TAG, "doGetWXAccessCode: ==========================================================");
    }


    @Subscriber(tag = WeiXinLoginBean.KEY_WEIXIN_ACCESS_TOKEN)
    public void doBindWeChatLogin_1_0_0(boolean event) {
        if (!event) {
            return;
        }
        mWxOpenId = SharedPreferencesUtil.getString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_OPEN_ID);
        mWxUnionId = SharedPreferencesUtil.getString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_UNIN_ID);
        mPresenter.bindWeChatLogin_1_0_0(mWxOpenId, mWxUnionId);
        Log.d(TAG, "doBindWeChatLogin_1_0_0: =============================================");
    }

    @Override
    public String getWeChatOpenId() {
        return mWxOpenId;
    }

    @Override
    public String getWeChatUnionId() {
        return mWxUnionId;
    }

    /**
     * 根据微信绑定状态选择跳转的页面
     *
     * @param data
     */
    @Override
    public void returnBindwx(WeiXinLoginBean data) {
        if (data == null) {
            System.out.println("没有获得微信绑定信息");
            return;
        }
        if (WeiXinLoginBean.PASSWORD_HAS_SET == data.getData().getPasswdStatus()
                && WeiXinLoginBean.WEIXIN_HAS_ASSCOCIATE == data.getData().getWeChatStatus()) {

//            保存用户信息
            LoginBeen.DataBeen bean = new LoginBeen.DataBeen();
            bean.setId(data.getData().getId());
            bean.setToken(data.getData().getToken());
            bean.setPhoneNo(data.getData().getPhoneNo());
            UserUtil.store(bean);
            EventBus.getDefault().post(true, LoginActivity.EVENT_KEY_ISLOGIN);
            //密码和状态都关联，直接登录
            UiUtils.makeText("登录成功");
            killMyself();
        } else if (WeiXinLoginBean.PASSWORD_NOT_SET == data.getData().getPasswdStatus()
                && WeiXinLoginBean.WEIXIN_HAS_ASSCOCIATE == data.getData().getWeChatStatus()) {
            //密码没关联和微信关联，不跳绑定手机号码界面，跳输入密码界面
            UiUtils.makeText("跳密码页面");
            Intent intent = new Intent(this, GetPassWordActivity.class);
            intent.putExtra(GetPassWordActivity.KEY_ASSCOIATE_STATUS, data.getData().getWeChatStatus());
            intent.putExtra(GetPassWordActivity.KEY_PASSWORD_STATUS, data.getData().getPasswdStatus());
            intent.putExtra(GetPassWordActivity.KEY_WECHAT_OPEN_ID, mWxOpenId);
            intent.putExtra(GetPassWordActivity.KEY_VERIFY_CODE, mWxUnionId);
            startActivity(intent);
        } else if (WeiXinLoginBean.PASSWORD_HAS_SET == data.getData().getPasswdStatus()
                && WeiXinLoginBean.WEIXIN_NOT_ASSCOCIATE == data.getData().getWeChatStatus()) {
            //密码关联和微信没关联，跳绑定手机号码界面，做进一步判断
            Intent intent = new Intent(this, GetPhoneNumActivity.class);
            intent.putExtra(GetPhoneNumActivity.KEY_ASSCOIATE_STATUS, data.getData().getWeChatStatus());
            intent.putExtra(GetPhoneNumActivity.KEY_PASSWORD_STATUS, data.getData().getPasswdStatus());
            intent.putExtra(GetPhoneNumActivity.KEY_WECHAT_OPEN_ID, mWxOpenId);
            intent.putExtra(GetPhoneNumActivity.KEY_WECHAT_UNION_ID, mWxUnionId);
            startActivity(intent);
        } else if (WeiXinLoginBean.PASSWORD_NOT_SET == data.getData().getPasswdStatus()
                && WeiXinLoginBean.WEIXIN_NOT_ASSCOCIATE == data.getData().getWeChatStatus()) {
            //密码没关联和微信没关联，走完全部流程
            Intent intent = new Intent(this, GetPhoneNumActivity.class);
            intent.putExtra(GetPhoneNumActivity.KEY_ASSCOIATE_STATUS, data.getData().getWeChatStatus());
            intent.putExtra(GetPhoneNumActivity.KEY_PASSWORD_STATUS, data.getData().getPasswdStatus());
            intent.putExtra(GetPhoneNumActivity.KEY_WECHAT_OPEN_ID, mWxOpenId);
            intent.putExtra(GetPhoneNumActivity.KEY_WECHAT_UNION_ID, mWxUnionId);
            startActivity(intent);
        }

    }

}