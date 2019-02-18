package com.catchsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.catchsite.beans.ScheduleInfo;
import com.catchsite.catchwork.ConvertSchdInfoToUrl;
import com.catchsite.catchwork.GrabJobByScheduleInfo;
import com.catchsite.catchwork.impl.ConvertBossSiteToUrl;

@Controller
@CrossOrigin
public class OnceCatchController {
	
	@Autowired
	private GrabJobByScheduleInfo grabJob;
	
	@ResponseBody
	@RequestMapping("/catchonce")
	public String doOnceCatch(ScheduleInfo info) {
		ConvertSchdInfoToUrl URLconvertion = new ConvertBossSiteToUrl();
		URLconvertion.doConvertSchdInfoToUrl(info);
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/testcatch")
	public void testCatch(ScheduleInfo info) {
		System.out.println(info);
		grabJob.doGrabJob(info);
	}
}
