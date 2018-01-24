package com.mokylin.cabal.common.io;

public class MsgType {
	/** 心跳请求 */
	public static final int Ping_C2S_Msg = 1;
	/** 心跳返回 */
	public static final int Ping_S2C_Msg = 2;
	/** 登录请求 */
	public static final int Login_C2S_Msg = 3;
	/** 登录返回 */
	public static final int Login_S2C_Msg = 4;
	/**消息广播 */
	public static final int BROADCAST_S2C_Msg = 5;
	/**断线*/
	public static final int Disconnect_S2C_Msg=6;
	/** 服务器列表请求 */
	public static final int GetServerList_C2S_Msg = 7;
	/** 服务器列表返回 */
	public static final int GetServerList_S2C_Msg = 8;
	/** 选区请求 */
	public static final int SelectArea_C2S_Msg = 9;
	/** 选区响应 */
	public static final int SelectArea_S2C_Msg = 10;
	/** 取消选区请求 */
	public static final int DeselectArea_C2S_Msg = 11;
	/** 取消选区响应 */
	public static final int DeselectArea_S2C_Msg = 12;
	/**处罚请求*/
	public static final int Punish_C2S_Msg = 13;
	/**处罚响应*/
	public static final int Punish_S2C_Msg = 14;
	/**GM聊天请求*/
	public static final int Chat_C2S_Msg = 15;
	/**玩家聊天回发*/
	public static final int Chat_S2C_Msg = 16;
	/**广播玩家的问题*/
	public static final int BROADCAST_QUESTION_S2C_Msg = 17;
	/**获取玩家的问题协议请求*/
	public static final int GET_QUESTION_LIST_C2S_Msg = 18;
	/**获取玩家的问题协议返回*/
	public static final int GET_QUESTION_LIST_S2C_Msg = 19;
	/**接待玩家请求*/
	public static final int Reception_Player_C2S_Msg = 20;
	/**接待成功与否*/
	public static final int Reception_Player_S2C_Msg = 21;
	/**删除某个问题*/
	public static final int REMOVE_QUESTION_S2C_Msg = 22;

	/**解除处罚*/
	public static final int UN_PUNISH_C2S_Msg = 23;


	/** 禁言*/
	public static final int PUNISH_TYPE_JINYAN = 2;
	/**封号*/
	public static final int PUNISH_TYPE_FENHAO = 4;

	/*封账号**/
	public static final int PUBLISH_TYPE_USER = 6;
	/**封IP*/
	public static final int PUBLISH_TYPE_IP = 7;

	/** 解除禁言*/
	public static final int UN_PUNISH_TYPE_JINYAN = 2;
	/**解除封号*/
	public static final int UN_PUNISH_TYPE_FENHAO = 1;

}
