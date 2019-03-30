package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TQuestionJudge;
import org.exam.bean.entity.TQuestionJudgeExample;
import org.exam.mapper.TQuestionJudgeMapper;
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
public class QuestionJudgeService {
    @Autowired
    TQuestionJudgeMapper tQuestionChoiceMapper;

    public PageInfo<List<TQuestionJudge>> list(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionJudgeExample academyExample = new TQuestionJudgeExample();
        academyExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TQuestionJudge> academyList = tQuestionChoiceMapper.selectByExample(academyExample);
        if(null!=academyList&&academyList.size()>0){
            return new PageInfo(academyList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public int insert(TQuestionJudge academy){
        return tQuestionChoiceMapper.insertSelective(academy);
    }
    public int update(TQuestionJudge academy){
        return tQuestionChoiceMapper.updateByPrimaryKeySelective(academy);
    }
    public int delete(Long id){
        return tQuestionChoiceMapper.deleteByPrimaryKey(id);
    }
}
