package com.elk.tourist.mvp.model;


import com.elk.base.mvp.BaseModel;
import com.elk.base.retrofit.CommonRetrofit;
import com.elk.tourist.api.cache.CacheManager;
import com.elk.tourist.api.service.ServiceManager;
import com.elk.tourist.api.service.TouristUserService;
import com.elk.tourist.bean.MineBean;
import com.elk.tourist.mvp.contract.MineContract;

import rx.Observable;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * 类 名: 我的模型
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 *date   2017/2/7
 *author lixuan
 */


public class MineModel extends BaseModel<ServiceManager, CacheManager> implements MineContract.Model {


    public MineModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    @Override
    public Observable<MineBean> my_1_0_0() {

        TouristUserService touristUserService = CommonRetrofit.getInstance().getRetrofit().create(TouristUserService.class);

        Observable<MineBean> mineBeanObservable = touristUserService.my_1_0_0("209f23a4-3d77-4ee3-a22f-7d3774d17e3f", "ME0wNjE4MTE3Njk1MzA0ODM4NDIwOWYyM2E0LTNkNzctNGVlMy1hMjJmLTdkMzc3NGQxN2UzZg==N");

        return mineBeanObservable;
    }

}