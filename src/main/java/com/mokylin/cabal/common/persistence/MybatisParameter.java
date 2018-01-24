package com.mokylin.cabal.common.persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/17 15:52
 * 项目: cabal-tools
 */
public class MybatisParameter<T> extends HashMap<String, Object> {

    private Page<T> page;

    public MybatisParameter() {
        //this.page = new Page<T>();
    }

    public MybatisParameter(HttpServletRequest request, HttpServletResponse response) {
        this.page = new Page<T>(request,response);
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}