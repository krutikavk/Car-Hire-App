package com.wip.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
//@Import(BeanValidatorPluginsConfiguration.class)
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}
	@Bean
	public Docket apiDocket() {
		String groupName = "Swagger";
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.wip.carrental"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.groupName(groupName)
				.apiInfo(apiInfo());
	}

	// Describe the apis
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("test")
				.description("Test")
				.version("1.0.0")
				.license("vvv")
				.build();
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/*").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}
}
