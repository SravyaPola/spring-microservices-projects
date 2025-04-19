package com.synex.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public LocaleResolver localeResolver() {
		// SessionLocaleResolver — which stores the user's locale preference in the HTTP
		// session."
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		// LocaleResolver bean so Spring can use it whenever it needs to figure out the
		// current locale.
		/// home?lang=fr --- It will switch the locale to French (`fr`).
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Add the locale change interceptor to the list of active interceptors.
		registry.addInterceptor(localeChangeInterceptor());
	}
	// overall reasons for this page: 
//	When a user hits /home?lang=fr, the interceptor catches it and changes the locale to French.
//	That locale is stored in their session, so the app can serve content in the correct language.
//  If there's no lang parameter, it uses English (fallback).

}