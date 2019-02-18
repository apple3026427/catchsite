package com.catchsite.catchwork;

import java.util.List;

import com.catchsite.beans.JobInfo;
import com.catchsite.beans.ScheduleInfo;

public interface GrabJobByScheduleInfo {
	List<JobInfo> doGrabJob(ScheduleInfo info);
}
