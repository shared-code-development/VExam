package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TAcademy;
import org.exam.bean.entity.TAcademyExample;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TAcademyMapper;
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
public class AcademyService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TAcademyMapper tAcademyMapper;

    public PageInfo<List<TAcademy>> academyList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TAcademyExample academyExample = new TAcademyExample();
        academyExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TAcademy> academyList = tAcademyMapper.selectByExample(academyExample);
        if(null!=academyList&&academyList.size()>0){
            return new PageInfo(academyList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public TAcademy get(Long academyId){
        return tAcademyMapper.selectByPrimaryKey(academyId);
    }
    public int insert(TAcademy academy){
        return tAcademyMapper.insertSelective(academy);
    }
    public int update(TAcademy academy){
        return tAcademyMapper.updateByPrimaryKeySelective(academy);
    }
    public int delete(Long id){
        return tAcademyMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TAcademyExample academyExample = new TAcademyExample();
        academyExample.createCriteria().andAcademyIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tAcademyMapper.deleteByExample(academyExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
