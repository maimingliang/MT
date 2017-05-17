package com.elk.tourist;

import android.app.Activity;

import com.elk.base.BaseFragment;
import com.elk.base.mvp.Presenter;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by jess on 8/5/16 14:11
 * contact with jess.yan.effort@gmail.com
 */
public abstract class WEFragment<P extends Presenter> extends BaseFragment<P> {
    protected WEApplication mWeApplication;

    @Override
    protected void inject() {
        mWeApplication = (WEApplication)mActivity.getApplication();
        setupFragmentComponent("ss");
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupFragmentComponent(Object o);
    private Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;
    }

    //得到可靠地Activity
    public Activity getSafeActivity() {
        return mActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = WEApplication.getRefWatcher(getActivity());//使用leakCanary检测fragment的内存泄漏
        if (watcher != null) {
            watcher.watch(this);
        }
        this.mWeApplication =null;
    }
}
