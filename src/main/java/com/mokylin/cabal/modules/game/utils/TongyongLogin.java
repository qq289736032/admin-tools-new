package com.mokylin.cabal.modules.game.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.utils.Md5Utils;

public class TongyongLogin {

    private static Logger logger = LoggerFactory.getLogger(TongyongLogin.class.getName());

    //private static final String COOP_NAME = "coopname";
    private static final String USER_ID = "uid";    // 这个字段是user_id 不是user_name但是游戏登陆接口竟然写的是user_name 好坑
    private static final String SERVER_ID = "sid";
    private static final String TIMESTAMP = "t";
    private static final String CM_FLAG = "adult";    //防沉迷
    private static final String SIGN = "sign";
    private static final String SIGN_SPLIT = "=";
    private static final String CLIENT = "client";
    private static final String PID = "pid";

    public static String LoginGame(String pid , String userId, String serverId, String gateUrl, String key) throws IOException, URISyntaxException {
        String checkUrl;
        CoopRequest coopRequest = new CoopRequest();
        coopRequest.setUserId(userId);
        coopRequest.setServerId(serverId);
        coopRequest.setCmFlag("1");    //0没有沉迷   1有沉迷
        coopRequest.setClient("1");     //1表示网站 2表示微端
        coopRequest.setPid(pid);
//        coopRequest.setCoopName(coopName);

//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date time=new Date();
//		String timestr=format.format(time);
        coopRequest.setTimestamp(String.valueOf(new Date().getTime()));

        //通用1
        coopRequest.setSign(generateSign(coopRequest, key));
        //通用接口2
        //coopRequest.setSign(generateSign(coopRequest));


        StringBuffer stringBuffer = new StringBuffer();
        
        String loginUrl = Global.getConfig("game.loginUrl");
        gateUrl = loginUrl + "?";
        
        stringBuffer
                .append(gateUrl)
                .append(USER_ID).append(SIGN_SPLIT)
                .append(coopRequest.getUserId())
                .append("&").append(SERVER_ID).append(SIGN_SPLIT)
                .append(coopRequest.getServerId())
                .append("&").append(CM_FLAG).append(SIGN_SPLIT)
                .append(coopRequest.getCmFlag())
                .append("&").append(TIMESTAMP).append(SIGN_SPLIT)
                .append(coopRequest.getTimestamp())
                .append("&").append(SIGN).append(SIGN_SPLIT)
                .append(coopRequest.getSign())
                .append("&").append(CLIENT).append(SIGN_SPLIT)
                .append(coopRequest.getClient())
                .append("&").append(PID).append(SIGN_SPLIT)
                .append(coopRequest.getPid());

        checkUrl = stringBuffer.toString().replaceAll(" ", "%20");
        logger.info("登陆游戏,url：【{}】",checkUrl);
        //Desktop.getDesktop().browse(new URI(checkUrl));
        return checkUrl;

    }


    //R2
    /*private static String generateSign(CoopRequest coopRequest) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(COOP_NAME).append(SIGN_SPLIT).append(coopRequest.getCoopName())
				.append("&").append(SERVER_ID).append(SIGN_SPLIT).append(coopRequest.getServerId())
				.append("&").append(USER_ID).append(SIGN_SPLIT).append(coopRequest.getUserId())
				.append("&").append("key=").append(CoopCfgUtil.getKey(coopRequest.getCoopName()))
				.append("&").append(TIMESTAMP).append(SIGN_SPLIT).append(coopRequest.getTimestamp());

		String md5value = Md5Utils.md5To32(buffer.toString());
		System.out.println(buffer.toString());
		System.out.println(md5value);
		return md5value;
	}*/

    //通用
    protected static String generateSign(CoopRequest coopRequest, String key) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(coopRequest.getPid());
        buffer.append(coopRequest.getServerId());
        buffer.append(coopRequest.getUserId());
        buffer.append(coopRequest.getTimestamp());
        buffer.append(key);
        String md5value = Md5Utils.md5To32(buffer.toString());
        logger.info("md5before:{}, after:{}", buffer.toString(), md5value);
        return md5value;
    }
}
