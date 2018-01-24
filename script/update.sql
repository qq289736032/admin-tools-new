ALTER TABLE `game_area`
ADD COLUMN `server_version`  varchar(255) NULL AFTER `open_time`;


ALTER TABLE `game_email`
ADD COLUMN `create_user_pid`  varchar(255) NULL AFTER `create_by`;

ALTER TABLE `game_area`
ADD COLUMN `combine_time`  timestamp NULL  COMMENT '合服时间';


CREATE TABLE `release_note` (
  `id` varchar(255) DEFAULT NULL,
  `server_ids` text,
  `version_id` varchar(200) DEFAULT NULL,
  `version_time` datetime DEFAULT NULL,
  `release_note` text,
  `release_content` text,
  `status` char(1) DEFAULT NULL COMMENT '是否发布，1表示已发布，0表示未发布',
  `create_name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `sql_history` (
  `id` varchar(64) DEFAULT NULL,
  `sql` text,
  `create_by` varchar(64) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `virtual_point` (
  `id` varchar(64) NOT NULL,
  `global` int(1) DEFAULT NULL,
  `server_ids` text,
  `role_ids` longtext COMMENT '玩家角色编号',
  `role_names` longtext,
  `goods` text,
  `create_name` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人-申请人',
  `approve_by` varchar(64) DEFAULT NULL COMMENT '确认发送人-审批人',
  `approve_name` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT '0' COMMENT '0审批中 1已发送 2已取消',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `virtual_config` (
  `id` varchar(255) NOT NULL,
  `item_id` varchar(200) DEFAULT NULL COMMENT '物品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '中文名',
  `create_name` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `role_silence_freeze_log`
ADD COLUMN `pid`  varchar(255) NULL AFTER `id`;


ALTER TABLE `game_email`
ADD COLUMN `pid`  varchar(255) NULL AFTER `id`;

ALTER TABLE `recharge`
ADD COLUMN `pid`  varchar(255) NULL AFTER `money_num`;

ALTER TABLE `vip_qq`
ADD COLUMN `month_min_recharge`  varchar(255) NULL AFTER `status`;


CREATE TABLE `recharge2` (
  `id` varchar(64) NOT NULL,
  `user_ids` varchar(64) DEFAULT NULL COMMENT '角色账号',
  `role_ids` text COMMENT '角色ID',
  `role_names` text COMMENT '角色名',
  `money_type` char(1) DEFAULT NULL COMMENT '货币类型',
  `money_num` int(11) DEFAULT NULL COMMENT '充值数量',
  `pid` varchar(255) DEFAULT NULL,
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


ALTER TABLE `activity_list`
MODIFY COLUMN `server_ids`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '服务器列表' AFTER `is_global`;

CREATE TABLE `vip_qq` (
  `id` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `server_ids` longtext,
  `qq` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `min_recharge` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `month_min_recharge` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `game_email`
MODIFY COLUMN `delay_hours`  int(11) NULL DEFAULT 0 COMMENT '延迟发送时间' AFTER `yb`,
ADD COLUMN `receive_time`  datetime NULL COMMENT '自定义领取时间' AFTER `delay_hours`;

ALTER TABLE `sys_dict`
ADD COLUMN `international_key`  varchar(255) NULL  COMMENT '国际化key' AFTER `label`;

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

CREATE TABLE `code_log` (
  `id` varchar(64) NOT NULL,
  `is_global` char(1) NOT NULL DEFAULT '0',
  `serverIds` text,
  `code` text,
  `create_name` varchar(64) NOT NULL,
  `create_by` varchar(64) NOT NULL,
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table role_silence_freeze_log modify column role_id varchar(255);