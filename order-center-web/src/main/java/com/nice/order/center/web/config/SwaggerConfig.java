package com.nice.order.center.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Note:<br>
 * 1. Must configure Swagger, or won't be able to access page although able to invoke customized API via Postman without any error
 *
 * TODO Learn more Swagger knowledge, like OAuth 2 integration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // TODO Docket is?
        // TODO DocumentationType is?
        // TODO enable(true) is?
        // TODO RequestHandlerSelectors is?
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(true).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Order Center").description("Application to handle any request related to order")
                .termsOfServiceUrl("https://www.nice.com").contact(new Contact("hai.huang.a@outlook.com", "", "")).version("1.0").build();
    }

}
