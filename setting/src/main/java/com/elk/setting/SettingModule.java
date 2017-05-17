package com.elk.setting;

import android.app.Application;

import com.elk.base.IModule;

import timber.log.Timber;

/**
 * /**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2017
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/5/17
 * author   maimingliang
 */


public class SettingModule implements IModule {
    @Override
    public void onLoad(Application app) {
        Timber.tag("SettingModule").d("-------> SettingModule ");
    }
}
