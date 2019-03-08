package com.catchsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.catchsite.beans.RspResult;
import com.catchsite.dao.JobStatistcsDao;

@Controller
@RequestMapping("/count")
public class JobStatisticsController {
	@Autowired
	private JobStatistcsDao ststcsDao;
	@RequestMapping("/all")
	@ResponseBody
	public RspResult countAll() {
		return new RspResult(ststcsDao.getStcs());
	}
}
