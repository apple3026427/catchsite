package com.catchsite.quartz;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.ScheduleInfo;
import com.catchsite.catchwork.anaylyzetool.AnalyzePageTool;
import com.catchsite.catchwork.impl.GrabJobPersistBySchdInfo;
import com.catchsite.config.SpringContextUtil;
import com.catchsite.dao.SchdInfoDao;
import com.catchsite.utils.DateUtil;
import com.google.gson.Gson;

@Component
public class CatchJob implements Job{
	@Autowired
	private SchdInfoDao infoDao;
	
//	@Autowired
//	private GrabJobPersistBySchdInfo grabJobPersist;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//预计使用GrabJobTempBySchdInfo中方法
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String jsonStr = dataMap.getString("info");
		Gson gson = new Gson();
		ScheduleInfo info = gson.fromJson(jsonStr, ScheduleInfo.class);
		GrabJobPersistBySchdInfo grabJobPersist = (GrabJobPersistBySchdInfo) SpringContextUtil.getBean("grabJobPersistBySchdInfo");
		grabJobPersist.doGrabJobPersist(info);
	}
	
	/**
	 * 临时开发测试使用，之后会删除
	 * @throws IOException
	 */
	public void doCatchJob() throws IOException {
		System.setProperty("webdriver.chrome.driver","D:\\work\\\\爬虫\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
//		String partUrl = (String) info.getSiteAndUrl().get("boss");
//		for(int i = 1; i <= info.getGrabPages(); i++) {
//			webDriver.get(partUrl + "&page=" + i);
//			
//		}
		webDriver.get("https://www.zhipin.com/c101010100-p100101/" + "e_104/");
		List<WebElement> elements = webDriver.findElements(By.className("job-primary"));
//		System.out.println(element.getText());
//		element.click();
		
		int i = 0;
		
		for(WebElement element : elements) {
			i ++ ;
			if(i == 9) {
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
			jobInfo.setCompanyName(element.findElement(By.className("company-text")).findElement(By.tagName("a")).getText());
			
			String companyInfo = element.findElement(By.className("company-text")).findElement(By.tagName("p")).getText();
			String[] comInfo = AnalyzePageTool.splitCompanyInfoForZHIPIN(companyInfo);
			jobInfo.setCompanyType(comInfo[0]);
			jobInfo.setFinancingStage(comInfo[1]);
			jobInfo.setCompanySize(comInfo[2]);
			jobInfo.setGrabDate(DateUtil.convertDate(new Date()));
			jobInfo.setOriginSite("bossֱ直聘");
			jobInfo.setReleaseDate(DateUtil.convertGrabDateToMyDate(element.findElement(By.className("info-publis")).findElement(By.tagName("p")).getText()));
			infoDao.insertJobInfo(jobInfo);
		}
//		System.out.println(elements.size());
		webDriver.quit();
	}
}
