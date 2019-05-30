package org.exam.config.oauth;
import org.exam.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsUtils;

/**
 * @author heshiyuan
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }

    /**
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService());
        super.configure(authenticationManagerBuilder);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //跨站域请求伪造
        httpSecurity.csrf().disable();
        httpSecurity
                // 规则自定义,需要的时候放在最前面，对需要匹配的的规则进行自定义与过滤==不拦截oauth要开放的资源
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                // authorizeRequests URL权限配置
                .authorizeRequests()
                // header中包含 Access-Control-Request-Method,允许访问
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/oauth/**","/login/**","/logout/**").permitAll()
                .anyRequest().anonymous()
        ;
    }
}
