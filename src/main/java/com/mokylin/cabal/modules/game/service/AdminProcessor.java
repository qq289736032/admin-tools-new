package com.mokylin.cabal.modules.game.service;


import com.mokylin.cabal.common.io.MsgType;
import com.mokylin.cabal.common.io.Session;

public abstract class AdminProcessor {
	abstract public void keepAlive(Session session, Object[] msg);

	abstract public void login(Session session, Object[] msg);

	abstract public void getServerList(Session session, Object[] msg);

	abstract public void selectArea(Session session, Object[] msg);

	abstract public void deselectArea(Session session, Object[] msg);

	abstract public void punish(Session session, Object[] msg);

	abstract public void chat(Session session, Object[] msg);
	
	abstract public void getQuestionList(Session session, Object[] msg);
	
	abstract public void receptionPlayer(Session session, Object[] msg);
	
	public void dispatch(Session session, int type, Object[] msg) {
		switch (type) {
		case MsgType.Ping_C2S_Msg:
			keepAlive(session, msg);
			break;
		case MsgType.Login_C2S_Msg:
			login(session, msg);
			break;
		case MsgType.GetServerList_C2S_Msg:
			getServerList(session, msg);
			break;

		case MsgType.SelectArea_C2S_Msg:
			selectArea(session, msg);
			break;
		case MsgType.DeselectArea_C2S_Msg:
			deselectArea(session, msg);
			break;
		case MsgType.Punish_C2S_Msg:
			punish(session, msg);
			break;
		case MsgType.UN_PUNISH_C2S_Msg:
			unPunish(session, msg);
			break;
		case MsgType.Chat_C2S_Msg:
			chat(session, msg);
			break;
		case MsgType.GET_QUESTION_LIST_C2S_Msg:
			getQuestionList(session, msg);
			break;
		case MsgType.Reception_Player_C2S_Msg:
			receptionPlayer(session, msg);
			break;
		default:
			break;
		}

	}

	abstract public void unPunish(Session session, Object[] msg);
}
