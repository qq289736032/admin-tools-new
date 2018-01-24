package com.mokylin.cabal.common.persistence;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.utils.AdminDefaultThreadFactory;
import com.mokylin.cabal.common.utils.DateUtils;

/**
 * 作者: 稻草鸟人 日期: 2014/10/23 11:18 项目: cabal-tools
 */
@Component
public class LogDaoTemplate {

	private final Logger log = LoggerFactory.getLogger(getClass());

	// @Resource private SqlSessionTemplate toolSqlSession;
	@Resource
	private SqlSessionTemplate logSqlSession;

	private ExecutorService exec = Executors.newCachedThreadPool(new AdminDefaultThreadFactory("log-dao"));
	// private CompletionService<List<Map<String, Object>>> service = new
	// ExecutorCompletionService(exec);

	public <E> Page<E> paging(String statement, MybatisParameter<E> parameter) {
		Page<E> page = parameter.getPage();
		List<E> dataList = selectList(statement, parameter);
		page.setList(dataList);
		return page;
	}

	/**
	 * 方法还可以再优化下...以后再写另外的高级方法吧 多表分页
	 * 
	 * @param statement
	 *            sql
	 * @param parameter
	 *            参数 startDate&endDate yyyyMMdd
	 * @param page
	 *            分页对象
	 * @param orderBy
	 *            排序列，目前只能是一个字段
	 * @return
	 * @throws Exception
	 */
	public Page selectPage(final String statement, final Map parameter, Page page, final String orderBy)
			throws Exception {
		CompletionService<List<Map<String, Object>>> service = new ExecutorCompletionService(exec);
		// Page page = parameter.getPage();
		List<Map<String, Object>> list = Lists.newArrayList();
		int defaultTime = Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		final int from = MapUtils.getInteger(parameter, "startDate", defaultTime);
		int to = MapUtils.getInteger(parameter, "endDate", defaultTime);
		if (to < from) {
			throw new Exception("日期结束时间大于开始时间！");
		}
		int tableNum = DateUtils.getDays(DateUtils.parseDate(to), DateUtils.parseDate(from));
		CountDownLatch latch = new CountDownLatch(tableNum);
		List<Future<List<Map<String, Object>>>> futures = Lists.newArrayList();
		for (int i = 1; i <= tableNum + 1; i++) {
			int finalI = i;
			final String date = DateUtils.addDays(String.valueOf(from), finalI - 1);
			Callable<List<Map<String, Object>>> callable = new Callable<List<Map<String, Object>>>() {

				@Override
				public List<Map<String, Object>> call() throws Exception {
					Map newParameter = Maps.newHashMap();
					String suffix = StringUtils.replace(date, "-", "");
					newParameter.putAll(parameter);
					newParameter.put("suffix", suffix);
					return selectList(statement, newParameter);
				}
			};
			futures.add(service.submit(callable));

			latch.countDown();
		}

		latch.await();

		for (Future<List<Map<String, Object>>> future : futures) {
			try {
				list.addAll(future.get());
			} catch (Exception e) {
				log.error("", e);
			}
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Long start = MapUtils.getLong(o1, orderBy);
				Long end = MapUtils.getLong(o2, orderBy);
				return end.compareTo(start);
			}
		});

		Integer end = page.getPageNo() * page.getPageSize();
		if (end > list.size()) {
			end = list.size();
		}
		page.setCount(list.size());
		page.setList(list.subList((page.getPageNo() - 1) * page.getPageSize(), end));
		return page;
	}

	/**
	 * 方法还可以再优化下...以后再写另外的高级方法吧 多表分页
	 * 
	 * @param statement
	 *            sql
	 * @param parameter
	 *            参数 startDate&endDate yyyyMMdd
	 * @param page
	 *            分页对象
	 * @param orderBy
	 *            排序列，目前只能是一个字段
	 * @return
	 * @throws Exception
	 */
	public Page selectPage2(final String statement, final Map parameter, Page page, final String orderBy)
			throws Exception {
		CompletionService<List<Map<String, Object>>> service = new ExecutorCompletionService(exec);
		// Page page = parameter.getPage();
		List<Map<String, Object>> list = Lists.newArrayList();
		int defaultTime = Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		final int from = MapUtils.getInteger(parameter, "startDate", defaultTime);
		int to = MapUtils.getInteger(parameter, "endDate", defaultTime);
		if (to < from) {
			throw new Exception("日期结束时间大于开始时间！");
		}
		int month = Integer.valueOf(DateUtils.getMonth(DateUtils.parseDate(from)));
		int tableNum = DateUtils.getMonthSpace(DateUtils.parseDate(from), DateUtils.parseDate(to));
		CountDownLatch latch = new CountDownLatch(tableNum);
		List<Future<List<Map<String, Object>>>> futures = Lists.newArrayList();
		for (int i = 0; i <= tableNum; i++) {
			final String date = String.valueOf(month + i);
			Callable<List<Map<String, Object>>> callable = new Callable<List<Map<String, Object>>>() {
				@Override
				public List<Map<String, Object>> call() throws Exception {
					Map newParameter = Maps.newHashMap();
					String suffix = StringUtils.replace(date, "-", "");
					newParameter.putAll(parameter);
					newParameter.put("suffix", suffix);
					return selectList(statement, newParameter);
				}
			};
			futures.add(service.submit(callable));
			latch.countDown();
		}
		latch.await();
		for (Future<List<Map<String, Object>>> future : futures) {
			try {
				list.addAll(future.get());
			} catch (Exception e) {
				log.error("", e);
			}
		}
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Long start = MapUtils.getLong(o1, orderBy);
				Long end = MapUtils.getLong(o2, orderBy);
				return end.compareTo(start);
			}
		});
		Integer end = page.getPageNo() * page.getPageSize();
		if (end > list.size()) {
			end = list.size();
		}
		page.setCount(list.size());
		page.setList(list.subList((page.getPageNo() - 1) * page.getPageSize(), end));
		return page;
	}

	/**
	 * 多表查询
	 * 
	 * @param statement
	 * @param parameter
	 *            startDate&endDate yyyy-MM-dd
	 * @return
	 */
	public List<Map<String, Object>> selectListByMultiTable(final String statement, final Map parameter)
			throws Exception {
		CompletionService<List<Map<String, Object>>> service = new ExecutorCompletionService(exec);
		// Page page = parameter.getPage();
		List<Map<String, Object>> list = Lists.newArrayList();
		int defaultTime = Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		final int from = MapUtils.getInteger(parameter, "startDate", defaultTime);
		int to = MapUtils.getInteger(parameter, "endDate", defaultTime);
		if (to < from) {
			throw new Exception("日期结束时间大于开始时间！");
		}
		int tableNum = DateUtils.getDays(DateUtils.parseDate(to), DateUtils.parseDate(from));
		CountDownLatch latch = new CountDownLatch(tableNum);
		List<Future<List<Map<String, Object>>>> futures = Lists.newArrayList();
		for (int i = 1; i <= tableNum + 1; i++) {
			int finalI = i;
			final String date = DateUtils.addDays(String.valueOf(from), finalI - 1);
			Callable<List<Map<String, Object>>> callable = new Callable<List<Map<String, Object>>>() {

				@Override
				public List<Map<String, Object>> call() throws Exception {
					Map newParameter = Maps.newHashMap();
					String suffix = StringUtils.replace(date, "-", "");
					newParameter.putAll(parameter);
					newParameter.put("suffix", suffix);
					return selectList(statement, newParameter);
				}
			};
			futures.add(service.submit(callable));

			latch.countDown();
		}

		latch.await();

		for (Future<List<Map<String, Object>>> future : futures) {
			try {
				list.addAll(future.get());
				// System.out.println("%%%%%=====>>>>"+future.get());
			} catch (Exception e) {
				log.error("", e);
			}
		}

		return list;
	}

	public <E> List<E> selectList(String statement) {
		log.info(statement);
		return logSqlSession.selectList(statement);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		log.info(statement);
		return logSqlSession.selectList(statement, parameter);
	}

	// public <T> T selectOneByServerId(String serverId, String statement) {
	// return this.selectOneByServerId(serverId, statement, null);
	// }
	//
	// public <T> T selectOneByServerId(String serverId, String statement,
	// Object parameter) {
	// try{
	// if (DbConnectionUtils.testGameServer(serverId)) {
	// String originalServerId = LookupContext.getCurrentServerId();
	// LookupContext.setCurrentServerId(serverId);
	// T t = logSqlSession.selectOne(statement, parameter);
	// LookupContext.setCurrentServerId(originalServerId);
	// return t;
	// }
	// }
	// catch(Exception e){
	// log.error("服务器serverId:[" + serverId + "] ERROR",e);
	// }
	// return null;
	// }
	//
	// public <E> List<E> selectListByServerId(String serverId, String
	// statement) {
	// return this.selectListByServerId(serverId, statement, null);
	// }
	//
	// public <E> List<E> selectListByServerId(String serverId, String
	// statement, Object parameter) {
	// try{
	// if (DbConnectionUtils.testGameServer(serverId)) {
	// String originalServerId = LookupContext.getCurrentServerId();
	// LookupContext.setCurrentServerId(serverId);
	// List<E> list = logSqlSession.selectList(statement, parameter);
	// LookupContext.setCurrentServerId(originalServerId);
	// return list;
	// }
	// }
	// catch(Exception e){
	// log.error("服务器serverId:[" + serverId + "] ERROR",e);
	// }
	// return Collections.EMPTY_LIST;
	// }
	//
	// /**
	// * 返回所有服务器的结果集(同一个logDbUrl的serverId视为一个)
	// * @param serverIdList 服务器ID集合
	// * @param statement Mapping ID
	// * @param parameter 查询参数
	// * @return 所有服务器的List形式的查询结果的合集
	// */
	// public <E> List<E> selectListByServerIdList(List<String> serverIdList,
	// String statement, Object parameter) {
	// if (CollectionUtils.isEmpty(serverIdList)) {
	// return Collections.EMPTY_LIST;
	// }
	// serverIdList =
	// toolSqlSession.selectList("gameServer.getServerIdByLogDbUrlWithoutRepetition",
	// serverIdList);//去重复gameDbUrl
	// List<E> resultList = new ArrayList();
	// Map<String, List<E>> resultMap = this.selectByServerIdList(serverIdList,
	// statement, parameter);
	// for (List<E> list : resultMap.values()) {
	// resultList.addAll(list);
	// }
	// return resultList;
	// }
	// /**
	// * 返回所有服务器的结果集(同一个logDbUrl的serverId视为一个)
	// * @param serverIdList 服务器ID集合
	// * @param statement Mapping ID
	// * @param parameter 查询参数
	// * @return key:服务器Id value:对应serverId查询结果
	// */
	// public <E> Map<String, List<E>> selectMapByServerIdList(List<String>
	// serverIdList, String statement, Object parameter) {
	// if (CollectionUtils.isEmpty(serverIdList)) {
	// return Collections.EMPTY_MAP;
	// }
	// serverIdList =
	// toolSqlSession.selectList("gameServer.getServerIdByLogDbUrlWithoutRepetition",
	// serverIdList);//去重复gameDbUrl
	// return this.selectByServerIdList(serverIdList, statement, parameter);
	// }
	//
	// /**
	// * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	// * @param statement Mapping ID
	// * @return key:服务器Id value:对应serverId查询结果
	// */
	// public <E> Map<String, List<E>> selectMapAllServers(String statement) {
	// return this.selectMapAllServers(statement, null);
	// }
	//
	// /**
	// * 返回所有服务器的结果集(同一个logDbUrl的serverId视为一个)
	// * @param statement Mapping Id
	// * @param parameter 查询参数
	// * @return key:服务器Id value:对应serverId查询结果
	// */
	// public <E> Map<String, List<E>> selectMapAllServers(String statement,
	// Object parameter) {
	// List<String> serverIdList =
	// toolSqlSession.selectList("gameServer.getAllServerIdByLogDbUrlWithoutRepetition");//合并同样logDbUrl的serverId
	// log.info("查询所有服务器的数据 statement:[{}] parameter:[{}]", statement,
	// parameter);
	// return this.selectByServerIdList(serverIdList, statement, parameter);
	// }
	//
	// /**
	// * 返回所有未合服非测试服的数据
	// * @param statement
	// * @param parameter
	// * @param <E>
	// * @return
	// */
	// public <E> Map<String, List<E>> selectMapAllActiveServers(String
	// statement, Object parameter) {
	// List<String> serverIdList =
	// toolSqlSession.selectList("gameServer.getAllServerId");//合并同样logDbUrl的serverId
	// log.info("查询所有服务器的数据 statement:[{}] parameter:[{}]", statement,
	// parameter);
	// return this.selectByServerIdList(serverIdList, statement, parameter);
	// }
	//
	// /**
	// * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	// * @param statement Mapping Id
	// * @return 所有服务器的List形式的查询结果的合集
	// */
	// public <E> List<E> selectListAllServers(String statement) {
	// List<E> resultList = new ArrayList();
	// Map<String, List<E>> resultMap = this.selectMapAllServers(statement);
	// for (List<E> list : resultMap.values()) {
	// resultList.addAll(list);
	// }
	// return resultList;
	// }

	// /**
	// * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	// * @param statement Mapping Id
	// * @param parameter 查询参数
	// * @return 所有服务器的List形式的查询结果的合集
	// */
	// public <E> List<E> selectListAllServers(String statement, Object
	// parameter) {
	// List<E> resultList = new ArrayList();
	// Map<String, List<E>> resultMap = this.selectMapAllServers(statement,
	// parameter);
	// for (List<E> list : resultMap.values()) {
	// resultList.addAll(list);
	// }
	// return resultList;
	// }

	public <T> T selectOne(String statement) {
		log.info(statement);
		return logSqlSession.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		log.info(statement);
		return logSqlSession.selectOne(statement, parameter);
	}

	public int insert(String statement) {
		log.info(statement);
		return logSqlSession.insert(statement);
	}

	public int insert(String statement, Object parameter) {
		log.info(statement);
		return logSqlSession.insert(statement, parameter);
	}

	public int batchInsert(String statement, List list) {
		if (list == null)
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
		return list.size();
	}

	public int update(String statement) {
		return logSqlSession.update(statement);
	}

	public int update(String statement, Object parameter) {
		return logSqlSession.update(statement, parameter);
	}

	public int delete(String statement) {
		return logSqlSession.delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return logSqlSession.delete(statement, parameter);
	}

	// /**
	// * 按serverIdList查询各个服务器数据(只按照serverId查询不做dburl去重)
	// * @param serverIdList 服务器ID集合
	// * @param statement Maping Id
	// * @param parameter 查询参数
	// * @return key:服务器Id value:对应serverId查询结果
	// */
	// private <E> Map<String, List<E>> selectByServerIdList(List<String>
	// serverIdList, final String statement, final Object parameter) {
	// if (CollectionUtils.isEmpty(serverIdList)) {
	// return Collections.EMPTY_MAP;
	// }
	// StopWatch sw = new StopWatch();
	// sw.start();
	// Map<String, List<E>> resultMap = new LinkedHashMap();
	// List<Callable<Map<String, List<E>>>> callableList = new ArrayList();
	// for (final String serverIds : serverIdList) {
	// Callable<Map<String, List<E>>> callable = new Callable() {
	// @Override
	// public Map<String, List<E>> call() throws Exception {
	// Map<String, List<E>> map = new HashMap();
	// String serverId = StringUtils.substringBefore(serverIds, ",");
	// LookupContext.setCurrentServerId(serverId);
	// List<E> list = logSqlSession.selectList(statement, parameter);
	// map.put(serverIds, list);
	// return map;
	// }
	// };
	// callableList.add(callable);
	// }
	// try {
	// List<Future<Map<String, List<E>>>> futrueList=
	// exec.invokeAll(callableList);
	// for (Future<Map<String, List<E>>> future : futrueList) {
	// try {
	// resultMap.putAll(future.get());
	// } catch (Exception e) {
	// log.error(null,e);
	// }
	// }
	// } catch (InterruptedException e) {
	// log.error(null,e);
	// }
	// log.info("服务器数量:{} statementName:{} 执行时间:{}", serverIdList.size(),
	// statement, sw.getTime());
	// return resultMap;
	// }
}
