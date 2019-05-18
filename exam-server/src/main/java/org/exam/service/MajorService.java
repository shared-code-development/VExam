package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TMajor;
import org.exam.bean.entity.TMajorExample;
import org.exam.bean.vo.MajorVo;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TMajorMapper;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    UKeyWorker majorIdWorker;
    public PageInfo<TMajor> majorList(Integer pageNum, Integer pageSize, String keyWords){
        PageHelper.startPage(pageNum, pageSize);
        TMajorExample majorExample = new TMajorExample();
        TMajorExample.Criteria criteria = majorExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andMajorNameLike("%"+keyWords+"%");
        }
        return PageUtils.nullListHandler(tMajorMapper.selectByExample(majorExample));
    }

    public TMajor get(Long majorId){
        return tMajorMapper.selectByPrimaryKey(majorId);
    }
    public int insert(TMajor major){
        major.setMajorId(majorIdWorker.getId());
        major.setCreator(1L);
        major.setUpdater(1L);
        return tMajorMapper.insertSelective(major);
    }
    public int update(MajorVo major){
        TMajor newMajor = new TMajor();
        BeanUtils.copyProperties(major, newMajor);
        return tMajorMapper.updateByPrimaryKeySelective(newMajor);
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
