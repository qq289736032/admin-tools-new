package com.mokylin.cabal.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/10
 * admin-tools
 */
public class DataContext {

    private static final Map<String, DataContext> platformContext = new HashMap<String, DataContext>();


    private String userId;

    private String pid;

    private String serverId;

    private String name;    // 服务器名称

    public static void put(String userId, String pid, String serverId, String name) {
        DataContext dataContext = platformContext.get(userId);
        if (dataContext == null) {
            dataContext = new DataContext();
        }
        dataContext.userId = userId;
        dataContext.pid = pid;
        dataContext.serverId = serverId;
        dataContext.name = name;
        platformContext.put(userId, dataContext);
    }

    public static void destroy(String userId) {
        platformContext.remove(userId);
    }

    public static DataContext getInstance(String userId) {

        return platformContext.get(userId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

}
