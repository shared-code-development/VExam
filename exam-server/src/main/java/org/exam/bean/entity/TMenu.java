package org.exam.bean.entity;

import lombok.Data;

import java.util.List;
@Data
public class TMenu {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private Byte keepAlive;

    private Byte requireAuth;

    private Integer parentId;

    private Byte isDel;

    private Long createTime;

    private Long updateTime;

    private Integer creater;

    private Integer updater;

    private Integer version;
    private List<TRole> roles;
    private List<TMenu> children;

}