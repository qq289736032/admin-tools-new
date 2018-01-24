package com.mokylin.cabal.common.constant;

public enum ConsumeYbType {

    NO_CONSUME_YB(1111, "不花费元宝"), //
    SHENMI_SHOP_BUY(1500, "神秘商店购买"), //
    SHENMI_SHOP_REFRESH(1501, "神秘商店刷新"), //
    LIMITTIME_MAIL(1600, "限时商城"), //
    LIHE(1700, "礼盒"), //
    ONLINE_SHOP(2000, "商城花费"), //
    XUNBAO(2100, "寻宝花费"), //
    DUOXUANLIBAO(2200, "多选礼包"), //
    YOUHUILIBAO(2300, "优惠券礼包"), //
    VIPCOPY_CHALLENGE(4000, "VIP副本挑战"), //
    JINGJI_CONSUME_YB(5000, "竞技场花费元宝"), //
    OPEN_BAG(6000, "背包开格"), //
    RELIVE_CONSUME(7000, "复活消耗"), //
    RECOVER_RESOURCE(10100, "资源追回"), //
    DAY_TASK(20100, "完成日常&家族任务"), //
    ZHUANPAN(21400, "转盘"), //
    ACUTION(30000, "拍卖行"), //
    FEIXIE_FLY(40700, "飞鞋传送"), //
    HUSONG_SELECT(41001, "护送选择"), //
    SHOUHU(42001, "守护神殿"), //
    ROLL(42400, "roll点"), //
    ZHONGSHENBAOXIANG(47500, "众神宝箱"), //
    RC_TASK_DOUBLE(48300, "日常任务多倍"), //
    YAOYI_PEYANG(48400, "资源线培养"), //
    YAOYI_JINJIE(48401, "资源线进阶"), //
    FULING_LEVEL(48700, "附灵升级"), //
    FULING_STAR(48701, "附灵星级"), //
    FULING_ACTIVATE(48702, "附灵激活"), //
    QIFU_CONSUME_YB(48800, "祈福花费元宝"), //
    LUNHUIMIJING_ATTRIBUTE_CONSUME_YB(48900, "轮回秘境购买属性"), //
    LUNHUIMIJING_RESET_CONSUME_YB(48901, "轮回秘境购买重置次数"), //
    CHONGZHISLONGLI_CONSUME_YB(49000, "充值送礼获得元宝"), //
    DAYSHOUCHONG_CONSUME_YB(49001, "每日首充花费元宝"), //
    EMAIL_GAIN_YB(49004, "邮件获得元宝"), //
    GUILD_CONSUME_YB(49005, "创建公会消耗元宝"), //
    TRADE_INCR(49006, "交易获得元宝"), //
    TRADE_DECR(49007, "交易失去元宝"), //
    YB_JUHUASUAN(49008, "聚划算返利元宝"), //
    GERENFUBEN_REWATD_DECR(49010, "个人副本在领一次"), //
    SHUAXINBIAOCHE_DECR(49011, "刷新镖车消耗元宝"), //
    XIANSHIZHEKOU_DECR(49012, "限时折扣购买物品消耗元宝"), //
    KAIFULIBAO_DECR(49013, "开服礼包购买消耗元宝"), //
    YUANBAOFANBEI_DECR(49014, "开服礼包购买消耗元宝"),
	YUANBAO_NOJ(49015, "非聚划算的所有元宝消耗"),
	TIANZUN_XIAOHAO(49016, "天尊赐福元宝消耗"),
	JIUTIANMIBAO_MIYAOXIAOHAO(49017,"九天秘宝秘钥消耗"),	
	FABAO_SHENGJIE_DECR(49018, "法宝升阶"),
	FAXIANG_ADD_TOUXIAN(49019, "法相升级,封神榜奖励称号"),
	QIRIHUODONG(49020, "七日活动"),
	ShouChongTuanGou(49021, "首充团购"),
	ShouJiZhangBei_New(49022, "收集装备送时装"),
	SHOUCHONGFANBEI_SONGBANGYUAN(49023, "首充翻倍"),
	Operation_Activity_Buy(49024, "购买类运营活动"),
	Reset_JuQing_FuBen(49100, "重置剧情副本"),
	Buy_Energy(49200, "购买精力"),
	
	
	;
	

    /**
     * 数据源编号
     */
    private int id;
    /**
     * 数据源描述
     */
    private String description;

    private ConsumeYbType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
