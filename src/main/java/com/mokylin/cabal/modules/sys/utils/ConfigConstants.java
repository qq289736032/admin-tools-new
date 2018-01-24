package com.mokylin.cabal.modules.sys.utils;

import com.mokylin.cabal.common.config.Global;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/23 17:03
 * 项目: cabal-tools
 *
 * 全局常量
 */
public class ConfigConstants {

    public static final String SELECTED_SERVER_KEY = "curServerId";

    public static final String DEFAULT_PLATFORM_KEY = "curPlatformId";


    //操作类型

    public static final String OPERATION_TYPE_SILENCE = "0";    //禁言

    public static final String OPERATION_TYPE_FREEZE = "1";     //封号

    public static final String OPERATION_TYPE_CANCEL_SILENCE = "3"; //取消禁言

    public static final String OPERATION_TYPE_CANCEL_FREEZE = "4";  //取消封号


    public static final String KEY_JIN_BI_LIMIT = "jinbi.limit";

    public static final String KEY_GOODS_LIMIT = "goods.limit";

    public static final String KEY_NOTICE = "key-game-update-list";

    public static final String JIN_BI_LIMIT ="100000";

    public static final String TICKET = "NITABAHAIZHENGEINIPOLE";

    public static final String CHANNEL_JINBI = "channel-jinbi";

    public static final String CHANNEL_GOODS = "channel-goods";

    public static final String CHANNEL_NOTICE = "channel-game-update-list";


    //版本日志
    public enum ReleaseNoteStatus {

        PUBLISHED(1),UNPUBLISHED(0);

        private int value;

        ReleaseNoteStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum DEMO {
        LEVEL_1("LEVEL_1","1-10"),LEVEL_2("LEVEL_2","11-20");
        private String key;
        private String value;

        DEMO(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    //流失用户等级区间
    public enum ChurnUserLevelInterval {
        LEVEL_INTERVAL_1("1-10"),
        LEVEL_INTERVAL_2("11-20"),
        LEVEL_INTERVAL_3("21-30"),
        LEVEL_INTERVAL_4("31-40"),
        LEVEL_INTERVAL_5("41-50"),
        LEVEL_INTERVAL_6("51-60"),
        LEVEL_INTERVAL_7("61-70"),
        LEVEL_INTERVAL_8("71-80"),
        LEVEL_INTERVAL_9("81-90"),
        LEVEL_INTERVAL_10("91-100"),
        LEVEL_INTERVAL_11("101-110"),
        LEVEL_INTERVAL_12("111-120"),
        LEVEL_INTERVAL_13("121-130"),
        LEVEL_INTERVAL_14("131-140"),
        LEVEL_INTERVAL_15("141-150"),
        LEVEL_INTERVAL_16("151-200"),
        LEVEL_INTERVAL_17("201-250"),
        LEVEL_INTERVAL_18("251-300"),
        LEVEL_INTERVAL_19("301-350"),
        LEVEL_INTERVAL_20("351-400"),
        LEVEL_INTERVAL_21("401-450"),
        LEVEL_INTERVAL_22("451-500");


        private String key;

        ChurnUserLevelInterval(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

}
