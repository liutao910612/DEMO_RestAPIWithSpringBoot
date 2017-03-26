package com.liutao.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zl on 2015/8/27.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 注意：这里可以定义多个bean
     * 启动工程后仅仅需打开http://localhost:8888/swagger-ui.html 链接即可使用swagger
     */
    @Bean
    public Docket testApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/liutao/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }
    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
                /**
                 * 注意以下的属性可以随意删减
                 */
                .title("THIS IS DEMO OF SPRINGBOOT AND SWAGGER")//大标题
                .description("this demo is used to explain how to use springBoot and swagger.")//详细描述
                .version("1.0")//版本
                .termsOfServiceUrl("NO terms of service")
                .contact(new Contact("LT",""  , "liutao910612@126.com"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}