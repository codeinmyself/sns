package com.puckteam.sns.base.result;

public class OutterResult {

	private int code;
	private String message;
	private Object content;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public OutterResult() {
		super();
	}
	
	public OutterResult(int code) {
		super();
		this.code = code;
	}
	
	public OutterResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public OutterResult(int code, String message, Object content) {
		super();
		this.code = code;
		this.message = message;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "OutterResult [code=" + code + ", message=" + message + ", content=" + content + "]";
	}
	
}
