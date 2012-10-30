package com.otv.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SecondTestJob implements Job {

	private Logger log = Logger.getLogger(SecondTestJob.class);
	
	public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
		log.debug("SecondTestJob triggered by "+jExeCtx.getTrigger().getKey().getName()+" runs successfully.");
	}
	
}
