package web.crawler.test.fonix.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import web.crawler.test.fonix.service.EmailService;

@Component
public class NotificationScheduler {
	
	private final EmailService emailService;
	
	@Autowired
	public NotificationScheduler(final EmailService emailService) {
		this.emailService = emailService;
	}
	
    @Scheduled(cron = "0 0 12 * * ?") //Run the schedule task every day at 12 noon
    public void informUsersDaily() {
        emailService.sendOfferEmailForScheduledUser("daily");
    }
    
    @Scheduled(cron = "0 0 12 ? * SUN ") // Run the schedules task every Sunday at 12 noon
    public void informUsersWeekly() {
    	emailService.sendOfferEmailForScheduledUser("weekly");    
    }
    
    @Scheduled(cron = "0 0 12 1 * ?") //Run the schedule task first day of the month at 12 noon
    public void informUsersMonthly() {
    	emailService.sendOfferEmailForScheduledUser("monthly");
    }
    
}
