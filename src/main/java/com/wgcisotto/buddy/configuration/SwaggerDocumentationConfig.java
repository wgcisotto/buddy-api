package com.wgcisotto.buddy.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

@Configuration
//@EnableSwagger2 //TODO: fazer funcionar
public class SwaggerDocumentationConfig {

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Buddy")
            .description("This is a sample projet, finance manager. For this sample, you can use the api key `special-key` to test the authorization     filters.")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", "fake@wgcisotto.io"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.wgcisotto.buddy"))
                    .paths(Predicates.or(s -> s.startsWith("/v1/accounts/") && s.endsWith("/movements")))
                    .build()
                .apiInfo(apiInfo());
    }

}
