package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TCourse;
import org.exam.bean.entity.TCourseExample;
import org.exam.mapper.TCourseMapper;
import org.exam.mapper.TUserMajorCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * exam-server/org.exam.service
 *
 * @author heshiyuan
 */
@Service
public class CourseService {
    @Autowired
    TCourseMapper tCourseMapper;

    @Autowired
    TUserMajorCourseMapper tUserMajorCourseMapper;

    public PageInfo<List<TCourse>> list(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(tCourseMapper.selectByExample(new TCourseExample()));
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

    /**
     *
     */
    public void selectCourse(){

    }
}
