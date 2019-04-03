/*
 Navicat Premium Data Transfer

 Source Server         : 简治理
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 47.105.168.44:3307
 Source Schema         : justzhili

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 04/04/2019 06:20:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for birthday
-- ----------------------------
DROP TABLE IF EXISTS `birthday`;
CREATE TABLE `birthday`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `gco` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `dataSigns` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '数据权限标志',
  `lunar_birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '农历生日',
  `type` int(2) NULL DEFAULT 0,
  `age` int(3) NULL DEFAULT 0,
  `next_solar` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '下一次公历生日',
  `next_lunar` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '下一次农历生日',
  `solar_days` int(4) NOT NULL COMMENT '下一次公历生日还剩天数',
  `lunar_days` int(4) NOT NULL COMMENT '下一次农历生日还剩天数',
  `updateTime` bigint(20) NULL DEFAULT 0 COMMENT '修改时间',
  `needPush` int(2) NULL DEFAULT 1 COMMENT '是否需要推送服务: 0 不需要 1 需要',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `gco`(`gco`) USING BTREE,
  INDEX `index_dataSigns`(`dataSigns`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of birthday
-- ----------------------------
INSERT INTO `birthday` VALUES (13, '龙艳', '1998-02-14 00:00:00', 'BT7', '我就是管理员', '阴历:1998年1月18日', 1, 21, '2020-02-14 00:00:00', '2020-02-11 00:00:00', 316, 313, 1554307200008, 1);
INSERT INTO `birthday` VALUES (16, '肖佳玉', '2017-12-02 01:00:00', 'BT9', '我就是管理员', '阴历:2017年10月15日', 1, 2, '2019-12-02 01:00:00', '2019-11-11 00:00:00', 242, 221, 1554307200008, 1);
INSERT INTO `birthday` VALUES (18, '张鑫斌', '1994-03-19 00:00:00', 'BT10', '我就是管理员', '阴历:1994年2月8日', 1, 25, '2020-03-19 00:00:00', '2020-03-01 00:00:00', 350, 332, 1554307200009, 1);
INSERT INTO `birthday` VALUES (19, '蒋凯', '1992-11-29 00:00:00', 'BT11', '我就是管理员', '阴历:1992年11月6日', 1, 27, '2019-11-29 00:00:00', '2019-12-01 00:00:00', 239, 241, 1554307200009, 1);
INSERT INTO `birthday` VALUES (20, '林广莲', '1965-11-24 00:00:00', 'BT12', '我就是管理员', '阴历:1965年11月2日', 1, 54, '2019-11-24 00:00:00', '2019-11-27 00:00:00', 234, 237, 1554307200009, 1);
INSERT INTO `birthday` VALUES (21, '肖婷玉', '2016-02-16 00:00:00', 'BT15', '我就是管理员', '阴历:2016年1月9日', 1, 3, '2020-02-16 00:00:00', '2020-02-02 00:00:00', 318, 304, 1554307200009, 1);
INSERT INTO `birthday` VALUES (22, '龙缘', '2005-02-12 00:00:00', 'BT16', '我就是管理员', '阴历:2005年1月4日', 1, 14, '2020-02-12 00:00:00', '2020-01-28 00:00:00', 314, 299, 1554307200009, 1);
INSERT INTO `birthday` VALUES (34, '张利鲲', '2012-04-29 00:00:00', 'BT36', 'J8E5yqQHDopckAGdwPATXA==', '阴历:2012年4月9日', 1, 7, '2019-04-29 00:00:00', '2019-05-13 00:00:00', 25, 39, 1554307200009, 1);
INSERT INTO `birthday` VALUES (36, '韦斌', '1993-12-04 00:00:00', 'BT38', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1993年10月21日', 1, 26, '2019-12-04 00:00:00', '2019-11-17 00:00:00', 244, 227, 1554307200009, 1);
INSERT INTO `birthday` VALUES (37, '肖浩宇', '2013-05-22 00:00:00', 'BT39', 'J8E5yqQHDopckAGdwPATXA==', '阴历:2013年4月13日', 1, 6, '2019-05-22 00:00:00', '2019-05-17 00:00:00', 48, 43, 1554307200009, 1);
INSERT INTO `birthday` VALUES (38, '张利鹏', '2015-10-05 00:00:00', 'BT40', 'J8E5yqQHDopckAGdwPATXA==', '阴历:2015年8月23日', 1, 4, '2019-10-05 00:00:00', '2019-09-21 00:00:00', 184, 170, 1554307200009, 1);
INSERT INTO `birthday` VALUES (39, '肖凤', '1989-10-06 00:00:00', 'BT41', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1989年9月7日', 1, 30, '2019-10-06 00:00:00', '2019-10-05 00:00:00', 185, 184, 1554307200009, 1);
INSERT INTO `birthday` VALUES (40, '张慧珍', '1990-09-27 00:00:00', 'BT42', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1990年8月9日', 1, 29, '2019-09-27 00:00:00', '2019-09-07 00:00:00', 176, 156, 1554307200009, 1);
INSERT INTO `birthday` VALUES (41, '张慧娟', '1988-03-03 00:00:00', 'BT43', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1988年1月16日', 1, 31, '2020-03-03 00:00:00', '2020-02-09 00:00:00', 334, 311, 1554307200009, 1);
INSERT INTO `birthday` VALUES (43, '赖琪', '1994-08-27 00:00:00', 'BT45', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1994年7月21日', 1, 25, '2019-08-27 00:00:00', '2019-08-21 00:00:00', 145, 139, 1554307200010, 1);
INSERT INTO `birthday` VALUES (44, '杜同周', '1992-03-21 00:00:00', 'BT46', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1992年2月18日', 1, 27, '2020-03-21 00:00:00', '2020-03-11 00:00:00', 352, 342, 1554307200010, 1);
INSERT INTO `birthday` VALUES (45, '钟素芬', '1956-04-02 00:00:00', 'BT47', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1956年2月22日', 1, 63, '2020-04-02 00:00:00', '2020-03-15 00:00:00', 364, 346, 1554307200010, 1);
INSERT INTO `birthday` VALUES (46, '唐城', '1992-05-19 00:00:00', 'BT49', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1992年4月17日', 1, 27, '2019-05-19 00:00:00', '2019-05-21 00:00:00', 45, 47, 1554307200010, 1);
INSERT INTO `birthday` VALUES (47, '结婚纪念日', '2017-02-03 00:00:00', 'BT51', 'J8E5yqQHDopckAGdwPATXA==', '阴历:2017年1月7日', 1, 2, '2020-02-03 00:00:00', '2020-01-31 00:00:00', 305, 302, 1554307200010, 1);
INSERT INTO `birthday` VALUES (49, '111', '2018-06-01 00:00:00', 'BT53', 'Yku3r2CqQLcCxBFz20eGoA==', '阴历:2018年4月18日', 1, 1, '2019-06-01 00:00:00', '2019-05-22 00:00:00', 58, 48, 1554307200010, 1);
INSERT INTO `birthday` VALUES (50, '111', '2018-06-03 00:00:00', 'BT54', 'Yku3r2CqQLcCxBFz20eGoA==', '阴历:2018年4月20日', 1, 1, '2019-06-03 00:00:00', '2019-05-24 00:00:00', 60, 50, 1554307200010, 1);
INSERT INTO `birthday` VALUES (51, '张伟群', '1962-06-08 00:00:00', 'BT55', 'J8E5yqQHDopckAGdwPATXA==', '阴历:1962年5月7日', 1, 57, '2019-06-08 00:00:00', '2019-06-09 00:00:00', 65, 66, 1554307200010, 1);

-- ----------------------------
-- Table structure for incrementer
-- ----------------------------
DROP TABLE IF EXISTS `incrementer`;
CREATE TABLE `incrementer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `moid` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '模块名字',
  `rule` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'ID生成规则',
  `next` bigint(20) NOT NULL DEFAULT 0 COMMENT '当前模块的当天的最大ID值',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `incrementer_moid_uk`(`moid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '给模块顺序生成ID值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of incrementer
-- ----------------------------
INSERT INTO `incrementer` VALUES (1, 'birthday', 'BT', 59);
INSERT INTO `incrementer` VALUES (2, 'pushsetting', 'PS', 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `user` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户编码',
  `dataSigns` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '数据权限标志',
  `type` int(2) NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('zxbjinx', 'J8E5yqQHDopckAGdwPATXA==', 0);
INSERT INTO `permission` VALUES ('zxbjinx', '我就是管理员', 1);
INSERT INTO `permission` VALUES ('test', 'CY9rzUYh03PK3k6DJie09g==', 0);
INSERT INTO `permission` VALUES ('zxbjinx1', 'GCPP689YmVTgvUG1VAPgOw==', 0);
INSERT INTO `permission` VALUES ('zxbjinx1', 'GCPP689YmVTgvUG1VAPgOw==', 0);
INSERT INTO `permission` VALUES ('alanweb', 'Yku3r2CqQLcCxBFz20eGoA==', 0);

-- ----------------------------
-- Table structure for pushsetting
-- ----------------------------
DROP TABLE IF EXISTS `pushsetting`;
CREATE TABLE `pushsetting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `pushType` int(2) NULL DEFAULT 0 COMMENT '推送方式: 0 不需要 1 短信服务 2 邮箱 3 qq 4 微信',
  `forwardDays` int(3) NULL DEFAULT 0 COMMENT '退钱多少天推送通知',
  `pushDate` bigint(20) NULL DEFAULT 0 COMMENT '上次推送时间',
  `stat` int(2) NULL DEFAULT 1 COMMENT '状态：0 关闭 1 开启',
  `gco` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_UNIQUE`(`userName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '推送设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pushsetting
-- ----------------------------
INSERT INTO `pushsetting` VALUES (2, 'zxbjinx', 1, 7, 1554249600010, 1, 'PS2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `nick_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `nation` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '城市/区',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详细地址',
  `qqcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'QQ号',
  `wechatcode` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '微信号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_UNIQUE`(`userName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'zxbjinx', 'zxbjinx', '张鑫斌', '15013720402', '', NULL, NULL, NULL, NULL, '', '');
INSERT INTO `user` VALUES (5, 'test', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `user` VALUES (6, 'zxbjinx1', 'zxbjinx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `user` VALUES (7, 'alanweb', '123123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '');

-- ----------------------------
-- Function structure for getnextint
-- ----------------------------
DROP FUNCTION IF EXISTS `getnextint`;
delimiter ;;
CREATE FUNCTION `getnextint`(moid VARCHAR(50))
 RETURNS int(11)
BEGIN
	-- 得到下一个序列数，返回，并更新到对应序列中
	DECLARE next INT;
	SET next = (select i.next from incrementer i where i.moid = moid ) + 1;
	UPDATE incrementer i SET i.next= next WHERE (i.moid=moid);
	return next;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
