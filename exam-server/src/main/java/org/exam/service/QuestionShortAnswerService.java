package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.bean.entity.TQuestionShortAnswerExample;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TQuestionShortAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    public PageInfo<List<TQuestionShortAnswer>> questionShortAnswerList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionShortAnswerExample questionShortAnswerExample = new TQuestionShortAnswerExample();
        questionShortAnswerExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TQuestionShortAnswer> questionShortAnswerList = tQuestionShortAnswerMapper.selectByExample(questionShortAnswerExample);
        return PageUtils.nullListHandler(questionShortAnswerList);
    }

    public TQuestionShortAnswer get(Long questionShortAnswerId){
        return tQuestionShortAnswerMapper.selectByPrimaryKey(questionShortAnswerId);
    }
    public int insert(TQuestionShortAnswer questionShortAnswer){
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
