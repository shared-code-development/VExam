package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.common.GlobalConstant;
import org.exam.mapper.TAcademyMapper;
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
public class AcademyService {

    @Autowired
    TAcademyMapper tAcademyMapper;

    public PageInfo<List<TAcademy>> academyList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TAcademyExample academyExample = new TAcademyExample();
        academyExample.createCriteria().andIsDelEqualTo(GlobalConstant.IS_DEL_ARRAY);
        List<TAcademy> academyList = tAcademyMapper.selectByExample(academyExample);
        if(null!=academyList&&academyList.size()>0){
            return new PageInfo(academyList);
        }else{
            return new PageInfo(new ArrayList());
        }
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
}
