package com.catchsite.catchwork.anaylyzetool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzePageTool {
	
	/**
	 * 使用正则把工作要求（地点 工作经验 学历）切分开
	 * @param workRequire
	 * @return
	 */
	public static String[] splitWorkRequireForZHIPIN(String workRequire) {
		String[] resultStr = new String[3];
		String workYearReg = "(经验不限)|(应届生)|(1年以内)|(1-3年)|(3-5年)|(5-10年)|(10年以上)";
		Pattern workYearPattern = Pattern.compile(workYearReg);
		Matcher workYearMatcher = workYearPattern.matcher(workRequire);
		boolean findworkYear = workYearMatcher.find();
		if(findworkYear == false) {
			return null;
		}
		resultStr[0] = workRequire.substring(0, workYearMatcher.start());
		resultStr[1] = workYearMatcher.group();
		resultStr[2] = workRequire.substring(workYearMatcher.end());
		return resultStr;
	}
	
	/**
	 * 使用正则把公司信息（公司行业、融资阶段、公司规模）切分开来，融资阶段位于
	 * 字符串中间，使用正则匹配融资阶段即可
	 * @param companyInfo
	 * @return
	 */
	public static String[] splitCompanyInfoForZHIPIN(String companyInfo) {
		String[] result = new String[3];
		String financingStage = "(未融资)|(天使轮)|(A轮)|(B轮)|(C轮)|(D轮及以上)|(已上市)|(不需要融资)";
		Pattern fncStagePattern = Pattern.compile(financingStage);
		Matcher fncStageMatch = fncStagePattern.matcher(companyInfo);
		boolean matched = fncStageMatch.find();
		if(!matched) {
			String companySize = "(\\d+-\\d+人)|\\d+人以上";
			Pattern pattern = Pattern.compile(companySize);
			Matcher companySizeMatcher = pattern.matcher(companyInfo);
			boolean companySizeMatched = companySizeMatcher.find();
			if(!companySizeMatched) {
				result[0] =  companyInfo;
				return result;
			} else {
				result[0] = companyInfo.substring(0, companySizeMatcher.start());
				result[2] = companySizeMatcher.group();
				return result;
			}
		} else {
			result[0] = companyInfo.substring(0, fncStageMatch.start());
			result[1] = fncStageMatch.group();
			result[2] = companyInfo.substring(fncStageMatch.end());
			return result;
		}
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		String string = "北京3-5年本科";
		String[] re = splitWorkRequireForZHIPIN(string);
		for(int i=0; i<3; i++) {
			System.out.println(re[i]);
		}
	}
}
