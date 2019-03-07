package com.catchsite.beans.statistcs;

public class JobStatistics {
	private Integer totalCount;
	// 工作年限要求为应届生的要求统计
	private Integer e102Count;
	// 工作年限要求为1年以内的要求统计
	private Integer e103Count;
	// 1-3年的统计
	private Integer e104Count;
	private Integer e105Count;
	private Integer e106Count;
	private Integer e107Count;
	//应届生平均工资统计
	private Integer avgE102Sal;
	//1年以内平均工资统计
	private Integer avgE103Sal;
	//1-3年平均工资
	private Integer avgE104Sal;
	private Integer avgE105Sal;
	private Integer avgE106Sal;
	private Integer avgE107Sal;
	//所有工作的平均工资
	private Integer avgSalary;

	// 统计日期，也即抓取日期
	private String countDate;

	@Override
	public String toString() {
		return "JobStatistics [totalCount=" + totalCount + ", e102Count=" + e102Count + ", e103Count=" + e103Count
				+ ", e104Count=" + e104Count + ", e105Count=" + e105Count + ", e106Count=" + e106Count + ", e107Count="
				+ e107Count + ", avgE102Sal=" + avgE102Sal + ", avgE103Sal=" + avgE103Sal + ", avgE104Sal=" + avgE104Sal
				+ ", avgE105Sal=" + avgE105Sal + ", avgE106Sal=" + avgE106Sal + ", avgE107Sal=" + avgE107Sal
				+ ", avgSalary=" + avgSalary + ", countDate=" + countDate + "]";
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getE102Count() {
		return e102Count;
	}

	public void setE102Count(Integer e_102Count) {
		this.e102Count = e_102Count;
	}

	public Integer getE103Count() {
		return e103Count;
	}

	public void setE103Count(Integer e_103Count) {
		this.e103Count = e_103Count;
	}

	public Integer getE104Count() {
		return e104Count;
	}

	public void setE104Count(Integer e_104Count) {
		this.e104Count = e_104Count;
	}

	public Integer getE105Count() {
		return e105Count;
	}

	public void setE105Count(Integer e_105Count) {
		this.e105Count = e_105Count;
	}

	public Integer getE106Count() {
		return e106Count;
	}

	public void setE106Count(Integer e_106Count) {
		this.e106Count = e_106Count;
	}

	public Integer getE107Count() {
		return e107Count;
	}

	public void setE107Count(Integer e_107Count) {
		this.e107Count = e_107Count;
	}

	public Integer getAvgE102Sal() {
		return avgE102Sal;
	}

	public void setAvgE102Sal(Integer avgE102Sal) {
		this.avgE102Sal = avgE102Sal;
	}

	public Integer getAvgE103Sal() {
		return avgE103Sal;
	}

	public void setAvgE103Sal(Integer avgE103Sal) {
		this.avgE103Sal = avgE103Sal;
	}

	public Integer getAvgE104Sal() {
		return avgE104Sal;
	}

	public void setAvgE104Sal(Integer avgE104Sal) {
		this.avgE104Sal = avgE104Sal;
	}

	public Integer getAvgE105Sal() {
		return avgE105Sal;
	}

	public void setAvgE105Sal(Integer avgE105Sal) {
		this.avgE105Sal = avgE105Sal;
	}

	public Integer getAvgE106Sal() {
		return avgE106Sal;
	}

	public void setAvgE106Sal(Integer avgE106Sal) {
		this.avgE106Sal = avgE106Sal;
	}

	public Integer getAvgE107Sal() {
		return avgE107Sal;
	}

	public void setAvgE107Sal(Integer avgE107Sal) {
		this.avgE107Sal = avgE107Sal;
	}

	public Integer getAvgSalary() {
		return avgSalary;
	}

	public void setAvgSalary(Integer avgSalary) {
		this.avgSalary = avgSalary;
	}

	public String getCountDate() {
		return countDate;
	}

	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
}
