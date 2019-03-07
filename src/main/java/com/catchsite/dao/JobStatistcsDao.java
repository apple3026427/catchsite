package com.catchsite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.catchsite.beans.statistcs.JobStatistics;

@Mapper
public interface JobStatistcsDao {
	@Insert("insert into job_ststcs values(null, #{e102Count}, #{e103Count}, #{e104Count}, "
			+ "#{e105Count}, #{e106Count}, #{e107Count}, #{totalCount}, #{avgE102Sal}, "
			+ "#{avgE103Sal}, #{avgE104Sal}, #{avgE105Sal}, #{avgE106Sal}, #{avgE107Sal}, "
			+ "#{avgSalary}, #{countDate})")
	int saveStcs(JobStatistics statistics);
	
	@Select("select * from job_ststcs")
	List<JobStatistics> getStcs();
}
