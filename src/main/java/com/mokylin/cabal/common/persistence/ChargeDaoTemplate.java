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


@Component
public class ChargeDaoTemplate {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private SqlSessionTemplate chargeSqlSession;

    public <E> Page<E> paging(String statement, MybatisParameter<E> parameter){
        Page<E> page = parameter.getPage();
        List<E> dataList = selectList(statement, parameter);
        page.setList(dataList);
        return page;
    }

    public <E> List<E> selectList(String statement) {
        return chargeSqlSession.selectList(statement);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        return chargeSqlSession.selectList(statement, parameter);
    }

    public <T> T selectOne(String statement) {
        return chargeSqlSession.selectOne(statement);
    }

    public <T> T selectOne(String statement, Object parameter) {
        return chargeSqlSession.selectOne(statement, parameter);
    }

    public int insert(String statement) {
        return chargeSqlSession.insert(statement);
    }

    public int insert(String statement, Object parameter) {
        return chargeSqlSession.insert(statement, parameter);
    }

    public int update(String statement) {
        return chargeSqlSession.update(statement);
    }

    public int update(String statement, Object parameter) {
        return chargeSqlSession.update(statement, parameter);
    }

    public int delete(String statement) {
        return chargeSqlSession.delete(statement);
    }

    public int delete(String statement, Object parameter) {
        return chargeSqlSession.delete(statement, parameter);
    }

    public void batchInsert(String statement, List paramsList){
        StopWatch sw = new StopWatch();
        sw.start();
        SqlSession batchSqlSession = null;
        try{
            batchSqlSession = chargeSqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
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
            batchSqlSession = chargeSqlSession.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
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
