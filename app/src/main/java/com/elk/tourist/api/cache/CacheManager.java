package com.elk.tourist.api.cache;


import com.elk.base.http.BaseCacheManager;

/**
 * Created by jess on 8/30/16 13:54
 * Contact with jess.yan.effort@gmail.com
 */

public class CacheManager implements BaseCacheManager {
    private CommonCache mCommonCache;

    /**
     * 如果需要添加Cache只需在构造方法中添加对应的Cache,
     * 在提供get方法返回出去,只要在CacheModule提供了该Cache Dagger2会自行注入
     * @param commonCache
     */

    public CacheManager(CommonCache commonCache) {
        this.mCommonCache = commonCache;
    }

    public CommonCache getCommonCache() {
        return mCommonCache;
    }

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
