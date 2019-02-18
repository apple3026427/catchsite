package com.catchsite.catchwork.anaylyzetool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzePageTool {
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
	
	public static void main(String[] args) {
		String string = "北京3-5年本科";
		String[] re = splitWorkRequireForZHIPIN(string);
		for(int i=0; i<3; i++) {
			System.out.println(re[i]);
		}
	}
}
