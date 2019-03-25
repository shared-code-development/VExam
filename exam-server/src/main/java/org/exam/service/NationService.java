package org.exam.service;

import net.bytebuddy.asm.Advice;
import org.exam.bean.entity.TNation;
import org.exam.bean.entity.TNationExample;
import org.exam.mapper.TNationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.service
 * @date 25/03/2019 23:23
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Service
public class NationService {
    @Autowired
    TNationMapper tNationMapper;
    public List<TNation> nationList(){
        return tNationMapper.selectByExample(new TNationExample());
    }
}
