SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '路径',
  `manu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `menu_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `is_parent` tinyint(4) NULL DEFAULT 0 COMMENT '是否为父节点',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父节点',
  `is_blank` tinyint(4) NULL DEFAULT NULL COMMENT '是否新标签页打开',
  `css_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `gmt_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 'TYPE_PAGE', '/dashboard', 'Dashboard', 1, 0, 0, 0, 'fa-dashboard', '2018-06-12 21:09:30', '2018-07-04 21:33:53', 1);
INSERT INTO `sys_menu` VALUES (2, 'TYPE_DROPDOWN', NULL, '系统管理', 2, 1, 0, 0, 'fa-sliders', '2018-06-13 23:45:07', '2018-07-04 21:33:53', 1);
INSERT INTO `sys_menu` VALUES (3, 'TYPE_PAGE', '/au/sysRole', '角色管理', 1, 0, 2, 0, 'fa-users', '2018-07-08 21:58:31', '2018-07-08 21:58:33', 1);
INSERT INTO `sys_menu` VALUES (4, 'TYPE_PAGE', '/au/user', '用户管理', 2, 0, 2, 0, 'fa-user', '2018-06-14 00:12:46', '2018-07-04 21:34:12', 1);
INSERT INTO `sys_menu` VALUES (5, 'TYPE_PAGE', '/au/menu', '菜单管理', 3, 0, 2, 0, 'fa-bars', '2018-06-30 21:37:22', '2018-07-04 21:34:12', 1);

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '所属角色',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `gmt_user_id` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES (1, 1, 1, '2018-06-12 21:34:30', '2018-06-12 21:34:33', NULL);
INSERT INTO `sys_menu_role` VALUES (2, 1, 2, '2018-06-13 23:46:21', '2018-06-13 23:46:24', NULL);
INSERT INTO `sys_menu_role` VALUES (3, 1, 3, '2018-06-13 23:46:32', '2018-06-13 23:46:35', NULL);
INSERT INTO `sys_menu_role` VALUES (4, 1, 4, '2018-06-14 00:13:05', '2018-06-14 00:13:08', NULL);
INSERT INTO `sys_menu_role` VALUES (5, 1, 5, '2018-06-30 21:37:45', '2018-06-30 21:37:47', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父角色id',
  `role_order` int(11) NOT NULL COMMENT '排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `gmt_user_id` int(11) NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', '超级管理员', 0, 1, '2018-06-06 22:35:07', '2018-06-06 22:35:10', NULL);
INSERT INTO `sys_role` VALUES (2, 'ROLE_USER', '普通用户', 1, 2, '2018-06-06 22:37:26', '2018-06-06 22:37:29', NULL);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `gmt_user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1, 1, '2018-06-07 21:02:03', '2018-06-07 21:02:06', NULL);
INSERT INTO `sys_role_user` VALUES (2, 2, 1, '2018-06-07 21:02:19', '2018-06-07 21:02:22', NULL);
INSERT INTO `sys_role_user` VALUES (3, 2, 2, '2018-06-18 16:44:46', '2018-06-18 16:44:49', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `gmt_user_id` int(11) NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', 'c4ca4238a0b923820dcc509a6f75849b', 'STATE_NORMAL', '111@126.com', '2018-06-06 22:39:03', '2018-06-06 22:39:05', NULL);
INSERT INTO `sys_user` VALUES (2, 'user', '普通用户', 'c4ca4238a0b923820dcc509a6f75849b', 'STATE_NORMAL', NULL, '2018-06-15 20:30:37', '2018-06-15 20:30:39', NULL);

SET FOREIGN_KEY_CHECKS = 1;
