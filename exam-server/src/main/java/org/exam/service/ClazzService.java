package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TClazz;
import org.exam.bean.entity.TClazzExample;
import org.exam.mapper.TClazzMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
public class ClazzService {
    @Autowired
    TClazzMapper tClazzMapper;

    public PageInfo<List<TClazz>> list(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TClazzExample academyExample = new TClazzExample();
        academyExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TClazz> academyList = tClazzMapper.selectByExample(academyExample);
        if(null!=academyList&&academyList.size()>0){
            return new PageInfo(academyList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public int insert(TClazz academy){
        return tClazzMapper.insertSelective(academy);
    }
    public int update(TClazz academy){
        return tClazzMapper.updateByPrimaryKeySelective(academy);
    }
    public int delete(Long id){
        return tClazzMapper.deleteByPrimaryKey(id);
    }
}
