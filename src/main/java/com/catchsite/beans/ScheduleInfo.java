package com.catchsite.beans;

import java.util.HashMap;
import java.util.Map;

public class ScheduleInfo {
	private Integer infoId;
	private String workYear;
	private String eduBg;
	private String loc;
	private String queryWord;
	private String workType;
	private String companyType;
	private String salary;
	private String financingStage;
	private String companySize;
	private String releaseDate;
	private Integer grabPages;
	private Map<String, Object> siteAndUrl = new HashMap<String, Object>();
	private Integer persist; 
	public Integer getInfoId() {
		return infoId;
	}
	public Integer getPersist() {
		return persist;
	}
	public void setPersist(Integer persist) {
		this.persist = persist;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getEduBg() {
		return eduBg;
	}
	public void setEduBg(String eduBg) {
		this.eduBg = eduBg;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getQueryWord() {
		return queryWord;
	}
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getFinancingStage() {
		return financingStage;
	}
	public void setFinancingStage(String financingStage) {
		this.financingStage = financingStage;
	}
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	public Integer getGrabPages() {
		return grabPages;
	}
	public void setGrabPages(Integer grabPages) {
		this.grabPages = grabPages;
	}
	
	public ScheduleInfo(Integer infoId, String workYear, String eduBg, String loc, String queryWord, String workType,
			String companyType, String salary, String financingStage, String companySize, String releaseDate,
			Integer grabPages, Map<String, Object> siteAndUrl) {
		super();
		this.infoId = infoId;
		this.workYear = workYear;
		this.eduBg = eduBg;
		this.loc = loc;
		this.queryWord = queryWord;
		this.workType = workType;
		this.companyType = companyType;
		this.salary = salary;
		this.financingStage = financingStage;
		this.companySize = companySize;
		this.releaseDate = releaseDate;
		this.grabPages = grabPages;
		this.siteAndUrl = siteAndUrl;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Map<String, Object> getSiteAndUrl() {
		return siteAndUrl;
	}
	public void setSiteAndUrl(Map<String, Object> siteAndUrl) {
		this.siteAndUrl = siteAndUrl;
	}
	@Override
	public String toString() {
		return "ScheduleInfo [infoId=" + infoId + ", workYear=" + workYear + ", eduBg=" + eduBg + ", loc=" + loc
				+ ", queryWord=" + queryWord + ", workType=" + workType + ", companyType=" + companyType + ", salary="
				+ salary + ", financingStage=" + financingStage + ", companySize=" + companySize + ", releaseDate="
				+ releaseDate + ", grabPages=" + grabPages + ", siteAndUrl=" + siteAndUrl + ", persist=" + persist
				+ "]";
	}
	
}
