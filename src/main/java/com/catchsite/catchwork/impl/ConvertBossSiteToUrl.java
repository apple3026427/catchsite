package com.catchsite.catchwork.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.catchsite.beans.ScheduleInfo;
import com.catchsite.catchwork.ConvertSchdInfoToUrl;

@Component
public class ConvertBossSiteToUrl implements ConvertSchdInfoToUrl{
	
	public void doConvertSchdInfoToUrl(ScheduleInfo info) {
		Map<String, Object> map = info.getSiteAndUrl();
		if(map == null) {
			map = new HashMap<String, Object>();
			info.setSiteAndUrl(map);
		}
		map.put("boss", "http://www.zhipin.com" + constructPart1(info) + constructPart2(info) + constructPart3(info));
//		info.setSiteAndUrl(map);
	}
	
	public String constructPart1(ScheduleInfo info) {
		//loc默认为北京
		String loc = info.getLoc() == null ? "c101010100" : info.getLoc();
		String workType = info.getWorkType() == null ? "" : "-" + info.getWorkType();
		String companyType = info.getCompanyType() == null ? "" : "-" + info.getCompanyType();
		return "/" + loc + workType + companyType;
	}
	
	public String constructPart2(ScheduleInfo info) {
		if(info.getWorkYear() == null && info.getEduBg() == null && info.getFinancingStage() == null
				&& info.getSalary() == null && info.getCompanySize() == null) {
			return "";
		}
		String workYear = info.getWorkYear() == null ? "" : info.getWorkYear() + "-";
		String eduBg = info.getEduBg() == null ? "" : info.getEduBg() + "-";
		String financingStage = info.getFinancingStage() == null ? "" : info.getFinancingStage() + "-";
		String salary = info.getSalary() == null ? "" : info.getSalary() + "-";
		String companySize = info.getCompanySize() == null ? "" : info.getCompanySize();
		return "/" + workYear + eduBg + financingStage + salary + companySize;
	}
	
	public String constructPart3(ScheduleInfo info) {
		String queryWord = info.getQueryWord() == null ? "" : info.getQueryWord();
		return "/?query=" + queryWord;
	}
}
