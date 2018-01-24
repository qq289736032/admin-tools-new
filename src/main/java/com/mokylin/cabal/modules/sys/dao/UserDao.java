/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.dao;

import com.mokylin.cabal.common.persistence.BaseDao;
import com.mokylin.cabal.common.persistence.Parameter;
import com.mokylin.cabal.modules.sys.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 用户DAO接口
 * @author 稻草鸟人
 * @version 2014-8-23
 */
@Repository
public class UserDao extends BaseDao<User> {
	
	public List<User> findAllList() {
		return find("from User where delFlag=:p1 order by id", new Parameter(User.DEL_FLAG_NORMAL));
	}
	
	public User findByLoginName(String loginName){
		return getByHql("from User where loginName = :p1 and delFlag = :p2", new Parameter(loginName, User.DEL_FLAG_NORMAL));
	}

	public int updatePasswordById(String newPassword, String id){
		return update("update User set password=:p1 where id = :p2", new Parameter(newPassword, id));
	}
	
	public int updateLoginInfo(String loginIp, Date loginDate, String id){
		return update("update User set loginIp=:p1, loginDate=:p2 where id = :p3", new Parameter(loginIp, loginDate, id));
	}

    /**
     * 得到有相同role角色的所有userId
     * @param id
     * @return
     */
    public List<String> findUserIdsByUserId(String id){
//        return findBySql("select DISTINCT ui.id as userId from sys_user ui,sys_user_role ur where ur.user_id=ui.id and ur.role_id in (\n" +
//                "\t\tselect ur.role_id from sys_user ui,sys_user_role ur where ui.id=ur.user_id and ui.id = :p1\n" +
//                "\t)\n",new Parameter(id));

		return findBySql("SELECT distinct user_id FROM sys_user_role  WHERE role_id in(\n" +
				"\tSELECT distinct role_id FROM role_game_platform WHERE game_platform_id in (\n" +
				"\t\tselect p.game_platform_id pid from sys_role r,role_game_platform p\n" +
				"\t\twhere r.id = p.role_id\n" +
				"\t\tand r.id in (\n" +
				"\t\t\tSELECT ur.role_id FROM sys_user u,sys_user_role ur\n" +
				"\t\t\tWHERE u.id = ur.user_id\n" +
				"\t\t\tand u.id = :p1\n" +
				"\t\t)\n" +
				"\t)\n" +
				")", new Parameter(id));
    }
	
}
