package com.elk.login;

import com.elk.base.BaseApplication;

import timber.log.Timber;

/**
 * /**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2017
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/5/11
 * author   maimingliang
 */


public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
