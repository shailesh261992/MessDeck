package com.app.messdeck.configuration;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.sf.oval.Validator;
import net.sf.oval.guard.GuardInterceptor;

@Configuration
public class OvalConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public GuardInterceptor guardInterceptor() {
		return new GuardInterceptor();
	}

	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		beanNameAutoProxyCreator.setBeanNames("VendorDTO");
		beanNameAutoProxyCreator.setInterceptorNames("guardInterceptor");
		return beanNameAutoProxyCreator;
	}
	
	@Bean
	public Validator getOvalValidator(){
		return new Validator();
	}
}
