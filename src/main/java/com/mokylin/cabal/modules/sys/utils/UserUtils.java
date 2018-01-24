/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.utils;


import java.security.Provider;
import java.text.DecimalFormat;
import java.util.*;

import com.google.common.collect.Lists;

import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mokylin.cabal.common.redis.Country;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.redis.SystemConstant;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.modules.sys.dao.*;
import com.mokylin.cabal.modules.sys.entity.*;
import com.mokylin.cabal.modules.sys.security.SystemAuthorizingRealm;
import com.mokylin.cabal.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.mokylin.cabal.modules.tools.dao.GamePlatformDao;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.*;


/**
 * 用户工具类
 *
 * @author 稻草鸟人
 * @version 2014-5-29
 */
public class UserUtils extends BaseService {

    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
    private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
    private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
    private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
    private static GamePlatformDao gamePlatformDao = SpringContextHolder.getBean(GamePlatformDao.class);

    public static final String CACHE_USER = "user";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";
    public static final String CACHE_AREA_LIST = "areaList";
    public static final String CACHE_OFFICE_LIST = "officeList";
    public static final String CACHE_GAME_PLATFORM_SET = "gamePlatformSet";
    public static final String CACHE_GAME_PLATFORM_LIST = "gamePlatformList";
    public static final String CACHE_ROLE_USER_ID_LIST = "roleUserIdList";

    public static User getUser() {
        User user = (User) getCache(CACHE_USER);
        if (user == null) {
            try {
                Subject subject = SecurityUtils.getSubject();
                Principal principal = (Principal) subject.getPrincipal();
                if (principal != null) {
                    user = userDao.get(principal.getId());
//					Hibernate.initialize(user.getRoleList());
                    putCache(CACHE_USER, user);
                }
            } catch (UnavailableSecurityManagerException e) {

            } catch (InvalidSessionException e) {

            }
        }
        if (user == null) {
            user = new User();
            try {
                SecurityUtils.getSubject().logout();
            } catch (UnavailableSecurityManagerException e) {

            } catch (InvalidSessionException e) {

            }
        }
        return user;
    }

    public static User getUser(boolean isRefresh) {
        if (isRefresh) {
            removeCache(CACHE_USER);
        }
        return getUser();
    }

    public static List<Role> getRoleList() {
        @SuppressWarnings("unchecked")
        List<Role> list = (List<Role>) getCache(CACHE_ROLE_LIST);
        if (list == null) {
            User user = getUser();
            DetachedCriteria dc = roleDao.createDetachedCriteria();
            dc.createAlias("office", "office");
            dc.createAlias("userList", "userList", JoinType.LEFT_OUTER_JOIN);
            dc.add(dataScopeFilter(user, "office", "userList"));
            dc.add(Restrictions.eq(Role.FIELD_DEL_FLAG, Role.DEL_FLAG_NORMAL));
            dc.addOrder(Order.asc("office.code")).addOrder(Order.asc("name"));
            list = roleDao.find(dc);
            putCache(CACHE_ROLE_LIST, list);
        }
        return list;
    }

    public static List<Menu> getMenuList() {
        @SuppressWarnings("unchecked")
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            User user = getUser();
            if (user.isAdmin()) {
                menuList = menuDao.findAllList();
            } else {
                menuList = menuDao.findByUserId(user.getId());
            }
            putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }


    //用户是否具有全平台访问权限
    public static boolean hasAllPlatformPermission(User user){
        boolean flag = false;
        for(Role role : user.getRoleList()){
            if(role.hasAllPlatformPermission()){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 根据UserId取得具有相同角色的所有的UserId，这个用于查询数据时权限
     * @param user
     * @return
     */
    public static List<String> getTheSameRoleUserIds(User user){
        List<String> roleUserIdList = (List<String>) getCache(CACHE_ROLE_USER_ID_LIST);
        if(roleUserIdList == null){
            roleUserIdList = userDao.findUserIdsByUserId(user.getId());
            putCache(CACHE_ROLE_USER_ID_LIST,roleUserIdList);
        }
        return roleUserIdList;
    }


    /**
     * 获取角色所有的服务器列表
     * @return serverIdList
     */
    public static List<String> getCurrentUserServerIdList(){
        Set<String> serverIdList = Sets.newHashSet();
        List<GamePlatform> platFormList = UserUtils.getGamePlatformListContainServer();
        for (GamePlatform gamePlatform : platFormList) {
            List<Server> serverList = gamePlatform.getGameServerList();
            for (Server server : serverList) {
                serverIdList.add(String.valueOf(server.getWorldId()));
            }
        }

        return new ArrayList<String>(serverIdList);
    }


    public static List<GamePlatform> getAllGamePlatform() {
        List<GamePlatform> gamePlatformList = (List<GamePlatform>) getCache(CACHE_GAME_PLATFORM_LIST);
        if (gamePlatformList == null) {
            gamePlatformList = gamePlatformDao.findAllList();
            
            putCache(CACHE_GAME_PLATFORM_LIST, gamePlatformList);
        }
        
        return gamePlatformList;
    }

    /**
     * 管理员具有所有服的权限，Role属性isGlobal为1的也具有所有平台权限
     * 不同角色可能具有相同平台权限，这里去除了重复的平台
     * @return
     */
    public static List<GamePlatform> getGamePlatformList() {
        Set<GamePlatform> gamePlatforms;
        User user = getUser();
        if (user.isAdmin()) {
            gamePlatforms = new HashSet<GamePlatform>(getAllGamePlatform());
        } else {
            gamePlatforms = Sets.newHashSet();
            for (Role role : user.getRoleList()) {
                if(role.hasAllPlatformPermission()){
                    gamePlatforms = new HashSet<GamePlatform>(getAllGamePlatform());
                    break;
                }
                for (GamePlatform gamePlatform : role.getGamePlatformList()) {
                    gamePlatforms.add(gamePlatform);
                }
            }
        }
        List <GamePlatform>   list=  new ArrayList<GamePlatform>(gamePlatforms);
        Collections.sort(list, new Comparator<GamePlatform>() {
            @Override
            public int compare(GamePlatform o1, GamePlatform o2) {
                int i = 0, j = 0;
                if (StringUtils.isNotEmpty(o1.getPid())) {
                    String p1 = o1.getPid().substring(0, 1);
                    if (com.mokylin.cabal.common.utils.StringUtils.isNum(p1)) {
                        i = Integer.parseInt(p1);
                    } else {
                        i = p1.hashCode();
                    }
                }
                if (StringUtils.isNotEmpty(o2.getPid())) {
                    String p2 = o2.getPid().substring(0, 1);
                    if (com.mokylin.cabal.common.utils.StringUtils.isNum(p2)) {
                        j = Integer.parseInt(p2);
                    } else {
                        j = p2.hashCode();
                    }
                }
                return i - j;
            }
        });
        return list;
    }


    public static List<String> getUserPlatformList(){
        List<GamePlatform> gamePlatforms = getGamePlatformList();
        List<String> pids = Lists.newArrayList();
        for(GamePlatform gamePlatform : gamePlatforms){
            pids.add(gamePlatform.getPid());
        }
        return pids;
    }

    
    /**
     * 
     * @return
     */
    public static List<GamePlatform> getGamePlatformListContainServer() {
    	List<GamePlatform> gamePlatforms = getGamePlatformList();
    	
        for(GamePlatform gamePlatform : gamePlatforms) {
        	Collection<Server> servers = GameAreaUtils.getGameServerList(gamePlatform.getPid());
        	if(servers.size() > 0) {
                List<Server> serverList = Lists.newArrayList();
                for(Server server : servers) {
                    serverList.add(server);
                }
                Collections.sort(serverList, new Comparator<Server>() {
                    @Override
                    public int compare(Server o1, Server o2) {
                        return o1.getWorldId() - o2.getWorldId();
                    }
                });
                gamePlatform.setGameServerList(serverList);
        	}
        }
        
        return new ArrayList<GamePlatform>(gamePlatforms);
    }

//    //当前用户所有,这里有重复的平台
//    public static List<GamePlatform> getCurrentUserGamePlatformList() {
//        List<GamePlatform> gamePlatformList = Lists.newArrayList();
//        for (Role role : getUser().getRoleList()) {
//            for (GamePlatform gamePlatform : role.getGamePlatformList()) {
//                gamePlatformList.add(gamePlatform);
//            }
//        }
//        return gamePlatformList;
//    }

    public static List<Area> getAreaList() {
        @SuppressWarnings("unchecked")
        List<Area> areaList = (List<Area>) getCache(CACHE_AREA_LIST);
        if (areaList == null) {
//			User user = getUser();
//			if (user.isAdmin()){
            areaList = areaDao.findAllList();
//			}else{
//				areaList = areaDao.findAllChild(user.getArea().getId(), "%,"+user.getArea().getId()+",%");
//			}
            putCache(CACHE_AREA_LIST, areaList);
        }
        return areaList;
    }


    public static List<Office> getOfficeList() {
        @SuppressWarnings("unchecked")
        List<Office> officeList = (List<Office>) getCache(CACHE_OFFICE_LIST);
        if (officeList == null) {
            User user = getUser();
//			if (user.isAdmin()){
//				officeList = officeDao.findAllList();
//			}else{
//				officeList = officeDao.findAllChild(user.getOffice().getId(), "%,"+user.getOffice().getId()+",%");
//			}
            DetachedCriteria dc = officeDao.createDetachedCriteria();
            dc.add(dataScopeFilter(user, dc.getAlias(), ""));
            dc.add(Restrictions.eq("delFlag", Office.DEL_FLAG_NORMAL));
            dc.addOrder(Order.asc("code"));
            officeList = officeDao.find(dc);
            putCache(CACHE_OFFICE_LIST, officeList);
        }
        return officeList;
    }


    public static User getUserById(String id) {
        if (StringUtils.isNotBlank(id)) {
            return userDao.get(id);
        } else {
            return null;
        }
    }

    // ============== User Cache ==============

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        Object obj = getCacheMap().get(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value) {
        getCacheMap().put(key, value);
    }

    public static void removeCache(String key) {
        getCacheMap().remove(key);
    }

    public static Map<String, Object> getCacheMap() {
        Map<String, Object> map = Maps.newHashMap();
        try {
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal) subject.getPrincipal();
            return principal != null ? principal.getCacheMap() : map;
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e) {

        }
        return map;
    }

	public static Server getDefaultGameServer(User user) {
		for (Role role : user.getRoleList()) {
			for (GamePlatform gamePlatform : role.getGamePlatformList()) {
				String pid = gamePlatform.getPid();
				Collection<Server> serverList = GameAreaUtils.getGameServerList(pid);
				for (Server server : serverList) {
					if (server.getStatus() == SystemConstant.SERVER_STATUS_OPENING) {
						return server;
					}
				}
			}
		}
		return null;
	}


    /**
     * @return
     */
    public static List<Server> getCurrentGameServerList(){
        return RedisUtils.getGameServerListByPid();
    }
    //国家
    public static List<Country> getCountry(String crossId){
		return RedisUtils.getCountry(crossId);
	}
    
    public  static   String  fourFiveData(double  twoString){
		return  String.format("%.2f", twoString);
	}
    
}
