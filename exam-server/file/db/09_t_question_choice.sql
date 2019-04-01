drop table if exists t_question_choice;
create table t_question_choice(
                                choice_id bigint(20) not null primary key comment '编号',
                                choice_name varchar(100) not null default '' comment '名称',
                                choice_type bit(1) not null default true comment '是否是单选，默认单选',
                                option_a varchar(100) comment 'A选项',
                                option_b varchar(100) comment 'B选项',
                                option_c varchar(100) comment 'C选项',
                                option_d varchar(100) comment 'D选项',
                                single_answer enum('A', 'B', 'C', 'D') comment '单选答案',
                                multi_answer varchar(4) comment '多选答案',
                                course_id bigint(20),
                                is_del bit(1) not null default true comment '删除标识',
                                create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
                                update_time timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间',
                                creator int(20) COMMENT '创建者',
                                updater int(20) COMMENT '更新者',
                                version int(20) DEFAULT 0 NOT NULL COMMENT '版本号，乐观锁专用'
) default charset = utf8 comment '选择题';