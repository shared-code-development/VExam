drop table if exists t_exam_paper;
create table t_exam_paper(
    paper_id bigint(20) not null primary key comment '试卷id',
    paper_name varchar(100) not null default '' comment '名称',
    course_id bigint(20),
    is_del bit(1) not null default true comment '删除标识',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
    creator int(20) COMMENT '创建者',
    updater int(20) COMMENT '更新者',
    version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '试卷表';