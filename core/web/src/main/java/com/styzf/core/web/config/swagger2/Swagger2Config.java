package com.styzf.core.web.config.swagger2;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties({ApiInfoProperties.class})
public class Swagger2Config {
    @Bean
    public Docket springfoxDocket(ApiInfoProperties apiInfo) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.ignoredParameterTypes(new Class[] { ApiIgnore.class });
        docket.apiInfo(apiInfo(apiInfo));
        docket.pathMapping("/").select().paths(PathSelectors.regex("^.*(?<!error)$")).build();
        docket.select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
        return docket;
    }
    
    @Bean
    ApiInfo apiInfo(ApiInfoProperties apiInfoProperties) {
        Contact contact = apiInfoProperties.getContact();
        ApiInfo apiInfo = new ApiInfo(apiInfoProperties.getTitle(), apiInfoProperties.getDescription(), apiInfoProperties.getVersion(), apiInfoProperties.getTermsOfServiceUrl(), new springfox.documentation.service.Contact(contact.getName(), contact.getUrl(), contact.getEmail()), apiInfoProperties.getLicense(), apiInfoProperties.getLicenseUrl());
        return apiInfo;
    }
    
}
