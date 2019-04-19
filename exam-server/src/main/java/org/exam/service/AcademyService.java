package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TAcademy;
import org.exam.bean.entity.TAcademyExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TAcademyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
@Transactional
public class AcademyService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TAcademyMapper tAcademyMapper;
    @Autowired
    UKeyWorker academyIdWorker;
    public PageInfo<List<TAcademy>> academyList(Integer pageNum, Integer pageSize, String keyWords){
        PageHelper.startPage(pageNum, pageSize);
        TAcademyExample academyExample = new TAcademyExample();
        TAcademyExample.Criteria criteria = academyExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andAcademyNameLike("%"+keyWords+"%");
        }
        List<TAcademy> academyList = tAcademyMapper.selectByExample(academyExample);
        return PageUtils.nullListHandler(academyList);
    }

    public TAcademy get(Long academyId){
        return tAcademyMapper.selectByPrimaryKey(academyId);
    }
    public int insert(TAcademy academy){
        academy.setAcademyId(academyIdWorker.getId());
        academy.setCreator(1L);
        academy.setUpdater(1L);
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
