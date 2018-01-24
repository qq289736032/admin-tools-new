/*
Navicat MySQL Data Transfer

Source Server         : 231_3306
Source Server Version : 50627
Source Host           : 192.168.1.231:3306
Source Database       : admin-tools

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-08-08 15:10:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `activity_config`
-- ----------------------------
DROP TABLE IF EXISTS `activity_config`;
CREATE TABLE `activity_config` (
  `id` varchar(255) NOT NULL,
  `alias` varchar(200) DEFAULT NULL COMMENT '活动别名（英文名）',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `create_name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_config
-- ----------------------------

-- ----------------------------
-- Table structure for `activity_list`
-- ----------------------------
DROP TABLE IF EXISTS `activity_list`;
CREATE TABLE `activity_list` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '配置文件名',
  `activity_name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `activity_desc` varchar(255) DEFAULT NULL COMMENT '活动描述',
  `original_file` varchar(255) DEFAULT NULL COMMENT '原文件名',
  `md5` varchar(32) DEFAULT NULL,
  `is_global` int(1) DEFAULT NULL COMMENT '是否全服',
  `server_ids` varchar(255) DEFAULT NULL COMMENT '服务器列表',
  `create_name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_list
-- ----------------------------

-- ----------------------------
-- Table structure for `analyze_file`
-- ----------------------------
DROP TABLE IF EXISTS `analyze_file`;
CREATE TABLE `analyze_file` (
  `id` varchar(64) NOT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `new_name` varchar(200) DEFAULT NULL,
  `file_path` varchar(200) DEFAULT NULL,
  `file_desc` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of analyze_file
-- ----------------------------

-- ----------------------------
-- Table structure for `feed_back`
-- ----------------------------
DROP TABLE IF EXISTS `feed_back`;
CREATE TABLE `feed_back` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `platform_id` varchar(50) DEFAULT NULL,
  `server_id` bigint(20) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `is_replied` bit(1) DEFAULT NULL COMMENT '是否已回复玩家',
  `replied_title` varchar(255) DEFAULT NULL COMMENT '回复玩家标题',
  `replied_content` varchar(255) DEFAULT NULL COMMENT '回复玩家正文',
  `feedback_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contact_info` varchar(255) DEFAULT NULL,
  `feedback_content` text COMMENT '运营回复玩家的内容',
  `total_recharge` decimal(10,2) DEFAULT NULL COMMENT '总充值RMB',
  `vip_level` int(11) DEFAULT NULL COMMENT '-1表示免费用户，0表示付费用户，1表示VIP1，以此类推',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feed_back
-- ----------------------------

-- ----------------------------
-- Table structure for `game_area`
-- ----------------------------
DROP TABLE IF EXISTS `game_area`;
CREATE TABLE `game_area` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `world_id` int(11) DEFAULT NULL,
  `area_id` int(11) NOT NULL,
  `world_name` varchar(255) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `area_type` int(11) DEFAULT NULL,
  `external_ip` varchar(255) DEFAULT NULL,
  `tcp_port` int(11) DEFAULT NULL,
  `web_port` varchar(255) DEFAULT NULL,
  `inner_port` varchar(255) DEFAULT NULL,
  `inner_ip` varchar(255) DEFAULT NULL,
  `pid` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `follower_id` int(11) DEFAULT NULL,
  `open_time` timestamp NULL DEFAULT '2000-01-01 00:00:00' COMMENT '开服时间',
  `server_version` varchar(255) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_area
-- ----------------------------
INSERT INTO game_area VALUES ('02e6d534d7ca425a819f73ef90ee69ae', '101', '0', 's101', 's101', '1', '192.168.1.231', '8888', '10086', '0', '192.168.1.231', 'baidu', '0', '0', '2016-08-02 16:07:01', 'not found	\nnot found', '2016-08-05 16:04:03');
INSERT INTO game_area VALUES ('50d18f69ec70456a904e9f4580017931', '5', '0', 's5', 's5', '1', '192.168.1.159', '8888', '10086', '0', '192.168.1.159', 'bs', '0', '0', '2016-07-29 20:34:41', 'not found	\nnot found', '2016-08-02 16:58:27');
INSERT INTO game_area VALUES ('5ba3ad4d74164035913b3b953f695686', '1', '0', '内网s1', '内网s1', '1', '192.168.1.231', '8888', '10086', '0', '192.168.1.231', '1', '0', '0', '2016-08-01 08:52:56', '20160806195547	\nnot found', '2016-08-08 14:53:03');
INSERT INTO game_area VALUES ('bf68be209a2e4bbf8037f0980412bbd5', '2', '0', 's2', 's2', '1', '192.168.1.192', '8888', '10086', '0', '192.168.1.192', 'gj', '0', '0', '2016-08-02 20:11:53', 'not found	\nnot found', '2016-08-02 21:21:14');
INSERT INTO game_area VALUES ('f6e87972514b4f8dad33472d92d29359', '102', '0', 's102', 's102', '1', '192.168.1.231', '8888', '10086', '0', '192.168.1.231', 'baidu', '0', '0', '2016-08-02 16:11:30', 'not found	\nnot found', '2016-08-02 16:58:27');

-- ----------------------------
-- Table structure for `game_command_log`
-- ----------------------------
DROP TABLE IF EXISTS `game_command_log`;
CREATE TABLE `game_command_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `server_ids` text COMMENT '服务器列表',
  `command` varchar(255) DEFAULT NULL COMMENT '协议',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态:1开启0关闭',
  `create_name` varchar(100) DEFAULT NULL COMMENT '添加人姓名',
  `create_by` varchar(64) DEFAULT NULL COMMENT '添加人ID',
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_command_log
-- ----------------------------

-- ----------------------------
-- Table structure for `game_email`
-- ----------------------------
DROP TABLE IF EXISTS `game_email`;
CREATE TABLE `game_email` (
  `id` varchar(64) NOT NULL,
  `server_ids` text COMMENT '服务器列表',
  `receiver_names` longtext COMMENT '角色名',
  `receiver_user_ids` longtext COMMENT '玩家账号',
  `vip_level` int(11) DEFAULT NULL COMMENT 'vip等级',
  `role_level` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `attachments` text COMMENT '物品列表',
  `jb` int(11) DEFAULT NULL COMMENT '金币',
  `yb` int(11) DEFAULT NULL COMMENT '元宝',
  `delay_hours` int(11) DEFAULT '0',
  `is_binding` int(11) DEFAULT NULL COMMENT '是否绑定 1是 0否',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人-申请人',
  `approve_by` varchar(64) DEFAULT NULL COMMENT '确认发送人-审批人',
  `approve_name` varchar(100) DEFAULT NULL,
  `email_status` int(1) DEFAULT '0' COMMENT '0发送完毕 1等待审批 2审批失败',
  `is_global` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_email
-- ----------------------------

-- ----------------------------
-- Table structure for `game_notice`
-- ----------------------------
DROP TABLE IF EXISTS `game_notice`;
CREATE TABLE `game_notice` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `content` text NOT NULL,
  `server_ids` text,
  `start_time` datetime NOT NULL COMMENT '公告开始发送时间',
  `finish_time` datetime NOT NULL,
  `step_time` int(11) DEFAULT NULL COMMENT '公告发送间隔时间(分钟)',
  `repeat_count` int(11) DEFAULT NULL COMMENT '重复次数',
  `notice_type` varchar(1) DEFAULT NULL COMMENT '公告类型0世界公告1滚动公告2系统公告',
  `notice_status` varchar(1) DEFAULT '0' COMMENT '公告状态',
  `is_global` int(1) DEFAULT NULL COMMENT '是否全部服务器',
  `game_platform_id` varchar(64) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建者姓名',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `game_platform`
-- ----------------------------
DROP TABLE IF EXISTS `game_platform`;
CREATE TABLE `game_platform` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '平台名',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sign_key` varchar(100) DEFAULT NULL,
  `pid` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `game_platform_name` (`name`) USING BTREE,
  KEY `game_platform_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游戏平台表';

-- ----------------------------
-- Records of game_platform
-- ----------------------------
INSERT INTO game_platform VALUES ('25049562a39045439a41922cb91fee58', '桂杰', '和哈哈哈哈哈哈哈哈哈', '哈哈哈哈哈哈', 'gj', '1', '2016-08-02 20:14:43', '1', '2016-08-02 20:14:43', null, '1');
INSERT INTO game_platform VALUES ('3f27a495225241a2b903022868f7c1e3', 'bs', 'bs', null, 'bs', 'system', '2016-08-02 16:58:27', null, null, null, '0');
INSERT INTO game_platform VALUES ('91c265727499491f84e860fac5bdc907', 'baidu', 'baidu', null, 'baidu', 'system', '2016-08-02 16:58:27', null, null, null, '0');
INSERT INTO game_platform VALUES ('a6d7fe3ea6bf4c16aa3f86d156fc3b0b', 'gj', 'gj', null, 'gj', 'system', '2016-08-02 20:11:51', null, null, null, '0');

-- ----------------------------
-- Table structure for `game_server`
-- ----------------------------
DROP TABLE IF EXISTS `game_server`;
CREATE TABLE `game_server` (
  `id` varchar(64) NOT NULL,
  `server_id` varchar(100) DEFAULT NULL,
  `server_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `game_platform_id` varchar(64) DEFAULT NULL,
  `game_db_url` varchar(200) DEFAULT NULL,
  `log_db_url` varchar(200) DEFAULT NULL,
  `gate_url` varchar(200) DEFAULT NULL,
  `game_server_remote_url` varchar(200) DEFAULT NULL,
  `server_status` int(1) DEFAULT NULL,
  `is_hefu` int(2) DEFAULT '0',
  `hefu_target` varchar(36) DEFAULT NULL,
  `hefu_time` timestamp NULL DEFAULT NULL,
  `is_test` int(2) DEFAULT '0',
  `server_open_time` timestamp NULL DEFAULT NULL COMMENT '开服时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='游戏服务器';

-- ----------------------------
-- Records of game_server
-- ----------------------------

-- ----------------------------
-- Table structure for `gift_base`
-- ----------------------------
DROP TABLE IF EXISTS `gift_base`;
CREATE TABLE `gift_base` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '礼包名称',
  `content` varchar(256) DEFAULT NULL COMMENT '内容',
  `attachments` text,
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_name` varchar(64) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼包的基础表';

-- ----------------------------
-- Records of gift_base
-- ----------------------------

-- ----------------------------
-- Table structure for `gift_code_0`
-- ----------------------------
DROP TABLE IF EXISTS `gift_code_0`;
CREATE TABLE `gift_code_0` (
  `id` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL COMMENT '激活码',
  `gift_id` varchar(64) NOT NULL COMMENT '礼包名称',
  `gift_name` varchar(64) NOT NULL,
  `gift_create_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '对应的礼包生成吗',
  `platform_id` varchar(64) NOT NULL DEFAULT '0' COMMENT '平台',
  `create_time` datetime NOT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `use_name` varchar(64) DEFAULT NULL,
  `use_by` varchar(0) DEFAULT NULL,
  `use_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 未使用 1 已使用',
  PRIMARY KEY (`id`),
  KEY `idx_code_0` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼包码分表';

-- ----------------------------
-- Records of gift_code_0
-- ----------------------------

-- ----------------------------
-- Table structure for `gift_code_1`
-- ----------------------------
DROP TABLE IF EXISTS `gift_code_1`;
CREATE TABLE `gift_code_1` (
  `id` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL,
  `gift_id` varchar(64) NOT NULL,
  `gift_name` varchar(64) NOT NULL,
  `gift_create_id` varchar(64) NOT NULL DEFAULT '0',
  `platform_id` varchar(64) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `create_name` varchar(64) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `use_name` varchar(64) DEFAULT NULL,
  `use_by` varchar(64) DEFAULT NULL,
  `use_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gift_code_1
-- ----------------------------

-- ----------------------------
-- Table structure for `gift_create_code`
-- ----------------------------
DROP TABLE IF EXISTS `gift_create_code`;
CREATE TABLE `gift_create_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) DEFAULT NULL COMMENT '为了插入后得到id才加的这个字段',
  `gift_id` varchar(64) NOT NULL COMMENT '礼包名称',
  `gift_name` varchar(64) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型 0 不可重复领取礼包 1 可重复领取礼包 ',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `prefix` varchar(32) DEFAULT NULL COMMENT '前缀',
  `code_length` int(11) DEFAULT '0' COMMENT '码长度',
  `pid` varchar(64) DEFAULT NULL COMMENT '平台id',
  `remarks` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_name` varchar(64) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `effective_time` datetime DEFAULT NULL COMMENT '生效时间',
  `effective_end_time` datetime DEFAULT NULL COMMENT '失效时间 不填表示永久',
  `update_time` datetime DEFAULT NULL,
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼包生成表';

-- ----------------------------
-- Records of gift_create_code
-- ----------------------------

-- ----------------------------
-- Table structure for `gm_account`
-- ----------------------------
DROP TABLE IF EXISTS `gm_account`;
CREATE TABLE `gm_account` (
  `id` varchar(64) NOT NULL,
  `platform_id` varchar(64) DEFAULT NULL,
  `server_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `role_type` int(11) DEFAULT NULL COMMENT '0普通玩家 1指导员 2GM',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_name` varchar(100) DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指导员和GM列表';

-- ----------------------------
-- Records of gm_account
-- ----------------------------

-- ----------------------------
-- Table structure for `maintain_task_definition`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_task_definition`;
CREATE TABLE `maintain_task_definition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `cron` varchar(200) DEFAULT NULL,
  `bean_class` varchar(200) DEFAULT NULL,
  `bean_name` varchar(200) DEFAULT NULL,
  `method_name` varchar(200) DEFAULT NULL,
  `is_start` tinyint(1) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_task_definition
-- ----------------------------

-- ----------------------------
-- Table structure for `monitor_config`
-- ----------------------------
DROP TABLE IF EXISTS `monitor_config`;
CREATE TABLE `monitor_config` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `warn_login` int(11) DEFAULT NULL COMMENT '登入预警天数',
  `warn_charge` int(11) DEFAULT NULL COMMENT '支付预警天数',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_name` varchar(64) DEFAULT NULL COMMENT '更新操作人员名字',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新操作人员ID',
  `create_time` datetime DEFAULT NULL,
  `create_name` varchar(64) DEFAULT NULL COMMENT '创建人员名字',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor_config
-- ----------------------------
INSERT INTO monitor_config VALUES ('5a8276c9b32e410480057ec8b7397061', '3', '3', '2016-07-15 12:06:58', null, null, '2016-07-15 12:06:58', 'mars', '1');

-- ----------------------------
-- Table structure for `month_revenue_estimate`
-- ----------------------------
DROP TABLE IF EXISTS `month_revenue_estimate`;
CREATE TABLE `month_revenue_estimate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `month` varchar(255) DEFAULT NULL COMMENT '月份(列如：201411)',
  `newUser` int(11) DEFAULT NULL COMMENT '新注册用户数',
  `oldUser` int(11) DEFAULT NULL COMMENT '老用户数',
  `month_active` int(11) DEFAULT NULL COMMENT '月活跃数',
  `payNum` int(11) DEFAULT NULL COMMENT '充值人数',
  `payingrate` double(15,3) DEFAULT NULL COMMENT '付费率',
  `arpu` double(15,3) DEFAULT NULL COMMENT 'ARPU',
  `income` int(11) DEFAULT NULL COMMENT '收入',
  `log_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of month_revenue_estimate
-- ----------------------------

-- ----------------------------
-- Table structure for `recharge`
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `id` varchar(64) NOT NULL,
  `user_ids` varchar(64) DEFAULT NULL COMMENT '角色账号',
  `role_ids` text COMMENT '角色ID',
  `role_names` text COMMENT '角色名',
  `money_type` char(1) DEFAULT NULL COMMENT '货币类型',
  `money_num` int(11) DEFAULT NULL COMMENT '充值数量',
  `server_id` varchar(200) DEFAULT NULL COMMENT '服务器ID',
  `recharge_type` char(1) DEFAULT NULL COMMENT '充值类型(正常玩家充值/给托充值)',
  `recharge_status` char(1) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `approve_by` varchar(64) DEFAULT NULL,
  `approve_name` varchar(64) DEFAULT NULL COMMENT '审核人',
  `remark` text COMMENT '备注',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recharge
-- ----------------------------

-- ----------------------------
-- Table structure for `role_game_platform`
-- ----------------------------
DROP TABLE IF EXISTS `role_game_platform`;
CREATE TABLE `role_game_platform` (
  `role_id` varchar(64) NOT NULL DEFAULT '',
  `game_platform_id` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`game_platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_game_platform
-- ----------------------------

-- ----------------------------
-- Table structure for `role_silence_freeze_log`
-- ----------------------------
DROP TABLE IF EXISTS `role_silence_freeze_log`;
CREATE TABLE `role_silence_freeze_log` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '主键',
  `user_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `isjinyan` bit(1) DEFAULT NULL COMMENT '是否禁言',
  `isfenghao` bit(1) DEFAULT NULL COMMENT '是否封号',
  `server_id` varchar(30) DEFAULT NULL COMMENT '服务器id',
  `operation_type` char(1) DEFAULT NULL COMMENT '操作类型',
  `msg` text COMMENT '原因',
  `expire_time` datetime DEFAULT NULL COMMENT '失效时间(禁言封号)',
  `create_name` varchar(100) DEFAULT NULL COMMENT '操作人名称',
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_silence_freeze_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_area`
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_area_parent_ids` (`parent_ids`(255)) USING BTREE,
  KEY `sys_area_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO sys_area VALUES ('0896d017434f4744bc1e6cbb4774fff0', '3c78fa9133dd45d0a32ffee22c084370', '0,1,3c78fa9133dd45d0a32ffee22c084370,', '210101', '闸北区', '4', '2', '2014-10-20 10:37:35', '2', '2014-10-20 10:37:50', '', '0');
INSERT INTO sys_area VALUES ('1', '0', '0,', '100000', '中国', '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('10', '8', '0,1,2,', '370532', '历城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('11', '8', '0,1,2,', '370533', '历城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('12', '8', '0,1,2,', '370534', '历下区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('13', '8', '0,1,2,', '370535', '天桥区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('14', '8', '0,1,2,', '370536', '槐荫区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('2', '1', '0,1,', '110000', '北京市', '2', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('3', '2', '0,1,2,', '110101', '东城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('3c78fa9133dd45d0a32ffee22c084370', '1', '0,1,', '210000', '上海', '2', '2', '2014-10-20 10:35:05', '2', '2014-10-20 10:37:02', '', '0');
INSERT INTO sys_area VALUES ('4', '2', '0,1,2,', '110102', '西城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('5', '2', '0,1,2,', '110103', '朝阳区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('6', '2', '0,1,2,', '110104', '丰台区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('7', '2', '0,1,2,', '110105', '海淀区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('8', '1', '0,1,', '370000', '山东省', '2', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('9', '8', '0,1,2,', '370531', '济南市', '3', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_area VALUES ('d21855870feb47849b63717b3f4085a1', '3c78fa9133dd45d0a32ffee22c084370', '0,1,3c78fa9133dd45d0a32ffee22c084370,', '210102', '浦东区', '4', '2', '2014-10-20 10:38:09', '2', '2014-10-20 10:38:14', '', '0');

-- ----------------------------
-- Table structure for `sys_daoliang`
-- ----------------------------
DROP TABLE IF EXISTS `sys_daoliang`;
CREATE TABLE `sys_daoliang` (
  `id` varchar(64) NOT NULL,
  `cpa` int(11) NOT NULL,
  `pid` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_daoliang
-- ----------------------------
INSERT INTO sys_daoliang VALUES ('91e024b29c8f4d6c802aa0518f36992d', '3', '1');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `international_key` varchar(255) DEFAULT NULL,
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO sys_dict VALUES ('02e7ae514ca84aadba45b0cd4be26a41', '已发送', 'dict1', '1', 'email_status', '系统邮件状态', '20', '104bc53879da4752b175acd55f10c1d3', '2014-11-07 09:32:00', '104bc53879da4752b175acd55f10c1d3', '2014-11-07 09:32:00', '', '0');
INSERT INTO sys_dict VALUES ('05fe3be2063b4a16b2950e651ff342b6', '聚划算购买礼包-寻宝', 'dict2', '3109', 'juhuasuan_type', '聚划算礼包', '80', '1', '2015-08-27 12:53:00', '1', '2015-08-27 12:53:00', '', '0');
INSERT INTO sys_dict VALUES ('086dd1eb846640a88a8bbd7c9d4eb2a3', '托尔', 'dict3', 'hs004', 'huashen_type', '化神', '10', '1', '2015-07-30 16:14:00', '1', '2015-07-30 16:20:00', '', '0');
INSERT INTO sys_dict VALUES ('09aa06a35ebf40198cde97ea8f05808e', '游戏建议', 'dict4', '0', 'feedback_type', '反馈类型', '10', '1', '2014-11-20 14:56:00', '1', '2014-11-20 14:56:00', '', '0');
INSERT INTO sys_dict VALUES ('0a0654b4ce904a048c47190fe84b4203', '已取消', 'dict5', '2', 'recharge_status', '充值状态', '30', '1', '2014-11-13 16:05:00', '1', '2014-11-13 16:05:00', '', '0');
INSERT INTO sys_dict VALUES ('0d7fa9d9170a4dff937895109494a19e', '聚划算购买礼包-强化石', 'dict6', '3107', 'juhuasuan_type', '聚划算礼包', '60', '1', '2015-08-27 12:52:00', '1', '2015-08-27 12:52:00', '', '0');
INSERT INTO sys_dict VALUES ('0d82e500ee084dcca2408d81320f5a37', '进行中', 'dict7', '2', 'notice_status', '公告状态', '30', '1', '2014-10-30 15:47:00', '1', '2014-10-30 15:47:00', '', '1');
INSERT INTO sys_dict VALUES ('1', '正常', 'dict8', '0', 'del_flag', '删除标记', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('10', '黄色', 'dict9', 'yellow', 'color', '颜色值', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('108f0c04c61d40a781a15b990cf4ac14', '维护', 'dict10', '1', 'server_status', '服务器状态', '11', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-21 10:29:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-21 10:29:00', '', '0');
INSERT INTO sys_dict VALUES ('11', '橙色', 'dict11', 'orange', 'color', '颜色值', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('12', '默认主题', 'dict12', 'default', 'theme', '主题方案', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('13', '天蓝主题', 'dict13', 'cerulean', 'theme', '主题方案', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('14', '橙色主题', 'dict14', 'readable', 'theme', '主题方案', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('15', '红色主题', 'dict15', 'united', 'theme', '主题方案', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('15b3b77588b84993b85b0a4c52530bf8', '已取消', 'dict16', '4', 'notice_status', '公告状态', '50', '1', '2014-10-30 15:49:00', '1', '2014-10-30 15:49:00', '', '0');
INSERT INTO sys_dict VALUES ('16', 'Flat主题', 'dict17', 'flat', 'theme', '主题方案', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('16d4e11583104006b364d0d97ae3f237', '帝释天', 'dict18', 'hs005', 'huashen_type', '化神', '20', '1', '2015-07-30 16:14:00', '1', '2015-07-30 16:21:00', '', '0');
INSERT INTO sys_dict VALUES ('16d8a27a70334c1d8c91c51deb89d1be', '狂战士', 'dict19', 'A', 'job_type', 'job_type', '10', '1', '2015-02-09 17:04:00', '1', '2015-02-09 17:04:00', '', '0');
INSERT INTO sys_dict VALUES ('17', '国家', 'dict20', '1', 'sys_area_type', '区域类型', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('170cbcbde6fa4ba5bf326094dce437f4', '露易丝', 'dict21', 'xiaolongnv', 'beauty_type', '舞姬', '10', '1', '2015-07-31 16:20:00', '1', '2015-07-31 16:20:00', '', '0');
INSERT INTO sys_dict VALUES ('18', '省份、直辖市', 'dict22', '2', 'sys_area_type', '区域类型', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('19', '地市', 'dict23', '3', 'sys_area_type', '区域类型', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('19839da7ecc740f9ac19a27a41e88515', '王钻至尊', 'dict24', '3', 'lv_type', '特权卡等级', '30', '1', '2015-08-31 16:33:00', '1', '2015-08-31 16:33:00', '', '0');
INSERT INTO sys_dict VALUES ('1b1c8d8fd3d640778ca74ac16cddc8c7', 'vip1用户', 'dict25', '1', 'log_type', '用户', '80', '1', '2015-08-03 13:29:00', '1', '2015-08-03 13:29:00', '', '0');
INSERT INTO sys_dict VALUES ('1f340213df1a4469bbfc6b2d8538f917', '粽情粽意', 'dict26', '62', 'title_type', '头衔编号', '130', '1', '2015-07-31 11:21:00', '1', '2015-07-31 11:21:00', '', '0');
INSERT INTO sys_dict VALUES ('1f340213df1a4469bbfc6b2f8538f917', '王城主宰', 'dict174', '66', 'title_type', '头衔编号', '150', '1', '2015-07-31 11:21:00', '1', '2015-07-31 11:21:00', '', '0');
INSERT INTO sys_dict VALUES ('2', '删除', 'dict27', '1', 'del_flag', '删除标记', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('20', '区县', 'dict28', '4', 'sys_area_type', '区域类型', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('21', '公司', 'dict29', '1', 'sys_office_type', '机构类型', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('22', '部门', 'dict30', '2', 'sys_office_type', '机构类型', '70', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('23', '一级', 'dict31', '1', 'sys_office_grade', '机构等级', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('2308dbcde0334803bb2d0439f7841ec0', '聚划算购买礼包-觉醒', 'dict32', '3104', 'juhuasuan_type', '聚划算礼包', '30', '1', '2015-08-27 12:51:00', '1', '2015-08-27 12:51:00', '', '0');
INSERT INTO sys_dict VALUES ('23c86cf467584759b6f0daa3af537efc', '审批中', 'dict33', '0', 'email_status', '系统邮件状态', '10', '104bc53879da4752b175acd55f10c1d3', '2014-11-07 09:31:00', '104bc53879da4752b175acd55f10c1d3', '2014-11-07 09:31:00', '', '0');
INSERT INTO sys_dict VALUES ('24', '二级', 'dict34', '2', 'sys_office_grade', '机构等级', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('24a27081853145e4b1660ccda45d7d08', '阿努比斯', 'dict35', 'hs002', 'huashen_type', '化神', '30', '1', '2015-07-30 16:13:00', '1', '2015-07-30 16:13:00', '', '0');
INSERT INTO sys_dict VALUES ('24e17a6b989a40f5a2d565199c2c30aa', '星际游侠', 'dict180', '80', 'title_type', '头衔编号', '220', '1', '2015-07-31 11:10:00', '1', '2015-11-25 13:57:43', '', '0');
INSERT INTO sys_dict VALUES ('24e17a6b989a40f5a2d565199c2c70aa', '鹊桥仙缘', 'dict179', '81', 'title_type', '头衔编号', '240', '1', '2015-07-31 11:10:00', '1', '2015-11-25 13:56:26', '', '0');
INSERT INTO sys_dict VALUES ('24e17a6b989a40f5a2d565199c2c70ba', '圣者再临', 'dict182', '79', 'title_type', '头衔编号', '210', '1', '2015-07-31 11:10:00', '1', '2015-11-25 13:57:32', '', '0');
INSERT INTO sys_dict VALUES ('25', '三级', 'dict36', '3', 'sys_office_grade', '机构等级', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('26', '四级', 'dict37', '4', 'sys_office_grade', '机构等级', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('27', '所有数据', 'dict38', '1', 'sys_data_scope', '数据范围', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('28', '所在公司及以下数据', 'dict39', '2', 'sys_data_scope', '数据范围', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('29', '所在公司数据', 'dict40', '3', 'sys_data_scope', '数据范围', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('298b16e73d834becbdbab16e9caf4c42', '米璐卡', 'dict41', 'nvwa', 'beauty_type', '舞姬', '40', '1', '2015-07-31 16:22:00', '1', '2015-07-31 16:22:00', '', '0');
INSERT INTO sys_dict VALUES ('2a484513553849b286199dbed9e2941c', '圣芒之羽', 'dict181', '78', 'title_type', '头衔编号', '200', '1', '2015-07-31 11:10:00', '1', '2015-11-25 13:56:52', '', '0');
INSERT INTO sys_dict VALUES ('2dd1d3b61a0b4cf08d31e277188bd7f4', '滚动公告', 'dict42', '2', 'notice_type', '公告类型', '20', '1', '2014-10-30 10:56:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-30 14:07:00', '', '1');
INSERT INTO sys_dict VALUES ('2e6b785c52ea4cc98df77d463a931952', 'vip10用户', 'dict43', '10', 'log_type', '用户', '170', '1', '2015-08-20 13:48:00', '1', '2015-08-20 13:48:00', '', '0');
INSERT INTO sys_dict VALUES ('3', '显示', 'dict44', '1', 'show_hide', '显示/隐藏', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('30', '所在部门及以下数据', 'dict45', '4', 'sys_data_scope', '数据范围', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('307f4c5a203e433a82b16f6f7cfd82e6', '怪物聊天', 'dict46', '6', 'chat_channel', '聊天频道', '70', '1', '2014-11-21 17:34:00', '1', '2014-11-21 17:34:00', '', '0');
INSERT INTO sys_dict VALUES ('31', '所在部门数据', 'dict47', '5', 'sys_data_scope', '数据范围', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('3185d84b995a4d0db72666df34b0e593', '妮可', 'dict48', 'baijingjing', 'beauty_type', '舞姬', '60', '1', '2015-07-31 16:23:00', '1', '2015-07-31 16:23:00', '', '0');
INSERT INTO sys_dict VALUES ('32', '仅本人数据', 'dict49', '8', 'sys_data_scope', '数据范围', '90', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('33', '按明细设置', 'dict50', '9', 'sys_data_scope', '数据范围', '100', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('34', '系统管理', 'dict51', '1', 'sys_user_type', '用户类型', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('35', '部门经理', 'dict52', '2', 'sys_user_type', '用户类型', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('36', '普通用户', 'dict53', '3', 'sys_user_type', '用户类型', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('38ed696b7a284c0fad87f9228f888096', '元宝', 'dict54', '2', 'money_type', '货币类型', '10', '1', '2014-11-13 16:11:00', '1', '2014-11-28 14:31:00', '', '0');
INSERT INTO sys_dict VALUES ('3eefd853a8e14accb61de5dab9aa03fc', '武器(左手)', 'dict55', '69', 'equip_type', '装备部位', '80', '1', '2015-08-03 15:14:00', '1', '2015-08-03 15:14:00', '', '0');
INSERT INTO sys_dict VALUES ('3fe7dbe0b4c84cdd87aa59338185c596', '杨戬', 'dict56', 'hs006', 'huashen_type', '化神', '40', '1', '2015-07-30 16:15:00', '1', '2015-07-30 16:21:00', '', '0');
INSERT INTO sys_dict VALUES ('4', '隐藏', 'dict57', '0', 'show_hide', '显示/隐藏', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('40861076a18c4a5785155b416bbb50a0', '绿钻贵族', 'dict58', '1', 'lv_type', '特权卡等级', '10', '1', '2015-08-31 16:32:00', '1', '2015-08-31 16:32:00', '', '0');
INSERT INTO sys_dict VALUES ('409f61cbe79a4bdead7a0f454ae04cc5', '护腕', 'dict59', '29', 'equip_type', '装备部位', '130', '1', '2015-08-03 16:01:00', '1', '2015-08-03 16:01:00', '', '0');
INSERT INTO sys_dict VALUES ('4526008a457e4de58fa4d685877177a4', '头盔', 'dict60', '55', 'equip_type', '装备部位', '10', '1', '2015-08-03 15:10:00', '1', '2015-08-03 15:10:00', '', '0');
INSERT INTO sys_dict VALUES ('4858f18cd600457f982f750f2d75fd08', '免费用户', 'dict61', 'log04', 'log_type', '用户', '40', '1', '2015-08-03 13:26:00', '1', '2015-08-03 13:26:00', '', '0');
INSERT INTO sys_dict VALUES ('49072d71733b4091ab5d29a48f38379a', '爱丽丝', 'dict62', 'jiufeng', 'beauty_type', '舞姬', '20', '1', '2015-07-31 16:21:00', '1', '2015-07-31 16:21:00', '', '0');
INSERT INTO sys_dict VALUES ('4c30dcc41dcb429ab8e737ce8c663933', 'vip2用户', 'dict63', '2', 'log_type', '用户', '90', '1', '2015-08-03 13:29:00', '1', '2015-08-03 13:29:00', '', '0');
INSERT INTO sys_dict VALUES ('4ed5e233e5404f719bc1e8f0c129245d', '停止', 'dict64', '2', 'server_status', '服务器状态', '12', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-21 10:29:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-21 10:29:00', '', '0');
INSERT INTO sys_dict VALUES ('5', '是', 'dict65', '1', 'yes_no', '是/否', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('51cfea5cab4c4038bbfa3da78ed44a2d', 'vip5用户', 'dict66', '5', 'log_type', '用户', '120', '1', '2015-08-20 13:45:00', '1', '2015-08-20 13:45:00', '', '0');
INSERT INTO sys_dict VALUES ('5369bfaa916e422e91f51b7d62508295', '平台', 'dict67', '2', 'pid_server', '平台及服务器', '20', '1', '2015-09-15 14:31:00', '1', '2015-09-15 14:31:00', '', '0');
INSERT INTO sys_dict VALUES ('538b3890be714865833a0796ed5e4145', '富贵逼人', 'dict68', '74', 'title_type', '头衔编号', '40', '1', '2015-07-31 11:08:00', '1', '2015-07-31 11:08:00', '', '0');
INSERT INTO sys_dict VALUES ('538b3890be714865833a0796ed5e4245', '竞技冠军', 'dict175', '82', 'title_type', '头衔编号', '160', '1', '2015-07-31 11:08:00', '1', '2015-07-31 11:08:00', '', '0');
INSERT INTO sys_dict VALUES ('566494379c3a44cda527361192c4e624', '流浪旅人', 'dict69', '1', 'destiny_type', '天命', '10', '1', '2015-08-31 13:35:00', '1', '2015-08-31 13:36:00', '', '0');
INSERT INTO sys_dict VALUES ('5730b43a5b4b4dafa478890743f6bd32', 'vip9用户', 'dict70', '9', 'log_type', '用户', '160', '1', '2015-08-20 13:47:00', '1', '2015-08-20 13:47:00', '', '0');
INSERT INTO sys_dict VALUES ('585aeb7c998746eda5630d11ed8df07c', '阿瑞斯', 'dict71', 'hs003', 'huashen_type', '化神', '50', '1', '2015-07-30 16:14:00', '1', '2015-07-30 16:20:00', '', '0');
INSERT INTO sys_dict VALUES ('58d1e23cbcd04f168897a6f63b0638ed', '聚划算奖励', 'dict72', '3102', 'juhuasuan_type', '聚划算礼包', '10', '1', '2015-08-27 12:50:00', '1', '2015-08-27 12:50:00', '', '0');
INSERT INTO sys_dict VALUES ('5a8bc0ba51ab4b028b509e7a184d769b', '去新用户', 'dict73', 'log03', 'log_type', '用户', '30', '1', '2015-08-03 13:26:00', '1', '2015-08-03 13:26:00', '', '0');
INSERT INTO sys_dict VALUES ('5b659721d9394a19b9293cfbcba4e50b', 'BUG反馈', 'dict74', '1', 'feedback_type', '反馈类型', '20', '1', '2014-11-20 14:57:00', '1', '2014-11-20 14:57:00', '', '0');
INSERT INTO sys_dict VALUES ('5da7975ed3bd477daf55bfa015424459', '聚划算购买礼包-全部特权', 'dict75', '3110', 'juhuasuan_type', '聚划算礼包', '90', '1', '2015-08-28 13:24:00', '1', '2015-08-28 13:24:00', '', '0');
INSERT INTO sys_dict VALUES ('5dc85ed9d8024808b7691200eac751c9', '内部人员充道具', 'dict76', '2', 'recharge_type', '充值类型', '40', '2', '2015-03-25 11:57:00', '2', '2015-03-25 11:57:00', '', '0');
INSERT INTO sys_dict VALUES ('5dc85ed9d8024808b7691200eac751f9', '玩家正常充值', 'dict77', '3', 'recharge_type', '充值类型', '42', '2', '2015-03-25 11:57:00', '2', '2015-03-25 11:57:00', '', '0');
INSERT INTO sys_dict VALUES ('5edf230fe17947aa89cbbfa33b085e9b', '衣服', 'dict78', '41', 'equip_type', '装备部位', '30', '1', '2015-08-03 15:12:00', '1', '2015-08-03 15:12:00', '', '0');
INSERT INTO sys_dict VALUES ('6', '否', 'dict79', '0', 'yes_no', '是/否', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('60', '调休', 'dict80', '4', 'oa_leave_type', '请假类型', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '1');
INSERT INTO sys_dict VALUES ('61', '婚假', 'dict81', '5', 'oa_leave_type', '请假类型', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '1');
INSERT INTO sys_dict VALUES ('61061e36dd88466188391daf77b34a1b', '奥丁', 'dict82', 'hs009', 'huashen_type', '化神', '60', '1', '2015-07-30 16:15:00', '1', '2015-07-30 16:15:00', '', '0');
INSERT INTO sys_dict VALUES ('6125d2472e104c108f9e41bac1126031', '待审批', 'dict83', '0', 'recharge_status', '充值状态', '10', '1', '2014-11-13 16:05:00', '1', '2014-11-13 16:05:00', '', '0');
INSERT INTO sys_dict VALUES ('62', '接入日志', 'dict84', '1', 'sys_log_type', '日志类型', '30', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('6217a5acbdca45d684ac56b1520867ac', '戒指_2', 'dict85', '121', 'equip_type', '装备部位', '160', '1', '2015-08-03 16:03:00', '1', '2015-08-03 16:03:00', '', '0');
INSERT INTO sys_dict VALUES ('63', '异常日志', 'dict86', '2', 'sys_log_type', '日志类型', '40', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('637045428edd458088e72503c38b9157', '已结束', 'dict87', '3', 'notice_status', '公告状态', '40', '1', '2014-10-30 15:48:00', '1', '2014-10-30 15:48:00', '', '1');
INSERT INTO sys_dict VALUES ('64', '单表增删改查', 'dict88', 'single', 'prj_template_type', '代码模板', '10', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('65', '所有entity和dao', 'dict89', 'entityAndDao', 'prj_template_type', '代码模板', '20', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('65fa3daa7b974a7393b2e4b8aa95b139', 'GM命令', 'dict90', '7', 'chat_channel', '聊天频道', '80', '1', '2014-11-21 17:35:00', '1', '2014-11-21 17:35:00', '', '0');
INSERT INTO sys_dict VALUES ('6630fb995f5547f5a99c0864426b6050', '聚划算购买礼包-宝石', 'dict91', '3108', 'juhuasuan_type', '聚划算礼包', '70', '1', '2015-08-27 12:53:00', '1', '2015-08-27 12:53:00', '', '0');
INSERT INTO sys_dict VALUES ('674bcb9f704448f5a0101abbc85bae81', '付费活跃用户', 'dict92', 'log07', 'log_type', '用户', '70', '1', '2015-08-03 13:28:00', '1', '2015-08-03 13:28:00', '', '0');
INSERT INTO sys_dict VALUES ('6bbcdb8c46ea48cebd131dbb05a601c5', 'vip12用户', 'dict93', '12', 'log_type', '用户', '190', '1', '2015-08-20 13:49:00', '1', '2015-08-20 13:52:00', '', '0');
INSERT INTO sys_dict VALUES ('6f480bcdb7d2411e9ccb42d9dcd9ecc2', '紫钻王者', 'dict94', '2', 'lv_type', '紫钻王者', '20', '1', '2015-08-31 16:32:00', '1', '2015-08-31 16:32:00', '', '0');
INSERT INTO sys_dict VALUES ('7', '红色', 'dict95', 'red', 'color', '颜色值', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('7249388233354b36a2741b040316c5c6', '饰品2', 'dict96', '122', 'equip_type', '装备部位', '170', '1', '2015-08-03 16:08:00', '1', '2015-08-03 16:08:00', '', '0');
INSERT INTO sys_dict VALUES ('73e3f7c3ed8640c496521cd5043d1924', 'vip7用户', 'dict97', '7', 'log_type', '用户', '140', '1', '2015-08-20 13:46:00', '1', '2015-08-20 13:46:00', '', '0');
INSERT INTO sys_dict VALUES ('75b3a14d67104e8a8b20b014b546fec2', '绑定元宝', 'dict98', '3', 'money_type', '货币类型', '20', '1', '2014-11-13 16:11:00', '1', '2014-11-28 14:31:00', '', '0');
INSERT INTO sys_dict VALUES ('7719af9783714f85aadc6639a251d352', '项链', 'dict99', '51', 'equip_type', '装备部位', '110', '1', '2015-08-03 16:00:00', '1', '2015-08-03 16:00:00', '', '0');
INSERT INTO sys_dict VALUES ('7bec36e8087a4f79bfa4f157d14d6e23', '十大杀神', 'dict100', '69', 'title_type', '头衔编号', '20', '1', '2015-07-31 11:07:00', '1', '2015-07-31 11:07:00', '', '0');
INSERT INTO sys_dict VALUES ('7bec36e8087a4f79bfa4f157d14d6r23', '璀璨之星', 'dict176', '75', 'title_type', '头衔编号', '170', '1', '2015-07-31 11:07:00', '1', '2015-07-31 11:07:00', '', '0');
INSERT INTO sys_dict VALUES ('7f0711c0828d4c49939cbd7f06492e00', '夺宝奇兵', 'dict101', '68', 'title_type', '头衔编号', '140', '1', '2015-07-31 11:21:00', '1', '2015-07-31 11:21:00', '', '0');
INSERT INTO sys_dict VALUES ('7f0711c0828d4c49939cbd7f06492t00', '教团新秀', 'dict177', '76', 'title_type', '头衔编号', '180', '1', '2015-07-31 11:21:00', '1', '2015-07-31 11:21:00', '', '0');
INSERT INTO sys_dict VALUES ('7f63fe99110040518c6d9df6234822c4', '付费用户', 'dict102', '0', 'log_type', '用户', '60', '1', '2015-08-03 13:27:00', '1', '2015-08-03 13:27:00', '', '0');
INSERT INTO sys_dict VALUES ('7f8aacce809a495cb982370c3c63db29', 'vip11用户', 'dict103', '11', 'log_type', '用户', '180', '1', '2015-08-20 13:50:00', '1', '2015-08-20 13:52:00', '', '0');
INSERT INTO sys_dict VALUES ('7fd1718546e545b39f67fab22e48c954', '鞋子', 'dict104', '25', 'equip_type', '装备部位', '150', '1', '2015-08-03 16:01:00', '1', '2015-08-03 16:01:00', '', '0');
INSERT INTO sys_dict VALUES ('8', '绿色', 'dict105', 'green', 'color', '颜色值', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('800b053da8cd4098aca7ec7fcd4e9927', '手套', 'dict106', '30', 'equip_type', '装备部位', '40', '1', '2015-08-03 15:12:00', '1', '2015-08-03 15:12:00', '', '0');
INSERT INTO sys_dict VALUES ('831b65c6f26446f386cfe1cf0848d879', '弓箭手', 'dict107', 'C', 'job_type', 'job_type', '30', '1', '2015-02-09 17:05:00', '1', '2015-02-09 17:05:00', '', '0');
INSERT INTO sys_dict VALUES ('840fb57cbc634806ad35cabd75195863', '聚划算购买礼包-浮游炮', 'dict108', '3112', 'juhuasuan_type', '聚划算礼包', '100', '1', '2015-08-27 12:54:00', '1', '2015-08-27 12:54:00', '', '0');
INSERT INTO sys_dict VALUES ('851ebaee03464615b7d10292ce293378', '公会聊天', 'dict109', '3', 'chat_channel', '聊天频道', '40', '1', '2014-11-21 17:33:00', '1', '2014-11-21 17:33:00', '', '0');
INSERT INTO sys_dict VALUES ('858a7e53debd4ea092f3163bcd83b672', '活跃用户', 'dict110', 'log01', 'log_type', '用户', '10', '1', '2015-08-03 13:25:00', '1', '2015-08-03 13:25:00', '', '0');
INSERT INTO sys_dict VALUES ('8728c27613c447059790529f0115585f', '服务器', 'dict111', '1', 'pid_server', '平台及服务器', '10', '1', '2015-09-15 14:32:00', '1', '2015-09-15 14:32:00', '', '0');
INSERT INTO sys_dict VALUES ('876ece6a4c7940eca2df5ea050852de7', '王者归来', 'dict112', '67', 'title_type', '头衔编号', '90', '1', '2015-07-31 11:15:00', '1', '2015-07-31 11:15:00', '', '0');
INSERT INTO sys_dict VALUES ('876ece6a4c7940eca2df5ea050852ye7', '灭世魔君', 'dict178', '77', 'title_type', '头衔编号', '190', '1', '2015-07-31 11:15:00', '1', '2015-07-31 11:15:00', '', '0');
INSERT INTO sys_dict VALUES ('878c9c68490b48788ca48b1367d94d2e', '世界频道', 'dict113', '4', 'chat_channel', '聊天频道', '50', '1', '2014-11-21 17:34:00', '1', '2014-11-21 17:34:00', '', '0');
INSERT INTO sys_dict VALUES ('8b7e6b0500ef414990561837531d13a5', '活动聊天', 'dict114', '8', 'chat_channel', '聊天频道', '90', '1', '2014-11-21 17:35:00', '1', '2014-11-21 17:35:00', '', '0');
INSERT INTO sys_dict VALUES ('8fdd30f48b7b4d7283d4f8e72483996b', 'vip3用户', 'dict115', '3', 'log_type', '用户', '100', '1', '2015-08-20 13:43:00', '1', '2015-08-20 13:43:00', '', '0');
INSERT INTO sys_dict VALUES ('9', '蓝色', 'dict116', 'blue', 'color', '颜色值', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO sys_dict VALUES ('9080dfc1004c458ebf8053f138cdd34a', '宙斯', 'dict117', 'hs016', 'huashen_type', '化神', '90', '1', '2015-07-30 16:16:00', '1', '2015-07-30 16:21:00', '', '0');
INSERT INTO sys_dict VALUES ('9208a3e14e854abb9867863876ca1c7c', '叫我土豪哥', 'dict118', '1', 'title_type', '头衔编号', '70', '1', '2015-07-31 11:10:00', '1', '2015-07-31 11:10:00', '', '0');
INSERT INTO sys_dict VALUES ('926bacaa93424c8ea2ff8f6cc7e5913c', '盾(右手)', 'dict119', '70', 'equip_type', '装备部位', '180', '1', '2015-08-03 16:08:00', '1', '2015-08-03 16:09:00', '', '0');
INSERT INTO sys_dict VALUES ('958044d63f614a6e9a197650b0312e20', '公会贡献值', 'dict120', '5', 'money_type', '货币类型', '40', '1', '2015-05-06 13:32:00', '1', '2015-05-06 13:32:00', '', '0');
INSERT INTO sys_dict VALUES ('97098dc5f7b94b49b24f4a0dd37e2273', '富可敌国', 'dict121', '73', 'title_type', '头衔编号', '50', '1', '2015-07-31 11:09:00', '1', '2015-07-31 11:09:00', '', '0');
INSERT INTO sys_dict VALUES ('978976afadf945b9b11f95d04a76b031', '世界公告', 'dict122', '0', 'notice_type', '公告类型', '10', '1', '2014-10-30 10:52:00', '1', '2014-10-30 10:52:00', '', '0');
INSERT INTO sys_dict VALUES ('9862894d04ad42b2824d53a562ea8c6c', '队伍聊天', 'dict123', '2', 'chat_channel', '聊天频道', '30', '1', '2014-11-21 17:33:00', '1', '2014-11-21 17:33:00', '', '0');
INSERT INTO sys_dict VALUES ('9c39c8d5a1214a9b8a20e744cece8388', '公会团队聊天', 'dict124', '9', 'chat_channel', '聊天频道', '100', '1', '2014-11-21 17:35:00', '1', '2014-11-21 17:35:00', '', '0');
INSERT INTO sys_dict VALUES ('9d5a671c18fd45bc81e49673a106f268', 'GM', 'dict125', '1', 'role_type', '游戏角色类型', '20', '1', '2014-11-19 13:13:00', '1', '2014-11-21 09:50:00', '', '0');
INSERT INTO sys_dict VALUES ('9dc635bd84634214928c2cd9be3c0fdd', 'vip4用户', 'dict126', '4', 'log_type', '用户', '110', '1', '2015-08-20 13:44:00', '1', '2015-08-20 13:44:00', '', '0');
INSERT INTO sys_dict VALUES ('a0c7164e8fbe4a45a98cbcd5d178e4d3', '私聊', 'dict127', '1', 'chat_channel', '聊天频道', '20', '1', '2014-11-21 17:01:00', '1', '2014-11-21 17:33:00', '', '0');
INSERT INTO sys_dict VALUES ('a163f4a973974785b06c7bb89573660d', '披风', 'dict128', '73', 'equip_type', '装备部位', '120', '1', '2015-08-03 16:00:00', '1', '2015-08-03 16:00:00', '', '0');
INSERT INTO sys_dict VALUES ('a82d4a0441ff443790d2534e7be96180', '正常', 'dict129', '0', 'server_status', '服务器状态', '10', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-21 10:29:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-21 10:29:00', '', '0');
INSERT INTO sys_dict VALUES ('a89dbbd08a9b47bcac6a44479f91c789', '系统公告', 'dict130', '1', 'notice_type', '公告类型', '20', '1', '2014-10-30 10:56:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-30 14:11:00', '', '0');
INSERT INTO sys_dict VALUES ('ad214b5bd5dc4b8eb37f3d1081ca3c2d', '卡特琳娜', 'dict131', 'change', 'beauty_type', '舞姬', '50', '1', '2015-07-31 16:22:00', '1', '2015-07-31 16:22:00', '', '0');
INSERT INTO sys_dict VALUES ('b69ab051d54844b0b5a265d63a050228', '孙悟空', 'dict132', 'hs000', 'huashen_type', '化神', '70', '1', '2015-07-30 16:11:00', '1', '2015-07-30 16:11:00', '', '0');
INSERT INTO sys_dict VALUES ('b69b31ff81fe4e7e901f963c953e7374', '聚划算购买礼包-翅膀', 'dict133', '3105', 'juhuasuan_type', '聚划算礼包', '40', '1', '2015-08-27 12:52:00', '1', '2015-08-27 12:52:00', '', '0');
INSERT INTO sys_dict VALUES ('b82f57e5164e45479fb94f07e62066e0', '免费活跃用户', 'dict134', 'log05', 'log_type', '用户', '50', '1', '2015-08-03 13:27:00', '1', '2015-08-03 13:27:00', '', '0');
INSERT INTO sys_dict VALUES ('bb2d531285cf45f588f355faf56a0c5d', '金币', 'dict135', '1', 'money_type', '货币类型', '30', '1', '2014-11-13 16:11:00', '1', '2014-11-28 14:31:00', '', '0');
INSERT INTO sys_dict VALUES ('bd0ead379e4d42a4a8b142e780749724', '国家玩法', 'dict136', '1', 'crossType', '跨服战斗模式', '11', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-22 16:59:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-22 17:12:00', '', '0');
INSERT INTO sys_dict VALUES ('bd851346e9184c0ba8c787011a1b77e3', '小伙伴们都惊呆了', 'dict137', '21', 'title_type', '头衔编号', '60', '1', '2015-07-31 11:10:00', '1', '2015-07-31 11:10:00', '', '0');
INSERT INTO sys_dict VALUES ('be3c9b8853394b8a832ff8b60ec9eeed', '客服号', 'dict138', '3', 'role_type', '游戏角色类型', '40', '1', '2015-03-03 09:59:00', '1', '2015-03-03 09:59:00', '', '0');
INSERT INTO sys_dict VALUES ('bfd41c62a89a41549e58a1dd4ab91693', 'vip6用户', 'dict139', '6', 'log_type', '用户', '130', '1', '2015-08-20 13:46:00', '1', '2015-08-20 13:46:00', '', '0');
INSERT INTO sys_dict VALUES ('c0ed09a15f334620883633ada18276f4', '喇叭', 'dict140', '5', 'chat_channel', '聊天频道', '60', '1', '2014-11-21 17:34:00', '1', '2014-11-21 17:34:00', '', '0');
INSERT INTO sys_dict VALUES ('c2e91a87565b4d1ea8a213962a30fd82', '徽章', 'dict141', '23', 'equip_type', '装备部位', '200', '1', '2015-08-03 16:10:00', '1', '2015-08-03 16:10:00', '', '0');
INSERT INTO sys_dict VALUES ('c3f6487190f941b19eaff95810e31ee6', '新用户', 'dict142', 'log02', 'log_type', '用户', '20', '1', '2015-08-03 13:25:00', '1', '2015-08-03 13:25:00', '', '0');
INSERT INTO sys_dict VALUES ('c560c18cc7944d41a5e0e01cb9cb2656', '场景内聊天', 'dict143', '0', 'chat_channel', '聊天频道', '10', '1', '2014-11-21 17:01:00', '1', '2014-11-21 17:33:00', '', '0');
INSERT INTO sys_dict VALUES ('c593fc6ed0a14aa69249cda6cdb5f195', '鞍具', 'dict144', '101', 'equip_type', '装备部位', '90', '1', '2015-08-03 15:14:00', '1', '2015-08-03 15:14:00', '', '0');
INSERT INTO sys_dict VALUES ('c63c11604ed74d32ae24e98702d59b2f', '已审批', 'dict145', '1', 'recharge_status', '充值状态', '20', '1', '2014-11-13 16:05:00', '1', '2014-11-13 16:05:00', '', '0');
INSERT INTO sys_dict VALUES ('c6d49403e4944ce6bf52c44611663286', '尚未发布', 'dict146', '0', 'notice_status', '公告状态', '10', '1', '2014-10-30 15:47:00', '1', '2014-10-30 15:47:00', '', '0');
INSERT INTO sys_dict VALUES ('c7dda63c9b444533a8c240d2210c2ba9', '普通玩家', 'dict147', '0', 'role_type', '游戏角色类型', '30', '1', '2015-02-03 10:16:00', '1', '2015-02-03 10:16:00', '', '0');
INSERT INTO sys_dict VALUES ('c90ba6c53f9f443d83ff44113dfc7d35', '匹配服务器', 'dict148', '0', 'crossType', '跨服战斗模式', '10', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-22 16:59:00', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-22 17:12:00', '', '0');
INSERT INTO sys_dict VALUES ('cc944eb6ca744466ab156500ccb1dfa5', '指导员', 'dict149', '2', 'role_type', '游戏角色类型', '10', '1', '2014-11-19 13:13:00', '1', '2014-11-21 09:50:00', '', '0');
INSERT INTO sys_dict VALUES ('cd7b308841734a13ab13f6184c33e424', '戒指_1', 'dict150', '21', 'equip_type', '装备部位', '60', '1', '2015-08-03 15:13:00', '1', '2015-08-03 15:13:00', '', '0');
INSERT INTO sys_dict VALUES ('cdd62bb526354afea9049a0fbc2124da', '聚划算购买礼包-坐骑', 'dict151', '3103', 'juhuasuan_type', '聚划算礼包', '20', '1', '2015-08-27 12:51:00', '1', '2015-08-27 12:51:00', '', '0');
INSERT INTO sys_dict VALUES ('d30fee33638d4452b9431498198f2b59', '平台充值', 'dict152', '1', 'recharge_type', '充值类型', '20', '1', '2014-11-13 16:08:00', '1', '2014-11-13 16:08:00', '', '0');
INSERT INTO sys_dict VALUES ('d664d1ea6d524f0db5ce4532d73b98bb', '无人可挡', 'dict153', '71', 'title_type', '头衔编号', '30', '1', '2015-07-31 11:07:00', '1', '2015-07-31 11:07:00', '', '0');
INSERT INTO sys_dict VALUES ('db352cf6ab7747f2a6fbbb6beb900620', '大陆守护者', 'dict154', '65', 'title_type', '头衔编号', '100', '1', '2015-07-31 11:19:00', '1', '2015-07-31 11:19:00', '', '0');
INSERT INTO sys_dict VALUES ('dec3ec0750474c98b50270aff7107863', '蹬具', 'dict155', '103', 'equip_type', '装备部位', '100', '1', '2015-08-03 15:15:00', '1', '2015-08-03 15:15:00', '', '0');
INSERT INTO sys_dict VALUES ('df13cf28d18b42a68419520785c1c456', 'vip8用户', 'dict156', '8', 'log_type', '用户', '150', '1', '2015-08-20 13:48:00', '1', '2015-08-20 13:49:00', '', '0');
INSERT INTO sys_dict VALUES ('e04f0bec1c214fecac82b27e85d5c4a2', '雅典娜', 'dict157', 'hs001', 'huashen_type', '化神', '80', '1', '2015-07-30 16:11:00', '1', '2015-07-30 16:12:00', '', '0');
INSERT INTO sys_dict VALUES ('e36cddbaae8544738cc81edefb58037a', '聚划算购买礼包-axp', 'dict158', '3106', 'juhuasuan_type', '聚划算礼包', '50', '1', '2015-08-27 12:52:00', '1', '2015-08-27 12:52:00', '', '0');
INSERT INTO sys_dict VALUES ('e4d527eca7804af89d8464ae6e7dae70', '逍遥天下行', 'dict159', '63', 'title_type', '头衔编号', '80', '1', '2015-07-31 11:11:00', '1', '2015-07-31 11:11:00', '', '0');
INSERT INTO sys_dict VALUES ('e64e8f4d8c584a7e9658cb22ca597067', '天下第一', 'dict160', '44', 'title_type', '头衔编号', '10', '1', '2015-07-31 11:07:00', '1', '2015-07-31 11:07:00', '', '0');
INSERT INTO sys_dict VALUES ('e7898ad3bb7448d7a023f55d2c77b061', '魔法师', 'dict161', 'D', 'job_type', 'job_type', '20', '1', '2015-02-09 17:05:00', '1', '2015-02-09 17:05:00', '', '0');
INSERT INTO sys_dict VALUES ('e7898ad3bb7448d7a023f55d2c99b060', '盾战士', 'dict162', 'B', 'job_type', 'job_type', '20', '1', '2015-02-09 17:05:00', '1', '2015-02-09 17:05:00', '', '0');
INSERT INTO sys_dict VALUES ('eadf0062f6bc467ba8fabf2dce96acbe', '其他', 'dict163', '3', 'feedback_type', '反馈类型', '40', '1', '2014-11-20 14:57:00', '1', '2014-11-20 14:57:00', '', '0');
INSERT INTO sys_dict VALUES ('eb51004b075d4cde99641dd97e4b9173', '已发布', 'dict164', '1', 'notice_status', '公告状态', '20', '1', '2014-10-30 15:47:00', '1', '2014-10-30 15:47:00', '', '0');
INSERT INTO sys_dict VALUES ('eba776da56984aa7aae62d45610f1c68', '腿部', 'dict165', '43', 'equip_type', '装备部位', '50', '1', '2015-08-03 15:12:00', '1', '2015-08-03 15:12:00', '', '0');
INSERT INTO sys_dict VALUES ('ee5a6ca13ef44fbeabc04c6a60cd4c9f', '投诉', 'dict166', '2', 'feedback_type', '反馈类型', '30', '1', '2014-11-20 14:57:00', '1', '2014-11-20 14:57:00', '', '0');
INSERT INTO sys_dict VALUES ('f0e55c9f92954fdea6289912aae2c4b0', '希利亚', 'dict167', 'qingxia', 'beauty_type', '舞姬', '30', '1', '2015-07-31 16:21:00', '1', '2015-07-31 16:21:00', '', '0');
INSERT INTO sys_dict VALUES ('f0efa07b8abc4af58f2d0a9fc68cb2f4', '腰部', 'dict168', '42', 'equip_type', '装备部位', '140', '1', '2015-08-03 16:01:00', '1', '2015-08-03 16:01:00', '', '0');
INSERT INTO sys_dict VALUES ('f5833c15e93949199b7352ff0baf5a68', '最强公会', 'dict169', '64', 'title_type', '头衔编号', '110', '1', '2015-07-31 11:20:00', '1', '2015-07-31 11:20:00', '', '0');
INSERT INTO sys_dict VALUES ('fbe92e9c70614da1b39ad6094600fb26', '肩铠', 'dict170', '45', 'equip_type', '装备部位', '20', '1', '2015-08-03 15:12:00', '1', '2015-08-03 15:12:00', '', '0');
INSERT INTO sys_dict VALUES ('fcfdc68d227f4a7c8b4b6bc2064cb089', '已取消', 'dict171', '2', 'email_status', '系统邮件状态', '30', '104bc53879da4752b175acd55f10c1d3', '2014-11-07 09:33:00', '104bc53879da4752b175acd55f10c1d3', '2014-11-07 09:33:00', '', '0');
INSERT INTO sys_dict VALUES ('fefba8b2ccbd4adfac05dddf9091ec13', '饰品1', 'dict172', '22', 'equip_type', '装备部位', '70', '1', '2015-08-03 15:14:00', '1', '2015-08-03 15:14:00', '', '0');
INSERT INTO sys_dict VALUES ('ff93d3f72301465a842395c1012bba09', '艾希', 'dict173', 'pipajing', 'beauty_type', '舞姬', '70', '1', '2015-07-31 16:23:00', '1', '2015-07-31 16:23:00', '', '0');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO sys_log VALUES ('24cdfeae3ac640fbb15596994d1a39fb', '1', '1', '2016-08-02 20:14:45', '192.168.1.192', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36', '/admin-tools/a/tools/gamePlatform/save', 'POST', 'id=&pid=gj&oldName=&name=桂杰&signKey=哈哈哈哈哈哈&description=和哈哈哈哈哈哈哈哈哈', '');
INSERT INTO sys_log VALUES ('7303b81143b8482f8f127935d8aceb4b', '1', '1', '2016-08-02 20:19:17', '192.168.1.192', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36', '/admin-tools/a/tools/gamePlatform/delete', 'GET', 'id=25049562a39045439a41922cb91fee58', '');

-- ----------------------------
-- Table structure for `sys_mdict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_mdict`;
CREATE TABLE `sys_mdict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_mdict_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_mdict_parent_ids` (`parent_ids`(255)) USING BTREE,
  KEY `sys_mdict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多级字典表';

-- ----------------------------
-- Records of sys_mdict
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `international_key` varchar(100) NOT NULL,
  `href` varchar(255) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_menu_parent_ids` (`parent_ids`(255)) USING BTREE,
  KEY `sys_menu_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO sys_menu VALUES ('014850bc2c8b4aac9e3d56f4f37a997e', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '用户升级时长', 'user.upgrade.time', '/log/roleUpgrade/list', '', 'search', '50', '1', '', '1', '2015-02-03 10:26:04', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('015d470cfead465d9e7bf342e2912a6b', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '绑元产出统计', 'output.goldIngot', '/log/goldIngot/outputGoldIngotList', '', 'search', '200', '1', '', '1', '2015-09-24 17:01:43', '1', '2015-09-24 17:01:43', null, '0');
INSERT INTO sys_menu VALUES ('0163dcae97b74488bea96db2abcf999c', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '月消费数据', 'monthly.consume.data', '/global/rechargeConsume/monthConsumeReport', '', 'th-large', '90', '1', '', '1', '2014-12-16 15:04:07', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('028dc850aa8a41898bd0ad425a305487', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '日充值统计', 'daily.recharge.stat', '/global/rechargeConsume/dailyRechargeReport', '', 'th-large', '30', '1', '', '1', '2014-12-15 15:28:05', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('04f7662e80ad488a8bfb8bcb405902cd', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '日综合报表', 'daily.comprehensive.report', '/global/dashboard/dailyComprehensiveReport', '', 'th-large', '40', '1', '', '1', '2014-11-27 16:56:13', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('08c12c3737bd48a5a1841d39ec45360d', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '月数据报表', 'monthly.data.report', '/global/dashboard/monthlyReport', '', 'th-large', '50', '1', '', '1', '2014-12-10 14:46:57', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('0913c827a55a4154a2b4ffdea7d04154', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '发送', 'game.email.send', '', '', '', '30', '1', 'game.email.send', '1', '2015-01-28 14:38:34', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('09e1fea387774662b8733c2f3f4ac141', 'a62395bcfb434c169c1784c9d18862bf', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,a62395bcfb434c169c1784c9d18862bf,', '国家管理', 'country.list', '/tools/crossArea/countryList', '', 'search', '40', '1', '', '1', '2015-04-28 15:57:52', '1', '2015-04-28 15:58:21', null, '0');
INSERT INTO sys_menu VALUES ('0a33502d8f4742eaa041a77bbea6bf43', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '批量补偿用户', 'game.email.name', '', '', '', '30', '1', 'game.email.name', '1', '2016-01-05 14:21:53', '1', '2016-01-05 14:23:25', null, '0');
INSERT INTO sys_menu VALUES ('0b89b750744b49afada2b9a2c6d16223', 'c088d87e488d4ee2bbb1b0693f486a7f', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c088d87e488d4ee2bbb1b0693f486a7f,', '充值申请', 'game.recharge.apply', '', '', '', '30', '1', 'game.recharge.apply', '1', '2015-01-28 15:03:28', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('0b8f61959129420bac915bb68c9f56cb', '1', '0,1,', '客服工具', 'customer.tools', '', '', '', '800', '1', '', '1', '2014-10-29 16:05:56', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('0c274076368a4b92ac2a10c460ddb6c5', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '极冰寒牢', 'ice.prison', '/log/ice/icePrisonList', '', 'search', '100', '1', '', '1', '2015-11-13 11:43:16', '1', '2015-11-13 11:43:51', null, '0');
INSERT INTO sys_menu VALUES ('0dd214c6387b4ac3ba6e07241cef59b1', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '聊天日志', 'game.chat.log', '/game/chat', '', 'th-large', '90', '1', '', '1', '2014-11-21 16:35:47', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('0dff5efa1881460381a59ab4f3da71d3', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台历史月度数据', 'platform.his.month.data', '/global/platformHistoryMonthData/platformHistoryMonthData', '', 'th-large', '40', '1', '', '1', '2014-12-26 10:51:35', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('0f400d499a9d472b8cef43c01671d7e0', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '回流用户统计', 'stat.use.back', '/log/userStat/refluxUser', '', 'search', '130', '1', '', '1', '2015-03-06 14:12:52', '1', '2015-03-06 14:12:52', null, '0');
INSERT INTO sys_menu VALUES ('0f86bac9147a404e958fd8b807b21aa0', '3', '0,1,2,3,', '合服信息列表', 'followerId.config', '/tools/gameArea/followerId', '', 'search', '100', '1', '', '1', '2016-03-16 17:02:08', '1', '2016-03-16 17:02:56', null, '0');
INSERT INTO sys_menu VALUES ('1', '0', '0,', '顶级菜单', 'top.menu', null, null, null, '0', '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_menu VALUES ('10', '3', '0,1,2,3,', '字典管理', 'dict.manager', '/sys/dict/', null, 'th-list', '60', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('11', '10', '0,1,2,3,10,', '查看', 'operation.view', null, null, null, '30', '0', 'sys:dict:view', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('12', '10', '0,1,2,3,10,', '修改', 'operation.modify', null, null, null, '30', '0', 'sys:dict:edit', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('12001394168843af85ec51275d2f856b', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '日活跃', 'daily.active', '/global/dashboard/dayActiveUserReport', '', 'th-large', '60', '1', '', '1', '2014-12-12 13:59:25', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('13', '2', '0,1,2,', '用户管理', 'group.user', '', '', '', '970', '1', '', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('14', '13', '0,1,2,13,', '区域管理', 'area.manager', '/sys/area/', '', 'th', '50', '0', '', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('15', '14', '0,1,2,13,14,', '查看', 'operation.view', null, null, null, '30', '0', 'sys:area:view', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('16', '14', '0,1,2,13,14,', '修改', 'operation.modify', null, null, null, '30', '0', 'sys:area:edit', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('16ae52e8d5b24e21944ee30081b65295', 'be11fae8a7514053936c3e5ca6b18989', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,be11fae8a7514053936c3e5ca6b18989,', '公告取消', 'game.notice.delete', '', '', '', '30', '1', 'game.notice.delete', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-01-27 17:27:29', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('17', '13', '0,1,2,13,', '机构管理', 'office.manager', '/sys/office/', '', 'th-large', '40', '0', '', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('18', '17', '0,1,2,13,17,', '查看', 'operation.view', null, null, null, '30', '0', 'sys:office:view', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('185d80b50d2b47d0998ca0842148a58d', '3', '0,1,2,3,', '游戏服务器', 'game.server', '/tools/gameArea/gameAreas', '', 'search', '90', '1', '', '1', '2015-01-15 15:14:31', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('19', '17', '0,1,2,13,17,', '修改', 'operation.modify', null, null, null, '30', '0', 'sys:office:edit', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('1934184d81364e22a4fb8dbda461a11b', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台在线统计', 'platform.online.stat', '/global/platformStatistics/platformOnlineStatistics', '', 'th-large', '60', '1', '', '1', '2014-12-26 10:54:40', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('1cf3baed960c40a4af9ea6ec251577c3', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '单服日数据报表', 'daily.serverDaily.report', '/global/dashboard/serverDailyReport', '', 'th-large', '70', '1', '', 'bebede4ccd604db8a0375fa2b880a8a0', '2014-12-10 11:26:35', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('1f18c145bb1a46a0b0dbf4a6e6d65e0d', '9b5f5c9874964798b912033bb54bd31e', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,9b5f5c9874964798b912033bb54bd31e,', '查看', 'log.level.view', '', '', '', '30', '1', 'log.level.view', '1', '2015-01-29 11:27:35', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('1f3a029f66a548ebad7ccc0912a6f1c9', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '浮游炮统计', 'funnel.statistics', '/log/funnel/funnelStatisticsList', '', 'search', '220', '1', '', '1', '2015-11-11 14:45:21', '1', '2015-11-11 14:49:10', null, '0');
INSERT INTO sys_menu VALUES ('1f58bdc3e6ca407a95dd6c04ae248884', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '炼狱之塔统计', 'purgatory.pagoda', '/log/pagoda/purgatoryPagodaList', '', 'search', '140', '1', '', '1', '2015-09-21 14:01:13', '1', '2015-09-21 14:01:13', null, '0');
INSERT INTO sys_menu VALUES ('2', '1', '0,1,', '系统设置', 'system.setup', '', '', '', '9000', '1', '', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('20', '13', '0,1,2,13,', '用户管理', 'user.manager', '/sys/user/', null, 'user', '30', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('20ee78e72c9942899c057656d0846aee', '3', '0,1,2,3,', '跨服服务器', 'cross.server', '/tools/crossArea/crossAreas', '', 'search', '100', '1', '', '1', '2015-01-21 14:31:15', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('21', '20', '0,1,2,13,20,', '查看', 'operation.view', null, null, null, '30', '0', 'sys:user:view', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('210ca2a4c27042b884b6607eaedf310a', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '在线时长统计', 'online.data.stat', '/global/onlineTime/onlineTimeReport', '', 'th-large', '110', '1', '', '1', '2014-12-22 11:08:23', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('21b8e9760d234904bdabc5b0bcaf43dd', 'fa6365affa0e40aa84f2a6db7e368dbe', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,fa6365affa0e40aa84f2a6db7e368dbe,', '回复', 'game.feedback.reply', '', '', '', '30', '1', 'game.feedback.reply', '1', '2015-01-28 15:48:11', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('22', '20', '0,1,2,13,20,', '修改', 'operation.modify', null, null, null, '30', '0', 'sys:user:edit', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('234def35d20b4bdea67d854a6955d9b6', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '猎命', 'hunt.huntLife', '/log/hunt/huntLifeList', '', 'search', '70', '1', '', '1', '2015-08-31 11:57:01', '1', '2015-08-31 11:57:01', null, '0');
INSERT INTO sys_menu VALUES ('23bf02fcdcb34002be1295f01ed31ca2', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '战阶分布', 'battle.statistics', '/log/battle/battleStatisticsList', '', 'search', '100', '1', '', '1', '2015-08-19 15:46:11', '1', '2015-08-19 15:47:06', null, '0');
INSERT INTO sys_menu VALUES ('27', '1', '0,1,', '我的面板', 'my.dashboard', '', '', '', '100', '0', '', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('28', '27', '0,1,27,', '个人信息', 'person.info', null, null, null, '990', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('287c530d2083492eb4ff71ef71211cae', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '组队副本', 'task.team', '/log/taskTeam/taskTeamList', '', 'search', '90', '1', '', '1', '2015-11-12 10:38:49', '1', '2015-11-12 10:39:34', null, '0');
INSERT INTO sys_menu VALUES ('288c258b3fae4f9e820bf1c638c35739', 'c088d87e488d4ee2bbb1b0693f486a7f', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c088d87e488d4ee2bbb1b0693f486a7f,', '批量取消', 'game.recharge.batchcancel', '', '', '', '30', '1', 'game.recharge.batchcancel', '1', '2014-11-13 18:02:22', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('28c4ddf22cac40ad887c97ca8aeefcc5', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '神兵进阶', 'magic.advanced', '/log/magic/magicAdvancedList', '', 'search', '250', '1', '', '1', '2016-03-23 10:49:11', '1', '2016-03-23 10:49:11', null, '0');
INSERT INTO sys_menu VALUES ('28d5c02fd98b43f19a38555616b6103a', '3', '0,1,2,3,', '平台管理', 'platform.manager', '/tools/gamePlatform', '', 'th-large', '70', '1', '', '2', '2014-10-27 15:03:20', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('29', '28', '0,1,27,28,', '个人信息', 'person.info', '/sys/user/info', null, 'user', '30', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('2a83e3dfec0542948353bb71504c4e82', '0b8f61959129420bac915bb68c9f56cb', '0,1,0b8f61959129420bac915bb68c9f56cb,', '客服日志', 'customer.operation.log', '', '', '', '40', '1', '', '1', '2014-11-17 15:21:08', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('2a9181577f8e46dca445a31e0d6e7002', '974d495a4bcb4311ab5d2b89e2c6fda2', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,', '游戏更新日志', 'game.update.log', '', '', '', '30', '1', '', '1', '2015-06-02 16:25:30', '1', '2015-06-02 16:25:30', null, '0');
INSERT INTO sys_menu VALUES ('2b86128a42d149648c75a593242df951', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '宝石镶嵌', 'jewel.upgrade', '/log/jewel/jewelUpgradeList', '', 'search', '40', '1', '', '1', '2015-07-30 17:35:11', '1', '2015-07-30 17:39:06', null, '0');
INSERT INTO sys_menu VALUES ('2caea8272cb543709c982dbf2ae7d9ef', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '综合日志查询', 'comprehensive.log.query', '/log/comprehensiveLogController/comprehensiveList', '', 'search', '70', '1', '', '1', '2015-01-07 11:20:34', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('2ceaa9271aaa4eb2a2df0fe7888bf426', '77', '0,1,76,77,', '充值统计', 'charge.statistics', '/log/recharge/chargeStatistics', '', 'th-large', '40', '1', '', '1', '2014-11-24 17:16:40', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('2d89454129ee48f49d661d0df266f2c1', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '实时地图用户分布', 'map.user.distribution', '/global/mapRoleOnline/list', '', 'search', '70', '1', '', '1', '2015-02-03 10:29:41', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('2e00ab42b70642078fa1421f8da6b815', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '日综合报表', 'daily.comprehensive.report', '/log/dashboard/dailyComprehensiveReport', '', 'th-large', '40', '1', '', '1', '2014-11-27 16:24:21', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('2ee7aba371ba452dbf1637eff6aa0ba8', '0b8f61959129420bac915bb68c9f56cb', '0,1,0b8f61959129420bac915bb68c9f56cb,', '系统公告', 'system.notice', '/notice/publish', '', 'th-large', '30', '1', '', '1', '2014-10-29 16:07:54', '1', '2015-02-06 17:00:20', null, '1');
INSERT INTO sys_menu VALUES ('2f65b012f0c94e83b084c7f3f4d8539a', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '称号激活统计', 'title.activation', '/log/title/titleActiveList', '', 'search', '50', '1', '', '1', '2015-07-30 17:35:11', '1', '2015-07-30 17:39:06', null, '0');
INSERT INTO sys_menu VALUES ('2fc473ad7d4f4e33a1b85077548df85f', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '挖尸体', 'excavate.corpse', '/log/excavate/excavateCorpseList', '', 'search', '30', '1', '', '1', '2015-08-25 11:03:48', '1', '2015-08-25 11:03:48', null, '0');
INSERT INTO sys_menu VALUES ('3', '2', '0,1,2,', '系统设置', 'system.manager', null, null, null, '980', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('30', '28', '0,1,27,28,', '修改密码', 'modify.password', '/sys/user/modifyPwd', null, 'lock', '40', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('3146b18984134f099842932b301b7f3c', '0b8f61959129420bac915bb68c9f56cb', '0,1,0b8f61959129420bac915bb68c9f56cb,', '客服工具', 'customer.tools', '', '', '', '30', '1', '', '1', '2014-10-29 16:15:44', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('31e8fa0466e84918adcbe4f7c9e8a1ff', 'be11fae8a7514053936c3e5ca6b18989', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,be11fae8a7514053936c3e5ca6b18989,', '公告修改', 'game.notice.edit', '', '', '', '30', '1', 'game.notice.edit', '1', '2015-01-28 14:15:47', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('335170ba4d8d44a094d3f666efec0961', '6796f5156a694f4ea1b8218fcec3ac29', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,6796f5156a694f4ea1b8218fcec3ac29,', '导出', 'log.propConsume.export', '', '', '', '30', '1', 'log.propConsume.export', '1', '2015-01-28 19:29:08', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('33e96a120190430387e38610c3994cf8', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '领地战统计', 'territory.war', '/log/territory/territoryWarList', '', 'search', '160', '1', '', '1', '2015-09-22 15:21:13', '1', '2015-09-22 15:21:13', null, '0');
INSERT INTO sys_menu VALUES ('34eda2e33d594d3194eb2656267022f3', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '邮件日志查询', 'email.log.query', '/log/mail/list', '', 'search', '100', '1', '', '1', '2015-01-07 11:23:14', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('3687d6ca32c1415f956f7e5d2fe9bad9', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '7日留存数据', 'seventh.remainer.data', '/global/sevenDaysRemainer/sevenDaysRemainerReport', '', 'th-large', '90', '1', '', '1', '2014-12-22 11:05:23', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('38519c0af4a749aab25570fbab9530e1', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '系统邮件', 'system.email', '/tools/gameEmail', '', 'th-large', '40', '1', '', '1', '2014-10-29 16:16:58', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('394e65035e68433db1a4860cb693f982', 'd5117230f69042d5a1a64acced76e1d0', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,d5117230f69042d5a1a64acced76e1d0,', '查看', 'log.propGain.view', '', '', '', '30', '1', 'log.propGain.view', '1', '2015-01-29 11:25:26', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('3b1ffdb70b044fc0a0910a90d168d563', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '基础信息', 'base.info', '/global/dashboard/baseReport', '', 'th-large', '30', '1', '', '1', '2014-11-27 16:20:34', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('3b43450c6c3f4898becdf2896e95d6fe', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '单服每日数据报表', 'daily.serverDaily.report', '/global/dashboard/serverDailyReport', '', '', '30', '1', '', 'bebede4ccd604db8a0375fa2b880a8a0', '2014-12-10 10:44:42', '1', '2015-02-06 17:00:24', null, '1');
INSERT INTO sys_menu VALUES ('3cb8326e252d4e558401a28a04e0d944', '3', '0,1,2,3,', '服务器管理', 'server.manager', '/tools/gameServer', '', 'th-large', '70', '0', '', '1', '2014-10-28 15:35:01', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('4', '3', '0,1,2,3,', '菜单管理', 'menu.manager', '/sys/menu/', null, 'list-alt', '30', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('431d25cbcb404823a9765d4d8299a319', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '活动面板消费分布', 'panel.consume.distribution', '/global/tradeController/activePanelDistribution', '', 'th-large', '140', '1', '', '1', '2014-12-23 10:50:18', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('4558e89d0aae483db618117f2d60d28e', 'ba8d1fffc0be41a08445ca3343c15669', '0,1,ba8d1fffc0be41a08445ca3343c15669,', '活动配置', 'activity.conf', '', '', '', '40', '1', '', '1', '2015-03-25 18:02:37', '1', '2015-03-25 18:02:37', null, '0');
INSERT INTO sys_menu VALUES ('455d3e03f56044288760a4eacc650673', 'ba8605c15e5647709e6552006a843964', '0,1,ba8605c15e5647709e6552006a843964,', '游戏日志', 'game.log', '', '', '', '1000', '1', '', '1', '2014-10-22 17:13:46', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('462aed564c504de68303a47be16140df', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '翅膀分布', 'cloak.distribution', '/log/cloak/cloakList', '', 'search', '70', '1', '', '1', '2015-08-04 14:09:55', '1', '2015-08-04 14:09:55', null, '0');
INSERT INTO sys_menu VALUES ('47bce2b6d4a04fdf9e4e35c0cbad0c38', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '技能书吞噬', 'skillbook.phagocytosis', '/log/phagocytosis/skillbookPhagocytosisList', '', 'search', '260', '1', '', '1', '2016-05-23 17:47:03', '1', '2016-05-23 17:47:03', null, '0');
INSERT INTO sys_menu VALUES ('495411498edf4f95a679b65fc9d87e57', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '活跃度任务统计', 'active.TakeTask', '/log/active/activeTakeTaskList', '', 'search', '210', '1', '', '1', '2015-09-24 18:32:45', '1', '2015-09-24 18:32:45', null, '0');
INSERT INTO sys_menu VALUES ('4b358f2ee31c4696971948bb599bfd64', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '军阶统计', 'rank.statistics', '/log/rank/rankStatisticsList', '', 'search', '110', '1', '', '1', '2015-08-21 17:01:52', '1', '2015-08-21 17:02:50', null, '0');
INSERT INTO sys_menu VALUES ('4b8afe35332a427294766ebaf6963f89', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '虚拟点数', 'virtual.point', '/tools/virtual', '', 'search', '120', '1', '', '1', '2015-07-01 10:52:11', '1', '2015-07-01 10:52:11', null, '0');
INSERT INTO sys_menu VALUES ('4c6d05eb266f4bd0a54182f981936faa', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '取消', 'game.email.cancel', '', '', '', '30', '1', 'game.email.cancel', '1', '2015-01-28 14:37:25', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('4ce3a28e05684f49bafad35fcfcaf5f1', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '日活跃用户等级分布', 'dayactive.userlevel', '/global/dayActiveLevel/dayActiveUserLevelList', '', 'th-large', '120', '1', '', '1', '2015-11-11 15:40:24', '1', '2015-11-11 15:40:24', null, '0');
INSERT INTO sys_menu VALUES ('4de4e41949fd47cfba69395b7d20a19f', '786dbf71d4b44fd4a122969122f91cfa', '0,1,e19a2e2c5adc43a09ccfc64c04002800,786dbf71d4b44fd4a122969122f91cfa,', '协议管理', 'game.command', '/tools/command/gameCommand', '', 'search', '40', '1', '', '1', '2015-12-25 17:11:06', '1', '2015-12-25 17:11:06', null, '0');
INSERT INTO sys_menu VALUES ('4e5dabae6ddd434da38376ae7ba89dcd', '4b8afe35332a427294766ebaf6963f89', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,4b8afe35332a427294766ebaf6963f89,', '申请虚拟点数', 'apply.virtual.point', '', '', '', '30', '1', 'apply.virtual.point', '1', '2015-07-22 11:20:07', '1', '2015-07-22 11:20:31', null, '0');
INSERT INTO sys_menu VALUES ('5', '4', '0,1,2,3,4,', '查看', 'operation.view', null, null, null, '30', '0', 'sys:menu:view', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('52af42abfa84461ba86010c2aa43e476', 'ba8d1fffc0be41a08445ca3343c15669', '0,1,ba8d1fffc0be41a08445ca3343c15669,', '基础配置', 'game.base.setup', '', '', '', '30', '1', '', '1', '2014-11-10 15:42:31', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('542f62abf1684a10a9645582464bbda6', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '资源线消费分布', 'resource.consume.distribution', '/global/tradeController/resLineDistribution', '', 'th-large', '130', '1', '', '1', '2014-12-23 10:49:22', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('561900fe97174eb2b19c87a562bd2737', '9b5f5c9874964798b912033bb54bd31e', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,9b5f5c9874964798b912033bb54bd31e,', '导出', 'log.level.export', '', '', '', '30', '1', 'log.level.export', '1', '2015-01-28 20:11:12', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('56583ff4cfec498e8e1c46d22bfb3a1f', '4b8afe35332a427294766ebaf6963f89', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,4b8afe35332a427294766ebaf6963f89,', '取消虚拟物品', 'cancel.apply.virtual', '', '', '', '60', '1', 'cancel.apply.virtual', '1', '2015-09-09 14:18:19', '1', '2015-09-09 14:18:49', null, '0');
INSERT INTO sys_menu VALUES ('56616218d06941c3882222a0a9887640', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '战斗力变化日志', 'power.change.log', '/log/powerChange/list', '', 'search', '140', '1', '', '1', '2015-09-23 10:56:05', '1', '2015-09-23 10:56:59', null, '0');
INSERT INTO sys_menu VALUES ('57fa80d6d0494b388392611ca4402f19', '72fc33098f174ca6a640233e81d0e862', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,72fc33098f174ca6a640233e81d0e862,', '查看', 'game.gm.view', '', '', '', '30', '1', 'game.gm.view', '1', '2015-01-28 21:00:58', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('5843679ecd414a85a536f697926d2a0f', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '国战统计', 'state.Combat', '/log/state/stateCombatList', '', 'search', '130', '1', '', '1', '2015-09-21 10:27:38', '1', '2015-09-21 10:28:31', null, '0');
INSERT INTO sys_menu VALUES ('5892f68ce8d248bb84e2f27eaaadbe7e', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '充值消费数据', 'recharge.consume.data', '', '', '', '50', '1', '', '1', '2014-12-15 15:26:46', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('5a79d4493f454739bc53d422fddff711', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '开服回本统计', 'open.huiben.stat', '/global/openServiceIncome/openServiceIncomeMany', '', 'th-large', '90', '1', '', '1', '2014-12-26 10:59:00', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('5ad304b3c78a4509afdb4155cf58f9dc', '8761da76074c42d2b6351de38fdf8360', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,8761da76074c42d2b6351de38fdf8360,', '登录', 'game.role.logingame', '', '', '', '30', '1', 'game.role.logingame', '1', '2015-01-28 15:23:01', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('5d1e7ac84842459a853b5420e9ecacbd', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '点击收藏游戏', 'collect.game', '/log/collect/collectGameList', '', 'search', '190', '1', '', '1', '2015-12-17 10:49:32', '1', '2015-12-17 10:49:32', null, '0');
INSERT INTO sys_menu VALUES ('5ddec7c9f2284df5842fe08141fa7db2', '9607672de915416cbee110fcb29c304d', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,9607672de915416cbee110fcb29c304d,', '导出', 'log.upgrade.export', '', '', '', '30', '1', 'log.upgrade.export', '1', '2015-01-28 20:13:13', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('5f447d194c134c0dbace9a1656e9629c', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '金币统计', 'coin.stat', '/log/moneyFlow/coinProduce', '', 'search', '150', '1', '', '1', '2015-06-16 16:28:42', '1', '2015-06-16 16:29:08', null, '0');
INSERT INTO sys_menu VALUES ('6', '4', '0,1,2,3,4,', '修改', 'operation.modify', null, null, null, '30', '0', 'sys:menu:edit', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('6062e7b62a3d430e8e0f5967b4120f1a', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '战斗力分布', 'log.rank', '/log/rank/todayMax', '', 'search', '150', '1', '', '1', '2015-10-14 18:12:31', '1', '2015-10-14 18:12:31', null, '0');
INSERT INTO sys_menu VALUES ('60689411ef6544729ddb76f65d3881f3', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '绑元统计', 'banding.stat', '/log/moneyFlow/bandingProduce', '', 'search', '160', '1', '', '1', '2015-06-16 16:23:32', '1', '2015-06-16 16:27:00', null, '0');
INSERT INTO sys_menu VALUES ('618aa2a707964f25b55560a2b35e5b0f', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '生成礼包码', 'tools.gift.creat.code', '/tools/gift/createCodeList', '', 'th-large', '80', '1', '', '1', '2015-02-06 17:02:08', '1', '2015-02-06 17:02:08', '', '0');
INSERT INTO sys_menu VALUES ('6335e78f97064d7495c7ea0d886e7f7d', '0b8f61959129420bac915bb68c9f56cb', '0,1,0b8f61959129420bac915bb68c9f56cb,', '系统邮件', 'system.email', '/game/email', '', 'th-large', '40', '1', '', '1', '2014-10-29 16:11:01', '1', '2015-02-06 17:00:20', null, '1');
INSERT INTO sys_menu VALUES ('6446fa92acc64f14bfd1d06694850f43', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '用户行为数据', 'user.action.data', '', '', '', '70', '1', '', 'bebede4ccd604db8a0375fa2b880a8a0', '2015-02-03 10:21:47', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('647f1a3138f146808f9d9b6535f9542b', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '幻魔密境统计', 'secret.area', '/log/area/secretAreaList', '', 'search', '170', '1', '', '1', '2015-09-22 17:45:15', '1', '2015-09-22 17:45:15', null, '0');
INSERT INTO sys_menu VALUES ('652879acc951475ea9b242c0863ae9ed', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '大R监控', 'monitor.role', '/game/daZhongRMonitor/daZhongRMonitorReport', '', 'th-large', '180', '1', '', '1', '2014-12-25 17:32:12', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('658d497b10e64e0aa3e5f5739ac4fa8e', '9607672de915416cbee110fcb29c304d', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,9607672de915416cbee110fcb29c304d,', '查看', 'log.upgrade.view', '', '', '', '30', '1', 'log.upgrade.view', '1', '2015-01-29 11:27:55', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('65c7d74fc10049bf8bfb7606773c9aa7', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '聚划算', 'calculate.quiteCalculate', '/log/calculate/quiteCalculateList', '', 'search', '50', '1', '', '1', '2015-08-26 19:09:04', '1', '2015-08-26 19:10:08', null, '0');
INSERT INTO sys_menu VALUES ('669a87df82d042049a2b2f0058f1d828', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '平台数据统计', 'platform.data.stat', '', '', '', '60', '1', '', '1', '2014-12-26 10:47:17', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('67', '2', '0,1,2,', '日志查询', 'log.search', null, null, null, '985', '1', null, '1', '2013-06-03 08:00:00', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('6796f5156a694f4ea1b8218fcec3ac29', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '道具消耗日志', 'consume.prop', '/log/propController/propConsumeList', '', 'search', '30', '1', '', '1', '2014-10-22 17:17:51', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('68', '67', '0,1,2,67,', '日志查询', 'log.search', '/sys/log', null, 'pencil', '30', '1', 'sys:log:view', '1', '2013-06-03 08:00:00', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('69555a914ed54b60aaa0eb5f5ed6fa4f', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '剧情副本', 'plot.plotTask', '/log/plot/plotTaskList', '', 'search', '80', '1', '', '1', '2015-08-31 14:53:00', '1', '2015-08-31 14:53:00', null, '0');
INSERT INTO sys_menu VALUES ('6a21d1612e874f7680727367918f7fc8', '4b8afe35332a427294766ebaf6963f89', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,4b8afe35332a427294766ebaf6963f89,', '发送虚拟物品', 'approve.virtual.goods', '', '', '', '50', '1', 'approve.virtual.goods', '1', '2015-07-22 11:22:43', '1', '2015-07-22 11:22:43', null, '0');
INSERT INTO sys_menu VALUES ('6a95ff0230e444d799f53638c8ba86c6', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '月营收报表', 'monthly.sales.report', '/global/dashboard/monthSalesReport', '', 'th', '60', '1', '', '1', '2014-12-10 14:48:47', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('6b2a72e5d43e4b06b2b5c21300064baf', 'c0fba9c9a6324933ae0615222c410f1a', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c0fba9c9a6324933ae0615222c410f1a,', '查看', 'game.chatmonitor.view', '', '', '', '30', '1', 'game.chatmonitor.view', '1', '2015-01-28 21:01:27', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('6b7f1afa82b54706b61eb278fb35497e', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '新服道具销售', 'new.server.goods.sale', '/global/tradeController/propSale?stype=1', '', 'th-large', '160', '1', '', '1', '2014-12-23 10:52:29', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('6e520b3a212b4fa4bb703b63a1110740', '8a4686e4c124450ea0f11de26957349c', '0,1,ba8d1fffc0be41a08445ca3343c15669,8a4686e4c124450ea0f11de26957349c,', '运维数据', 'coop.coopBase', '/tools/coop/coopBaseList', '', 'search', '60', '1', '', '1', '2015-11-05 17:08:12', '1', '2015-11-05 17:10:33', null, '0');
INSERT INTO sys_menu VALUES ('6eb4114c4f4e44b68fcdbde07f2b0708', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '装备统计', 'equipment.statistics', '/log/equip/equipStrengthenList', '', 'search', '60', '1', '', '1', '2015-08-03 10:35:24', '1', '2015-08-03 10:35:24', null, '0');
INSERT INTO sys_menu VALUES ('7', '3', '0,1,2,3,', '角色管理', 'role.manager', '/sys/role/', null, 'lock', '50', '1', null, '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('71b29e8b6a724cada697a115a74b006c', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '舞姬分布', 'beauty.distribution', '/log/beauty/list', '', 'search', '30', '1', '', '1', '2015-07-29 14:32:49', '1', '2015-07-29 14:32:49', null, '0');
INSERT INTO sys_menu VALUES ('72fc33098f174ca6a640233e81d0e862', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '指导员管理', 'game.gm.manager', '/game/role/gmList', '', 'th-large', '80', '1', '', '1', '2014-11-19 11:09:35', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('76', '1', '0,1,', '玩家信息', 'player.info', '', '', '', '1000', '1', '', '1', '2013-12-12 08:00:00', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('77', '76', '0,1,76,', '玩家信息', 'player.info', '', '', '', '30', '1', '', '1', '2013-12-12 08:00:00', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('78', '77', '0,1,76,77,', '充值查询', 'charge.search', '/log/recharge', '', 'th-large', '30', '1', '', '1', '2013-12-12 08:00:00', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('786dbf71d4b44fd4a122969122f91cfa', 'e19a2e2c5adc43a09ccfc64c04002800', '0,1,e19a2e2c5adc43a09ccfc64c04002800,', '开发工具', 'develop.tool', '', '', '', '30', '1', '', '1', '2015-08-27 16:42:16', '1', '2015-08-27 16:42:16', null, '0');
INSERT INTO sys_menu VALUES ('786f174e39c74f54bad888f7d4e4e90b', '2caea8272cb543709c982dbf2ae7d9ef', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,2caea8272cb543709c982dbf2ae7d9ef,', '查看', 'log.comprehensive.view', '', '', '', '30', '1', 'log.comprehensive.view', '1', '2015-01-29 11:27:04', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('79', '78', '0,1,76,77,78,', '查看', 'operation.view', null, null, null, '30', '0', 'prj:project:view', '1', '2013-12-12 08:00:00', '1', '2015-02-06 17:00:22', null, '1');
INSERT INTO sys_menu VALUES ('7971b2c30f7e4c858dc8c02f5bf57d42', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '用户职业分布', 'user.job.distribution', '/global/jobDistribution', '', 'search', '30', '1', '', '1', '2015-02-03 10:23:57', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('79c1347373c64b7b8f78bc35204f2079', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '货币消耗日志', 'money.consume.log', '/log/moneyConsumeLog/moneyConsumeLogReport', '', 'search', '60', '1', '', '1', '2015-01-04 15:26:54', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('7a43792f8a3e466c9914eb5ea42bf138', '77', '0,1,76,77,', '角色在线统计', 'role.online.statistic', '/log/roleOnline', '', 'th-large', '50', '0', '', '1', '2014-11-25 10:59:00', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('7a9be168b3f04a2b91c9963382d811fa', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '综合数据统计', 'comprehensive.statistics', '', '', '', '30', '1', '', '1', '2014-11-27 16:11:52', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('7bfb04bd618f4c1db34fafbe894a88ce', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '玩家登陆日志', 'player.login.log', '/log/roleLogin', '', 'th-large', '80', '1', '', '1', '2014-11-21 10:33:03', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('8', '7', '0,1,2,3,7,', '查看', 'operation.view', null, null, null, '30', '0', 'sys:role:view', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('80', '78', '0,1,76,77,78,', '修改', 'operation.modify', null, null, null, '40', '0', 'prj:project:edit', '1', '2013-12-12 08:00:00', '1', '2015-02-06 17:00:22', null, '1');
INSERT INTO sys_menu VALUES ('814ce365cc1941e3ad559c24e5be64df', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '星图分布', 'map.star', '/log/beauty/starDistribution', '', 'search', '90', '1', '', '1', '2015-08-20 09:32:11', '1', '2015-08-20 09:33:26', null, '0');
INSERT INTO sys_menu VALUES ('8175c9591fb64103b548372fbce143a9', 'c088d87e488d4ee2bbb1b0693f486a7f', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c088d87e488d4ee2bbb1b0693f486a7f,', '批量恢复', 'game.recharge.batchrecover', '', '', '', '30', '1', 'game.recharge.batchrecover', '1', '2015-01-28 15:04:27', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('81f2b205c4634da59f7dfe7f4797d699', '79c1347373c64b7b8f78bc35204f2079', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,79c1347373c64b7b8f78bc35204f2079,', '导出', 'log.moneyConsume.export', '', '', '', '30', '1', 'log.moneyConsume.export', '1', '2015-01-28 19:59:01', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('824efe819d1f4b0cb87727e197610eaf', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '大R监控配置', 'monitor.config', '/tools/monitorConfig/monitorConfig', '', 'th-large', '50', '1', '', '1', '2014-12-26 10:13:00', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('83afdbdd61fa411e8c595b6c9fbf90ca', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '大乐透', 'lotto.bigLotto', '/log/lotto/bigLottoList', '', 'search', '40', '1', '', '1', '2015-08-26 10:08:38', '1', '2015-08-26 10:08:38', null, '0');
INSERT INTO sys_menu VALUES ('8585ed01db6f4f188effe2367fd02cd8', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '道具销售', 'goods.sale', '/global/tradeController/propSale?stype=0', '', 'th-large', '150', '1', '', '1', '2014-12-23 10:51:32', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('85dc1b1626b544d8820a92d8f52c4f4d', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', 'AXP获取日志', 'axp.gain.log', '/log/axpGetLog/axpGetLogReport', '', 'search', '65', '1', '', '1', '2015-04-23 15:59:44', '1', '2015-04-23 15:59:44', null, '0');
INSERT INTO sys_menu VALUES ('874acc83c9334f10ba0341ea3379f21c', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '恢复', 'game.email.recover', '', '', '', '30', '1', 'game.email.recover', '1', '2015-01-28 14:38:12', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('8761da76074c42d2b6351de38fdf8360', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '玩家信息', 'player.info', '/game/role', '', 'th-large', '60', '1', '', '1', '2014-11-14 19:07:07', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('8979e586f9004308bb3fc7ce2e7ce144', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '增幅器', 'amplifier.distribution', '/log/amplifier/amplifierDistributionList', '', 'search', '230', '1', '', '1', '2015-11-13 11:46:49', '1', '2015-11-13 11:46:49', null, '0');
INSERT INTO sys_menu VALUES ('8a409be26a2b49d3a1c9c6bcf6585d7f', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '充值消费排行', 'recharge.consume.ranking', '/global/rechargeConsume/rechargeConsumeRanking', '', 'th-large', '120', '1', '', '1', '2014-12-23 10:48:15', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('8a4686e4c124450ea0f11de26957349c', 'ba8d1fffc0be41a08445ca3343c15669', '0,1,ba8d1fffc0be41a08445ca3343c15669,', '游戏配置', 'game.config', '', '', '', '50', '1', '', '1', '2015-07-15 15:48:41', '1', '2015-07-15 15:48:41', null, '0');
INSERT INTO sys_menu VALUES ('8aace06f36ce47329d13dfd7101b7489', 'c088d87e488d4ee2bbb1b0693f486a7f', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c088d87e488d4ee2bbb1b0693f486a7f,', '批量审核', 'game.recharge.batchadd', '', '', '', '30', '1', 'game.recharge.batchadd', '1', '2014-11-13 18:01:58', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('8b4e73de8d8f458c8d2cb2e2ff69579d', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台统计页', 'platform.stat.page', '/global/platformStatistics/platformStatistics', '', 'th-large', '30', '1', '', '1', '2014-12-26 10:50:13', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('8cdfc1ac60d6473a8bc1a65cb0df708d', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '礼包码查询', 'tools.gift.view.code', '/tools/gift/viewCodeList', '', 'th-large', '90', '1', '', '1', '2015-02-06 17:03:11', '1', '2015-02-06 17:03:11', '', '0');
INSERT INTO sys_menu VALUES ('8cf24e1d8582429b995dddd2ee07bfff', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '战力排行统计', 'stat.zhandouli.rank', '/log/rank/power', '', 'search', '90', '1', '', '2', '2015-03-06 13:47:52', '2', '2015-03-06 13:47:52', null, '0');
INSERT INTO sys_menu VALUES ('8d007f494fd345a9947b333a9c07f926', '4b8afe35332a427294766ebaf6963f89', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,4b8afe35332a427294766ebaf6963f89,', '添加虚拟物品', 'add.virtual.goods', '', '', '', '40', '1', 'add.virtual.goods', '1', '2015-07-22 11:21:56', '1', '2015-07-22 11:21:56', null, '0');
INSERT INTO sys_menu VALUES ('8ef68bd503124bc0a40d30dc010cbd0d', 'a8b59f132bb54231b205e31e9c1b9ae0', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,a8b59f132bb54231b205e31e9c1b9ae0,', '导出', 'game.guild.export', '', '', '', '30', '1', 'game.guild.export', '1', '2015-01-28 20:18:25', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('8f3f411d3a344198a18d22c4ea73ef05', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '游戏系统参与度', 'game.system.join', '', '', '', '90', '1', '', '1', '2015-08-25 11:03:00', '1', '2015-08-25 11:03:00', null, '0');
INSERT INTO sys_menu VALUES ('9', '7', '0,1,2,3,7,', '修改', 'operation.modify', null, null, null, '30', '0', 'sys:role:edit', '1', '2013-05-27 08:00:00', '1', '2015-02-06 17:00:28', null, '0');
INSERT INTO sys_menu VALUES ('9101ee0eacda4916b4eca19510f458f0', '77', '0,1,76,77,', '等级排行榜', 'role.level.ranking', '/game/role/levelRanking', '', 'th-large', '60', '1', '', '1', '2014-11-25 14:16:22', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('952d6ef62d92427b92425cf4bb887853', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '基础配置', 'game.base.setup', '/tools/gameConfig', '', 'th-large', '30', '1', '', '1', '2014-11-10 15:45:33', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('95405708ae0a412aaa0c51d67fe2f0cb', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '矿战统计', 'mine.war', '/log/mine/mineWarList', '', 'search', '180', '1', '', '1', '2015-09-23 17:40:34', '1', '2015-09-23 17:40:34', null, '0');
INSERT INTO sys_menu VALUES ('95dc231837dd42ca9a628994de881e36', '4558e89d0aae483db618117f2d60d28e', '0,1,ba8d1fffc0be41a08445ca3343c15669,4558e89d0aae483db618117f2d60d28e,', '活动列表', 'activity.list', '/tools/activity/activityList', '', 'th-list', '40', '1', '', '1', '2015-03-26 09:39:12', '1', '2015-03-26 09:39:12', null, '0');
INSERT INTO sys_menu VALUES ('9607672de915416cbee110fcb29c304d', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '进阶日志查询', 'role.level.upgrade.log.query', '/log/upgradeLog/upgradeLogReport', '', 'search', '90', '1', '', '1', '2015-01-07 11:22:17', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('9675bf6383d142e5b56cdc8017027ea2', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '货币获取日志', 'money.gain.log', '/log/moneyGetLog/moneyGetLogReport', '', 'search', '50', '1', '', '1', '2015-01-04 15:26:10', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('974d495a4bcb4311ab5d2b89e2c6fda2', '1', '0,1,', '运营管理', 'operation.manager', '', '', '', '4000', '1', '', '1', '2015-01-22 16:11:26', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('9846213b7d1a4efda484269f5e4a30b5', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '道具统计', 'prop.stat', '/log/goodsFlow/propProduce', '', 'search', '170', '1', '', '1', '2015-06-16 16:31:51', '1', '2015-06-16 16:31:51', null, '0');
INSERT INTO sys_menu VALUES ('9a619bd9bc7e48febca45f7e088aab5d', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '流失用户统计', 'stat.user.lose', '/log/userStat/churnUser', '', 'search', '120', '1', '', '1', '2015-03-06 14:11:26', '1', '2015-03-06 14:11:41', null, '0');
INSERT INTO sys_menu VALUES ('9a657905fa4c4fdab30be8ec0f58769f', 'c088d87e488d4ee2bbb1b0693f486a7f', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c088d87e488d4ee2bbb1b0693f486a7f,', '查看', 'game.recharge.view', '', '', '', '30', '1', 'game.recharge.view', '1', '2015-01-28 20:59:01', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('9b303d9586c94294b90e8fa8f3a771ac', 'c0fba9c9a6324933ae0615222c410f1a', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c0fba9c9a6324933ae0615222c410f1a,', '修改关键字', 'game.chatmonitor.keyWordsEdit', '', '', '', '30', '1', 'game.chatmonitor.keyWordsEdit', '1', '2015-01-28 17:42:30', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('9b5f5c9874964798b912033bb54bd31e', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '等级日志查询', 'role.level.log.query', '/log/levelLog/levelLogReport', '', 'search', '80', '1', '', '1', '2015-01-07 11:21:26', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('a151ee349a004e53956dc6d389301bab', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '超级会员', 'super.member', '/tools/super/list', '', 'th-large', '140', '1', '', '1', '2015-08-14 11:08:41', '1', '2015-08-14 11:08:41', null, '0');
INSERT INTO sys_menu VALUES ('a158f6c15f924671bc262ca5072196be', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '荣耀战场', 'honor.battleground', '/log/honor/honorBattlegroundList', '', 'search', '110', '1', '', '1', '2015-11-13 11:45:09', '1', '2015-11-13 11:45:09', null, '0');
INSERT INTO sys_menu VALUES ('a23bc1ed45c6448fa47c9827724b0755', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '运镖统计', 'dart.yunDart', '/log/dart/yunDartList', '', 'search', '190', '1', '', '1', '2015-09-24 11:51:54', '1', '2015-09-24 11:51:54', null, '0');
INSERT INTO sys_menu VALUES ('a27a3d612bd14b238b68bc4fd893c5b7', 'a62395bcfb434c169c1784c9d18862bf', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,a62395bcfb434c169c1784c9d18862bf,', '战区管理', 'battlearea.manager', '/tools/crossArea/battleArea', '', 'search', '30', '1', '', '1', '2015-01-22 16:13:06', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('a3dd69b9bf55499c831b270faf492918', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '拍卖行日志查询', 'stall.trade.log', '/log/stallTrade/list', '', 'search', '140', '1', '', '1', '2015-08-25 16:25:18', '1', '2015-08-25 16:26:17', null, '0');
INSERT INTO sys_menu VALUES ('a5f0d5a50da24ea29549d7b6c2d97533', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '充值消费时点分布', 'recharge.consume.distribution', '/global/rechargeConsume/rechargeConsumeTimePiont', '', 'th-large', '110', '1', '', '1', '2014-12-23 10:47:17', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('a62395bcfb434c169c1784c9d18862bf', '974d495a4bcb4311ab5d2b89e2c6fda2', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,', '战区管理', 'battlearea.manager', '', '', '', '30', '1', '', '1', '2015-01-22 16:11:50', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('a8b59f132bb54231b205e31e9c1b9ae0', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '公会日志查询', 'guild.log.query', '/game/guildLog/getGuildList', '', 'search', '120', '1', '', '1', '2015-01-07 11:24:59', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('aa23e93985dd4734ae41481dcbf5bf38', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '批量取消', 'batch.cancel', '', '', '', '30', '1', 'game.email.batchcancel', '1', '2014-11-12 17:17:05', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('aa32d9f4e721481392e0d8be1f1d0729', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '在线数据', 'online.data', '/global/onlinedata/onlineDataStatistics', '', 'th-large', '70', '1', '', '1', '2014-12-12 14:00:49', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('aa7d8004d8194882b8591d8619e72a66', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '预估配置', 'estimate.config', '/tools/config/list', '', 'th-large', '40', '1', '', '1', '2014-12-12 11:52:09', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('ab84798efe5b4d0db2012d51312ee86f', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '日消费统计', 'daily.consume.stat', '/global/rechargeConsume/dailyConsumeReport', '', 'th-large', '40', '1', '', '1', '2014-12-15 15:29:44', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('ac4da40055994d55a76d814836bc0ec4', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '摆摊日志查询', 'stall.log.query', '', '', 'search', '110', '0', '', '1', '2015-01-07 11:24:24', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('afd0ec6d7d59419f893c5b26f0b85ff4', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '新增修改', 'game.email.edit', '', '', '', '30', '1', 'game.email.edit', '1', '2015-01-28 14:36:39', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('b1bbb2727dd344a78537431182590d55', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '实时分服统计', 'real.time.service.stat', '/log/realTimeService/realTimeServiceReport', '', 'th-large', '80', '1', '', '1', '2014-12-11 16:22:32', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('b2f665d3c6f04bb5b4d753b4a68c65e0', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '平台导量配置', 'platform.reg.config', '/tools/daoLiangConfig/daoLiangConfig', '', 'th-large', '60', '1', '', '1', '2014-12-30 17:58:17', '1', '2015-02-06 17:00:27', null, '0');
INSERT INTO sys_menu VALUES ('b346417d7ab94b9687452c4b33577e99', '2a9181577f8e46dca445a31e0d6e7002', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,2a9181577f8e46dca445a31e0d6e7002,', '游戏更新日志', 'game.update.log', '/tools/config/gameUpdate', '', 'search', '30', '1', '', '1', '2015-06-02 16:26:08', '1', '2015-06-02 16:27:02', null, '0');
INSERT INTO sys_menu VALUES ('b3b67012f6534771914b1c5f1a7e15dd', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', 'AXP消耗日志', 'axp.consume.log', '/log/axpConsumeLog/axpConsumeLogReport', '', 'search', '66', '1', '', '1', '2015-04-23 17:10:17', '1', '2015-04-23 17:10:17', null, '0');
INSERT INTO sys_menu VALUES ('b477b42f15754f5d8051257bc6feba43', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '批量恢复', 'batch.recover', '', '', '', '30', '1', 'game.email.batchrecover', '1', '2014-11-12 17:17:35', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('b5688d61611248fca2b26dd6c5bf2acc', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '任务完成度统计', 'stat.task.complete', '/global/taskFinish/list', '', 'search', '60', '1', '', '1', '2015-02-03 10:28:03', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('b5fb64eb8da2447e81a4a0ec5d4a57bf', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '资源线统计', 'stat.resource.line', '', '', '', '80', '1', '', '1', '2015-07-29 11:39:08', '1', '2015-07-29 11:39:27', null, '0');
INSERT INTO sys_menu VALUES ('b94340e3b46947e396745d6131a7b99f', '7a9be168b3f04a2b91c9963382d811fa', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,7a9be168b3f04a2b91c9963382d811fa,', '滚服用户统计', 'gunfu.user.stat', '/global/dashboard/gunfuUserStatReport', '', 'th-large', '90', '1', '', '1', '2014-12-11 16:23:41', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('b96204586d634302b6f7138701217f15', 'be11fae8a7514053936c3e5ca6b18989', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,be11fae8a7514053936c3e5ca6b18989,', '公告发布', 'game.notice.publish', '', '', '', '30', '1', 'game.notice.publish', '1', '2015-01-28 14:16:32', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('ba6ad56917b64b718c6eeca74643451a', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', 'VIP勋章', 'vip.medal', '/log/medal/vipMedalList', '', 'search', '240', '1', '', '1', '2016-03-23 10:45:01', '1', '2016-03-23 10:49:30', null, '0');
INSERT INTO sys_menu VALUES ('ba8605c15e5647709e6552006a843964', '1', '0,1,', '游戏日志', 'game.log', '', '', '', '2000', '1', '', '2', '2014-10-22 16:39:59', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('ba8d1fffc0be41a08445ca3343c15669', '1', '0,1,', '游戏配置', 'game.setup', '', '', '', '8000', '1', '', '1', '2014-11-10 15:41:21', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('bbae5cd87dca4bce9d810041455aa0ec', '2caea8272cb543709c982dbf2ae7d9ef', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,2caea8272cb543709c982dbf2ae7d9ef,', '导出', 'log.comprehensive.export', '', '', '', '30', '1', 'log.comprehensive.export', '1', '2015-01-28 20:06:21', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('bc8d95b336954560a0dd66f75114d23a', '8761da76074c42d2b6351de38fdf8360', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,8761da76074c42d2b6351de38fdf8360,', '封号', 'game.role.fenghao', '', '', '', '30', '1', 'game.role.fenghao', '1', '2015-01-28 15:17:25', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('bd6829ab484c4ce0b8321527b6fe07cc', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '魔王降临', 'devil.sbefall', '/log/devil/devilSbefallList', '', 'search', '180', '1', '', '1', '2015-12-16 15:45:50', '1', '2015-12-16 15:46:48', null, '0');
INSERT INTO sys_menu VALUES ('be11fae8a7514053936c3e5ca6b18989', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '系统公告', 'system.notice', '/tools/gameNotice', '', 'th-large', '30', '1', '', '1', '2014-10-29 16:16:30', '1', '2015-02-06 17:00:20', null, '0');
INSERT INTO sys_menu VALUES ('bed33de5a5314d6f9ceea1d2ec1302c3', '8a4686e4c124450ea0f11de26957349c', '0,1,ba8d1fffc0be41a08445ca3343c15669,8a4686e4c124450ea0f11de26957349c,', '客户端配置', 'client.config', '/tools/config/client', '', 'search', '40', '1', '', '1', '2015-07-15 16:08:41', '1', '2015-07-15 16:08:41', null, '0');
INSERT INTO sys_menu VALUES ('bf6a920f8011451891c82a3bcfca411c', '8761da76074c42d2b6351de38fdf8360', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,8761da76074c42d2b6351de38fdf8360,', '删除角色', 'game.role.delete', '', '', '', '30', '1', 'game.role.delete', '1', '2014-11-18 18:31:14', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('bfb2c38417aa41acbfdeadf3eabb59f5', '79c1347373c64b7b8f78bc35204f2079', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,79c1347373c64b7b8f78bc35204f2079,', '查看', 'log.moneyConsume.view', '', '', '', '30', '1', 'log.moneyConsume.view', '1', '2015-01-29 11:26:24', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('c038a78e89b345a4be7b42fd3b318c58', '8761da76074c42d2b6351de38fdf8360', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,8761da76074c42d2b6351de38fdf8360,', '禁言', 'game.role.jinyan', '', '', '', '30', '1', 'game.role.jinyan', '1', '2015-01-28 15:16:42', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('c088d87e488d4ee2bbb1b0693f486a7f', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '元宝申请', 'game.treasure.charge', '/tools/recharge', '', 'th-large', '50', '1', '', '1', '2014-11-13 14:59:36', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('c0fba9c9a6324933ae0615222c410f1a', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '聊天监控', 'chat.monitor', '/game/chat/monitor', '', 'th-large', '100', '1', '', '1', '2014-11-21 11:59:04', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('c155249ac29640ce94598fde4e813672', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '查看', 'game.email.view', '', '', '', '30', '1', 'game.email.view', '1', '2015-01-28 20:57:33', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('c15edb59a1df470c87b29f4b83252ebc', '77', '0,1,76,77,', '战力排行榜', 'battle.effectiveness.ranking', '/game/role/battleRanking', '', 'th-large', '70', '1', '', '1', '2014-11-26 11:32:50', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('c340988175964625b3e8c8a31b15f727', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '在线数据分布', 'online.data.distribution', '/global/onlinedata/onlinePointDisList', '', 'th-large', '80', '1', '', '1', '2014-12-12 14:01:41', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('c3424f59f8be49e596f75a49e845f538', '3', '0,1,2,3,', '日志服务器', 'logger.server.list', '/tools/logger/list', '', '', '110', '1', '', '1', '2015-04-24 17:48:19', '1', '2015-04-24 17:48:19', null, '0');
INSERT INTO sys_menu VALUES ('c4157a7154924bb68542ca8b9205184c', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '新注册', 'new.registration', '/global/newRegisterStatistics/newRegisterStatisticsReport', '', 'th-large', '50', '1', '', '1', '2014-12-12 13:58:28', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('c44d2efd7f974eb088ec4aaf337112ca', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台充值统计', 'platform.recharge', '/charge/platform/platformRechargeList', '', 'th-large', '100', '1', '', '1', '2015-11-18 14:25:57', '1', '2015-11-18 14:25:57', null, '0');
INSERT INTO sys_menu VALUES ('c882999111194a2889ce2f0b75f40e3e', 'c0fba9c9a6324933ae0615222c410f1a', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c0fba9c9a6324933ae0615222c410f1a,', '监控', 'game.chatmonitor.monitor', '', '', '', '30', '1', 'game.chatmonitor.monitor', '1', '2015-01-28 17:42:08', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('cce9e15d662c4443ac8ed822f996f895', 'a8b59f132bb54231b205e31e9c1b9ae0', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,a8b59f132bb54231b205e31e9c1b9ae0,', '查看', 'game.guild.view', '', '', '', '30', '1', 'game.guild.view', '1', '2015-01-29 11:28:14', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('cd22ae07700640cf89de66548076347c', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '日志限制配置', 'log.limit.config', '/tools/config/limitList', '', 'th-large', '100', '1', '', '1', '2015-02-13 19:50:50', '1', '2015-02-13 20:01:17', null, '0');
INSERT INTO sys_menu VALUES ('cda24f5e26044c72916c1d08d2c99964', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台注册转化统计', 'platform.reg.translate.stat', '/global/platformStatistics/platformRegConvertionStatistics', '', 'th-large', '50', '1', '', '1', '2014-12-26 10:53:48', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('ce14bef780934c078269e8f6ccc115c7', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '元宝产出消耗', 'treasure.produce.consume.stat', '/global/coinProduceConsume/coinProduceConsumeReport', '', 'th-large', '170', '1', '', '1', '2014-12-25 17:32:41', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('ce57185a7e17455faf5db75585ba7a61', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '充值排行统计', 'stat.recharge.rank', '/log/rank/rechargeRank', '', 'search', '100', '1', '', '1', '2015-03-06 14:08:03', '1', '2015-03-06 14:08:03', null, '0');
INSERT INTO sys_menu VALUES ('d071453a615e4dc1a4a238f75db83d73', '974d495a4bcb4311ab5d2b89e2c6fda2', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,', '查询系统', 'query.system', '', '', '', '60', '1', '', '1', '2015-06-24 14:24:03', '1', '2015-06-24 14:24:19', null, '0');
INSERT INTO sys_menu VALUES ('d0dad1d1b8f24b0b8fcf120cb6e9ae5d', '6796f5156a694f4ea1b8218fcec3ac29', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,6796f5156a694f4ea1b8218fcec3ac29,', '查看', 'log.propConsume.view', '', '', '', '30', '1', 'log.propConsume.view', '1', '2015-01-28 21:01:55', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('d2f4e993befb432ca4ba1711610426ab', '8761da76074c42d2b6351de38fdf8360', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,8761da76074c42d2b6351de38fdf8360,', '查看', 'game.role.view', '', '', '', '30', '1', 'game.role.view', '1', '2015-01-28 20:59:33', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('d379ffd3b03f4013abfc439958ff8d4f', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '星盘等级分布', 'star.rank', '/log/star/starRankList', '', 'search', '120', '1', '', '1', '2015-08-26 15:44:17', '1', '2015-08-26 15:47:18', null, '0');
INSERT INTO sys_menu VALUES ('d3d557729b8a4827bab39304d374a753', '2a9181577f8e46dca445a31e0d6e7002', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,2a9181577f8e46dca445a31e0d6e7002,', '游戏发布说明', 'release.note', '/tools/release', '', 'search', '40', '1', '', '1', '2015-06-04 16:08:30', '1', '2015-06-04 16:08:30', null, '0');
INSERT INTO sys_menu VALUES ('d4de81176a6241429c4bcb4c5e663649', '2a83e3dfec0542948353bb71504c4e82', '0,1,0b8f61959129420bac915bb68c9f56cb,2a83e3dfec0542948353bb71504c4e82,', '封号禁言日志', 'silence.freeze.log', '/game/role/silenceFreezeLog', '', 'th-large', '30', '1', '', '1', '2014-11-17 15:28:29', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('d5117230f69042d5a1a64acced76e1d0', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '道具获取日志', 'gain.prop', '/log/propController/propGainList', '', 'search', '40', '1', '', '1', '2014-10-22 17:16:31', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('d7119b11d26a4fd0bbb77596900b0110', 'ebed66010adc43babf135c819d23151d', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,ebed66010adc43babf135c819d23151d,', 'IP黑名单', 'blacklist.ip', '/tools/black/ipSilenceFreeze', '', 'search', '30', '1', '', '1', '2015-06-19 14:48:10', '1', '2015-06-19 14:48:10', null, '0');
INSERT INTO sys_menu VALUES ('d72e11d78521435b8958597b125ac878', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '元宝统计', 'treasure.stat', '/log/moneyFlow/treasureProduce', '', 'search', '140', '1', '', '1', '2015-06-16 16:17:02', '1', '2015-06-16 16:29:16', null, '0');
INSERT INTO sys_menu VALUES ('dafa292225cf4137a0dec8547539fc1e', '9675bf6383d142e5b56cdc8017027ea2', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,9675bf6383d142e5b56cdc8017027ea2,', '导出', 'log.moneyGain.export', '', '', '', '30', '1', 'log.moneyGain.export', '1', '2015-01-28 19:58:39', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('dc8a8501a6e7452e96b754e122515f19', '8a4686e4c124450ea0f11de26957349c', '0,1,ba8d1fffc0be41a08445ca3343c15669,8a4686e4c124450ea0f11de26957349c,', '防沉迷配置', 'fangchenmi.config', '/tools/config/chenmi', '', 'search', '30', '1', '', '1', '2015-07-15 16:03:00', '1', '2015-07-15 16:03:00', null, '0');
INSERT INTO sys_menu VALUES ('de70ca9efc63461aa17dfeabe1b37608', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '消费排行统计', 'stat.consume.rank', '/log/rank/consumeRank', '', 'search', '110', '1', '', '1', '2015-03-06 14:09:49', '1', '2015-03-06 14:09:49', null, '0');
INSERT INTO sys_menu VALUES ('df6b38cb21a04feb85e06e5ad222b0a8', '455d3e03f56044288760a4eacc650673', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,', '多选礼包查询', 'treasure.box.log', '/log/treasure/treasureBox', '', 'search', '130', '1', '', '1', '2015-06-04 19:03:18', '1', '2015-06-04 19:03:18', null, '0');
INSERT INTO sys_menu VALUES ('e19a2e2c5adc43a09ccfc64c04002800', '1', '0,1,', '开发工具', 'develop.tool', '', '', '', '9100', '1', '', '1', '2015-08-27 16:41:36', '1', '2015-08-27 16:41:36', null, '0');
INSERT INTO sys_menu VALUES ('e2e1dae988e24ba0b8c7324c844f64bd', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '月消费数据(绑元)', 'monthly.consume.banding.data', '/global/rechargeConsume/monthBindConsumeReport', '', 'th-large', '100', '1', '', '1', '2014-12-16 15:05:47', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('e2fc6cb8dc1342e2bb074369bf79a964', '4558e89d0aae483db618117f2d60d28e', '0,1,ba8d1fffc0be41a08445ca3343c15669,4558e89d0aae483db618117f2d60d28e,', '活动名配置', 'activity.name.conf', '/tools/activity/activityConfigList', '', 'search', '30', '1', '', '1', '2015-03-26 09:38:14', '1', '2015-03-26 09:38:14', null, '0');
INSERT INTO sys_menu VALUES ('e3881367206a451090b7d8f78799ea73', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '坐骑分布', 'horse.upgrade', '/log/horseUpgrade/horseUpgradeList', '', 'search', '80', '1', '', '1', '2015-08-05 16:01:38', '1', '2015-08-05 16:01:38', null, '0');
INSERT INTO sys_menu VALUES ('e4229bb663594185b347dc0c3c25c11d', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '礼包生成', 'tools.gift.base', '/tools/gift/giftBaseList', '', 'th-large', '70', '1', '', '1', '2015-02-06 13:58:41', '1', '2015-02-06 17:00:27', '', '0');
INSERT INTO sys_menu VALUES ('e4b15b5466ff4d38b617aa8a0bcdbdce', 'a8b59f132bb54231b205e31e9c1b9ae0', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,a8b59f132bb54231b205e31e9c1b9ae0,', '公会删除', 'game.guild.delete', '', '', '', '30', '1', 'game.guild.delete', '1', '2015-01-28 20:18:45', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('e51e67017ce74b53a46599f0898f8186', '38519c0af4a749aab25570fbab9530e1', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,38519c0af4a749aab25570fbab9530e1,', '批量补偿', 'game.email.batchadd', '', '', '', '30', '1', 'game.email.batchadd', '1', '2014-11-13 11:33:50', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('e54d812f0d304cafb8509722c358b972', 'ed7788e32a664a3aadb0baf8fe9049c4', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,', '基础运营数据', 'base.business.data', '', '', '', '40', '1', '', '1', '2014-12-12 13:53:31', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('e5652fe30e04473194bf6f80eb904380', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '注册转化(统计)', 'daily.reg.converation.rate', '/global/basicOperation/regConvertionTotal', '', 'th-large', '40', '1', '', '1', '2014-12-12 13:56:38', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('e9a6e97b802e4eaaaad609e0c270bcff', 'c088d87e488d4ee2bbb1b0693f486a7f', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,c088d87e488d4ee2bbb1b0693f486a7f,', '元宝申请（特别）', 'game.recharge.add', '', '', '', '30', '1', 'game.recharge.add', '1', '2015-08-18 20:30:14', '1', '2015-08-18 20:30:14', null, '0');
INSERT INTO sys_menu VALUES ('eba32c46d1504ccba71cce50e3334e77', '52af42abfa84461ba86010c2aa43e476', '0,1,ba8d1fffc0be41a08445ca3343c15669,52af42abfa84461ba86010c2aa43e476,', '日消费统计', 'daily.consume.stat', '', '', 'th-large', '40', '1', '', '1', '2014-12-15 15:29:01', '1', '2015-02-06 17:00:27', null, '1');
INSERT INTO sys_menu VALUES ('ebed66010adc43babf135c819d23151d', '974d495a4bcb4311ab5d2b89e2c6fda2', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,', '黑名单管理', 'blacklist.manager', '', '', '', '30', '1', '', '1', '2015-06-19 14:44:54', '1', '2015-06-19 14:44:54', null, '0');
INSERT INTO sys_menu VALUES ('ed40eb3437a841e3a56b5f01ea3f3694', '786dbf71d4b44fd4a122969122f91cfa', '0,1,e19a2e2c5adc43a09ccfc64c04002800,786dbf71d4b44fd4a122969122f91cfa,', '代码执行', 'tool.code', '/tools/code/code', '', 'search', '30', '1', '', '1', '2015-08-27 16:46:02', '1', '2015-08-27 16:46:02', null, '0');
INSERT INTO sys_menu VALUES ('ed7788e32a664a3aadb0baf8fe9049c4', '1', '0,1,', '数据统计', 'data.statistics', '', '', '', '3000', '1', '', '1', '2014-11-27 16:08:58', '1', '2015-02-06 17:00:24', null, '0');
INSERT INTO sys_menu VALUES ('eebef1c765b44098b994e339cdc92e79', '72fc33098f174ca6a640233e81d0e862', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,72fc33098f174ca6a640233e81d0e862,', '新增修改', 'game.gm.edit', '', '', '', '30', '1', 'game.gm.edit', '1', '2015-01-28 16:37:35', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('efd96d53354f419481c85e4b3aebb10f', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '注册转化(分服)', 'reg.converation.rate', '/global/basicOperation/regConvertionRealTime', '', 'th-large', '30', '1', '', '1', '2014-12-12 13:56:08', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('f0eb46b2f7384ea6aa423a058a78e25a', 'be11fae8a7514053936c3e5ca6b18989', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,be11fae8a7514053936c3e5ca6b18989,', '查看', 'game.notice.view', '', '', '', '30', '1', 'game.notice.view', '1', '2015-01-28 20:57:03', '1', '2015-02-06 17:00:21', null, '0');
INSERT INTO sys_menu VALUES ('f348d53776ac4dcd885b6e5f53334093', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台充值消费统计', 'platform.recharge.consume.stat', '/global/platformRechargeConsume/platformRechargeConsume', '', 'th-large', '80', '1', '', '1', '2014-12-26 10:56:28', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('f3924dcde92b4f919703b50763a5b3b7', 'b5fb64eb8da2447e81a4a0ec5d4a57bf', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,b5fb64eb8da2447e81a4a0ec5d4a57bf,', '守护神殿统计', 'guard.temple', '/log/guard/guardTempleList', '', 'search', '150', '1', '', '1', '2015-09-22 10:43:50', '1', '2015-09-22 10:43:50', null, '0');
INSERT INTO sys_menu VALUES ('f428174a9379486ba8a96224d57b6bcd', 'd5117230f69042d5a1a64acced76e1d0', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,d5117230f69042d5a1a64acced76e1d0,', '导出', 'log.propGain.export', '', '', '', '30', '1', 'log.propGain.export', '1', '2015-01-28 19:31:42', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('f4b0047f66fc49e38afba0a8fa4da4e1', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '充值区间分布', 'recharge.range.distribution', '/global/rechargeConsume/rechargeDistribution', '', 'th-large', '60', '1', '', '1', '2014-12-16 15:01:59', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('f4e6d42544274572b7bf1e74fde3e2fc', 'fa6365affa0e40aa84f2a6db7e368dbe', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,fa6365affa0e40aa84f2a6db7e368dbe,', '查看', 'game.feedback.view', '', '', '', '30', '1', 'game.feedback.view', '1', '2015-01-28 21:00:29', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('f62d1596449243b29e58dbd074dcae4b', '9675bf6383d142e5b56cdc8017027ea2', '0,1,ba8605c15e5647709e6552006a843964,455d3e03f56044288760a4eacc650673,9675bf6383d142e5b56cdc8017027ea2,', '查看', 'log.moneyGain.view', '', '', '', '30', '1', 'log.moneyGain.view', '1', '2015-01-29 11:25:53', '1', '2015-02-06 17:00:23', null, '0');
INSERT INTO sys_menu VALUES ('f7038e1e7cb74432a0e0357ca7c21bdc', '8a4686e4c124450ea0f11de26957349c', '0,1,ba8d1fffc0be41a08445ca3343c15669,8a4686e4c124450ea0f11de26957349c,', '全局开关', 'global.switch', '/config/globalSwitch', '', 'search', '50', '1', '', '1', '2015-07-24 15:35:38', '1', '2015-07-24 15:35:38', null, '0');
INSERT INTO sys_menu VALUES ('f7589dd6dfd74cada5ae22efa7c28fae', '6446fa92acc64f14bfd1d06694850f43', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,6446fa92acc64f14bfd1d06694850f43,', '用户等级分布', 'user.level.distribution', '/log/roleUpgrade/levelDistribution', '', 'search', '40', '1', '', '1', '2015-02-03 10:24:56', '1', '2015-02-07 17:34:50', null, '0');
INSERT INTO sys_menu VALUES ('f8e04d1c3e9d4499a42bd3415d70bde6', '669a87df82d042049a2b2f0058f1d828', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,669a87df82d042049a2b2f0058f1d828,', '平台活跃统计', 'platform.active.stat', '/global/platformStatistics/platformActiveStatistics', '', 'th-large', '70', '1', '', '1', '2014-12-26 10:55:38', '1', '2015-02-06 17:00:26', null, '0');
INSERT INTO sys_menu VALUES ('fa6365affa0e40aa84f2a6db7e368dbe', '3146b18984134f099842932b301b7f3c', '0,1,0b8f61959129420bac915bb68c9f56cb,3146b18984134f099842932b301b7f3c,', '玩家反馈', 'player.feedback', '/tools/feedback', '', 'th-large', '70', '1', '', '1', '2014-11-20 14:17:02', '1', '2015-02-06 17:00:22', null, '0');
INSERT INTO sys_menu VALUES ('fb09bc240bfc4267937408f560f15441', 'e54d812f0d304cafb8509722c358b972', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,e54d812f0d304cafb8509722c358b972,', '月留存数据', 'month.remainer.data', '/global/monthRemainer/monthRemainerReport', '', 'th-large', '100', '1', '', '1', '2014-12-22 11:07:13', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('fbc785a47e1b4c1da0cc2bcf9f5ed510', '8f3f411d3a344198a18d22c4ea73ef05', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,8f3f411d3a344198a18d22c4ea73ef05,', '竞技场', 'pvp.pvpLog', '/log/pvp/pvpLogList', '', 'search', '60', '1', '', '1', '2015-08-27 15:36:27', '1', '2015-08-27 15:36:27', null, '0');
INSERT INTO sys_menu VALUES ('fca931b9b50c4f09835e0fca57c609a8', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '日消费统计(绑元)', 'daily.consume.banding.stat', '/global/rechargeConsume/dailyBindConsumeReport', '', 'th-large', '50', '1', '', '1', '2014-12-16 14:59:58', '1', '2015-02-06 17:00:25', null, '0');
INSERT INTO sys_menu VALUES ('fd03f401735245f2b6e41e174a07f3ac', 'd071453a615e4dc1a4a238f75db83d73', '0,1,974d495a4bcb4311ab5d2b89e2c6fda2,d071453a615e4dc1a4a238f75db83d73,', 'SQL查询', 'sql.query', '/tools/query', '', 'search', '30', '1', '', '1', '2015-06-24 14:26:40', '1', '2015-06-24 14:26:40', null, '0');
INSERT INTO sys_menu VALUES ('fd741b5b33944fd4a4e49c853b658459', '5892f68ce8d248bb84e2f27eaaadbe7e', '0,1,ed7788e32a664a3aadb0baf8fe9049c4,5892f68ce8d248bb84e2f27eaaadbe7e,', '月充值数据', 'monthly.recharge.data', '/global/rechargeConsume/monthlyRecharge', '', 'th-large', '80', '1', '', '1', '2014-12-16 15:03:10', '1', '2015-02-06 17:00:25', null, '0');

-- ----------------------------
-- Table structure for `sys_office`
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `area_id` varchar(64) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` char(1) NOT NULL COMMENT '机构类型',
  `grade` char(1) NOT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_office_parent_ids` (`parent_ids`(255)) USING BTREE,
  KEY `sys_office_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO sys_office VALUES ('1', '0', '0,', '2', '100000', '深圳市肃羽科技有限公司', '1', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO sys_office VALUES ('10', '7', '0,1,7,', '8', '200003', '市场部', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('11', '7', '0,1,7,', '8', '200004', '技术部', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('12', '7', '0,1,7,', '9', '201000', '济南市分公司', '1', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('13', '12', '0,1,7,12,', '9', '201001', '公司领导', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('14', '12', '0,1,7,12,', '9', '201002', '综合部', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('15', '12', '0,1,7,12,', '9', '201003', '市场部', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('16', '12', '0,1,7,12,', '9', '201004', '技术部', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('17', '12', '0,1,7,12,', '11', '201010', '济南市历城区分公司', '1', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('18', '17', '0,1,7,12,17,', '11', '201011', '公司领导', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('19', '17', '0,1,7,12,17,', '11', '201012', '综合部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('1bf1cccd375445f599b21310be96138a', '8', '0,1,7,8,', '8', '200003', '策划部', '2', '2', '', '', '', '', '', '', '2', '2014-10-17 16:44:01', '2', '2014-10-20 10:42:46', '', '1');
INSERT INTO sys_office VALUES ('2', '1', '0,1,', '2', '100001', '公司领导', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO sys_office VALUES ('20', '17', '0,1,7,12,17,', '11', '201013', '市场部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('21', '17', '0,1,7,12,17,', '11', '201014', '技术部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('22', '12', '0,1,7,12,', '12', '201020', '济南市历下区分公司', '1', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('23', '22', '0,1,7,12,22,', '12', '201021', '公司领导', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('24', '22', '0,1,7,12,22,', '12', '201022', '综合部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('25', '22', '0,1,7,12,22,', '12', '201023', '市场部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('26', '22', '0,1,7,12,22,', '12', '201024', '技术部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('3', '1', '0,1,', '2', '100002', '人力部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO sys_office VALUES ('4', '1', '0,1,', '2', '100003', '市场部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO sys_office VALUES ('5', '1', '0,1,', '2', '100004', '技术部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO sys_office VALUES ('6', '1', '0,1,', '2', '100005', '研发部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO sys_office VALUES ('6a146b43f197498aa0ce0d1cdea51a5a', '7', '0,1,7,', '8', '200003', '策划部', '2', '1', '', '', '', '', '', '', '2', '2014-10-17 16:44:40', '2', '2014-10-20 10:42:46', '', '1');
INSERT INTO sys_office VALUES ('7', '1', '0,1,', '0896d017434f4744bc1e6cbb4774fff0', '200000', '肃羽科技', '1', '2', '', '', '', '', '', '', '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', '', '1');
INSERT INTO sys_office VALUES ('8', '7', '0,1,7,', '8', '200001', '公司领导', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');
INSERT INTO sys_office VALUES ('9', '7', '0,1,7,', '8', '200002', '综合部', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-10-20 10:42:46', null, '1');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `is_global` char(1) DEFAULT NULL COMMENT '是否全平台权限',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role VALUES ('1', '1', '系统管理员', '1', '1', '1', '2013-05-27 08:00:00', '2', '2014-10-31 17:37:47', '', '0');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO sys_role_menu VALUES ('1', '0b8f61959129420bac915bb68c9f56cb');
INSERT INTO sys_role_menu VALUES ('1', '1');
INSERT INTO sys_role_menu VALUES ('1', '10');
INSERT INTO sys_role_menu VALUES ('1', '11');
INSERT INTO sys_role_menu VALUES ('1', '12');
INSERT INTO sys_role_menu VALUES ('1', '13');
INSERT INTO sys_role_menu VALUES ('1', '14');
INSERT INTO sys_role_menu VALUES ('1', '15');
INSERT INTO sys_role_menu VALUES ('1', '16');
INSERT INTO sys_role_menu VALUES ('1', '17');
INSERT INTO sys_role_menu VALUES ('1', '18');
INSERT INTO sys_role_menu VALUES ('1', '19');
INSERT INTO sys_role_menu VALUES ('1', '2');
INSERT INTO sys_role_menu VALUES ('1', '20');
INSERT INTO sys_role_menu VALUES ('1', '21');
INSERT INTO sys_role_menu VALUES ('1', '22');
INSERT INTO sys_role_menu VALUES ('1', '27');
INSERT INTO sys_role_menu VALUES ('1', '28');
INSERT INTO sys_role_menu VALUES ('1', '28d5c02fd98b43f19a38555616b6103a');
INSERT INTO sys_role_menu VALUES ('1', '29');
INSERT INTO sys_role_menu VALUES ('1', '3');
INSERT INTO sys_role_menu VALUES ('1', '30');
INSERT INTO sys_role_menu VALUES ('1', '3cb8326e252d4e558401a28a04e0d944');
INSERT INTO sys_role_menu VALUES ('1', '4');
INSERT INTO sys_role_menu VALUES ('1', '455d3e03f56044288760a4eacc650673');
INSERT INTO sys_role_menu VALUES ('1', '5');
INSERT INTO sys_role_menu VALUES ('1', '6');
INSERT INTO sys_role_menu VALUES ('1', '67');
INSERT INTO sys_role_menu VALUES ('1', '6796f5156a694f4ea1b8218fcec3ac29');
INSERT INTO sys_role_menu VALUES ('1', '68');
INSERT INTO sys_role_menu VALUES ('1', '7');
INSERT INTO sys_role_menu VALUES ('1', '8');
INSERT INTO sys_role_menu VALUES ('1', '9');
INSERT INTO sys_role_menu VALUES ('1', 'ba8605c15e5647709e6552006a843964');
INSERT INTO sys_role_menu VALUES ('1', 'd5117230f69042d5a1a64acced76e1d0');

-- ----------------------------
-- Table structure for `sys_role_office`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_office`;
CREATE TABLE `sys_role_office` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `office_id` varchar(64) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';

-- ----------------------------
-- Records of sys_role_office
-- ----------------------------
INSERT INTO sys_role_office VALUES ('7', '10');
INSERT INTO sys_role_office VALUES ('7', '11');
INSERT INTO sys_role_office VALUES ('7', '12');
INSERT INTO sys_role_office VALUES ('7', '13');
INSERT INTO sys_role_office VALUES ('7', '14');
INSERT INTO sys_role_office VALUES ('7', '15');
INSERT INTO sys_role_office VALUES ('7', '16');
INSERT INTO sys_role_office VALUES ('7', '17');
INSERT INTO sys_role_office VALUES ('7', '18');
INSERT INTO sys_role_office VALUES ('7', '19');
INSERT INTO sys_role_office VALUES ('7', '20');
INSERT INTO sys_role_office VALUES ('7', '21');
INSERT INTO sys_role_office VALUES ('7', '22');
INSERT INTO sys_role_office VALUES ('7', '23');
INSERT INTO sys_role_office VALUES ('7', '24');
INSERT INTO sys_role_office VALUES ('7', '25');
INSERT INTO sys_role_office VALUES ('7', '26');
INSERT INTO sys_role_office VALUES ('7', '7');
INSERT INTO sys_role_office VALUES ('7', '8');
INSERT INTO sys_role_office VALUES ('7', '9');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `company_id` varchar(64) NOT NULL COMMENT '归属公司',
  `office_id` varchar(64) NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`) USING BTREE,
  KEY `sys_user_login_name` (`login_name`) USING BTREE,
  KEY `sys_user_company_id` (`company_id`) USING BTREE,
  KEY `sys_user_update_date` (`update_date`) USING BTREE,
  KEY `sys_user_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user VALUES ('0ece0c17f75a4729862aacb212253dfc', '1', '1', 'leo', 'e860d7ce955641f3a7ad243ff81fba3ec59805bcad61805b0247fcf4', null, null, '', '', '', null, '192.168.1.80', '2016-07-15 12:20:02', '1', '2016-07-15 12:19:39', '1', '2016-07-15 12:24:35', '最高管理员', '0');
INSERT INTO sys_user VALUES ('1', '1', '1', 'mars', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0001', '稻草鸟人', '75999267@qq.com', '8675', '8675', null, '192.168.1.247', '2016-08-06 22:01:25', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '最高管理员', '0');
INSERT INTO sys_user VALUES ('e1cef47aee644cbe8a8d9947da9c15c0', '1', '1', 'test', 'f87fb6f2508a65037c5e3fdb2d089b1a6944d9fb2b701aec8b7a7194', null, null, '', '', '', null, null, null, '1', '2016-07-29 15:55:05', '1', '2016-07-29 15:55:05', '测试', '0');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role VALUES ('0ece0c17f75a4729862aacb212253dfc', '1');
INSERT INTO sys_user_role VALUES ('1', '1');
INSERT INTO sys_user_role VALUES ('e1cef47aee644cbe8a8d9947da9c15c0', '1');
