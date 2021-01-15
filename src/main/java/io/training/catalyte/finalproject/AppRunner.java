package io.training.catalyte.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Starts up the application
 */
@SpringBootApplication
@EnableSwagger2
public class AppRunner {

  public static void main(String[] args) {
    SpringApplication.run(AppRunner.class, args);
  }
}
