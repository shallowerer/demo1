package com.huaxianvwa.school.config;
/**
 * @author zsj
 * @date 2020/3
 */
import java.util.List;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    //所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
	registry.addMapping("/**")
	        .allowCredentials(true)
	        .allowedOrigins("*")
	        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
	        .allowedHeaders("*")
	            .maxAge(3600);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/workspace/graduration/img/");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Validator getValidator() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO 自动生成的方法存根
		return null;
	}

    
}
