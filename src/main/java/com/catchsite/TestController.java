package com.catchsite;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.catchsite.beans.JobInfo;
import com.catchsite.dao.SchdInfoDao;
import com.catchsite.quartz.CatchJob;

@Controller
public class TestController {
	@Autowired
	private CatchJob job;

	@Autowired
	private SchdInfoDao infoDao;

	@RequestMapping("/test")
	@ResponseBody
	public void fotTest() throws IOException {
		job.doCatchJob();
	}

	@RequestMapping("/testse")
	@ResponseBody
	public List<JobInfo> forSelect() {
		List<JobInfo> infoList = infoDao.selectInfo();
		infoList.stream().forEach((e) -> {
			e.setReleaseDate(e.getReleaseDate().substring(5, e.getReleaseDate().length() - 3));
			e.setGrabDate(e.getGrabDate().substring(5, e.getGrabDate().length() - 3));
		});
		return infoList;
	}

//	public static void main(String[] args) {
//		new TestController().grabWork(4, null);
//	}
	public String grabWork(Integer exep, String Lang) {
		System.setProperty("webdriver.chrome.driver", "D:\\work\\爬虫\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		String exepe = "e_10";
		String exepyear = null;
		exep = exep == null ? 0 : exep;
		switch (exep) {
		case 2:
			exepyear = exepe + "2/";
			break;
		case 3:
			exepyear = exepe + "3/";
			break;
		case 4:
			exepyear = exepe + "4/";
			break;
		case 5:
			exepyear = exepe + "5/";
			break;
		case 6:
			exepyear = exepe + "6/";
			break;
		case 7:
			exepyear = exepe + "7/";
			break;
		default:
			exepyear = "e_101/";
			break;
		}
		webDriver.get("https://www.zhipin.com/c101010100/" + exepyear + "?query=java");
		List<WebElement> elements = webDriver.findElements(By.className("job-title"));
//		System.out.println(element.getText());
//		element.click();
		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
//		System.out.println(elements.size());
//		webDriver.quit();
		return null;
	}
}
