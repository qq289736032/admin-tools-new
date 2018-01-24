package com.mokylin.cabal.common.persistence;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/17 11:41
 * 项目: cabal-tools
 */
@Component
public class ToolDaoTemplate {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private SqlSessionTemplate toolSqlSession;

    public <E> Page<E> paging(String statement, MybatisParameter<E> parameter){
        Page<E> page = parameter.getPage();
        List<E> dataList = selectList(statement, parameter);
        page.setList(dataList);
        return page;
    }

    public <E> List<E> selectList(String statement) {
        return toolSqlSession.selectList(statement);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
    	try {
    		return toolSqlSession.selectList(statement, parameter);	
		} catch (Exception e) {
			log.error(e.getMessage() + "--" + statement);
		}
    	return null;
    }

    public <T> T selectOne(String statement) {
        //return toolSqlSession.selectOne(statement);
        try {
    		return toolSqlSession.selectOne(statement);	
		} catch (Exception e) {
			log.error(e.getMessage() + "--" + statement);
		}
    	return null;
    }

    public <T> T selectOne(String statement, Object parameter) {
        //return toolSqlSession.selectOne(statement, parameter);
        try {
    		return toolSqlSession.selectOne(statement, parameter);	
		} catch (Exception e) {
			log.error(e.getMessage() + "--" + statement);
		}
    	return null;
    }

    public int insert(String statement) {
        //return toolSqlSession.insert(statement);
        try {
    		return toolSqlSession.insert(statement);	
		} catch (Exception e) {
			log.error(e.getMessage() + "--" + statement);
		}
    	return 0;
    }

    public int insert(String statement, Object parameter) {
        return toolSqlSession.insert(statement, parameter);
    }

    public int update(String statement) {
        return toolSqlSession.update(statement);
    }

    public int update(String statement, Object parameter) {
        return toolSqlSession.update(statement, parameter);
    }

    public int delete(String statement) {
        return toolSqlSession.delete(statement);
    }

    public int delete(String statement, Object parameter) {
        return toolSqlSession.delete(statement, parameter);
    }

    public void batchInsert(String statement, List paramsList){
        StopWatch sw = new StopWatch();
        sw.start();
        SqlSession batchSqlSession = null;
        try{
            batchSqlSession = toolSqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            for (Object record : paramsList) {
                batchSqlSession.insert(statement, record);
            }
        }
        catch(Exception e){log.error(null, e);}
        finally{
            if (batchSqlSession != null) {
                batchSqlSession.commit();
                batchSqlSession.close();
            }
        }
        log.info("批量新增 {}条记录  statement:[{}] 完成时间:[{}]", paramsList.size(), statement, sw.getTime());
    }

    public int batchInsert2(String statement, List list){
        StopWatch sw = new StopWatch();
        sw.start();
        if(list == null || list.size()==0) return 0;
        if(list.size() > 500){
            int size = list.size();
            int divisor = size%500>0?size/500:size/500+1;
            for(int i=0; i<divisor; i++){
                int toindex = (i+1)*500;
                if( i == divisor - 1 ){
                    toindex = size;
                }
                List tempList = list.subList(i*500,toindex);
                if (tempList.size() == 0) {
                	continue;
                }
                insert(statement,tempList);
            }
        }else{
            insert(statement,list);
        }
        log.info("批量新增 {}条记录  statement:[{}] 完成时间:[{}]", list.size(), statement, sw.getTime());
        return  list.size();
    }
    
    // map里面再包一个list
    public int batchInsert2(String statement, Map<String, Object> map){
    	List list = (List)map.get("parmList");
        StopWatch sw = new StopWatch();
        sw.start();
        if(list == null || list.size()==0) return 0;
        if(list.size() > 500){
            int size = list.size();
            int divisor = size%500>0?size/500:size/500+1;
            for(int i=0; i<divisor; i++){
                int toindex = (i+1)*500;
                if( i == divisor - 1 ){
                    toindex = size;
                }
                List tempList = list.subList(i*500,toindex);
                if (tempList.size() == 0) {
                	continue;
                }
                map.put("parmList", tempList);
                insert(statement,map);
            }
        }else{
            insert(statement,map);
        }
        log.info("批量新增 {}条记录  statement:[{}] 完成时间:[{}]", list.size(), statement, sw.getTime());
        return  list.size();
    }

    public void batchUpdate(String statement, List paramsList){
        StopWatch sw = new StopWatch();
        sw.start();
        SqlSession batchSqlSession = null;
        try{
            batchSqlSession = toolSqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            for (Object record : paramsList) {
                batchSqlSession.update(statement, record);
            }
        }
        catch(Exception e){log.error(null, e);}
        finally{
            if (batchSqlSession != null) {
                batchSqlSession.commit();
                batchSqlSession.close();
            }
        }
        log.info("批量更新 {}条记录  statement:[{}] 完成时间:[{}]", paramsList.size(), statement, sw.getTime());
    }

    public int batchUpdate2(String statement, List list){
        StopWatch sw = new StopWatch();
        sw.start();
        if(list == null || list.size()==0) return 0;
        if(list.size() > 500){
            int size = list.size();
            int divisor = size%500>0?size/500:size/500+1;
            for(int i=0; i<divisor; i++){
                int toindex = (i+1)*500;
                if( i == divisor - 1 ){
                    toindex = size;
                }
                List tempList = list.subList(i*500,toindex);
                if (tempList.size() == 0) {
                	continue;
                }
                update(statement,tempList);
            }
        }else{
            update(statement,list);
        }
        log.info("批量更新 {}条记录  statement:[{}] 完成时间:[{}]", list.size(), statement, sw.getTime());
        return  list.size();
    }
}
