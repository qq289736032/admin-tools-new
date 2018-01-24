package com.mokylin.cabal.common.redis;


public class SystemConstant {
//	public static final Map<Integer, AtomicLong> map=new ConcurrentHashMap<Integer, AtomicLong>();
//	/**test*/
//	public static void increase(int type){
//		AtomicLong num=map.get(type);
//		if(num==null){
//			num= new AtomicLong(0);
//			map.put(type, num);
//		}
//		num.incrementAndGet();
//	}
//	
//	public static final String GAME_NAME="ST";
//	public static final String TICKET = "NITABAHAIZHENGEINIPOLE";
//	public static final String DEFAULT_KEY = "xianlingkey";
//	public static final String LOCALHOST = "127.0.0.1";
	// 服务器类型
	/** 服务器间战斗模式 */
	public final static int BATTLE_CROSS = 0;
	/** 国建之间的战斗模式 */
	public final static int COUNTRY_CROSS = 1;
	/** 游戏服务器 */
	public final static int SERVER_TYPE_GAME = 1;
	/** 跨服服务器 */
	public final static int SERVER_TYPE_CROSS = 2;
	/** 全局服务器 */
	public final static int SERVER_TYPE_GLOBAL = 3;
//	/** 翻译服务器 */
//	public final static int SERVER_TYPE_TRANSLATE = 4;
	/** 日志服务器 */
	public final static int SERVER_TYPE_LOGGER = 5;
//	/** 充值服务器 */
//	public final static int SERVER_TYPE_CHARGE = 6;
//	/** 网关服务器 */
//	public final static int SERVER_TYPE_GATE = 7;
//	/** router服务器 */
//	public final static int SERVER_TYPE_ROUTER = 8;
//	// 跨服服务器子类型
//	/** 全功能型服务器 */
//	public final static int SERVER_SUBTYPE_NIUBILITY = 0;
//	// /**定向性的跨服服务器(跨服竞技场,跨服世界BOSS,挖矿,虫群入侵,押镖,砍旗,杀将军,据点争夺,王城战)*/
//	// public final static int SERVER_SUBTYPE_DIRECT = 2;
//	// 跨服服务器定向玩法分类
//	/** 跨服竞技场 */
//	public final static int SERVER_DIRECT_ARENA = 1;
//	/** 跨服竞技场-英雄 */
//	public final static int SERVER_ARENA_SUBTYPE_HERO = (1 << 8) | SERVER_DIRECT_ARENA;
//	/** 跨服竞技场-荣誉 */
//	public static final int SERVER_ARENA_SUBTYPE_HONOR = (1 << 9) | SERVER_DIRECT_ARENA;
//	/** 跨服竞技场-勇气 */
//	public static final int SERVER_ARENA_SUBTYPE_COURAGE = (1 << 10) | SERVER_DIRECT_ARENA;
//	/** 跨服竞技场-试炼 */
//	public static final int SERVER_ARENA_SUBTYPE_TRIAL = (1 << 11) | SERVER_DIRECT_ARENA;
//
//	/** 匹配服务器 */
//	public final static int SERVER_DIRECT_INSTANCE_MATCHER = 2;
//	/** 国家玩法 [挖矿,虫群入侵,押镖,砍旗,杀将军,据点争夺,王城战] */
//	public final static int SERVER_DIRECT_COUNTRY = 3;
//	/** 非定向性的跨服服务器(跨服随机匹配,组队) */
//	public final static int SERVER_SUBTYPE_UNDIRECT = 4;
//
//	/**
//	 * 后台显示战区类型用的
//	 */
//	public final static int[] SERVER_BATTLEAREAS = { SERVER_DIRECT_ARENA, SERVER_DIRECT_COUNTRY, SERVER_ARENA_SUBTYPE_HERO,
//			SERVER_ARENA_SUBTYPE_HONOR, SERVER_ARENA_SUBTYPE_COURAGE, SERVER_ARENA_SUBTYPE_TRIAL, SERVER_DIRECT_INSTANCE_MATCHER };
//
	/** 服务器处于正常运行状态 */
	public final static int SERVER_STATUS_OPENING = 0;
	/** 服务器处于运维维护状态,只能允许GM进来 */
	public final static int SERVER_STATUS_MAINTAIN = 1;
	/** 服务器处于停服状态 */
	public final static int SERVER_STATUS_STOPED = 2;
//	/** 对不起,你可能使用了非法外挂,请自觉关闭,小心账号被盗 */
//	public final static String REASON_PLUGIN = "对不起,你可能使用了非法外挂,请自觉关闭,小心账号被盗";
//	/** 对不起,同一账号重复登陆，踢掉前一个连接 */
//	public final static String REASON_SAME_USER_LOGIN = "对不起,同一账号重复登陆，踢掉前一个连接";
//	/** server RPC超时设置 单位毫秒 */
//	public final static int TIMEOUT = 5000;
//	/***/
//	public static int serverType = SERVER_TYPE_GAME;
//
//	/** 广播打印数量下限 */
//	public static int BROADCAST_NUM = 20;
//
//	// public static String UNKNOW_SERVER="游戏禁区";
//	// session的类型
//	/** 玩家直连生成的session 绑定1个玩家 */
//	public final static int SESSION_TYPE_PLAYER = 1;
//
//	/** 服务器中转RPC生成的session,绑定多个玩家 */
//	public final static int SESSION_TYPE_RPC = 2;
//	// 服务器系统分组
//	/** 业务逻辑组 */
//	public static final byte GROUP_BUS_CACHE = 1;
//	/** 业务初始化组 */
//	public static final byte GROUP_BUS_INIT = 2;
//	/** 场景组 */
//	public static final byte GROUP_STAGE = 3;
//	/** 公共组 */
//	public static final byte GROUP_PUBLIC = 4;
//
//	public static final String NAME_BUS_CACHE = "bus_cache";
//	public static final String NAME_BUS_INIT = "bus_init";
//	public static final String NAME_STAGE = "stage";
//	public static final String NAME_PUBLIC = "public";
//	
//	/**
//	 * 正常下线
//	 */
//	public static final byte LOGOUT_TYPE_NOMRAL = 0;
//	/**
//	 * 关闭服务器下线
//	 */
//	public static final byte LOGOUT_TYPE_SHUTDOWN = 1;
//	
//	/***
//	 * 任务集市uri
//	 */
//	public static final String MARKET_TASK_URI = "/tencent/market_task";
//	
//	/** check: 开发者仅需要查询任务步骤是否完成，返回步骤完成状态。 */
//	public static final String CMD_CHECK = "check";
//	/** check_award: 开发者需要查询任务步骤是否完成，若步骤已完成，直接给用户发货（payitem），并返回发货是否成功。 */
//	public static final String CMD_CHECK_AWARD = "check_award";
//	/** award: 平台通知开发者直接给给用户发货，开发者返回发货是否成功。 */
//	public static final String CMD_AWARD = "award";
//	/** 集市任务奖励位偏移 */
//	public static final int MARKET_TASK_AWARD_BIT_OFFSET = 16;
//
//	public static String getGroupName(byte group) {
//		switch (group) {
//		case GROUP_BUS_CACHE: {
//
//			return NAME_BUS_CACHE;
//		}
//		case GROUP_BUS_INIT: {
//
//			return NAME_BUS_INIT;
//		}
//		case GROUP_STAGE: {
//			return NAME_STAGE;
//		}
//		case GROUP_PUBLIC: {
//			return NAME_PUBLIC;
//		}
//		}
//		return null;
//	}

}
