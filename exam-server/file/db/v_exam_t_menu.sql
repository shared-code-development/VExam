CREATE TABLE v_exam.t_menu
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    url varchar(64),
    path varchar(64),
    component varchar(64),
    name varchar(64),
    icon_cls varchar(64),
    keep_alive tinyint(1),
    require_auth tinyint(1),
    parent_id int(11),
    creater int(11) COMMENT '创建者',
    updater int(11) COMMENT '更新者',
    version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用',
    is_del tinyint(1) DEFAULT '0' NOT NULL COMMENT '是否删除：0-启用；1-删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    sort tinyint(3) DEFAULT '0' NOT NULL COMMENT '排序字段'
);
CREATE INDEX idx_parent_id ON v_exam.t_menu (parent_id);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (1, '/', null, null, '所有', null, null, null, 0, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-26 21:06:02', 0);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (3, '/', '/home', 'Home', '学生管理', 'fa fa-address-card-o', null, 1, 1, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-25 20:18:42', 0);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (4, '/', '/home', 'Home', '院系管理', 'fa fa-money', null, 1, 1, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-25 20:18:42', 0);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (5, '/', '/home', 'Home', '专业管理', 'fa fa-bar-chart', null, 1, 1, null, null, 0, 0, '2018-12-25 20:18:37', '2018-12-25 20:18:42', 0);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (6, '/', '/home', 'Home', '用户资料', 'fa fa-money', null, null, 1, null, null, 0, 0, '2018-12-25 20:44:47', '2018-12-25 21:30:08', 1);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (9, '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, null, 1, null, null, 0, 0, '2018-12-25 20:44:47', '2018-12-25 21:30:08', 1);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (10, '/system/config', '/system/config', 'SystemConfig', '基础配置', 'fa fa-windows', null, null, 9, null, null, 0, 0, '2018-12-25 20:45:43', '2018-12-25 21:30:08', 1);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (30, '/user/list', '/user/student', 'UserTeacher', '教师列表', 'fa fa-address-card-o', null, null, 6, null, null, 0, 0, '2018-12-26 23:15:33', '2018-12-27 02:53:36', 2);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (31, null, null, null, '吞吞吐吐', null, null, null, 1, null, null, 0, 0, '2019-03-23 19:38:02', '2019-03-23 19:38:02', 1);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (32, null, null, null, 'sfdf ', null, null, null, 3, null, null, 0, 0, '2019-03-23 19:43:42', '2019-03-23 19:43:42', 0);
INSERT INTO v_exam.t_menu (id, url, path, component, name, icon_cls, keep_alive, require_auth, parent_id, creater, updater, version, is_del, create_time, update_time, sort) VALUES (33, null, null, null, 'ppppppp', null, null, null, 32, null, null, 0, 0, '2019-03-23 19:44:55', '2019-03-23 19:44:55', 0);