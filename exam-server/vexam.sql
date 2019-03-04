drop database if exists v_exam;
CREATE DATABASE `v_exam` DEFAULT CHARACTER SET utf8;

USE `v_exam`;
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  user_id varchar(18) null comment '用户编号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `tele_phone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) DEFAULT '1',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  create_time bigint(16) not null default 0 comment '创建时间',
  update_time bigint(16) not null default 0 comment '更新时间',
  creater int(11) comment '创建者',
  updater int(11) comment '更新者',
  version int(11) not null  default 0 comment '版本号，乐观锁专用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `t_user` VALUES ('0',null, '系统管理员', '18568887789', '010-82881234', '北京昌平', '1', 'admin',
                             '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
                             'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', null, now(), now(), 0, 0, 0);

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `icon_cls` varchar(64) DEFAULT NULL,
  `keep_alive` tinyint(1) DEFAULT NULL,
  `require_auth` tinyint(1) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  create_time bigint(16) not null default 0 comment '创建时间',
  update_time bigint(16) not null default 0 comment '更新时间',
  creater int(11) comment '创建者',
  updater int(11) comment '更新者',
  version int(11) not null  default 0 comment '版本号，乐观锁专用',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_menu_role`;
CREATE TABLE `t_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_menu_id` (`menu_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_nation`;
CREATE TABLE `t_nation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

INSERT INTO `t_nation` VALUES ('1', '汉族');
INSERT INTO `t_nation` VALUES ('2', '蒙古族');
INSERT INTO `t_nation` VALUES ('3', '回族');
INSERT INTO `t_nation` VALUES ('4', '藏族');
INSERT INTO `t_nation` VALUES ('5', '维吾尔族');
INSERT INTO `t_nation` VALUES ('6', '苗族');
INSERT INTO `t_nation` VALUES ('7', '彝族');
INSERT INTO `t_nation` VALUES ('8', '壮族');
INSERT INTO `t_nation` VALUES ('9', '布依族');
INSERT INTO `t_nation` VALUES ('10', '朝鲜族');
INSERT INTO `t_nation` VALUES ('11', '满族');
INSERT INTO `t_nation` VALUES ('12', '侗族');
INSERT INTO `t_nation` VALUES ('13', '瑶族');
INSERT INTO `t_nation` VALUES ('14', '白族');
INSERT INTO `t_nation` VALUES ('15', '土家族');
INSERT INTO `t_nation` VALUES ('16', '哈尼族');
INSERT INTO `t_nation` VALUES ('17', '哈萨克族');
INSERT INTO `t_nation` VALUES ('18', '傣族');
INSERT INTO `t_nation` VALUES ('19', '黎族');
INSERT INTO `t_nation` VALUES ('20', '傈僳族');
INSERT INTO `t_nation` VALUES ('21', '佤族');
INSERT INTO `t_nation` VALUES ('22', '畲族');
INSERT INTO `t_nation` VALUES ('23', '高山族');
INSERT INTO `t_nation` VALUES ('24', '拉祜族');
INSERT INTO `t_nation` VALUES ('25', '水族');
INSERT INTO `t_nation` VALUES ('26', '东乡族');
INSERT INTO `t_nation` VALUES ('27', '纳西族');
INSERT INTO `t_nation` VALUES ('28', '景颇族');
INSERT INTO `t_nation` VALUES ('29', '柯尔克孜族');
INSERT INTO `t_nation` VALUES ('30', '土族');
INSERT INTO `t_nation` VALUES ('31', '达斡尔族');
INSERT INTO `t_nation` VALUES ('32', '仫佬族');
INSERT INTO `t_nation` VALUES ('33', '羌族');
INSERT INTO `t_nation` VALUES ('34', '布朗族');
INSERT INTO `t_nation` VALUES ('35', '撒拉族');
INSERT INTO `t_nation` VALUES ('36', '毛难族');
INSERT INTO `t_nation` VALUES ('37', '仡佬族');
INSERT INTO `t_nation` VALUES ('38', '锡伯族');
INSERT INTO `t_nation` VALUES ('39', '阿昌族');
INSERT INTO `t_nation` VALUES ('40', '普米族');
INSERT INTO `t_nation` VALUES ('41', '塔吉克族');
INSERT INTO `t_nation` VALUES ('42', '怒族');
INSERT INTO `t_nation` VALUES ('43', '乌孜别克族');
INSERT INTO `t_nation` VALUES ('44', '俄罗斯族');
INSERT INTO `t_nation` VALUES ('45', '鄂温克族');
INSERT INTO `t_nation` VALUES ('46', '崩龙族');
INSERT INTO `t_nation` VALUES ('47', '保安族');
INSERT INTO `t_nation` VALUES ('48', '裕固族');
INSERT INTO `t_nation` VALUES ('49', '京族');
INSERT INTO `t_nation` VALUES ('50', '塔塔尔族');
INSERT INTO `t_nation` VALUES ('51', '独龙族');
INSERT INTO `t_nation` VALUES ('52', '鄂伦春族');
INSERT INTO `t_nation` VALUES ('53', '赫哲族');
INSERT INTO `t_nation` VALUES ('54', '门巴族');
INSERT INTO `t_nation` VALUES ('55', '珞巴族');
INSERT INTO `t_nation` VALUES ('56', '基诺族');
