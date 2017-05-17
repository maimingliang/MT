package com.elk.tourist;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.elk.base.BaseApplication;
import com.elk.base.IModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import timber.log.Timber;

/**
 * Created by jess on 8/5/16 11:07
 * contact with jess.yan.effort@gmail.com
 */
public class WEApplication extends BaseApplication {

    private RefWatcher mRefWatcher;//leakCanary观察器

    @Override
    public void onCreate() {
        super.onCreate();


//        if(BuildConfig.LOG_DEBUG){

//            CrashHandler crashHandler = CrashHandler.getInstance();
//            crashHandler.init(this);
//        }




        if (BuildConfig.LOG_DEBUG) {//Timber日志打印
            Timber.plant(new Timber.DebugTree());
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }




        installLeakCanary();//leakCanary内存泄露检查

        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化


        //初始化module

        long l = System.currentTimeMillis();

        for(String name : ModuleConfig.moduleList){
            try {
                Class<?> clazz = Class.forName(name);
                Object instance = clazz.newInstance();

                if(clazz.newInstance() instanceof IModule){
                    Method method = clazz.getDeclaredMethod("onLoad", Application.class);
                    method.invoke(instance,this);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        Timber.tag(TAG).d("----- time = " +(System.currentTimeMillis() - l));
    }




    @Override
    public void onTerminate() {
        super.onTerminate();

        if (mRefWatcher != null)
            this.mRefWatcher = null;
    }

    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        WEApplication application = (WEApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

}
