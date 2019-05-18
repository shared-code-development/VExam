package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TQuestionChoice;
import org.exam.bean.entity.TQuestionChoiceExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TQuestionChoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
public class QuestionChoiceService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TQuestionChoiceMapper tQuestionChoiceMapper;
    @Autowired
    UKeyWorker choiceIdWorker;
    public PageInfo<TQuestionChoice> questionChoiceList(Integer pageNum, Integer pageSize, String keyWords, Long courseId){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionChoiceExample questionChoiceExample = new TQuestionChoiceExample();
        TQuestionChoiceExample.Criteria criteria = questionChoiceExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andChoiceNameLike("%"+keyWords+"%");
        }
        if(Objects.nonNull(courseId)){
            criteria.andCourseIdEqualTo(courseId);
        }
        List<TQuestionChoice> questionChoiceList = tQuestionChoiceMapper.selectByExample(questionChoiceExample);
        return PageUtils.nullListHandler(questionChoiceList);
    }

    public TQuestionChoice get(Long questionChoiceId){
        return tQuestionChoiceMapper.selectByPrimaryKey(questionChoiceId);
    }
    public int insert(TQuestionChoice questionChoice){
        questionChoice.setChoiceId(choiceIdWorker.getId());
        questionChoice.setCreator(0L);
        questionChoice.setUpdater(0L);
        return tQuestionChoiceMapper.insertSelective(questionChoice);
    }
    public int update(TQuestionChoice questionChoice){
        return tQuestionChoiceMapper.updateByPrimaryKeySelective(questionChoice);
    }
    public int delete(Long id){
        return tQuestionChoiceMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TQuestionChoiceExample questionChoiceExample = new TQuestionChoiceExample();
        questionChoiceExample.createCriteria().andChoiceIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tQuestionChoiceMapper.deleteByExample(questionChoiceExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
