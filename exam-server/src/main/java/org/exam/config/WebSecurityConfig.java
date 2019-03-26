package org.exam.config;

import org.exam.handler.*;
import org.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author heshiyuan
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    CustomMetadataSource metadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // 认证配置
        authenticationManagerBuilder
                // 登录用户跟数据库比对
                .userDetailsService(userService)
                // 密码加密后验证
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                // 以下资源放行
                .ignoring()
                    .antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico", "/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                    .formLogin()
                        .loginPage("/login_p")
                            .loginProcessingUrl("/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            // 登录失败处理器
                            .failureHandler(myAuthenticationFailureHandler)
                            // 登录成功处理器
                            .successHandler(myAuthenticationSuccessHandler)
                            // 登录相关接口放行
                            .permitAll()
                .and()
                    // 配置退出接口
                    .logout()
                        // 退出地址
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(myLogoutSuccessHandler)
                        // 退出接口放行
                        .permitAll()
                .and()
                    .csrf().disable()
                // 异常处理器配置
                .exceptionHandling()
                    // 访问拒绝处理器
                    .accessDeniedHandler(deniedHandler);
    }
}