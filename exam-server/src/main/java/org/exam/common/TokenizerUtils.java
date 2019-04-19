package org.exam.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.common
 * @date 19/04/2019 20:49
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class TokenizerUtils {
    /**
     * 智能分词器
     * @param keyWords
     * @return
     */
    public static List<String> tokenizer(String keyWords){
        if(StringUtils.isBlank(keyWords)) return new ArrayList<>();
        List<String> returnList = new ArrayList<>();
        returnList.add(keyWords);
        int length = keyWords.length();
        return returnList;
    }
}
