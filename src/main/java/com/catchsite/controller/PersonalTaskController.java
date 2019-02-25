package com.catchsite.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.catchwork.ConvertSchdInfoToUrl;
import com.catchsite.catchwork.GrabJobByScheduleInfo;
import com.catchsite.catchwork.impl.ConvertBossSiteToUrl;
import com.catchsite.catchwork.impl.GrabJobTempBySchdInfo;
import com.catchsite.dao.SchdInfoDao;

@Controller
@CrossOrigin
public class PersonalTaskController {
	
	@Autowired
	@Qualifier("grabJobTempBySchdInfo")
	private GrabJobTempBySchdInfo grabJob;
	@Autowired
	private SchdInfoDao infoDao;
	
	@ResponseBody
	@RequestMapping("/catchonce")
	public String doOnceCatch(ScheduleInfo info) {
		ConvertSchdInfoToUrl URLconvertion = new ConvertBossSiteToUrl();
		URLconvertion.doConvertSchdInfoToUrl(info);
		return null;
	}
	
	/**
	 * 
	 * @param info
	 */
	@ResponseBody
	@RequestMapping("/testcatch")
	public void testCatch(ScheduleInfo info) {
		System.out.println(info);
		grabJob.doGrabJob(info);
	}
	
	/**
	 * 获取工作信息列表
	 * @return
	 */
	@RequestMapping("/getJobInfoList")
	@ResponseBody
	public List<JobInfo> getJobInfoList() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		List<JobInfo> infoList = infoDao.selectInfo();
		//对数据库返回的日期数据进行处理
		infoList.stream().forEach((e) -> {
			String releaseDate = e.getReleaseDate();
			int releaseMonth = Integer.valueOf(releaseDate.substring(5, 7));
			int releaseDayOfMonth = Integer.valueOf(releaseDate.substring(8, 10));
			if(releaseMonth == month && releaseDayOfMonth == day) {
				e.setReleaseDate("发布于" + releaseDate.substring(11, 16));
			}
			if(releaseMonth == month && releaseDayOfMonth == day - 1) {
				e.setReleaseDate("发布于昨天");
			}
			e.setReleaseDate("发布于" + releaseDate.substring(5, 10));
			e.setGrabDate(e.getGrabDate().substring(5, e.getGrabDate().length() - 3));
		});
		return infoList;
	}
}
