drop table if exists t_user_clazz_course;
create table t_user_clazz_course(
  id bigint(20) primary key comment '主键',
  clazz_id bigint(20) not null default 0 comment '班级id',
  course_id bigint(20) not null default 0 comment '课程id',
  user_id bigint(20) not null default 0 comment '用户id',
  is_del bit(1) not null default true comment '删除标识',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator int(20) COMMENT '创建者',
  updater int(20) COMMENT '更新者',
  version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) charset = utf8 comment '用户课程关系表';
