package com.mokylin.cabal.modules.rebate.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.RebateDaoTemplate;

@Transactional(readOnly=true)
@Service
public class RebateDetailService {
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;

}
