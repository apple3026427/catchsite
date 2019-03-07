package com.catchsite.beans;

public enum StatusCode {
	SUCCESS(1,"OK"),
	ERROR(0, "server error"),
	UNKNOWN(2, "");
	private Integer code;
	private String msg;
	private StatusCode(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
