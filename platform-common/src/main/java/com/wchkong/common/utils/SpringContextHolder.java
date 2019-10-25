package com.wchkong.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 以静态变量保存Spring ApplicationContext,可在任意代码中取出ApplicaitonContext.
 * 
 */
public class SpringContextHolder implements ApplicationContextAware, ApplicationListener
{
	private static final Logger LOG = LoggerFactory.getLogger(SpringContextHolder.class);
	private static ApplicationContext applicationContext;
	private static boolean hasLoaded = false;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext()
	{
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name)
	{
		try {
			checkApplicationContext();
			return (T) applicationContext.getBean(name);
		} catch (Exception e) {
			LOG.error("no bean in spring context");
			return null;
		}
	}

	private static void checkApplicationContext()
	{
		if (applicationContext == null)
		{
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
		}
	}

	public static boolean containsBean(String beanName)
	{
		checkApplicationContext();
		return applicationContext.containsBean(beanName);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event)
	{
		if (event instanceof ContextRefreshedEvent)
		{
			hasLoaded = true;
		}
	}

	public static boolean isSpringContextLoaded()
	{
		return hasLoaded;
	}
}