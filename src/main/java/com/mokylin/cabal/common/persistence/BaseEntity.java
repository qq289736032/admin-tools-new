/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.common.persistence;

import com.google.common.collect.Maps;
import com.mokylin.cabal.modules.sys.entity.User;
import com.mokylin.cabal.modules.sys.utils.UserUtils;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Map;

/**
 * Entity支持类
 *
 * @author 稻草鸟人
 * @version 2014-01-15
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前用户
     */
    protected User currentUser;

    /**
     * 当前实体分页对象
     */
    protected Page<T> page;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    protected Map<String, String> sqlMap;

    /**
     * 从页面搜索还是从菜单搜索（用于非页面搜索时设置默认搜索条件）
     */
    private boolean searchFromPage;

    /**
     * 用于搜索多个ID的时候设置搜索条件
     */
    private String ids;

    @XmlTransient
    @Transient
    public User getCurrentUser() {
        if (currentUser == null) {
            currentUser = UserUtils.getUser();
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @XmlTransient
    @Transient
    public Page<T> getPage() {
        if (page == null) {
            page = new Page<T>();
        }
        return page;
    }

    public Page<T> setPage(Page<T> page) {
        this.page = page;
        return page;
    }

    @XmlTransient
    @Transient
    public Map<String, String> getSqlMap() {
        if (sqlMap == null) {
            sqlMap = Maps.newHashMap();
        }
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

    @Transient
    public boolean isSearchFromPage() {
        return searchFromPage;
    }

    @Transient
    public void setSearchFromPage(boolean searchFromPage) {
        this.searchFromPage = searchFromPage;
    }

    @Transient
    public String getIds() {
        return ids;
    }

    @Transient
    public void setIds(String ids) {
        this.ids = ids;
    }


    // 显示/隐藏
    public static final String SHOW = "1";
    public static final String HIDE = "0";

    // 是/否
    public static final String YES = "1";
    public static final String NO = "0";

    // 删除标记（0：正常；1：删除；2：审核；）
    public static final String FIELD_DEL_FLAG = "delFlag";
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";

}
