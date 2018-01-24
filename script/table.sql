/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : cabal_test

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2015-05-19 17:01:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity_config
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
-- Table structure for activity_list
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
-- Table structure for analyze_file
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
-- Table structure for feed_back
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
-- Table structure for game_area
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
-- Table structure for game_email
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
-- Table structure for game_notice
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
-- Table structure for game_platform
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
-- Table structure for game_server
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
-- Table structure for gift_base
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
-- Table structure for gift_code_0
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
-- Table structure for gift_code_1
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
-- Table structure for gift_create_code
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
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='礼包生成表';

-- ----------------------------
-- Table structure for gm_account
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
-- Table structure for maintain_task_definition
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
-- Table structure for monitor_config
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
-- Table structure for month_revenue_estimate
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for recharge
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
-- Table structure for role_game_platform
-- ----------------------------
DROP TABLE IF EXISTS `role_game_platform`;
CREATE TABLE `role_game_platform` (
  `role_id` varchar(64) NOT NULL DEFAULT '',
  `game_platform_id` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`game_platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_silence_freeze_log
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
-- Table structure for sys_area
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
-- Table structure for sys_daoliang
-- ----------------------------
DROP TABLE IF EXISTS `sys_daoliang`;
CREATE TABLE `sys_daoliang` (
  `id` varchar(64) NOT NULL,
  `cpa` int(11) NOT NULL,
  `pid` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
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
-- Table structure for sys_log
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
-- Table structure for sys_mdict
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
-- Table structure for sys_menu
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
-- Table structure for sys_office
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
-- Table structure for sys_role
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
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Table structure for sys_role_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_office`;
CREATE TABLE `sys_role_office` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `office_id` varchar(64) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';

-- ----------------------------
-- Table structure for sys_user
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
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Table structure for game_command_log
-- ----------------------------
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

ALTER TABLE `sys_dict`
ADD COLUMN `international_key`  varchar(255) NULL AFTER `label`;