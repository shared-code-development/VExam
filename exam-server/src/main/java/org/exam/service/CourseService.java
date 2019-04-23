package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TCourse;
import org.exam.bean.entity.TCourseExample;
import org.exam.bean.entity.TMajorExample;
import org.exam.bean.vo.CourseVo;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TCourseMapper;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    UKeyWorker courseIdWorker;

    public PageInfo<List<TCourse>> courseList(Integer pageNum, Integer pageSize, String keyWords){
        PageHelper.startPage(pageNum, pageSize);
        TCourseExample courseExample = new TCourseExample();
        TCourseExample.Criteria criteria = courseExample.createCriteria().andIsDelEqualTo(Boolean.TRUE);
        if(StringUtils.isNotBlank(keyWords)){
            criteria.andCourseNameLike("%"+keyWords+"%");
        }
        return PageUtils.nullListHandler(tCourseMapper.selectByExample(courseExample));
    }

    public TCourse get(Long courseId){
        return tCourseMapper.selectByPrimaryKey(courseId);
    }
    public int insert(TCourse course){
        course.setCourseId(courseIdWorker.getId());
        course.setCreator(1L);
        course.setUpdater(1L);
        return tCourseMapper.insertSelective(course);
    }
    public int update(CourseVo course){
        TCourse newCourse = new TCourse();
        BeanUtils.copyProperties(course, newCourse);
        return tCourseMapper.updateByPrimaryKeySelective(newCourse);
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
