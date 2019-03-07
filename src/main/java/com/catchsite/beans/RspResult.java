package com.catchsite.beans;

import java.util.ArrayList;

public class RspResult {
	private Object data;
	private StatusCode status;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public StatusCode getStatus() {
		return status;
	}
	public void setStatus(StatusCode status) {
		this.status = status;
	}
	
	public RspResult(Object data, StatusCode status, String msg) {
		this.data = data;
		this.status = status;
		this.msg = msg;
	}
	
	public RspResult(Object data, StatusCode status) {
		this.data = data;
		this.status = status;
	}
	
	public RspResult(Object data) {
		this.data = data;
		this.status = StatusCode.SUCCESS;
		this.msg = StatusCode.SUCCESS.getMsg();
	}
	
//	public RspResult(StatusCode status) {
//		data = (T) new ArrayList<>();
//		this.status = status;
//	}
//	
//	public RspResult(StatusCode status, String msg) {
//		this.status = status;
//		this.msg = msg;
//		data = (T) new ArrayList<>();
//	}
}
