package org.exam.bean.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * @author heshiyuan
 */
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

    private Integer creator;

    private Integer updater;

    private Integer version;
    private List<TRole> roles = new ArrayList<>();
    private List<TMenu> children = new ArrayList<>();
    private Byte sort;
}