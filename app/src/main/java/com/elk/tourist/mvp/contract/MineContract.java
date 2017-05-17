package com.elk.tourist.mvp.contract;


import com.elk.base.mvp.BaseView;
import com.elk.base.mvp.IModel;
import com.elk.tourist.bean.MineBean;

import rx.Observable;

/**
 * 类       名:   我的契约
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/2/24
 * author   tanxiang(monster)
 */
public interface MineContract {

    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        void setData(MineBean.DataEntity data);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<MineBean> my_1_0_0();
    }
}
