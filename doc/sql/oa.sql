/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : oa

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 18/05/2019 13:59:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dk
-- ----------------------------
DROP TABLE IF EXISTS `dk`;
CREATE TABLE `dk`  (
  `userId` int(5) NULL DEFAULT NULL COMMENT '用户id',
  `dkTime` datetime(0) NULL DEFAULT NULL COMMENT '打卡时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gsgg
-- ----------------------------
DROP TABLE IF EXISTS `gsgg`;
CREATE TABLE `gsgg`  (
  `ggId` int(5) NOT NULL,
  `ggTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `ggNr` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `ggTime` datetime(0) NULL DEFAULT NULL COMMENT '公告时间',
  `isZs` int(1) NULL DEFAULT NULL COMMENT '是否展示',
  PRIMARY KEY (`ggId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gsgg
-- ----------------------------
INSERT INTO `gsgg` VALUES (1, '五一长假', '公司各部门：     “年会”，是公司一年一度不可缺少的“家庭盛会”。年末岁初，通过年会这种形式来组织各种活动，激扬士气、部署战略、制定目标，奏响新一年度工作的序曲。\r\n    年会总结回顾20XX年度各项工作，对20XX年工作做出安排和部署，表彰年度各项先进，增进公司内部员工的交流和沟通，促', '2019-05-15 14:59:35', 1);
INSERT INTO `gsgg` VALUES (2, '公告2', '促进公司的企业文化建设，同时计划20XX年年会邀请供应商参加，分享公司一年来的业绩成果，以快乐开心的气氛，加强与供应商的合作和沟通。\r\n   0XX年年会主题：激情、创新、融合、跨越     为更好的组织20XX年公司年会，通知如下：     1、公司组织文艺节目演出，要求以部门为单位上报演出节目。每个部门都必须有节目。人员比较少的部门可以组合。', '2019-05-16 14:59:56', 1);

-- ----------------------------
-- Table structure for hytz
-- ----------------------------
DROP TABLE IF EXISTS `hytz`;
CREATE TABLE `hytz`  (
  `hyId` int(5) NOT NULL AUTO_INCREMENT COMMENT '会议id',
  `hynr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议内容',
  `hyTime` datetime(0) NULL DEFAULT NULL COMMENT '会议时间',
  `hydd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议地点',
  `hybtzr` int(5) NULL DEFAULT NULL COMMENT '会议被通知人id',
  `hyztr` int(5) NULL DEFAULT NULL COMMENT '会议通知人id',
  PRIMARY KEY (`hyId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qjsq
-- ----------------------------
DROP TABLE IF EXISTS `qjsq`;
CREATE TABLE `qjsq`  (
  `qjId` int(6) NOT NULL AUTO_INCREMENT,
  `qjyy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假原因',
  `qjms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假描述',
  `ksTime` datetime(0) NULL DEFAULT NULL COMMENT '请假开始时间',
  `jsTime` datetime(0) NULL DEFAULT NULL COMMENT '请假结束时间',
  `qjzt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请假状态',
  `userId` int(5) NULL DEFAULT NULL COMMENT '请假人',
  PRIMARY KEY (`qjId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qjsq
-- ----------------------------
INSERT INTO `qjsq` VALUES (9, '事假', '回家', '2019-05-11 00:00:00', '2019-05-11 00:00:00', '审批中', 1);
INSERT INTO `qjsq` VALUES (10, '病假', '', '2019-05-12 00:00:00', '2019-05-13 00:00:00', '审批中', 1);

-- ----------------------------
-- Table structure for rw
-- ----------------------------
DROP TABLE IF EXISTS `rw`;
CREATE TABLE `rw`  (
  `rwId` int(5) NOT NULL,
  `rwmc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `rwms` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `rwTime` datetime(0) NULL DEFAULT NULL COMMENT '截止时间',
  `jsUserId` int(5) NULL DEFAULT NULL COMMENT '接收人',
  `fbUserId` int(5) NULL DEFAULT NULL COMMENT '发布人',
  `jsUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人名称',
  `fbUserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人名称',
  `jjcd` int(2) NULL DEFAULT NULL COMMENT '紧急程度',
  `isComplete` int(2) NULL DEFAULT NULL COMMENT '是否完成：1完成，2未完成',
  PRIMARY KEY (`rwId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rw
-- ----------------------------
INSERT INTO `rw` VALUES (1, '任务1', '任务任务任务任务任务任务任务任务任务任务任务', '2019-05-17 16:37:50', 3, 1, 'zjw', 'ycy', 1, NULL);

-- ----------------------------
-- Table structure for rz
-- ----------------------------
DROP TABLE IF EXISTS `rz`;
CREATE TABLE `rz`  (
  `rzId` int(11) NOT NULL AUTO_INCREMENT,
  `rdnr` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `rzTime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `userId` int(5) NULL DEFAULT NULL COMMENT '记录人id',
  PRIMARY KEY (`rzId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(5) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nikeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '花名',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `zw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `permission` int(2) NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ycy', 'cymm', '1', '经理', 1);
INSERT INTO `user` VALUES (3, 'zjw', 'cc', '3', '员工', 3);

-- ----------------------------
-- Table structure for xmjd
-- ----------------------------
DROP TABLE IF EXISTS `xmjd`;
CREATE TABLE `xmjd`  (
  `xmmc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xmjd` int(6) NULL DEFAULT NULL,
  `xmId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`xmId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xmjd
-- ----------------------------
INSERT INTO `xmjd` VALUES ('项目1', 77, 1);
INSERT INTO `xmjd` VALUES ('项目2', 11, 2);
INSERT INTO `xmjd` VALUES ('项目3', 100, 3);

SET FOREIGN_KEY_CHECKS = 1;
