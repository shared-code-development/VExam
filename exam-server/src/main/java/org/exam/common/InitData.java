package org.exam.common;

import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TDic;
import org.exam.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.common
 * @date 13/05/2019 20:28
 */
@Component
public class InitData {
    @Autowired
    DicService dicService;

    public Map<String, TDic> dicMap = new HashMap<>();
    public Map<String, Long> dicIdMap = new HashMap<>();
//    @PostConstruct
    public void init(){
        PageInfo<TDic> pageInfo = dicService.list(1, 1000);
        List<TDic> list = pageInfo.getList();
        dicMap = list.stream().collect(Collectors.toMap(TDic::getDicValue, Function.identity()));
        dicIdMap = list.stream().collect(Collectors.toMap(TDic::getDicValue, TDic::getDicId));
    }
}
