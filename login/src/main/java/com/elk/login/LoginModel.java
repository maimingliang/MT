package com.elk.login;

import com.elk.base.HttpResult;
import com.elk.base.LoginBeen;
import com.elk.base.WeiXinLoginBean;
import com.elk.base.mvp.BaseModel;
import com.elk.base.retrofit.CommonRetrofit;
import com.elk.login.api.cache.CacheManager;
import com.elk.login.api.service.LoginSevice;
import com.elk.login.api.service.ServiceManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;


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
 *date   2017/1/21
 *author lixuan
 */


public class LoginModel extends BaseModel<ServiceManager, CacheManager> implements LoginContract.Model ,LoginContract.WeixinModel{
    private static final String TAG = "LoginModel";

    private Retrofit mRetrofit_weixin;
//    private ApiService mApiService;



    public LoginModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weixin.qq.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//新的配置
                .build();
//        mApiService = retrofit.create(ApiService.class);

    }


    /**正常登陆
     * @param userID
     * @param passWorld
     * @return
     */
    public Observable<LoginBeen.DataBeen> login(String userID, String passWorld) {
        LoginSevice loginSevice = CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class);

       return loginSevice.login(userID,passWorld).flatMap(new Func1<LoginBeen, Observable<LoginBeen.DataBeen>>() {
            @Override
            public Observable<LoginBeen.DataBeen> call(LoginBeen loginBeen) {
                 return Observable.just(loginBeen.getData());
            }
        });

    }

    /**注册
     * @param userID
     * @param passWord
     * @param code
     * @param accType
     * @return
     */
    public Observable<RegisterEntity> register(String userID, String passWord, String code,int accType) {
        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).register(userID, passWord, code, accType);

    }

    /**微信绑定获取验证码
     * @param phoneNo
     * @return
     */
    public Observable<CodeBean> bindGetOtpCode(String phoneNo) {
        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).bindGetOtpCode(phoneNo);
     }


    /**未注册手机获取验证码
     * @param phoneNo
     * @return
     */
    public Observable<CodeBean> getUnRegisterVerifCode(String phoneNo) {
        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).registerGetOtpCode(phoneNo);
     }

    /**已注册手机获取验证码
     * @param phoneNo
     * @return
     */
    public Observable<CodeBean> getHasResideredVerifCode(String phoneNo) {
        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).registeredGetOtpCode(phoneNo);

    }

    /**忘记密码
     * @param phoneNo
     * @return
     */
    public Observable<CodeBean> modifyPassword(String phoneNo , String passwd, String code) {
        return null;
//        return mServiceManager .getMTouristUserService().modifyPassword(phoneNo,passwd,code)
//                .map(new Func1<HttpResult<String>, CodeBean>() {
//            @Override
//            public CodeBean call(HttpResult<String> stringHttpResult) {
//                CodeBean bean=new CodeBean();
//                bean.setCode(stringHttpResult.getCode());
//                return bean;
//            }
//        });
    }

    /**账号登出
     * @param token
     * @return
     */
    @Override
    public Observable<HttpResult<String>> logOut(String token) {
        return null;
//        return mServiceManager .getMTouristUserService().logout(token);
    }

    /**微信登录
     * @param
     * @param
     * @return
     */
    @Override
    public Observable<WeiXinLoginBean> weChatLogin_1_0_0(String weChatOpenId, String weChatUnionId) {

        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).weChatLogin_1_0_0(weChatOpenId, weChatUnionId);
     }

    @Override
    public Observable<PhoneNumBindBean> bindPhoneNo_1_0_0(String accessToken, String weChatOpenId, String weChatUnionId, String code, String phoneNo) {

        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).bindPhoneNo_1_0_0(accessToken, weChatOpenId, weChatUnionId, code, phoneNo);

     }

    @Override
    public Observable<WeixinPassWordBindBean> setPassword_1_0_0(String phoneNo, String passwd) {
        return CommonRetrofit.getInstance().getRetrofit().create(LoginSevice.class).setPassword_1_0_0(phoneNo, passwd);

    }


    public void getWXAccessCode(String code) {
 //        UiUtils.makeText("正在回调微信请求码 . . .");
//     Call<WxAccecCodeBean> call= mApiService.getACCESS_TOKEN("wxe40fa01c8aeccec2", "dcedbc1d96e639cbf4da159465d46f41",code,"authorization_code");
//         call.enqueue(new Callback<WxAccecCodeBean>() {
//             @Override
//             public void onResponse(Call<WxAccecCodeBean> call, Response<WxAccecCodeBean> response) {
//                 Log.d(TAG, "onResponse: body====================" + response.body().toString());
//                 Log.d(TAG, "request: url====================" + call.request().url());
//               if (response.body().getErrcode()==null){
//                   SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_ACCESS_TOKEN,response.body().getAccess_token());
//						SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_OPEN_ID,response.body().getOpenid());
//						SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_UNIN_ID,response.body().getUnionid());
//						SharedPreferencesUtil.setString(UiUtils.getContext(), WeiXinLoginBean.KEY_WEIXIN_REFRESH_ACCESS_CODE,response.body().getRefresh_token());
//                   Log.d(TAG, "request: ==================== Code get!!!!" );
//                    //  启动微信登录
//                   EventBus.getDefault().post(true,WeiXinLoginBean.KEY_WEIXIN_ACCESS_TOKEN);
//               }
//
//             }
//
//             @Override
//             public void onFailure(Call<WxAccecCodeBean> call, Throwable t) {
//
//             }

//         });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

//    public class WxAccecCodeBean{
//
//        /**
//         * access_token : ACCESS_TOKEN
//         * expires_in : 7200
//         * refresh_token : REFRESH_TOKEN
//         * openid : OPENID
//         * scope : SCOPE
//         * unionid : o6_bmasdasdsad6_2sgVt7hMZOPfL
//         */
//        private String errcode;
//        private String errmsg;
//        private String access_token;
//        private int expires_in;
//        private String refresh_token;
//        private String openid;
//        private String scope;
//        private String unionid;
//
//        @Override
//        public String toString() {
//            return "WxAccecCodeBean{" +
//                    "errcode='" + errcode + '\'' +
//                    ", errmsg='" + errmsg + '\'' +
//                    ", access_token='" + access_token + '\'' +
//                    ", expires_in=" + expires_in +
//                    ", refresh_token='" + refresh_token + '\'' +
//                    ", openid='" + openid + '\'' +
//                    ", scope='" + scope + '\'' +
//                    ", unionid='" + unionid + '\'' +
//                    '}';
//        }
//    }

//    public interface ApiService {
//        @GET("sns/oauth2/access_token")
//        Call<WxAccecCodeBean> getACCESS_TOKEN(
//                @Query("appid") String appid,
//                @Query("secret") String secret,
//                @Query("code") String code,
//                @Query("grant_type") String grant_type
//        );
//
////        @GET("sns/oauth2/access_token")
////        Observable<WxAccecCodeBean> getACCESS_TOKEN(
////                @Body RequestBody body
////        );
//    }

}