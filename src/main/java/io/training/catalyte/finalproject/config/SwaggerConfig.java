package io.training.catalyte.finalproject.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * class which allows swagger documentation for my project
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false) // don't show 401 or 403 returns
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(Predicates.not(PathSelectors.regex("/error"))) // Exclude Spring error controllers
        .build();
  }

}
