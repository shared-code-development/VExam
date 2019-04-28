drop table if exists t_question_judge;
create table t_question_judge(
                               judge_id bigint(20) primary key not null comment '编号',
                               judge_name varchar(100) not null default '' comment '名称',
                               answer bit(1) not null comment '答案',
                               course_id bigint(20),
                               is_del bit(1) not null default true comment '删除标识：0-未删除；1-已删除',
                               create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                               update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
                               creator bigint(20) COMMENT '创建者',
                               updater bigint(20) COMMENT '更新者',
                               version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '判断题';