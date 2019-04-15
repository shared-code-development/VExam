package org.exam.bean.dto;

import lombok.Data;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.bean.dto
 * @date 15/04/2019 22:39
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Data
public class DicTreeNode {

    private Long dicId;
    private String dicName;
    private List<DicTreeNode> children;

}
