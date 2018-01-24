package com.mokylin.cabal.common.persistence;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/17 11:41
 * 项目: cabal-tools
 */
@Component
public class GlobalDaoTemplate {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private SqlSessionTemplate globalSqlSession;

    public <E> Page<E> paging(String statement, MybatisParameter<E> parameter){
        Page<E> page = parameter.getPage();
        List<E> dataList = selectList(statement, parameter);
        page.setList(dataList);
        return page;
    }

    public <E> List<E> selectList(String statement) {
        return globalSqlSession.selectList(statement);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        return globalSqlSession.selectList(statement, parameter);
    }

    public <T> T selectOne(String statement) {
        return globalSqlSession.selectOne(statement);
    }

    public <T> T selectOne(String statement, Object parameter) {
        return globalSqlSession.selectOne(statement, parameter);
    }

    public int insert(String statement) {
        return globalSqlSession.insert(statement);
    }

    public int insert(String statement, Object parameter) {
        return globalSqlSession.insert(statement, parameter);
    }

    public int update(String statement) {
        return globalSqlSession.update(statement);
    }

    public int update(String statement, Object parameter) {
        return globalSqlSession.update(statement, parameter);
    }

    public int delete(String statement) {
        return globalSqlSession.delete(statement);
    }

    public int delete(String statement, Object parameter) {
        return globalSqlSession.delete(statement, parameter);
    }

    public void batchInsert(String statement, List paramsList){
        StopWatch sw = new StopWatch();
        sw.start();
        SqlSession batchSqlSession = null;
        try{
            batchSqlSession = globalSqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
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
        log.debug("批量新增 {}条记录  statement:[{}] 完成时间:[{}]", paramsList.size(), statement, sw.getTime());
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
        log.debug("批量新增 {}条记录  statement:[{}] 完成时间:[{}]", list.size(), statement, sw.getTime());
        return  list.size();
    }

    public void batchUpdate(String statement, List paramsList){
        StopWatch sw = new StopWatch();
        sw.start();
        SqlSession batchSqlSession = null;
        try{
            batchSqlSession = globalSqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
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
        log.debug("批量更新 {}条记录  statement:[{}] 完成时间:[{}]", paramsList.size(), statement, sw.getTime());
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
        log.debug("批量更新 {}条记录  statement:[{}] 完成时间:[{}]", list.size(), statement, sw.getTime());
        return  list.size();
    }
}
