package by.tc.auction.controller.listener.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import by.tc.auction.service.server_operation.realization.ServerOperationJobService;

/**
 * A class is used to execute a server operation (ending auctions).
 * @author semenovich
 *
 */
public class QuartzJobListener implements ServletContextListener {

	private static final int FIVE_MINUTES = 5;
	
	private static final String JOB_IDENTITY = "serverOperationJobService";
	private static final String TRIGGER_IDENTITY = "serverOperationJobServiceTrigger";
	private static final String GROUP = "group1";
	
	private static final Logger logger = Logger.getLogger(QuartzJobListener.class);

	/**
	 * Executes a server operation (ending auctions).
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		  try{
			  StdSchedulerFactory schedFact = (StdSchedulerFactory) sce
					  .getServletContext()
				      .getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
			  Scheduler sched = schedFact.getScheduler();
	
	    	  JobDetail job = newJob(ServerOperationJobService.class)
	    	      .withIdentity(JOB_IDENTITY, GROUP)
	    	      .build();
	
	    	  Trigger trigger = newTrigger()
	    	      .withIdentity(TRIGGER_IDENTITY, GROUP)
	    	      .startNow()
	    	      .withSchedule(simpleSchedule()
	    	      .withIntervalInMinutes(FIVE_MINUTES)
	    	      .repeatForever())
	    	      .build();
	
	    	  sched.scheduleJob(job, trigger);
    	  } catch (SchedulerException e) {
    		  logger.error("Error in QuartzJobListener", e);
    	  }
	}
}
