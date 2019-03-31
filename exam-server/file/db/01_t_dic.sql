CREATE TABLE v_exam.t_dic
(
    dic_id bigint(20) PRIMARY KEY NOT NULL comment '分布式id',
    dic_key varchar(20) not null unique comment '字典-键',
    dic_value varchar(40) comment '字典-值',
    dic_type_id varchar(20) comment '字典类型',
    is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator bigint(20) not null COMMENT '创建者',
    updater bigint(20) not null COMMENT '更新者',
    version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
);

# 性别：1-男；2-女；0-保密
# 婚姻状况：1-已婚；2-未婚；3-离异
# INSERT INTO v_exam.t_role (id, name, nameZh) VALUES (1, 'ROLE_admin', '管理员权限');
# INSERT INTO v_exam.t_role (id, name, nameZh) VALUES (2, 'ROLE_teacher', '教师权限');
# INSERT INTO v_exam.t_role (id, name, nameZh) VALUES (3, 'ROLE_student', '学生权限');
#
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (1, '汉族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (2, '蒙古族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (3, '回族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (4, '藏族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (5, '维吾尔族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (6, '苗族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (7, '彝族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (8, '壮族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (9, '布依族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (10, '朝鲜族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (11, '满族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (12, '侗族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (13, '瑶族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (14, '白族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (15, '土家族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (16, '哈尼族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (17, '哈萨克族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (18, '傣族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (19, '黎族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (20, '傈僳族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (21, '佤族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (22, '畲族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (23, '高山族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (24, '拉祜族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (25, '水族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (26, '东乡族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (27, '纳西族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (28, '景颇族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (29, '柯尔克孜族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (30, '土族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (31, '达斡尔族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (32, '仫佬族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (33, '羌族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (34, '布朗族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (35, '撒拉族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (36, '毛难族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (37, '仡佬族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (38, '锡伯族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (39, '阿昌族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (40, '普米族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (41, '塔吉克族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (42, '怒族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (43, '乌孜别克族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (44, '俄罗斯族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (45, '鄂温克族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (46, '崩龙族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (47, '保安族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (48, '裕固族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (49, '京族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (50, '塔塔尔族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (51, '独龙族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (52, '鄂伦春族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (53, '赫哲族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (54, '门巴族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (55, '珞巴族');
# INSERT INTO v_exam.t_dic (nation_id, name) VALUES (56, '基诺族');