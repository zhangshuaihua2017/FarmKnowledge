/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : farm_db

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2019-12-02 08:53:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accout` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `exist` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', '1');
INSERT INTO `admin` VALUES ('2', 'zsh', '123', '1');

-- ----------------------------
-- Table structure for `crop`
-- ----------------------------
DROP TABLE IF EXISTS `crop`;
CREATE TABLE `crop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `img1` varchar(150) DEFAULT NULL,
  `img2` varchar(150) DEFAULT NULL,
  `img3` varchar(150) DEFAULT NULL,
  `matureTime` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `exist` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crop
-- ----------------------------
INSERT INTO `crop` VALUES ('1', '玫瑰花', '100', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3420024572,1793552629&fm=26&gp=0.jpg', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3402841371,3026077591&fm=26&gp=0.jpg', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2752334488,1445932567&fm=26&gp=0.jpg', '5', '1000', '500', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accout` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `nickName` varchar(100) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `level` int(11) DEFAULT '1',
  `experience` bigint(11) DEFAULT '0',
  `grade` int(11) DEFAULT '1',
  `money` bigint(11) DEFAULT '0',
  `online` int(11) DEFAULT '1',
  `exist` int(11) DEFAULT '1',
  `land1` int(11) DEFAULT '1',
  `land2` int(11) DEFAULT '1',
  `land3` int(11) DEFAULT '1',
  `land4` int(11) DEFAULT '1',
  `land5` int(11) DEFAULT '0',
  `land6` int(11) DEFAULT '0',
  `land7` int(11) DEFAULT '0',
  `land8` int(11) DEFAULT '0',
  `land9` int(11) DEFAULT '0',
  `land10` int(11) DEFAULT '0',
  `land11` int(11) DEFAULT '0',
  `land12` int(11) DEFAULT '0',
  `land13` int(11) DEFAULT '0',
  `land14` int(11) DEFAULT '0',
  `land15` int(11) DEFAULT '0',
  `land16` int(11) DEFAULT '0',
  `land17` int(11) DEFAULT '0',
  `land18` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9', '皮皮涛', '123', 'yxt', 'https://zhangshuaihua2017.github.io/lianxi/picture/yxt.jpg', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `user` VALUES ('10', '皮皮天', '123', 'jgd', 'https://zhangshuaihua2017.github.io/lianxi/picture/jgd.JPG', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `user` VALUES ('11', '傻飞', '123', 'syf', 'https://zhangshuaihua2017.github.io/lianxi/picture/sf.JPG', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `userauthority`
-- ----------------------------
DROP TABLE IF EXISTS `userauthority`;
CREATE TABLE `userauthority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `openId` varchar(150) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userauthority
-- ----------------------------
INSERT INTO `userauthority` VALUES ('10', '9', 'zxc', 'QQ');

-- ----------------------------
-- Table structure for `userbag`
-- ----------------------------
DROP TABLE IF EXISTS `userbag`;
CREATE TABLE `userbag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useId` int(11) DEFAULT NULL,
  `cropId` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userbag
-- ----------------------------

-- ----------------------------
-- Table structure for `usercrop`
-- ----------------------------
DROP TABLE IF EXISTS `usercrop`;
CREATE TABLE `usercrop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `cropId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `progress` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usercrop
-- ----------------------------
