/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : shoppingwebsite_db

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2025-05-10 13:24:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '用户ID',
  `name` varchar(255) default NULL COMMENT '姓名',
  `tel` varchar(255) default NULL COMMENT '电话',
  `addr` text COMMENT '地址',
  `ismr` varchar(255) default NULL COMMENT '是否默认',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('5', '1', '杨刚', '13872961155', '洪山区雄楚大道632号', '否');
INSERT INTO `address` VALUES ('6', '13', '刘鹏', '13875962211', '武汉市洪山区雄楚大道632号', '否');
INSERT INTO `address` VALUES ('7', '14', '李强', '13875962211', '洪山区雄楚大道632号', '否');

-- ----------------------------
-- Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '注册用户ID',
  `productid` int(11) default NULL COMMENT '商品ID',
  `num` int(11) default NULL COMMENT '数量',
  `sellerid` int(11) default NULL COMMENT '商家ID',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  KEY `productid` (`productid`),
  KEY `sellerid` (`sellerid`),
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`sellerid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '一级分类',
  `fatherid` int(11) default NULL COMMENT '上一级ID',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('20', '全部', '0');
INSERT INTO `category` VALUES ('21', '手机', '20');
INSERT INTO `category` VALUES ('22', '电脑', '20');
INSERT INTO `category` VALUES ('23', '智能手表', '20');

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '注册用户ID',
  `productid` int(11) default NULL COMMENT '商品ID',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  KEY `productid` (`productid`),
  CONSTRAINT `collect_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collect_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('15', '14', '8');
INSERT INTO `collect` VALUES ('17', '14', '6');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '注册用户ID',
  `productid` int(11) default NULL COMMENT '商品ID',
  `score` int(11) default NULL COMMENT '评分',
  `content` text COMMENT '评价内容',
  `savetime` varchar(255) default NULL COMMENT '时间',
  `hfcontent` text COMMENT '回复内容',
  `sellerid` int(11) default NULL COMMENT '商家ID',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  KEY `productid` (`productid`),
  KEY `sellerid` (`sellerid`),
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`sellerid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('3', '14', '6', '5', '存+1TB超速硬盘HALIWEI国行【2', '2025-05-08 22:33:39', '存+1TB超速硬盘HALIWEI国行【2', '4');

-- ----------------------------
-- Table structure for `history`
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '注册用户ID',
  `productid` int(11) default NULL COMMENT '商品ID',
  `score` decimal(10,2) default NULL COMMENT '权重',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  KEY `productid` (`productid`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `history_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('1', '1', '9', '5.00');
INSERT INTO `history` VALUES ('2', '1', '8', '5.00');
INSERT INTO `history` VALUES ('3', '1', '7', '5.00');
INSERT INTO `history` VALUES ('4', '1', '6', '5.00');
INSERT INTO `history` VALUES ('5', '1', '5', '5.00');
INSERT INTO `history` VALUES ('6', '1', '4', '5.00');
INSERT INTO `history` VALUES ('7', '1', '3', '5.00');
INSERT INTO `history` VALUES ('8', '1', '2', '5.00');
INSERT INTO `history` VALUES ('9', '1', '1', '5.00');
INSERT INTO `history` VALUES ('10', '2', '9', '5.00');
INSERT INTO `history` VALUES ('11', '2', '8', '5.00');
INSERT INTO `history` VALUES ('12', '2', '7', '5.00');
INSERT INTO `history` VALUES ('13', '2', '6', '5.00');
INSERT INTO `history` VALUES ('14', '2', '5', '5.00');
INSERT INTO `history` VALUES ('15', '2', '4', '5.00');
INSERT INTO `history` VALUES ('16', '2', '3', '5.00');
INSERT INTO `history` VALUES ('17', '2', '2', '5.00');
INSERT INTO `history` VALUES ('18', '2', '1', '5.00');
INSERT INTO `history` VALUES ('19', '9', '9', '5.00');
INSERT INTO `history` VALUES ('20', '9', '8', '5.00');
INSERT INTO `history` VALUES ('21', '9', '7', '5.00');
INSERT INTO `history` VALUES ('22', '9', '6', '5.00');
INSERT INTO `history` VALUES ('23', '9', '4', '5.00');

-- ----------------------------
-- Table structure for `imgadv`
-- ----------------------------
DROP TABLE IF EXISTS `imgadv`;
CREATE TABLE `imgadv` (
  `id` int(11) NOT NULL auto_increment,
  `filename` varchar(255) default NULL COMMENT '图片文件名',
  `url` varchar(255) default NULL COMMENT '链接地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='轮播图表';

-- ----------------------------
-- Records of imgadv
-- ----------------------------
INSERT INTO `imgadv` VALUES ('6', 'f3179f40-6438-4678-ae8f-68b0a7628ee4.jpg', '#');
INSERT INTO `imgadv` VALUES ('7', '4669f6d0-87e7-4710-8f85-3f3ebd123a88.jpg', '#');

-- ----------------------------
-- Table structure for `inventory`
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL auto_increment,
  `productid` int(11) default NULL COMMENT '商品ID',
  `num` int(11) default NULL COMMENT '出入库数量',
  `flag` varchar(255) default NULL COMMENT '出入库标识',
  `savetime` varchar(255) default NULL COMMENT '日期',
  `delstatus` varchar(255) default NULL COMMENT '删除状态',
  PRIMARY KEY  (`id`),
  KEY `productid` (`productid`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES ('38', '4', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('39', '3', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('40', '2', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('41', '1', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('42', '9', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('43', '8', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('44', '7', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('45', '6', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('46', '5', '100', 'in', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('47', '8', '1', 'out', '2025-05-08', '0');
INSERT INTO `inventory` VALUES ('48', '6', '2', 'out', '2025-05-08', '0');

-- ----------------------------
-- Table structure for `loginip`
-- ----------------------------
DROP TABLE IF EXISTS `loginip`;
CREATE TABLE `loginip` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '操作员',
  `addr` varchar(255) default NULL COMMENT 'IP地址',
  `content` text COMMENT '内容',
  `savetime` varchar(255) default NULL COMMENT '时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginip
-- ----------------------------
INSERT INTO `loginip` VALUES ('800', 'admin', '127.0.0.1', '用户【admin】于2025-05-10 13:06:18退出系统！', '2025-05-10 13:06:18');
INSERT INTO `loginip` VALUES ('801', 'admin', '127.0.0.1', '用户【admin】于2025-05-10 13:21:31登录系统！', '2025-05-10 13:21:31');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `uname` varchar(255) default NULL COMMENT '登录名',
  `upass` varchar(255) default NULL COMMENT '密码',
  `tname` varchar(255) default NULL COMMENT '姓名',
  `filename` varchar(255) default NULL COMMENT '图片文件名',
  `tel` varchar(255) default NULL COMMENT '联系电话',
  `status` varchar(255) default NULL COMMENT '状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'H001', '123', '杨刚', '1fa0a806-f60d-4801-82c8-13d897203e5f.png', '13872961155', '正常');
INSERT INTO `member` VALUES ('2', 'H002', '123', '闫慧', '813bdfb5-76e1-4328-9ddc-c8340a0546ee.jpg', '13875962211', '正常');
INSERT INTO `member` VALUES ('9', 'H003', '123', '李娟', '6020a9f5-3d5a-4cc6-b556-868d4c5c5450.jpg', '13875962211', '正常');
INSERT INTO `member` VALUES ('14', 'H004', '123', '李强', '07f0b2ee-4249-47b6-825e-79efeb2682bf.jpg', '13875962211', '正常');

-- ----------------------------
-- Table structure for `menus`
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `id` int(11) NOT NULL auto_increment,
  `serial` int(11) default NULL COMMENT '序号',
  `name` varchar(255) default NULL COMMENT '名称',
  `icon` varchar(255) default NULL COMMENT '图标',
  `url` varchar(255) default NULL COMMENT '访问地址',
  `upid` int(11) default NULL COMMENT '上一级ID',
  `leaf` int(11) default NULL COMMENT '是否存在下一级菜单',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('2', '1', '系统管理', 'el-icon-setting', '#', '0', '0');
INSERT INTO `menus` VALUES ('3', '2', '系统用户', 'el-icon-user', '#', '0', '0');
INSERT INTO `menus` VALUES ('4', '3', '商品管理', 'el-icon-reading', '#', '0', '0');
INSERT INTO `menus` VALUES ('5', '1', '角色管理', null, 'roleList', '2', '0');
INSERT INTO `menus` VALUES ('6', '1', '菜单管理', null, 'menusList', '2', '0');
INSERT INTO `menus` VALUES ('8', '1', '新增角色', null, 'roleAdd', '5', '0');
INSERT INTO `menus` VALUES ('9', '2', '编辑角色', null, 'roleEdit', '5', '0');
INSERT INTO `menus` VALUES ('10', '3', '删除角色', null, 'roleDelete', '5', '0');
INSERT INTO `menus` VALUES ('11', '1', '新增菜单', null, 'menusAdd', '6', '0');
INSERT INTO `menus` VALUES ('12', '1', '编辑菜单', null, 'menusEdit', '6', '0');
INSERT INTO `menus` VALUES ('13', '3', '删除菜单', null, 'menusDelete', '6', '0');
INSERT INTO `menus` VALUES ('22', '1', '销售人员', null, 'userList', '3', '0');
INSERT INTO `menus` VALUES ('23', '1', '新增销售人员', null, 'userAdd', '22', '0');
INSERT INTO `menus` VALUES ('24', '2', '编辑销售人员', null, 'userEdit', '22', '0');
INSERT INTO `menus` VALUES ('25', '3', '删除销售人员', null, 'userDelete', '22', '0');
INSERT INTO `menus` VALUES ('26', '4', '分类管理', null, 'categoryList', '4', '0');
INSERT INTO `menus` VALUES ('27', '1', '新增分类', null, 'categoryAdd', '26', '0');
INSERT INTO `menus` VALUES ('28', '2', '编辑分类', null, 'categoryEdit', '26', '0');
INSERT INTO `menus` VALUES ('29', '3', '删除分类', null, 'categoryDelete', '26', '0');
INSERT INTO `menus` VALUES ('30', '5', '商品管理', null, 'productList', '4', '0');
INSERT INTO `menus` VALUES ('31', '1', '新增商品', null, 'productAdd', '30', '0');
INSERT INTO `menus` VALUES ('32', '2', '编辑商品', null, 'productEdit', '30', '0');
INSERT INTO `menus` VALUES ('33', '3', '删除商品', null, 'productDelete', '30', '0');
INSERT INTO `menus` VALUES ('34', '4', '添加库存', null, 'inventoryAdd', '30', '0');
INSERT INTO `menus` VALUES ('47', '5', '订单管理', 'el-icon-collection-tag', '#', '0', '0');
INSERT INTO `menus` VALUES ('48', '1', '订单管理', null, 'tOrderList', '47', '0');
INSERT INTO `menus` VALUES ('49', '1', '占位', null, '空', '48', '0');
INSERT INTO `menus` VALUES ('54', '5', '上下架权限（有）', null, 'updateIssj', '30', '0');
INSERT INTO `menus` VALUES ('55', '6', '上下架权限（无）', null, 'noUpdateIssj', '30', '0');
INSERT INTO `menus` VALUES ('56', '3', '公告管理', null, 'newsList', '2', '0');
INSERT INTO `menus` VALUES ('57', '1', '新增公告', null, 'newsAdd', '56', '0');
INSERT INTO `menus` VALUES ('58', '2', '编辑公告', null, 'newsEdit', '56', '0');
INSERT INTO `menus` VALUES ('59', '3', '删除公告', null, 'newsDelete', '56', '0');
INSERT INTO `menus` VALUES ('60', '4', '轮播图管理', null, 'imgadvList', '2', '0');
INSERT INTO `menus` VALUES ('61', '1', '新增轮播图', null, 'imgadvAdd', '60', '0');
INSERT INTO `menus` VALUES ('62', '2', '编辑轮播图', null, 'imgadvEdit', '60', '0');
INSERT INTO `menus` VALUES ('63', '3', '删除轮播图', null, 'imgadvDelete', '60', '0');
INSERT INTO `menus` VALUES ('64', '6', '评价管理', 'el-icon-reading', '#', '0', '0');
INSERT INTO `menus` VALUES ('65', '1', '商品评价', null, 'commentList', '64', '0');
INSERT INTO `menus` VALUES ('66', '1', '占位', null, '空', '65', '0');
INSERT INTO `menus` VALUES ('74', '8', '统计管理', 'el-icon-s-data', '#', '0', '0');
INSERT INTO `menus` VALUES ('75', '1', '统计分析', null, 'statisticsList', '74', '0');
INSERT INTO `menus` VALUES ('76', '1', '占位', null, '空', '75', '0');
INSERT INTO `menus` VALUES ('77', '2', '注册用户', null, 'memberList', '3', '0');
INSERT INTO `menus` VALUES ('78', '1', '删除用户', null, 'memberDelete', '77', '0');
INSERT INTO `menus` VALUES ('89', '5', '留言管理', null, 'messageList', '2', '0');
INSERT INTO `menus` VALUES ('90', '1', '回复留言', null, 'messageEdit', '89', '0');
INSERT INTO `menus` VALUES ('91', '2', '删除留言', null, 'messageDelete', '89', '0');
INSERT INTO `menus` VALUES ('92', '4', '销售人员审核', null, 'updateAudit', '22', '0');
INSERT INTO `menus` VALUES ('93', '7', '审核（有）', null, 'updateProductAudit', '30', '0');
INSERT INTO `menus` VALUES ('94', '8', '审核（无）', null, 'noupdateProductAudit', '30', '0');
INSERT INTO `menus` VALUES ('95', '6', '日志管理', null, 'loginipList', '2', '0');
INSERT INTO `menus` VALUES ('96', '1', '占位', null, '-', '95', '0');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '用户ID',
  `content` text COMMENT '留言内容',
  `savetime` varchar(255) default NULL COMMENT '时间',
  `hfcontent` text COMMENT '回复内容',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL COMMENT '标题',
  `filename` varchar(255) default NULL COMMENT '图片文件名',
  `content` text COMMENT '内容',
  `savetime` varchar(255) default NULL COMMENT '时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('5', '直购合并网上商城超市直购成交结果公告', '0c38e6d3-9e82-4f78-8b0c-f025fbef9815.jpg', '<p>直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告直购合并网上商城超市直购成交结果公告</p>', '2025-05-07 15:28:48');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '注册用户ID',
  `content` text COMMENT '内容',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL auto_increment,
  `roleid` int(11) default NULL COMMENT '角色ID',
  `menusid` int(11) default NULL COMMENT '菜单ID',
  `upmenusid` int(11) default NULL COMMENT '二级菜单ID',
  `topmenusid` int(11) default NULL COMMENT '一级菜单ID',
  `flag` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `roleid` (`roleid`),
  KEY `menusid` (`menusid`),
  KEY `upmenusid` (`upmenusid`),
  KEY `topmenusid` (`topmenusid`),
  CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_ibfk_2` FOREIGN KEY (`menusid`) REFERENCES `menus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_ibfk_3` FOREIGN KEY (`upmenusid`) REFERENCES `menus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_ibfk_4` FOREIGN KEY (`topmenusid`) REFERENCES `menus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('776', '2', '31', '30', '4', '3');
INSERT INTO `permission` VALUES ('777', '2', '32', '30', '4', '3');
INSERT INTO `permission` VALUES ('778', '2', '33', '30', '4', '3');
INSERT INTO `permission` VALUES ('779', '2', '34', '30', '4', '3');
INSERT INTO `permission` VALUES ('780', '2', '54', '30', '4', '3');
INSERT INTO `permission` VALUES ('781', '2', '94', '30', '4', '3');
INSERT INTO `permission` VALUES ('782', '2', '49', '48', '47', '3');
INSERT INTO `permission` VALUES ('783', '2', '66', '65', '64', '3');
INSERT INTO `permission` VALUES ('784', '2', '76', '75', '74', '3');
INSERT INTO `permission` VALUES ('810', '1', '8', '5', '2', '3');
INSERT INTO `permission` VALUES ('811', '1', '9', '5', '2', '3');
INSERT INTO `permission` VALUES ('812', '1', '10', '5', '2', '3');
INSERT INTO `permission` VALUES ('813', '1', '11', '6', '2', '3');
INSERT INTO `permission` VALUES ('814', '1', '12', '6', '2', '3');
INSERT INTO `permission` VALUES ('815', '1', '13', '6', '2', '3');
INSERT INTO `permission` VALUES ('816', '1', '57', '56', '2', '3');
INSERT INTO `permission` VALUES ('817', '1', '58', '56', '2', '3');
INSERT INTO `permission` VALUES ('818', '1', '59', '56', '2', '3');
INSERT INTO `permission` VALUES ('819', '1', '61', '60', '2', '3');
INSERT INTO `permission` VALUES ('820', '1', '62', '60', '2', '3');
INSERT INTO `permission` VALUES ('821', '1', '63', '60', '2', '3');
INSERT INTO `permission` VALUES ('822', '1', '90', '89', '2', '3');
INSERT INTO `permission` VALUES ('823', '1', '91', '89', '2', '3');
INSERT INTO `permission` VALUES ('824', '1', '96', '95', '2', '3');
INSERT INTO `permission` VALUES ('825', '1', '23', '22', '3', '3');
INSERT INTO `permission` VALUES ('826', '1', '24', '22', '3', '3');
INSERT INTO `permission` VALUES ('827', '1', '25', '22', '3', '3');
INSERT INTO `permission` VALUES ('828', '1', '92', '22', '3', '3');
INSERT INTO `permission` VALUES ('829', '1', '78', '77', '3', '3');
INSERT INTO `permission` VALUES ('830', '1', '27', '26', '4', '3');
INSERT INTO `permission` VALUES ('831', '1', '28', '26', '4', '3');
INSERT INTO `permission` VALUES ('832', '1', '29', '26', '4', '3');
INSERT INTO `permission` VALUES ('833', '1', '33', '30', '4', '3');
INSERT INTO `permission` VALUES ('834', '1', '54', '30', '4', '3');
INSERT INTO `permission` VALUES ('835', '1', '93', '30', '4', '3');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '名称',
  `filename` varchar(255) default NULL COMMENT '图片文件名',
  `categoryid1` int(11) default NULL COMMENT '一级分类ID',
  `categoryid2` int(11) default NULL COMMENT '二级分类ID',
  `content` text COMMENT '描述',
  `price` decimal(10,2) default NULL COMMENT '价格',
  `issj` varchar(255) default NULL COMMENT '上下架',
  `tprice` decimal(10,2) default NULL COMMENT '特价',
  `sellerid` int(11) default NULL COMMENT '商家ID',
  `audit` varchar(255) default NULL COMMENT '审核',
  PRIMARY KEY  (`id`),
  KEY `categoryid1` (`categoryid1`),
  KEY `categoryid2` (`categoryid2`),
  KEY `sellerid` (`sellerid`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`sellerid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryid1`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`categoryid2`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'OPPO K12x', '5e321358-e3df-49ef-9ffd-d13e9d818a65.jpg', '20', '21', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">OPPO K12x 8GB+256GB 钛空灰 政府国家补贴 120Hz旗舰级OLED直屏 80W闪充 5500mAh大电池 骁龙5G手机OPPO K12x 8GB+256GB 钛空灰 政府国家补贴 120Hz旗舰级OLED直屏 80W闪充 5500mAh大电池 骁龙5G手机OPPO K12x 8GB+256GB 钛空灰 政府国家补贴 120Hz旗舰级OLED直屏 80W闪充 5500mAh大电池 骁龙5G手机OPPO K12x 8GB+256GB 钛空灰 政府国家补贴 120Hz旗舰级OLED直屏 80W闪充 5500mAh大电池 骁龙5G手机OPPO K12x 8GB+256GB 钛空灰 政府国家补贴 120Hz旗舰级OLED直屏 80W闪充 5500mAh大电池 骁龙5G手机OPPO K12x 8GB+256GB 钛空灰 政府国家补贴 120Hz旗舰级OLED直屏 80W闪充 5500mAh大电池 骁龙5G手机</span></p>', '999.00', '已上架', '0.00', '3', '已通过');
INSERT INTO `product` VALUES ('2', '荣耀X60', '7164cd93-252c-4eba-b62a-9effeb8bdd17.jpg', '20', '21', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴荣耀X60 十面抗摔防水耐磨 5800mAh大电池 一亿像素 AI影像 8+128 海湖青 5G AI手机 国家补贴</span></p>', '1149.00', '已上架', '0.00', '3', '已通过');
INSERT INTO `product` VALUES ('3', ' OPPO A3i Plus', 'f33967be-73d5-46c2-9ca0-e2a78dcd491e.jpg', '20', '21', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\"> OPPO A3i Plus 12GB+256GB 冷晶紫 全新耐用战神 超硬核抗摔耐磨 OLED超亮阳光屏 5G智能手机 国家补贴 OPPO A3i Plus 12GB+256GB 冷晶紫 全新耐用战神 超硬核抗摔耐磨 OLED超亮阳光屏 5G智能手机 国家补贴 OPPO A3i Plus 12GB+256GB 冷晶紫 全新耐用战神 超硬核抗摔耐磨 OLED超亮阳光屏 5G智能手机 国家补贴 OPPO A3i Plus 12GB+256GB 冷晶紫 全新耐用战神 超硬核抗摔耐磨 OLED超亮阳光屏 5G智能手机 国家补贴 OPPO A3i Plus 12GB+256GB 冷晶紫 全新耐用战神 超硬核抗摔耐磨 OLED超亮阳光屏 5G智能手机 国家补贴 OPPO A3i Plus 12GB+256GB 冷晶紫 全新耐用战神 超硬核抗摔耐磨 OLED超亮阳光屏 5G智能手机 国家补贴</span></p>', '1299.00', '已上架', '0.00', '3', '已通过');
INSERT INTO `product` VALUES ('4', '华为手机华为mate70', 'afb6ab1b-ec32-4b45-843c-acdb3bc3b4e5.jpg', '20', '21', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">华为手机华为mate70 pro 店里选购 24期 分期 免息畅享 70X 双曲护眼屏 五星超耐摔 鸿蒙曲面屏手机 【雪域白】256GB 官方标配华为手机华为mate70 pro 店里选购 24期 分期 免息畅享 70X 双曲护眼屏 五星超耐摔 鸿蒙曲面屏手机 【雪域白】256GB 官方标配华为手机华为mate70 pro 店里选购 24期 分期 免息畅享 70X 双曲护眼屏 五星超耐摔 鸿蒙曲面屏手机 【雪域白】256GB 官方标配华为手机华为mate70 pro 店里选购 24期 分期 免息畅享 70X 双曲护眼屏 五星超耐摔 鸿蒙曲面屏手机 【雪域白】256GB 官方标配华为手机华为mate70 pro 店里选购 24期 分期 免息畅享 70X 双曲护眼屏 五星超耐摔 鸿蒙曲面屏手机 【雪域白】256GB 官方标配华为手机华为mate70 pro 店里选购 24期 分期 免息畅享 70X 双曲护眼屏 五星超耐摔 鸿蒙曲面屏手机 【雪域白】256GB 官方标配</span></p>', '2449.00', '已上架', '0.00', '3', '已通过');
INSERT INTO `product` VALUES ('5', '联想拯救者Y7000', '8d472743-fd8e-4877-9436-f94580de98f5.jpg', '20', '22', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%联想拯救者Y7000 电竞游戏笔记本电脑酷睿i7-13650HX 24G 512G RTX4060 144Hz电竞屏 国家补贴20%</span></p>', '699.00', '已上架', '0.00', '4', '已通过');
INSERT INTO `product` VALUES ('6', 'HALIWEI高性能金属轻薄本', '1a70a65e-e069-4770-b091-f843382dde21.jpg', '20', '22', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16Ultra1060独显+蓝光 32GB运存+1TB超速硬盘</span></p>', '2476.00', '已上架', '1200.00', '4', '已通过');
INSERT INTO `product` VALUES ('7', '学生手提', 'b72155f7-aedc-4b58-9ccf-8b97918e49ac.jpg', '20', '22', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16pro+4K窄边全面屏蓝光级 16GB运存+256G超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16pro+4K窄边全面屏蓝光级 16GB运存+256G超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16pro+4K窄边全面屏蓝光级 16GB运存+256G超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16pro+4K窄边全面屏蓝光级 16GB运存+256G超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16pro+4K窄边全面屏蓝光级 16GB运存+256G超速硬盘HALIWEI国行【2025款英特尔+酷睿i7】笔记本电脑高性能金属轻薄本网课学习设计便携商务办公游戏学生手提 【AI终端】英特尔16pro+4K窄边全面屏蓝光级 16GB运存+256G超速硬盘</span></p>', '1248.00', '已上架', '0.00', '4', '已通过');
INSERT INTO `product` VALUES ('8', '智能手环', '5bc7b5b4-273e-4b03-8d9c-1acdf4db1b98.jpg', '20', '23', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">智能手环运动计步器闹钟彩屏蓝牙小米手环手表多功能手机情侣男女通用 炫酷黑-工厂直供-质量保障智能手环运动计步器闹钟彩屏蓝牙小米手环手表多功能手机情侣男女通用 炫酷黑-工厂直供-质量保障智能手环运动计步器闹钟彩屏蓝牙小米手环手表多功能手机情侣男女通用 炫酷黑-工厂直供-质量保障智能手环运动计步器闹钟彩屏蓝牙小米手环手表多功能手机情侣男女通用 炫酷黑-工厂直供-质量保障智能手环运动计步器闹钟彩屏蓝牙小米手环手表多功能手机情侣男女通用 炫酷黑-工厂直供-质量保障</span></p>', '17.00', '已上架', '0.00', '4', '已通过');
INSERT INTO `product` VALUES ('9', '华为严选华为2024', '7dae9e39-54dc-44cf-be83-07483ede3d28.jpg', '20', '23', '<p><span style=\"color: rgb(102, 102, 102); background-color: rgb(255, 255, 255); font-size: 16px;\">华为严选华为2024最新款男款手表watch gt4智能手表男测心率血氧压力监测蓝牙通话微信消息NFC防水接打电话 网钢带全黑nfc门禁+接打电话华为严选华为2024最新款男款手表watch gt4智能手表男测心率血氧压力监测蓝牙通话微信消息NFC防水接打电话 网钢带全黑nfc门禁+接打电话华为严选华为2024最新款男款手表watch gt4智能手表男测心率血氧压力监测蓝牙通话微信消息NFC防水接打电话 网钢带全黑nfc门禁+接打电话华为严选华为2024最新款男款手表watch gt4智能手表男测心率血氧压力监测蓝牙通话微信消息NFC防水接打电话 网钢带全黑nfc门禁+接打电话华为严选华为2024最新款男款手表watch gt4智能手表男测心率血氧压力监测蓝牙通话微信消息NFC防水接打电话 网钢带全黑nfc门禁+接打电话华为严选华为2024最新款男款手表watch gt4智能手表男测心率血氧压力监测蓝牙通话微信消息NFC防水接打电话 网钢带全黑nfc门禁+接打电话</span></p>', '1629.00', '已上架', '1450.00', '4', '已通过');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL auto_increment,
  `memberid` int(11) default NULL COMMENT '注册用户ID',
  `commentid` int(11) default NULL COMMENT '评价ID',
  `content` text COMMENT '回复内容',
  `savetime` varchar(255) default NULL COMMENT '时间',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  KEY `commentid` (`commentid`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`commentid`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价回复表';

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '销售人员');

-- ----------------------------
-- Table structure for `t_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_detail`;
CREATE TABLE `t_detail` (
  `id` int(11) NOT NULL auto_increment,
  `ddno` varchar(255) default NULL COMMENT '订单号',
  `memberid` int(11) default NULL,
  `productid` int(11) default NULL COMMENT '商品ID',
  `num` int(11) default NULL COMMENT '数量',
  `status` varchar(255) default NULL,
  `savetime` varchar(255) default NULL COMMENT '日期',
  PRIMARY KEY  (`id`),
  KEY `productid` (`productid`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `t_detail_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_detail_ibfk_2` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of t_detail
-- ----------------------------
INSERT INTO `t_detail` VALUES ('35', '362450', '14', '6', '2', '交易完成', '2025-05-08');
INSERT INTO `t_detail` VALUES ('36', '362450', '14', '8', '1', '交易完成', '2025-05-08');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL auto_increment,
  `ddno` varchar(255) default NULL COMMENT '订单编号',
  `memberid` int(11) default NULL COMMENT '用户ID',
  `product_info` text COMMENT '商品信息',
  `total` decimal(10,2) default NULL COMMENT '金额',
  `address` varchar(255) default NULL COMMENT '收货地址',
  `savetime` varchar(255) default NULL COMMENT '时间',
  `status` varchar(255) default NULL COMMENT '状态',
  `sellerid` int(11) default NULL COMMENT '商家ID',
  PRIMARY KEY  (`id`),
  KEY `memberid` (`memberid`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('6', '362450', '14', 'HALIWEI高性能金属轻薄本 * 2<br/>智能手环 * 1<br/>', '2417.00', '洪山区雄楚大道632号 李强 13875962211', '2025-05-08 22:32:26', '交易完成', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `uname` varchar(255) default NULL COMMENT '登录名',
  `upass` varchar(255) default NULL COMMENT '密码',
  `tname` varchar(255) default NULL COMMENT '姓名',
  `filename` varchar(255) default NULL COMMENT '图片文件名',
  `tel` varchar(255) default NULL COMMENT '电话',
  `status` varchar(255) default NULL COMMENT '状态',
  `roleid` int(11) default NULL COMMENT '角色ID',
  `audit` varchar(255) default NULL COMMENT '审核',
  PRIMARY KEY  (`id`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', 'admin', '25f3f4a4-3d1e-4004-9bc7-1bb3ed009916.jpg', '13875961123', '正常', '1', '已通过');
INSERT INTO `user` VALUES ('3', 'S001', '123', '陈波', '7e74bede-6df2-40d1-9809-a905cc42c0af.jpg', '13875962211', '正常', '2', '已通过');
INSERT INTO `user` VALUES ('4', 'S002', '123', '李军', '0496dbc6-5c39-42d8-a0a3-38eb22344a58.jpg', '13875962211', '正常', '2', '已通过');
INSERT INTO `user` VALUES ('5', 'S003', '123', '刘华', '4378e6c1-76b5-4b6a-9773-700be51d02ee.jpg', '13875962299', '正常', '2', '已通过');
INSERT INTO `user` VALUES ('6', 'S004', '123', '李娜', 'dcc052bb-9728-41d3-8b6c-a745297db8da.jpg', '13875962211', '正常', '2', '已通过');
