package com.mokylin.cabal.modules.sys.utils;

import com.mokylin.cabal.common.core.DataContext;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import org.apache.commons.collections.MapUtils;
import sun.security.krb5.internal.PAData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/12
 * admin-tools
 */
public class AuthCondition {


    public static void filter(MybatisParameter parameter) {
        String sql = "";
        boolean isGlobalPlatformPermission = MapUtils.getBoolean(parameter, "isGlobalPlatformPermission");
        List<String> pidList = (List<String>) MapUtils.getObject(parameter, "pidList");
        if(isGlobalPlatformPermission) {
            sql = " AND 1 = 1";
        }else {
            int size = pidList.size();
            if(size == 1) {
                sql = " pid = '" + pidList.get(0) +"'";
            }else {
                int i = 0;
                sql = " AND pid in ( ";
                for(String pid : pidList) {
                    sql = sql + "'" + pid + "'";
                    i ++;
                    if(size > i) {
                        sql = sql + ",";
                    }
                }
                sql = sql + " )";
            }
        }
        parameter.put("filter", sql);
    }

    /**
     * 按照平台来查询
     * @param parameter
     */
    public static void filterPlatform(MybatisParameter parameter) {
        String pid = DataContext.getInstance(UserUtils.getUser().getId()).getPid();
        if(StringUtils.isNotEmpty(pid)){
            parameter.put("filter", " AND pid = '" + DataContext.getInstance(UserUtils.getUser().getId()).getPid() + "'");
        }else{
            parameter.put("filter", "");
        }
    }

}
