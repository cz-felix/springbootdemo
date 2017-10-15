package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class SpringbootdemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootdemoApplication.class);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			container.addErrorPages(error404Page);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
