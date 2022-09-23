/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.88.14
 Source Server Type    : MySQL
 Source Server Version : 50727 (5.7.27-log)
 Source Host           : 192.168.88.14:3306
 Source Schema         : fileServer

 Target Server Type    : MySQL
 Target Server Version : 50727 (5.7.27-log)
 File Encoding         : 65001

 Date: 23/09/2022 17:23:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_src
-- ----------------------------
DROP TABLE IF EXISTS `file_src`;
CREATE TABLE `file_src`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件名',
  `type` int(3) NOT NULL COMMENT '文件类型',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `user_uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属用户的uid',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_src
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限标识',
  `permission_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  `father_id` int(11) NOT NULL COMMENT '父级权限',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径',
  `is_menu` int(11) NOT NULL COMMENT '菜单',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_code`(`permission_code`, `flag`) USING BTREE,
  UNIQUE INDEX `path`(`path`, `flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (23, '25', '周低大', 0, '/minim velit ipsum sed nisi', 1, '2022-09-22 17:26:17', '2022-09-22 17:28:59', 1663838938, 1663824325, 0);
INSERT INTO `permission` VALUES (28, '200', '周低大1', 0, '/aaaa', 1, '2022-09-22 17:26:22', '2022-09-22 17:26:22', 1663824639, 1663824639, 0);
INSERT INTO `permission` VALUES (30, '2000', '周低大11', 23, '/aaaaa', 1, '2022-09-23 11:00:54', '2022-09-23 11:00:54', 1663824726, 1663824726, NULL);
INSERT INTO `permission` VALUES (31, '20000', '周低大111', 23, '/aaaaa1', 1, '2022-09-22 17:26:25', '2022-09-22 17:26:25', 1663824748, 1663824748, 0);
INSERT INTO `permission` VALUES (33, '200000', '周低大1111', 28, '/aaaaa11', 1, '2022-09-22 17:26:26', '2022-09-22 17:26:26', 1663824796, 1663824796, 0);
INSERT INTO `permission` VALUES (35, '2000000', '周低大11111', 28, '/aaaaa111', 1, '2022-09-22 17:26:27', '2022-09-22 17:26:27', 1663824808, 1663824808, 0);
INSERT INTO `permission` VALUES (36, '20001000', '周低大111111', 23, 'a/aaaa1111', 1, '2022-09-22 17:26:28', '2022-09-22 17:26:28', 1663824813, 1663824813, 0);
INSERT INTO `permission` VALUES (37, '20001000:write', '周低大111111:解决问题权限', 36, '/aaaaa1111/write', 0, '2022-09-22 17:26:32', '2022-09-22 17:26:32', 1663834381, 1663834381, NULL);
INSERT INTO `permission` VALUES (41, '20001000:write', '周低大111111:解决问题权限', 36, '/aaaaa1111/write', 0, '2022-09-23 11:15:25', '2022-09-23 11:15:25', 1663835093, 1663835093, 0);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问题标题',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `src_id` json NOT NULL COMMENT '文件id数组',
  `user_id` int(11) NOT NULL COMMENT '提出用户的id',
  `permission_id` int(11) NOT NULL COMMENT '项目id 问题属于哪个项目',
  `type` int(3) NOT NULL COMMENT '问题类型',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '0代表有效 1代表无效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '唯一约束',
  UNIQUE INDEX `path`(`path`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(10) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '用户');
INSERT INTO `role` VALUES (2, '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for update_diary
-- ----------------------------
DROP TABLE IF EXISTS `update_diary`;
CREATE TABLE `update_diary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `versions` float NOT NULL COMMENT '版本号',
  `permission_id` int(11) NOT NULL COMMENT '属于项目的id',
  `project_id` json NOT NULL COMMENT '解决了哪些问题',
  `src_id` json NOT NULL COMMENT '提交的文件id',
  `user_id` int(11) NOT NULL COMMENT '解决者的id',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of update_diary
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录账号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户昵称 或 姓名',
  `password` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime NOT NULL COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '0代表有效 1代表无效',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '个人信息',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid`(`uid`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'admin', '2022-09-21 14:11:36', '2022-09-21 14:11:39', 0, 0, 0, '1', '1');
INSERT INTO `user` VALUES (2, 'user', 'user', 'user', '2022-09-21 14:50:49', '2022-09-21 14:50:53', 0, 0, 0, '12', '1');
INSERT INTO `user` VALUES (3, '61666', '值气由她理', 'adminn', '2022-09-22 15:12:28', '2022-09-22 15:12:28', 1663830747, 1663830747, 0, '18131568601', 'irure non qui cillum');
INSERT INTO `user` VALUES (4, '45354345', '响龙细', 'cupidatatanim', '2022-09-22 15:24:54', '2022-09-22 15:24:54', 1663831494, 1663831494, 0, '18103693742', 'enim in sed');
INSERT INTO `user` VALUES (10, 'test', 'elllo', 'admin15462', '2022-09-23 14:46:37', '2022-09-23 14:46:37', 1663915596, 1663915596, 0, '17329989256', '测试');
INSERT INTO `user` VALUES (11, 'admin11', '我是谁', 'a123456', '2022-09-23 15:48:26', '2022-09-23 15:48:26', 1663919305, 1663919305, 0, '14564482456', '我的是');

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`user_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (2, 28);
INSERT INTO `user_permission` VALUES (2, 30);
INSERT INTO `user_permission` VALUES (2, 41);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 2);
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (3, 1);
INSERT INTO `user_role` VALUES (4, 1);
INSERT INTO `user_role` VALUES (5, 1);
INSERT INTO `user_role` VALUES (10, 1);
INSERT INTO `user_role` VALUES (11, 1);

SET FOREIGN_KEY_CHECKS = 1;
