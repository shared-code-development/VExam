package org.exam.mapper;

import lombok.extern.slf4j.Slf4j;
import org.exam.ExamServerApplication;
import org.exam.bean.entity.TUser;
import org.exam.bean.entity.TUserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * exam-server/org.exam.mapper
 *
 * @author heshiyuan
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamServerApplication.class)
public class TUserMapperTest {

    @Autowired
    TUserMapper tUserMapper;
    @Test
    public void testSelectByExample(){
        List<TUser> userList = tUserMapper.selectByExample(new TUserExample());
        log.info("{}", userList.toArray().toString());
    }
}