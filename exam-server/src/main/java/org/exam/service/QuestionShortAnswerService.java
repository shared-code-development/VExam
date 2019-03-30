package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.bean.entity.TQuestionShortAnswerExample;
import org.exam.mapper.TQuestionShortAnswerMapper;
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
public class QuestionShortAnswerService {

    @Autowired
    TQuestionShortAnswerMapper tQuestionShortAnswerMapper;

    public PageInfo<List<TQuestionShortAnswer>> list(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionShortAnswerExample academyExample = new TQuestionShortAnswerExample();
        academyExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TQuestionShortAnswer> academyList = tQuestionShortAnswerMapper.selectByExample(academyExample);
        if(null!=academyList&&academyList.size()>0){
            return new PageInfo(academyList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public int insert(TQuestionShortAnswer academy){
        return tQuestionShortAnswerMapper.insertSelective(academy);
    }
    public int update(TQuestionShortAnswer academy){
        return tQuestionShortAnswerMapper.updateByPrimaryKeySelective(academy);
    }
    public int delete(Long id){
        return tQuestionShortAnswerMapper.deleteByPrimaryKey(id);
    }
}
