drop table if exists t_academy;
create table t_academy(
  id bigint(11) primary key auto_increment comment '主键',
  academy_no bigint(20) not null default 0 comment '学院编号',
  academy_name varchar(40) not null default '' comment '学院名称',
  is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator int(11) COMMENT '创建者',
  updater int(11) COMMENT '更新者',
  version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '学院表';

drop table if exists t_major;
create table t_major(
  id bigint(11) primary key auto_increment comment '主键',
  major_no bigint(20) not null default 0 comment '专业编号',
  major_name varchar(40) not null default '' comment '专业名称',
  academy_id bigint(11) not null default 0 comment '学院id',
  is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator int(11) COMMENT '创建者',
  updater int(11) COMMENT '更新者',
  version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '专业表';

drop table if exists t_clazz;
create table t_clazz(
  id bigint(11) primary key auto_increment comment '主键',
  major_no bigint(20) not null default 0 comment '班级编号',
  major_name varchar(40) not null default '' comment '班级名称',
  academy_id bigint(11) not null default 0 comment '专业id',
  is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator int(11) COMMENT '创建者',
  updater int(11) COMMENT '更新者',
  version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '班级表';

drop table if exists t_course;
create table t_course(
  id bigint(11) primary key auto_increment comment '主键',
  course_no bigint(20) not null default 0 comment '课程编号',
  course_name varchar(40) not null default '' comment '课程名称',
  is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator int(11) COMMENT '创建者',
  updater int(11) COMMENT '更新者',
  version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '课程表';

drop table if exists t_user_major_course;
drop table if exists t_user_clazz_course;
create table t_user_clazz_course(
  id bigint(11) primary key auto_increment comment '主键',
  clazz_id bigint(11) not null default 0 comment '班级id',
  course_id bigint(11) not null default 0 comment '课程id',
  user_id bigint(11) not null default 0 comment '用户id',
  is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator int(11) COMMENT '创建者',
  updater int(11) COMMENT '更新者',
  version int(11) DEFAULT '0' NOT NULL COMMENT '版本号，乐观锁专用'
) charset = utf8 comment '用户课程关系表';



