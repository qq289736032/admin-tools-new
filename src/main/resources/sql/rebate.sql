/*
Navicat MySQL Data Transfer

Source Server         : 95
Source Server Version : 50630
Source Host           : 192.168.1.95:3306
Source Database       : ops_tools_minshuguo

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2016-12-15 09:29:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `rebate_goods`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_goods`;
CREATE TABLE `rebate_goods` (
  `id` varchar(32) NOT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `goods_desc` varchar(255) DEFAULT NULL,
  `goods_price` int(11) DEFAULT NULL,
  `goods_prop` tinyint(1) DEFAULT NULL COMMENT '物品属性（0：绑定 1不绑定）',
  `exchange_limit` tinyint(1) DEFAULT NULL COMMENT '兑换限制（0：无 1：单角色每月上限 2：单角色累积上限）',
  `top_num` int(11) DEFAULT NULL COMMENT '上限数量',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `rebate_goods_log`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_goods_log`;
CREATE TABLE `rebate_goods_log` (
  `id` varchar(32) NOT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `goods_desc` varchar(255) DEFAULT NULL,
  `goods_price` int(11) DEFAULT NULL,
  `goods_prop` tinyint(1) DEFAULT NULL COMMENT '物品属性（0：绑定 1不绑定）',
  `exchange_limit` tinyint(1) DEFAULT NULL COMMENT '兑换限制（0：无 1：单角色每月上限 2：单角色累积上限）',
  `top_num` int(11) DEFAULT NULL COMMENT '上限数量',
  `edit_type` varchar(20) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_goods_spec`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_goods_spec`;
CREATE TABLE `rebate_goods_spec` (
  `id` varchar(32) NOT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `goods_desc` varchar(255) DEFAULT NULL,
  `goods_price` int(11) DEFAULT NULL,
  `goods_prop` tinyint(1) DEFAULT NULL COMMENT '物品属性（0：绑定 1不绑定）',
  `exchange_limit` tinyint(1) DEFAULT NULL COMMENT '兑换限制（0：无 1：单角色每月上限 2：单角色累积上限）',
  `top_num` int(11) DEFAULT NULL COMMENT '上限数量',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_keep_day`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_keep_day`;
CREATE TABLE `rebate_keep_day` (
  `id` varchar(32) NOT NULL,
  `day` int(11) DEFAULT NULL COMMENT '保留天数',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rebate_keep_day
-- ----------------------------
INSERT INTO rebate_keep_day VALUES ('f731c53fe2664dd184ed63fac17a2daf', '3', '稻草鸟人', '1', '2016-11-22 04:58:18', '稻草鸟人', '2016-11-22 17:44:10', '0');

-- ----------------------------
-- Table structure for `rebate_keep_day_log`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_keep_day_log`;
CREATE TABLE `rebate_keep_day_log` (
  `id` varchar(32) NOT NULL,
  `day` int(11) DEFAULT NULL,
  `edit_type` varchar(20) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_opera_detail`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_opera_detail`;
CREATE TABLE `rebate_opera_detail` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `opera_log_id` varchar(32) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `goods_prop` varchar(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `sum_money` bigint(20) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_opera_log`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_opera_log`;
CREATE TABLE `rebate_opera_log` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `pid` varchar(32) DEFAULT NULL,
  `plat_name` varchar(32) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `server_name` varchar(32) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `gold` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `email_title` varchar(255) DEFAULT NULL,
  `email_text` text,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_ratio`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_ratio`;
CREATE TABLE `rebate_ratio` (
  `id` varchar(32) NOT NULL,
  `day_amount` bigint(20) DEFAULT NULL,
  `rebate_ratio` int(11) DEFAULT NULL COMMENT '返利比例',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_ratio_log`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_ratio_log`;
CREATE TABLE `rebate_ratio_log` (
  `id` varchar(32) NOT NULL,
  `day_amount` bigint(20) DEFAULT NULL,
  `rebate_ratio` int(11) DEFAULT NULL COMMENT '返利比例',
  `edit_type` varchar(20) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_recharge`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_recharge`;
CREATE TABLE `rebate_recharge` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '角色账号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
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
  `pid` varchar(200) DEFAULT NULL,
  `server_name` varchar(200) DEFAULT NULL,
  `plat_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `rebate_statistic_recharge`
-- ----------------------------
DROP TABLE IF EXISTS `rebate_statistic_recharge`;
CREATE TABLE `rebate_statistic_recharge` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `statistic_date` date DEFAULT NULL COMMENT '统计日期：（2016-12-05）',
  `sum_money` bigint(20) DEFAULT NULL COMMENT '合计金额',
  `recharge_times` int(11) DEFAULT NULL COMMENT '充值次数',
  `rebate_ratio` int(11) DEFAULT NULL COMMENT '返还比例',
  `rebate_gold` bigint(20) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  `server_id` varchar(200) DEFAULT NULL COMMENT '服务器ID',
  `pid` varchar(200) DEFAULT NULL,
  `plat_name` varchar(200) DEFAULT NULL,
  `server_name` varchar(200) DEFAULT NULL,
  `role_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

