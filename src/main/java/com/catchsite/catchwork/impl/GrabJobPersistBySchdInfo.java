package com.catchsite.catchwork.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.beans.statistcs.JobStatistics;
import com.catchsite.catchwork.BrowserDriverInstance;
import com.catchsite.catchwork.statistics.CountWork;
import com.catchsite.dao.JobStatistcsDao;
import com.catchsite.dao.SchdInfoDao;

@Component
public class GrabJobPersistBySchdInfo extends GrabJobTempBySchdInfo{
	
//	@Autowired
//	private ConvertBossSiteToUrl convertBossSiteToUrl;
//	@Autowired
//	private BrowserDriverInstance browserDriverInstance;
	@Autowired
	private SchdInfoDao infoDao;
	@Autowired
	private JobStatistcsDao statistcsDao;
	
	/**
	 * 该方法对应的是系统方法，需要把工作年限的要求拆分后在进行抓取（boss直聘网站信息显示有限），目前
	 * 限定可选条件不包括    发布日期和工作年限的要求。
	 * 方法内会设置发布日期为1天内，工作年限拆分后循环抓取。
	 * @param info
	 */
	public void doGrabJobPersist(ScheduleInfo info) {
		//设置抓取发布日期为1天内
		info.setPeriod("1");
		List<JobInfo> list = new ArrayList<>(500);
		for(int i = 0; i < 6; i++) {
			info.setWorkYear("e_10" + String.valueOf(i+2));
			List<JobInfo> infoList =  super.doGrabJob(info);
			list.addAll(infoList);
			if(infoList.size() != 0) {
				infoDao.insertJobInfoList(infoList);
			}
		}
		System.out.println(list.size());
		JobStatistics statistics = CountWork.countJob(list);
		statistcsDao.saveStcs(statistics);
	}
}
