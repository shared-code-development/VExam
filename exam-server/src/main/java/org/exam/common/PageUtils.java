package org.exam.common;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.common
 * @date 31/03/2019 10:59
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class PageUtils {

    public static <T> PageInfo<List<T>> nullListHandler(List<T> list){
        if(null!=list&&list.size()>0){
            return new PageInfo(list);
        }else{
            return new PageInfo<>(new ArrayList<>());
        }
    }
}
