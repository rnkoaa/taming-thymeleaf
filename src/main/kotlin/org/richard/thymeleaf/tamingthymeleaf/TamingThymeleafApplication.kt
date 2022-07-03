package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templateresolver.ITemplateResolver

@SpringBootApplication
class TamingThymeleafApplication

fun main(args: Array<String>) {
	runApplication<TamingThymeleafApplication>(*args)
}

@Configuration
class TamingThymeleafConfiguration {

	@Bean
	fun svgTemplateResolver(): ITemplateResolver {
		val templateResolver = SpringResourceTemplateResolver()
		templateResolver.prefix = "classpath:/templates/svg/";
		templateResolver.suffix = ".svg";
		templateResolver.setTemplateMode("XML");

		return templateResolver;
	}
}