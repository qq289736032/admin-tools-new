/**
 * Copyright &copy; 2014-2015 <a href="httparamMap://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.service;

import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.sys.dao.LogDao;
import com.mokylin.cabal.modules.sys.entity.Log;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 日志Service
 *
 * @author 稻草鸟人
 * @version 2014-6-2
 */
@Service
@Transactional(readOnly = true)
public class LogService extends BaseService {

    @Autowired
    private LogDao logDao;

    public Log get(String id) {
        return logDao.get(id);
    }

    public Page<Log> find(Page<Log> page, Map<String, Object> paramMap) {
        DetachedCriteria dc = logDao.createDetachedCriteria();

        Long createById = StringUtils.toLong(paramMap.get("createById"));
        if (createById > 0) {
            dc.add(Restrictions.eq("createBy.id", createById));
        }

        String requestUri = Objects.toString(paramMap.get("requestUri"));
        if (StringUtils.isNotBlank(requestUri)) {
            dc.add(Restrictions.like("requestUri", "%" + requestUri + "%"));
        }

        String exception = Objects.toString(paramMap.get("exception"));
        if (StringUtils.isNotBlank(exception)) {
            dc.add(Restrictions.eq("type", Log.TYPE_EXCEPTION));
        }

        Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
        if (beginDate == null) {
            beginDate = DateUtils.setDays(new Date(), 1);
            paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
        }
        Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
        if (endDate == null) {
            endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
            paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
        }
        dc.add(Restrictions.between("createDate", beginDate, endDate));

        dc.addOrder(Order.desc("createDate"));
        return logDao.find(page, dc);
    }

    /**
     * 默认readOnly为false
     *
     * @param log
     */
    @Transactional(readOnly = false)
    public void save(Log log) {
        logDao.clear();
        logDao.save(log);
    }

}
