CREATE TABLE v_exam.t_dic_type
(
  dic_type_id bigint(20) PRIMARY KEY NOT NULL comment '分布式id',
  dic_type_name varchar(20) not null unique comment '字典-类型名称',
  is_del bit(1) not null default 0 comment '删除标识：0-未删除；1-已删除',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
  update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
  creator bigint(20) not null COMMENT '创建者',
  updater bigint(20) not null COMMENT '更新者',
  version int(5) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
);