/*
Navicat MySQL Data Transfer

Source Server         : 95
Source Server Version : 50630
Source Host           : 192.168.1.95:3306
Source Database       : ops_tools_minshuguo

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2016-12-15 09:32:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `opera_gold_log`
-- ----------------------------
DROP TABLE IF EXISTS `opera_gold_log`;
CREATE TABLE `opera_gold_log` (
  `id` varchar(32) NOT NULL,
  `passageway` varchar(255) NOT NULL COMMENT '通道',
  `plat_name` varchar(255) DEFAULT NULL,
  `server_name` varchar(32) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `gold` bigint(20) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `plat_welfare_config`
-- ----------------------------
DROP TABLE IF EXISTS `plat_welfare_config`;
CREATE TABLE `plat_welfare_config` (
  `id` varchar(32) NOT NULL,
  `plat_name` varchar(255) DEFAULT NULL COMMENT '平台名',
  `plat_nature` varchar(255) DEFAULT NULL COMMENT '平台性质',
  `gold_pool_category` varchar(64) DEFAULT NULL COMMENT '奖金池性质',
  `new_service_gold` bigint(20) DEFAULT NULL COMMENT '新服资源（元宝）',
  `r_resource_amount` bigint(20) DEFAULT NULL COMMENT '大R资源金额(rmb)',
  `r_resource_ratio` int(11) DEFAULT NULL COMMENT '大R资源比例',
  `single_charge_ratio` int(11) DEFAULT NULL COMMENT '单服总充值后续比例',
  `top_charge` bigint(20) DEFAULT NULL COMMENT '内部号最高充值限制(rmb)',
  `top_gold_day` bigint(20) DEFAULT NULL COMMENT '单角色每日最高限额(元宝)',
  `top_hold_gold` bigint(20) DEFAULT NULL COMMENT '单角色持有最高额度(元宝)',
  `top_internal_number` bigint(20) DEFAULT NULL COMMENT '单区内部号数量上限(个)',
  `add_time_limit` int(11) DEFAULT NULL COMMENT '内部号添加时间限制(天)',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（0：正常 1：暂停）',
  `pid` varchar(32) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `return_resource`
-- ----------------------------
DROP TABLE IF EXISTS `return_resource`;
CREATE TABLE `return_resource` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `dr_amount` bigint(20) DEFAULT NULL COMMENT '大R充值返回奖金池多少资源',
  `single_server_amount` bigint(20) DEFAULT NULL COMMENT '单服总充值返回奖金池多少资源',
  `pid` varchar(32) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `single_account_log`
-- ----------------------------
DROP TABLE IF EXISTS `single_account_log`;
CREATE TABLE `single_account_log` (
  `id` varchar(32) NOT NULL,
  `top_charge` bigint(20) DEFAULT NULL,
  `edit_type` varchar(20) DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `is_influence` varchar(20) DEFAULT NULL COMMENT '是否受批量修改影响',
  `pid` varchar(255) DEFAULT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  `server_id` varchar(255) DEFAULT NULL,
  `server_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `single_server_config`
-- ----------------------------
DROP TABLE IF EXISTS `single_server_config`;
CREATE TABLE `single_server_config` (
  `id` varchar(32) NOT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `new_service_gold` bigint(20) DEFAULT NULL COMMENT '新服资源(元宝)',
  `r_resource_amount` bigint(20) DEFAULT NULL COMMENT '大R资源金额(rmb)',
  `r_resource_ratio` int(11) DEFAULT NULL COMMENT '大R资源比例',
  `single_charge_ratio` int(11) DEFAULT NULL COMMENT '单服总充值后续比例',
  `top_charge` bigint(20) DEFAULT NULL COMMENT '内部号最高充值限制(rmb)',
  `top_gold_day` bigint(20) DEFAULT NULL COMMENT '单角色每日最高额度(元宝)',
  `top_hold_gold` bigint(20) DEFAULT NULL COMMENT '单角色持有最高额度(元宝)',
  `top_internal_number` bigint(20) DEFAULT NULL COMMENT '单区内部号数量上限(个)',
  `add_time_limit` int(11) DEFAULT NULL COMMENT '内部号添加时间限制(天)',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态(0：生效，1：不生效)',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `is_influence` tinyint(1) DEFAULT NULL COMMENT '是否受批量修改影响',
  `plat_name` varchar(255) DEFAULT NULL,
  `server_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `single_server_log`
-- ----------------------------
DROP TABLE IF EXISTS `single_server_log`;
CREATE TABLE `single_server_log` (
  `id` varchar(32) NOT NULL,
  `new_service_gold` bigint(20) DEFAULT NULL,
  `r_resource_amount` bigint(20) DEFAULT NULL,
  `r_resource_ratio` int(11) DEFAULT NULL,
  `single_charge_ratio` int(11) DEFAULT NULL,
  `top_charge` bigint(20) DEFAULT NULL,
  `top_gold_day` bigint(20) DEFAULT NULL,
  `top_hold_gold` bigint(20) DEFAULT NULL,
  `top_internal_number` bigint(20) DEFAULT NULL,
  `add_time_limit` int(11) DEFAULT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `server_name` varchar(32) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `edit_type` varchar(20) DEFAULT NULL COMMENT '操作类型',
  `is_influence` varchar(20) DEFAULT NULL COMMENT '是否受批量修改影响',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `welfare_common_temp`
-- ----------------------------
DROP TABLE IF EXISTS `welfare_common_temp`;
CREATE TABLE `welfare_common_temp` (
  `id` varchar(32) NOT NULL,
  `plat_nature` varchar(64) DEFAULT NULL COMMENT '公会性质',
  `new_service_gold` bigint(20) DEFAULT NULL COMMENT '新服资源（元宝）',
  `r_resource_amount` bigint(20) DEFAULT NULL COMMENT '大R资源金额(rmb)',
  `r_resource_ratio` int(11) DEFAULT NULL COMMENT '大R资源比例',
  `single_charge_ratio` int(11) DEFAULT NULL COMMENT '单服总充值后续比例',
  `top_charge` bigint(20) DEFAULT NULL COMMENT '内部号最高充值限制(rmb)',
  `top_gold_day` bigint(20) DEFAULT NULL COMMENT '单角色每日最高限额(元宝)',
  `top_hold_gold` bigint(20) DEFAULT NULL COMMENT '单角色持有最高额度(元宝)',
  `top_internal_number` bigint(20) DEFAULT NULL COMMENT '单区内部号数量上限(个)',
  `add_time_limit` int(11) DEFAULT NULL COMMENT '内部号添加时间限制(天)',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of welfare_common_temp
-- ----------------------------
INSERT INTO welfare_common_temp VALUES ('1', '网盟', '1', '1', '1', '3', '2', '4', '5', '6', '8', '1', '1', '2016-11-08 17:14:56', '稻草鸟人', '2012-11-23 23:49:15', '0');

-- ----------------------------
-- Table structure for `welfare_num`
-- ----------------------------
DROP TABLE IF EXISTS `welfare_num`;
CREATE TABLE `welfare_num` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `use_people` varchar(255) DEFAULT NULL COMMENT '使用人',
  `use_people_post` varchar(255) DEFAULT NULL COMMENT '使用人职务',
  `passageway` varchar(255) DEFAULT NULL COMMENT '通道',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态（0：正常 1：封停）',
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `server_name` varchar(32) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  `top_charge` bigint(20) DEFAULT NULL COMMENT '最高充值限制',
  `is_influence` tinyint(1) DEFAULT NULL COMMENT '是否受批量修改影响',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `welfare_num_log`
-- ----------------------------
DROP TABLE IF EXISTS `welfare_num_log`;
CREATE TABLE `welfare_num_log` (
  `id` varchar(32) NOT NULL,
  `plat_name` varchar(255) DEFAULT NULL,
  `server_id` varchar(32) DEFAULT NULL,
  `server_name` varchar(32) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `use_people` varchar(255) DEFAULT NULL,
  `use_people_post` varchar(255) DEFAULT NULL,
  `edit_type` tinyint(1) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `welfare_setting_log`
-- ----------------------------
DROP TABLE IF EXISTS `welfare_setting_log`;
CREATE TABLE `welfare_setting_log` (
  `id` varchar(32) NOT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `plat_name` varchar(32) DEFAULT NULL,
  `plat_nature` varchar(64) DEFAULT NULL,
  `new_service_gold` bigint(20) DEFAULT NULL,
  `r_resource_amount` bigint(20) DEFAULT NULL,
  `r_resource_ratio` int(11) DEFAULT NULL,
  `single_charge_ratio` int(11) DEFAULT NULL,
  `top_charge` bigint(20) DEFAULT NULL,
  `top_gold_day` bigint(20) DEFAULT NULL,
  `top_hold_gold` bigint(20) DEFAULT NULL,
  `top_internal_number` bigint(20) DEFAULT NULL,
  `add_time_limit` int(11) DEFAULT NULL,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `edit_type` tinyint(1) DEFAULT NULL,
  `gold_pool_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

