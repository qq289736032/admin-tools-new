package com.mokylin.cabal.common.redis;

public class RedisConstant {
//	// Redis库的类型
//	/** 游戏库 */
//	public final static int REDIS_TYPE_GAME = 1;
//	/** 全局库 只有一个 */
//	public final static int REDIS_TYPE_GLOBAL_SINGLE = 2;
//	/** 角色库只有一个 */
//	public final static int REDIS_TYPE_GLOBAL_ROLE = 3;
//	/** 战区库 */
//	public final static int REDIS_TYPE_BATTLEAREA = 4;
//
//	/** 每页显示数据 */
//	public static final int MAX_RANK_PAGE_SIZE = 20;
//	/** 拍卖行物品流通次数统计前xx个 */
//	public static final int AUCTION_FLOW_STAT_SIZE = 50;
//
//	// /** 统计库只有一个(放一些定时统计信息，在线等，还有异常信息) */
//	// public final static int REDIS_TYPE_GLOBAL_STAT = 4;
	/** 游戏服务器 */
	public static final String KEY_SERVER_GAME = "game";
	/** 跨服服务器 */
	public static final String KEY_SERVER_CROSS = "cross";
	/** 全局服务器 */
	public static final String KEY_SERVER_GLOBAL = "global";
	/** redis战区服务器 */
	public static final String KEY_CROSS_AREA = "cross_area";
//	/** 翻译服务器 */
//	public static final String KEY_SERVER_TRANSLATE = "translate";
//	/** 充值服务器 */
//	public static final String KEY_SERVER_CHARGE = "charge";
//	/** 网关服务器 */
//	public static final String KEY_SERVER_GATE = "gate";
	/** 日志服务器 */
	public static final String KEY_SERVER_LOGGER = "logger";
//	/** 代理服务器 */
//	public static final String KEY_SERVER_ROUTER = "router";
//
//	/** 翻译服务器选举自增ID */
//	public static final String KEY_TRANSLATE_ID = "translate-id";
//	/** 成就第一个达到最高等级 */
//	public static final String KEY_ACHIEVE_TOP_LEVEL = "toplv";
//
//	// 特殊的指定跨服配置
//	public static final String KEY_CROSS_CONFIG = "cross_config:";
//	public static final String FIELD_CROSS_ID = "cross_id";
//	public static final String FIELD_CROSS_CAMP = "cross_camp";

	// 频道
	/** 全局服务器频道 */
	public static final String CHANNEL_SERVER_GLOBAL = "channel-global";
	/** 跨服服务器频道 */
	public static final String CHANNEL_SERVER_CROSS = "channel-cross";
	/** 游戏服务器频道 */
	public static final String CHANNEL_SERVER_GAME = "channel-game";
//	/** 充值服务器频道 */
//	public static final String CHANNEL_SERVER_CHARGE = "channel-charge";
//	/** 翻译服务器频道 */
//	public static final String CHANNEL_SERVER_TRANSLATE = "channel-translate";
//	/** 网关服务器频道 */
//	public static final String CHANNEL_SERVER_GATE = "channel-gate";
	/** 日志服务器频道 */
	public static final String CHANNEL_SERVER_LOGGER = "channel-logger";
//	/** 代理服务器频道 */
//	public static final String CHANNEL_SERVER_ROUTER = "channel-router";
//	/** 服务器在线人数频道 */
//	public static final String CHANNEL_SERVER_ONLINE = "channel-online";
//	/** 战区频道 */
	public static final String CHANNEL_BATTLE_AREA = "channel-battle_area";

//	/** 国家频道 */
//	public static final String CHANNEL_COUNTRY = "channel-country";
//	/** 服务器频道 */
	public static final String CHANNEL_SERVER = "channel-server-";
	/** 统计频道 */
	public static final String CHANNEL_STATISTIC = "channel-stat";
//	/** CDN频道 */
//	public static final String CHANNEL_CDN = "channel-cdn";
//	/** 维护频道 */
//	public static final String CHANNEL_MAINTAIN = "channel-maintain";
//	/** 监控频道 */
//	public static final String CHANNEL_MONITOR = "channel-monitor";
//
//	/** 全服拍卖行 */
//	public static final String CHANNLE_GLOBAL_AUCTION = "channel-auction";
//	/** 官方商城 */
//	public static final String CHANNEL_GLOBAL_MALL = "channel-gmall";
//	/** 敏感词 */
//	public static final String CHANNEL_SENSITIVE_WORDS = "channel-sensitive";
//	/**Crescent*/
//	public static final String CRESCENT = "crescent";
//	/** 消息类型和消息的分隔符 */
	public static final String CHANNEL_SEPARATOR = "@";

	public static final String BATTLE_KEY = "battle:"; //战区信息

//
//	/** 加战区 */
//	public static final int CHANNEL_SERVER_add_battle_area = 1;
//	/** 删除战区 */
//	public static final int CHANNEL_SERVER_remove_battle_area = 2;
//	/** 处理骚扰战结果 */
//	public static final int CHANNEL_SERVER_country_harass = 3;
//	/** 处理灭国战结果 */
//	public static final int CHANNEL_SERVER_country_awar = 4;
//	/** 跨服广播回来的结果 */
//	public static final int CHANNEL_SERVER_BROADCAST_MSG = 10;
//	/** 处理王权争夺的国王结果设置 */
//	public static final int CHANNEL_SERVER_COMPLETE_KING_MSG = 11;
//	/** 处理替换国家信息 */
//	public static final int CHANNEL_SERVER_CHANGE_COUNTRY_INFO = 12;
//	/** 处理王权争夺后 参与争夺公会的奖励发放 */
//	public static final int CHANNEL_SERVER_COMPLETE_KING_REWARD_MSG = 13;
//	/** 处理生成国家战神 */
//	public static final int CHANNEL_SERVER_HANDLER_COUNTRY_GOD_WAR_MSG = 14;
//
//	// 处理战区REDIS广播回来的消息类型
//	/** 全战区广播的消息类型 */
//	public static final int CHANNEL_BATTLE_AREA_AREA_TYPE = -1000;
//
//	
//
//	// /**更新所属国家*/
//	// public static final int CHANNEL_SERVER_update_country=3;
//	// /**删除国家*/
//	// public static final int CHANNEL_SERVER_remove_country=4;
//
//	// // 角色相关
//	// public static final String KEY_ROLE_SERVER = "R_S";
//	/** 激活码 */
//	public static final String KEY_ACTIVATE_CODE = "activate_code:";
//	/** 已使用激活码数量 */
//	public static final String KEY_ACTIVATE_CODE_USEDNUM = "activate_code_used_num";
//	/** cdn源 */
//	public static final String KEY_CDN = "cdn";
//	// 用户相关
//	public static final String KEY_USER = "u";
//	// 只存当前最新登录的玩家
//	public static final String KEY_LAST_LOGIN = "u2";
//	// 只存改账户所有的角色ID,最新的在列头
//	public static final String KEY_ROLE_LIST = "u3";
//	/** GM */
//	public static final String KEY_GM = "gm";
//
//	/*********************************************************/
//	/** 转化率 */
//	public static final String KEY_CONVERT = "convert";
//	/** 加载点:已激活的用户 */
//	public static final String KEY_USER_ACTIVATED = "0";
//	/** 加载点:进入到游戏首页的用户数 */
//	public static final String KEY_USER_INDEX = "1";
//	/** 加载点:首页展现完成 */
//	public static final String KEY_LOAD_MAIN = "11";
//	/** 加载点:加载Flash主程序完成 */
//	public static final String KEY_LOADING_FLASH = "2";
//	/** 加载点:加载创角配置文件完成 */
//	public static final String KEY_LOADING_CREATROLE_CONFIG = "3";
//	/** 加载点:到创角界面，已创建user的用户数 */
//	public static final String KEY_USER_CREATED = "20";
//	/** 加载点:加载创角UI文件完成 */
//	public static final String KEY_LOADING_CREATROLE_UI = "21";
//	/** 加载点:创建角色，已创建role的用户数 */
//	public static final String KEY_USER_HASROLE = "30";
//	/** 加载点:进入场景 */
//	public static final String KEY_ENTER_FIRST_STAGE = "31";
//	/** 加载点:角色有操作过游戏行为，升级到2级 */
//	public static final String KEY_ROLE_LEVELUP = "35";
//	/*********************************************************/
//	// public static final String KEY_USER_SERVER = "U_S";
//
	public static final String KEY_ROLE_NAME = "krn";
//	public static final String KEY_ROLE_SERVER = "K_R_S";
//	/**
//	 * 角色的一些简单数据，全局共享.
//	 */
//	public static final String KEY_ROLE_INFO = "role:";
//
//	/** Redis角色属性：ID */
//	public static final String ROLE_FIELD_ID = "id";
//	/** Redis角色属性：名称 */
	public static final String ROLE_FIELD_NAME = "4";

	public static final String ROLE_FIELD_USER_ID = "36";

//	/** Redis角色属性：职业 */
//	public static final String ROLE_FIELD_JOB = "job";
//	/** Redis角色属性：种族 */
//	public static final String ROLE_FIELD_RACE = "race";
//	/** Redis角色属性：性别 */
//	public static final String ROLE_FIELD_GENDER = "gender";
//	/** Redis角色属性：等级 */
//	public static final String ROLE_FIELD_LEVEL = "level";
//	/** Redis角色属性：头像 */
//	public static final String ROLE_FIELD_FACE = "face";
//	/** Redis角色属性：排行崇拜 */
//	public static final String ROLE_FIELD_WORSHIP = "worship";
//	/** Redis角色属性：排行爱戴 */
//	public static final String ROLE_FIELD_RESPECT = "respect";
//	/** Redis角色属性：角色当前穿的装备模板ID数组 */
//	public static final String ROLE_FIELD_EQUIP_ARRAY = "equips";
//	/** Redis角色属性：宠物ID */
//	public static final String ROLE_FIELD_PET_ID = "pet_id";
//	/** Redis角色属性：坐骑幻化Id */
//	public static final String ROLE_FIELD_HORSE_ID = "horse_id";
//	/** Redis角色属性：披风幻化Id */
//	public static final String ROLE_FIELD_CLOAK_ID = "cloak_id";
//	/** Redis角色属性：神器Id */
//	public static final String ROLE_FIELD_ARTIFACT_ID = "artifact_id";
//	/** Redis角色属性：总战力 */
//	public static final String ROLE_FIELD_POWER = "power";
//	/** Redis角色属性：总战力 */
//	public static final String ROLE_FIELD_SPECIAL_SKILL_ID = "special_skill_id";
//
//	/** Redis角色属性：公会ID */
//	public static final String ROLE_FIELD_GUILD_ID = "guildId";
//	/** Redis角色属性：公会名 */
//	public static final String ROLE_FIELD_GUILD_NAME = "guildName";
//	/** Redis角色属性：巅峰等级 */
//	public static final String ROLE_FIELD_PEAK_LEVEL = "peakLevel";
//	/** Redis角色属性：地图 */
//	public static final String ROLE_FIELD_MAP_ID = "mapId";
//	/** Redis角色属性：在线状态 */
//	public static final String ROLE_FIELD_ONLINE = "online";
//	/** Redis角色属性：服务器ID */
//	public static final String ROLE_FIELD_SERVER_ID = "serverId";
//	/** Redis角色属性：登出时间 */
//	public static final String ROLE_FIELD_LOGOUT_TIME = "logoutTime";
//	/** Redis角色属性：vip等级 */
//	public static final String ROLE_FIELD_VIP_LEVEL = "vipLevel";
//	/** Redis角色属性：vip特权 */
//	public static final String ROLE_FIELD_VIP_PRIVILEGES = "vipPrivileges";
//	/** Redis角色属性：蓝钻等级信息 */
//	public static final String ROLE_FIELD_BLUE_LEVEL_VALUE = "blueLevelValue";
//	/** Redis角色属性：黄钻等级信息 */
//	public static final String ROLE_FIELD_YELLOW_LEVEL_VALUE = "yellowLevelValue";
//	/** Redis角色属性：公会等级 */
//	public static final String GUILD_FIELD_LEVEL = "guildLevel";
//
//	// ----------------------------------------------------
//	/** 服区共享数据key */
//	public static final String SERVER_SHARE_DATA_KEY = "server_share_key:";
//	/** worldId */
//	public static final String SERVER_WORLD_ID = "world_id";
//	/** 服区开服时间 */
//	public static final String OPEN_SERVER_TIME = "open_server_time";
//	/** 本服前xx等级 */
//	public static final String SERVER_TOP_LEVEL = "top_level";
//	/** 本服的世界等级 */
//	public static final String SERVER_WORLD_LEVEL = "world_level";
//	/** 本服前300名战斗力集合 */
//	public static final String SERVER_TOP_COMBATS = "top_combat";
//
//	/** 全战区平均角色等级 */
//	public static final String BATTLE_AREA_AVG_LEVEL = "battleAre_avg_level";
//	/** 全战区平均平均战斗力 */
//	public static final String BATTLE_AREA_AVG_COMBAT = "battleAre_avg_combat";
//
//	// -------------------国家相关key-----------------------
//	// CountryEntity
//	/** 国家编号 */
	public static final String COUNTRY_KEY = "country:";
//	/** 国家编号 */
//	public static final String COUNTRY_ID = "country_id";
//	/** 势力，红，黄，蓝，绿 */
//	public static final String COUNTRY_FORCE = "force";
//	/** 国家名 */
//	public static final String COUNTRY_NAME = "country_name";
//	/** 国王Id */
//	public static final String KINGROLEID = "king_role_id";
//	/** 国王名 */
//	public static final String KINGROLENAME = "king_role_name";
//	/** 国家战斗力 */
//	public static final String POWER = "power";
//	/** 国家角色平均等级 */
//	public static final String COUNTRY_AVG_LEVEL = "C_AVG_LEVEL";
//	/** 国家公告 */
//	public static final String NOTICE = "notice";
//	/** 是否为弱国状态(1.弱国) */
//	public static final String WEAKSTATUS = "weakStatus";
//	/** 国家是否处于战争状态 */
//	public static final String FIGHT_STATUS = "fight_status";
//	/** 国家服务器列表 */
//	public static final String COUNTRY_SERVER_LIST = "server_list";
//	/** 国家创建时间 */
//	public static final String COUNTRY_ADD_TIME = "country_add_time";
//	/** 国家修改时间 */
//	public static final String COUNTRY_MODIFY_TIME = "country_modify_time";
//	/** 跨服皇城占领时间戳 */
//	public static final String PYOCCUPY_DAY = "pyoccupy_day";
//	/** 国家战神 */
//	public static final String COUNTRY_GOD_WAR = "god_war";
//	/** 上次国战奖励Id */
//	public static final String COUNTRY_REWARD_ID = "reward_id";
//	/** 国战类型 */
//	public static final String COUNTRY_AWAR_TYPE = "awar_type";
//	/** 上次国战结果 */
//	public static final String COUNTRY_RESULT = "result";
//	/** 国家当前抢夺到的资源 */
//	public static final String COUNTRY_STRONGHOLD = "stronghold";
//	/** 皇权占领状态 */
//	public static final String GOVT_COUNTRY_HOLD_STATUS = "govt_hold_status";
//
//	// CountryTask
//	/** 国家任务key */
//	public static final String COUNTRY_TASK_KEY = "country_task:";
//	/** 国家已接取任务 */
//	public static final String COUNTRY_RECEIVE_TASK = "receive_task";
//	/** 国家任务完成进度 */
//	public static final String COUNTRY_TASK_PROGRESS = "task_progress";
//	/** 国家任务 刷新时间 */
//	public static final String COUNTRY_REFRESH_TIME = "refresh_time";
//	/** 国家任务 是否在开启状态 */
//	public static final String COUNTRY_TASK_OPEN_STATUS = "task_open_status";
//	/** 当前正在进行中的过国家任务 */
//	public static final String COUNTRY_TASK_CURRENT_PROGRESS = "country_task_progress";
//	/*** 国家任务期间角色杀人数 */
//	public static final String COUNTRY_TASK_KILL_ROLE_PROGRESS = "task_kill_role_num:";
//
//	// CountryMember
//	/** 角色Id */
//	public static String CT_M_ROLE_ID = "ct_m:role_id";
//	/** 角色名字 */
//	public static String CT_M_ROLE_NAME = "ct_m:role_name";
//	/** 角色等级 */
//	public static String CT_M_ROLE_LEVEL = "ct_m:role_level";
//	/** 角色性别 */
//	public static String CT_M_ROLE_GENDER = "ct_m:role_gender";
//	/** 角色种族 */
//	public static String CT_M_ROLE_RACE = "ct_m:role_race";
//	/** 职位 */
//	public static String CT_M_POSITION = "ct_m:position";
//	/** 日功勋值 */
//	public static String DAY_EXPLOIT = "day_exploit";
//	/** 昨日排名(在每天清理排名数据时,确定当前角色排名) */
//	public static String YESTERDAY_RANK = "yesterday_rank";
//	/** 已领功勋段奖励次数 */
//	public static String REWARD_RANK_UNIT = "reward_rank_unit";
//
//	// CountryGuild
//	/** 参加王权争霸公会Id */
//	public static String COMPLETE_GUILD_ID = "C_G_ID";
//	/** 参加王权争霸公会名 */
//	public static String COMPLETE_GUILD_NAME = "C_G_N";
//	/** 当前公会属于哪个区服 */
//	public static String COMPLETE_GUILD_IN_WORLD = "C_G_IN_W";
//
//	// OFFICERCMD
//	/** 使用官员令的角色 */
//	public static String OFFICER_CMD_POSITION_ID = "cmd_position_id";
//	/** 当前官员令属于哪个国家 */
//	public static String OFFICER_CMD_COUNTRY = "cmd_country_id";
//	/** 当前攻击的据点 */
//	public static String OFFICER_CMD_STRONGHOLD_ID = "cmd_stronghold_id";
//	/** 官员令失效时间 */
//	public static String OFFICER_CMD_INVALID_TIME = "cmd_invalid_time";
//	/** 官员令使用个数 */
//	public static String OFFICER_CMD_COUNTER_NUM = "cmd_use_counter_num";
//	/** 使用官员令的时间戳 */
//	public static String OFFICE_CMD_USE_TIME = "cmd_use_time";
//	/** 当前官员令类型 */
//	public static String OFFICE_CMD_TYPE = "cmd_type";
//
//	// role_id key
//	/** 国家成员信息 */
//	public static final String COUNTRY_MEMBER_INFO = "CT_member_info:";// country_member_info
//	/** 官员令信息 */
//	public static final String COUNTRY_OFFICER_CMD_INFO = "officer_cmd_info:";
//
//	// guild_id key
//	/** 参加王权争霸的公会信息 */
//	public static final String COUNTRY_GUILD_INFO = "CT_guild_info:";// country_guild_info
//
//	// server_id key
//	/** 根据serverId 获取国家ID */
//	public static final String GET_COUNTRYID_BY_SERVERID = "G_CTID_B_SID:";// get_countryId_by_serverId
//	/** 获取当前服务器下所有国家成员的roleId */
//	public static final String SERVER_ROLE_LIST = "server_role_list:";
//	/** 获取当前服务器下参加王权争霸的公会id */
//	public static final String SERVER_COMPLETE_KING_LIST = "S_CK_L:";
//
//	// country_id key
//	/** 获取国家下面服务器Id列表 */
//	public static final String GET_SERVER_BY_COUNTRYID_LIST = "G_S_B_CTID_L:";// get_server_by_countryId_list
//	/** 获取当前国家时间点[单位:小时]资源奖励列表 */
//	public static final String GET_ALL_COUNTRY_STRONGHOLD_TIMEPOINT_LIST = "G_A_CT_SH_TP_L:";// get_all_country_stronghold_timepoint_list
//	/** 国家官员令集合(存的为职位id) */
//	public static final String COUNTRY_OFFICER_CMD_LIST = "CT_CMD_LIST:";
//	/** 国家王权争霸结束的时间戳 */
//	public static String LAUNCH_KING_WAR_TIME_KEY = "L_K_W_E_T:";
//	/** 国家已发布的所有官员令计算器 */
//	public static final String OFFICERCMD_COUNTER_KEY = "offCmd_counter:key";
//	/** 玩家已响应的官员令计算器 */
//	public static final String RESPONE_COUNTER_KEY = "respone_counter:key";
//	/** 国家战役[骚扰、灭国、皇城、王权] 次数计算器 */
//	public static final String COUNTRY_WAR_COUNTER_KEY = "country_war_counter:key";
//	/** 当前国家拥有的据点信息 */
//	public static final String COUNTRY_STRONGHOLD_KEY = "country_stronghold_key:";
//
//	// cross_id key
//	/** 当前跨服服务器下所有据点 */
//	public static final String CROSS_STRONGHOLD_LIST = "CS_SH_L:";// cross_stronghold_list
//	/** 战争类型 */
//	public static final String WAR_TYPE = "war_type:";// war_type
//	/** 当前跨服服务器下整点资源倍率记录值 */
//	public static final String STRONGHOLD_TIME_MULTIPLE_HISTORY = "SH_T_M_H:";
//	/** 当前战区下所有国家正在进行的国家任务 */
//	public static final String TASK_BATTLEAREA_KEY = "task_battleArea:";
//
//	// country_id key sort
//	/** 国家职位(军衔)排序 */
//	public static final String COUNTRY_POSITION_SORT = "CT_position_sort:";// country_position_sort
//	/** 国家成员功勋者排序 */
//	public static final String COUNTRY_MEMBER_EXPLOIT_SORT = "CT_M_ET_sort:";// country_member_exploit_sort
//	/** 国家公会功勋值排行(一段时间内) */
//	public static final String COUNTRY_GUILD_EXPLOIT_SORT = "CT_G_ET_sort:";// country_guild_exploit_sort
//
//	// country_task key sort
//	/** 国家任务玩家贡献的进度排名 */
//	public static final String COUNTRY_TASK_RANK_SORT = "task_rank_sort:";
//
//	/**
//	 * 战区的一些简单数据，全局共享.
//	 */
//	public static final String KEY_BATTLE_AREA = "battle_area:";
//	/** 属于某游戏服的战区列表 */
//	public static final String KEY_AREA_SERVER = "area-server:";
//	/** 账号激活(不区分区了) */
//	public static final String KEY_USER_ACTIVATE = "userActive";
//	/** 参加过封测的玩家 */
//	public static final String KEY_USER_CB = "closebeta";
//	// //战区属性
//	// /** ID */
//	// public static final String BA_FIELD_ID = "id";
//	// /** 名称 */
//	// public static final String BA_FIELD_NAME = "name";
//	// /** cross_id */
//	// public static final String BA_CROSS_ID = "cross_id";
//	// /** 类型 */
//	// public static final String BA_FIELD_TYPE = "type";
//	// /**参赛者 */
//	// public static final String BA_FIELD_CLIST = "list";
//	// /**添加时间*/
//	// public static final String BA_FIELD_ADD_TIME = "add_time";
//	// /**修改时间*/
//	// public static final String BA_FIELD_MODIFY_TIME = "modify_time";
//
//	/** 全服拍卖行 */
//	public static final String KEY_AUCTION = "auction";
//	/** 官方商城 */
//	public static final String KEY_GLOBAL_MALL = "global_mall:";
//	/**
//	 * 删除
//	 */
//	public static final int SYNC_TYPE_DEL = 0;
//	/**
//	 * 更新
//	 */
//	public static final int SYNC_TYPE_UPDATE = 1;
//
//	/** id */
//	public static final String MALL_ID = "id";
//	/** itemid */
//	public static final String MALL_ITEMID = "itemid";
//	/** countPerOne */
//	public static final String MALL_COUNT_PER_ONE = "count_per_one";
//	/** bindOrNot */
//	public static final String MALL_BIND_OR_NOT = "bind_or_not";
//	/** individualLimit */
//	public static final String MALL_INDIVIDUAL_LIMIT = "ind_limit";
//	/** totalLimit */
//	public static final String MALL_TOTAL_LIMIT = "total_limit";
//	/** leftCount */
//	public static final String MALL_LEFT_COUNT = "left_count";
//	/** lastWorldId */
//	public static final String MALL_LAST_WORLD_ID = "last_worldid";
//	/** price */
//	public static final String MALL_PRICE = "price";
//	/** startMillis */
//	public static final String MALL_START_MILLIS = "start";
//	/** endMillis */
//	public static final String MALL_END_MILLIS = "end";
//
//	/** 统计拍卖行中物品流通次数 keys */
//	public static final String AUCTION_FLOW_COUNT_KEY = "auction_flow_key";
//
//	// 运营统计需求----------------------
//	/** 副本进入角色列表 */
//	public static final String STAT_INSTANCE_ROLE_LIST = "stat_instance_role_list:";
//	/** 副本进入计数Hash */
//	public static final String STAT_INSTANCE_ENTER_COUNT = "stat_instance_enter_count";
//	/** 副本完成计数Hash */
//	public static final String STAT_INSTANCE_FINISH_COUNT = "stat_instance_finish_count";
//
//	// 记录匹配平均等待时间-------------------------
//	/** 副本匹配平均等待时间Key. */
//	public static final String MATCH_TIME_INSTANCE_KEY = "match_time_instance";
//	/** 战场匹配平均等待时间Key. */
//	public static final String MATCH_TIME_BATTLEFIELD_KEY = "match_time_battlefield";
//
//	/** 集市任务redis前缀 */
//	public static final String MARKET_TASK_PREFIX = "mtp:";
//
//	/** 预计开服时间 */
//	public static final String FORESEE_SERVER_OPEN_TIMESTAMP = "fsopentime";
//	/** 维护公告地址 */
//	public static final String KEY_MAINTAIN_ANNOUNCE_URL = "maintainurl";
//
//	// ---------------------------敏感词列表-----------------------------
//	public static final String KEY_SENSITIVE_WORDS = "sensitive:";
//
//	// ---------------------------排行榜系统----------------------------
//	/** 本服总战力排行榜Key */
//	public static final String KEY_RANK_TOTAL_POWER = "rank_total_power";
//	/** 本服等级排行榜Key */
//	public static final String KEY_RANK_LEVEL = "rank_level";
//	/** 本服装备排行榜Key */
//	public static final String KEY_RANK_EQUIP = "rank_equip";
//	/** 本服坐骑排行榜Key */
//	public static final String KEY_RANK_HORSE = "rank_horse";
//	/** 本服披风排行榜Key */
//	public static final String KEY_RANK_CLOAK = "rank_cloak";
//	/** 本服神器排行榜Key */
//	public static final String KEY_RANK_ARTIFACT = "rank_artifact";
//	/** 本服宠物排行榜Key */
//	public static final String KEY_RANK_PET = "rank_pet";
//	/** 本服珠宝排行榜Key */
//	public static final String KEY_RANK_JEWELRY = "rank_jewelry";
//	/** 本服附魔排行榜Key */
//	public static final String KEY_RANK_ENCHANT = "rank_enchant";
//	/** 本服击杀排行榜Key */
//	public static final String KEY_RANK_KILL = "rank_kill";
//
//	/** 跨服总战力排行榜Key */
//	public static final String KEY_RANK_CROSS_TOTAL_POWER = "rank_cross_total_power";
//	/** 跨服公会排行榜Key */
//	public static final String KEY_RANK_CROSS_GUILD = "rank_cross_guild";
//	/** 跨服击杀排行榜Key */
//	public static final String KEY_RANK_CROSS_KILL = "rank_cross_kill";
//
//	// 以下3个Key为跨服排行榜的本地缓存，用于下次排行时，清理没有上排行榜的Key
//	/** 本服总战力排行榜Key的缓存 */
//	public static final String KEY_RANK_CACHE_TOTAL_POWER = "rank_cache_total_power";
//	/** 本服总战力排行榜Key的缓存 */
//	public static final String KEY_RANK_CACHE_GUILD = "rank_cache_guild";
//	/** 本服总战力排行榜Key的缓存 */
//	public static final String KEY_RANK_CACHE_KILL = "rank_cache_kill";



	/** 客服给玩家发消息 */
	public static final int CHANNEL_CHAT_GM2PLAYER_Msg = 18;

	/** 监控频道 */
	public static final String CHANNEL_CHAT = "channel-chat";


	/*修改战区频道  惊天动地版本*/
	public static final String CHANNEL_GAME_CROSS = "channel-game-cross-";

	public static final String CHANEL_BLACK_LIST_IP = "channel-blacklist-ip";

	/**
	 * user黑名单发布频道
	 */
	public static final String CHANEL_BLACK_LIST_USER = "channel-blacklist-user-";

	/*游戏服和战区的对应关系  惊天动地版本*/
	public static final String KEY_GAME_CROSS = "game-cross";

	/**
	 * IP黑名单
	 */
	public static final String KEY_BLACK_LIST_IP = "blacklist-ip";
	/**
	 * user黑名单
	 */
	public static final String KEY_BLACK_LIST_USER = "blacklist-user:";


	/**
	 * 开启防沉迷
	 */
	public static final String KEY_GATE_OPEN_CHENMI = "gate-open-chenmi";

	/** 防沉迷变化channel */
	public static final String CHANNEL_GATE_CHENMI = "channel-gate-chenmi";
	/** 防沉迷删除channel */
	public static final String CHANNEL_GATE_CHENMI_DEL = "channel-gate-chenmi-del";
	/**客户端地址的key */
	public static final String KEY_CLIENT_URL = "gate-client-url";
	/** 客户端地址的channel */
	public static final String CHANNEL_CLIENT_URL = "channel-gate-client-url";

//	/**测试服的sid */
//	public static final String KEY_TEST_SID = "gate-test-sid";
//	/** 测试服的sid的channel */
//	public static final String CHANNEL_TEST_SID = "channel-gate-test-sid";

	public static final String CHANNEL_CLIENT_URL_DEL = "channel-gate-client-url-del";

	/** coopbase信息的key */
	public static final String KEY_GATE_CB = "gate-coopbase";
	
	public static final String CHANNEL_GATE_CB = "channel-gate-coop-base";
	
	public static final String GAME_COMMAND = "modules";

}