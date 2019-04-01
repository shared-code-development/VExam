drop table if exists t_academy;
create table t_academy(
  academy_id bigint(20) primary key comment '学院编号',
  academy_name varchar(40) not null comment '学院名称',
  creator bigint(20) COMMENT '创建者',
  updater bigint(20) COMMENT '更新者',
  version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用',
  is_del bit(1) DEFAULT true NOT NULL COMMENT '是否删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL on update current_timestamp COMMENT '更新时间'
) default charset = utf8 comment '学院表';