package com.catchsite.catchwork.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.catchwork.BrowserDriverInstance;
import com.catchsite.catchwork.GrabJobByScheduleInfo;
import com.catchsite.dao.SchdInfoDao;

@Component
public class GrabJobPersistBySchdInfo extends GrabJobTempBySchdInfo{
	
	@Autowired
	private ConvertBossSiteToUrl convertBossSiteToUrl;
	@Autowired
	private BrowserDriverInstance browserDriverInstance;
	@Autowired
	private SchdInfoDao infoDao;
	
	public void doGrabJobPersist(ScheduleInfo info) {
		List<JobInfo> infoList =  super.doGrabJob(info);
		infoDao.insertJobInfoList(infoList);
	}
}
