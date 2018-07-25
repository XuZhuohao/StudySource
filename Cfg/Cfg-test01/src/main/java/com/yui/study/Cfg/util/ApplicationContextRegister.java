package com.yui.study.Cfg.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器
 *
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextRegister.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
