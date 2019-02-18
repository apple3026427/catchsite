package com.catchsite.beans;

public class JobInfo {
	private Integer jobId;
	private String jobName;
	private String salary;
	private String workYear;
	private String eduBg;
	private String loc;
	private String companyName;
	private String companyFullName;
	private String companyInfo;
	private String releaseDate;
	private String grabDate;
	private String jobDesc;
	private String originSite;
	
	
	
	@Override
	public String toString() {
		return "JobInfo [jobId=" + jobId + ", jobName=" + jobName + ", salary=" + salary + ", workYear=" + workYear
				+ ", eduBg=" + eduBg + ", loc=" + loc + ", companyName=" + companyName + ", companyFullName="
				+ companyFullName + ", companyInfo=" + companyInfo + ", releaseDate=" + releaseDate + ", grabDate="
				+ grabDate + ", jobDesc=" + jobDesc + ", originSite=" + originSite + "]";
	}
	public JobInfo(Integer jobId, String jobName, String salary, String workYear, String eduBg, String loc,
			String companyName, String companyFullName, String companyInfo, String releaseDate, String grabDate,
			String jobDesc, String originSite) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.salary = salary;
		this.workYear = workYear;
		this.eduBg = eduBg;
		this.loc = loc;
		this.companyName = companyName;
		this.companyFullName = companyFullName;
		this.companyInfo = companyInfo;
		this.releaseDate = releaseDate;
		this.grabDate = grabDate;
		this.jobDesc = jobDesc;
		this.originSite = originSite;
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
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public JobInfo() {
		super();
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyFullName() {
		return companyFullName;
	}
	public void setCompanyFullName(String companyFullName) {
		this.companyFullName = companyFullName;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getGrabDate() {
		return grabDate;
	}
	public void setGrabDate(String grabDate) {
		this.grabDate = grabDate;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getOriginSite() {
		return originSite;
	}
	public void setOriginSite(String originSite) {
		this.originSite = originSite;
	}
}
