drop table if exists t_question_choice;
create table t_question_choice(
    id bigint(11) primary key auto_increment comment '主键',
    choice_no bigint(20) not null default 0 comment '编号',
    choice_name varchar(100) not null default '' comment '名称',
    choice_type bit(1) not null default 0 comment '是否是单选，默认单选',
    option_a varchar(100) comment 'A选项',
    option_b varchar(100) comment 'B选项',
    option_c varchar(100) comment 'C选项',
    option_d varchar(100) comment 'D选项',
    single_answer enum('A', 'B', 'C', 'D') comment '单选答案',
    multi_answer varchar(4) comment '多选答案',
    course_id bigint(11),
    is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(11) COMMENT '创建者',
    updater int(11) COMMENT '更新者',
    version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '选择题';

drop table if exists t_question_judge;
create table t_question_judge(
    id bigint(11) primary key auto_increment comment '主键',
    judge_no bigint(20) not null default 0 comment '编号',
    judge_name varchar(100) not null default '' comment '名称',
    answer bit(1) comment '答案',
    is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(11) COMMENT '创建者',
    updater int(11) COMMENT '更新者',
    version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '判断题';

drop table if exists t_question_short_answer;
create table t_question_short_answer(
    id bigint(11) primary key auto_increment comment '主键',
    short_answer_no bigint(20) not null default 0 comment '编号',
    short_answer_name varchar(100) not null default '' comment '名称',
    answer varchar(200) comment '答案',
    is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(11) COMMENT '创建者',
    updater int(11) COMMENT '更新者',
    version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '简答题';