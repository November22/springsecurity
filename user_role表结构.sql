/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : user_role

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-03-09 09:21:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `id` char(40) NOT NULL,
  `m_name` char(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` char(40) NOT NULL,
  `r_name` char(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_model
-- ----------------------------
DROP TABLE IF EXISTS `role_model`;
CREATE TABLE `role_model` (
  `role_id` char(40) NOT NULL,
  `model_id` char(40) NOT NULL,
  `id` char(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `model_id` (`model_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `role_model_ibfk_1` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`),
  CONSTRAINT `role_model_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(40) NOT NULL,
  `u_name` char(40) NOT NULL COMMENT '用户的登录名称',
  `u_password` char(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` char(40) NOT NULL,
  `role_id` char(40) NOT NULL,
  `id` char(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
