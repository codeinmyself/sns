package com.puckteam.sns.base.result;

import com.puckteam.sns.base.constant.BaseConstant;

/**
 * 内部通讯类
 * @author PoemWhite
 *
 */
public class InnerResult {

	/**
	 * 结果
	 * 0 失败
	 * 1 成功
	 */
	private int code;
	private String message;
	
	
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
	
	public InnerResult() {
		super();
	}
	
	public InnerResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	public boolean isSuccess(){
		
		if(BaseConstant.SUCCESS == getCode()){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "InnerResult [code=" + code + ", message=" + message + "]";
	}
	
	
}
