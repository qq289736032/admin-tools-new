package com.mokylin.cabal.common.constant;

import java.util.HashMap;
import java.util.Map;

public enum OperateType {

    // 以前遗留下来的
    CMD_SYSTEM(101, "GM指令", ConsumeYbType.NO_CONSUME_YB),
    GM_INCR(102, "后台发放", ConsumeYbType.NO_CONSUME_YB),
    GM_DECR(103, "后台回收", ConsumeYbType.NO_CONSUME_YB),
    BAG_OPEN(1011, "背包开格", ConsumeYbType.OPEN_BAG),
    STORE_OPEN(1012, "仓库开格", ConsumeYbType.OPEN_BAG),
    VIP_DAY_REWARD(1040, "VIP每日奖励", ConsumeYbType.NO_CONSUME_YB),
    VIP_WEEK_REWARD(1041, "VIP每周奖励", ConsumeYbType.NO_CONSUME_YB),
    BAG_DELETE(2020, "直接删除物品", ConsumeYbType.NO_CONSUME_YB),
    SHOUHU_RESET(2160, "守护神殿重置", ConsumeYbType.NO_CONSUME_YB),
    SHOUHU_PASSALL(2161, "守护神殿一键通关", ConsumeYbType.NO_CONSUME_YB),
    SHOUHU_BOXREWARD(2162, "守护神殿宝箱奖励", ConsumeYbType.NO_CONSUME_YB),
    SHOUHU_PASS_REWARD(2163, "守护神殿过关奖励", ConsumeYbType.NO_CONSUME_YB),
    SHOUHU_PASS(2167, "守护神殿通关", ConsumeYbType.SHOUHU),
    SAODANG_AWARD(3031, "女娲副本快速扫荡奖励", ConsumeYbType.NO_CONSUME_YB),
    XUNBAO(3081, "寻宝", ConsumeYbType.XUNBAO),
    XINXUNBAO(3083, "新寻宝", ConsumeYbType.XUNBAO),
    PROP_DUOXUANLIBAO_DECR(3171, "多选礼包消耗", ConsumeYbType.DUOXUANLIBAO),
    PROP_DUOXUANLIBAO_INCR(3172, "多选礼包获得", ConsumeYbType.NO_CONSUME_YB),
    PROP_YOUHUIQUAN_DECR(3173, "优惠券礼包消耗", ConsumeYbType.YOUHUILIBAO),
    PROP_YOUHUIQUAN_INCR(3174, "优惠券礼包获得", ConsumeYbType.NO_CONSUME_YB),
    EVERYDAY_EXCHANGE(4047, "每日兑换获得", ConsumeYbType.NO_CONSUME_YB),
    AUCTION_DECR(4101, "拍卖行失去道具", ConsumeYbType.NO_CONSUME_YB),
    AUCTION_SHANGJIA(4100, "拍卖行上架道具", ConsumeYbType.NO_CONSUME_YB),
    AUCTION_XIAJIA(4105, "拍卖行下架道具", ConsumeYbType.NO_CONSUME_YB),
    AUCTION_BUY(4102, "拍卖行购买道具", ConsumeYbType.ACUTION),
    AUCTION_PROFIT(4103, "领取拍卖行收益", ConsumeYbType.NO_CONSUME_YB),
    GOODS_BUYBACK_SELL(4120, "回购物品出售", ConsumeYbType.NO_CONSUME_YB),
    GOODS_BUYBACK_BUY(4121, "回购物品回收", ConsumeYbType.NO_CONSUME_YB),
    SHENMI_SHOP_BUY(5001, "神秘商店购买物品消耗", ConsumeYbType.SHENMI_SHOP_BUY),
    SHENMI_SHOP_EXCHANE(5002, "神秘商店积分兑换消耗", ConsumeYbType.NO_CONSUME_YB),
    SHENMI_SHOP_DIANQUAN_REFRESH(5003, "神秘商店点券刷新消耗", ConsumeYbType.SHENMI_SHOP_REFRESH),
    ONLINE_REWARD_BINDYB_GET(5012, "x获取在线奖励绑定", ConsumeYbType.NO_CONSUME_YB),
    QIANDAO_REWARD_GET(5013, "x获取签到奖励", ConsumeYbType.NO_CONSUME_YB),
    YAOJIANG_GET(5015, "x摇奖", ConsumeYbType.NO_CONSUME_YB),
    KAIFU_HUODONG_RANK_GET(5020, "开服七日活动排行获得", ConsumeYbType.NO_CONSUME_YB),
    KAIFU_HUODONG_SHOUCHONG_GET(5021, "首充活动获得", ConsumeYbType.NO_CONSUME_YB),
    LEIJI_XIAOFEI_RANK_GET(5022, "累计消费排行获得", ConsumeYbType.NO_CONSUME_YB),
    LEIJI_KILLMONSTER_RANK_GET(5023, "累计杀怪排行获得", ConsumeYbType.NO_CONSUME_YB),
    LEIJI_RECHARGE_RANK_GET(5024, "累计充值排行获得", ConsumeYbType.NO_CONSUME_YB),
    JINGJI_RANK_GET(5025, "竞技场排行获得", ConsumeYbType.NO_CONSUME_YB),
    HEFU_EVERYDAY_EXCHANGE(5032, "合服每日兑换获得", ConsumeYbType.NO_CONSUME_YB),
    HEFU_LIGIN_AWARD(5033, "合服七天登录获得", ConsumeYbType.NO_CONSUME_YB),
    HEFU_GUOZHANRANK_AWARD(5034, "合服国战排行获得", ConsumeYbType.NO_CONSUME_YB),
    HEFU_XUNBAORANK_AWARD(5036, "合服寻宝排行获得", ConsumeYbType.NO_CONSUME_YB),
    HEFU_RECHARGERANK_AWARD(5039, "合服战力排行获得", ConsumeYbType.NO_CONSUME_YB),
    HEFU_ZHUANPAN(5037, "合服转盘花元宝", ConsumeYbType.ZHUANPAN),
    FENDANG_RECHARGE_ARAWD(5040, "分挡充值奖励", ConsumeYbType.NO_CONSUME_YB),
    FENDANG_DUIHUAN_ARAWD(5041, "分挡兑换奖励", ConsumeYbType.NO_CONSUME_YB),
    LIMITTIME_MALL(5080, "限时商城", ConsumeYbType.LIMITTIME_MAIL),
    GUOQING_ONLINE(5115, "国庆在线奖励", ConsumeYbType.NO_CONSUME_YB),
    GUOQING_RECHARGE(5116, "国庆充值返利奖励", ConsumeYbType.NO_CONSUME_YB),
    COUNTYR_EXCHANGE(5120, "国家商店兑换", ConsumeYbType.NO_CONSUME_YB),
    GUOZHAN_AWARD(6000, "国战奖励", ConsumeYbType.NO_CONSUME_YB),
    GUOZHAN_BEIZHAN(6001, "国战备战", ConsumeYbType.NO_CONSUME_YB),
    PHONE_AWARD(6010, "手机验证获得奖励", ConsumeYbType.NO_CONSUME_YB),
    SHOUCANG_AWARD(6011, "收藏游戏获得奖励", ConsumeYbType.NO_CONSUME_YB),
    KUAFU_PVP_DAY_AWARD(6030, "跨服pvp每日奖励", ConsumeYbType.NO_CONSUME_YB),
    ROLL(6032, "roll点", ConsumeYbType.ROLL),
    KUAFU_PVP_SUCCESS(6040, "PVP匹配成功", ConsumeYbType.NO_CONSUME_YB),
    KUAFU_PVP_CANCEL(6041, "PVP取消匹配", ConsumeYbType.NO_CONSUME_YB),
    DUMMY_PROP_ADD(6050, "虚拟道具增加", ConsumeYbType.NO_CONSUME_YB),
    ZHONGSHENBAOXIANG(7003, "众神宝箱", ConsumeYbType.ZHONGSHENBAOXIANG),

    /**
     * 以上是之前的没有做整理
     */

    /**
     * 占时无日志的
     */

    XIANG_QIAN_BAOSHI(7018, "镶嵌宝石", ConsumeYbType.NO_CONSUME_YB), XIE_ZHAN_BAOSHI(7019, "卸载宝石", ConsumeYbType.NO_CONSUME_YB), DUOCAI_BAOSHI_HECHENG(7020, "多彩宝石合成", ConsumeYbType.NO_CONSUME_YB), BAOSHI_KUAISHU_HECHENG(7025, "宝石快速合成消耗", ConsumeYbType.NO_CONSUME_YB),

    /**
     * 不走统一的日志begin
     */
    ITEM_SPLIT(2024, "拆分物品", ConsumeYbType.NO_CONSUME_YB), 
    ITEM_DISCARD(2025, "丢弃物品", ConsumeYbType.NO_CONSUME_YB), 
    NORMAL(1, "resource_chage_log", ConsumeYbType.NO_CONSUME_YB), 
    CHARGE(1020, "游戏充值", ConsumeYbType.NO_CONSUME_YB), 
    ROLE_REAL_RECHARGE(5099, "玩家正常充值", ConsumeYbType.NO_CONSUME_YB), 
    GIFT_CODE_REWARD(2023, "激活码领取奖励", ConsumeYbType.NO_CONSUME_YB), 
    REVIEVE_USE(2050, "复活消耗", ConsumeYbType.RELIVE_CONSUME), 
    COLLECT_GET(2051, "采集获得", ConsumeYbType.NO_CONSUME_YB), 
    FIEXIE_FLY(2052, "飞鞋传送", ConsumeYbType.FEIXIE_FLY), 
    EQUIP_GETOFF(2080, "卸下装备", ConsumeYbType.NO_CONSUME_YB), 
    DEAD_DROP(2190, "死亡掉落", ConsumeYbType.NO_CONSUME_YB),
    /**
     * 不走统一的日志end
     */
    VIP_TEJIA(1042, "VIP礼包", ConsumeYbType.NO_CONSUME_YB),
    ACHIEVE_FINISH(2001, "成就达成", ConsumeYbType.NO_CONSUME_YB),
    SYSTEM_REWARD(2040, "系统奖励", ConsumeYbType.NO_CONSUME_YB),
    EQUIP_STRENGTH_USE(2088, "装备强化消耗", ConsumeYbType.NO_CONSUME_YB),
    HUSONG_SELECT(2101, "选择护送对象", ConsumeYbType.HUSONG_SELECT),
    HUSONG_SUCCESS_REWARD(2103, "护送成功收益", ConsumeYbType.NO_CONSUME_YB),
    SKILL_UPGRADE(2140, "角色技能升级", ConsumeYbType.NO_CONSUME_YB),
    LINGDIZHAN_REWARD_DAY(2170, "领地战每日奖励", ConsumeYbType.NO_CONSUME_YB),
    LINDIZHAN_OVER_REWARD(2171, "领地战结束奖励", ConsumeYbType.NO_CONSUME_YB),
    STAGE_PICKUP_YXB(2191, "拾取游戏币", ConsumeYbType.NO_CONSUME_YB),
    STAGE_PICKUP_GOODS(2192, "拾取物品", ConsumeYbType.NO_CONSUME_YB),
    MAIL(3021, "获取邮件中的物品", ConsumeYbType.EMAIL_GAIN_YB),
    RICHANG_INCR_THICK(3042, "日常&家族任务提升任务奖励", ConsumeYbType.NO_CONSUME_YB),
    RICHANG_ZHIJIE_FINISH_CURLOOP(3043, "日常&家族任务直接完成本环", ConsumeYbType.DAY_TASK),

    RICHANG_TASK_ZHIJIE_BEST_FINISH_ALL(3045, "日常&家族任务直接最优完成全部", ConsumeYbType.DAY_TASK),
    RICHANG_TASK_NORMAL_FINISH(3047, "日常&家族任务正常完成", ConsumeYbType.NO_CONSUME_YB),
    MAIN_TASK_DECR_GOODS(3061, "主线任务扣除道具", ConsumeYbType.NO_CONSUME_YB),
    ZHANGJIE_AWARD(3062, "主线任务章节奖励", ConsumeYbType.NO_CONSUME_YB),

    MAIN_TASK_COMPLETE(3063, "主线任务完成", ConsumeYbType.NO_CONSUME_YB),
    MAIN_TASK_RECEVIE(3064, "接受主线任务", ConsumeYbType.NO_CONSUME_YB),
    ZYZHUIHUI_ZHUIHUI(3111, "资源追回", ConsumeYbType.RECOVER_RESOURCE),
    GONGHUI_CREATE(3121, "创建家族扣除", ConsumeYbType.GUILD_CONSUME_YB),
    GONGHUI_GONGXIAN(3122, "家族贡献", ConsumeYbType.NO_CONSUME_YB),
    JINGJI_BUY_COUNT(3131, "竞技场购买竞技次数", ConsumeYbType.JINGJI_CONSUME_YB),
    JINGJI_CLEAR_CD(3132, "竞技场清除CD", ConsumeYbType.JINGJI_CONSUME_YB),
    JINGJI_TIAOZHAN_AWARD(3134, "竞技场挑战奖励", ConsumeYbType.NO_CONSUME_YB),

    JINGJI_RANK_AWARD(3136, "竞技场排名奖励", ConsumeYbType.NO_CONSUME_YB),
    HORN(3151, "喇叭聊天", ConsumeYbType.NO_CONSUME_YB),
    TEAMFUBEN_AWARD(3161, "组队副本奖励", ConsumeYbType.NO_CONSUME_YB),
    PROP_DECR(3175, "直接消耗道具", ConsumeYbType.NO_CONSUME_YB),
    PROP_DECR_YXB(3176, "直接消耗游戏币", ConsumeYbType.NO_CONSUME_YB),
    PROP_DECR_LIJIN(3177, "直接消耗游礼金", ConsumeYbType.NO_CONSUME_YB),
    PROP_OPEN_LIHE_DECR(3178, "打开礼盒消耗", ConsumeYbType.LIHE),
    PROP_OPEN_LIHE_INCR(3179, "打开礼盒获得", ConsumeYbType.NO_CONSUME_YB),
    PROP_YB_INCR(3182, "获取元宝", ConsumeYbType.NO_CONSUME_YB),
    GONGHUI_BOSS_OPEN(3221, "开启公会BOSS", ConsumeYbType.NO_CONSUME_YB),
    GONGHUI_BOSS_AWARD(3222, "公会BOSS奖励", ConsumeYbType.NO_CONSUME_YB),
    GONGHUI_BOSS_UPGRADE(3223, "公会BOSS升级", ConsumeYbType.NO_CONSUME_YB),
    VIPFUBEN_CHANLLENGE(3241, "vip副本挑战", ConsumeYbType.VIPCOPY_CHALLENGE),
    PRE_RECHARGE_BACK(3251, "预充值返现", ConsumeYbType.NO_CONSUME_YB),
    ROLE_CHANGE_NAME(3300, "更名卡-改名", ConsumeYbType.NO_CONSUME_YB),
    LEVEL_LIBAO_INCR(4041, "等级礼包", ConsumeYbType.NO_CONSUME_YB),
    LOGIN_AWARD(4046, "14天登录奖励", ConsumeYbType.NO_CONSUME_YB),
    ONLINE_REWARD(4048, "在线礼包", ConsumeYbType.NO_CONSUME_YB),
    DAYSHOUCHONG_INCR(4049, "每日首充", ConsumeYbType.DAYSHOUCHONG_CONSUME_YB),
    GERENFUBEN_REWATD_DECR(4052, "个人副本", ConsumeYbType.GERENFUBEN_REWATD_DECR),
    TOUXIAN_DECR(4071, "头衔消耗", ConsumeYbType.NO_CONSUME_YB),
    DUIHUAN_GOODS_INCR(4110, "藏宝阁物品兑换", ConsumeYbType.NO_CONSUME_YB),
    TRADE_INCR(4130, "交易获得物品", ConsumeYbType.TRADE_INCR),
    TRADE_DECR(4131, "交易失去物品", ConsumeYbType.TRADE_DECR),
    MALL_BUY_GOODS(4140, "商城购买", ConsumeYbType.ONLINE_SHOP),
    EXCHANGE_CODE(4160, "激活码兑换码", ConsumeYbType.NO_CONSUME_YB),
    WEIDUAN_REWARD(5011, "获取微端奖励", ConsumeYbType.NO_CONSUME_YB),
    GM_DELETE_ITEM(5140, "GM删除玩家道具", ConsumeYbType.NO_CONSUME_YB),
    RC_TASK_DOUBLE(7009, "日常任务多倍消耗", ConsumeYbType.RC_TASK_DOUBLE),
    YAOYI_PEYANG(7010, "资源线培养", ConsumeYbType.YAOYI_PEYANG),
    YAOYI_JINJIE(7011, "资源线进阶", ConsumeYbType.YAOYI_JINJIE),
    YAOYI_SKILL(7012, "资源线技能升级", ConsumeYbType.NO_CONSUME_YB),
    HUANHUA_ACTIVATE(7013, "幻化激活", ConsumeYbType.NO_CONSUME_YB),
    JIANGYAO_FENGYIN(7014, "降妖封印", ConsumeYbType.NO_CONSUME_YB),
    FULING_LEVEL(7015, "附灵升级", ConsumeYbType.FULING_LEVEL),
    FULING_STAR(7016, "附灵星级", ConsumeYbType.FULING_STAR),
    FULING_ACTIVATE(7017, "附灵激活", ConsumeYbType.FULING_ACTIVATE),
    COMPOUND_ITEM(7021, "材料合成", ConsumeYbType.NO_CONSUME_YB),
    EQUIP_LEVEL(7022, "装备提升星级", ConsumeYbType.NO_CONSUME_YB),
    EQUIP_TILIAN(7023, "装备提升星级", ConsumeYbType.NO_CONSUME_YB),
    JIHUO_FUWEN(7024, "激活符文", ConsumeYbType.NO_CONSUME_YB),
    PROP_DECR_ZHENQI(7026, "直接消耗真气", ConsumeYbType.NO_CONSUME_YB),
    FULING_BAIFENG(7027, "附灵百封", ConsumeYbType.FULING_ACTIVATE),
    QIFU(7028, "祈福奖励", ConsumeYbType.QIFU_CONSUME_YB), YB_JUHUASUAN(7029, "聚划算返利元宝", ConsumeYbType.YB_JUHUASUAN),

    JITIANQIFU(7030, "祭天祈福", ConsumeYbType.NO_CONSUME_YB),
    SHANGUSHILIAN(7031, "上古试炼", ConsumeYbType.NO_CONSUME_YB),
    WUJINSHENYUAN(7032, "无尽深渊", ConsumeYbType.NO_CONSUME_YB),
    TIAOZHANBOSS(7033, "挑战BOSS", ConsumeYbType.NO_CONSUME_YB),
    LIHE_EQUIP(7034, "礼盒根据等级开出装备", ConsumeYbType.NO_CONSUME_YB),
    LUNHUIMIJING_COUNT(7035, "轮回秘境击杀怪物奖励", ConsumeYbType.NO_CONSUME_YB),
    LUNHUIMIJING_ATTRIBUTE(7036, "轮回秘境购买属性加成", ConsumeYbType.LUNHUIMIJING_ATTRIBUTE_CONSUME_YB),
    MEIRI_QIANDAO(7037, "每日签到奖励", ConsumeYbType.NO_CONSUME_YB),
    CHONGZHI_SONGLI(7038, "充值送礼", ConsumeYbType.NO_CONSUME_YB),
    CHENGZHU_DAY(7039, "城主之战每日奖励", ConsumeYbType.NO_CONSUME_YB),
    QUANMINGCHENGBANG(7040, "全民冲榜", ConsumeYbType.NO_CONSUME_YB),
    XINGYUN_LUNPAN(7041, "充值送礼", ConsumeYbType.NO_CONSUME_YB),
    CHENGZHANG_TASK(7042, "成长任务", ConsumeYbType.NO_CONSUME_YB),
    DATI(7043, "答题", ConsumeYbType.NO_CONSUME_YB),
    LUNHUIMIJING_BUYCOUNT(7044, "轮回秘境购买重置次数", ConsumeYbType.LUNHUIMIJING_RESET_CONSUME_YB),
    EQUIP_PINGZHI(7045, "装备提升品质", ConsumeYbType.NO_CONSUME_YB),
    EQUIP_SHENGJI(7046, "装备提升级别", ConsumeYbType.NO_CONSUME_YB),
    ADD_JINLUN(7047, "添加经纶值", ConsumeYbType.NO_CONSUME_YB),
    SHOUJI_ZHUANGBEI(7048, "收集装备", ConsumeYbType.NO_CONSUME_YB),
    SHUAXIN_BIAOCHE(7049, "刷新镖车", ConsumeYbType.SHUAXINBIAOCHE_DECR),
    EQUIP_FENJIE_BIAOCHE(7050, "装备分解", ConsumeYbType.NO_CONSUME_YB),
    EQUIP_CHENGZHUANG(7051, "橙装兑换", ConsumeYbType.NO_CONSUME_YB),
    XIANSHI_ZHEKOU(7052, "限时折扣", ConsumeYbType.NO_CONSUME_YB),
    JUQINGFUBEN_REWARD(7053, "剧情副本", ConsumeYbType.NO_CONSUME_YB),
    XIANSHI_TEHUI(7054, "限时特惠", ConsumeYbType.NO_CONSUME_YB),
    KAIFU_LIBAO(7055, "开服礼包", ConsumeYbType.KAIFULIBAO_DECR),
    XIAOFEIDIAN_JINJIERI(7056, "消费点进阶日", ConsumeYbType.NO_CONSUME_YB),
    XIAOFEISONGLI_SONGLI(7057, "消费送礼", ConsumeYbType.NO_CONSUME_YB),
    JINGMAI_DECRYXB(7058, "经脉系统消耗银两", ConsumeYbType.NO_CONSUME_YB),
    HUSONG_REWARD(7059, "押镖成功获得银两", ConsumeYbType.NO_CONSUME_YB),
    ZHUANSHENG_MODULE(7060, "转生系统获得物品", ConsumeYbType.NO_CONSUME_YB),
    ZHUANSHENG_UPDATESKILL(7061, "转生系统升级技能", ConsumeYbType.NO_CONSUME_YB),
    SHANGGUZHILU(7062, "上古之路获得物品", ConsumeYbType.NO_CONSUME_YB),
    YUANBAOFANBEI_DECR(7063, "元宝翻倍消耗元宝", ConsumeYbType.YUANBAOFANBEI_DECR),
    YUANBAOFANBEI_INCR(7064, "元宝翻倍获得元宝", ConsumeYbType.NO_CONSUME_YB),
    

    LINGHUFUBEN(7065, "灵狐副本完成", ConsumeYbType.NO_CONSUME_YB),
    PLATFORM_TASK(7066, "Tencent市集任务", ConsumeYbType.NO_CONSUME_YB),

    BOSSTIAOZHAN_COMSUME(7067, "BOSS挑战消耗令牌", ConsumeYbType.NO_CONSUME_YB),
    HUANGZUAN_REWARD(7068, "黄钻领取奖励", ConsumeYbType.NO_CONSUME_YB),
    FAXIANG_ADD_TOUXIAN(7068, "法相升级,封神榜奖励称号", ConsumeYbType.FAXIANG_ADD_TOUXIAN),
    FABAO_SHENGJIE_DECR(7069, "法宝升阶", ConsumeYbType.FABAO_SHENGJIE_DECR),
    FAXIANG_JIANGLI(7070, "法相奖励,封神榜奖励", ConsumeYbType.NO_CONSUME_YB),
    BaiBei_FanLi(7071, "百倍返利", ConsumeYbType.TRADE_DECR),
	MeiRi_ShouChong(7072, "每日首充", ConsumeYbType.DAYSHOUCHONG_CONSUME_YB),
	QIRIHUODONG(7073, "七日活动", ConsumeYbType.QIRIHUODONG),
	ShouChongTuanGou(7074, "首充团购", ConsumeYbType.ShouChongTuanGou),
	ShouJiZhangBei_New(7075, "收集装备送时装", ConsumeYbType.ShouJiZhangBei_New),
	CUXIAO_HAOLI(7076,"促销豪礼",ConsumeYbType.LIMITTIME_MAIL),
	HONGBAO_FANLI(7077,"红包全反",ConsumeYbType.YUANBAO_NOJ),
	TIANZUN_CIFU(7078, "天尊赐福", ConsumeYbType.TIANZUN_XIAOHAO),
	JIUTIANMIBAO_MIYAOCHAOJIANG(7079, "九天秘宝秘钥消耗", ConsumeYbType.JIUTIANMIBAO_MIYAOXIAOHAO),
	JIUTIANMIBAO_LIPINFASONG(7080, "九天秘宝", ConsumeYbType.JIUTIANMIBAO_MIYAOXIAOHAO),
	SHOUCHONG_FANBEI(7081, "首冲翻倍", ConsumeYbType.SHOUCHONGFANBEI_SONGBANGYUAN),
	Operation_Activity_recharge(7082, "充值类运营活动", ConsumeYbType.NO_CONSUME_YB),
	Operation_Activity_cost(7083, "购买类运营活动", ConsumeYbType.Operation_Activity_Buy),
	Reset_JuQing_FuBen(8000, "重置剧情副本", ConsumeYbType.Reset_JuQing_FuBen),
	BuyEnergy(8100, "购买精力", ConsumeYbType.Buy_Energy),
	
	LANZUAN_REWARD(8101, "蓝钻领取奖励", ConsumeYbType.NO_CONSUME_YB),
    ;

    /**
     * 数据源编号
     */
    private int id;
    /**
     * 数据源描述
     */
    private String description;

    private ConsumeYbType type;

    private OperateType(int id, String description, ConsumeYbType type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    /**
     * 操作类型编号
     */
    public int getId() {
        return id;
    }

    /**
     * 数据源描述
     */
    public String getDescription() {
        return description;
    }

    public ConsumeYbType getType() {
        return type;
    }

    public static OperateType[] getOperateTypes() {
        return OperateType.values();
    }

    private static Map<Integer, OperateType> result = new HashMap<>();

    private static void checkData() {
        if (!result.isEmpty()) {
            return;
        }
        for (OperateType type : OperateType.values()) {
            result.put(type.getId(), type);
        }
    }

    public static OperateType getOperateType(int id) {
        checkData();
        return result.get(id);
    }

    /**
     * 是否为充值类型
     */
    public static boolean isCharge(OperateType operateType) {
        switch (operateType) {
        case CHARGE:
        case CMD_SYSTEM:
        default:
            return false;
        }
    }

    /**
     * 是否通知客户端
     */
    public static boolean isNotifyClient(OperateType operateType) {
        if (EQUIP_GETOFF.equals(operateType)) {
            return false;
        }
        return true;
    }

    /**
     * 是否需要抛事件(拍卖行和银行不计入消费和获得)
     */

    public static boolean isNeedPublishEvent(OperateType operateType) {
        if (AUCTION_XIAJIA.equals(operateType) || AUCTION_SHANGJIA.equals(operateType)
                        || AUCTION_DECR.equals(operateType) || AUCTION_BUY.equals(operateType)
                        || AUCTION_PROFIT.equals(operateType)) {
            return false;
        }
        return true;
    }

    /**
     * 是否是进阶事件 是进阶的，日志数据前三位：进阶前等级，进阶后等级，进阶物品名称
     */
    public static boolean isJinjieType(int id) {
        OperateType type = getOperateType(id);
        return false;
    }

    /**
     * 是否是接受任务
     */
    public static boolean isTaskRecevie(int intType) {
        OperateType type = getOperateType(intType);
        if (type == null) {
            return false;
        }
        boolean flag = false;
        switch (type) {
        case MAIN_TASK_RECEVIE:
            flag = true;
            break;
        default:
            break;
        }

        return flag;
    }

    /**
     * 是否是取消任务
     */
    public static boolean isTaskCancel(int intType) {
        // OperateType type = getOperateType(intType);
        boolean flag = false;
        return flag;
    }

    /**
     * 是否是任务完成
     */
    public static boolean isTaskFinish(int intType) {
        OperateType type = getOperateType(intType);

        if (type == null) {
            return false;
        }
        boolean flag = false;
        switch (type) {
        case MAIN_TASK_COMPLETE:
            flag = true;
            break;
        default:
            break;
        }

        return flag;
    }

    /**
     * 获得消耗元宝的资源线
     *
     * @param intType
     * @return
     */
    public static int getResourceLine(int intType) {
        OperateType type = getOperateType(intType);
        return type.getType().getId();
    }

    /**
     * 聚划算操作类型
     */
    public static OperateType getJuhuaSuanOperateType(int libaoId) {
        switch (libaoId) {

        default:
            throw new UnknownError("unknown libaoId intType!!!! --- " + libaoId);
        }
    }

}
