/*
 Navicat Premium Data Transfer

 Source Server         : loongya
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : eachvi

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 15/12/2020 18:21:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tj_user
-- ----------------------------
DROP TABLE IF EXISTS `tj_user`;
CREATE TABLE `tj_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tj_user
-- ----------------------------
INSERT INTO `tj_user` VALUES (1, '刘海龙hh', 'aa', '淡忘');
INSERT INTO `tj_user` VALUES (2, 'hhh', 'hh', 'ddd');
INSERT INTO `tj_user` VALUES (3, 'fwefwhh', 'fwef', 'fwef');

SET FOREIGN_KEY_CHECKS = 1;
