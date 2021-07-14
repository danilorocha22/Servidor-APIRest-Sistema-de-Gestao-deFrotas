package com.danilo.apirestgestaodefrotas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SuppressWarnings("all")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.danilo.apirestgestaodefrotas"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Sistema de Gestão de Frotas API REST",
                "API REST para gerencimaneto de frota veicular - por Danilo Rocha",
                "1.0",
                "Termos de Serviço",
                "Danilo Rocha",
                "https://github.com/danilorocha22",
                "https://github.com/danilorocha22"
                );

        return apiInfo;
    }


}
