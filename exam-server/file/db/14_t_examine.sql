drop table if exists t_examine;
create table t_examine(
    examine_id bigint(20) not null primary key comment 'id',
    examine_name varchar(100) not null default '' comment '名称',
    paper_id bigint(20),
    start_time timestamp DEFAULT CURRENT_TIMESTAMP comment '考试开始时间',
    end_time timestamp DEFAULT CURRENT_TIMESTAMP comment '考试结束时间',
    is_del bit(1) not null default true comment '删除标识',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(20) COMMENT '创建者',
    updater int(20) COMMENT '更新者',
    version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '考试表';