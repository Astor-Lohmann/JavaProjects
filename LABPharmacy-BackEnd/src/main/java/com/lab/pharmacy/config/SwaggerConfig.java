package com.lab.pharmacy.config;

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


@Configuration
public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(getApiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/api/**"))
                    .build()
                        .useDefaultResponseMessages(false);
        }

        private ApiInfo getApiInfo() {
            return new ApiInfoBuilder()
                    .title("Pharmacy LAB")
                    .description("API Demonstração")
                    .contact(new Contact("The code of us", "www.livros.com.", "email@dominio.com.br"))
                    .version("1.0")
                    .build();
        }
    }

