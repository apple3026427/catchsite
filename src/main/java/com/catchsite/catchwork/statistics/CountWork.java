package com.catchsite.catchwork.statistics;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.statistcs.JobStatistics;
import com.catchsite.utils.DateUtil;

/**
 * 统计相关工作
 * @author 王睿
 * @date 2019年3月7日 下午1:55:07
 */
public class CountWork {
	
	/**
	 * 循环遍历list进行统计
	 * @param list
	 * @return 返回一个统计实体类，包括各类统计信息
	 */
	public static JobStatistics countJob(List<JobInfo> list) {
		JobStatistics statistics = new JobStatistics();
		statistics.setTotalCount(list.size());
		Integer e_102Count = 0;
		Integer e_103Count = 0;
		Integer e_104Count = 0;
		Integer e_105Count = 0;
		Integer e_106Count = 0;
		Integer e_107Count = 0;
		
		int E102SalSum = 0;
		int E103SalSum = 0;
		int E104SalSum = 0;
		int E105SalSum = 0;
		int E106SalSum = 0;
		int E107SalSum = 0;
		for(JobInfo jobInfo : list) {
			String workYear = jobInfo.getWorkYear();
			if(workYear.equals("应届生")) {
				e_102Count += 1;
				E102SalSum += CountWork.computeSal(jobInfo.getSalary());
			}
			if(workYear.equals("1年以内")) {
				e_103Count += 1;
				E103SalSum += CountWork.computeSal(jobInfo.getSalary());
			}
			if(workYear.equals("1-3年")) {
				e_104Count += 1;
				E104SalSum += CountWork.computeSal(jobInfo.getSalary());
			}
			if(workYear.equals("3-5年")) {
				e_105Count += 1;
				E105SalSum += CountWork.computeSal(jobInfo.getSalary());
			}
			if(workYear.equals("5-10年")) {
				e_106Count += 1;
				E106SalSum += CountWork.computeSal(jobInfo.getSalary());
			}
			if(workYear.equals("10年以上")) {
				e_107Count += 1;
				E107SalSum += CountWork.computeSal(jobInfo.getSalary());
			}
		}
		statistics.setE102Count(e_102Count);
		statistics.setE103Count(e_103Count);
		statistics.setE104Count(e_104Count);
		statistics.setE105Count(e_105Count);
		statistics.setE106Count(e_106Count);
		statistics.setE107Count(e_107Count);
		statistics.setAvgE102Sal(E102SalSum / e_102Count);
		statistics.setAvgE103Sal(E103SalSum / e_103Count);
		statistics.setAvgE104Sal(E104SalSum / e_104Count);
		statistics.setAvgE105Sal(E105SalSum / e_105Count);
		statistics.setAvgE106Sal(E106SalSum / e_106Count);
		statistics.setAvgE107Sal(E107SalSum / e_107Count);
		statistics.setTotalCount(list.size());
		statistics.setAvgSalary((E102SalSum + E102SalSum + E102SalSum + E102SalSum + E102SalSum
				+ E102SalSum) / list.size() );
		statistics.setCountDate(DateUtil.convertDate(new Date()));
		return statistics;
	}
	
	/**
	 * 把“xxk-xxk"格式的薪水转化为具体的整数形式的薪水
	 * @param sal
	 * @return
	 */
	public static Integer computeSal(String sal) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(sal);
		matcher.find();
		Integer lowSal = Integer.valueOf(matcher.group()) * 1000;
		matcher.find();
		Integer highSal = Integer.valueOf(matcher.group()) * 1000;
		return (lowSal + highSal) / 2;
	}
	public static void main(String[] args) {
		System.out.println(CountWork.computeSal("6k-23k"));
	}
}
