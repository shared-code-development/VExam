package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.mapper.TMajorMapper;
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
public class MajorService {
    @Autowired
    TMajorMapper tMajorMapper;

    public PageInfo<List<TMajor>> majorList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<TMajor> list = tMajorMapper.selectByExample(new TMajorExample());
        if(null!=list&&list.size()>0){
            return new PageInfo(list);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public int insert(TMajor major){
        return tMajorMapper.insertSelective(major);
    }

    public int delete(Long id){
        return tMajorMapper.deleteByPrimaryKey(id);
    }

    public int update(TMajor major){
        return tMajorMapper.updateByPrimaryKeySelective(major);
    }
}
