package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TClazz;
import org.exam.bean.entity.TClazzExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TClazzMapper;
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
public class ClazzService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TClazzMapper tClazzMapper;
    @Autowired
    UKeyWorker clazzIdWorker;
    public PageInfo<List<TClazz>> clazzList(Integer pageNum, Integer pageSize, String keyWords){
        PageHelper.startPage(pageNum, pageSize);
        TClazzExample clazzExample = new TClazzExample();
        TClazzExample.Criteria criteria = clazzExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andClassNameLike("%"+keyWords+"%");
        }
        return PageUtils.nullListHandler(tClazzMapper.selectByExample(clazzExample));
    }

    public TClazz get(Long clazzId){
        return tClazzMapper.selectByPrimaryKey(clazzId);
    }
    public int insert(TClazz clazz){
        clazz.setClazzId(clazzIdWorker.getId());
        clazz.setCreator(1L);
        clazz.setUpdater(1L);
        return tClazzMapper.insertSelective(clazz);
    }
    public int update(TClazz clazz){
        return tClazzMapper.updateByPrimaryKeySelective(clazz);
    }
    public int delete(Long id){
        return tClazzMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TClazzExample clazzExample = new TClazzExample();
        clazzExample.createCriteria().andClazzIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tClazzMapper.deleteByExample(clazzExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
