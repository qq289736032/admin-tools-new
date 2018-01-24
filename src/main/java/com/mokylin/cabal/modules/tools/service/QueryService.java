package com.mokylin.cabal.modules.tools.service;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/25
 * admin-tools
 */
@Service
public class QueryService {

    public String[] getHeaders(List<Map<String,Object>> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String,Object> map = list.get(0);
            int size = map.size();
            String[] ret = new String[size];
            int i = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                String name = entry.getKey();
                ret[i] = name;
                i++;
            }

            return ret;
        }
        return null;
    }

}
