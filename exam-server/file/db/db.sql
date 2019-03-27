create table t_course(
  id bigint(11) primary key auto_increment comment '主键',
  course_no bigint(20) not null default 0 comment '课程编号',
  course_name varchar(40) not null default '' comment '课程名称',
  is_del tinyint(1) not null default 0 comment '删除标识：0-未删除；1-已删除'
) default charset = utf8 comment '课程表';
create table t_major(
  id bigint(11) primary key auto_increment comment '主键',
  major_no bigint(20) not null default 0 comment '专业编号',
  major_name varchar(40) not null default '' comment '专业名称',
  academy_id bigint(11) not null default 0 comment '学院id',
  is_del tinyint(1) not null default 0 comment '删除标识：0-未删除；1-已删除'
) default charset = utf8 comment '专业表';
create table t_user_major_course(
  id bigint(11) primary key auto_increment comment '主键',
  major_id bigint(11) not null default 0 comment '专业id',
  course_id bigint(11) not null default 0 comment '课程id',
  user_id bigint(11) not null default 0 comment '用户id'
) charset = utf8 comment '课程关系表';

create table t_academy(
  id bigint(11) primary key auto_increment comment '主键',
  academy_no bigint(20) not null default 0 comment '学院编号',
  academy_name varchar(40) not null default '' comment '学院名称',
  is_del tinyint(1) not null default 0 comment '删除标识：0-未删除；1-已删除'
) default charset = utf8 comment '学院表';

