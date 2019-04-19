package org.exam.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger2.enable}")
    private boolean enableSwagger;
    @Value("${swagger2.host}")
    private String swaggerHost;
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                /*Class<?> declaringClass = input.declaringClass();
                // 排除
                if (declaringClass == BasicErrorController.class) {
                    return false;
                }
                // 被注解的类
                if(declaringClass.isAnnotationPresent(ApiOperation.class)){
                    return true;
                }
                // 被注解的方法
                if(input.isAnnotatedWith(ResponseBody.class)) {
                    return true;
                }*/
                /**
                 * 只有添加了ApiOperation注解的method才在API中显示
                 */
                /*if (input.isAnnotatedWith(ApiOperation.class)){
                    return true;
                }*/
                return true;
            }
        };
        /**
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder ticketPar = new ParameterBuilder();
        //header中的ticket参数非必填，传空也可以
        ticketPar
                .name("access-token")
                .description("用户token")
                .defaultValue("eyJhbGciOiJIUzUxMwIjw")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false)
                .build();

        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                //为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("org.exam.controller"))
                .apis(predicate)
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/user/**"))
                .build()
//                .securityContexts(securityContexts())
//                .pathMapping("/")
//                .globalOperationParameters(pars)
                .enable(enableSwagger);
    }


 /*   private List<ApiKey> securitySchemes() {
        return newArrayList(
            new ApiKey("access-token", "access-token", "header")
        );
    }*/

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!test).*$"))
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
            new SecurityReference("access-token", authorizationScopes)
        );
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("微考试系统 restful api")
                //创建人
                .contact(new Contact("heshiyuan",
                        "https://github.com/shiyuan2he",
                        "shiyuan4work@126.com"))
                //版本号
                .version("2.0")
                //描述
                .description("在线考试系统api")
                .build();
    }
}
