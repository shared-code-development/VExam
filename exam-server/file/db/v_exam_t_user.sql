CREATE TABLE v_exam.t_user
(
    id int(11) PRIMARY KEY NOT NULL COMMENT '主键id' AUTO_INCREMENT,
    user_id varchar(18) COMMENT '用户编号',
    name varchar(32) COMMENT '姓名',
    phone char(11) COMMENT '手机号码',
    tele_phone varchar(16) COMMENT '住宅电话',
    address varchar(64) COMMENT '联系地址',
    is_del tinyint(1) DEFAULT '0' NOT NULL,
    username varchar(255) COMMENT '用户名',
    password varchar(255) COMMENT '密码',
    userface varchar(255),
    remark varchar(255),
    create_time bigint(16) DEFAULT '0' NOT NULL COMMENT '创建时间',
    update_time bigint(16) DEFAULT '0' NOT NULL COMMENT '更新时间',
    creater int(11) COMMENT '创建者',
    updater int(11) COMMENT '更新者',
    version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用',
    sex tinyint(1) DEFAULT '0' NOT NULL COMMENT '性别：1-男；2-女；0-保密',
    id_card varchar(18) DEFAULT '' NOT NULL COMMENT '身份证号',
    nation_id bigint(11) DEFAULT '0' NOT NULL COMMENT '民族',
    marital_status varchar(2) DEFAULT '' NOT NULL COMMENT '婚姻状况：1-已婚；2-未婚；3-离异',
    native_place varchar(100) DEFAULT '' NOT NULL COMMENT '籍贯',
    birthday bigint(16) DEFAULT '0' NOT NULL COMMENT '出生日期',
    email varchar(50) DEFAULT '' NOT NULL COMMENT '电子邮箱',
    enrollment_date bigint(16) DEFAULT '0' NOT NULL COMMENT '入学日期',
    graduation_date bigint(16) DEFAULT '0' NOT NULL COMMENT '毕业日期'
);
INSERT INTO v_exam.t_user (id, user_id, name, phone, tele_phone, address, is_del, username, password, userface, remark, create_time, update_time, creater, updater, version, sex, id_card, nation_id, marital_status, native_place, birthday, email, enrollment_date, graduation_date) VALUES (1, '1', '系统管理员', '18568887789', '010-82881234', '北京昌平', 0, 'admin', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', null, 20181225014253, 20181225014253, 0, 0, 0, 0, '', 0, '', '', 0, '', 0, 0);