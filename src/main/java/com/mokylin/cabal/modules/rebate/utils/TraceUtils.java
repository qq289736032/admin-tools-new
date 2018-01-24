package com.mokylin.cabal.modules.rebate.utils;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.modules.sys.utils.UserUtils;

public class TraceUtils {
	public static void saveTrace(MybatisBaseBean bean){
		bean.setCreateBy(UserUtils.getUser().getId());
		bean.setCreateName(UserUtils.getUser().getLoginName());
		bean.setUpdateBy(UserUtils.getUser().getLoginName());
	}
	
	public static void updateTrace(MybatisBaseBean bean){
		bean.setUpdateBy(UserUtils.getUser().getLoginName());
	}
}
