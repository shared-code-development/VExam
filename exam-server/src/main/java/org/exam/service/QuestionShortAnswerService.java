package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.bean.entity.TQuestionShortAnswerExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TQuestionShortAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
public class QuestionShortAnswerService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TQuestionShortAnswerMapper tQuestionShortAnswerMapper;
    @Autowired
    UKeyWorker sampleAnswerIdWorker;

    public PageInfo<TQuestionShortAnswer> questionShortAnswerList(Integer pageNum, Integer pageSize, String keyWords, Long courseId){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionShortAnswerExample questionShortAnswerExample = new TQuestionShortAnswerExample();
        TQuestionShortAnswerExample.Criteria criteria = questionShortAnswerExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andShortAnswerNameLike("%"+keyWords+"%");
        }
        if(Objects.nonNull(courseId)){
            criteria.andCourseIdEqualTo(courseId);
        }
        List<TQuestionShortAnswer> questionShortAnswerList = tQuestionShortAnswerMapper.selectByExample(questionShortAnswerExample);
        return PageUtils.nullListHandler(questionShortAnswerList);
    }

    public TQuestionShortAnswer get(Long questionShortAnswerId){
        return tQuestionShortAnswerMapper.selectByPrimaryKey(questionShortAnswerId);
    }
    public int insert(TQuestionShortAnswer questionShortAnswer){
        questionShortAnswer.setShortAnswerId(sampleAnswerIdWorker.getId());
        questionShortAnswer.setCreator(0L);
        questionShortAnswer.setUpdater(0L);
        return tQuestionShortAnswerMapper.insertSelective(questionShortAnswer);
    }
    public int update(TQuestionShortAnswer questionShortAnswer){
        return tQuestionShortAnswerMapper.updateByPrimaryKeySelective(questionShortAnswer);
    }
    public int delete(Long id){
        return tQuestionShortAnswerMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TQuestionShortAnswerExample questionShortAnswerExample = new TQuestionShortAnswerExample();
        questionShortAnswerExample.createCriteria().andShortAnswerIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tQuestionShortAnswerMapper.deleteByExample(questionShortAnswerExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
