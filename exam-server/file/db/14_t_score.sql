drop table if exists t_score;
create table t_score(
    score_id bigint(20) not null primary key comment 'id',
    score numeric(4) comment '分数',
    paper_id bigint(20),
    start_time timestamp DEFAULT CURRENT_TIMESTAMP comment '考试开始时间',
    end_time timestamp DEFAULT CURRENT_TIMESTAMP comment '考试结束时间',
    is_del bit(1) not null default true comment '删除标识',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(20) COMMENT '创建者',
    updater int(20) COMMENT '更新者',
    version int(20) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '成绩表';