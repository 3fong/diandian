package com.liu.diandian;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.liu.diandian.job.MyJob;

/**
 * @Description: spring初始化配置
 * @author liulei
 * @date 2018年4月24日 下午8:17:26
 */
@ComponentScan("com.liu.diandian")
public class Application {
	
	@Autowired
	private QuartzConfig quartz;
	
	public static void main(String[] args) {
		/*ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		DataSource bean = ctx.getBean(DataSource.class);
		try {
			System.out.println(bean.getLoginTimeout());
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		

         try {
     		ApplicationContext ctx = new AnnotationConfigApplicationContext(QuartzConfig.class);
     		Properties bean = ctx.getBean(Properties.class);
        	 SchedulerFactory sf = new StdSchedulerFactory(bean);
        			Scheduler scheduler = sf.getScheduler();
			scheduler.start();
			JobDetail job = newJob(MyJob.class)
				      .withIdentity("job1", "group1")
				      .build();

				  Trigger trigger = newTrigger()
				      .withIdentity("trigger1", "group1")
				      .startNow()
				            .withSchedule(simpleSchedule()
				              .withIntervalInSeconds(5)
				              .repeatForever())            
				      .build();

				  scheduler.scheduleJob(job, trigger);
//			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}

