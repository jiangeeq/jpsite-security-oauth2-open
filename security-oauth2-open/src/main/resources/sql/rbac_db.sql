/*
Navicat MySQL Data Transfer

Source Server         : 192.168.104.102
Source Server Version : 80017
Source Host           : 192.168.104.102:3306
Source Database       : rbac_db

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-10-17 17:16:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ClientDetails
-- ----------------------------
DROP TABLE IF EXISTS `ClientDetails`;
CREATE TABLE `ClientDetails` (
  `appId` varchar(128) COLLATE utf8_bin NOT NULL,
  `resourceIds` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `appSecret` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `grantTypes` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `redirectUrl` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `authorities` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `autoApproveScopes` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ClientDetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `client_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `clientId` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `resource_ids` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `scope` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `authorized_grant_types` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `web_server_redirect_uri` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `authorities` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8_bin DEFAULT NULL,
  `autoapprove` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `client_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) NOT NULL,
  `permName` varchar(50) DEFAULT NULL,
  `permTag` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '查询订单', 'showOrder', '/showOrder');
INSERT INTO `sys_permission` VALUES ('2', '添加订单', 'addOrder', '/addOrder');
INSERT INTO `sys_permission` VALUES ('3', '修改订单', 'updateOrder', '/updateOrder');
INSERT INTO `sys_permission` VALUES ('4', '删除订单', 'deleteOrder', '/deleteOrder');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'add_user', '添加管理员');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(10) DEFAULT NULL,
  `perm_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`perm_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `lastLoginTime` date DEFAULT NULL,
  `enabled` int(5) DEFAULT NULL,
  `accountNonExpired` int(5) DEFAULT NULL,
  `accountNonLocked` int(5) DEFAULT NULL,
  `credentialsNonExpired` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '张三', '$2a$10$7YJO.unJklmfILm5fV4kLugKPTZZjm/Zshc3HtBwoLsGoggcD2se.', '2018-11-13', '2018-11-13', '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', 'userAdd', '小余', '241ceff9ad10c4d359234872a9695faa', '2018-11-13', '2018-11-13', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
