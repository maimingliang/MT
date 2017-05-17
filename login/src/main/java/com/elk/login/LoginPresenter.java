package com.elk.login;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.elk.base.AppManager;
import com.elk.base.LoginBeen;
import com.elk.base.OutputCode;
import com.elk.base.WeiXinLoginBean;
import com.elk.base.mvp.BasePresenter;
import com.elk.base.rxError.core.RxErrorHandler;
import com.elk.base.rxError.handler.ErrorHandleSubscriber;
import com.elk.base.utils.RxUtils;
import com.elk.base.utils.UiUtils;
import com.elk.base.utils.UserUtil;

import org.simple.eventbus.EventBus;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */


/**
 * 类 名: Login
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 *date   2017/2/6
 *author lixuan
 */

public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private AppManager mAppManager;
    private LoginContract.Model mModel;
    private LoginContract.View mRootView;
    private int mPage;

    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mModel=  model;
        this.mRootView=  rootView;
    }

    /**登录
     * @param id
     * @param passwd
     */
    public void login(String id, String passwd) {



        mModel.login(id,passwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<LoginBeen.DataBeen>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<LoginBeen.DataBeen>(mErrorHandler) {
                    @Override
                    public void onNext(LoginBeen.DataBeen dataBeen) {
                        if (dataBeen == null) {
                            return;
                        }

                        UserUtil.store(dataBeen);
                        EventBus.getDefault().post(true, LoginActivity.EVENT_KEY_ISLOGIN);
                        mRootView.killMyself();
                    }
                });
    }


    /**
     * 注册
     */
    public void doRegisterRequest(final String userID, final String passWord, String code, int accType) {
        mModel.register(userID, passWord,code,accType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<RegisterEntity>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<RegisterEntity>(mErrorHandler) {
                    @Override
                    public void onNext(RegisterEntity registerEntity) {
                        EventBus.getDefault().post(true, LoginActivity.EVENT_KEY_ISREGIST);
                        UiUtils.makeText("注册成功");
                        LoginBeen.DataBeen bean=new LoginBeen.DataBeen();
                        bean.setId(registerEntity.getData().getId());
                        bean.setPhoneNo(registerEntity.getData().getPhoneNo());
                        bean.setToken(registerEntity.getData().getToken());
//                        UserUtil.store(bean);
//                        mRootView.launchActivity(new Intent(UiUtils.getContext(), MineUserInfoActivity.class));
                        mRootView.killMyself();
                    }
                });
    }

    /**获取注册验证码
     * @param phoneNo
     */
    public void requestRegistVerifyCode(String phoneNo) {
        mModel.getUnRegisterVerifCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<CodeBean>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
                    @Override
                    public void onNext(CodeBean codeBean) {
                        mRootView.handleVerifyCode(codeBean);
                    }
                });
    }

    //==========================================注册页=================================================

    //========================忘记密码，和明亮写重了===========================================
    /**获取忘记密码验证码
     * @param phoneNo
     */
    public void requestHasResideredVerifCode(String phoneNo) {
        mModel.getHasResideredVerifCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<CodeBean>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
                    @Override
                    public void onNext(CodeBean codeBean) {
                        mRootView.handleVerifyCode(codeBean);
                    }
                });
    }
//
//    /**提交修改密码
//     * @param phoneNo
//     */
//    public void requestModifyPassword(String phoneNo , String passwd, String code) {
//        mModel.modifyPassword(phoneNo,passwd,code)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxUtils.<CodeBean>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
//                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
//                    @Override
//                    public void onNext(CodeBean codeBean) {
//                        UiUtils.makeText("密码修改成功，开始退出账号");
////                        doLogout(UserUtil.getToken());
//                        }
//                });
//    }
////
//    /**登出
//     * @param token
//     */
//    private void doLogout(String token) {
//        mModel.logOut(token)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxUtils.<HttpResult<String>>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
//                .subscribe(new ErrorHandleSubscriber<HttpResult<String>>(mErrorHandler) {
//                    @Override
//                    public void onNext(HttpResult<String> stringHttpResult) {
//                        UserUtil.clear();
//                        mRootView.launchActivity(new Intent(UiUtils.getContext(),LoginActivity.class));
//                        UiUtils.makeText("账号退出成功，请重新登录");
//                        mRootView.killMyself();
//                    }
//                });
//    }
    //========================忘记密码，和明亮写重了===========================================


    //=========================================绑定微信==============================

    /**
     * 获取微信授权绑定
     */
    public void bindWeChatLogin_1_0_0(String openID,String UnionID){
        mModel.weChatLogin_1_0_0(openID,UnionID)
                .subscribeOn(Schedulers.io())  /*http请求线程*/
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();
                    }
                })
                .subscribe(new ErrorHandleSubscriber<WeiXinLoginBean>(mErrorHandler) {
                    @Override
                    public void onNext(WeiXinLoginBean data) {
                        Log.d(TAG, "WeiXinLoginBean:----------------------------> "+data.toString());
                        UiUtils.makeText("微信授权成功");
                        mRootView.returnBindwx(data);
                    }
                });
    }

    /**微信注册绑定手机
     * @param accessToken
     * @param weChatOpenId
     * @param weChatUnionId
     * @param code
     * @param phoneNo
     */
    public void bindPhoneNo_1_0_0(String accessToken, String weChatOpenId, String weChatUnionId, final String code, String phoneNo){
        mModel.bindPhoneNo_1_0_0(accessToken, weChatOpenId,weChatUnionId,code,phoneNo)
                .subscribeOn(Schedulers.io())  /*http请求线程*/
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();
                    }
                })
                .subscribe(new ErrorHandleSubscriber<PhoneNumBindBean>(mErrorHandler) {
                    @Override
                    public void onNext(PhoneNumBindBean codeBean) {
                        Log.d(TAG, "PhoneNumBindBean:----------------------------> "+codeBean.toString());
                        if (!codeBean.getCode().equals(OutputCode.OK.getCode())){
                            return;
                        }
                        if (WeiXinLoginBean.WEIXIN_HAS_ASSCOCIATE==codeBean.getData().getWeChatStatus()
                                &&WeiXinLoginBean.PASSWORD_HAS_SET==codeBean.getData().getPasswdStatus()
                                ){
                            LoginBeen.DataBeen bean= new LoginBeen.DataBeen();
                            bean.setId(codeBean.getData().getId());
                            bean.setToken(codeBean.getData().getToken());
                            bean.setPhoneNo(codeBean.getData().getPhoneNo());
                            UserUtil.store(bean);
                            UiUtils.makeText("手机号码和微信绑定成功");
                            EventBus.getDefault().post(true, LoginActivity.EVENT_KEY_ISLOGIN);
                            mRootView.killMyself();
                        }else if (
                                WeiXinLoginBean.WEIXIN_HAS_ASSCOCIATE==codeBean.getData().getWeChatStatus()
                                        &&WeiXinLoginBean.PASSWORD_NOT_SET==codeBean.getData().getPasswdStatus()
                                ){
                           mRootView.launchActivity(new Intent(UiUtils.getContext(),GetPassWordActivity.class));
                            mRootView.killMyself();
                        }
                    }
                });
    }


    /**获取忘记密码验证码
     * @param phoneNo
     */
    public void bindGetOtpCode(String phoneNo) {
        mModel.bindGetOtpCode(phoneNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.<CodeBean>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
                .subscribe(new ErrorHandleSubscriber<CodeBean>(mErrorHandler) {
                    @Override
                    public void onNext(CodeBean codeBean) {
                        mRootView.handleVerifyCode(codeBean);
                    }
                });
    }

    /**绑定微信密码
     * @param phoneNo
     * @param passwd
     */
    public void setPassword_1_0_0(String phoneNo, String passwd){
        mModel.setPassword_1_0_0(phoneNo,passwd)
                .subscribeOn(Schedulers.io())  /*http请求线程*/
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mRootView.showLoading();
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();
                    }
                })
                .subscribe(new ErrorHandleSubscriber<WeixinPassWordBindBean>(mErrorHandler) {
                    @Override
                    public void onNext(WeixinPassWordBindBean codeBean) {
                        Log.d(TAG, "WeixinPassWordBindBean:----------------------------> "+codeBean.toString());
                        UiUtils.makeText("密码绑定成功");
                        LoginBeen.DataBeen bean= new LoginBeen.DataBeen();
                        bean.setId(codeBean.getData().getId());
                        bean.setToken(codeBean.getData().getToken());
                        bean.setPhoneNo(codeBean.getData().getPhoneNo());
                        UserUtil.store(bean);
                        EventBus.getDefault().post(true, LoginActivity.EVENT_KEY_ISLOGIN);
                        mRootView.killMyself();
                    }
                });
    }


public void getAccessCode(String code){
   mModel.getWXAccessCode( code);
//           .subscribeOn(Schedulers.io())
//           .observeOn(AndroidSchedulers.mainThread())
//           .compose(RxUtils.<LoginModel.WxAccecCodeBean>bindToLifecycle(mRootView))//使用RXlifecycle,使subscription和activity一起销毁
//           .subscribe(new ErrorHandleSubscriber<LoginModel.WxAccecCodeBean>(mErrorHandler) {
//               @Override
//               public void onNext(LoginModel.WxAccecCodeBean codeBean) {
//
//                   SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_ACCESS_TOKEN,codeBean.getAccess_token());
//                   SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_OPEN_ID,codeBean.getOpenid());
//                   SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_UNIN_ID,codeBean.getUnionid());
//                   Timber.tag(TAG).d("-----------------> WeiXinACCESS = " + codeBean.getAccess_token().toString());
//                   EventBus.getDefault().post(true,WeiXinLoginBean.KEY_WEIXIN_ACCESS_TOKEN);
//               }
//           });
}
    //=========================================微信登录==============================
//====================================================


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }

}