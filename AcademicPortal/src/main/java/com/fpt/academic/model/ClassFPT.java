package com.fpt.academic.model;

public class ClassFPT {
	int class_id;
	String code;
	
	/**
	 * 
	 */
	public ClassFPT() {
		super();
	}
	
	/**
	 * @param class_id
	 * @param code
	 */
	public ClassFPT(int class_id, String code) {
		super();
		this.class_id = class_id;
		this.code = code;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
