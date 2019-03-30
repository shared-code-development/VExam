package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TQuestionChoice;
import org.exam.bean.entity.TQuestionChoiceExample;
import org.exam.mapper.TQuestionChoiceMapper;
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
public class QuestionChoiceService {

    @Autowired
    TQuestionChoiceMapper tQuestionChoiceMapper;

    public PageInfo<List<TQuestionChoice>> list(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TQuestionChoiceExample academyExample = new TQuestionChoiceExample();
        academyExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TQuestionChoice> academyList = tQuestionChoiceMapper.selectByExample(academyExample);
        if(null!=academyList&&academyList.size()>0){
            return new PageInfo(academyList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public int insert(TQuestionChoice academy){
        return tQuestionChoiceMapper.insertSelective(academy);
    }
    public int update(TQuestionChoice academy){
        return tQuestionChoiceMapper.updateByPrimaryKeySelective(academy);
    }
    public int delete(Long id){
        return tQuestionChoiceMapper.deleteByPrimaryKey(id);
    }
}
