package com.liu.diandian;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author liulei
 * @date 2018年4月25日 下午5:41:12
 */
@Component
@PropertySource("classpath:quartz.properties")
public class QuartzConfig {

	@Autowired
    private Environment env;
	
	public Properties initProperties() {
		Properties props = new Properties();
		props.setProperty("org.quartz.scheduler.instanceName", env.getProperty("org.quartz.scheduler.instanceName"));
		props.setProperty("org.quartz.threadPool.threadCount", env.getProperty("org.quartz.threadPool.threadCount"));
		props.setProperty("org.quartz.threadPool.class", env.getProperty("org.quartz.threadPool.class"));
		props.setProperty("org.quartz.jobStore.class", env.getProperty("org.quartz.jobStore.class"));
		return props;
	}
}

