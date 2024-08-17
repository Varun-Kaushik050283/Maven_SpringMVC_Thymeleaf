package com.greatlearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("com.greatlearning")
public class SpringConfig {

	/**
	 * To configure thymeleaf view resolver
	 *
	 * 1. Autowire application context
	 *
	 * 2. Get Thymeleaf view resolver as @Bean 2.1. Set Custom Template Engine in
	 * ThymeleafViewResolver of Type SpringTemplateEngine 2.2. Return
	 * ThymeleafViewResolver
	 *
	 * 3. Get Template Engine of Spring Template Engine as @Bean 3.1 Set Custom
	 * Template Resolver in TemplateEngine 3.2 Set SpringELCompiler as true (enable)
	 * in Custom Template Engine 3.3 return TemplateEngine of type
	 * SpringTemplateEngine
	 *
	 * 4. Get Template Resolver of Type Spring Resource Template Resolver as @Bean
	 * 4.1. Set Application Context to Spring Resource Template Resolver 4.2. Set
	 * Prefix to Spring Resource Template Resolver as "/WEB-INF/views/" 4.3. Set
	 * Suffix to Spring Resource Template Resolver as ".html" 4.4. Set Template Mode
	 * to Spring Resource Template Resolver as HTML 4.5. Set Cacheable to Spring
	 * Resource Template Resolver as true 4.6. return Spring resource template
	 * resolver
	 */

	// 1. Autowire ApplicationContext
	@Autowired
	private ApplicationContext applicationContext;

	// 4. Get custom template resolver of type Spring Resource Template Resolver as
	// @Bean
	@Bean
	public SpringResourceTemplateResolver getTemplateResolver() {
		SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
		springResourceTemplateResolver.setApplicationContext(this.applicationContext);
		springResourceTemplateResolver.setPrefix("/WEB-INF/views/");
		springResourceTemplateResolver.setSuffix(".html");
		springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
		springResourceTemplateResolver.setCacheable(true);
		return springResourceTemplateResolver;
	}

	// 3. Get custom template engine of type Spring Template Engine
	@Bean
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(getTemplateResolver());
		springTemplateEngine.setEnableSpringELCompiler(true);
		return springTemplateEngine;
	}

	// 2. Getting thymeleaf view resolver
	@Bean
	public ThymeleafViewResolver getThymeleafViewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(getTemplateEngine());
		return thymeleafViewResolver;
	}

}
