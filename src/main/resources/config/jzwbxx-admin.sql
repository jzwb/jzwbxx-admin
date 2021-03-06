/*
Navicat MySQL Data Transfer

Source Server         : mac-mysql
Source Server Version : 50642
Source Host           : 192.168.31.90:3306
Source Database       : jzwbxx-admin

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2020-05-20 23:48:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '', '', '2020-01-01 00:00:00', '2020-03-19 23:13:56');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role` (
  `admins` bigint(20) NOT NULL,
  `roles` bigint(20) NOT NULL,
  PRIMARY KEY (`admins`,`roles`) USING BTREE,
  KEY `roles` (`roles`) USING BTREE,
  KEY `admins` (`admins`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `content` longtext,
  `ip` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `parameter` longtext,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `create_date` (`create_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `tree_path` varchar(255) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `orders` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '2020-03-04 22:07:31', '2020-05-20 23:12:33', '系统管理', '', 'fa fa-gear', '_self', '0', ',', '', null, '2');
INSERT INTO `sys_menu` VALUES ('2', '2020-03-04 22:08:44', '2020-05-20 23:36:02', '管理员管理', '/admin/admin/index/', 'fa fa-users', '_self', '1', ',1,', 'admin:admin', '1', '9999');
INSERT INTO `sys_menu` VALUES ('3', '2020-03-04 22:09:34', '2020-05-20 23:12:33', '角色管理', '/admin/role/index/', 'fa fa-users', '_self', '1', ',1,', 'admin:role', '1', '9999');
INSERT INTO `sys_menu` VALUES ('4', '2020-03-04 22:09:34', '2020-05-20 23:12:33', '菜单管理', '/admin/menu/index/', 'fa fa-window-maximize', '_self', '1', ',1,', 'admin:menu', '1', '9999');
INSERT INTO `sys_menu` VALUES ('5', '2020-03-04 22:09:34', '2020-05-20 23:12:33', '存储插件管理', '/admin/storage_plugin/index/', 'fa fa-align-justify', '_self', '1', ',1,', 'admin:storage_plugin', '1', '9999');
INSERT INTO `sys_menu` VALUES ('6', '2020-05-20 23:10:41', '2020-05-20 23:35:52', '用户管理', '/admin/user/index/', 'fa fa-user', '_self', '0', ',', '', null, '1');

-- ----------------------------
-- Table structure for sys_plugin_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_plugin_config`;
CREATE TABLE `sys_plugin_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `is_enabled` tinyint(1) NOT NULL,
  `plugin_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `plugin_id` (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_plugin_config
-- ----------------------------
INSERT INTO `sys_plugin_config` VALUES ('1', '2020-03-08 23:23:29', '2020-03-13 22:45:52', null, '1', 'filePlugin');

-- ----------------------------
-- Table structure for sys_plugin_config_attribute
-- ----------------------------
DROP TABLE IF EXISTS `sys_plugin_config_attribute`;
CREATE TABLE `sys_plugin_config_attribute` (
  `plugin_config_id` bigint(20) NOT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`plugin_config_id`,`name`),
  KEY `plugin_config_id` (`plugin_config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_plugin_config_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '2014-07-02 00:02:44', '2014-07-02 00:02:44', '拥有管理后台最高权限', '', '超级管理员');

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority` (
  `role_id` bigint(20) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES ('1', 'admin:admin');
INSERT INTO `sys_role_authority` VALUES ('1', 'admin:role');
INSERT INTO `sys_role_authority` VALUES ('1', 'admin:menu');
INSERT INTO `sys_role_authority` VALUES ('1', 'admin:storage_plugin');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `union_id` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('5', 'e10adc3949ba59abbe56e057f20f883e', '13632435479', 'jzwb@hotmail.com', '测试账号', '', null, null, '2020-05-20 23:15:01', '2020-05-20 23:15:01');
