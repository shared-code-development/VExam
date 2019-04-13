package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TQuestionJudge;
import org.exam.bean.entity.TQuestionJudgeExample;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TQuestionJudgeMapper;
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
public class QuestionJudgeService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TQuestionJudgeMapper tQuestionJudgeMapper;

    public PageInfo<List<TQuestionJudge>> questionJudgeList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionJudgeExample questionJudgeExample = new TQuestionJudgeExample();
        questionJudgeExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TQuestionJudge> questionJudgeList = tQuestionJudgeMapper.selectByExample(questionJudgeExample);
        return PageUtils.nullListHandler(questionJudgeList);
    }

    public TQuestionJudge get(Long questionJudgeId){
        return tQuestionJudgeMapper.selectByPrimaryKey(questionJudgeId);
    }
    public int insert(TQuestionJudge questionJudge){
        return tQuestionJudgeMapper.insertSelective(questionJudge);
    }
    public int update(TQuestionJudge questionJudge){
        return tQuestionJudgeMapper.updateByPrimaryKeySelective(questionJudge);
    }
    public int delete(Long id){
        return tQuestionJudgeMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TQuestionJudgeExample questionJudgeExample = new TQuestionJudgeExample();
        questionJudgeExample.createCriteria().andJudgeIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tQuestionJudgeMapper.deleteByExample(questionJudgeExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
