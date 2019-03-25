CREATE TABLE v_exam.t_role
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(64),
    nameZh varchar(64) COMMENT '角色名称'
);
INSERT INTO v_exam.t_role (id, name, nameZh) VALUES (1, 'ROLE_admin', '管理员权限');
INSERT INTO v_exam.t_role (id, name, nameZh) VALUES (2, 'ROLE_teacher', '教师权限');
INSERT INTO v_exam.t_role (id, name, nameZh) VALUES (3, 'ROLE_student', '学生权限');