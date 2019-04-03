package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TMajor;
import org.exam.bean.entity.TMajorExample;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TMajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
public class MajorService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TMajorMapper tMajorMapper;

    public PageInfo<List<TMajor>> majorList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TMajorExample majorExample = new TMajorExample();
        majorExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TMajor> majorList = tMajorMapper.selectByExample(majorExample);
        if(null!=majorList&&majorList.size()>0){
            return new PageInfo(majorList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public TMajor get(Long majorId){
        return tMajorMapper.selectByPrimaryKey(majorId);
    }
    public int insert(TMajor major){
        return tMajorMapper.insertSelective(major);
    }
    public int update(TMajor major){
        return tMajorMapper.updateByPrimaryKeySelective(major);
    }
    public int delete(Long id){
        return tMajorMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TMajorExample majorExample = new TMajorExample();
        majorExample.createCriteria().andMajorIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tMajorMapper.deleteByExample(majorExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
