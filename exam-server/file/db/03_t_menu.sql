drop table if exists t_menu;
CREATE TABLE v_exam.t_menu
(
    menu_id bigint(20) PRIMARY KEY NOT NULL,
    url varchar(64),
    path varchar(64),
    component varchar(64),
    name varchar(64),
    icon_cls varchar(64),
    parent_id bigint(20),
    creator bigint(20) COMMENT '创建者',
    updater bigint(20) COMMENT '更新者',
    version int(5) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用',
    is_del bit(1) DEFAULT true NOT NULL COMMENT '是否删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL on update current_timestamp COMMENT '更新时间',
    sort tinyint(3) COMMENT '排序字段'
);

INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (0, '/', '/home', 'Home', '顶级菜单', null, -1, null, null, 0, false, '2019-03-26 11:06:32', '2019-03-26 11:06:32', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1, '/', '/home', 'Home', '系统管理', 'fa fa-windows', 0, 0, 0, 0, false, '2019-03-26 09:40:45', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (2, '/system/config', '/system/config', 'SystemConfig', '基础配置', 'fa fa-windows', 1, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251300211335368704, null, null, null, 'ooo', null, 0, null, null, 0, false, '2019-03-26 14:32:42', '2019-03-26 14:32:42', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155456, '/', '/home', null, 'yyyyy', null, 0, null, null, 0, true, '2019-03-26 14:37:32', '2019-03-26 14:37:32', null);


(1, '/', null, null, '所有', null, null, null, 0, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-26 21:06:02', 0);
(3, '/', '/home', 'Home', '学生管理', 'fa fa-address-card-o', null, 1, 1, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-25 20:18:42', 0);
(4, '/', '/home', 'Home', '院系管理', 'fa fa-money', null, 1, 1, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-25 20:18:42', 0);
(5, '/', '/home', 'Home', '专业管理', 'fa fa-bar-chart', null, 1, 1, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-25 20:18:42', 0);
(6, '/', '/home', 'Home', '用户资料', 'fa fa-money', null, null, 1, null, null, 0, 0, '2018-12-25 20:44:47', '2018-12-25 21:30:08', 1);
(9, '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, null, 1, null, null, 0, 0, '2018-12-25 20:44:47', '2018-12-25 21:30:08', 1);
(10, '/system/config', '/system/config', 'SystemConfig', '基础配置', 'fa fa-windows', null, null, 9, null, null, 0, 0, '2018-12-25 20:45:43', '2018-12-25 21:30:08', 1);
(30, '/user/list', '/user/student', 'UserTeacher', '教师列表', 'fa fa-address-card-o', null, null, 6, null, null, 0, 0, '2018-12-26 23:15:33', '2018-12-27 02:53:36', 2);