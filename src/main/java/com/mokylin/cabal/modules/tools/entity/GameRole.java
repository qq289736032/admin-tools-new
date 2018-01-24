package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.common.utils.excel.annotation.ExcelField;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/4 15:21
 * 项目: admin-tools
 * 申请邮件导入批量角色 模板 实体类
 */
public class GameRole extends MybatisBaseBean {


    private static final long serialVersionUID = -6553262288146078872L;

    private String name;

    @ExcelField(title = "角色名称", align = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
