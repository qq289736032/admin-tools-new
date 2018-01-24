package com.mokylin.cabal.common.core;

public abstract class ErrorCode {
    public static final String _OK = "OK";
    // 空数组
    public static final Object[] EMPTY_ARRAY = new Object[] {};

    /** 失败 */
    public static final int EC_FAILED = 0;
    public static final Object[] FAILED = new Object[] { 0 };

    /** 成功 */
    public static final int EC_OK = 1;
    public static final Object[] OK = new Object[] { 1 };

    /** 角色找不到 */
    public static final int EC_NOT_FIND_USER = 2;

    /** 订单已存在 */
    public static final int EC_ORDER_EXIST = 3;

    /** 没有登录 */
    public static final int EC_NOT_LOGIN = -110;

    /** 生成动态error code */
    public static Object[] create(int code, Object... args) {
        int length = args.length;
        Object[] ret = new Object[2 + length];
        ret[0] = 0;
        ret[1] = code;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                ret[2 + i] = args[i];
            }
        }
        return ret;
    }

    public static boolean isOK(Object[] code) {
        boolean ret = false;
        if ((int) (code[0]) != ErrorCode.EC_FAILED) {
            ret = true;
        }
        return ret;
    }

    // ***********************************************通用模块*********************************
    /** 很抱歉,角色等级不足 */
    public static final Integer LEVEL_NOT_ENOUGH = 1000;
    /** 需要角色达到{0}级 */
    public static final Integer LEVEL_NEED = 1001;
    /** 很抱歉,角色钻石不足 */
    public static final Object[] YB_NOT_ENOUGH = new Object[] { 0, 1002 };
    /** 很抱歉,角色游戏币不足 */
    public static final Object[] JB_NOT_ENOUGH = new Object[] { 0, 1003 };
    /** 很抱歉,角色绑定钻石不足 */
    public static final Object[] BINDYB_NOT_ENOUGH = new Object[] { 0, 1004 };
    /** 很抱歉,所需物品不足 */
    public static final Object[] GOODS_NOT_ENOUGH = new Object[] { 0, 1005 };
    /** 很抱歉,背包格位不足 */
    public static final Object[] BAG_LESS = new Object[] { 0, 1006 };
    /** 很抱歉, 角色绑定游戏币不足 */
    public static final Object[] BIND_COIN_NOT_ENOUGH = new Object[] { 0, 1007 };
    // /** 很抱歉,物品已过期 */
    // public static final Integer GOODS_EXPIRE = 1007;
    /** 很抱歉,物品不存在 */
    public static final Object[] GOODS_INEXISTENCE = new Object[] { 0, 1008 };
    /** 虚拟货币血色精魄不足 */
    public static final Object[] VIRTUAL_MONEY_104_NOET_ENOUGH = new Object[] { 0, 1009 };
    // /** 不是VIP */
    // public static final Object[] ROLE_1010 = new Object[] { 0, 1010 };
    /** 很抱歉,角色vip等级不足 */
    public static final Object[] VIP_LEVEL_NOT_ENOUGH = new Object[] { 0, 1011 };
    /** 复活所需物品不足 */
    public static final Object[] NO_REVIVE_GOODS = new Object[] { 0, 1012 };
    // /** 1013 坐骑等级不足 */
    // public static final Integer ZUOQI_RANK_NOT_ENOUGH = 1013;
    // /** 1014 角色经验不足 */
    // public static final Integer ROLE_EXP_NOT_ENOUGH = 1014;
    /** 声望不足 */
    public static final Object[] PRESTIGE_NOT_ENOUGH = new Object[] { 0, 1015 };
    /** 荣誉点不足 */
    public static final Object[] CONTRIBUTION_NOT_ENOUGH = new Object[] { 0, 1016 };
    /** 系统中不存在对应的模板数据 */
    public static final Object[] NO_SYS_TEMPLATE = new Object[] { 0, 1017 };
    /** 战斗力不足 */
    public static final Object[] POWER_NOT_ENOUGH = new Object[] { 0, 1018 };

    /** 非法数据 (数量太大) */
    public static final Object[] INVALID_DATA = new Object[] { 0, 1019 };

    public static final Object[] SEARCHCOIN_NOT_ENOUGHT = new Object[] { 0, 1020 };
    /** 当前操作在跨服状态下不允许 */
    public static final Object[] CROSS_HANDLE_DATA_NOT_ALLOW = new Object[] { 0, 1021 };
    /** 很抱歉，虚拟货币不足 */
    public static final Object[] VIRTUAL_MONEY_NOT_ENOUGH = new Object[] { 0, 1023 };

    /** 很抱歉，您当前正在跨服中，不可以执行当前操作。 */
    public static final Object[] CrossStateNotDoSomething = new Object[] { 0, 1024 };
    /** 非法数据,输入参数的请求页码错误 */
    public static final Object[] INPUT_PAGE_NUM_ERROR = new Object[] { 0, 1025 };
    /** 有CD的道具,不能进行全部使用操作 */
    public static final Object[] ERROR_CODE_1026 = new Object[] { 0, 1026 };
    /** 死亡状态下不能释放技能,请下线 */
    public static final Object[] ERROR_CODE_1027 = new Object[] { 0, 1027 };
    /** 跨服服务器[{0}]正在维护中,请稍后再次尝试... */
    public static final int ERROR_CODE_1028 = 1028;
    /** 国家系统配置环境异常 */
    public static final Object[] ERROR_CODE_1029 = new Object[] { 0, 1029 };
    /** 跨服服务器开始维护，你已被请回本服. */
    public static final Object[] ERROR_CODE_1030 = new Object[] { 1030 };
    /** 很抱歉，当前地图不可以直接传送 */
    public static final Object[] ERROR_CODE_1031 = new Object[] { 0, 1031 };
    /** CD还在冷却中 */
    public static final Object[] ERROR_CODE_1032 = new Object[] { 0, 1032 };
    /** 1033 该系统维护中，请稍后再试 */
    public static final Object[] ERROR_CODE_1033 = new Object[] { 0, 1033 };
    // ***********************************************账号系统*********************************
    /** 角色名称不合法 */
    public static final Object[] ROLE_NAME_NO_ALLOW = new Object[] { 0, 2001 };
    /** 角色名称已经存在 */
    public static final Object[] NAME_REPEATED = new Object[] { 0, 2002 };
    /** 对不起，玩家不在线 */
    public static final Object[] ROLE_OFFLINE = new Object[] { 0, 2003 };
    /** 一个账号下已经存在角色 */
    public static final Object[] ROLE_EXIST_SAME_USERID = new Object[] { 0, 2005 };
    // /** 账号不存在 */
    // public static final Integer NO_ACCOUNT = 2006;
    // --------------------------------系统使用ErrorCode----------------------------

    /** 服务器升级维护中，请耐心等待 */
    public static final Object[] SERVER_MAINTAINING = new Object[] { 0, 2008 };
    /** 亲，你又调皮了,请重新登录 */
    public static final Object[] REQUEST_NOT_ACCEPTED = new Object[] { 0, 2009 };
    /** 名字字符过长，请重新输入 */
    public static final Object[] NAME_TOO_LONG = new Object[] { 0, 2010 };
    /** 验证所需签名错误 */
    public static final Object[] CHECK_FAILED = new Object[] { 0, 2011 };
    /** 游戏区{0}不存在 */
    public static final int SERVER_NOT_EXIST = 2012;
    /** 游戏区{0}维护中，请稍等再试 */
    public static final int SERVER_MAINTAIN = 2013;
    /** 2014 角色已经删除，不用重复删除 */
    public static final Object[] DELETE_FAIL_CAUSE_HAS_DELETED = new Object[] { 0, 2014 };
    /** 2015 角色没有删除，不用恢复 */
    public static final Object[] RESTORE_FAIL_CAUSE_NORMAL_STATE = new Object[] { 0, 2015 };
    /** 2016 角色删除超过了24小时，不可以恢复 */
    public static final Object[] RESTORE_FAIL_CAUSE_DELETE_TOO_LONG = new Object[] { 0, 2016 };
    /** 2017 此角色为公会会长，必须将会长转让给其他会员或解散公会，才可以删除角色. */
    public static final Object[] DELETE_FAIL_CAUSE_GUILD_PRESIDENT = new Object[] { 0, 2017 };
    /** 2018 此角色为跨服争霸的国王，不可以删除角色 */
    public static final Object[] DELETE_FAIL_CAUSE_KING = new Object[] { 0, 2018 };

    /** 兑换NPC声望的物品中，存在不接受的物品 */
    public static final Object[] CONTRIBUTION_ITEM_FAIL = new Object[] { 0, 2200 };
    /** 尚未完成{0}任务，不可以兑换NPC声望 */
    public static final int CONTRIBUTION_WITHOUT_TASK = 2201;
    /** 兑换NPC声望达到上限 */
    public static final Object[] ERROR_2202 = new Object[] { 0, 2202 };

    /** 2203 尚未完成{0}任务，不可以领取章节奖励 */
    public static final int GAIN_CHAPTER_AWARDS_WIGHOUT_TASK = 2203;
    /** 2204 已经领取过该章节奖励了 */
    public static final Object[] GAIN_CHAPTER_AWARDS_DUPLICATED = new Object[] { 0, 2204 };
    /** 2205 没有对应的章节奖励 */
    public static final Object[] GAIN_CHAPTER_AWARDS_NOT_FOUND = new Object[] { 0, 2205 };

    // ********************************************充值系统*********************************
    // 充值系统
    /** 暂时不能充值,{0} */
    public final static int CANT_CHARGE = 3001;
    /** 充值用的道具数据不存在 */
    public final static int TEMPLATE_NOT_EXISTED = 3002;
    /** 加游戏币失败 */
    public final static int CANT_ADD_DIAMOND = 3003;
    /** 3004 充值系统维护中，请稍后再试 */
    public final static Object[] CHARGE_MAINTAINED = new Object[] { 0, 3004 };
    /** 暂时不能领取开通礼包,{0} */
    public final static int CANT_DONATE = 3005;

    /** token过期或者不存在 ID可以和我们自己的重复 */
    public final static int TENCENT_TOKEN_INVALID = 1005;

    // /** 充值小于零 */
    // public final static int LESS_THAN_ZERO = 3001;
    // /** 充值业务异常 */
    // public final static int BUS_EXCEPTION = 3002;
    // /** 订单号重复 */
    // public final static int ORDER_REPEAT = 3003;
    // ***********************************************装备系统*********************************
    /** 装备的物品不存在 */
    public static final Object[] EQUIP_ERROE_31001 = new Object[] { 0, 31001 };
    /** 佩戴异常 */
    public static final Object[] EQUIP_ERROE_31002 = new Object[] { 0, 31002 };
    // /** 背包格位不足 */
    // public static final Integer EQUIP_ERROE_31003 = 31003;
    /** 物品不存在 */
    public static final Object[] ERROR_31004 = new Object[] { 0, 31004 };
    /** 此物品不是装备 */
    public static final Object[] ERROR_31005 = new Object[] { 0, 31005 };
    /** 装备佩戴槽位不正确 */
    public static final Object[] ERROR_31006 = new Object[] { 0, 31006 };
    /** 等级不足 */
    public static final Object[] ERROR_31007 = new Object[] { 0, 31007 };
    /** 性别不对 */
    public static final Object[] ERROR_31008 = new Object[] { 0, 31008 };
    // /** 已经强化到了顶级 */
    // public static final Integer ERROR_31009 = 31009;
    /** 已经强化到了顶级 */
    public static final Object[] STRENGHTHEN_MAX = new Object[] { 0, 31009 };
    // /** 不能强化 */
    // public static final Integer ERROR_31010 = 31010;
    /** 不能强化 */
    public static final Object[] EQUIP_NOT_STRENGTHEN = new Object[] { 0, 31010 };
    /** 金钱不足 */
    public static final Object[] ERROR_31011 = new Object[] { 0, 31011 };
    // /** 物品不足 */
    // public static final Integer ERROR_31012 = 31012;
    /** (升星)物品不足 */
    public static final Object[] STARUP_GOOD_NOT_ENOUGH = new Object[] { 0, 31012 };

    // /** 此装备不能进阶 */
    // public static final Integer ERROR_31013 = 31013;
    /**
     * 职业不正确
     */
    public static final Object[] ERROR_31014 = new Object[] { 0, 31014 };

    // /** 清洗所消耗的物品不足 */
    // public static final Integer ERROR_31015 = 31018;
    // /**
    // * vip等级不足
    // */
    // public static final Integer ERROR_31016 = 31019;
    // /**
    // * 需要装备强化等级不足
    // */
    // public static final Integer ERROR_31017 = 31017;
    // /** 等级不一致 */
    // public static final Integer ERROR_31020 = 31020;
    // /**
    // * 31021 属性都已经达到最大值,无需鉴定
    // */
    // public static final Integer ERROR_31021 = 31021;
    // /**
    // * 31022 装备数量不足
    // */
    // public static final Integer ERROR_31022 = 31022;
    // /**
    // * 31023 装备等级不同
    // */
    // public static final Integer ERROR_31023 = 31023;
    // /**
    // * 31024 选择的不是装备 和鉴定材料
    // */
    // public static final Integer ERROR_31024 = 31024;
    // /**
    // * 31025 不能使用高级材料替代装备
    // */
    // public static final Integer ERROR_31025 = 31025;
    // /**
    // * 31026 合成次数已经用完
    // */
    // public static final Integer ERROR_31026 = 31026;
    // /**
    // * 31027 此装备不能鉴定
    // */
    // public static final Integer ERROR_31027 = 31027;
    // /**
    // * 31028 此装备不能作为鉴定材料
    // */
    // public static final Integer ERROR_31028 = 31028;
    // /**
    // * 31029 此装【{0}】备不能作为合成材料
    // */
    // public static final Integer ERROR_31029 = 31029;
    // /**
    // * 31030 已经强化到了最高等级
    // */
    // public static final Integer ERROR_31034 = 31034;
    // /**
    // * 公告
    // */
    // public static final Integer ERROR_31035 = 31035;

    /** 左手有副武器 */
    public static final Object[] LEFT_EXIT_WEAPONS = new Object[] { 0, 31036 };
    /** 裝備類型不同 */
    public static final Object[] EQUIPMENT_TYPE_DIFF = new Object[] { 0, 31037 };
    /** 装备耐久度无损耗 */
    public static final Object[] NOT_DUR_REPAIR = new Object[] { 0, 31038 };
    /** 装备孔已开启过 */
    public static final Object[] EQUIP_HOLE_EXIST = new Object[] { 0, 31039 };
    /** 开孔类型不符合(当前装备不包含改孔类型) */
    public static final Object[] EQUIP_NOT_HOLE_OPEN = new Object[] { 0, 31040 };
    /** 开孔材料不足 */
    public static final Object[] EQUIP_PUNCH_NOT_ENOUGH_GOODS = new Object[] { 0, 31041 };
    /** 当前镶嵌孔已有镶嵌珠宝 */
    public static final Object[] EQUIP_STUFF_JEWELRY_EXIST = new Object[] { 0, 31042 };
    /** 当前镶嵌孔没有镶嵌珠宝 */
    public static final Object[] EQUIP_STUFF_JEWELRY_NOT_EXIST = new Object[] { 0, 31043 };
    /** 当前孔位置未开启 */
    public static final Object[] EQUIP_HOLE_NOT_OPEN = new Object[] { 0, 31044 };
    /** 不能镶嵌同类型的珠宝 */
    public static final Object[] EQUIP_JEWELRY_TYPE_EXIST = new Object[] { 0, 31045 };
    /** 当前孔位已开启,不能重复开启 */
    public static final Object[] EQUIP_HOLE_OPEN_EXIST = new Object[] { 0, 31046 };
    /** 合成配方不存在 */
    public static final Object[] EQUIP_COMPOUND_NOT_EXIST = new Object[] { 0, 31047 };
    /** 珠宝快速合成后,当前镶嵌孔不能镶嵌 */
    public static final Object[] EQUIP_COMPOUND_NOT_PUNCH = new Object[] { 0, 31048 };
    /** 珠宝可镶嵌孔与被镶嵌孔类型不符合 */
    public static final Object[] EQUIP_JEWELRY_NOT_MATCH_HOLE = new Object[] { 0, 31049 };
    /** 附魔值已达最大 */
    public static final Object[] EQUIP_ENCHANT_MAX = new Object[] { 0, 31050 };
    /** 绑定物品不可分解 */
    public static final Object[] EQUIP_ANALYSIS_IS_BIND = new Object[] { 0, 31051 };
    /** 装备孔开启条件不足 */
    public static final Object[] EQUIP_PUNCH_CONDITION_FAIL = new Object[] { 0, 31052 };
    /** 合成失败 */
    public static final Object[] EQUIP_COMPOUND_FAIL = new Object[] { 0, 31053 };
    /** 合成失败{0}次 */
    public static final int EQUIP_COMPOUND_FAILS = 31054;
    /** 珠宝功能尚未开启 */
    public static final Object[] JEWELRY_NO_OPEN = new Object[] { 0, 31055 };
    /** 当前装备不可附魔(无附魔配置信息) */
    public static final Object[] EQUIP_ENCHANT_TEMPLATE = new Object[] { 0, 31059 };
    /** 合成配方没有激活 */
    public static final Object[] EQUIP_COMPOUND_NOT_ACTIVATE = new Object[] { 0, 31060 };
    /** 合成配方已经激活 */
    public static final Object[] EQUIP_COMPOUND_ACTIVATED = new Object[] { 0, 31061 };

    /** 资质需要突破 */
    public static final Object[] QUALIFICATION_NOT_ENOUGHT = new Object[] { 0, 31062 };
    /** 升星已到达最大 在前端里返回的是已达到装备最大星级，这是策划要求的 */
    public static final Object[] STARUP_MAX = new Object[] { 0, 31063 };
    /** 已达到最大升星阶段 */
    public static final Object[] STARUP_GRADATION_MAX = new Object[] { 0, 31064 };
    /** 升星等级未达到突破要求 */
    public static final Object[] STARUP_GRADATION_NOT_TRUE = new Object[] { 0, 31065 };
    /** 已达到装备最大星级 */
    public static final Object[] STARUP_MAXSTAR_NOW = new Object[] { 0, 31066 };
    /** (装备强化)物品不足 */
    public static final Object[] STRENGTH_GOODS_NOT_ENOUGHT = new Object[] { 0, 31067 };
    // ------------系统提示消息(33000-33999)-----------------------------------------------------
    /** {0}选择了需求取向:{1} */
    public final static int Error_33000 = 33000;
    /** {0}选择了贪婪取向:{1} */
    public final static int Error_33001 = 33001;
    /** 你获得了物品:{0} */
    public final static int Error_33002 = 33002;
    /** {0}放弃了选择物品:{1} */
    public final static int Error_33003 = 33003;
    /** {0}赢得了:{1} */
    public final static int Error_33004 = 33004;
    /** (需求){0}点:{1}({2}) */
    public final static int Error_33005 = 33005;
    /** (贪婪){0}点:{1}({2}) */
    public final static int Error_33006 = 33006;

    /** 很抱歉，当前物品不属于您。 */
    public final static Object[] Error_33007 = new Object[] { 0, 33007 };
    /** 很抱歉，您不可以把物品分配给他。 */
    public final static Object[] Error_33008 = new Object[] { 0, 33008 };

    /** 测试结果：击杀 {0} 只 {1} ,掉落物品如下： */
    public final static int Error_33010 = 33010;
    /** {0} 件: {1} */
    public final static int Error_33011 = 33011;
    /** {0}已经改名为{1} */
    public final static int Error_33012 = 33012;

    /** {0}得到资源箱，红队增加{1}点资源 */
    public final static int Error_33014 = 33014;
    /** {0}得到资源箱，蓝队增加{1}点资源 */
    public final static int Error_33015 = 33015;
    // 功能降级
    /** 服务器功能维护中，请耐心等待 */
    public static final Object[] FUNCTION_33016 = new Object[] { 0, 33016 };
    /** 聊天功能维护中，请耐心等待 */
    public static final Object[] FUNCTION_33017 = new Object[] { 33017 };
    /** 商城系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33018 = new Object[] { 0, 33018 };
    /** 坐骑系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33019 = new Object[] { 0, 33019 };
    /** 披风系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33020 = new Object[] { 0, 33020 };
    /** 任务系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33021 = new Object[] { 0, 33021 };
    /** 签到系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33022 = new Object[] { 0, 33022 };
    /** 等级礼包系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33023 = new Object[] { 0, 33023 };
    /** 资源追回系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33024 = new Object[] { 0, 33024 };
    /** 离线经验追回系统维护中，请耐心等待 */
    public static final Object[] FUNCTION_33025 = new Object[] { 0, 33025 };
    /** 心跳密室维护中，请耐心等待 */
    public static final Object[] FUNCTION_33026 = new Object[] { 0, 33026 };
    /** 训练场维护中，请耐心等待 */
    public static final Object[] FUNCTION_33027 = new Object[] { 0, 33027 };
    /** 地精财务行维护中，请耐心等待 */
    public static final Object[] FUNCTION_33028 = new Object[] { 0, 33028 };
    /** 礼品商人维护中，请耐心等待 */
    public static final Object[] FUNCTION_33029 = new Object[] { 0, 33029 };

    /** 本服世界BOSS被{0}终结一击。 */
    public final static int Error_33030 = 33030;
    /** {0}击杀了{0}，掉落了巨量的金钱和材料。 */
    public final static int Error_33032 = 33032;

    /** 你不是国王,无权限进行禁言操作 */
    public final static Object[] Error_33033 = new Object[] { 0, 33033 };
    /** 由于xxx情况你已被国王禁言,在xxx频道下,你将被禁言1个小时 */
    public final static int Error_33034 = 33034;

    /** 你已经被禁止发言。 */
    public final static Object[] CHAT_FORBIDDEN_33035 = new Object[] { 33035 };
    /** 封号了 */
    public final static Object[] LOGIN_GAME_FORBIDDEN_33036 = new Object[] { 0, 33036 };
    /** 33037 角色已经删除，不允许登陆。 */
    public final static Object[] LOGIN_GAME_FORBIDDEN_33037 = new Object[] { 0, 33037 };

    /** {0}获得的{1}成就 */
    public static final int ACHIEVE_FINISH = 33040;

    /** {0}通过寻宝获得了{1} */
    public static final int ERROR_33050 = 33050;
    /** {0}获得了{1} //(寻宝) */
    public static final int ERROR_33051 = 33051;

    /** 33060 账号未激活，不允许登陆。 */
    public static final Object[] NOT_ALLOW_LOGIN_ACCOUNT_NOT_ACTIVATED = new Object[] { 0, 33060 };

    /** 宠物合成维护中，请耐心等待 */
    public static final Object[] FUNCTION_33130 = new Object[] { 0, 33130 };
    /** 宠物图鉴维护中，请耐心等待 */
    public static final Object[] FUNCTION_33131 = new Object[] { 0, 33131 };
    /** 拍卖行维护中，请耐心等待 */
    public static final Object[] FUNCTION_33132 = new Object[] { 0, 33132 };
    /** 寻宝维护中，请耐心等待 */
    public static final Object[] FUNCTION_33133 = new Object[] { 0, 33133 };

    /** 国王向【{0}】发动了灭国战争，【{kuafu|立刻前往}】 */
    public static final int ERROR_33140 = 33140;
    /** 国王向【{0}】发动了国家骚扰，【{kuafu|立刻前往}】 */
    public static final int ERROR_33141 = 33141;

    /** 皇城争夺将在30分钟后开启，请做好战前准备 */
    public static final int ERROR_33142 = 33142;
    /** 皇城争夺将在10分钟后开启，请做好战前准备 */
    public static final int ERROR_33143 = 33143;
    /** 皇城争夺已经开启，欢迎大家立即进入跨服争霸 */
    public static final int ERROR_33144 = 33144;

    /** 33200 您的登陆过期了，请重新登录 */
    public static final Object[] ERROR_33200 = new Object[] { 0, 33200 };
    /** 33201 你击败了{0}，获得了{1}点战场功勋 */
    public static final int Error_33201 = 33201;

    // ------------跨服战场 (34000-34999)-----------------------------------
    /** 你不是队长，不可以带下匹配战场 */
    public final static Object[] Error_34000 = new Object[] { 0, 34000 };
    /** 你已有组队，不可以匹配个人战场 */
    public final static Object[] Error_34001 = new Object[] { 0, 34001 };
    /** 你没有组队，请去玩个人匹配 */
    public final static Object[] Error_34002 = new Object[] { 0, 34002 };
    /** 你的等级不足{0}级，无法进入战场匹配队列 */
    public final static int Error_34003 = 34003;
    /** {0}的等级不足{0}级，无法进入战场匹配队列 */
    public final static int Error_34004 = 34004;
    /** 你逃跑处罚中，请稍后再尝试... */
    public final static Object[] Error_34005 = new Object[] { 0, 34005 };
    /** {0}逃跑处罚中，请稍后再尝试... */
    public final static int Error_34006 = 34006;
    /** 匹配随机战场的同时不可以匹配其他战场. */
    public final static Object[] Error_34007 = new Object[] { 0, 34007 };
    /** 同时最多只能匹配两个战场. */
    public final static Object[] Error_34008 = new Object[] { 0, 34008 };
    /** 匹配随机战场前先取消其他战场匹配队列. */
    public final static Object[] Error_34010 = new Object[] { 0, 34010 };
    /** 很抱歉，战场已匹配成功，不可离开匹配队列. */
    public final static Object[] Error_34011 = new Object[] { 0, 34011 };
    /** {0}拒绝了{1}战场的匹配 */
    public final static int Error_34012 = 34012;
    /** {0}拒绝了随机战场的匹配 */
    public final static int Error_34013 = 34013;

    /** {0}玩家已掉线，系统自动取消了{1}战场的匹配 */
    public final static int Error_34014 = 34014;

    /** 你已离开了副本匹配队列 */
    public final static Object[] Error_34016 = new Object[] { 0, 34016 };
    /** {0}取消了副本匹配 */
    public final static int Error_34017 = 34017;
    /** 很抱歉，您当前处于跨服状态，无法直接进入匹配场景，系统已帮你拒绝进入. */
    public final static int Error_34018 = 34018;
    /** {0}进入跨服状态，系统自动取消了您的匹配队列。 */
    public final static int Error_34019 = 34019;

    /** 很抱歉，您当前处于跨服状态，无法进入匹配队列 */
    public final static Object[] Error_34020 = new Object[] { 0, 34020 };
    /** 很抱歉，队员当前处于跨服状态，无法进入匹配队列 */
    public final static Object[] Error_34021 = new Object[] { 0, 34021 };

    /** 很抱歉，队员的等级不在一个匹配区间，不可匹配战场 */
    public final static Object[] Error_34022 = new Object[] { 0, 34022 };

    // ------------体力值系统 (35000-35099)-----------------------------------
    /** 你今天购买次数已上限 */
    public static Object[] Error_35000 = new Object[] { 0, 35000 };
    /** 体力已满，不需要再购买体力值 */
    public static Object[] Error_35001 = new Object[] { 0, 35001 };

    // ------------排行榜(35100-35199)-----------------------------------
    /** 很抱歉，您今天已经向该玩家赠送过花朵了 */
    public static final Object[] Error_35100 = new Object[] { 0, 35100 };
    /** 很抱歉，您今日赠花数量已达上限：{0}次 */
    public static final int Error_35101 = 35101;
    /** 很抱歉，您今天已经向该玩家赠送过鸡蛋了 */
    public static final Object[] Error_35103 = new Object[] { 0, 35103 };
    /** 很抱歉，您今日赠鸡蛋数量已达上限：{0}次 */
    public static final int Error_35104 = 35104;
    /** 很抱歉，没有搜索到名为[{0}]的玩家 */
    public static final int Error_35105 = 35105;
    /** 很抱歉，玩家[{0}]还没有上榜 */
    public static final int Error_35106 = 35106;

    // ------------组队系统(35200-35299)-----------------------------------
    /** 很抱歉，您不能对自己进行队伍操作 */
    public static final Object[] Error_35200 = new Object[] { 0, 35200 };
    /** 很抱歉,对方正在自动匹配中. */
    public static final Object[] Error_35201 = new Object[] { 0, 35201 };
    /** 很抱歉，该玩家已加入其它队伍 */
    public static final Object[] Error_35202 = new Object[] { 0, 35202 };
    /** 很抱歉,只有队长才能操作 */
    public static final Object[] Error_35203 = new Object[] { 0, 35203 };
    /** 很抱歉,您的队伍已满员 */
    public static final Object[] Error_35204 = new Object[] { 0, 35204 };
    /** 很抱歉,当前场景不可以踢出成员 */
    public static final Object[] Error_35205 = new Object[] { 0, 35205 };
    /** 很抱歉,您的操作已过期 */
    public static final Object[] Error_35206 = new Object[] { 0, 35206 };
    /** 很抱歉,您当前的团队人数超过组队人数的上限啦. */
    public static final Object[] Error_35207 = new Object[] { 0, 35207 };
    /** 很抱歉,对方设置了仅好友才可以发出组队邀请. */
    public static final Object[] Error_35208 = new Object[] { 0, 35208 };
    /** 很抱歉,对方设置了拒绝所有人的组队邀请. */
    public static final Object[] Error_35209 = new Object[] { 0, 35209 };
    /** 很抱歉,您已匹配成功，不可以再邀请他人加入队伍。 */
    public static final Object[] Error_35210 = new Object[] { 0, 35210 };
    /** 很抱歉,您已进入匹配队列中. */
    public static final Object[] Error_35211 = new Object[] { 0, 35211 };
    /** 很抱歉,此副本不可以匹配... */
    public static final Object[] Error_35212 = new Object[] { 0, 35212 };
    /** 很抱歉，当前场景中不可以自由组队... */
    public static final Object[] Error_35213 = new Object[] { 0, 35213 };
    /** 很抱歉,当前场景不可以转化模式 */
    public static final Object[] Error_35214 = new Object[] { 0, 35214 };
    /** 很抱歉,匹配服务器正在维护中，请稍后... */
    public static final Object[] Error_35219 = new Object[] { 0, 35219 };
    /** {0}已离线，系统自动取消进入副本。 */
    public static final int Error_35220 = 35220;
    /** {0}已离线，系统自动取消 {1} 匹配。 */
    public static final int Error_35221 = 35221;
    /** {0}离开了队伍，系统自动取消进入副本。 */
    public static final int Error_35222 = 35222;
    /** {0}离开了队伍，系统自动取消{1}匹配。 */
    public static final int Error_35223 = 35223;
    /** 队伍正在匹配中，您不可以切换到{0}地图。 */
    public static final int Error_35224 = 35224;
    /** 很抱歉,您正在匹配中. */
    public static final Object[] Error_35225 = new Object[] { 0, 35225 };
    /** 您已准备直接进入副本，系统取消了您的自动匹配队列 */
    public static final Object[] Error_35227 = new Object[] { 35227 };

    /** 队伍匹配成功，您不可以离开队伍。 */
    public static final Object[] Error_35228 = new Object[] { 0, 35228 };
    /** 队伍匹配成功，您不可以踢出成员。 */
    public static final Object[] Error_35229 = new Object[] { 0, 35229 };
    /** 您的队伍成员发生变化，系统取消了您的自动匹配队列 */
    public static final Object[] Error_35230 = new Object[] { 35230 };
    /** 很抱歉,对方设计了组队邀请认证，请等待对方确认. */
    public static final Object[] Error_35231 = new Object[] { 0, 35231 };
    /** 很抱歉,{0}拒绝了你的组队邀请. */
    public static final int Error_35232 = 35232;
    /** 很抱歉,你们所选的职责让匹配系统很为难，请记住：三个输出，一个坦克，一个奶妈。 */
    public static final Object[] Error_35233 = new Object[] { 0, 35233 };
    /** 很抱歉,对方没有进入国家地图. */
    public static final Object[] Error_35234 = new Object[] { 0, 35234 };

    // ------------家园系统(35300-35399)-----------------------------------
    /** 很抱歉，您当前正在跨服中，不可以直接进入家园系统！ */
    public static final Object[] Error_35300 = new Object[] { 0, 35300 };
    /** 很抱歉，您还没有家园。 */
    public static final Object[] Error_35301 = new Object[] { 0, 35301 };
    /** 很抱歉，{0}还没有家园。 */
    public static final int Error_35302 = 35302;
    /** 很抱歉，当前场景中，您不可以直接进入家园！ */
    public static final Object[] Error_35303 = new Object[] { 0, 35303 };
    /** 家园鉴定等级不足 */
    public static final Object[] Error_35304 = new Object[] { 0, 35304 };
    /** 35306 该项目还没有开发 */
    public static final Object[] Error_35306 = new Object[] { 0, 35306 };
    /** 35307 找不到对应的资源 */
    public static final Object[] Error_35307 = new Object[] { 0, 35307 };
    /** 35308 已经是最高等级了 */
    public static final Object[] Error_35308 = new Object[] { 0, 35308 };
    /** 35309 没有空闲的cd队列 */
    public static final Object[] Error_35309 = new Object[] { 0, 35309 };
    /** 35310 已经升级了，不用重复升级 */
    public static final Object[] Error_35310 = new Object[] { 0, 35310 };
    /** 35311 升级CD中，不可以升级 */
    public static final Object[] Error_35311 = new Object[] { 0, 35311 };

    // ------------跨服活动系统(35400-35499)-----------------------------------
    /** 很抱歉，您今天的护送次数已达上限。 */
    public static final Object[] Error_35400 = new Object[] { 0, 35400 };
    /** 很抱歉，您接取镖车的资金不够." */
    public static final Object[] Error_35401 = new Object[] { 0, 35401 };
    /** 很抱歉，您今天的护卫次数已达上限。 */
    public static final Object[] Error_35402 = new Object[] { 0, 35402 };
    /** 同服玩家{0}的物资正在被打劫，是否前去{1} */
    public static final int Error_35403 = 35403;
    /** {0}接取了{1}，前去{2} */
    public static final int Error_35404 = 35404;
    /** 很抱歉，护卫物资需要先进入国家地图，然后才可以护卫物资. */
    public static final Object[] Error_35405 = new Object[] { 0, 35405 };
    /** 很抱歉，护卫物资已结束，请重新选择目标. */
    public static final Object[] Error_35406 = new Object[] { 0, 35406 };
    /** 很抱歉，您不能护卫自己的物资. */
    public static final Object[] Error_35407 = new Object[] { 0, 35407 };
    /** 很抱歉，同一时间，您只能护送一量物资. */
    public static final Object[] Error_35408 = new Object[] { 0, 35408 };
    /** {0}开始护卫您的物资. */
    public static final int Error_35409 = 35409;
    /** 很抱歉，您需要先击杀所有护旗神兽。 */
    public static final Object[] Error_35410 = new Object[] { 0, 35410 };
    /** 很抱歉，您不可以占领自己国家的据点。 */
    public static final Object[] Error_35411 = new Object[] { 0, 35411 };
    /** 很抱歉，您不是国王，不可以占领王战。 */
    public static final Object[] Error_35412 = new Object[] { 0, 35412 };
    /** 很抱歉，当前场景不可以直接进入国家地图. */
    public static final Object[] Error_35413 = new Object[] { 0, 35413 };

    // ------------竞技场系统(35500-35599)-----------------------------------
    /** 很抱歉，您正处于跨服状态中，不可以挑战竞技场。 */
    public static final Object[] Error_35500 = new Object[] { 0, 35500 };
    /** 很抱歉，您今天可挑战次数已用完. */
    public static final Object[] Error_35501 = new Object[] { 0, 35501 };

    // ------------采集系统(35600-35699)-----------------------------------
    /** 很抱歉，您今日的采集次数已用完. */
    public static final Object[] Error_35600 = new Object[] { 0, 35600 };
    /** 很抱歉，请您再离目标近一点. */
    public static final Object[] Error_35601 = new Object[] { 0, 35601 };

    // ------------每日活动系统(35700-35799)-----------------------------------
    /** 很抱歉，您正处于跨服状态中，不可以进入活动场景。 */
    public static final Object[] Error_35700 = new Object[] { 0, 35700 };
    /** 很抱歉，当前活动已结束。 */
    public static final Object[] Error_35701 = new Object[] { 0, 35701 };
    /** 很抱歉，非活动期间，不可以进入。 */
    public static final Object[] Error_35702 = new Object[] { 0, 35702 };

    // ------------副本房间匹配系统系统(35800-35899)-----------------------------------
    /** 很抱歉，当前房间已开始战斗啦。 */
    public static final Object[] Error_35800 = new Object[] { 0, 35800 };
    /** 很抱歉，您的战斗力不满足当房间的需求。 */
    public static final Object[] Error_35801 = new Object[] { 0, 35801 };
    /** 很抱歉，您输入的房间密码不正确。 */
    public static final Object[] Error_35802 = new Object[] { 0, 35802 };
    /** 很抱歉，房间成员还没有准备. */
    public static final Object[] Error_35803 = new Object[] { 0, 35803 };
    /** 很抱歉，您距下次发布寻找队友的时间还有一段时间. */
    public static final Object[] Error_35804 = new Object[] { 0, 35804 };
    /** {0} 正在寻找一起挑战{1}的队友，要求战力{2}以上。立即加入 */
    public static final int Error_35805 = 35805;
    /** 很抱歉，当前房间已满员，无需再寻找新队友. */
    public static final Object[] Error_35806 = new Object[] { 0, 35806 };
    /** 很抱歉，您当前已有队伍，不可以加入房间，您可以尝试创建一个房间. */
    public static final Object[] Error_35807 = new Object[] { 0, 35807 };
    /** 很抱歉，您当前正在匹配副本. */
    public static final Object[] Error_35808 = new Object[] { 0, 35808 };
    /** 很抱歉，队员当前正在匹配副本. */
    public static final Object[] Error_35809 = new Object[] { 0, 35809 };
    /** 很抱歉，当前房间已满员. */
    public static final Object[] Error_35810 = new Object[] { 0, 35810 };

    // ------------爬塔系统(35900-35999)-----------------------------------
    /** 很抱歉，您已领取过此宝箱。 */
    public static final Object[] Error_35900 = new Object[] { 0, 35900 };
    /** 很抱歉，领取此宝箱需打通第{0}层。 */
    public static final int Error_35901 = 35901;
    /** 很抱歉，今天您已达最大重置次数。 */
    public static final Object[] Error_35902 = new Object[] { 0, 35902 };
    /** 很抱歉，您已到达最高层，不可以扫荡。 */
    public static final Object[] Error_35903 = new Object[] { 0, 35903 };

    // ***********************************************拍卖行系统*********************************
    /** 拍卖行中可拍卖物品个数已达上限 */
    public static final Object[] AUCTION_SELL_NUM_MAX = new Object[] { 0, 425000 };
    /** 扣除拍卖物/物品数量时不成功 */
    public static final Object[] AUCTION_DECR_ERROR = new Object[] { 0, 425001 };
    /** 拍卖品不存在[已下架 或者 已经被购买] */
    public static final Object[] AUCTION_ITEM_NOT_EXIST = new Object[] { 0, 425002 };
    // /** 拍卖物品数量不足 */
    // public static final Object[] AUCTION_SELL_NUM_NOT_ENOUGH = new Object[] {
    // 0, 425003 };
    /** 绑定道具不能拍卖 */
    public static final Object[] AUCTION_ITEM_BING_ERROR = new Object[] { 0, 425004 };
    /** 自己待拍卖的物品自己不能购买 */
    public static final Object[] AUCTION_READY_ITEM_ERROR = new Object[] { 0, 425005 };
    /** 不可以世界拍卖该道具 */
    public static final Object[] AUCTION_CANT_GLOBAL = new Object[] { 0, 425006 };
    /** 价格过低，操作失败 */
    public static final Object[] AUCTION_PRICE_TOO_LOW = new Object[] { 0, 425007 };
    /** 425008 您的装备已镶嵌珠宝，无法寄售 */
    public static final Object[] AUCTION_REJECT_CAUSE_JEWELS = new Object[] { 0, 425008 };
    /** 425009 没有可以领取的收益 */
    public static final Object[] AUCTION_NO_PROFIT = new Object[] { 0, 425009 };
    /** 拍卖行中可拍卖货币条目已达上限 */
    public static final Object[] AUCTION_CURRENCY_SELL_NUM_MAX = new Object[] { 0, 425010 };
    /** 425011 要叫卖的拍卖不存在 */
    public static final Object[] AUCTION_SHOWOFF_NOT_EXISTS = new Object[] { 0, 425011 };
    /** 425012 拍卖行叫卖过于频繁 */
    public static final Object[] AUCTION_SHOWOFF_TOO_FREQUENCY = new Object[] { 0, 425012 };
    /** 425013 拍卖行今日领取收益已达上限 */
    public static final Object[] AUCTION_PROFIT_RECEIVE_REACH_LIMIT = new Object[] { 0, 425013 };

    /** 425020 {0}：出售{1} 数量：{2}, 钻石价格：{3} */
    public static final int AUCTION_ITEM_SHOWOFF_INFO = 425020;
    /** 425021 {0}：出售钻石：{1}, 金币价格：{2} */
    public static final int AUCTION_DIAMOND_SHOWOFF_INFO = 425021;
    /** 425022 {0}：出售金币：{1}, 钻石价格：{2} */
    public static final int AUCTION_GOLD_SHOWOFF_INFO = 425022;

    // ***********************************************邮件系统*********************************
    /** 邮件不存在 */
    public static final Object[] NOT_FOUND_EMAIL = new Object[] { 0, 60001 };
    /** 领取邮件费用不足 */
    public static final Object[] GAIN_ATTACH_FEE_NOT_ENOUGTH = new Object[] { 0, 60008 };

    // ***********************************************任务系统*********************************
    /** 81101 今日 日常任务已全部完成 */
    public static final Object[] ERROR_81101 = new Object[] { 0, 81101 };
    /** 当前等级没有合适的悬赏任务 */
    public static final Object[] ERROR_81109 = new Object[] { 0, 81109 };
    /** 当前悬赏任务奖励星级已经最大 */
    public static final Object[] ERROR_81110 = new Object[] { 0, 81110 };
    /** 81111 当前悬赏任务已经完成，不能刷新星级 */
    public static final Object[] ERROR_81111 = new Object[] { 0, 81111 };

    /** 很抱歉，您的兽栏格位已达上限。 */
    public static final Object[] Pet_82200 = new Object[] { 0, 82200 };
    /** 很抱歉，此卡尚未鉴定。 */
    public static final Object[] Pet_82201 = new Object[] { 0, 82201 };
    /** 很抱歉，此卡已鉴定过了。 */
    public static final Object[] Pet_82202 = new Object[] { 0, 82202 };
    /** 很抱歉，此宠物不喜欢吃这类型的道具。 */
    public static final Object[] Pet_82203 = new Object[] { 0, 82203 };
    /** 宠物不存在。 */
    public static final Object[] Pet_82204 = new Object[] { 0, 82204 };
    /** 宠物名不合法 */
    public static final Object[] Pet_82205 = new Object[] { 0, 82205 };
    /** 宠物等级不能超过玩家等级。 */
    public static final Object[] Pet_82206 = new Object[] { 0, 82206 };
    /** 宠物等级已到满级。 */
    public static final Object[] Pet_82207 = new Object[] { 0, 82207 };
    /** 宠物好感度等级已达满级。 */
    public static final Object[] Pet_82208 = new Object[] { 0, 82208 };
    /** 宠物种类不符 */
    public static final Object[] Pet_82209 = new Object[] { 0, 82209 };
    /** 已达到最大升星等级 */
    public static final Object[] Pet_82210 = new Object[] { 0, 82210 };
    /** 消耗宠物等级不够 */
    public static final Object[] Pet_82211 = new Object[] { 0, 82211 };
    /** 宠物升星失败 */
    public static final Object[] Pet_82212 = new Object[] { 0, 82212 };
    /** 宠物资质已最大 */
    public static final Object[] Pet_82213 = new Object[] { 0, 82213 };
    // ***********************************************坐骑系统*********************************
    /** 已是最高阶坐骑，暂时无法继续进阶 */
    public static final Object[] Horse_82150 = new Object[] { 0, 82150 };
    /** 人物等级达到[XX]级后，才可继续进阶 */
    public static final int Horse_82151 = 82151;
    /** 需要XX材料名[XX颗]，才可继续进阶 */
    public static final int Horse_82152 = 82152;
    /** 需要金币：XXXX，才可继续进阶 */
    public static final int Horse_82153 = 82153;
    /** 道具不足无法激活坐骑 */
    public static final Object[] Horse_82154 = new Object[] { 0, 82154 };
    /** XXX坐骑已经激活 */
    public static final int Horse_82155 = 82155;
    /** 使用{0}激活{1}需要坐骑达到{2}阶 */
    public static final int Horse_82156 = 82156;
    /** 坐骑未幻化激活 */
    public static final Object[] Horse_82157 = new Object[] { 0, 82157 };
    /** 82158 坐骑幻化{0}已达最高等级 */
    public static final int Horse_82158 = 82158;
    /** 82159 体验的幻化不可以升级，请从正规渠道购买正规幻化，么么哒~~ */
    public static final Object[] Error_82159 = new Object[] { 0, 82159 };

    // ***********************************************披风系统*********************************
    /** 已是最高阶披风，暂时无法继续进阶 */
    public static final Object[] Cloak_82350 = new Object[] { 0, 82350 };
    /** 人物等级达到[XX]级后，才可继续进阶 */
    public static final int Cloak_82351 = 82351;
    /** 需要XX材料名[XX颗]，才可继续进阶 */
    public static final int Cloak_82352 = 82352;
    /** 需要金币：XXXX，才可继续进阶 */
    public static final int Cloak_82353 = 82353;
    /** 道具不足无法激活披风 */
    public static final Object[] Cloak_82354 = new Object[] { 0, 82354 };
    /** XXX披风已经激活 */
    public static final int Cloak_82355 = 82355;
    /** 使用{0}激活{1}需要披风达到{2}阶 */
    public static final int Cloak_82356 = 82356;
    /** 披风未幻化激活 */
    public static final Object[] Cloak_82357 = new Object[] { 0, 82357 };
    /** 82358 披风幻化{0}已达最高等级 */
    public static final int Cloak_82358 = 82358;
    // ---------------副本系统-----------------------------------
    /** 很抱歉，你不是队长，不能带队进副本 */
    public static final Object[] CANT_ENTER_INSTANCE = new Object[] { 0, 83012 };
    /** 83013 很抱歉，队员等级不足 */
    public static final Object[] INSTANCE_83013 = new Object[] { 0, 83013 };
    /** 83014 很抱歉，进入副本所需要的道具不足 */
    public static final Object[] INSTANCE_83014 = new Object[] { 0, 83014 };
    /** 83015 很抱歉，队员进入副本所需要的道具不足 */
    public static final Object[] INSTANCE_83015 = new Object[] { 0, 83015 };
    /** 83016 很抱歉，进入副本前置任务未完成 */
    public static final Object[] INSTANCE_83016 = new Object[] { 0, 83016 };
    /** 83017 很抱歉，队员进入副本前置任务未完成 */
    public static final Object[] INSTANCE_83017 = new Object[] { 0, 83017 };
    /** 83018 很抱歉，进入副本需要完成{0} */
    public static final int INSTANCE_83018 = 83018;
    /** 83019 很抱歉，队员进入副本需要完成{0} */
    public static final int INSTANCE_83019 = 83019;
    /** 83020 很抱歉，今天进入本副本次数已达上限 */
    public static final Object[] INSTANCE_83020 = new Object[] { 0, 83020 };
    /** 83021 很抱歉，队员今天进入本副本次数已达上限 */
    public static final Object[] INSTANCE_83021 = new Object[] { 0, 83021 };
    /** 83022 很抱歉，再次进入本副本还有一段时间 */
    public static final Object[] INSTANCE_83022 = new Object[] { 0, 83022 };
    /** 83023 很抱歉，队员再次进入本副本还有一段时间 */
    public static final Object[] INSTANCE_83023 = new Object[] { 0, 83023 };
    /** 83024 很抱歉，你已在副本中 */
    public static final Object[] INSTANCE_83024 = new Object[] { 0, 83024 };
    /** 83025 很抱歉，队员已在副本中 */
    public static final Object[] INSTANCE_83025 = new Object[] { 0, 83025 };
    /** 83026 很抱歉，{0}拒绝进入副本. */
    public static final int INSTANCE_83026 = 83026;
    /** 很抱歉，您的VIP等级不满足进入此副本. */
    public static final Object[] INSTANCE_83027 = new Object[] { 0, 83027 };
    /** 很抱歉，队员的VIP等级不满足进入此副本. */
    public static final Object[] INSTANCE_83028 = new Object[] { 0, 83028 };
    /** 很抱歉，您不可以带非好友进入此副本. */
    public static final Object[] INSTANCE_83029 = new Object[] { 0, 83029 };
    /** 很抱歉，个人不可以单独进入团队副本，里面很危险. */
    public static final Object[] INSTANCE_83030 = new Object[] { 0, 83030 };
    /** 很抱歉，有队员正在跨服中，不可以进入副本。 */
    public static final Object[] INSTANCE_83031 = new Object[] { 0, 83031 };
    /** 很抱歉，当前副本的只能进入 {0} 个人 */
    public static final int Error_83032 = 83032;
    /** 很抱歉，当前邀请请求已超时. */
    public static final Object[] Error_83033 = new Object[] { 0, 83033 };
    /** 很抱歉，您当前等级还不足以扫荡. */
    public static final Object[] Error_83034 = new Object[] { 0, 83034 };
    /** 很抱歉，您当前的体力值不足以扫荡. */
    public static final Object[] Error_83035 = new Object[] { 0, 83035 };
    /** 很抱歉，您需要先通关当前副本. */
    public static final Object[] Error_83036 = new Object[] { 0, 83036 };
    /** 很抱歉，您的巅峰等级不满足进入此副本. */
    public static final Object[] INSTANCE_83037 = new Object[] { 0, 83037 };
    /** 很抱歉，队员的巅峰等级不满足进入此副本. */
    public static final Object[] INSTANCE_83038 = new Object[] { 0, 83038 };

    // ***********************************************在线客服*********************************
    // /** 请输入标题和内容 */
    // public final static String q85000 = "85000";
    // /** 请重新输入标题 */
    // public final static String q85001 = "85001";

    // ***********************************************神将禁地*********************************
    // /** 等级不足 */
    // public static final Integer LEVEL_LESS = 86000;
    // /** 请先完成前一副本 */
    // public static final Integer FRONT_FIRST = 86002;
    // /** 很抱歉,今天已经挑战过该副本 */
    // public static final Integer IS_PASS = 86003;
    // /** 需要消耗的道具或元宝都不足 */
    // public static final Integer COMSUME_NOT_ENOUGH = 86004;
    // /** 挖掘次数已满 */
    // public static final Integer JINDI_GATHER_FULL = 86005;
    // /** 挖掘消耗的元宝不足 */
    // public static final Integer JINDI_COST_YB = 86007;
    // /** 很抱歉，什么都木有采集到 */
    // public static final Integer JINDI_GATHER_NULL = 86008;

    // ***********************************************领地战*********************************
    // /** 今日领地战将在半小时后开启，请各帮派做好战前准备，点击主界面顶部的三界争霸面板可以查询帮派领地战规则（无需报名，默认全部帮派参与） */
    // public static final Integer _30MINUTE = 87001;
    // /** 今日领地战将在10分钟后开启，请各军团做好战前准备，点击主界面顶部的三界争霸面板可以查询帮派领地战规则（无需报名，默认全部帮派参与） */
    // public static final Integer _10MINUTE = 87002;
    // /** 领地争夺战已经开启，请各位帮主立即进入领地战场争夺您的领地 */
    // public static final Integer WARSTART = 87003;
    // /** 您在今日有过帮派更换行为，因此本次收益无法领取 */
    // public static final Integer CHANGE_CLAN_DAYS = 87010;
    // /** 您在领地战地图内没有停留满15分钟，本次收益无法领取 */
    // public static final Integer NO_20 = 87011;
    // /** 很抱歉，您今天发生过帮会变更操作，无法领取该领地的每日奖励 */
    // public static final Integer CLAN_CHANGED_TODAY = 87012;
    // /** 您今日已经征收过该领地的收益了 */
    // public static final Integer GAINED_TODAY = 87013;
    // /** 很抱歉，您所在的帮派尚未占领该地，无法征收该领地收益 */
    // public static final Integer NO_ZHANLING = 87014;
    // /** 很抱歉，没有帮派无法领取 */
    // public static final Integer NO_CLAN2GAIN = 87015;
    // /** 很抱歉，没有奖励可领取 */
    // public static final Integer NO_RESULTAWARD = 87016;

    // /** 不可拔旗 */
    // public static final Integer STAGE_ERROR = 8888888;
    // /** 已关闭 */
    // public static final Integer CLOSE = 87031;
    // /** 没有公会 */
    // public static final Integer NO_CLAN = 87032;
    // /** 不是帮主 */
    // public static final Integer NOT_LEADER = 87032;
    // /** 帮贡不足 */
    // public static final Integer LESS_MONEY = 87034;
    // /** 已结束 */
    // public static final Integer OVER = 87035;
    // /** 旗帜已被他人拔走 */
    // public static final Integer FLAG_NOT_EXIST = 87052;
    // public static final Integer NOT_BANNER = 87053;
    // /** 正在被他人操作 */
    // public static final Integer OTHER_OPERATE = 87054;
    // /** 不可拔自己的旗 */
    // public static final Integer OPERATE_SELF = 87055;
    // /** 战争结束，不能拔旗 */
    // public static final Integer BAQI_WAR_OVER = 87056;
    // /** 战争结束--持有20分钟 */
    // public static final Integer WAR_OVER_MSG_20 = 87004;
    // /** 战争结束--60分钟时持有 */
    // public static final Integer WAR_OVER_MSG_60 = 87005;
    // /** 战争结束--无人持有旗帜 */
    // public static final Integer WAR_OVER_MSG_NO = 87006;
    // /** 守旗20分钟 */
    // public static final Integer WAR_OVER_SHOU = 87007;
    // /** 失去旗帜 */
    // public static final Integer BANNER_LOSE = 87043;
    // /** 获得旗帜 */
    // public static final Integer BANNER_GAIN = 87041;
    //
    // /** 不允许玫瑰复活 */
    // public static final Integer MEIGUI_CANNT = 87060;
    // /** 领地战期间不可使用飞鞋 */
    // public static final Integer CANNT_FLY = 87061;
    /** 87062 不能采集,你没有此任务 */
    public static final Object[] CAIJI_NOTASK = new Object[] { 0, 87062 };
    /** 找不到要解救的NPC */
    public static final Object[] NO_RESCUE_NPC = new Object[] { 0, 87063 };
    /** 没有该解救任务 */
    public static final Object[] NO_RESCUE_TASK = new Object[] { 0, 87064 };
    // /** 87063 角色已经死亡 */
    // public static final Integer CODE_87063 = 87063;

    // ***********************************************骑战兵器*********************************
    // /** 87100 你还未开启技能格位 */
    // public static final int ERROR_87100 = 87100;
    // /** 87101 你已经学习了此技能 */
    // public static final int ERROR_87101 = 87101;
    // /** 广播 */
    // public static final int ERROR_87102 = 87102;
    // /** 你没有骑战兵器，不能使用该物品 */
    // public static final int ERROR_87103 = 87103;
    // /** 使用已经达到上限 */
    // public static final int ERROR_87104 = 87104;

    // ***********************************************仙阶*********************************
    // /** 90001 你已经完成了该活跃度，不需要秒杀 */
    // public static final Integer ERROR_90001 = 90001;
    // /** 90002 此活跃度不能别秒杀 */
    // public static final Integer ERROR_90002 = 90002;
    // /** 90003 你已经领取了该奖励 */
    // public static final Integer ERROR_90003 = 90003;
    // /** 90004 活跃度不足 */
    // public static final Integer ERROR_90004 = 90004;

    // ***********************************************组队副本*********************************
    // /** 你已经加入了一个队伍 */
    // public static final Integer ERROR_92001 = 92001;
    // /** 队伍人数已达上线 */
    // public static final Integer ERROR_92002 = 92002;
    // /** 92003 战力不足 */
    // public static final Integer ERROR_92003 = 92003;
    // /** 你已经属于本队伍的人 */
    // public static final Integer ERROR_92004 = 92004;
    // /** 你已经加入了其它队伍 */
    // public static final Integer ERROR_92005 = 92005;
    // /** 此队伍人数过多，不能进入此地图 */
    // public static final Integer ERROR_92006 = 92006;
    // /** 挑战本副本，队友【{0}】的等级不足 */
    // public static final Integer ERROR_92007 = 92007;
    // /** 队友【{0}】前置副本未完成，不能挑战此副本 */
    // public static final Integer ERROR_92008 = 92008;
    // /** 队伍正在战斗中，此时不能加入该队伍 */
    // public static final Integer ERROR_92009 = 92009;
    // /** 正在战斗中不能切换关卡 */
    // public static final Integer ERROR_92010 = 92010;
    // /** 不是队长不能切换关卡 */
    // public static final Integer ERROR_92011 = 92011;
    // /** 不是队长不能踢出玩家 */
    // public static final Integer ERROR_92012 = 92012;
    // /** 正在副本任务，禁止踢人 */
    // public static final Integer ERROR_92013 = 92013;
    // /** 被踢者不属于本队伍 */
    // public static final Integer ERROR_92014 = 92014;
    // /** 你不是队长不能解算队伍 */
    // public static final Integer ERROR_92015 = 92015;
    // /** 正在战斗中 不能解算队伍 */
    // public static final Integer ERROR_92016 = 92016;
    // /** 队伍里面还有玩家没有准备 */
    // public static final Integer ERROR_92017 = 92017;
    // /** 不是队长不能开始副本 */
    // public static final Integer ERROR_92018 = 92018;
    // /** 92019 前置关卡未完成，不能接受邀请 */
    // public static final Integer ERROR_92019 = 92019;
    // /** 92020 队伍已经解散 */
    // public static final Integer ERROR_92020 = 92020;
    // public static final Integer ERROR_92021 = 92021;
    // public static final Integer ERROR_92022 = 92022;
    // /** 92023 不是队长不能更改战力 */
    // public static final Integer ERROR_92023 = 92023;
    // /** 92024 战力过高 */
    // public static final Integer ERROR_92024 = 92024;
    // public static final Integer ERROR_92025 = 92025;
    // /** 92026 正在战斗中 */
    // public static final Integer ERROR_92026 = 92026;
    // /** 广播 */
    // public static final Integer ERROR_92027 = 92027;
    // /** 92028 条件未达成，不能抽奖 */
    // public static final Integer ERROR_92028 = 92028;
    // /** 92029 等级过高不能抽奖 */
    // public static final Integer ERROR_92029 = 92029;
    // /** 92030 你已经退出了副本，不能进行抽奖 */
    // public static final Integer ERROR_92030 = 92030;

    // ***********************************************藏宝阁*********************************
    // /** 93001 物品已经下架 */
    // public static final Integer ERROR_93001 = 93001;
    // /** 93002 兑换已经达到最大次数 */
    // public static final Integer ERROR_93002 = 93002;
    // /** 93003 今天兑换点数已达上限 */
    // public static final Integer ERROR_93003 = 93003;
    // /** 93004 所需兑换值不足 */
    // public static final Integer ERROR_93004 = 93004;

    // ***********************************************竞技*********************************
    // /** 95000 购买次数已经用完 */
    // public static final Integer error_95000 = 95000;
    // /** 95001 战力已经达到 20% 不能刷新 */
    // public static final Integer error_95001 = 95001;
    // /** 95002 挑战的次数已经用完 */
    // public static final Integer error_95002 = 95002;
    // /** 95003 CD时间未到，你可以点击消除CD时间 */
    // public static final Integer error_95003 = 95003;
    // /** 挑战别人成功 */
    // public static final Integer error_95004 = 95004;
    // /** 被别人挑战失败 */
    // public static final Integer error_95005 = 95005;
    // /** 挑战别人失败 */
    // public static final Integer error_95006 = 95006;
    // /** 被别人挑战成功 */
    // public static final Integer error_95007 = 95007;
    // /** 95008 你已经领取了该奖励*/
    // public static final Integer error_95008 = 95008;
    // /** 95010 你没有奖励可以领取 */
    // public static final Integer error_95010 = 95010;
    // /** 95011 CD时间不需要清除 */
    // public static final Integer error_95011 = 95011;
    // /** 95012 没有奖励可以领取 */
    // public static final Integer error_95012 = 95012;
    // /** 95013 挑战中 */
    // public static final Integer error_95013 = 95013;
    // /** 第一名被击败的广播 */
    // public static final Integer error_95014 = 95014;
    // public static final Integer error_95015 = 95015;

    // ***********************************************七圣boss活动*********************************
    // /** 98001 七圣公告 倒计时公告{0} */
    // public static final Integer CODE_98001 = 98001;
    // /** 98002 蟠桃园公告 倒计时公告{0} */
    // public static final Integer CODE_98002 = 98002;
    // /** 98003 活动还未开始 */
    // public static final Integer CODE_98003 = 98003;
    // /** 98004 你没有帮派，不能参加该活动 */
    // public static final Integer CODE_98004 = 98004;
    // /** 98005 七圣boss活动开始公告 */
    // public static final Integer CODE_98005 = 98005;
    // /** 98006 蟠桃园 活动开始公告 */
    // public static final Integer CODE_98006 = 98006;
    // /** 98007 恭喜【xxxx帮派名】帮派的【xxx人名】在七圣乱斗中成功击杀了【xxxboss名】 */
    // public static final Integer CODE_98007 = 98007;
    // /** 恭喜玩家【xxx人名】在七圣乱斗中成功击杀了【xxxboss名】 */
    // public static final Integer CODE_98008 = 98008;

    // ***********************************************技能书*********************************
    /** 99000 操作异常 */
    public static final Object[] CODE_99000 = new Object[] { 0, 99000 };
    // /** 99001 此技能书不能吞噬别的技能书 */
    // public static final Integer CODE_99001 = 99001;
    // /** 99002 技能书等级最大不能吞噬 */
    // public static final Integer CODE_99002 = 99002;
    // /** 99003 类型不符 */
    // public static final Integer CODE_99003 = 99003;
    // /** 99004 此技能格位 未开启 */
    // public static final Integer CODE_99004 = 99004;
    // /** 99005 坐骑等级不足 */
    // public static final Integer CODE_99005 = 99005;
    // /** 99006 骑兵等级不足 */
    // public static final Integer CODE_99006 = 99006;
    // /** 99007 此类型技能书能装备一本 */
    // public static final Integer CODE_99007 = 99007;

    // ***********************************************寻宝*********************************
    // /** 很抱歉，寻宝活动出错了 */
    // public final static Integer k99000 = 99100;
    // /** 寻宝活动已经过期 */
    // public final static Integer k99001 = 99101;
    // /** 需要的元宝不足 */
    // public final static Integer k99002 = 99102;
    // /** 需要的道具不足 */
    // public final static Integer k99003 = 99103;
    // /** 今天寻宝没有达到次数，不能领取 */
    // public final static Integer k99004 = 99104;
    // /** 今天已经领取过了 */
    // public final static Integer k99005 = 99105;
    // public final static Integer XUNBAO_MSG = 99110;

    // ***********************************************道具系统*********************************
    /** 100001 道具使用异常 */
    public static final Object[] PROP_ERROR_100001 = new Object[] { 0, 100001 };
    /** 100002 此道具不是消耗类道具 */
    public static final Object[] PROP_ERROR_100002 = new Object[] { 0, 100002 };
    /** 100003 使用此物品的等级不足 */
    public static final Object[] PROP_ERROR_100003 = new Object[] { 0, 100003 };
    /** 100004 职业不符 */
    public static final Object[] PROP_ERROR_100004 = new Object[] { 0, 100004 };
    // /** 100005 血和蓝已满 */
    // public static final Integer PROP_ERROR_100005 = 100005;
    // /** 100006 血已满 */
    // public static final Integer PROP_ERROR_100006 = 100006;
    // /** 100007 蓝已满 */
    // public static final Integer PROP_ERROR_100007 = 100007;
    /** 100008 物品使用失败 */
    public static final Object[] PROP_ERROR_100008 = new Object[] { 0, 100008 };
    /** 100009 物品已经过期 */
    public static final Object[] PROP_ERROR_100009 = new Object[] { 0, 100009 };
    /** 你已经使用过了一个{坐骑祝福爆击卡了} */
    public static final Object[] PROP_ERROR_100010 = new Object[] { 0, 100010 };
    /** 你已经使用过了一个{坐骑几率提升卡} */
    public static final Object[] PROP_ERROR_100011 = new Object[] { 0, 100011 };
    /** 你已经使用过了一个{装备强化幸运符} */
    public static final Object[] PROP_ERROR_100012 = new Object[] { 0, 100012 };
    /** 100013 技能书使用失败 */
    public static final Object[] PROP_ERROR_100013 = new Object[] { 0, 100013 };
    /** 100014 坐骑等级不足 */
    public static final Object[] PROP_ERROR_100014 = new Object[] { 0, 100014 };
    /** 100015 坐骑阶级不够 */
    public static final Object[] PROP_ERROR_100015 = new Object[] { 0, 100015 };
    /** 100016 披风阶级不够 */
    public static final Object[] PROP_ERROR_100016 = new Object[] { 0, 100016 };
    /** CD时间未到 */
    public static final Object[] PROP_ERROR_100017 = new Object[] { 0, 100017 };
    /** 100018 道具不能在此地点使用 */
    public static final Object[] PROP_ERROR_100018 = new Object[] { 0, 100018 };
    /** 100019 宠物经验卡不能在背包使用 */
    public static final Object[] PROP_ERROR_100019 = new Object[] { 0, 100019 };
    /** 100020 当前效果类道具的效果不存在 */
    public static final Object[] PROP_ERROR_100020 = new Object[] { 0, 100020 };
    /** 100021 正在做任务，不允许使用飞鞋 */
    public static final Object[] PROP_ERROR_100021 = new Object[] { 0, 100021 };
    /** 100022 道具使用1个足以 */
    public static final Object[] PROP_ERROR_100022 = new Object[] { 0, 100022 };
    /** 100023 功能已经开启，不用重复开启 */
    public static final Object[] PROP_ERROR_100023 = new Object[] { 0, 100023 };
    // ***********************************************光翼*********************************
    // /** 广播 */
    // public static final Integer CODE_102000 = 102000;
    /** 102001 你已经拥有了光翼 */
    public static final Object[] CODE_102001 = new Object[] { 0, 102001 };
    /** 还未激活 */
    public static final Object[] CODE_102002 = new Object[] { 0, 102002 };

    // ***********************************************登录系统*********************************
    // /** 103000 登录天数不足 */
    // public static final Integer code_103000 = 103000;
    // /** 103001 你已经领取了该奖励 */
    // public static final Integer code_103001 = 103001;

    // ***********************************************头衔*********************************
    /** 104000 未激活该头衔 */
    public static final Object[] code_104000 = new Object[] { 0, 104000 };
    /** 104001 该头衔已经过期 */
    public static final Object[] code_104001 = new Object[] { 0, 104001 };
    // /** 104002 你已经激活了该头衔 */
    // public static final Integer code_104002 = 104002;
    // /** 公告 */
    // public static final Integer code_104003 = 104003;
    // public static final Integer code_104004 = 104004;
    // /** 105000 积分不足 */
    // public static final Integer CODE_105000 = 105000;
    // /** 105001 今日使用铜钱次数已用完 */
    // public static final Integer CODE_105001 = 105001;
    // /** vip等级不足，未开始连续抽奖 */
    // public static final Integer CODE_105002 = 105002;
    // /** 106000 号码格式错误，或者区域错误 */
    // public static final Integer ERROR_1 = 106000;

    // ***********************************************追回*********************************
    // /** 107000 你已经追回了此次奖励 */
    // public static final Integer CODE_107001 = 107000;

    // ***********************************************聊天*********************************
    /** 不能和自己私聊 */
    public static final Object[] PM_TARGET_SAME = new Object[] { 200002 };
    /** 私聊 目标角色不在线 */
    public static final Object[] PM_ROLE_OFFLINE = new Object[] { ROLE_OFFLINE[1] };
    /** 很抱歉,国家聊天暂不支持,原因为当前游戏区未开通国家功能 */
    public static final Object[] ERROR_CODE_200003 = new Object[] { 200003 };
    /** 很抱歉,您当前需要支付聊天的游戏币不足 */
    public static final Object[] ERROR_CODE_200004 = new Object[] { 200004 };
    /** 很抱歉,您当前聊天需要支付的道具不足 */
    public static final Object[] ERROR_CODE_200005 = new Object[] { 200005 };
    /** 很抱歉,当前聊天模式未开启 */
    public static final Object[] ERROR_CODE_200006 = new Object[] { 200006 };
    /** 很抱歉,您当前聊天速度太快,请稍后尝试 */
    public static final Object[] ERROR_CODE_200007 = new Object[] { 200007 };

    // ***********************************************场景*********************************
    // /** 当前状态下禁止跳跃 */
    // public static final Integer JUMP_STATE_NO = 400001;
    // /** 当前场景禁止跳跃 */
    // public static final Integer JUMP_STAGE_NO = 400002;

    // **********************************************技能系统********************
    /** 技能配置不存在或已升到最高级 */
    public static final Object[] NOT_FOUND_SKILL_CONFIG = new Object[] { 0, 420001 };
    // /** 技能正在创建中 */
    // public static final Integer SKILL_IN_CREATE = 420002;
    // /** 技能不开放 */
    // public static final Integer SKILL_NOT_OPEN = 420003;
    // /** 技能学习失败 */
    // public static final Integer SKILL_STUDY_FAIL = 420004;
    /** 420005 未学习此技能 */
    public static final Object[] STUDY_NOT = new Object[] { 0, 420005 };
    /** 420006 请先提升人物等级 */
    public static final Object[] STUDY_LEVEL = new Object[] { 0, 420006 };
    /** 420007 已经学习过了此技能 */
    public static final Object[] STUDY_SKILL = new Object[] { 0, 420007 };
    /** 420008 学习此技能的角色等级不足 */
    public static final Object[] STUDY_LEVELNO = new Object[] { 0, 420008 };
    /** 420009 学习技能职业不符合 */
    public static final Object[] STUDY_JOBNO = new Object[] { 0, 420009 };
    /** 当前已经升到最高级 */
    public static final Object[] SKILL_MAX_LEVEL = new Object[] { 0, 420010 };
    // /** 420011 合成所需碎片不足 */
    // public static final Integer HECHENGSUIPIAN_NOT = 420011;
    // /** 420012 神将等级不足 */
    // public static final Integer SHENGJI_LEVEL_NOT = 420012;
    // /** 420013 技能等级不足 */
    // public static final Integer SHENGJI_SKILLLEVEL_NOT = 420013;
    // /** 420014 已经拥有了该变身 */
    // public static final Integer shengjiang_420014 = 420014;
    // /** 420015 很抱歉，您还未激活该武魂所对应的变身 */
    // public static final Integer shengjiang_420015 = 420015;
    // /** 420016 很抱歉，当前变身可使用武魂次数已达到上限。 */
    // public static final Integer shengjiang_420016 = 420016;
    // /** 420017 该武魂不能使用在该变身 */
    // public static final Integer shengjiang_420017 = 420017;
    // /** 420018 所需变身阶级不够 */
    // public static final Integer shengjiang_420018 = 420018;
    /** 特技升阶时，角色等级不够 */
    public static final Object[] SPECAIL_SKILL_UP_NOT_LEVEL = new Object[] { 0, 420019 };
    /** 角色特技升阶已达最大 或者 当前特技不存在 */
    public static final Object[] SPECAIL_SKILL_LVL_MAX = new Object[] { 0, 420020 };
    /** 当前将要切换的特技未激活 */
    public static final Object[] SPECAIL_SKILL_NOT_ACTIV = new Object[] { 0, 420021 };

    /** 很抱歉,当前公会等级太低,本技能未开放 */
    public static final Object[] STUDY_SKILL_BY_GUILD_FAIL = new Object[] { 0, 420032 };
    /** 很抱歉,目前你没有公会,不能学习公会技能 */
    public static final Object[] STUDY_GUILD_SKILL_LIMIT = new Object[] { 0, 420033 };
    /** 很抱歉,你目前公会贡献值不足 */
    public static final Object[] GUILD_CONTRIBUTE_NOT_ENOUGH = new Object[] { 0, 420034 };
    /** 特技升级失败 */
    public static final Object[] SPECAIL_SKILL_LVUP_FAIL = new Object[] { 0, 420035 };
    /** 光武抗性技能升级失败 */
    public static final Object[] RESIST_SKILL_UPGRADE_FAILE = new Object[] { 0, 420036 };
    /** 不能重复开启天赋页签 */
    public static final Object[] TALENT_MODE_ACTIVATED = new Object[] { 0, 420037 };
    /** 天赋页签未开启不能切换 */
    public static final Object[] TALENT_MODE_NOT_EXIST = new Object[] { 0, 420038 };
    /** 很抱歉,天赋切换CD冷却中 */
    public static final Object[] SWITCH_TALENT_MODE_COOD = new Object[] { 0, 420039 };
    /** 当前等级的效果被动技能(被动技能)配置表不存在 */
    public static final Object[] ERROR_CODE_420040 = new Object[] { 0, 420040 };

    // ***********************************************神器系统*********************************
    /** 当前将要佩戴的神器未激活 */
    public static final Object[] ARTIFACT_NOT_ACTIVATION = new Object[] { 0, 421001 };
    /** 当前神器佩戴职业需求不符合 */
    public static final Object[] ARTIFACT_JOB_NOT_VERIFY = new Object[] { 0, 421002 };
    /** 开启神器所需的道具不足 */
    public static final Object[] ARTIFACT_OPEN_item_NOT_ENOUGH = new Object[] { 0, 421003 };
    /** 神器已经开启 */
    public static final Object[] ARTIFACT_OPENED = new Object[] { 0, 421004 };
    /** 开启神器没有对应神器 */
    public static final Object[] ARTIFACT_OPEN_NOID = new Object[] { 0, 421005 };

    /** 神器已激活，由于职业不匹配，神器不能佩带 */
    public static final Object[] ARTIFACT_ACTIVATION_JOB_NOT_MATCH = new Object[] { 0, 421006 };
    /** 神器已激活 */
    public static final Object[] ARTIFACT_ACTIVATION = new Object[] { 0, 421007 };
    /** 神器升星所需的道具不足 */
    public static final Object[] ERROR_421008 = new Object[] { 0, 421008 };

    // ***********************************************升阶系统*********************************
    // /** 很抱歉,将星图已激活 */
    // public static final String CUR_JIANGXINGTU_ACTIVED = "423000";
    /** 很抱歉,前置将星图没激活 */
    public static final Object[] BEFORE_JIANGXINGTU_NOT = new Object[] { 0, 423001 };
    // /** 很抱歉，激活所需花费不足 */
    // public static final String MONEY_NOT_ENOUGH = "423002";
    // /** 您还未点满当前将星,无法进阶 */
    // public static final String s423004 = "423004";
    // /** 已是最高阶变身,暂时无法继续进阶 */
    // public static final String s423005 = "423005";
    // /** 很抱歉,将星图已升到最高级 */
    // public static final String s423006 = "423006";
    // /** 上一层天赋没有升{0}点天赋 */
    // public static final String Error_423007 = "423007";
    /** 主动天赋已激活 */
    public static final Integer Error_423008 = 423008;
    /** 天赋点已达到上限 */
    public static final Object[] Error_423009 = new Object[] { 0, 423009 };
    /** 当前天赋模id系统不存在 */
    public static final Object[] Error_423010 = new Object[] { 0, 423010 };
    /** 当前天赋技能职业不匹配 */
    public static final Object[] Error_423011 = new Object[] { 0, 423011 };
    /** 无当前等级的技能模板数据 */
    public static final Object[] Error_423012 = new Object[] { 0, 423012 };
    /** 天赋所属的模块错误(非法数据) */
    public static final Object[] ERROR_423013 = new Object[] { 0, 423013 };
    // /**
    // * 玩家[font color='#00FF00']{0}[/font]在【{1}】贯通了[font
    // * color='#00FF00']{open|天元心法,HeroMediator,xinfa}[/font],【{2}】，大家恭喜他
    // */
    // public static final String notice_code = "423010";
    // /** [font color='#00FF00']{0}[/font]成功将【{1}】进阶成【{2}】阶 */
    // public static final String s423011 = "423011";

    // ***********************************************摊位交易*********************************
    // /** 自己已在交易中 */
    // public static final Integer ROLE_SELF_TRADEING = 424001;
    // /** 对方在交易中 */
    // public static final Integer ROLE_TRADEING = 424002;
    // /** 自己不在交易中 */
    // public static final Integer ROLE_STATE_TRADE_ERROR = 424003;
    // /** 对方不在交易中[暂时没有用] */
    // public static final Integer ROLE_NO_TRADEING = 424004;
    // /** 交易的金钱不能小于0 */
    // public static final Integer TRADE_MONEY_LESS_ZERO = 424005;
    // /** 交易物品不存在 */
    // public static final Integer TRADE_GOODS_NO_EXIST = 424006;
    // /** 交易的物品是绑定物品 */
    // public static final Integer TRADE_GOODS_BIND = 424007;
    // /** 交易格索引号错误 */
    // public static final Integer TRADE_INDEX_ERROR = 424008;
    // /** 交易被拒绝带名字 */
    // public static final Integer TRADE_NAME_REJECT = 424009;
    // /** 交易被拒绝 */
    // public static final Integer TRADE_REJECT = 424010;
    // /** 交易数据背包放不下 */
    // public static final Integer TRADE_BAG_NO_SLOT = 424011;
    // /** 交易后你的游戏币会超出最大20亿，中断交易 */
    // public static final Integer TRADE_JB_MORE_MAX = 424012;
    // /** 交易后你的元宝会超出最大20亿，中断交易 */
    // public static final Integer TRADE_YB_MORE_MAX = 424013;
    // /** 交易异常，服务器中断交易 */
    // public static final Integer TRADE_EXCEPTION = 424014;
    // /** 你没有可领取元宝领取 */
    // public static final int RECIVE_NO_VALUE = 424016;
    // /** 领取后元宝账户会超过最高值，请下次再领取 */
    // public static final int RECIVE_YB_MAX = 424017;
    // /** 交易物品数据进入背包异常，服务器中断交易 */
    // public static final Integer TRADE_GOODS_IN_BAG_EXCEPTION = 424018;
    // /** 交易角色不在线 */
    // public static final Integer TRADE_ROLE_NO_ONLINE = 424019;
    // /** 物品过期 */
    // public static final Integer TRADE_GOODS_EXPIRE = 424020;
    // /** 交易得到元宝日志， {0}月{1}日{2}时{3}分{4}秒 玩家【{5}】交易给您{6}元宝 */
    // public static final Integer TRADE_LOG = 424040;
    // /** 摊位名字中存在非法或敏感字符 */
    // public static final int BOOTH_NAME_NO_ALLOW = 424044;
    // /** 绑定物品不能摊位 */
    // public static final int NO_BIND_GOODST = 424046;
    // /** 物品数量大于格子上物品的最大数量 */
    // public static final int GOODS_COUNT_MAX = 424068;
    // /** 摊位的物品个数达到最大值 */
    // public static final int BOOTH_GOODS_COUNT_MAX = 424047;
    // /** 请先输入对该物品的元宝定价 */
    // public static final int NO_INPUT_MONEY = 424048;
    // /** 物品已不在摊位里 */
    // public static final int GOODS_NO_IN_BOOTH = 424049;
    // /** 出售者已不在线 */
    // public static final int SELL_ROLE_NO_ONLINE = 424050;
    // /** 出售者元宝会超过元宝的最大值 */
    // public static final int SELL_YB_MAX = 424052;
    // /** 摆摊物品位置没有变化，不需要处理 */
    // public static final int BOOTH_SELL_INDEX_SAME = 424053;
    // /** 不能购买自己的摆摊物品 */
    // public static final int NO_BUY_SELF_GOODS = 424054;
    // /**
    // * 【出售记录】：{0}月{1}日{2}时{3}分{4}秒
    // * 玩家【{5}】在您的摊位购买了【{6}】，数量：{7}，您获得铜钱：{8}，可领取元宝：{9}，其中系统扣税【{10}】
    // */
    // public static final int SELL_BOOTH_GOODS_LOG = 424042;
    // /** 购买摆摊物品日志 */
    // public static final int BUY_BOOTH_GOODS_LOG = 424043;
    // /**
    // * 购买摊位上的元宝日志 【购买记录】：{0}月{1}日{2}时{3}分{4}秒 您在玩家【{5}】的摊位购买了
    // {6}【元宝】，您失去游戏币：{7}
    // */
    // public static final int BUY_BOOTH_YB_LOG = 424064;
    // /**
    // * 摆摊出售元宝日志 【出售记录】：{0}月{1}日{2}时{3}分{4}秒 玩家【{5}】在您的摊位购买了 {6}【元宝】，您获得游戏币：{7}
    // */
    // public static final int SELL_BOOTH_YB_LOG = 424063;
    // /**
    // * 购买摊位上的游戏币日志 【购买记录】：{0}月{1}日{2}时{3}分{4}秒
    // 您在玩家【{5}】的摊位购买了{6}【游戏币】，您失去元宝：{7}
    // */
    // public static final int BUY_BOOTH_JB_LOG = 424066;
    // /**
    // * 摆摊出售游戏币日志 【出售记录】：{0}月{1}日{2}时{3}分{4}秒
    // * 玩家【{5}】在您的摊位购买了{6}【游戏币】，您获得元宝：{7}，其中系统扣税【{8}】
    // */
    // public static final int SELL_BOOTH_JB_LOG = 424065;
    // /** 摊位发生变化，请刷新后再购买 */
    // public static final int BOOTH_CHANGE_REFRESH = 424067;
    // /** 游戏币或元宝不能超过最大值9亿 */
    // public static final int JB_OR_YB_MORE_MAX = 424060;
    // /** 游戏币商品已下架 */
    // public static final int JB_SELL_NO_EXIST = 424061;
    // /** 元宝商品已下架 */
    // public static final int YB_SELL_NO_EXIST = 424062;
    // /** 角色至少拥有1元宝,方可领取交易元宝 */
    // public static final int ROLE_YB_NOT_ENOUGH = 424069;

    // **********************************背包相关errorCode********************************
    /** 物品不存在 */
    public static Object[] NO_GOODS = new Object[] { 0, 430201 };
    /** 格位没有还没有激活 */
    public static final Object[] NOT_ACTIVATED_SLOT = new Object[] { 0, 430202 };
    /** 没有足够的物品 */
    public static final Object[] GOODS_LESS = new Object[] { 0, 430203 };
    /** 拆分的目标格位有物品 */
    public static final Object[] SPLIT_SLOT_HAVE_GOODS = new Object[] { 0, 430204 };
    /** 拆分物品的数量不正确 */
    public static final Object[] SPLIT_COUNT_ERROR = new Object[] { 0, 430205 };
    /** 操作不被允许无效 */
    public static final Object[] OPERATE_INVALID = new Object[] { 0, 430206 };
    /** 激活格位数量超过背包最大格位 */
    public static final Object[] ACTIVATED_EXCEED_MAX = new Object[] { 0, 430207 };
    /** 铜钱不足 */
    public static final Object[] TONGQIAN_NO_ENOUGH = new Object[] { 0, 430208 };
    /** 灵石加绑定灵石不足 */
    public static final Object[] LINGSHI_AND_BIND_NO_ENOUGH = new Object[] { 0, 430209 };
    /** 背包格位已经全部打开 */
    public static final Object[] BAG_ALL_SLOT_OPEN = new Object[] { 0, 430210 };
    /** 背包空格位不足 */
    public static final Object[] BAG_EMPTY_SLOT_NO_ENOUGH = new Object[] { 0, 430214 };

    /** 仓库格位已全部开完 */
    public static final Object[] STORAGE_ALL_SLOT_OPEN = new Object[] { 0, 430246 };

    /** 430251 玩家物品已消失 */
    public static final Object[] GOODS_NOT = new Object[] { 0, 430251 };

    /** 绑定物品不能丢弃 */
    public static final Object[] BIND_NO_DISCARD = new Object[] { 0, 430218 };
    /** 背包格位不足，物品放不下 */
    public static Object[] BAG_SLOT_LESS = new Object[] { 0, 430212 };
    /** 背包扩展格位不足 */
    public static Object[] EXPEND_BAG_LESS = new Object[] { 0, 430220 };
    /** 不能捡物品，因为进入副本时他体力不够 */
    public static Object GOOD_430252 = 430252; // 这个地方是特意改成这样的 会带个参数给客户端
    /** 拾取物品不存在,原因为:被他人拾取或者已被系统回收 */
    public static Object GOOD_430253 = 430253; // 这个地方是特意改成这样的 会带个参数给客户端
    /** 寻宝仓库空间不足 */
    public static final Object[] TREASURE_NOTENOUGHT = new Object[] { 0, 430254 };
    /** 角色不存在 */
    public static Object[] ROLE_NOT_EXISTS = new Object[] { 0, 430281 };
    /** 当前物品不具备被销毁的功能 */
    public static Object[] ITEM_NOT_DESTROY = new Object[] { 0, 430282 };
    /** 场景中的效果球已不存在 */
    public static Object[] ELEMENT_BUFF_ITEM_NOT_EXIST = new Object[] { 0, 430283 };
    /** 快捷恢复效果的配置编号错误 */
    public static int NOT_EXIST_FASTRECOVER_CONFIG = 430284;
    /** 配置的快捷恢复效果不存在 */
    public static int NOT_EXIST_FASTRECOVE_BUFF = 430285;
    /** 拾取物品已被系统回收 */
    public static int NOT_EXIST_FASTRECOVE_GOODS = 430286;
    /** 拾取物品目前属于他人,不能被拾取 */
    public static int GOODS_OWNER_OTHER = 430287;

    // ***********************************************商城模块*********************************
    /** 消耗货币类型错误 */
    public static Object[] ACCOUNT_TYPE_ERROR = new Object[] { 0, 430401 };
    /** 物品已不在购买列表内 */
    public static Object[] NO_IN_BUY_LIST = new Object[] { 0, 430402 };
    /** 430405 此物品不能出售 */
    public static final Object[] ERROR_430405 = new Object[] { 0, 430405 };
    /** 物品已过期或马上就会过期 */
    public static Object[] GOODS_NOW_EXPIRE = new Object[] { 0, 430408 };
    /** 物品数量超过单次购买上限 */
    public static Object[] HANDSEL_MAX_NUM = new Object[] { 0, 430416 };
    /** 商品数量不足 */
    public static Object[] ITEM_COUNT_NOT_ENOUGH = new Object[] { 0, 430420 };
    /** 商品当日购买数量达到上限 */
    public static Object[] DAY_BUY_MAX_NUM = new Object[] { 0, 430421 };
    /** 本商品不可以赠送好友 */
    public static Object[] ERROR_430422 = new Object[] { 0, 430422 };
    /** 商品购买数量达到上限 */
    public static Object[] BUY_MAX_NUM = new Object[] { 0, 430423 };
    /** 只可以赠送给本服玩家 */
    public static Object[] NOT_SAME_SERVER_NOT_ALLOW = new Object[] { 0, 430424 };

    // *******************************官方商城********************************
    /** 个人购买达到上限 */
    public static Object[] INDIVIDUAL_COUNT_TOO_MANY = new Object[] { 0, 431000 };
    // **********************************打坐系统********************************
    // /** 距离太远 */
    // public static final Integer DA_ZUO_DISTANCE = 440001;
    // /** 无法与该玩家双修 */
    // public static final Integer DA_ZUO_ROLE = 440002;
    // /** 正在打坐中 */
    // public static final Integer DA_ZUO = 440003;
    // /** 战斗状态下禁止打坐 */
    // public static final Integer DAZUO_FIGHT_NO = 440004;

    // **********************************好友模块********************************
    /** 玩家不存在 */
    public static final Object[] ROLE_NOTEXISTS = new Object[] { 0, 450001 };
    /** 不能添加自己为好友 */
    public static final Object[] FRIEND_NOT_ADD = new Object[] { 0, 450020 };
    /** 450021 很抱歉，您最多只能添加{0}位好友，目前已达上限。 */
    public static final int FRIENDS_COUNT_LIMIT = 450021;
    /** 好友列表中已存在此角色 */
    public static final Object[] ROLE_EXISTS_FRIENDS = new Object[] { 0, 450022 };
    /** 他在您的黑名单中，请先删除，否则无法添加好友。 */
    public static final Object[] FRIEND_EXISTS_BLACKLIST = new Object[] { 0, 450023 };
    /** 您的好友上线了 */
    public static final int FRIEND_ONLINE = 450024;
    /** 您的好友下线了 */
    public static final int FRIEND_OFFLINE = 450025;
    /** 好友战败 */
    public static final int FRIEND_FIGHT_FAIL = 450026;
    /** 好友战赢 */
    public static final int FRIEND_FIGHT_WIN = 450027;
    /** 对方好友数量达到上限 */
    public static final Object[] FRIENDS_OTHER_COUNT_LIMIT = new Object[] { 0, 450028 };
    /** 你被对方给鄙视了，无法进行此操作 */
    public static final Object[] YOU_ARE_BS = new Object[] { 0, 450029 };
    /** 私聊 你被对方给鄙视了，无法进行此操作 */
    public static final Object[] PM_YOU_ARE_BS = new Object[] { YOU_ARE_BS[1] };
    /** 很抱歉，心情需要在48个字符之间 */
    public static final int F450030 = 450030;
    /** 心情中包含敏感非法字符，修改失败 */
    public static final int F450031 = 450031;
    /** 你鄙视了对方，请先解除黑名单 */
    public static final Object[] YOU_BS_OTHER = new Object[] { 0, 450032 };
    /** 不能添加自己为仇人 */
    public static final Object[] FOE_NOT_ADD = new Object[] { 0, 450040 };
    /** 仇人数量达到上限 */
    public static final int FOE_COUNT_LIMIT = 450041;
    // /** 仇人列表中已存在此玩家 */
    // public static final int ROLE_EXISTS_FOE = 450042;
    // /** 自动添加仇人失败 */
    // public static final int AUTO_ADD_FOE_FAIL = 450043;
    // /** 您的仇人上线了 */
    // public static final int FOE_ONLINE = 450044;
    // /** 您的仇人下线了 */
    // public static final int FOE_OFFLINE = 450045;
    // /** 仇人战绩-1 */
    // public static final int FOE_COUNT_DECR = 450046;
    // /** 仇人战绩+1 */
    // public static final int FOE_COUNT_INCR = 450047;
    /** 仇人战败 */
    public static final int FOE_FIGHT_FAIL = 450048;
    /** 仇人战赢 */
    public static final int FOE_FIGHT_WIN = 450049;
    /** 不能添加自己为黑名单 */
    public static final Object[] BLACKLIST_NOT_ADD = new Object[] { 0, 450060 };
    /** 黑名单列表中已存在此玩家 */
    public static final int ROLE_EXISTS_BLACKLIST = 450062;
    /** 黑名单已存在好友列表 */
    public static final int BLACKLIST_EXISTS_FRIEND = 450063;
    /** 对方不是你的好友 */
    public static final Object[] TARGET_IS_NOT_YOUR_FRIEND = new Object[] { 0, 450085 };
    /** 今日送礼次数已经用完 */
    public static final Object[] NO_CHANCE_TO_SEND_GIFT = new Object[] { 0, 450086 };
    /** 道具数量不足 */
    public static final Object[] ITEM_NOT_ENOUGH = new Object[] { 0, 450087 };
    /** 绑定道具不可赠送 */
    public static final Object[] CANNOT_SEND_BIND_GIFT = new Object[] { 0, 450088 };
    /** 礼物没找到或者已过期 */
    public static final Object[] GIFT_NOT_FOUND = new Object[] { 0, 450089 };
    /** 今日领取礼物次数已经用完 */
    public static final Object[] NO_CHANCE_TO_RETRIEVE_GIFT = new Object[] { 0, 450090 };
    /** 自己给自己，自撸的节奏么 */
    public static final Object[] SEND_GIFT_TO_YOURSELF = new Object[] { 0, 450091 };
    /** 亲啊，财不露富啊，这不是你自己的号啊 */
    public static final Object[] TARGET_ISNOT_YOUR_ROLE = new Object[] { 0, 450092 };
    /** {0}不允许赠送 */
    public static final int ITEM_SEND_GIFT_FORBIDDEN = 450094;
    /** 450095 好友功能尚未开启 */
    public static final Object[] FRIENDS_FUNCTION_NOT_OPENED = new Object[] { 0, 450095 };
    /** 450096 对方好友功能尚未开启 */
    public static final Object[] TARGET_FRIENDS_FUNCTION_NOT_OPENED = new Object[] { 0, 450096 };

    // **********************************组队********************************
    /** 很抱歉,您的队伍已满员 */
    public static final Object[] TEAM_FULL = new Object[] { 0, 460001 };
    /** 很抱歉,目标队伍已满员 */
    public static final Object[] TARGET_TEAM_FULL = new Object[] { 0, 460002 };
    /** 很抱歉,您的操作已过期 */
    public static final Object[] OPERATION_EXPIRED = new Object[] { 0, 460004 };
    /** 对不起,该玩家已加入其它队伍 */
    public static final Object[] ROLE_TEAM_HAS = new Object[] { 0, 460005 };
    // /** 您已有队伍 */
    // public static final String IN_TEAM = "460006";
    /** 很抱歉,只有队长才能操作 */
    public static final Object[] TEAM_NOT_LEADER = new Object[] { 0, 460007 };
    // /** 很抱歉,您不能开除自己 */
    // public static final String TEAM_NOT_EXPEL = "460008";
    /** 对不起,{0}玩家拒绝了您的队长任命请求 */
    public static final int REFUSED_LEADER = 460009;
    // /** 很抱歉,您申请加入的队伍已解散 */
    // public static final String TEAM_DISMISS = "460010";
    // /** 对不起,{0}队长拒绝了您的入队请求 */
    // public static final String TEAM_LEADER_REFUSED_REQUEST = "460011";
    // /** 对不起,{0}玩家拒绝了你的组队邀请 */
    // public static final String TEAM_ROLE_REFUSE_REQUEST = "460012";
    /** 对方选择放弃获得物品的机会~ */
    public static final Object[] Error_460015 = new Object[] { 0, 460015 };
    /** 对不起,自己不能对自己进行队伍操作 */
    public static final Object[] T460013 = new Object[] { 0, 460013 };
    /** 正在组队副本中，不能踢人 */
    public static final Object[] T460014 = new Object[] { 0, 460014 };
    // /** 正在组队副本中，不能加入野外队伍 */
    // public static final String T460015 = "460015";

    // ----------------------------------------------------公会模块----------------------------------------
    // /** 很抱歉，公会名字不可为空 */
    public static final Object[] g470000 = new Object[] { 0, 470000 };
    // /** 很抱歉，会旗名字不可为空 */
    // public static final int g470001 = 470001;
    /** 您已加入公会，如需建帮则需先退出公会 */
    public static final Object[] OWER_GUILD = new Object[] { 0, 470002 };
    /** 很抱歉，公会名字需要在2-7个字符之间 */
    public static final Object[] GUILD_NAME_LENGHT_SHORT = new Object[] { 0, 470003 };
    /** 很抱歉，公会名字中包含敏感非法字符 */
    public static final Object[] g470004 = new Object[] { 0, 470004 };
    /** 很抱歉，公会名字已被其他公会占用 */
    public static final Object[] GUILD_NAME_EXIST = new Object[] { 0, 470005 };
    /** 很抱歉，公会名字需大于2个字符 */
    public static final Object[] g470006 = new Object[] { 0, 470006 };
    // /** 很抱歉，会旗名字中包含敏感非法字符 */
    // public static final int g470007 = 470007;
    // /** 很抱歉，会旗名字已被其他公会占用 */
    // public static final int g470008 = 470008;
    // /** 很抱歉，创建公会所需游戏币不足 */
    // public static final int g470009 = 470009;
    /** 很抱歉，您的等级太低无法创建公会 */
    public static final Object[] CREATE_GUILD_LEVEL_NOT_ENOUGH = new Object[] { 0, 470010 };
    /** 您已加入公会 */
    public static final Object[] GUILD_MEMBER_EXIST = new Object[] { 0, 470011 };
    /** 很抱歉，公会人数已满，无法招收更多成员了 */
    public static final Object[] GUILD_MEMBER_FULL = new Object[] { 0, 470012 };
    /** 很抱歉，对方会长/副会长/管理员均不在线 */
    public static final Object[] MANAGER_NOT_ONLINE = new Object[] { 0, 470013 };
    // /** 很抱歉，公会最大成员上限为{0}人，目前已达上限 */
    // public static final Object[] g470014 = new Object[] { 0, 470014 };
    /** 很抱歉，你申请的公会不存在 */
    public static final Object[] GUILD_NOT_EXIST = new Object[] { 0, 470015 };
    /** 很抱歉,公会接受者,不是被转让公会的会员 */
    public static final Object[] TARGET_NOT_GUILD_MEMBER = new Object[] { 0, 470016 };
    /** 申请者已加入公会 */
    public static final Object[] g470017 = new Object[] { 0, 470017 };
    /** 很抱歉，您当前还未加入任何公会 */
    public static final Object[] g470018 = new Object[] { 0, 470018 };
    /** 很抱歉,当前的操作您的权限不够 */
    public static final Object[] g470019 = new Object[] { 0, 470019 };
    /** 很抱歉，当前任命的职位人数已达上限 */
    public static final Object[] g470020 = new Object[] { 0, 470020 };
    /** 很抱歉，目标成员当前已经脱离本公会 */
    public static final Object[] g470021 = new Object[] { 0, 470021 };
    /** 很抱歉，您不是公会会长，无权转让公会会长职位 */
    public static final Object[] g470022 = new Object[] { 0, 470022 };
    /** 很抱歉，你当前不是公会成员 */
    public static final Object[] g470023 = new Object[] { 0, 470023 };
    /** 很抱歉，自己不能开除自己 */
    public static final Object[] g470024 = new Object[] { 0, 470024 };
    /** 您是会长，如需退会，请先任命其他成员为会长 */
    public static final Object[] g470025 = new Object[] { 0, 470025 };
    /** 很抱歉，您邀请的目标已加入别的公会 */
    public static final Object[] g470026 = new Object[] { 0, 470026 };
    /** 很抱歉,您已经加入其它公会 */
    public static final Object[] g470027 = new Object[] { 0, 470027 };
    /** 很抱歉，您当前的邀请函已失效(时间过期) */
    public static final Object[] g470028 = new Object[] { 0, 470028 };
    /** 很抱歉，您要加入的公会目前已不存在 */
    public static final Object[] g470029 = new Object[] { 0, 470029 };
    /** 很抱歉，当前角色等级，公会工资未配置 */
    public static final Object[] g470030 = new Object[] { 0, 470030 };
    /** 很抱歉，今天公会工资您已领取过了 */
    public static final Object[] g470031 = new Object[] { 0, 470031 };
    /** 很抱歉，您的贡献值不足 */
    public static final Object[] g470032 = new Object[] { 0, 470032 };
    /** 很抱歉,当前公会无捐献配置信息 */
    public static final Object[] g470033 = new Object[] { 0, 470033 };
    /** 很抱歉，本次公会升级需求物品数量不足 */
    public static final Object[] g470034 = new Object[] { 0, 470034 };
    /** 很抱歉，本次公会升级需求金币不足 */
    public static final Object[] g470035 = new Object[] { 0, 470035 };
    /** 很抱歉，您当前职位下不能申请入团 */
    public static final Object[] g470036 = new Object[] { 0, 470036 };
    /** 很抱歉，自己不能转让公会给自己 */
    public static final Object[] g470037 = new Object[] { 0, 470037 };
    /** 很抱歉，自己不能处理自己的申请需求 */
    public static final Object[] g470038 = new Object[] { 0, 470038 };
    /** 很抱歉，你已是当前团队成员,不能重复申请加入团队 */
    public static final Object[] g470039 = new Object[] { 0, 470039 };
    /** 很抱歉，公告中包含敏感非法字符 */
    public static final Object[] g470040 = new Object[] { 0, 470040 };
    /** 很抱歉,对方等级未达标,不能加入公会 */
    public static final Object[] g470041 = new Object[] { 0, 470041 };
    /** 很抱歉,公会等级已达最大或者系统配置信息不存在 */
    public static final Object[] g470042 = new Object[] { 0, 470042 };
    /** 很抱歉,申请者目前已是军团成员 */
    public static final Object[] g470043 = new Object[] { 0, 470043 };
    /** 错误调用,你当前没有加入任何军团 */
    public static final Object[] g470044 = new Object[] { 0, 470044 };
    /** 很抱歉,当前等级下的公会捐献仓库已满，请升级公会等级 */
    public static final Object[] g470045 = new Object[] { 0, 470045 };
    /** 很抱歉,被邀请对象不存在 */
    public static final Object[] g470046 = new Object[] { 0, 470046 };
    /** 很抱歉，您当前等级未达加入公会等级需求 */
    public static final Object[] g470047 = new Object[] { 0, 470047 };
    /** 很抱歉,您当前是国王不能进行公会会长职位转让操作 */
    public static final Object[] g470048 = new Object[] { 0, 470048 };
    /** 很抱歉,不能对目标执行职位变化(目标玩家在跨服状态下) */
    public static final Object[] g470049 = new Object[] { 0, 470049 };
    /** 很抱歉,转让公会成员在跨服状态,不能执行公会转让 */
    public static final Object[] g470050 = new Object[] { 0, 470050 };

    /** 471000 {0}公会成立 */
    public static final int g471000 = 471000;
    // /** 471001 公会升到{0}级 */
    // public static final String g471001 = "471001";
    // /** 471002 公会旗帜升到{0}级 */
    // public static final String g471002 = "471002";
    /** 471003 {0}玩家加入本公会，公会变得更加强大 */
    public static final int g471003 = 471003;
    /** 471004 {0}玩家被{1}玩家逐出公会 */
    public static final int g471004 = 471004;
    /** 471005 {0}玩家离开了我们的公会 */
    public static final int g471005 = 471005;
    /** 471006 {0}玩家被任命为{1} */
    public static final int g471006 = 471006;
    // /** 471007 公会成员{0}玩家在地图{1}被玩家{2}击败 */
    // public static final Object[] g471007 = new Object[] { 0, 471007 };
    // /** 471008 公会成员{0}玩家在地图{1}击败了玩家{2} */
    // public static final Object[] g471008 = new Object[] { 0, 471008 };
    // /** 471009 公会成员{0}玩家在地图{1}对BOSS{2}完成了最后一击 */
    // public static final Object[] g471009 = new Object[] { 0, 471009 };
    /** 471010 {0}玩家向公会捐献了:{1}*数量;{2}*数量,获得贡献点:{3}贡献点 */
    public static final int g471010 = 471010;
    /** 471011 {0}玩家向公会捐献了：{1} 游戏币,获得贡献点:{2} */
    public static final int g471011 = 471011;
    /** 471012 {0}玩家被任命的职位为:{1}{2} */
    public static final int g471012 = 471012;
    /** {0}玩家完成了公会任务：公会金币增加:{1} 获得贡献值:{2} */
    public static final int g471013 = 471013;
    /** 需要公会等级达到{0}级 */
    public static final int g471014 = 471014;

    /** 军团人数已达上限 */
    public static final Object[] g471020 = new Object[] { 0, 471020 };
    /** 不是公会成员不能申请进入公会军团 */
    public static final Object[] g471021 = new Object[] { 0, 471021 };
    /** 很抱歉,您没有权限解散公会 */
    public static final Object[] g471022 = new Object[] { 0, 471022 };
    /** 很抱歉,您等级没有达到接任等级要求 */
    public static final Object[] g471023 = new Object[] { 0, 471023 };
    /** 很抱歉,您公会贡献值没有达到接任需求 */
    public static final Object[] g471024 = new Object[] { 0, 471024 };
    /** 很抱歉,不能重复参加竞选接任 */
    public static final Object[] g471025 = new Object[] { 0, 471025 };
    /** 很抱歉,当前团被任命的职位人数已达上限 */
    public static final Object[] g471026 = new Object[] { 0, 471026 };
    /** 很抱歉,您无权限对其他军团成员进行任命操作 */
    public static final Object[] g471027 = new Object[] { 0, 471027 };

    /** 公会创建成功后的广播消息 */
    public static final int g471050 = 471050;
    /** 角色加入公会时，公会系统消息 */
    public static final int g471051 = 471051;
    /** 您在公会中的职务变更为:{0} */
    public static final int g471052 = 471052;
    /** 您将会长职位禅让给了：{0} 玩家名字 */
    public static final int g471053 = 471053;
    /** 恭喜您被提升为公会会长 */
    public static final int g471054 = 471054;
    /** 本公会[{0}]变更，欢迎新会长：{1} */
    public static final int g471055 = 471055;
    /** {0} 已经脱离了公会 */
    public static final int g471056 = 471056;
    /** 471057 您在公会中的职务变更为:{0}{1} */
    public static final int g471057 = 471057;

    // 公会任务
    /** 当前公会任务奖励星级已经最大 */
    public static final Object[] g471058 = new Object[] { 0, 471058 };
    /** 跨服皇城争夺战期间当前操作不禁用 */
    public static final Object[] g471059 = new Object[] { 0, 471059 };

    // /**
    // * 您没有权限开除{0}
    // */
    // public static final int g470015 = 470015;
    //
    // /**
    // * 很抱歉,{0}{1}拒绝了您的入会请求
    // */
    // public static final int g470016 = 470016;
    //
    // /**
    // * 很抱歉,公会已解散
    // */
    // public static final int g470017 = 470017;
    //
    // /**
    // * 很抱歉,您没有公会
    // */
    // public static final int g470018 = 470018;
    //
    // /**
    // * 很抱歉，{0}已加入公会
    // */
    // public static final int g470019 = 470019;
    //
    // /**
    // * 很抱歉，{0}已离线
    // */
    // public static final int g470020 = 470020;
    //
    // /**
    // * 很抱歉，您没有权限修改会旗
    // */
    // public static final int g470021 = 470021;
    //
    // /**
    // * 很抱歉，您没有权限解散公会
    // */
    // public static final int g470022 = 470022;
    //
    // /**
    // * 您是会长，如需退帮，请先任命其他成员为会长
    // */
    // public static final int g470023 = 470023;
    //
    // /**
    // * 您是会长，不能开除自己
    // */
    // public static final int g470024 = 470024;
    //
    // /**
    // * 很抱歉，您没有权限修改会旗名
    // */
    // public static final int g470025 = 470025;
    //
    // /**
    // * 很抱歉，您没有权限升级会旗
    // */
    // public static final int g470026 = 470026;
    //
    // /**
    // * 请选择一个正确的新会旗造型
    // */
    // public static final int g470027 = 470027;
    //
    // /**
    // * 很抱歉，更换造型所需消耗的金钱不足或公会贡献资源不足
    // */
    // public static final int g470028 = 470028;
    //
    // /**
    // * 很抱歉，修改会旗名所需消耗的金钱不足或公会贡献资源不足
    // */
    // public static final int g470029 = 470029;
    //
    // /**
    // * 很抱歉，升级会旗所需消耗的金钱不足或公会贡献资源不足
    // */
    // public static final int g470030 = 470030;
    //
    // /**
    // * 请输入一个新的会旗名字
    // */
    // public static final int g470031 = 470031;
    //
    // /**
    // * 您的会旗等级已达顶级
    // */
    // public static final int g470032 = 470032;
    //
    // /**
    // * 很抱歉，您没有权限设置公会信息
    // */
    // public static final int g470033 = 470033;
    //
    // /**
    // * 很抱歉，您没有权限设置公会公告
    // */
    // public static final int g470034 = 470034;
    //
    // /**
    // * 很抱歉，您没有权限更改此职权
    // */
    // public static final int g470035 = 470035;
    //
    // /**
    // * 很遗憾，会旗升级失败
    // */
    // public static final int g470036 = 470036;
    //
    // /**
    // * 很抱歉，您没有权限设置外交关系
    // */
    // public static final int g470039 = 470039;
    //
    // /**
    // * 很抱歉，您无法将自己的公会列为联盟
    // */
    // public static final int g470040 = 470040;
    //
    // /**
    // * 该公会已经是您的联盟公会了
    // */
    // public static final int g470041 = 470041;
    //
    // /**
    // * 对方等级不足，无法邀请
    // */
    // public static final int g470043 = 470043;
    //
    // /**
    // * 公会结盟需对方会长同意，但他目前不在线
    // */
    // public static final int g470044 = 470044;
    //
    // /**
    // * 很抱歉，您无法将自己的公会列为敌对
    // */
    // public static final int g470045 = 470045;
    //
    // /**
    // * 很抱歉，请选择正确的旗帜
    // */
    // public static final int g470046 = 470046;
    //
    // /**
    // * 该公会已经是您的敌对公会
    // */
    // public static final int g470047 = 470047;
    //
    // /**
    // * 很抱歉,目标没有公会
    // */
    // public static final int g470048 = 470048;
    //
    // /**
    // * 您所在的公会每日活跃玩家不足，请多招募成员，并鼓励成员每日上线，否则您的公会将会于【{0}】日内解散。
    // */
    // public static final int g470049 = 470049;
    //
    // /**
    // * 很遗憾，{0}玩家拒绝了您的公会邀请
    // */
    // public static final int g470050 = 470050;
    //
    // /**
    // * 很抱歉,您没有权限设置外交关系
    // */
    // public static final int g470051 = 470051;
    //
    // /**
    // * 很遗憾，对方会长：{0}拒绝了您的结盟邀请
    // */
    // public static final int g470052 = 470052;
    //
    // /**
    // * 管理员职位已满
    // */
    // public static final int g470053 = 470053;
    //
    // /**
    // * 副会长职位已满
    // */
    // public static final int g470054 = 470054;
    //
    // /**
    // * 很抱歉，公会公告需要在100个汉字之间
    // */
    // public static final int g470055 = 470055;
    //
    // /**
    // * 您是该公会的敌对公会,如需结盟请先联系其会长:{0} 解除敌对关系
    // */
    // public static final int g470056 = 470056;
    //
    // /**
    // * 公会公告中包含敏感非法字符，修改失败
    // */
    // public static final int g470057 = 470057;
    //
    // /**
    // * {0}公会的会旗升级至{1}级，他们变得更强大了
    // */
    // public static final int g470058 = 470058;
    //
    // /**
    // * 很抱歉,公会贡献仓库中的{0}存量已达到上限
    // */
    // public static final int g470059 = 470059;
    //
    // /**
    // * 很抱歉,公会贡献仓库中的游戏币已达到上限
    // */
    // public static final int g470060 = 470060;
    //
    // /**
    // * 很抱歉,你的等级不足，无法申请加入公会
    // */
    // public static final int g470061 = 470061;
    //
    // /**
    // * 很抱歉,您无法邀请自己加入公会
    // */
    // public static final int g470062 = 470062;
    //
    // /**
    // * 请重新输入公会公告
    // */
    // public static final int g470063 = 470063;
    //
    // /**
    // * 您所在公会已解散
    // */
    // public static final int g470064 = 470064;
    //
    // /**
    // * 您所处的公会：{0}，被【{1}】，解散了。
    // */
    // public static final int g470065 = 470065;
    // /** 领地战期间不可转让 */
    // public static final int g470068 = 470068;
    //
    // /**
    // * {0}加入了公会,让我们一起欢迎他
    // */
    // public static final int g470100 = 470100;
    //
    // /**
    // * {0}被{1}逐出了公会
    // */
    // public static final int g470101 = 470101;
    //
    // /**
    // * {0}离开了我们的公会
    // */
    // public static final int g470102 = 470102;
    //
    // /**
    // * {0}被{1}任命为{2}
    // */
    // public static final int g470103 = 470103;
    //
    // /**
    // * 恭喜恭喜，您的公会与{0}公会结成联盟，并肩战斗
    // */
    // public static final int g470104 = 470104;
    //
    // /**
    // * 本公会已将{0}公会列为敌对公会
    // */
    // public static final int g470105 = 470105;

    // /**
    // * {0}公会已将本帮列为敌对公会
    // */
    // public static final int g470106 = 470106;
    //
    // /**
    // * 公会成员{0}在地图【{1}】被玩家【{2}】击败
    // */
    // public static final int g470107 = 470107;
    //
    // /**
    // * 公会成员{0}在地图【{1}】击败了玩家【{2}】
    // */
    // public static final int g470108 = 470108;
    //
    // /**
    // * 公会成员{0}在地图【{1}】击败BOSS【{2}】
    // */
    // public static final int g470109 = 470109;
    //
    // /**
    // * 公会成员{0}在地图【{1}】被BOSS【{2}】击败
    // */
    // public static final int g470110 = 470110;
    //
    // /**
    // * {0}向公会贡献仓库提交了：{1}，获得公会贡献点：{2}
    // */
    // public static final int g470111 = 470111;
    //
    // /**
    // * 会长启用了新的会旗名称：{0}，消耗公会贡献仓库：金币:{1},物品:{2}
    // */
    // public static final int g470112 = 470112;
    //
    // /**
    // * 会长启用了新的会旗造型，消耗公会贡献仓库：金币:{0},物品:{1}
    // */
    // public static final int g470113 = 470113;
    //
    // /**
    // * 公会旗帜升级到{0}级，消耗公会贡献仓库：金币:{1},物品:{2}
    // */
    // public static final int g470114 = 470114;
    //
    // /**
    // * 成功占领[【{0}】地图]的旗帜，消耗公会贡献仓库：{1}
    // */
    // public static final int g470115 = 470115;
    //
    // /**
    // * 同盟公会[{0}]解散了
    // */
    // public static final int g470116 = 470116;
    //
    // /**
    // * 敌对公会[{0}]解散了
    // */
    // public static final int g470117 = 470117;
    //
    // /**
    // * [{0}]解除了与本帮之间的联盟关系
    // */
    // public static final int g470118 = 470118;
    //
    // /**
    // * {0}向公会贡献仓库提交了：{1}游戏币，获得公会贡献点：{2}
    // */
    // public static final int g470119 = 470119;
    //
    // /**
    // * [公会]{0}将[公会]{1}列入了敌对公会名单
    // */
    // public static final int g470120 = 470120;
    //
    // /**
    // * [公会]{0}与[公会]{1}结成联盟，江湖风起云涌
    // */
    // public static final int g470121 = 470121;
    //
    // /**
    // * 江湖风起云涌，一个全新的公会：【{0}】成立了
    // */
    // public static final int g470123 = 470123;
    //
    // /**
    // * 您拒绝了{0}公会的结盟请求
    // */
    // public static final int g470124 = 470124;
    //
    // /**
    // * 您拒绝了来自{0}公会的加入公会邀请
    // */
    // public static final int g470125 = 470125;
    //
    // /**
    // * [{0}]添加了与本公会之间的敌对关系
    // */
    // public static final int g470126 = 470126;
    //
    // /**
    // * [{0}]解除了与本公会之间的敌对关系
    // */
    // public static final int g470127 = 470127;
    //
    // /**
    // * [{0}]解除了与本公会之间的同盟关系
    // */
    // public static final int g470128 = 470128;
    // /**
    // * 很抱歉，您背包中的{0}令数量不足
    // */
    // public static final int g470038 = 470038;
    //
    // /**
    // * 三界争霸期间不可解散公会
    // */
    // public static final int g470066 = 470066;
    //
    // /**
    // * 三界争霸期间不可更改会中职务
    // */
    // public static final int g470067 = 470067;
    //
    // /**
    // * 领地战期间不可解散帮派
    // */
    // public static final int g470069 = 470069;
    public static final Object[] g430201 = new Object[] { 0, 430201 };

    // ********************************************任务系统*********************************
    /** 任务不能交付 */
    public static final Object[] TASK_NO_COMPLETE = new Object[] { 0, 480000 };
    /** 任务物品不足够，不能交付 */
    public static final Object[] TASK_NO_COMPLETE_NO_ITEM = new Object[] { 0, 480001 };

    /** 480010 呜呜~当年不是说好不抛弃不放弃的么 */
    public static final Object[] TASK_ABANDON_FORBIDDEN = new Object[] { 0, 480010 };
    /** 480011 亲，你要放弃神马，你是要放弃治疗么 */
    public static final Object[] TASK_STATE_ABANDON_FORBIDDEN = new Object[] { 0, 480011 };
    /** 480015 当前任务不允许一键完成 */
    public static final Object[] TASK_DUMMY_FINISH_FORBIDDEN = new Object[] { 0, 480015 };
    /** 480016 当前任务状态不允许完成 */
    public static final Object[] TASK_STATE_DUMMY_FINISH_FORBIDDEN = new Object[] { 0, 480016 };

    /** 480020 没有该完成任务的倍率 */
    public static final Object[] TASK_PAY_ILLEGAL_MULTI = new Object[] { 0, 480020 };
    /** 任务重复接取 */
    public static final Object[] TASK_DUPLICATED_RECEIVE = new Object[] { 0, 480072 };
    /** 任务已完成 */
    public static final Object[] TASK_COMPLETED = new Object[] { 0, 480074 };
    /** 接取任务条件不满足[前置声望不足] */
    public static final Object[] BEFORE_SHENG_NOT_ENGOUHT = new Object[] { 0, 480075 };
    /** 接取任务条件不满足[杀怪数不足] */
    public static final Object[] BEFORE_KILL_MONSTER_NOT_ENGOUHT = new Object[] { 0, 480076 };
    /** 接取任务条件不满足[前置任务未做] */
    public static final Object[] BEFORE_TASK_NOT_EXIT = new Object[] { 0, 480077 };
    /** 当前任务已经完成，并且已经交付 */
    public static final Object[] TASK_COMPLETED_EXIST = new Object[] { 0, 480078 };
    /** 没有该任务 */
    public static final Object[] NO_TASK = new Object[] { 0, 480079 };
    /** 接取任务条件不满足[职业不符] */
    public static final Object[] BEFORE_JOB_NOT_MATCH = new Object[] { 0, 480080 };
    /** 接取任务条件不满足[没有解救NPC] */
    public static final Object[] BEFORE_RESCUE_NOT_FINISH = new Object[] { 0, 480081 };
    /** 接取任务条件不满足[采集数量不足] */
    public static final Object[] BEFORE_GATHER_NOT_ENOUGH = new Object[] { 0, 480082 };
    /** 接取任务条件不满足[道具数量不足] */
    public static final Object[] BEFORE_ITEM_NOT_ENOUGH = new Object[] { 0, 480083 };
    /** 接取任务条件不满足[副本没有完成] */
    public static final Object[] BEFORE_INSTANCE_NOT_ENOUGH = new Object[] { 0, 480084 };
    /** 接取任务条件不满足[道具没有使用] */
    public static final Object[] BEFORE_ITEM_NOT_USED = new Object[] { 0, 480085 };
    /** 接取任务条件不满足[等级不满足] */
    public static final Object[] BEFORE_LEVEL_NOT_ENOUGH = new Object[] { 0, 480086 };
    /** 接取任务条件不满足[战斗力不满足] */
    public static final Object[] BEFORE_POWER_NOT_ENOUGH = new Object[] { 0, 480087 };
    /** 接取任务条件不满足[装备强化等级不满足] */
    public static final Object[] BEFORE_EQUIP_STRENGTH_NOT_ENOUGH = new Object[] { 0, 480088 };
    /** 接取任务条件不满足[坐骑进阶不满足] */
    public static final Object[] BEFORE_HORSE_RANK_NOT_ENOUGH = new Object[] { 0, 480089 };
    /** 接取任务条件不满足[战阶等级不满足] */
    public static final Object[] BEFORE_QUALIFICATION_LEVEL_NOT_ENOUGH = new Object[] { 0, 480090 };
    /** 接取任务条件不满足[披风进阶不满足] */
    public static final Object[] BEFORE_CLOAK_RANK_NOT_ENOUGH = new Object[] { 0, 480091 };
    /** 接取任务条件不满足[全身宝石等级不满足] */
    public static final Object[] BEFORE_EQUIP_JEWELS_NOT_ENOUGH = new Object[] { 0, 480092 };
    /** 接取任务条件不满足[没有进行首充] */
    public static final Object[] BEFORE_FIRST_CHARGE_NOT_ENOUGH = new Object[] { 0, 480093 };
    /** 你没公会还来刷新获取公会任务,你是在用挂吧 */
    public static final Object[] NOT_GUILD_REFRESH_GUILD_TASK = new Object[] { 0, 480100 };
    /** 同一天不能多次刷新 */
    public static final Object[] SAME_DAY_NOT_REFRESH_AGAIN = new Object[] { 0, 480101 };
    /** 未领取任务，无法刷新 */
    public static final Object[] CANNOT_REFRESH_WITHOUT_ACCEPT_TASK = new Object[] { 0, 480102 };
    /** 公会任务已完成，不可以刷新 */
    public static final Object[] CANNOT_REFRESH_BECAUSE_TASK_FINISHED = new Object[] { 0, 480103 };
    /** 480120 任务{0}还没有完成 */
    public static final int SPECIFY_TASK_UNFINISHED = 480120;

    // ********************************************地图系统*********************************
    /** 铜钱不足 */
    public static final Object[] TELEPORT_MONEY_ERROR = new Object[] { 0, 490001 };
    /** 等级不足 */
    public static final Object[] TELEPORT_MIN_LEVEL_ERROR = new Object[] { 0, 490002 };
    /** 等级过高 */
    public static final Object[] TELEPORT_MAX_LEVEL_ERROR = new Object[] { 0, 490003 };
    /** 道具不足 */
    public static final Object[] TELEPORT_NEED_ITEM_ERROR = new Object[] { 0, 490004 };
    /** 道具不足 */
    public static final Object[] TELEPORT_MUST_ITEM_ERROR = new Object[] { 0, 490005 };
    // /** 指定任务未完成 */
    // public static final Integer TELEPORT_TASK_COMPLETE_ERROR = 490006;
    // /** 未在规定时间内 */
    // public static final Integer TELEPORT_TIME_ERROR = 490007;
    // /** 无此线路 */
    // public static final Integer TELEPORT_LINE_ERROR = 490008;
    /** 您已在此线路 */
    public static final Object[] TELEPORT_SAME_LINE_ERROR = new Object[] { 0, 490009 };
    /** 很抱歉，您尚未脱离战斗状态，无法进行传送 */
    public static final Object[] TELEPORT_IN_FIGHTING_ERROR = new Object[] { 0, 490010 };
    /** 死亡状态下不允许传送 */
    public static final Object[] TELEPORT_IN_DEAD_ERROR = new Object[] { 0, 490011 };
    // public static final Integer TELEPORT_IN_PK_BATTLE = 490012;
    // /** 和玩家pk状态下不能使用回城卷轴 */
    // public static final Integer TELEPORT_IN_PK_ERROR = 490014;
    /** 很抱歉，不可以传送到目标场景 */
    public static final Object[] Error_490015 = new Object[] { 0, 490015 };
    // ********************************************坐骑模块*********************************
    // /** 520001 坐骑阶层已为最大阶层 */
    // public static final int ZUOQI_MAX_RANK = 520001;
    /** 520004 坐骑阶数不足 */
    public static final Object[] RANK_NOT_ENOUGH = new Object[] { 0, 520004 };
    /** 520005 当前无坐骑 */
    public static final Object[] NO_ZUOQI = new Object[] { 0, 520005 };
    // /** 520006 加载坐骑失败 */
    // public static final int LOAD_ERROR = 520006;
    // /** 520007 物品不足 */
    // public static final int GOODS_ADEQUATE = 520007;
    // /** 520008 使用该物品等级不够 */
    // public static final int GOODS_LEVEL = 520008;
    // /** 520009 此物品不是技能书 */
    // public static final int ERROR_520009 = 520009;
    // /** 520010 你还未开启技能格位 */
    // public static final int ERROR_520010 = 520010;
    /** 使用已经达到上限 */
    public static final Object[] ERROR_520011 = new Object[] { 0, 520011 };
    // /** 520012 你已经学习了此技能 */
    // public static final int ERROR_520012 = 520012;
    /** 坐骑进阶广播 */
    public static final int ERROR_520013 = 520013;

    /** 520016 坐骑幻化还没激活 */
    public static final int ERROR_520016 = 520016;
    /** 520017 所在场景不允许上坐骑 */
    public static final Object[] ERROR_520017 = new Object[] { 0, 520017 };
    /** 520018 {0}阶{1}星的坐骑只能使用{2}个{3} */
    public static final int ERROR_520018 = 520018;
    /** 520019 道具什么属性都没有增加 */
    public static final Object[] ERROR_520019 = new Object[] { 0, 520019 };
    /** 520020 坐骑幻化还没激活或者已过期 */
    public static final Object[] ERROR_520020 = new Object[] { 0, 520020 };
    // /** 没有返还奖励可领取 */
    // public static final int ERROR_520020 = 520020;
    // /** 没有美人奖励可领取 */
    // public static final int ERROR_520021 = 520021;
    // /** 已领取进阶返还 */
    // public static final int ERROR_520022 = 520022;
    // /** 已领取美人奖励 */
    // public static final int ERROR_520023 = 520023;
    // /** 活动已过期 */
    // public static final int ERROR_520024 = 520024;
    /** 521005 当前无披风 */
    public static final Object[] NO_CLOAK = new Object[] { 0, 521005 };

    /** 521008 披风幻化还没激活 */
    public static final Object[] ERROR_521008 = new Object[] { 0, 521008 };
    /** 521009 披风阶数不足 */
    public static final Object[] ERROR_521009 = new Object[] { 0, 521009 };
    /** 521010 {0}阶{1}星的披风只能使用{2}个{3} */
    public static final int ERROR_521010 = 521010;

    /** 521011 当前已激活的幻化坐骑已过期,请选择未过期的幻化坐骑 */
    public static final Object[] ERROR_521011 = new Object[] { 0, 521011 };
    /** 521012 当前已激活的幻化披风已过期,请选择未过期的幻化披风 */
    public static final Object[] ERROR_521012 = new Object[] { 0, 521012 };

    // ********************************************宝石升级*********************************
    // /** 宝石已激活 */
    // public static final Integer HAS_JIHUO = 530001;
    // /** 不能操作 */
    // public static final Integer CANNT_OPERATE = 530002;
    /** 天赋点不足 */
    public static final Object[] CAILIAO_NOT_ENOUGH_j = new Object[] { 0, 530003 };
    // /** 激活真气不足 */
    // public static final Integer ZHENQI_NOT_ENOUGH_J = 530004;
    // /** 需要激活先 */
    // public static final Integer NEED_JIHUO = 530005;
    // /** 已是顶级 */
    // public static final Integer TOP_LEVEL = 530006;
    // /** 升级材料不足 */
    // public static final Integer CAILIAO_NOT_ENOUGH_U = 530007;
    // /** 升级真气不足 */
    // public static final Integer ZHENQI_NOT_ENOUGH_U = 530008;
    // /** 宝石总等级不足 */
    // public static final Integer BAOSHI_LEVEL_LESS = 530010;
    // /** 宝石升级全服广播 */
    // public static final Integer BAOSHI_NOTIFY = 530011;
    // /** 加载配置错误 */
    // public static final int LOAD_CONFIG_ERROR = 520019;

    // ********************************************神树系统*********************************
    // /** 很抱歉，等级不足{0}级 */
    // public static final int s540001 = 540001;
    // /** 暂无果实干旱，无需浇水 */
    // public static final int s540002 = 540002;
    // /** 该果实已经成熟，无需浇灌仙露 */
    // public static final int s540003 = 540003;
    // /** 该奇异果已成熟，请采摘 */
    // public static final int s540004 = 540004;
    // /** 暂无果实成熟，推荐催熟奇异果 */
    // public static final int s540005 = 540005;
    // /** 为{0}的果实浇水成功，您的人物经验+{1} */
    // public static final int s540006 = 540006;
    // /** 浇水成功，获得经验：{0} */
    // public static final int s540007 = 540007;
    // /** 灵果产量保底了，请手下留情 */
    // public static final int s540008 = 540008;
    // /** 您已经抢收过一次了，一个果实只能抢收一次 */
    // public static final int s540009 = 540009;
    // /** 今日仙露浇灌次数已用完 */
    // public static final int s540010 = 540010;
    // /** 请稍后进行浇灌 */
    // public static final int s540011 = 540011;
    // /** 很抱歉，元宝不足，催熟失败 */
    // public static final int s540012 = 540012;
    // /** 为{0}的果实浇水成功，您的神树浇水经验今日已达到上限 */
    // public static final int s540013 = 540013;
    // /** 浇水成功，您的神树浇水经验今日已达到上限 */
    // public static final int s540014 = 540014;
    // /** 今日抢收果实次数达到上限 */
    // public static final int s540015 = 540015;
    // /** 【{0}】玩家在生命神树中摘取了【{1}】灵果，获得了：【{2}】 */
    // public static final int s540100 = 540100;
    // /** 【{0}】玩家在生命神树中摘取了【{1}】灵果，获得了：强化+【{2}】的{3}【{4}】 */
    // public static final int s540101 = 540101;
    // /** 您的【{0}】被【{1}】抢收了一部分产量，为此您获得了【经验+{2}】的补偿 */
    // public static final int s540103 = 540103;
    // /** 您抢收了【{0}】的【{1}】中的【{2}】,真是手疾眼快,兵贵神速啊 */
    // public static final int s540104 = 540104;
    // /** 您的【{0}】干旱了,【{1}】为您进行了友情浇水 */
    // public static final int s540106 = 540106;
    // /** 【{0}】的【{1}】干旱,您为其友情浇灌,您为此获得了经验奖励【{2}】 */
    // public static final int s540107 = 540107;

    // ********************************************排行榜*********************************
    // /** 很抱歉，您今天已经向该玩家赠送过花朵了 */
    // public static final String r583000 = "583000";
    // /** 很抱歉，您今日赠花数量已达上限：{0}次 */
    // public static final String r583001 = "583001";
    // /** 很抱歉，您今天已经向该玩家赠送过鸡蛋了 */
    // public static final String r583002 = "583002";
    // /** 很抱歉，您今日赠鸡蛋数量已达上限：{0}次 */
    // public static final String r583003 = "583003";
    // /** 很抱歉，您搜索的关键字没有进入排行榜 */
    // public static final String r583004 = "583004";
    // /** 很抱歉，您还没有进入排行榜 */
    // public static final String r583005 = "583005";

    // ********************************************奖励系统*********************************
    // /** 今日您已签到过 */
    // public static final String j592000 = "592000";
    /** 奖励已领取 */
    public static final Object[] j592001 = new Object[] { 0, 592001 };
    // /** 签到次数不足,不能领取奖励 */
    // public static final String j592002 = "592002";
    /** 您已领取今天全部在线时间随机奖励 */
    public static final Object[] j592003 = new Object[] { 0, 592003 };
    /** 继续保持在线您才可以摇奖 */
    public static final Object[] j592004 = new Object[] { 0, 592004 };
    /** 您上个月的奖励已领取，本月奖励在下月5-10号之间可领取 */
    public static final Object[] j592005 = new Object[] { 0, 592005 };
    /** 很抱歉,您上个月没有累计绑定元宝 */
    public static final Object[] j592006 = new Object[] { 0, 592006 };

    /** 592016 您今天已经领过该奖励了，明天再来吧，么么哒~~ */
    public static final Object[] Error_592016 = new Object[] { 0, 592016 };

    /** 没有找到对应的资源 */
    public static final Object[] Error_592020 = new Object[] { 0, 592020 };
    /** 该资源已全部完成 */
    public static final Object[] Error_592021 = new Object[] { 0, 592021 };
    /** 所有资源已全部完成 */
    public static final Object[] Error_592022 = new Object[] { 0, 592022 };

    /** 592030 持续保持每天上线才可以领奖 */
    public static final Object[] Error_592030 = new Object[] { 0, 592030 };
    /** 592031 该天的奖励已经领取了 */
    public static final Object[] Error_592031 = new Object[] { 0, 592031 };
    // /** 很抱歉,没有找到对应的VIP配置 */
    // public static final String j592007 = "592007";
    // /** 很抱歉,当前还不能领取VIP奖励 */
    // public static final String j592008 = "592008";
    // /** 成为高级vip享受更多补签特权 */
    // public static final String j592009 = "592009";
    // /** 已签到过无需补签 */
    // public static final String j592010 = "592010";
    // /** 592011 不是微端登录，不能拿取奖励 */
    // public static final String j592011 = "592011";
    // /** 592012 你已经领取了微端奖励 */
    // public static final String j592012 = "592012";
    // /** 592013 你已经领取了该奖励 */
    // public static final String j592013 = "592013";
    // /** 592014 活动已经过期 */
    // public static final String j592014 = "592014";

    // ********************************************拜仙系统*********************************
    // /** 已达最大次数 */
    // public static final Integer MAXCOUNT = 595001;

    // ********************************************国战系统*********************************
    // /** 30分钟后开启 */
    // public static final Integer FENGSHENZHAN_596001 = 596001;
    // /** 10分钟后开启 */
    // public static final Integer FENGSHENZHAN_596002 = 596002;
    // /** 开启 */
    // public static final Integer FENGSHENZHAN_596003 = 596003;
    // /***/
    // public static final Integer FENGSHENZHAN_596006 = 596006;
    // /** 金币不足 */
    // public static final Integer FENGSHENZHAN_596007 = 596007;
    // /** 拿起玉玺 */
    // public static final Integer FENGSHENZHAN_596008 = 596008;
    // /** 被别人杀了 */
    // public static final Integer FENGSHENZHAN_596009 = 596009;
    // /** 时间满20分钟，结束 */
    // public static final Integer FENGSHENZHAN_596010 = 596010;
    // /** 攻城结束 */
    // public static final Integer FENGSHENZHAN_596011 = 596011;
    // /** 副本时间到，结束 */
    // public static final Integer FENGSHENZHAN_596013 = 596013;
    // /** 没有王城之主 */
    // public static final Integer FENGSHENZHAN_596014 = 596014;
    // /***/
    // public static final Integer FENGSHENZHAN_596020 = 596020;
    // /***/
    // public static final Integer FENGSHENZHAN_NOSTART = 596021;
    // /***/
    // public static final Integer FENGSHENZHAN_INCOPY = 596022;
    // /** 玉玺没了 */
    // public static final Integer FENGSHENZHAN_596023 = 596023;
    // /** 玉玺正在被他人操作 */
    // public static final Integer FENGSHENZHAN_596024 = 596024;
    // /** 操作非法 */
    // public static final Integer FENGSHENZHAN_596025 = 596025;
    // /** 正在采集，时间还没到 */
    // public static final Integer FENGSHENZHAN_596026 = 596026;
    // /** 玉玺回归 */
    // public static final Integer FENGSHENZHAN_596027 = 596027;
    // /** 封神战结束 **/
    // public static final Integer FENGSHENZHAN_596028 = 596028;
    // /** 进入等级不足 **/
    // public static final Integer FENGSHENZHAN_596034 = 596034;
    // /** 没有配置无法领取 */
    // public static final Integer FENGSHENZHAN_596030 = 596030;
    // /** 不是王城之主无法领取 */
    // public static final Integer FENGSHENZHAN_596035 = 596035;
    // /** 没有官职，不可领取 */
    // public static final Integer FENGSHENZHAN_596036 = 596036;
    // /** 奖励被领过 */
    // public static final Integer FENGSHENZHAN_596031 = 596031;
    // /** 背包格位不足 */
    // public static final Integer FENGSHENZHAN_596032 = 596032;
    // /** 今天已领过奖励 **/
    // public static final Integer FENGSHENZHAN_596033 = 596033;
    // /** 没有奖励可领取 */
    // public static final Integer FENGSHENZHAN_596040 = 596040;
    // /** 时间不足，无法领取 */
    // public static final Integer FENGSHENZHAN_596041 = 596041;
    // /** 三天内换过帮会 */
    // public static final Integer FENGSHENZHAN_596042 = 596042;
    // /** 当前不在拔旗 */
    // public static final Integer FENGSHENZHAN_596043 = 596043;
    // /** 修改失败，公会铜钱不足1000万 */
    // public static final Integer FENGSHENZHAN_596044 = 596044;
    // /** 不是王城之主无法操作 */
    // public static final Integer FENGSHENZHAN_596045 = 596045;
    // /** 国号广播 */
    // public static final Integer FENGSHENZHAN_596046 = 596046;
    // /** 有敏感字 */
    // public static final Integer FENGSHENZHAN_596047 = 596047;

    // ********************************************矿战*********************************
    // /** 可采集次数已用尽 */
    // public static final Integer b597000 = 597000;
    /** 宝藏正在被采集 */
    public static final Object[] b597001 = new Object[] { 0, 597001 };
    /** 采集异常 */
    public static final Object[] b597002 = new Object[] { 0, 597002 };
    // /** 宝藏大门已关闭 */
    // public static final Integer b597004 = 597004;
    /** 您正在采集中 */
    public static final Object[] b597005 = new Object[] { 0, 597005 };
    // /** 宝藏活动未开启 */
    // public static final Integer b597006 = 597006;
    /** 很抱歉,等级不足 */
    // public static final Integer b597007 = 597007;
    // /** 很抱歉，请进入符合等级条件的宝藏线路 */
    // public static final Integer b597008 = 597008;
    /** 很抱歉，采集时间不足,请重新采集 */
    public static final Object[] b597009 = new Object[] { 0, 597009 };
    /** 采集点不存在 */
    public static final Object[] b597010 = new Object[] { 0, 597010 };
    // /** 当前等级不足尚未开启宝藏功能 */
    // public static final Integer b597011 = 597011;

    /** 597012 小样，你等级不够，再修炼几年再来采集吧 */
    public static final Object[] ERROR_597012 = new Object[] { 0, 597012 };

    // ********************************************属性丹*********************************
    // /** 85001 属性丹使用已经达到上线 */
    // public static final Integer ERROR_85001 = 851003;

    // ********************************************礼品码功能*********************************
    // /** 无效 */
    // public final static Integer VALID = 550001;
    // /** 已被使用 */
    // public final static Integer USED = 550002;
    // /** 背包满，领取失败 */
    // public final static Integer FULL = 550004;
    // /** 不可重复领取 */
    // public final static Integer REPEAT = 550003;

    // ********************************************礼包*********************************

    // ********************************************VIP*********************************
    /** 621001 今日已经领取过了 */
    public static final Object[] Error_621001 = new Object[] { 0, 621001 };
    /** 621002 你的vip等级不能购买礼包 */
    public static final Object[] Error_621002 = new Object[] { 0, 621002 };
    /** 610006 已经领取过该等级礼包了 */
    public static final Object[] ERRO_620006 = new Object[] { 0, 620006 };
    /** 621010 别闹，哥没有礼包给你 */
    public static final Object[] Error_621010 = new Object[] { 0, 621010 };
    /** 621011 前置权限没有开启 */
    public static final Object[] Error_621011 = new Object[] { 0, 621011 };
    /** 621012 vip等级不足，不能开启该特权 */
    public static final Object[] Error_621012 = new Object[] { 0, 621012 };
    /** 621013 当前权限已经开启 */
    public static final Object[] Error_621013 = new Object[] { 0, 621013 };
    /** 621014 权限的前置功能没有开启 */
    public static final Object[] Error_621014 = new Object[] { 0, 621014 };
    /** 621015 特权不存在 */
    public static final Object[] Error_621015 = new Object[] { 0, 621015 };
    /** 621016 已经有vip特权了，不需要体验vip特权 */
    public static final Object[] Error_621016 = new Object[] { 0, 621016 };
    /** 621017 已经领过体验vip了，不能重复领取 */
    public static final Object[] Error_621017 = new Object[] { 0, 621017 };
    /** 621018 找不到对应的特权卡 */
    public static final Object[] Error_621018 = new Object[] { 0, 621018 };
    /** 621019 没有激活对应的特权卡 */
    public static final Object[] Error_621019 = new Object[] { 0, 621019 };
    /** 621020 已经领取过对应特权卡的礼包 */
    public static final Object[] Error_621020 = new Object[] { 0, 621020 };
    /** 621021 没有对应特权卡的礼包 */
    public static final Object[] Error_621021 = new Object[] { 0, 621021 };
    /** 621022 特权卡没有绑定钻石奖励 */
    public static final Object[] Error_621022 = new Object[] { 0, 621022 };
    /** 621023 购买特权卡可以开放此功能 */
    public static final Object[] Error_621023 = new Object[] { 0, 621023 };

    // -------------------家园系统----------------------
    /** 还没开始训练 */
    public static final Object[] Error_622001 = new Object[] { 0, 622001 };
    /** 提升VIP等级才可以使用训练场 */
    public static final Object[] Error_622002 = new Object[] { 0, 622002 };
    /** 今日训练场经验已满 */
    public static final Object[] Error_622003 = new Object[] { 0, 622003 };
    /** 提升VIP等级可以使用地精财务行 */
    public static final Object[] Error_622010 = new Object[] { 0, 622010 };
    /** 你这是要兑换，还是想吃霸王餐 */
    public static final Object[] Error_622011 = new Object[] { 0, 622011 };
    /** 622012 今日兑换超过上限 */
    public static final Object[] Error_622012 = new Object[] { 0, 622012 };
    /** 622013 累积提取点才可以提取绑定钻石 */
    public static final Object[] Error_622013 = new Object[] { 0, 622013 };
    /** 622014 地精财务行没有绑定钻石 */
    public static final Object[] Error_622014 = new Object[] { 0, 622014 };
    /** 622020 提升VIP等级才可以可以领取礼品 */
    public static final Object[] Error_622020 = new Object[] { 0, 622020 };
    /** 622021 礼品列表已过期 */
    public static final Object[] Error_622021 = new Object[] { 0, 622021 };
    /** 622022 不存在的礼品 */
    public static final Object[] Error_622022 = new Object[] { 0, 622022 };
    /** 622023 该礼品已经领取或者转送了 */
    public static final Object[] Error_622023 = new Object[] { 0, 622023 };
    /** 622024 今日领取已达上限，提升VIP等级或者明天再来吧 */
    public static final Object[] Error_622024 = new Object[] { 0, 622024 };

    /** 鉴定-没有可鉴定的物品: 623001 */
    public static final Object[] Error_623001 = new Object[] { 0, 623001 };

    /** 商铺-商铺还未开放: 624001 */
    public static final Object[] Error_624001 = new Object[] { 0, 624001 };
    /** 商铺-CD还在冷却中: 624002 */
    public static final Object[] Error_624002 = new Object[] { 0, 624002 };
    /** 商铺-没有合适的售卖物品: 624003 */
    public static final Object[] Error_624003 = new Object[] { 0, 624003 };
    /** 商铺-CD没有在冷却: 624004 */
    public static final Object[] Error_624004 = new Object[] { 0, 624004 };
    /** 商铺-购买次数已经达到上限: 624005 */
    public static final Object[] Error_624005 = new Object[] { 0, 624005 };

    /** 藏宝阁-购买次数已经达到购买上限: 625001 */
    public static final Object[] Error_625001 = new Object[] { 0, 625001 };
    /** 藏宝阁-{0}货币消耗已经达到购买上限: 625002 */
    public static final int Error_625002 = 625002;
    // ********************************************职业资质*********************************
    /** 战勋值不足 */
    public static final Object[] WAREMBLEM_NOT_ENOUGHT = new Object[] { 0, 690005 };
    /** 激活战阶碎片所需的道具不足 */
    public static final Object[] ERROR_690006 = new Object[] { 0, 690006 };

    // ********************************************活动*********************************
    // /** 很抱歉,领取条件不足 */
    // public final static Integer k792001 = 792001;
    // /** 奖励已领取过 */
    // public final static Integer k792002 = 792002;
    // /** 活动已过期 */
    // public final static Integer k792003 = 792003;
    // /** 很抱歉,限量物品数量不足 */
    // public final static Integer k792004 = 792004;
    // /** 很抱歉,配置找不到 */
    // public final static Integer k792005 = 792005;
    // /** 今日领奖次数已达到上限 */
    // public final static Integer k792006 = 792006;
    // /** 领奖次数已达到上限 */
    // public final static Integer k792007 = 792007;
    // /** 今日购买次数已达到上限 */
    // public final static Integer k792008 = 792008;
    // /** 购买次数已达到上限 */
    // public final static Integer k792009 = 792009;
    // /** 配置不存在 */
    // public final static Integer k792020 = 792020;
    // /** 不可重复领取 */
    // public final static Integer k792021 = 792021;
    // /** 不可领取 */
    // public final static Integer k792022 = 792022;
    // /** 已过期，不可领取 */
    // public final static Integer k792023 = 792023;
    // /** 领取次数已满 */
    // public final static Integer k792024 = 792024;
    // /** 所有奖励已经领完 */
    // public final static Integer k792010 = 792010;
    // /** 领取时间不足 */
    // public final static Integer k792011 = 792011;
    // /** 不在名单内，请联系客服QQ验证 */
    // public static final String NOTIN_LIST = "794000";
    // /** 很抱歉，这个活动未配置 */
    // public static final String NOT_CONFIG = "794002";

    // ********************************************投保系统*********************************
    /** 已经投资了月卡银行 */
    public final static Object[] Error_793000 = new Object[] { 0, 793000 };
    /** 您已经领取过该返利，无法再次领取 */
    public final static Object[] Error_793001 = new Object[] { 0, 793001 };
    /** 返利的领取时间已错过或尚未到达 */
    public final static Object[] Error_793002 = new Object[] { 0, 793002 };
    // /** 该返利的全部数额已经领取，无法补领 */
    // public final static String t793004 = "793004";
    // /** 很抱歉,您还没投资,无法领取返利 */
    // public final static Object[] t793005 = new Object[] { 0, 793005 };

    // ********************************************国家系统系统*********************************

    /** 很抱歉,您今天的每日功勋排行奖励已领取过 */
    public final static Object[] Error_800000 = new Object[] { 0, 800000 };
    /** 很抱歉,您当前功勋阶段奖励已领取完毕 */
    public final static Object[] Error_800001 = new Object[] { 0, 800001 };
    /** 很抱歉,您当前时间点国家资源奖励未结算. */
    public final static Object[] Error_800002 = new Object[] { 0, 800002 };
    /** 很抱歉,您当前时间点国家资源奖励已领取过 */
    public final static Object[] Error_800003 = new Object[] { 0, 800003 };
    /** 很抱歉,当前操作您权限不够 */
    public final static Object[] Error_800004 = new Object[] { 0, 800004 };
    /** 很抱歉,国家当前职位的官员令已使用完毕 */
    public final static Object[] Error_800005 = new Object[] { 0, 800005 };
    /** 很抱歉,您 昨日无排名,无奖励 */
    public final static Object[] Error_800006 = new Object[] { 0, 800006 };
    /** 很抱歉,您未加入公会 */
    public final static Object[] Error_800007 = new Object[] { 0, 800007 };
    /** 很抱歉,您当前不是公会会长 */
    public final static Object[] Error_800008 = new Object[] { 0, 800008 };
    /** 很抱歉,活动未到开启时间 */
    public final static Object[] Error_800009 = new Object[] { 0, 800009 };
    /** 很抱歉,当前国家名字包含敏感词 */
    public final static Object[] Error_800010 = new Object[] { 0, 800010 };
    /** 很抱歉,您已参加本次活动,不能重复报名 */
    public final static Object[] Error_800011 = new Object[] { 0, 800011 };
    /** 很抱歉,当前国家正处于战争状态下,不能修改国名 */
    public final static Object[] Error_800012 = new Object[] { 0, 800012 };
    /** 很抱歉,当前官员令在系统中不存在 */
    public final static Object[] Error_800013 = new Object[] { 0, 800013 };
    /** 很抱歉,当前官员令已失效 */
    public final static Object[] Error_800014 = new Object[] { 0, 800014 };
    /** 很抱歉,你当前场景下不能使用官员令 */
    public final static Object[] Error_800015 = new Object[] { 0, 800015 };
    /** 很抱歉,自己国家目前已是战斗状态，不能再进行攻击 */
    public final static Object[] Error_800016 = new Object[] { 0, 800016 };
    /** 很抱歉,当前目标国家已为战斗状态，不能再进行攻击 */
    public final static Object[] Error_800017 = new Object[] { 0, 800017 };
    /** 很抱歉,不在跨服战役场景下,不能对据点发起官员令操作 */
    public final static Object[] Error_800018 = new Object[] { 0, 800018 };
    /** 很抱歉,自己国家不能攻击自己国家 */
    public final static Object[] Error_800019 = new Object[] { 0, 800019 };
    /** 很抱歉,系统中未找到您将要任命人员的数据 */
    public final static Object[] Error_800020 = new Object[] { 0, 800020 };
    /** 很抱歉,非法的官员令响应 */
    public final static Object[] Error_800021 = new Object[] { 0, 800021 };
    /** 很抱歉,您当前无公会,不能发动骚扰战 */
    public final static Object[] Error_800022 = new Object[] { 0, 800022 };
    /** 很抱歉,您当前公会资金不够发动骚扰战 */
    public final static Object[] Error_800023 = new Object[] { 0, 800023 };
    /** 很抱歉,国家一次只能发布一次有效官员令,请等当前官员令失效后再发布 */
    public final static Object[] Error_80024 = new Object[] { 0, 800024 };
    /** 很抱歉,今天战役次数已达上限 */
    public final static Object[] Error_80025 = new Object[] { 0, 800025 };
    /** 很抱歉,国家战神不能被任命 */
    public final static Object[] Error_80026 = new Object[] { 0, 800026 };
    /** 很抱歉,国战目标国当前未配置游戏区不能发起国家战役 */
    public final static Object[] Error_80027 = new Object[] { 0, 800027 };
    /** 很抱歉,您当前不是国家官员无法设置复活据点 */
    public final static Object[] Error_80028 = new Object[] { 0, 800028 };
    /** 很抱歉,您只能在本国占有的据点设置复活据点 */
    public final static Object[] Error_80029 = new Object[] { 0, 800029 };
    /** 很抱歉,您当前国家已有一个复活据点,一个国家只能设置一个复活据点 */
    public final static Object[] Error_80030 = new Object[] { 0, 800030 };
    /** 很抱歉,复活据点只有在国战期间才能设置 */
    public final static Object[] Error_80031 = new Object[] { 0, 800031 };

    /** 恭喜您，国家名字成功修改为：{0} */
    public final static int Error_800100 = 800100;
    /** 国王：{0}，已将本国国名修改为：{1} */
    public final static int Error_800101 = 800101;
    /** {0} 已经被国王任命为{1}，举国欢庆恭贺 */
    public final static int Error_800102 = 800102;

    /** 我国向 {0} 国发动了灭国战,请全体国民进入作战状态！ */
    public final static int Error_800103 = 800103;
    /** {0} 国发动了对本国的灭国战,请全体国民全面防守！ */
    public final static int Error_800104 = 800104;

    // -----攻击方消息提示
    /** 我国消灭了 {0} 国的所有防守将领取得了此次灭国战的胜利 */
    public final static int Error_800105 = 800105;
    /** 灭国战限时结束未消灭敌国所有防守将领,本国被判战败 */
    public final static int Error_800106 = 800106;
    // -----防守方消息提示
    /** 灭国战限时结束,本国防守成功取得了胜利 */
    public final static int Error_800107 = 800107;
    /** 灭国战限时结束,本国所有将领被消灭，本国被判战败 */
    public final static int Error_800108 = 800108;

    /** 灭国战还剩余30分钟就将结算,请全力攻击 */
    public final static int Error_800109 = 800109;
    /** 灭国战还剩余10分钟就将结算,请全力防守 */
    public final static int Error_800110 = 800110;

    /** 我国防守将领 {0} 被消灭了 */
    public final static int Error_800111 = 800111;
    /** {0} 国防守将领 {1} 已经被消灭 */
    public final static int Error_800112 = 800112;
    /** 我国防守将领 {0} 受到了敌国攻击，请全体国民速速前往防御！前往防御 */
    public final static int Error_800113 = 800113;
    /** 本国正在攻击 {0} 国防守将领 {1},请全体国民速速前往攻击！前往攻击 */
    public final static int Error_800114 = 800114;

    /** {0} 国发动了对本国的骚扰战,请全体国民全面防守！ */
    public final static int Error_800120 = 800120;

    // -----攻击方消息提示
    /** 我国消灭了 {0} 国的所有防守将领取得了此次骚扰战的胜利 */
    public final static int Error_800121 = 800121;
    /** 骚扰战限时结束,未消灭敌国所有防守将领,本国被判战败 */
    public final static int Error_800122 = 800122;
    // -----防守方消息提示
    /** 骚扰战限时结束,本国防守成功取得了胜利 */
    public final static int Error_800123 = 800123;
    /** 骚扰战限时结束，本国所有将领被消灭，本国被判战败 */
    public final static int Error_800124 = 800124;

    /** [官职名+官员名字]发起了官员令,请国民积极响应！响应号令 */
    /** [{0} {1}]发起了官员令,请国民积极响应！响应号令 */
    public final static int Error_800130 = 800130;

    /** 灭国战争大门开启，请做好战前准备 */
    public final static int Error_800150 = 800150;
    /** 骚扰战大门开启，请做好战前准备 */
    public final static int Error_800151 = 800151;
    /** {0}公会赢得国家王权，各位国民向新国王 {1} 祝贺 */
    public final static int Error_800152 = 800152;

    // 国家关于被击杀消息
    // 本国"官员名.玩家名字"被"国家名.玩家名字"击败了!快快前往协助!
    /** 本国"{0}.{1}"被"{2}.{3}"击败了!快快前往协助! */
    public final static int Error_800153 = 800153;

    // 本国"玩家名字"击败了"国家名.官员名.玩家名字
    /** 本国"{0}"击败了"{1}.{2}.{3}" */
    public final static int Error_800154 = 800154;

    /** 当前系统中国家系统暂时未开放 */
    public final static Object[] Error_801000 = new Object[] { 0, 801000 };

    /** 提示当前任务还未结算，不能领取奖励 */
    public final static Object[] Error_801001 = new Object[] { 0, 801001 };
    /** 要领取奖的任务不存在 */
    public final static Object[] Error_801002 = new Object[] { 0, 801002 };
    /** 当前任务失败,并且无任何任务贡献，不能领取任务排名奖励 */
    public final static Object[] Error_801003 = new Object[] { 0, 801003 };
    /** 当前国家任务奖励今天已领取过 */
    public final static Object[] Error_801004 = new Object[] { 0, 801004 };
    /** 当前国家任务已结算 */
    public final static Object[] Error_801005 = new Object[] { 0, 801005 };
    /** 当前国家任务不是捐献任务,不能捐献 */
    public final static Object[] Error_801006 = new Object[] { 0, 801006 };

    /**  */
    public final static Object[] Error_801007 = new Object[] { 0, 801007 };
    /**  */
    public final static Object[] Error_801008 = new Object[] { 0, 801008 };
    /**  */
    public final static Object[] Error_801009 = new Object[] { 0, 801009 };
    /**  */
    public final static Object[] Error_801010 = new Object[] { 0, 801010 };
    /**  */
    public final static Object[] Error_801011 = new Object[] { 0, 801011 };
    /**  */
    public final static Object[] Error_801012 = new Object[] { 0, 801012 };
    /**  */
    public final static Object[] Error_801013 = new Object[] { 0, 801013 };
    /**  */
    public final static Object[] Error_801014 = new Object[] { 0, 801014 };
    /**  */
    public final static Object[] Error_801015 = new Object[] { 0, 801015 };
    /**  */
    public final static Object[] Error_801016 = new Object[] { 0, 801016 };

    // ********************************************广播系统*********************************
    /** 物品掉落广播 */
    public static final Integer GOODS_DROP_BROADCAST = 800400;

    // ------------ 时装 --------------------------------
    /** 没有时装信息 */
    public final static Object[] Error_900001 = new Object[] { 0, 900001 };
    /** 当前时装已经达到最大阶数 */
    public final static Object[] Error_900002 = new Object[] { 0, 900002 };
    /** 时装已经激活 */
    public final static Object[] Error_900003 = new Object[] { 0, 900003 };

    // ---------------------每日福利---------------------------
    /** 今日无法再次喝酒 */
    public static final Object[] SEDUCE_LESS_TIME = new Object[] { 0, 90998 };
    /** 目前没有可找回的离线经验 */
    public final static Object[] Error_90999 = new Object[] { 0, 90999 };
    /** 不可以重复签到 */
    public final static Object[] Error_91000 = new Object[] { 0, 91000 };
    /** 还没有达到该签到天数。 */
    public final static Object[] Error_91001 = new Object[] { 0, 91001 };
    /** 已领取过该签到天数的奖励 */
    public static final Object[] Error_91002 = new Object[] { 0, 91002 };
    /** 亲，今天还木有签到。 */
    public static final Object[] Error_91003 = new Object[] { 0, 91003 };
    /** 今天已经摇奖次数用光了 */
    public static final Object[] Error_91004 = new Object[] { 0, 91004 };
    /** 不存在的奖励 */
    public static final Object[] Error_91005 = new Object[] { 0, 91005 };
    /** 不存在的领取方式。 */
    public static final Object[] Error_91006 = new Object[] { 0, 91006 };

    /**
     * [font color='#FF0000']VIP等级不足[/font],[font
     * color='#FFFF00']{0}[/font][font color='#ffff00']可以补签前[/font][font
     * color='#FFFF00']{1}[/font][font color='#ffff00']天[/font]
     */
    public static final int Error_91007 = 91007;
    /** 免费饮酒次数已达上限 */
    public static final Object[] Error_91008 = new Object[] { 0, 91008 };
    /** 活跃度飞镖已经射完 */
    public static final Object[] Error_91009 = new Object[] { 0, 91009 };
    /** 获得活跃度才可以射飞镖 */
    public static final Object[] Error_91010 = new Object[] { 0, 91010 };
    /** 没有找到对应的活跃度 */
    public static final Object[] Error_91011 = new Object[] { 0, 91011 };
    /** 该项活跃度已经完成 */
    public static final Object[] Error_91012 = new Object[] { 0, 91012 };
    /** 该项活跃度不支持钻石完成 */
    public static final Object[] Error_91013 = new Object[] { 0, 91013 };
    /** 获得活跃度才可以领奖 */
    public static final Object[] Error_91014 = new Object[] { 0, 91014 };
    /** 已经领过对应的奖励了 */
    public static final Object[] Error_91015 = new Object[] { 0, 91015 };
    // ********************************************防沉迷*********************************
    // 防沉迷 （非0,errCode发送方式 不写成Object[]）
    public static final Integer CM_STAGE_0 = 91016;
    public static final Integer CM_STAGE_1 = 91017;
    public static final Integer CM_STAGE_3 = 91018;
    public static final Integer CM_STAGE_7 = 91019;
    // 防沉迷其他提示
    /** 防沉迷时间不可以接取任务。 */
    public static final Object[] Error_91020 = new Object[] { 0, 91020 };
    /** 防沉迷时间不可以交付任务。 */
    public static final Object[] Error_91021 = new Object[] { 0, 91021 };

    // 首冲
    /** 还不可以领奖 */
    public static final Object[] Error_91030 = new Object[] { 0, 91030 };
    /** 91031 找不到你的职业对应的首冲奖励 */
    public static final Object[] Error_91031 = new Object[] { 0, 91031 };

    /** 打坐已开启 */
    public static final Object[] MEDITITAION_WASOPEN = new Object[] { 0, 91032 };
    /** 打坐未开启 */
    public static final Object[] MEDITATION_UNOPEN = new Object[] { 0, 91033 };
    /** 此阶段活跃度抽奖未开启 */
    public static final Object[] AKTIVECO_NOT_OPEN = new Object[] { 0, 91034 };
    /** 激活码错误或已被使用 */
    public static final Object[] ACTIVE_CODE_USED = new Object[] { 0, 91035 };
    /** 打坐系统目前无法使用 */
    public static final Object[] MEDITATION_UNUSE = new Object[] { 0, 91036 };

    /** 没有感恩节配置 */
    public static final Object[] THINKSDAY_NO_CONFIG = new Object[] { 0, 91037 };
    /** 感恩节活动不在时间范围内 */
    public static final Object[] THINKSDAY_NO_TIME_SCOPE = new Object[] { 0, 91038 };
    /** 感恩节奖励配置出错 */
    public static final Object[] THINKSDAY_AWARD_ERROR = new Object[] { 0, 91039 };
    /** 感恩节奖励已领取过了 */
    public static final Object[] THINKSDAY_AWARD_END = new Object[] { 0, 91040 };
    /** 已使用过此种激活码,无法再次使用 */
    public static final Object[] ACTIVE_CODE_TYPE_USED = new Object[] { 0, 91041 };
    /** 要领取的地精宝箱类型错误 */
    public static final Object[] ELF_TREASURE_ERROR_TYPE = new Object[] { 0, 91042 };
    /** 不存在的地精宝箱 */
    public static final Object[] ELF_TREASURE_UNFIND = new Object[] { 0, 91043 };
    /** 地精宝箱已过期 */
    public static final Object[] ELF_TREASURE_EXPIRED = new Object[] { 0, 91044 };

    // ---------------------------------王者之路----------------------------------
    /** 91100 条件尚未达成，不可以领取奖励 */
    public static final Object[] Error_91100 = new Object[] { 0, 91100 };
    /** 91101 奖励已经领取，不可以重复领奖 */
    public static final Object[] Error_91101 = new Object[] { 0, 91101 };
    /** 91102 没有找到对应的奖励 */
    public static final Object[] Error_91102 = new Object[] { 0, 91102 };
    /** 91103 不是黄金特权vip，不可以领取该奖励 */
    public static final Object[] Error_91103 = new Object[] { 0, 91103 };
    /** 91104 装备战斗力不足 */
    public static final Object[] Error_91104 = new Object[] { 0, 91104 };
    /** 91105 宝石战斗力不足 */
    public static final Object[] Error_91105 = new Object[] { 0, 91105 };
    /** 91106 现在还不可以领奖 */
    public static final Object[] Error_91106 = new Object[] { 0, 91106 };

    // -----------------------------------钻特权------------------------------------
    /** 91200 不是黄钻 */
    public static final Object[] Error_91200 = new Object[] { 0, 91200 };
    /** 91201 不是蓝钻 */
    public static final Object[] Error_91201 = new Object[] { 0, 91201 };
    /** 91202 日登礼包已领，明天再来吧 */
    public static final Object[] Error_91202 = new Object[] { 0, 91202 };
    /** 91203 豪华或者年费钻会员才能领取该奖励 */
    public static final Object[] Error_91203 = new Object[] { 0, 91203 };
    /** 91204 年费钻会员才能领取该奖励 */
    public static final Object[] Error_91204 = new Object[] { 0, 91204 };
    /** 91205 今天已经领取，明天再来吧 */
    public static final Object[] Error_91205 = new Object[] { 0, 91205 };
    /** 91206 不支持的渠道 */
    public static final Object[] Error_91206 = new Object[] { 0, 91206 };
}
