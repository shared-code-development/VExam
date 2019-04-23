-- we don't know how to generate schema v_exam (class Schema) :(
create table t_academy
(
  academy_id bigint not null comment '学院编号'
    primary key,
  academy_name varchar(40) not null comment '学院名称',
  creator bigint null comment '创建者',
  updater bigint null comment '更新者',
  version int(5) default '0' not null comment '版本号，乐观锁专用',
  is_del bit default true not null comment '是否删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
  comment '学院表'
;

create table t_clazz
(
  clazz_id bigint default '0' not null comment '班级编号'
    primary key,
  class_name varchar(40) default '' not null comment '班级名称',
  major_id bigint default '0' not null comment '专业id',
  creator bigint null comment '创建者',
  updater bigint null comment '更新者',
  version int(5) default '0' not null comment '版本号，乐观锁专用',
  is_del bit default true not null comment '是否删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
  comment '班级表'
;

create table t_course
(
  course_id bigint not null comment '课程编号'
    primary key,
  course_name varchar(40) default '' not null comment '课程名称',
  major_id bigint null comment '课程所属专业',
  creator bigint null comment '创建者',
  updater bigint null comment '更新者',
  version int(5) default '0' not null comment '版本号，乐观锁专用',
  is_del bit default true not null comment '是否删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
  comment '课程表'
;

create table t_dic
(
  dic_id bigint not null comment '分布式id'
    primary key,
  dic_key varchar(20) not null comment '字典-键',
  dic_value varchar(40) null comment '字典-值',
  dic_type_id bigint null,
  parent_dic_id bigint null comment '父级字典id',
  is_del bit default true not null comment '删除标识：0-未删除；1-已删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
  creator bigint(11) not null comment '创建者',
  updater bigint(11) not null comment '更新者',
  version bigint(11) default '0' not null comment '版本号，乐观锁专用',
  constraint dic_key
  unique (dic_key)
)
  comment '字典表'
;

create table t_dic_type
(
  dic_type_id bigint not null comment '分布式id'
    primary key,
  dic_type_name varchar(20) not null comment '字典-类型名称',
  is_del bit default true not null comment '删除标识：0-未删除；1-已删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
  creator bigint(11) not null comment '创建者',
  updater bigint(11) not null comment '更新者',
  version bigint(11) default '0' not null comment '版本号，乐观锁专用',
  constraint dic_type_name
  unique (dic_type_name)
)
;

create table t_major
(
  major_id bigint not null comment '专业编号'
    primary key,
  major_name varchar(40) default '' not null comment '专业名称',
  academy_id bigint default '0' not null comment '学院id',
  creator bigint null comment '创建者',
  updater bigint null comment '更新者',
  version int(5) default '0' not null comment '版本号，乐观锁专用',
  is_del bit default true not null comment '是否删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
  comment '专业表'
;

create table t_menu
(
  menu_id bigint not null
    primary key,
  url varchar(64) null,
  path varchar(64) null,
  component varchar(64) null,
  name varchar(64) null,
  icon_cls varchar(64) null,
  parent_id bigint null,
  creator bigint null comment '创建者',
  updater bigint null comment '更新者',
  version int(5) default '0' not null comment '版本号，乐观锁专用',
  is_del bit default true not null comment '是否删除：0-启用；1-删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
  sort tinyint(3) null comment '排序字段'
)
;

create table t_menu_role
(
  id int auto_increment
    primary key,
  menu_id int null,
  role_id int null
)
;

create index idx_menu_id
  on t_menu_role (menu_id)
;

create index idx_role_id
  on t_menu_role (role_id)
;

create table t_user
(
  user_id bigint not null comment '用户id，分布式id'
    primary key,
  name varchar(10) null comment '姓名',
  mobile_phone char(11) null comment '手机号码',
  telephone varchar(16) null comment '住宅电话',
  address varchar(64) null comment '联系地址',
  username varchar(20) null comment '用户名',
  password varchar(64) null comment '密码',
  sex char null comment '性别：1-男；2-女；0-保密，关联字典表主键',
  id_card varchar(18) null comment '身份证号',
  nation_id bigint null comment '民族',
  user_face varchar(64) null comment '用户图像',
  marital_status bigint null comment '婚姻状况：1-已婚；2-未婚；3-离异,关联字典表主键',
  native_place varchar(100) default '' not null comment '籍贯',
  birthday bigint(16) default '0' not null comment '出生日期',
  email varchar(50) default '' not null comment '电子邮箱',
  enrollment_date bigint(16) default '0' not null comment '入学日期：学生专用',
  graduation_date bigint(16) default '0' not null comment '毕业日期：学生专用',
  is_del bit default true not null comment '删除标识：0-未删除；1-已删除',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
  creator bigint not null comment '创建者',
  updater bigint not null comment '更新者',
  version bigint(5) default '0' not null comment '版本号，乐观锁专用'
)
;

create table t_user_clazz_course
(
  id bigint not null comment '主键'
    primary key,
  clazz_id bigint default '0' not null comment '班级id',
  course_id bigint default '0' not null comment '课程id',
  user_id bigint default '0' not null comment '用户id',
  start_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '上课开始时间',
  end_time timestamp default '0000-00-00 00:00:00' not null comment '上课结束时间',
  is_del bit default true not null comment '删除标识',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
  creator int(20) null comment '创建者',
  updater int(20) null comment '更新者',
  version int(5) default '0' not null comment '版本号，乐观锁专用'
)
  comment '用户课程关系表'
;

INSERT INTO v_exam.t_academy (academy_id, academy_name, creator, updater, version, is_del, create_time, update_time) VALUES (1264995765567754240, '软件学院', 1, 1, 0, true, '2019-03-27 17:58:31', '2019-03-27 17:58:31');
INSERT INTO v_exam.t_academy (academy_id, academy_name, creator, updater, version, is_del, create_time, update_time) VALUES (1264995853161598976, '计算机学院', 1, 1, 0, true, '2019-03-27 17:58:42', '2019-03-27 17:58:42');
INSERT INTO v_exam.t_clazz (clazz_id, class_name, major_id, creator, updater, version, is_del, create_time, update_time) VALUES (1265629093023322112, 'Java1001', 1265035533324390400, 1, 1, 0, true, '2019-03-28 00:00:00', '2019-03-28 00:00:00');
INSERT INTO v_exam.t_clazz (clazz_id, class_name, major_id, creator, updater, version, is_del, create_time, update_time) VALUES (1265634969595547648, 'Java1002', 1265035533324390400, 1, 1, 0, true, '2019-03-28 00:44:36', '2019-03-28 00:44:36');
INSERT INTO v_exam.t_course (course_id, course_name, major_id, creator, updater, version, is_del, create_time, update_time) VALUES (1266146773971243008, 'C语言编程', 1265035533324390400, 1, 1, 0, true, '2019-03-28 04:23:32', '2019-03-28 04:23:32');
INSERT INTO v_exam.t_dic (dic_id, dic_key, dic_value, dic_type_id, parent_dic_id, is_del, create_time, update_time, creator, updater, version) VALUES (1262144439456370688, '1', '男', 1259998655600267264, null, false, '2019-03-27 16:52:50', '2019-03-27 16:52:50', 1, 1, 0);
INSERT INTO v_exam.t_dic (dic_id, dic_key, dic_value, dic_type_id, parent_dic_id, is_del, create_time, update_time, creator, updater, version) VALUES (1262150212429287424, '2', '女', 1259998655600267264, null, true, '2019-03-27 17:04:18', '2019-03-27 17:04:18', 1, 1, 0);
INSERT INTO v_exam.t_dic (dic_id, dic_key, dic_value, dic_type_id, parent_dic_id, is_del, create_time, update_time, creator, updater, version) VALUES (1262163765357973504, 'admin', '超级管理员', 1259998750760636416, null, true, '2019-03-27 17:31:14', '2019-03-27 17:31:14', 1, 1, 0);
INSERT INTO v_exam.t_dic (dic_id, dic_key, dic_value, dic_type_id, parent_dic_id, is_del, create_time, update_time, creator, updater, version) VALUES (1262163911302975488, 'teacher', '教师', 1259998750760636416, null, true, '2019-03-27 17:31:31', '2019-03-27 17:31:31', 1, 1, 0);
INSERT INTO v_exam.t_dic (dic_id, dic_key, dic_value, dic_type_id, parent_dic_id, is_del, create_time, update_time, creator, updater, version) VALUES (1262163984418082816, 'student', '学生', 1259998750760636416, null, true, '2019-03-27 17:31:40', '2019-03-27 17:31:40', 1, 1, 0);
INSERT INTO v_exam.t_dic_type (dic_type_id, dic_type_name, is_del, create_time, update_time, creator, updater, version) VALUES (1259998655600267264, 'sex', true, '2019-03-27 11:09:21', '2019-03-27 11:10:38', 1, 1, 0);
INSERT INTO v_exam.t_dic_type (dic_type_id, dic_type_name, is_del, create_time, update_time, creator, updater, version) VALUES (1259998750760636416, 'role', true, '2019-03-27 11:09:32', '2019-03-27 11:10:38', 1, 1, 0);
INSERT INTO v_exam.t_major (major_id, major_name, academy_id, creator, updater, version, is_del, create_time, update_time) VALUES (1265035533324390400, 'Java技术', 1264995765567754240, 1, 1, 0, true, '2019-03-27 19:17:32', '2019-03-28 00:45:02');
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (0, '/', '/home', 'Home', '顶级菜单', null, -1, null, null, 0, true, '2019-03-26 11:06:32', '2019-03-27 11:21:47', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1, '/', '/home', 'Home', '系统管理', 'fa fa-windows', 0, 0, 0, 0, true, '2019-03-26 09:40:45', '2019-03-27 11:21:47', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (2, '/system/config', '/system/config', 'SystemConfig', '基础配置', 'fa fa-windows', 1, null, null, 0, true, '2019-03-26 10:06:41', '2019-03-27 11:21:47', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1259949471136223232, '/', '/system/dic', 'SystemDic', '字典', 'fa fa-windows', 1, null, null, 0, true, '2019-03-27 09:31:38', '2019-03-27 11:21:22', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1260661253257564160, '/', '/system/academy', 'SystemAcademy', '学院', 'fa fa-windows', 1, null, null, 0, true, '2019-03-27 11:30:18', '2019-03-27 11:30:18', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1265006956859625472, '/', '/system/major', 'SystemMajor', '专业', 'fa fa-windows', 1, null, null, 0, true, '2019-03-27 18:20:45', '2019-03-27 18:20:45', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1265007296128487424, '/', '/system/clazz', 'SystemClazz', '班级', 'fa fa-windows', 1, null, null, 0, true, '2019-03-27 18:21:26', '2019-03-27 18:21:26', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266105917313912832, '/', '/', 'Home', '课程管理', null, 0, null, null, 0, true, '2019-03-28 03:02:19', '2019-03-28 03:02:19', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266106551492677632, '/', '/course/manage', 'CourseManage', '课程', null, 1266105917313912832, null, null, 0, true, '2019-03-28 03:03:34', '2019-03-28 03:03:34', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266106734372720640, '/', '/course/select', 'CourseSelect', '选课', null, 1266105917313912832, null, null, 0, true, '2019-03-28 03:03:56', '2019-03-28 03:03:56', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266107093757464576, '/', '/', 'Home', '题库管理', null, 0, null, null, 0, true, '2019-03-28 03:04:39', '2019-03-28 03:04:39', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266107513590517760, '/', '/question/choice', 'QuestionChoice', '选择题', null, 1266107093757464576, null, null, 0, true, '2019-03-28 03:05:29', '2019-03-28 03:05:29', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266107846911856640, '/', '/question/judge', 'QuestionJudge', '判断题', null, 1266107093757464576, null, null, 0, true, '2019-03-28 03:06:09', '2019-03-28 03:06:09', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266108355664154624, '/', '/question/sample/answer', 'QuestionSampleAnswer', '简答题', null, 1266107093757464576, null, null, 0, true, '2019-03-28 03:07:09', '2019-03-28 03:07:09', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266108724838404096, '/', '/', 'Home', '个人资料', null, 0, null, null, 0, true, '2019-03-28 03:07:53', '2019-03-28 03:07:53', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1266109067823419392, '/', '/user/profile', 'UserProfile', '个人信息', null, 1266108724838404096, null, null, 0, true, '2019-03-28 03:08:34', '2019-03-28 03:08:34', null);