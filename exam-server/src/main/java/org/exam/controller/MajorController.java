package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TMajor;
import org.exam.bean.vo.MajorVo;
import org.exam.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "Major")
@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    MajorService majorService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TMajor>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords){
        return ResponseBean.ok(majorService.majorList(pageNum, pageSize, keyWords));
    }

    @PutMapping
    public ResponseBean<Integer> put(MajorVo major){
        return ResponseBean.ok(majorService.update(major));
    }
    @PostMapping
    public ResponseBean post(TMajor major){
        return ResponseBean.ok(majorService.insert(major));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean<Integer> delete(@PathVariable("id") Long id){
        return ResponseBean.ok(majorService.delete(id));
    }
    @DeleteMapping
    public ResponseBean<Integer> deleteMany(@RequestParam("ids") Long[] ids){
        return ResponseBean.ok(majorService.delete(ids));
    }
}