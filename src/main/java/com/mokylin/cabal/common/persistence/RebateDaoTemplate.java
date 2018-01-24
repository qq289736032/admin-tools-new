package com.mokylin.cabal.common.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class RebateDaoTemplate {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Resource
	private SqlSessionTemplate rebateSqlSession;

	public <T> T selectOne(String statement) {
		return rebateSqlSession.selectOne(statement);
	}
	
	public <T> T selectOne(String statement, Object parameter) {
		return rebateSqlSession.selectOne(statement, parameter);
	}

	public <E> List<E> selectList(String statement) {
		return rebateSqlSession.selectList(statement);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return rebateSqlSession.selectList(statement, parameter);
	}
	
	/**
	 * 分页
	 * @param statement
	 * @param parameter
	 * @param b 
	 * @return
	 */
	public <E> List<E> selectList(String statement, Object parameter,RowBounds b) {
		return rebateSqlSession.selectList(statement, parameter, b);
	}

	public int update(String statement, Object parameter) {
		return rebateSqlSession.update(statement, parameter);
	}

	public int batchInsert2(String statement, List list) {
		StopWatch sw = new StopWatch();
		sw.start();
		if (list == null || list.size() == 0)
			return 0;
		if (list.size() > 500) {
			int size = list.size();
			int divisor = size % 500 > 0 ? size / 500 : size / 500 + 1;
			for (int i = 0; i < divisor; i++) {
				int toindex = (i + 1) * 500;
				if (i == divisor - 1) {
					toindex = size;
				}
				List tempList = list.subList(i * 500, toindex);
				if (tempList.size() == 0) {
					continue;
				}
				insert(statement, tempList);
			}
		} else {
			insert(statement, list);
		}
		log.debug("批量新增 {}条记录  statement:[{}] 完成时间:[{}]", list.size(),
				statement, sw.getTime());
		return list.size();
	}

	public int insert(String statement, Object parameter) {
		return rebateSqlSession.insert(statement, parameter);
	}
}
