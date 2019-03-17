package org.exam.handler;

import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TMenuExample;
import org.exam.bean.entity.TRole;
import org.exam.enums.RoleEnum;
import org.exam.mapper.TMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    TMenuMapper tMenuMapper;

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        TMenuExample menuExample = new TMenuExample();
        menuExample.createCriteria().andIsDelEqualTo((byte) 0);
//        List<TMenu> allMenu = tMenuMapper.selectByExample(menuExample);
        List<TMenu> allMenu = tMenuMapper.getAllMenu();
        for (TMenu menu : allMenu) {
            if(StringUtils.isEmpty(menu.getUrl())) {continue;}
            if (antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getRoles().size()>0) {
                List<TRole> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList(RoleEnum.LOGIN.getCode());
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
