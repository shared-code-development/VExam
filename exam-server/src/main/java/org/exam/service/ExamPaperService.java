package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TExamPaper;
import org.exam.bean.entity.TExamPaperExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TExamPaperMapper;
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
public class ExamPaperService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TExamPaperMapper tExamPaperMapper;
    @Autowired
    UKeyWorker examPaperIdWorker;

    public PageInfo<List<TExamPaper>> questionShortAnswerList(Integer pageNum, Integer pageSize, String keyWords){
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
}
