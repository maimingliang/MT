package com.elk.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.elk.base.rxError.core.RxErrorHandler;
import com.elk.base.rxError.handler.listener.ResponseErroListener;

import timber.log.Timber;

public abstract class BaseApplication extends Application {

    protected static BaseApplication mApplication;
    protected final String TAG = this.getClass().getSimpleName();

    protected AppManager mAppManager;


    private static int mMainThreadId;
    private static Handler mHandler;
    private static RxErrorHandler sRxErrorHandler;

    /**
     * 返回上下文
     *
     * @return
     */
    public static Context getContext() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mMainThreadId = android.os.Process.myTid();
        mAppManager = new AppManager(this);
        mHandler = new Handler();
        sRxErrorHandler = RxErrorHandler
                .builder()
                .with(this)
                .responseErroListener(sResponseErroListener)
                .build();


    }


    private static ResponseErroListener sResponseErroListener = new ResponseErroListener() {
        @Override
        public void handleResponseError(Context context, Exception e) {
            Timber.e(e.getMessage());
            //            if (e instanceof UnauthorizedException) {
            //                UserUtil.clear();
            //                ARouter.getInstance().build("/touristUser/login").navigation();
            //            } else if (e instanceof SystemException) {
            //                UiUtils.makeText(OutputCode.EX.getMessage());
            //            } else if (e instanceof ParameterException) {
            //                UiUtils.makeText(OutputCode.PM.getMessage());
            //            } else if (e instanceof MessageException) {
            //                UiUtils.makeText(e.getMessage());
            //            }else if(e instanceof HttpException){
            //                UiUtils.makeText("网络不给力！请稍后重试");
            //            } else if (e instanceof ConnectException) {
            //                UiUtils.makeText("网络出错啦！请检查后重试");
            //                //                            UiUtils.makeText(e.getMessage());
            //            }else if (e instanceof RxCacheException) {
            //                UiUtils.makeText("无法连接到服务器获取数据");
            //                //                            UiUtils.makeText(e.getMessage());
            //            }else {
            //                UiUtils.makeText(OutputCode.EX.getMessage());
            //            }
        }
    };

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();

        if (mAppManager != null) {//释放资源
            this.mAppManager.release();
            this.mAppManager = null;
        }
        if (mApplication != null)
            mApplication = null;
    }



    public AppManager getAppManager() {
        return mAppManager;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static RxErrorHandler getRxErrorHandler(){
        return  sRxErrorHandler;
    }

}
