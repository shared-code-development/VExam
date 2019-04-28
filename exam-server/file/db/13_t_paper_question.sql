drop table if exists t_paper_question;
create table t_paper_question(
    id bigint(20) not null primary key comment '主键',
    question_id bigint(20) comment '题库id，可能是选择题，判断题，简答题任意表中的主键',
    dic_type_id bigint(20) comment '题型：选择题，判断题，简答题',
    sort int(4) not null comment '序号',
    is_del bit(1) not null default true comment '删除标识',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator bigint(20) COMMENT '创建者',
    updater bigint(20) COMMENT '更新者',
    version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '试卷题库表';