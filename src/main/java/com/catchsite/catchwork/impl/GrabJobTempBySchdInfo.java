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
import com.catchsite.beans.ScheduleInfoSites;
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
		//把info中的boss直聘根据要求转化为url
		convertBossSiteToUrl.doConvertSchdInfoToUrl(info);
		//抓取boss直聘相关信息
		grabBossSiteJob(info, jobList);
		//待补充  ： 预计在此调用抓取拉勾网的方法
		return jobList;
	}
	
	
	public void grabJobBySite(ScheduleInfoSites site, ScheduleInfo info, List<JobInfo> jobList) {
		if(site.getSiteName() != null && site.getSiteName().equals("boss")) {
			grabBossSiteJob(info, jobList);
		}
	}
	
	/**
	 * 把info中的infoSites通过循环比对获取boss直聘URL以及抓取数量，并进行抓取
	 * @param info 抓取的信息，包括抓取的条件，如工作年限，学历要求，抓取的网站，抓取数量等。
	 * @param jobList 抓取到的信息放到该list中
	 */
	public void grabBossSiteJob(ScheduleInfo info, List<JobInfo> jobList) {
		webDriver = browserDriverInstance.getWebDriver();
		ScheduleInfoSites bossSite = null;
		for(ScheduleInfoSites infoSites  :  info.getSiteAndUrl()) {
			if(infoSites.getSiteName().equals("boss")) {
				bossSite = infoSites;
			}
		}
		//为空说明不含boss直聘
		if(bossSite == null) {
			return ;
		}
		//需要抓取的数量
		int grabNum = bossSite.getGrabNum();
		//boss直聘最多只能显示10页共300条招聘信息
		grabNum = grabNum > 300 ? 300 : grabNum;
		//30是boss直聘pageSize
		int grabPage = grabNum %30 == 0 ? grabNum/30 : grabNum/30 + 1;
		//已经抓取的数量
		int grabedNum = 0;
		//循环翻页
		for(int i = 0; i<grabPage; i++) {
			webDriver.get(bossSite.getSiteUrl() + "&page=" + (i + 1));
			//获取该页和job相关的标签集合
			List<WebElement> elements = webDriver.findElements(By.className("job-primary"));
			for (WebElement element : elements) {
				if (grabedNum == grabNum) {
					break;
				}
				grabedNum++;
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
				String companyInfo = element.findElement(By.className("company-text")).findElement(By.tagName("p")).getText();
				String[] comInfo = AnalyzePageTool.splitCompanyInfoForZHIPIN(companyInfo);
				System.out.println("companyInfo =====" + companyInfo);
				System.out.println("cmInfo[0]===" + comInfo[0]);
				System.out.println("cmInfo[1]===" + comInfo[1]);
				System.out.println("cmInfo[2]===" + comInfo[2]);
				jobInfo.setCompanyType(comInfo[0]);
				jobInfo.setFinancingStage(comInfo[1]);
				jobInfo.setCompanySize(comInfo[2]);
				jobInfo.setGrabDate(DateUtil.convertDate(new Date()));
				jobInfo.setOriginSite("bossֱ直聘");
				jobInfo.setReleaseDate(
						DateUtil.convertGrabDateToMyDate(element.findElement(By.className("info-publis")).findElement(By.tagName("p")).getText()));
				System.out.println(jobInfo);
				jobList.add(jobInfo);
			}
			//小于30说明到末页
			if(elements.size() < 30) {
				break;
			}
		}
	}
}
