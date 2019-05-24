package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.*;
import org.exam.bean.vo.HandleGourpExamPaperVo;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.InitData;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Transactional
@Service
public class ExamPaperService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TExamPaperMapper tExamPaperMapper;
    @Autowired
    UKeyWorker examPaperIdWorker;
    @Autowired
    UKeyWorker paperQuestionIdWorker;
    @Autowired
    TPaperQuestionMapper tPaperQuestionMapper;
    @Autowired
    TQuestionChoiceMapper tQuestionChoiceMapper;
    @Autowired
    TQuestionJudgeMapper tQuestionJudgeMapper;
    @Autowired
    TQuestionShortAnswerMapper tQuestionShortAnswerMapper;

    public PageInfo<TExamPaper> list(Integer pageNum, Integer pageSize, String keyWords){
        PageHelper.startPage(pageNum, pageSize);
        TExamPaperExample questionShortAnswerExample = new TExamPaperExample();
        TExamPaperExample.Criteria criteria = questionShortAnswerExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andPaperNameLike("%"+keyWords+"%");
        }
        List<TExamPaper> questionShortAnswerList = tExamPaperMapper.selectByExample(questionShortAnswerExample);
        return PageUtils.nullListHandler(questionShortAnswerList);
    }

    public TExamPaper get(Long questionShortAnswerId){
        return tExamPaperMapper.selectByPrimaryKey(questionShortAnswerId);
    }
    public int insert(TExamPaper questionShortAnswer){
        questionShortAnswer.setPaperId(examPaperIdWorker.getId());
        questionShortAnswer.setCreator(0L);
        questionShortAnswer.setUpdater(0L);
        return tExamPaperMapper.insertSelective(questionShortAnswer);
    }
    public int update(TExamPaper questionShortAnswer){
        return tExamPaperMapper.updateByPrimaryKeySelective(questionShortAnswer);
    }
    public int delete(Long id){
        return tExamPaperMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TExamPaperExample questionShortAnswerExample = new TExamPaperExample();
        questionShortAnswerExample.createCriteria().andPaperIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tExamPaperMapper.deleteByExample(questionShortAnswerExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
    @Autowired
    InitData ininData;
    /**
     *
     * @param handleGourpExamPaperVo
     * @return
     */
    public Integer groupExamPaper(HandleGourpExamPaperVo handleGourpExamPaperVo){
        AtomicInteger atomicInteger = new AtomicInteger(0);
        handleGourpExamPaperVo.getQuestionIds().stream().forEach(id -> {
            TPaperQuestion paperQuestion = new TPaperQuestion();
            TPaperQuestionExample example = new TPaperQuestionExample();
            TPaperQuestionExample.Criteria criteria = example.createCriteria();
            if(StringUtils.equals("choice", handleGourpExamPaperVo.getQuestionType())){
                criteria.andDicTypeIdEqualTo(1282382195935678464L);
                paperQuestion.setDicTypeId(1282382195935678464L);
            }else if(StringUtils.equals("judge", handleGourpExamPaperVo.getQuestionType())){
                criteria.andDicTypeIdEqualTo(1282382342652432384L);
                paperQuestion.setDicTypeId(1282382342652432384L);
            }else if(StringUtils.equals("sampleAnswer", handleGourpExamPaperVo.getQuestionType())){
                criteria.andDicTypeIdEqualTo(1282382477885181952L);
                paperQuestion.setDicTypeId(1282382477885181952L);
            }
            criteria.andQuestionIdEqualTo(id);
            List<TPaperQuestion> list = tPaperQuestionMapper.selectByExample(example);
            if(null!=list&&list.size()>0){
                paperQuestion = list.get(0);
                if(paperQuestion.getIsDel() == false){
                    paperQuestion.setIsDel(true);
                    paperQuestion.setUpdater(null);
                    tPaperQuestionMapper.updateByPrimaryKeySelective(paperQuestion);
                    atomicInteger.incrementAndGet();
                }
            }else{
                paperQuestion.setId(paperQuestionIdWorker.getId());
                paperQuestion.setPaperId(handleGourpExamPaperVo.getPaperId());
                paperQuestion.setQuestionId(id);
                tPaperQuestionMapper.insertSelective(paperQuestion);
                atomicInteger.incrementAndGet();
            }
        });
        return atomicInteger.get();
    }

    public Map<String, Object> getPaperQuestion(Long paperId){
        Map<String, Object> returnMap = new HashMap<>();
        AtomicInteger serialNumber = new AtomicInteger(0);
        List<Map<String, Object>> choiceList = new ArrayList<>();
        getQuestionChoiceOfPaper(paperId).stream().forEach(entity -> {
            Map<String, Object> innerMap = new HashMap<>();
            innerMap.put("serialNumber", serialNumber.incrementAndGet());
            innerMap.put("choiceName", entity.getChoiceName());
            innerMap.put("choiceId", entity.getChoiceId().toString());
            innerMap.put("answer", entity.getAnswer());
            innerMap.put("optionA", entity.getOptionA());
            innerMap.put("optionB", entity.getOptionB());
            innerMap.put("optionC", entity.getOptionC());
            innerMap.put("optionD", entity.getOptionD());
            choiceList.add(innerMap);
        });
        returnMap.put("choiceList", choiceList);

        List<Map<String, Object>> judgeList = new ArrayList<>();
        getQuestionJudgeOfPaper(paperId).stream().forEach(entity -> {
            Map<String, Object> innerMap = new HashMap<>();
            innerMap.put("serialNumber", serialNumber.incrementAndGet());
            innerMap.put("judgeName", entity.getJudgeName());
            innerMap.put("judgeId", entity.getJudgeId().toString());
            innerMap.put("answer", entity.getAnswer());
            judgeList.add(innerMap);
        });
        returnMap.put("judgeList", judgeList);

        List<Map<String, Object>> shortAnswerList = new ArrayList<>();
        getQuestionSampleAnswerOfPaper(paperId).stream().forEach(entity -> {
            Map<String, Object> innerMap = new HashMap<>();
            innerMap.put("serialNumber", serialNumber.incrementAndGet());
            innerMap.put("shortAnswerName", entity.getShortAnswerName());
            innerMap.put("shortAnswerId", entity.getShortAnswerId().toString());
            innerMap.put("answer", entity.getAnswer());
            shortAnswerList.add(innerMap);
        });
        returnMap.put("shortAnswerList", shortAnswerList);
        return returnMap;
    }

    public List<TQuestionChoice> getQuestionChoiceOfPaper(Long paperId){
        TPaperQuestionExample paperQuestionExample = new TPaperQuestionExample();
        paperQuestionExample.createCriteria().andPaperIdEqualTo(paperId)
                .andDicTypeIdEqualTo(1282382195935678464L).andIsDelEqualTo(true);
        List<TPaperQuestion> questionList = tPaperQuestionMapper.selectByExample(paperQuestionExample);

        TQuestionChoiceExample questionChoiceExample = new TQuestionChoiceExample();
        questionChoiceExample.createCriteria()
                .andChoiceIdIn(questionList.stream().map(TPaperQuestion::getQuestionId).collect(Collectors.toList()));
        return tQuestionChoiceMapper.selectByExample(questionChoiceExample);
    }

    public List<TQuestionJudge> getQuestionJudgeOfPaper(Long paperId){
        TPaperQuestionExample paperQuestionExample = new TPaperQuestionExample();
        paperQuestionExample.createCriteria().andPaperIdEqualTo(paperId)
                .andDicTypeIdEqualTo(1282382342652432384L).andIsDelEqualTo(true);
        List<TPaperQuestion> questionList = tPaperQuestionMapper.selectByExample(paperQuestionExample);

        TQuestionJudgeExample questionJudgeExample = new TQuestionJudgeExample();
        questionJudgeExample.createCriteria()
                .andJudgeIdIn(questionList.stream().map(TPaperQuestion::getQuestionId).collect(Collectors.toList()));
        return tQuestionJudgeMapper.selectByExample(questionJudgeExample);
    }

    public List<TQuestionShortAnswer> getQuestionSampleAnswerOfPaper(Long paperId){
        TPaperQuestionExample paperQuestionExample = new TPaperQuestionExample();
        paperQuestionExample.createCriteria().andPaperIdEqualTo(paperId)
                .andDicTypeIdEqualTo(1282382477885181952L).andIsDelEqualTo(true);
        List<TPaperQuestion> questionList = tPaperQuestionMapper.selectByExample(paperQuestionExample);

        TQuestionShortAnswerExample questionShortAnswerExample = new TQuestionShortAnswerExample();
        questionShortAnswerExample.createCriteria()
                .andShortAnswerIdIn(questionList.stream().map(TPaperQuestion::getQuestionId).collect(Collectors.toList()));
        return tQuestionShortAnswerMapper.selectByExample(questionShortAnswerExample);
    }
}
