package com.catchsite.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class InitDataAfterServerStarted implements ApplicationRunner {
	@Autowired
	private Scheduler scheduler;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		scheduler.start();
	}

}
