package com.catchsite.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.catchsite.beans.ScheduleInfo;
import com.catchsite.quartz.CatchJob;
import com.google.gson.Gson;

@Controller
@RequestMapping("/system-task")
@CrossOrigin
public class SystemTaskController {
	
	@Autowired
	private Scheduler scheduler;
	
	/**
	 * 新增定时任务
	 * @param info
	 * @return
	 */
	@RequestMapping("/add-task")
	@ResponseBody
	public String addSystemTask(@RequestBody ScheduleInfo info ) {
		Gson gson = new Gson();
		String infoStr = gson.toJson(info);
		System.out.println("infoStr=" + infoStr);
		Map<String, Object> map = new HashMap<>();
		map.put("info", infoStr);
		JobDataMap dataMap = new JobDataMap(map);
		JobDetail jobDetail = JobBuilder.newJob(CatchJob.class).setJobData(dataMap)
				.build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(info.getCronStr())).build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping("/get-tasks")
	@ResponseBody
	public List<Map<String, Object>> getAllSystemTasks() {
		Set<JobKey> jobKeys = null;
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
			for(JobKey jobKey : jobKeys) {
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				Map<String, Object> map = jobDetail.getJobDataMap().getWrappedMap();
				String jobKeyStr = jobKey.getGroup() + jobKey.getName();
				map.put("jobKey", jobKeyStr);
				list.add(map);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String delSystemTask(ScheduleInfo info) {
		
		return null;
	}
	
	public String updateSystemTask(Map<String, Object> map ) {
		return null;
	}
	
	private void setMap(JobDataMap map) {
		
	}
}
