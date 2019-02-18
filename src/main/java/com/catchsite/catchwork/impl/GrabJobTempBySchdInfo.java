package com.catchsite.catchwork.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.catchwork.BrowserDriverInstance;
import com.catchsite.catchwork.GrabJobByScheduleInfo;
import com.catchsite.catchwork.anaylyzetool.AnalyzePageTool;
import com.catchsite.utils.DateUtil;

@Component
public class GrabJobTempBySchdInfo implements GrabJobByScheduleInfo {

	@Autowired
	private ConvertBossSiteToUrl convertBossSiteToUrl;
	@Autowired
	private BrowserDriverInstance browserDriverInstance;

	WebDriver webDriver = null;

	public List<JobInfo> doGrabJob(ScheduleInfo info) {
		List<JobInfo> jobList = new ArrayList<JobInfo>();
		convertBossSiteToUrl.doConvertSchdInfoToUrl(info);
		
		
		Map<String, Object> siteToUrl = info.getSiteAndUrl();
		System.out.println(siteToUrl == null);
		String bossUrl = (String) siteToUrl.get("boss");
		String noWorry = (String) siteToUrl.get("noWorry");
		String pinkySwear = (String) siteToUrl.get("pinkySwear");
		if (bossUrl != null) {
			GrabBossSiteJob(info, jobList);
		}
		if (noWorry != null) {
			
		}
		if (pinkySwear != null) {

		}
		return jobList;
	}

	public void GrabBossSiteJob(ScheduleInfo info, List<JobInfo> jobList) {
		webDriver = browserDriverInstance.getWebDriver();
		webDriver.get((String) info.getSiteAndUrl().get("boss"));
		List<WebElement> elements = webDriver.findElements(By.className("job-primary"));
		int i = 0;
		//暂时只抓取4条
		for (WebElement element : elements) {
			i++;
			if (i == 5) {
				break;
			}
			System.out.println(element.getText());
			JobInfo jobInfo = new JobInfo();
			jobInfo.setJobName(element.findElement(By.className("job-title")).getText());
			jobInfo.setSalary(element.findElement(By.className("red")).getText());
			String jobRequire = element.findElement(By.className("info-primary")).findElement(By.tagName("p")).getText();
			String[] require = AnalyzePageTool.splitWorkRequireForZHIPIN(jobRequire);
			jobInfo.setLoc(require[0]);
			jobInfo.setWorkYear(require[1]);
			jobInfo.setEduBg(require[2]);
			jobInfo.setCompanyName(
					element.findElement(By.className("company-text")).findElement(By.tagName("a")).getText());
			jobInfo.setCompanyInfo(
					element.findElement(By.className("company-text")).findElement(By.tagName("p")).getText());
			jobInfo.setGrabDate(DateUtil.convertDate(new Date()));
			jobInfo.setOriginSite("bossֱ直聘");
			jobInfo.setReleaseDate(
					DateUtil.convertGrabDateToMyDate(element.findElement(By.className("info-publis")).findElement(By.tagName("p")).getText()));
			System.out.println(jobInfo);
			jobList.add(jobInfo);
		}
	}
}
