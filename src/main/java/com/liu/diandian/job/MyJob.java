package com.liu.diandian.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description: TODO
 * @author liulei
 * @date 2018年4月25日 下午4:26:21
 */
public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("job key: "+arg0.getJobDetail().getKey());
		System.out.println("Trigger key: "+arg0.getTrigger().getKey());
	}

}

