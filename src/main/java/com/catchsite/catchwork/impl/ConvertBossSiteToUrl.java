package com.catchsite.catchwork.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.beans.ScheduleInfoSites;
import com.catchsite.catchwork.ConvertSchdInfoToUrl;

@Component
public class ConvertBossSiteToUrl implements ConvertSchdInfoToUrl{
	
	public void doConvertSchdInfoToUrl(ScheduleInfo info) {
		List<ScheduleInfoSites> list = info.getSiteAndUrl();
		System.out.println(list.size());
		for(ScheduleInfoSites infoSites : list) {
			if(infoSites.getSiteName().equals("boss")) {
				infoSites.setSiteUrl("http://www.zhipin.com" + constructPart1(info) + constructPart2(info) + constructPart3(info));
			}
			if(infoSites.getSiteName().equals("pinkySwear")) {
				//生成拉勾网url
			}
		}
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
		String period = info.getPeriod() == null? "" : info.getPeriod();
		return "/?query=" + queryWord + "&period=" + period;
	}
}
