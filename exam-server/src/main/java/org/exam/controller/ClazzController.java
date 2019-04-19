package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TClazz;
import org.exam.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "Clazz")
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    ClazzService clazzService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TClazz>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords){
        return ResponseBean.ok(clazzService.clazzList(pageNum, pageSize, keyWords));
    }

    @PutMapping
    public ResponseBean<Integer> put(TClazz clazz){
        return ResponseBean.ok(clazzService.update(clazz));
    }
    @PostMapping
    public ResponseBean post(TClazz clazz){
        return ResponseBean.ok(clazzService.insert(clazz));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(clazzService.delete(id));
    }

    @DeleteMapping(value = "/{ids}")
    public ResponseBean deleteMany(@PathVariable("ids") Long ids){
        return ResponseBean.ok(clazzService.delete(ids));
    }
}