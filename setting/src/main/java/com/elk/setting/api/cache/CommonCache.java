package com.elk.setting.api.cache;


/**注释使用解释：
 * @LiftCahe用来定义缓存的生命周期，duration参数设置过期时长
 *
 *@EncryptKey & @Encrypt 用于缓存加密
 * EncryptKey：作用于接口，接口下所有方法使用同一个Key加密
 * Encrypt：作用于方法，方法中使用这个key
 *
 * @Expirable 避免缓存被回收
 *
 * @SchemeMigration & @Migration 用于数据迁移
 * 参数解释：
 *
 * DynamicKey&DynamicKeyGroup用于缓存分页数据
 *DynamicKey：只要传入页数就可以了
 * DynamicKeyGroup：在构造方法中第一参数传入页数，第二个参数传用户标识。可以根据不同的用户返回不同的页面数据
 *
 * EvictProvider & EvictDynamicKey & EvictDynamicKeyGroup用于清除缓存
 * 不传入参数，默认设置为false，保存所有缓存
 * 设置为true，删除缓存
 * EvictProvider：删除所有缓存
 * EvictDynamicKey：删除某个分页面下的缓存
 * EvictDynamicKeyGroup：删除对应页面，对应用户的缓存
 *
 *
 * ==============================================================
 * Created by jess on 8/30/16 13:53
 * Contact with jess.yan.effort@gmail.com
 */
public interface CommonCache {

//=================================================首页====================================

//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<HomeGuide> getGuideList(Observable<HomeGuide> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取首页列表
//    Observable<HomeTravelJournalBean> getHomeTravelNotList(Observable<HomeTravelJournalBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取旅游志列表
//    Observable<TravelNoteBean> getTravelJournalList(Observable<TravelNoteBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取吐槽列表
//    Observable<SpiltslotBean> getSpitslotList(Observable<SpiltslotBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取吐槽详情列表
//    Observable<SpitslotDetailBean> getSpitslotDetail(Observable<SpitslotDetailBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取问问列表
//    Observable<WenwenBean> getWenwenList(Observable<WenwenBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//
//    //=================================================首页====================================
//
//
////=================================================发现====================================
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<FoodBeen> getFoodList(Observable<FoodBeen> FootList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<LoginBeen> getIdData(Observable<LoginBeen> IdData, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<HotelListBeen> getHotelList(Observable<HotelListBeen> HotelList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<TravelEssayBean> getTravelEssayList(Observable<TravelEssayBean> HotelList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
////=================================================发现====================================
//
//
//
//
//
//
//
////=================================================我的====================================
////=======================================我的收藏==========================
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<MineCollectionHotelBeen> getMineCollectionHotelList(Observable<MineCollectionHotelBeen> mineCollectionList, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<MineCollectionScenicBeen> getMineCollectionScenicList(Observable<MineCollectionScenicBeen> mineCollectionList, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<MineCollectionFoodBean> getMineCollectionFoodList(Observable<MineCollectionFoodBean> mineCollectionList, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<MineCollectionGuideBeen> getMineCollectionGuideList(Observable<MineCollectionGuideBeen> mineCollectionList, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
//    Observable<MineColllectionDairyBean> getMineCollectionDairyList(Observable<MineColllectionDairyBean> mineCollectionList, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
////=======================================我的收藏==========================
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取我的吐槽列表
//    Observable<MineSpiltslotBean> getMimeSpitslotList(Observable<MineSpiltslotBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取我的问问我的发布列表
//    Observable<MineWenwenPublishListBean> getWenwenPublishList(Observable<MineWenwenPublishListBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);
//
//    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)//获取我的问问我的抢答列表
//    Observable<MineWenwenAnswerListBean> getWenwenAnswerList(Observable<MineWenwenAnswerListBean> oHomeList, DynamicKey idLastUserQueried, EvictProvider evictProvider);

//=================================================我的====================================


}
