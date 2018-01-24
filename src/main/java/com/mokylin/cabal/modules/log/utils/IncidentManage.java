package com.mokylin.cabal.modules.log.utils;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import org.apache.commons.collections.MapUtils;

/**
 */
public class IncidentManage {

	/**
	 * 按照事件类型来查询
	 * 
	 * @param parameter
	 */
	public static void filterOperationType(MybatisParameter parameter) {
		String operatorType = MapUtils.getString(parameter, "operatorType");
		if (StringUtils.isNotEmpty(operatorType)) {
			if (operatorType.contains(",")) {
				parameter.put("filter", " AND operate_type in (" + operatorType + ")");
			} else {
				parameter.put("filter", " AND operate_type = '" + operatorType + "'");
			}
		} else {
			parameter.put("filter", "");
		}

	}

}
