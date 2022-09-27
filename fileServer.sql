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

 Date: 27/09/2022 17:37:01
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
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `user_uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属用户的uid',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file_src
-- ----------------------------
INSERT INTO `file_src` VALUES (1, 'ffmpeg.dll', '/upload/admin/2022-09-26`16`48`35==ffmpeg.dll', 'admin', '2022-09-26 16:48:36', '2022-09-26 16:48:36', 1664182115, 1664182115, 0);
INSERT INTO `file_src` VALUES (2, 'ffmpeg.dll', '/upload/admin/2022-09-26`16`51`07==ffmpeg.dll', 'admin', '2022-09-26 16:51:07', '2022-09-26 16:51:07', 1664182267, 1664182267, 0);
INSERT INTO `file_src` VALUES (3, 'ffmpeg.dll', '/upload/admin/2022-09-26`16`51`34==ffmpeg.dll', 'admin', '2022-09-26 16:51:35', '2022-09-26 16:51:35', 1664182294, 1664182294, 0);
INSERT INTO `file_src` VALUES (4, 'ffmpeg.dll', '/upload/admin/2022-09-26`17`03`05==ffmpeg.dll', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (5, 'chrome_100_percent.pak', '/upload/admin/2022-09-26`17`03`05==chrome_100_percent.pak', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (6, 'chrome_200_percent.pak', '/upload/admin/2022-09-26`17`03`05==chrome_200_percent.pak', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (7, 'd3dcompiler_47.dll', '/upload/admin/2022-09-26`17`03`05==d3dcompiler_47.dll', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (8, 'ffmpeg.dll', '/upload/admin/2022-09-26`17`03`05==ffmpeg.dll', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (9, 'icudtl.dat', '/upload/admin/2022-09-26`17`03`05==icudtl.dat', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (10, 'libEGL.dll', '/upload/admin/2022-09-26`17`03`05==libEGL.dll', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (11, 'libGLESv2.dll', '/upload/admin/2022-09-26`17`03`05==libGLESv2.dll', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (12, 'LICENSE.electron.txt', '/upload/admin/2022-09-26`17`03`05==LICENSE.electron.txt', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (13, 'LICENSES.chromium.html', '/upload/admin/2022-09-26`17`03`05==LICENSES.chromium.html', 'admin', '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0);
INSERT INTO `file_src` VALUES (14, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`43`54==ffmpeg.dll', 'admin', '2022-09-27 16:43:54', '2022-09-27 16:43:54', 1664268234, 1664268234, 0);
INSERT INTO `file_src` VALUES (15, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`44`55==ffmpeg.dll', 'admin', '2022-09-27 16:44:56', '2022-09-27 16:44:56', 1664268296, 1664268296, 0);
INSERT INTO `file_src` VALUES (16, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`46`28==ffmpeg.dll', 'admin', '2022-09-27 16:46:29', '2022-09-27 16:46:29', 1664268388, 1664268388, 0);
INSERT INTO `file_src` VALUES (17, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`46`52==ffmpeg.dll', 'admin', '2022-09-27 16:46:52', '2022-09-27 16:46:52', 1664268412, 1664268412, 0);
INSERT INTO `file_src` VALUES (18, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`48`55==ffmpeg.dll', 'admin', '2022-09-27 16:48:56', '2022-09-27 16:48:56', 1664268535, 1664268535, 0);
INSERT INTO `file_src` VALUES (19, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`49`34==ffmpeg.dll', 'admin', '2022-09-27 16:49:34', '2022-09-27 16:49:34', 1664268574, 1664268574, 0);
INSERT INTO `file_src` VALUES (20, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`49`52==ffmpeg.dll', 'admin', '2022-09-27 16:49:52', '2022-09-27 16:49:52', 1664268592, 1664268592, 0);
INSERT INTO `file_src` VALUES (21, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`49`54==ffmpeg.dll', 'admin', '2022-09-27 16:49:54', '2022-09-27 16:49:54', 1664268594, 1664268594, 0);
INSERT INTO `file_src` VALUES (22, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`49`56==ffmpeg.dll', 'admin', '2022-09-27 16:49:56', '2022-09-27 16:49:56', 1664268596, 1664268596, 0);
INSERT INTO `file_src` VALUES (23, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==ffmpeg.dll', 'admin', '2022-09-27 16:50:29', '2022-09-27 16:50:29', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (24, 'chrome_100_percent.pak', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==chrome_100_percent.pak', 'admin', '2022-09-27 16:50:29', '2022-09-27 16:50:29', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (25, 'chrome_200_percent.pak', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==chrome_200_percent.pak', 'admin', '2022-09-27 16:50:29', '2022-09-27 16:50:29', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (26, 'd3dcompiler_47.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==d3dcompiler_47.dll', 'admin', '2022-09-27 16:50:30', '2022-09-27 16:50:30', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (27, 'ffmpeg.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==ffmpeg.dll', 'admin', '2022-09-27 16:50:30', '2022-09-27 16:50:30', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (28, 'icudtl.dat', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==icudtl.dat', 'admin', '2022-09-27 16:50:30', '2022-09-27 16:50:30', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (29, 'libEGL.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==libEGL.dll', 'admin', '2022-09-27 16:50:30', '2022-09-27 16:50:30', 1664268629, 1664268629, 0);
INSERT INTO `file_src` VALUES (30, 'libGLESv2.dll', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`29==libGLESv2.dll', 'admin', '2022-09-27 16:50:31', '2022-09-27 16:50:31', 1664268630, 1664268630, 0);
INSERT INTO `file_src` VALUES (31, 'LICENSE.electron.txt', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`30==LICENSE.electron.txt', 'admin', '2022-09-27 16:50:31', '2022-09-27 16:50:31', 1664268630, 1664268630, 0);
INSERT INTO `file_src` VALUES (32, 'LICENSES.chromium.html', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`50`30==LICENSES.chromium.html', 'admin', '2022-09-27 16:50:31', '2022-09-27 16:50:31', 1664268630, 1664268630, 0);
INSERT INTO `file_src` VALUES (33, 'LICENSES.chromium.html', 'http://192.168.88.123:8080/api/upload/admin/2022-09-27`16`56`24==LICENSES.chromium.html', 'admin', '2022-09-27 16:56:25', '2022-09-27 16:56:25', 1664268984, 1664268984, 0);

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
  `flag` int(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_code`(`permission_code`, `flag`) USING BTREE,
  UNIQUE INDEX `path`(`path`, `flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (23, '25', '周低大', 0, '/minim velit ipsum sed nisi', 1, '2022-09-22 17:26:17', '2022-09-22 17:28:59', 1663838938, 1663824325, 0);
INSERT INTO `permission` VALUES (28, '200', '周低大1', 0, '/aaaa', 1, '2022-09-22 17:26:22', '2022-09-22 17:26:22', 1663824639, 1663824639, 0);
INSERT INTO `permission` VALUES (36, '20001000', '周低大111111', 23, '/aaaa1111', 1, '2022-09-26 09:55:24', '2022-09-26 09:55:24', 1663824813, 1663824813, 0);
INSERT INTO `permission` VALUES (41, '20001000:write', '周低大111111:解决问题权限', 36, '/aaaaa1111/write', 0, '2022-09-23 11:15:25', '2022-09-23 11:15:25', 1663835093, 1663835093, 0);
INSERT INTO `permission` VALUES (42, '79', '华门道般油便', 23, '/non', 1, '2022-09-26 16:12:21', '2022-09-26 16:12:21', 1664164623, 1664164623, 0);
INSERT INTO `permission` VALUES (43, '79:write', '华门道般油便:解决问题权限', 42, '/non/', 0, '2022-09-27 14:00:02', '2022-09-27 14:00:02', 1664164623, 1664164623, 1);
INSERT INTO `permission` VALUES (44, '54', '律适取', 28, '/irure', 1, '2022-09-26 16:14:44', '2022-09-26 16:14:44', 1664180060, 1664180060, 0);
INSERT INTO `permission` VALUES (45, '54:write', '律适取:解决问题权限', 44, '/irure/', 0, '2022-09-27 13:56:04', '2022-09-27 13:56:04', 1664180060, 1664180060, 1);
INSERT INTO `permission` VALUES (52, '544', '律适', 28, '/irure2', 1, '2022-09-26 16:27:39', '2022-09-26 16:27:39', 1664180859, 1664180859, 0);
INSERT INTO `permission` VALUES (53, '544:write', '律适:解决问题权限', 52, '/irure2/write', 0, '2022-09-27 13:59:58', '2022-09-27 13:59:58', 1664180859, 1664180859, 1);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问题标题',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `src_id` json NOT NULL COMMENT '文件id数组',
  `user_uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '提出用户的uid',
  `permission_id` int(11) NOT NULL COMMENT '项目id 问题属于哪个项目',
  `type` int(3) NOT NULL COMMENT '问题类型 0一般 1重要 2紧急',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL COMMENT '逻辑删除',
  `update_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '对应版本id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (6, '问题', '25', '[]', 'admin', 42, 0, '2022-09-27 10:40:27', '2022-09-27 10:40:27', 1664246411, 1664179865, 0, 0);
INSERT INTO `problem` VALUES (10, '2545245', '2452', '[1]', 'admin', 36, 0, '2022-09-26 16:48:36', '2022-09-26 16:48:36', 1664182115, 1664182115, 0, 0);
INSERT INTO `problem` VALUES (11, '25452452', '24522', '[2]', 'admin', 36, 0, '2022-09-26 16:51:07', '2022-09-26 16:51:07', 1664182267, 1664182267, 0, 0);
INSERT INTO `problem` VALUES (12, '25452452', '24522', '[3]', 'admin', 36, 0, '2022-09-26 16:51:35', '2022-09-26 16:51:35', 1664182294, 1664182294, 0, 0);
INSERT INTO `problem` VALUES (13, '超多文件的问题', '24522', '[4, 5, 6, 7, 8, 9, 10, 11, 12, 13]', 'admin', 36, 0, '2022-09-26 17:03:06', '2022-09-26 17:03:06', 1664182985, 1664182985, 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

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
  `version_max` int(5) NOT NULL COMMENT '大版本号',
  `version_min` int(5) NOT NULL COMMENT '小版本号',
  `permission_id` int(11) NOT NULL COMMENT '属于项目的id',
  `problem_id` json NOT NULL COMMENT '解决了哪些问题',
  `src_id` json NOT NULL COMMENT '提交的文件id',
  `user_uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '解决者的id',
  `created_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_timestamp` int(11) NOT NULL COMMENT '修改时间戳',
  `created_timestamp` int(11) NOT NULL COMMENT '创建时间戳',
  `flag` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of update_diary
-- ----------------------------
INSERT INTO `update_diary` VALUES (1, '543', 1, 0, 36, '[23]', '[19]', 'admin', '2022-09-27 16:55:38', '2022-09-27 16:55:38', 1664268574, 1664268574, 0);
INSERT INTO `update_diary` VALUES (2, '543', 1, 1, 36, '[23]', '[20]', 'admin', '2022-09-27 17:23:16', '2022-09-27 17:23:16', 1664268592, 1664268592, 0);
INSERT INTO `update_diary` VALUES (3, '543', 1, 2, 36, '[23]', '[21]', 'admin', '2022-09-27 17:23:17', '2022-09-27 17:23:17', 1664268594, 1664268594, 0);
INSERT INTO `update_diary` VALUES (4, '543', 1, 3, 36, '[23]', '[22]', 'admin', '2022-09-27 17:23:18', '2022-09-27 17:23:18', 1664268596, 1664268596, 0);
INSERT INTO `update_diary` VALUES (5, '543', 1, 4, 36, '[23]', '[23, 24, 25, 26, 27, 28, 29, 30, 31, 32]', 'admin', '2022-09-27 17:23:19', '2022-09-27 17:23:19', 1664268630, 1664268630, 0);
INSERT INTO `update_diary` VALUES (6, '542', 1, 5, 36, '[23]', '[33]', 'admin', '2022-09-27 17:23:21', '2022-09-27 17:23:21', 1664268984, 1664268984, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'admin', '2022-09-21 14:11:36', '2022-09-21 14:11:39', 0, 0, 0, '1', '1');
INSERT INTO `user` VALUES (2, 'user', 'user', 'user', '2022-09-21 14:50:49', '2022-09-21 14:50:53', 0, 0, 0, '12', '1');
INSERT INTO `user` VALUES (3, '61666', '值气由她理', 'adminn', '2022-09-22 15:12:28', '2022-09-22 15:12:28', 1663830747, 1663830747, 0, '18131568601', 'irure non qui cillum');
INSERT INTO `user` VALUES (4, '45354345', '响龙细', 'cupidatatanim', '2022-09-22 15:24:54', '2022-09-22 15:24:54', 1663831494, 1663831494, 0, '18103693742', 'enim in sed');
INSERT INTO `user` VALUES (10, 'test', 'elllo', 'admin15462', '2022-09-23 14:46:37', '2022-09-23 14:46:37', 1663915596, 1663915596, 0, '17329989256', '测试');
INSERT INTO `user` VALUES (11, 'admin11', '我是谁', 'a123456', '2022-09-23 15:48:26', '2022-09-23 15:48:26', 1663919305, 1663919305, 0, '14564482456', '我的是');
INSERT INTO `user` VALUES (12, 'test1', 'test1', 'a123456', '2022-09-26 15:38:30', '2022-09-26 15:38:30', 1664177910, 1664177910, 0, '17358846854', 'agae');
INSERT INTO `user` VALUES (13, 'qqqq49', '制书论各系响红', 'pariatureuqui', '2022-09-26 16:12:10', '2022-09-26 16:12:10', 1664179929, 1664179929, 0, '18183352600', 'sit');

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`user_id`, `permission_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (2, 23);
INSERT INTO `user_permission` VALUES (2, 28);
INSERT INTO `user_permission` VALUES (2, 36);
INSERT INTO `user_permission` VALUES (2, 43);
INSERT INTO `user_permission` VALUES (2, 45);
INSERT INTO `user_permission` VALUES (2, 53);
INSERT INTO `user_permission` VALUES (4, 28);
INSERT INTO `user_permission` VALUES (4, 44);
INSERT INTO `user_permission` VALUES (4, 52);
INSERT INTO `user_permission` VALUES (4, 53);
INSERT INTO `user_permission` VALUES (10, 28);
INSERT INTO `user_permission` VALUES (10, 44);
INSERT INTO `user_permission` VALUES (10, 45);
INSERT INTO `user_permission` VALUES (10, 52);
INSERT INTO `user_permission` VALUES (10, 53);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `role_id`) USING BTREE
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
INSERT INTO `user_role` VALUES (12, 1);
INSERT INTO `user_role` VALUES (13, 1);

SET FOREIGN_KEY_CHECKS = 1;
