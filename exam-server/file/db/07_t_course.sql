drop table if exists t_course;
create table t_course(
   course_id bigint(20) primary key not null comment '课程编号',
   course_name varchar(40) not null default '' comment '课程名称',
   major_id bigint(20) comment '课程所属专业',
   creator bigint(20) COMMENT '创建者',
   updater bigint(20) COMMENT '更新者',
   version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用',
   is_del bit(1) DEFAULT true NOT NULL COMMENT '是否删除',
   create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
   update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL on update current_timestamp COMMENT '更新时间'
) default charset = utf8 comment '课程表';