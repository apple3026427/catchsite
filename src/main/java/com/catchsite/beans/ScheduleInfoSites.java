package com.catchsite.beans;

public class ScheduleInfoSites {
	private Integer infoId;
	private String siteName;
	private String siteUrl;
	private Integer grabNum;
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public Integer getGrabNum() {
		return grabNum;
	}
	public void setGrabNum(Integer grabNum) {
		this.grabNum = grabNum;
	}
	@Override
	public String toString() {
		return "ScheduleInfoSites [infoId=" + infoId + ", siteName=" + siteName + ", siteUrl=" + siteUrl + ", grabNum="
				+ grabNum + "]";
	}
	public ScheduleInfoSites(Integer infoId, String siteName, String siteUrl, Integer grabNum) {
		super();
		this.infoId = infoId;
		this.siteName = siteName;
		this.siteUrl = siteUrl;
		this.grabNum = grabNum;
	}
	public ScheduleInfoSites() {
		super();
		// TODO Auto-generated constructor stub
	}
}
