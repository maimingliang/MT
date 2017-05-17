package com.elk.setting.api.service;


import com.elk.base.http.BaseServiceManager;

/**
 * Created by jess on 8/5/16 13:01
 * contact with jess.yan.effort@gmail.com
 */

public class ServiceManager implements BaseServiceManager {

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
