package com.catchsite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.catchsite.beans.JobInfo;

@Mapper
public interface SchdInfoDao {
	
	@Insert("insert into job values(null, #{jobName}, #{salary}, #{workYear}, #{eduBg}, #{loc}, #{companyName},"
			+ " #{companyFullName}, #{companyType}, #{financingStage}, #{companySize}, #{releaseDate}, #{grabDate}, "
			+ "#{jobDesc}, #{originSite}, #{username}, #{taskId})")
	Integer insertJobInfo(JobInfo job);
	
	@Select("select * from job")
	List<JobInfo> selectInfo();
	
	Integer insertJobInfoList(List<JobInfo> list);
}
