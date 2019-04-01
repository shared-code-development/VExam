drop table if exists t_question_short_answer;
create table t_question_short_answer(
    short_answer_id bigint(20) not null primary key comment '编号',
    short_answer_name varchar(100) not null default '' comment '名称',
    answer varchar(200) comment '答案',
    course_id bigint(20),
    is_del bit(1) not null default true comment '删除标识',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(20) COMMENT '创建者',
    updater int(20) COMMENT '更新者',
    version int(20) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '简答题';