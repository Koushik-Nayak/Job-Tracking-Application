package com.jsp.job.tracking.config;

import java.time.LocalDate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

@Configuration
@EnableWebMvc
@ComponentScan("com.jsp.job.tracking")
public class JobTrackingConfig implements WebMvcConfigurer{
	
	@Bean
	public RequestMappingHandlerMapping createHandlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	@Bean
	public InternalResourceViewResolver createViewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
	}

	@Bean
	public EntityManager createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("koushi").createEntityManager();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**")
	            .addResourceLocations("/css/");
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(String.class, LocalDate.class, 
				source -> LocalDate.parse(source));
	}
}
