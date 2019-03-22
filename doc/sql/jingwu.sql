/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : jingwu

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 21/03/2019 15:18:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for add_point
-- ----------------------------
DROP TABLE IF EXISTS `add_point`;
CREATE TABLE `add_point`  (
  `addId` int(4) NOT NULL AUTO_INCREMENT,
  `userId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加分人Id',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加分人名称',
  `addName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加分项目名称',
  `addPoint` int(4) NULL DEFAULT NULL COMMENT '加分分数',
  PRIMARY KEY (`addId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of add_point
-- ----------------------------
INSERT INTO `add_point` VALUES (1, '1', 'zjwzjw', '见义勇为', 10);

-- ----------------------------
-- Table structure for delete_point
-- ----------------------------
DROP TABLE IF EXISTS `delete_point`;
CREATE TABLE `delete_point`  (
  `deleteId` int(4) NOT NULL AUTO_INCREMENT,
  `userId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被扣分人Id',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被扣分人名称',
  `deleteName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扣分项目名称',
  `deletePoint` int(4) NULL DEFAULT NULL COMMENT '扣除分数',
  PRIMARY KEY (`deleteId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delete_point
-- ----------------------------
INSERT INTO `delete_point` VALUES (1, '1', 'zjwzjw', '被子不整齐', 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permisssionId` int(4) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permissionDegree` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`permisssionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for punish
-- ----------------------------
DROP TABLE IF EXISTS `punish`;
CREATE TABLE `punish`  (
  `punishId` int(4) NOT NULL AUTO_INCREMENT,
  `punishName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '惩罚项目名称',
  `userId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被惩罚人Id',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被惩罚人名称',
  `punishReason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '惩罚原因',
  `punishComplete` int(1) NULL DEFAULT NULL COMMENT '惩罚是否完成 1：完成 2：未完成',
  PRIMARY KEY (`punishId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of punish
-- ----------------------------
INSERT INTO `punish` VALUES (1, '跑步', '1', 'zjwzjw', '扣班级分数', 1);

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `qdId` int(4) NOT NULL AUTO_INCREMENT,
  `qdbm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区队编码',
  `qdmc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区队名称',
  `qdms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区队简介',
  PRIMARY KEY (`qdId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES (1, 'zjjc1591801', '15级网络安全与执法一队', '浙江警察学院 15级 网络安全与执法专业 一班');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permissionDegree` int(4) NULL DEFAULT NULL COMMENT '权限级别',
  `qdbm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区队编码',
  `kpfs` double(4, 0) NULL DEFAULT NULL COMMENT '考评分数',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zjwzjw', '123456', 3, 'zjjc1591801', NULL);

-- ----------------------------
-- Table structure for user_wj
-- ----------------------------
DROP TABLE IF EXISTS `user_wj`;
CREATE TABLE `user_wj`  (
  `wjId` int(4) NOT NULL AUTO_INCREMENT,
  `wjName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违纪名称',
  `wjMs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违纪描述',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违纪人名称',
  `userId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '违纪人Id',
  `wj_point` int(4) NULL DEFAULT NULL COMMENT '违纪扣除分数',
  PRIMARY KEY (`wjId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for weak_check
-- ----------------------------
DROP TABLE IF EXISTS `weak_check`;
CREATE TABLE `weak_check`  (
  `checkId` int(4) NOT NULL AUTO_INCREMENT,
  `checkName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检查名称',
  `checkDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '检查描述',
  `checkPoint` int(4) NULL DEFAULT NULL COMMENT '检查分数',
  PRIMARY KEY (`checkId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
