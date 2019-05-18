package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TQuestionJudge;
import org.exam.bean.entity.TQuestionJudgeExample;
import org.exam.bean.vo.QuestionJudgeVo;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TQuestionJudgeMapper;
import org.springframework.beans.BeanUtils;
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
public class QuestionJudgeService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TQuestionJudgeMapper tQuestionJudgeMapper;
    @Autowired
    UKeyWorker judgeIdWorker;

    public PageInfo<TQuestionJudge> questionJudgeList(Integer pageNum, Integer pageSize, String keyWords, Long courseId){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionJudgeExample questionJudgeExample = new TQuestionJudgeExample();
        TQuestionJudgeExample.Criteria criteria = questionJudgeExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andJudgeNameLike("%"+keyWords+"%");
        }
        if(Objects.nonNull(courseId)){
            criteria.andCourseIdEqualTo(courseId);
        }
        List<TQuestionJudge> questionJudgeList = tQuestionJudgeMapper.selectByExample(questionJudgeExample);
        return PageUtils.nullListHandler(questionJudgeList);
    }

    public TQuestionJudge get(Long questionJudgeId){
        return tQuestionJudgeMapper.selectByPrimaryKey(questionJudgeId);
    }
    public int insert(QuestionJudgeVo questionJudgeVo){
        TQuestionJudge questionJudge = new TQuestionJudge();
        questionJudge.setJudgeId(judgeIdWorker.getId());
        questionJudge.setCreator(0L);
        questionJudge.setUpdater(0L);
        return tQuestionJudgeMapper.insertSelective(questionJudge);
    }
    public int update(QuestionJudgeVo questionJudgeVo){
        TQuestionJudge questionJudge = new TQuestionJudge();
        BeanUtils.copyProperties(questionJudgeVo, questionJudge);
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
