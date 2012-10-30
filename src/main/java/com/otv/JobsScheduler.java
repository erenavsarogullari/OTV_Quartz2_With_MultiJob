package com.otv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.otv.job.FirstTestJob;
import com.otv.job.SecondTestJob;

/**
 * @author onlinetechvision.com
 * @since 18 Sept 2011
 * @version 1.0.0
 *
 */
public class JobsScheduler {
	
	public static void main(String[] args) {
		
		try {
						
			// specify the firstjob' s details..
			JobDetail firstTestJobDetail = JobBuilder.newJob(FirstTestJob.class)
			    .withIdentity("firstTestJob")
			    .build();
			
			// specify the secondjob' s details..
			JobDetail secondTestJobDetail = JobBuilder.newJob(SecondTestJob.class)
			    .withIdentity("secondTestJob")
			    .build();
			
			// specify the running period of the firstjob
			Trigger firstTrigger = TriggerBuilder.newTrigger()
				  .withIdentity("FirstTrigger")
			      .withSchedule(  
                    SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever())  
                             .build(); 
			
			// specify the running period of the secondjob
			Trigger secondTrigger = TriggerBuilder.newTrigger()
				  .withIdentity("SecondTrigger")
			      .withSchedule(  
                    SimpleScheduleBuilder.simpleSchedule().repeatMinutelyForever())  
                             .build();  
			
			// create firstTrigger list
			List<Trigger> firstTriggerList = new ArrayList<Trigger>();
			firstTriggerList.add(firstTrigger);
			
			// create secondTrigger list
			List<Trigger> secondTriggerList = new ArrayList<Trigger>();
			secondTriggerList.add(secondTrigger);
	    	
			// link jobdetails and trigger lists
	    	Map<JobDetail,List<Trigger>> map = new HashMap<JobDetail, List<Trigger>>();
	    	map.put(firstTestJobDetail, firstTriggerList);
	    	map.put(secondTestJobDetail, secondTriggerList);
	    	
	    	//schedule the jobs
	    	SchedulerFactory schFactory = new StdSchedulerFactory();
	    	Scheduler sch = schFactory.getScheduler();
			sch.start();
	    	sch.scheduleJobs(map, false);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
