package org.exam.exception;

import org.exam.enums.BusinessEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heshiyuan
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>(3);
        map.put("status", BusinessEnum.SERVER_ERROR_EXCEPTION.getCode());
        map.put("msg", BusinessEnum.SERVER_ERROR_EXCEPTION.getMessage());
        mv.addAllObjects(map);
        return mv;
    }
}