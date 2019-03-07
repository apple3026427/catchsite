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

import com.catchsite.beans.RspResult;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.beans.StatusCode;
import com.catchsite.quartz.SystemCatchJob;
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
		JobDetail jobDetail = JobBuilder.newJob(SystemCatchJob.class).setJobData(dataMap)
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
	/**
	 * 获取所有定时任务的信息
	 * @return
	 */
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
				String jobKeyStr = jobKey.getGroup() + "," + jobKey.getName();
				map.put("jobKey", jobKeyStr);
				list.add(map);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 删除一个定时任务
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del-task")
	public RspResult delSystemTask(@RequestBody Map<String, Object> map) {
		String[] jobKeyStr = ( (String) map.get("jobKey") ).split(",");
		
		JobKey jobKey = JobKey.jobKey(jobKeyStr[1], jobKeyStr[0]);
		boolean delOK = false;
		try {
			delOK = scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return new RspResult(new ArrayList<>(), StatusCode.UNKNOWN, "deleted = " + delOK);
	}
	
	public String updateSystemTask(Map<String, Object> map ) {
		
		return null;
	}
}
