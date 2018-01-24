package com.mokylin.cabal.modules.log.service;

import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.modules.log.entity.UserLevel;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/11
 * admin-tools
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService {

    public void processWeek(int num,String levelInterval, Map<String,UserLevel> map){
        //String levelInterval = churnUserLevelInterval.getKey();
        if(map.containsKey(levelInterval)){
            UserLevel userLevel = map.get(levelInterval);
            userLevel.setWeekUser(userLevel.getWeekUser() + num);
        }else{
            UserLevel userLevel = new UserLevel();
            userLevel.setWeekUser(num);
            map.put(levelInterval, userLevel);
        }
    }


    /**
     *
     * @param map   返回值
     * @param type  处理的流失用户类型
     * @param churnUserMap  流失用户数据
     */
    public void process(Map<String,UserLevel> map, UserType type,List<Map<String,Object>> churnUserMap){
        for(Map<String,Object> churnMap : churnUserMap) {
            int level = MapUtils.getIntValue(churnMap, "level");
            int num = MapUtils.getIntValue(churnMap, "num");

            for(ConfigConstants.ChurnUserLevelInterval churnUserLevelInterval : ConfigConstants.ChurnUserLevelInterval.values()){
                String key = churnUserLevelInterval.getKey();
                String[] str = key.split("-");
                if(level >= Integer.parseInt(str[0]) && level <= Integer.parseInt(str[1])){
                    if(map.containsKey(key)){
                        UserLevel userLevel = map.get(key);
                        if(type == UserType.TYPE_WEEK){
                            userLevel.setWeekUser(userLevel.getWeekUser() + num);
                        }else if(type == UserType.TYPE_DOUBLE_WEEK){
                            userLevel.setDoubleWeekUser(userLevel.getDoubleWeekUser() + num);
                        }else if(type == UserType.TYPE_MONTH){
                            userLevel.setMonthUser(userLevel.getMonthUser() + num);
                        }
                    }else{
                        UserLevel userLevel = new UserLevel();
                        if(type == UserType.TYPE_WEEK){
                            userLevel.setWeekUser(num);
                            map.put(key, userLevel);
                        }else if(type == UserType.TYPE_DOUBLE_WEEK){
                            userLevel.setDoubleWeekUser(num);
                            map.put(key, userLevel);
                        }else if(type == UserType.TYPE_MONTH){
                            userLevel.setMonthUser(num);
                            map.put(key, userLevel);
                        }
                    }
                }
            }
        }
    }

    public enum UserType {
        TYPE_WEEK(1,"周流失/回流用户"),
        TYPE_DOUBLE_WEEK(2,"双周流失/回流用户"),
        TYPE_MONTH(3,"月流失/回流用户");


        private int key;
        private String desc;

        UserType(int key, String desc) {
            this.key = key;
            this.desc = desc;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
