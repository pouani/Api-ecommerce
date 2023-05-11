package com.stage.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.stage.ecommerce.utils.IConstants.APP_ROOT;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public Docket apiConf(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("API APPLICATION VENTE EN LIGNE documentation")
                                .title("E-COMMERCE REST API")
                                .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stage.ecommerce"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build();
    }
}
