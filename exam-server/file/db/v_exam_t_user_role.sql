CREATE TABLE v_exam.t_user_role
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11),
    role_id int(11)
);
CREATE INDEX idx_user_id ON v_exam.t_user_role (user_id);
CREATE INDEX idx_role_id ON v_exam.t_user_role (role_id);
INSERT INTO v_exam.t_user_role (id, user_id, role_id) VALUES (1, 1, 1);





INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155460, '/', '/home', 'Home', '题库管理', 'fa fa-windows', 0, 0, 0, 0, false, '2019-03-26 09:40:45', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155457, '/', '/home', 'Home', '试卷管理', 'fa fa-windows', 0, 0, 0, 0, false, '2019-03-26 09:40:45', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155458, '/', '/home', 'Home', '统计管理', 'fa fa-windows', 0, 0, 0, 0, false, '2019-03-26 09:40:45', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155459, '/', '/home', 'Home', '个人资料', 'fa fa-windows', 0, 0, 0, 0, false, '2019-03-26 09:40:45', '2019-03-26 10:06:41', null);


INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155461, '', '/person/info', 'PersonInfo', '个人信息', 'fa fa-windows', 1251302663275155459, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155462, '', '/system/dic', 'SystemDic', '字典', 'fa fa-windows', 1, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155463, '', '/system/academy', 'SystemAcademy', '学院', 'fa fa-windows', 1, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155464, '', '/system/major', 'SystemMajor', '专业', 'fa fa-windows', 1, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155465, '', '/system/clazz', 'SystemClazz', '班级', 'fa fa-windows', 1, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);

INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155466, '', '/question/choice', 'QuestionChoice', '选择题', 'fa fa-windows', 1251302663275155460, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155467, '', '/question/judge', 'QuestionJudge', '判断提', 'fa fa-windows', 1251302663275155460, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155468, '', '/question/sample/answer', 'QuestionSampleAnswer', '简答题', 'fa fa-windows', 1251302663275155460, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);

INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155469, '', '/paper/info', 'PaperInfo', '试卷', 'fa fa-windows', 1251302663275155457, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);
INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155470, '', '/paper/examine', 'PaperExamine', '考试', 'fa fa-windows', 1251302663275155457, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);

INSERT INTO v_exam.t_menu (menu_id, url, path, component, name, icon_cls, parent_id, creator, updater, version, is_del, create_time, update_time, sort) VALUES (1251302663275155471, '', '/statistics/score', 'StatisticsScore', '成绩统计', 'fa fa-windows', 1251302663275155458, null, null, 0, false, '2019-03-26 10:06:41', '2019-03-26 10:06:41', null);