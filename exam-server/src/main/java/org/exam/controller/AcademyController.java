package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Academy")
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    AcademyService academyService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TAcademy>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords){
        return ResponseBean.ok(academyService.academyList(pageNum, pageSize, keyWords));
    }
    @PutMapping
    public ResponseBean<Integer> put(TAcademy academy){
        return ResponseBean.ok(academyService.update(academy));
    }
    @PostMapping
    public ResponseBean<Integer> post(TAcademy academy){
        return ResponseBean.ok(academyService.insert(academy));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseBean<Integer> delete(@PathVariable("id") Long id){
        return ResponseBean.ok(academyService.delete(id));
    }
    @DeleteMapping(value = "/{ids}")
    public ResponseBean<Integer> deleteMany(@PathVariable("ids") Long[] ids){
        return ResponseBean.ok(academyService.delete(ids));
    }

}