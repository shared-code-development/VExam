package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TAcademy;
import org.exam.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "学院相关")
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    AcademyService academyService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TAcademy>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(academyService.academyList(pageNum, pageSize));
    }

    @PutMapping
    public ResponseBean<Integer> put(TAcademy academy){
        return ResponseBean.ok(academyService.update(academy));
    }
    @PostMapping
    public ResponseBean post(TAcademy academy){
        return ResponseBean.ok(academyService.insert(academy));
    }


    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(academyService.delete(id));
    }

}