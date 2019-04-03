package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TCourse;
import org.exam.bean.entity.TCourseExample;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
public class CourseService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TCourseMapper tCourseMapper;

    public PageInfo<List<TCourse>> courseList(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        TCourseExample courseExample = new TCourseExample();
        courseExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        List<TCourse> courseList = tCourseMapper.selectByExample(courseExample);
        if(null!=courseList&&courseList.size()>0){
            return new PageInfo(courseList);
        }else{
            return new PageInfo(new ArrayList());
        }
    }

    public TCourse get(Long courseId){
        return tCourseMapper.selectByPrimaryKey(courseId);
    }
    public int insert(TCourse course){
        return tCourseMapper.insertSelective(course);
    }
    public int update(TCourse course){
        return tCourseMapper.updateByPrimaryKeySelective(course);
    }
    public int delete(Long id){
        return tCourseMapper.deleteByPrimaryKey(id);
    }
    public int delete(Long[] ids) {
        TCourseExample courseExample = new TCourseExample();
        courseExample.createCriteria().andCourseIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tCourseMapper.deleteByExample(courseExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }
}
